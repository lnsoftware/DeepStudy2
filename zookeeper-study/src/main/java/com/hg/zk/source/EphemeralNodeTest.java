package com.hg.zk.source;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.io.Closeables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.KillSession;
import org.apache.curator.test.TestingServer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EphemeralNodeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EphemeralNodeTest.class);
    private static final String PATH = "/foo";
    private static final int NUM_ATTEMPTS = 3;
    private static final int CREATE_TIMEOUT_IN_SECONDS = 10;
    private static final int DELETE_TIMEOUT_IN_SECONDS = 10;
    private static final int RECREATE_TIMEOUT_IN_SECONDS = 10;  // If this is made to be 20, the test seems to always pass.

    private final TestingServer _testingServer;
    private final CuratorFramework _curator;
    private final CuratorFramework _verificationCurator;

    public static void main(String[] args) throws Exception {
        int i = 0;
        while (++i > 0) {
            long startTime = System.currentTimeMillis();
            EphemeralNodeTest test = null;

            try {
                LOGGER.info("");
                LOGGER.info("###");
                LOGGER.info("### STARTING ITERATION #{}", i);
                LOGGER.info("###");
                LOGGER.info("");

                test = new EphemeralNodeTest();
                test.run();
            } catch (Exception e) {
                LOGGER.info("Caught:", e);
                throw e;
            } finally {
                if (test != null) {
                    LOGGER.info("Calling test.cleanup()...");
                    test.cleanup();
                    LOGGER.info("Finished test.cleanup().");
                }

                LOGGER.info("");
                LOGGER.info("###");
                double duration = (System.currentTimeMillis() - startTime) / 1000.;
                LOGGER.info("### FINISHED ITERATION #{} (duration: {}s)", i, duration);
                LOGGER.info("###");
                LOGGER.info("");
                LOGGER.info(Strings.repeat("=", 100));
            }
        }
    }

    public EphemeralNodeTest() throws Exception {
        _testingServer = log("Creating TestingServer", new Callable<TestingServer>() {
            @Override
            public TestingServer call() throws Exception {
                return new TestingServer();
            }
        });

        _curator = log("Creating curator", new Callable<CuratorFramework>() {
            @Override
            public CuratorFramework call() throws Exception {
                return newCurator();
            }
        });

        _verificationCurator = log("Creating verification curator", new Callable<CuratorFramework>() {
            @Override
            public CuratorFramework call() throws Exception {
                return newCurator();
            }
        });
    }

    private void cleanup() throws Exception {
        log("Closing verification curator", new Runnable() {
            @Override
            public void run() {
                try {
                    Closeables.close(_verificationCurator,false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        log("Closing curator", new Runnable() {
            @Override
            public void run() {
                _curator.close();
//                Closeables.close(_curator,false);
            }
        });

        log("Closing TestingServer", new Runnable() {
            @Override
            public void run() {

                try {
                    _testingServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Closeables.closeQuietly(_testingServer);
            }
        });
    }

    private void run() throws Exception {
        LOGGER.info("STARTING...");

        log("Registering connection listener", new Runnable() {
            @Override
            public void run() {
                _curator.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                    @Override
                    public void stateChanged(CuratorFramework client, ConnectionState newState) {
                        if (newState == ConnectionState.RECONNECTED) {
                            new Thread() {
                                @Override
                                public void run() {
                                    boolean success = createEphemeralNode(PATH);
                                    LOGGER.info("Recreated node: {}", success);
                                }
                            }.start();
                        }
                    }
                });
            }
        });

        try {
            boolean success = log("Registering " + PATH, new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return createEphemeralNode(PATH);
                }
            });
            assertTrue(success);

            // Wait until the node is visible to the verification curator...
            boolean visible = log("Waiting until " + PATH + " is visible to verification curator", new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    Trigger trigger = new Trigger();
                    Stat stat = _verificationCurator
                            .checkExists()
                            .usingWatcher(trigger)
                            .forPath(PATH);
                    return (stat != null) || trigger.waitUntilFired(CREATE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                }
            });
            assertTrue(visible);

            final Trigger deletionTrigger = log("Registering deletion trigger with verification curator", new Callable<Trigger>() {
                @Override
                public Trigger call() throws Exception {
                    Trigger trigger = new Trigger();
                    _verificationCurator
                            .checkExists()
                            .usingWatcher(trigger)
                            .forPath(PATH);
                    return trigger;
                }
            });

            // Kill the main curator session, thus cleaning up the node...
            log("Killing main curator session", new Runnable() {
                @Override
                public void run() {
                    killSession(_curator);
                }
            });

            boolean deleted = log("Waiting for " + PATH + " to be deleted", new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return deletionTrigger.waitUntilFired(DELETE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                }
            });
            assertTrue(deleted);

            // Now put a watch in the background looking to see if it gets created...
            boolean recreated = log("Waiting for " + PATH + " to be recreated", new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    Trigger trigger = new Trigger();
                    Stat stat = _verificationCurator
                            .checkExists()
                            .usingWatcher(trigger)
                            .forPath(PATH);
                    return (stat != null) || trigger.waitUntilFired(RECREATE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                }
            });
            assertTrue(recreated);
        } finally {
            LOGGER.info("FINISHED.");
        }
    }

    private boolean createEphemeralNode(String path) {
        for (int i = 0; i < NUM_ATTEMPTS; i++) {
            try {
                _curator.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(path);
                return true;
            } catch (KeeperException.NodeExistsException e) {
                LOGGER.info("i:{}, KeeperException.NodeExistsException in register: {}", i, e.getMessage());
                // Sometimes a server can restart faster than ZooKeeper can notice and clean up the ephemeral node.  So
                // when this happens we won't be able to create a new ephemeral node because one is already there.  This
                // is problematic because the existing ephemeral node isn't tied to our session with ZooKeeper and thus
                // not tied to our lifetime.  So in order to make sure that we end up creating a node tied to our
                // lifetime we will delete the existing node and create a new one from our session.
                if (!deleteNode(path)) {
                    // We weren't able to delete the node after trying multiple times.  Propagate the original
                    // exception to our caller as a RuntimeException.
                    throw Throwables.propagate(e);
                }
            } catch (Exception e) {
                LOGGER.info("i:{}, Ignored exception in register", i);
                LOGGER.info("Exception", e);
            }
        }

        return false;
    }

    private boolean deleteNode(String path) {
        for (int i = 0; i < NUM_ATTEMPTS; i++) {
            try {
                _curator.delete().forPath(path);
                return true;
            } catch (Exception e) {
                LOGGER.info("i:{}, Ignored exception in deleteNode", i);
                LOGGER.info("Exception:", e);
            }
        }

        return false;
    }

    public void killSession(CuratorFramework curator) {
        try {
            KillSession.kill(curator.getZookeeperClient().getZooKeeper(), _testingServer.getConnectString());
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private CuratorFramework newCurator() throws Exception {
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(_testingServer.getConnectString())
                .retryPolicy(new RetryNTimes(0, 0))
                .threadFactory(new ThreadFactoryBuilder().setDaemon(true).build())
                .build();
        curator.start();
        return curator;
    }

    private static <T> T log(String name, Callable<T> callable) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("{}: STARTING", name);
        try {
            return callable.call();
        } catch (Exception e) {
            LOGGER.info(name + " Exception", e);
            throw e;
        } finally {
            double duration = (System.currentTimeMillis() - startTime) / 1000.;
            LOGGER.info("{}: FINISHED (duration: {}s)", name, duration);
        }
    }

    private static void log(String name, final Runnable runnable) throws Exception {
        log(name, new Callable<Void>() {
            @Override
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }

    private void assertTrue(boolean expression) {
        if (!expression) {
            throw new RuntimeException("FAILURE");
        }
    }

    private static final class Trigger implements Watcher {
        private final CountDownLatch _latch = new CountDownLatch(1);

        @Override
        public void process(WatchedEvent event) {
            _latch.countDown();
        }

        public boolean waitUntilFired(long duration, TimeUnit unit) {
            try {
                return _latch.await(duration, unit);
            } catch (InterruptedException e) {
                throw Throwables.propagate(e);
            }
        }
    }
}
