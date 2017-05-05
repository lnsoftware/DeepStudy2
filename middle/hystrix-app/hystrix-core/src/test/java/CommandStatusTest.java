
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.zhiyin.hystrix.core.property.CommandConst;
import com.zhiyin.hystrix.core.property.CommandUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@Slf4j
public class CommandStatusTest {

    static class CommandThatFailsSilently extends HystrixCommand<String> {

        private final boolean throwException;
        private final Integer sleepMillSeconds;

        public CommandThatFailsSilently(boolean throwException) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.throwException = throwException;
            this.sleepMillSeconds = 100;
        }

        public CommandThatFailsSilently(Integer sleepMillSeconds) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.throwException = false;
            this.sleepMillSeconds = sleepMillSeconds;
        }

        public CommandThatFailsSilently() {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.throwException = false;
            this.sleepMillSeconds = 100;
        }

        @Override
        protected String run() {
            if (throwException) {
                throw new RuntimeException(CommandConst.FailExp);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillSeconds);
            } catch (InterruptedException e) {
            }

            return CommandConst.RetSucc;
        }

        @Override
        protected String getFallback() {
            log.info(CommandUtil.status(this).toString());
            return CommandConst.RetFail;
        }
    }

    public static class UnitTest {

        @Test
        public void testSuccess() {
            assertEquals(CommandConst.RetSucc, new CommandThatFailsSilently().execute());
        }

        @Test
        public void testFailure() {
            try {
                assertEquals(CommandConst.RetFail, new CommandThatFailsSilently(true).execute());
            } catch (HystrixRuntimeException e) {
                fail("we should not get an exception as we fail silently with a fallback");
            }
        }

        @Test
        public void timeout() {
            try {
                assertEquals(CommandConst.RetFail, new CommandThatFailsSilently(2000).execute());
            } catch (HystrixRuntimeException e) {
                fail("we should not get an exception as we fail silently with a fallback");
            }
        }
    }

}
