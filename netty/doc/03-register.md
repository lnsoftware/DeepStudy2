


### io.netty.channel.AbstractChannel.AbstractUnsafe#register
由于是在主线程执行，所以eventLoop.inEventLoop()为false。
```
      AbstractChannel.this.eventLoop = eventLoop;

            if (eventLoop.inEventLoop()) {
                register0(promise);
            } else {
                try {
                    eventLoop.execute(new Runnable() {
                        @Override
                        public void run() {
                            register0(promise);
                        }
                    });
                } catch (Throwable t) {
                    logger.warn(
                            "Force-closing a channel whose registration task was not accepted by an event loop: {}",
                            AbstractChannel.this, t);
                    closeForcibly();
                    closeFuture.setClosed();
                    safeSetFailure(promise, t);
                }
            }
```

### io.netty.util.concurrent.SingleThreadEventExecutor#execute

```
boolean inEventLoop = inEventLoop();
        if (inEventLoop) {
            addTask(task);
        } else {
            startThread();
            addTask(task);
            if (isShutdown() && removeTask(task)) {
                reject();
            }
        }

        if (!addTaskWakesUp && wakesUpForTask(task)) {
            wakeup(inEventLoop);
        }
```

由于inEventLoop为false
进入else分支，startThread -> doStartThread，它提交线程任务，最终执行io.netty.channel.nio.NioEventLoop#run，这个run方法里处理select事件（processSelectedKeys）及运行task（runAllTasks）。


addTask方法将task放入taskQueue

task会被执行runAllTasks -> pollTask -> safeExecute -> task.run
最后触发task里的register0方法


### io.netty.channel.nio.AbstractNioChannel#doRegister

```
                selectionKey = javaChannel().register(eventLoop().selector, 0, this);

```

selector注册到channel中

