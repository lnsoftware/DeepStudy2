import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;

/**
 * 运行时动态更新配置信息
 * Created by hg on 2016/10/17.
 */
public class DynamicPropertiey {

    static String CommandKey = "DyPro";
    static int loopCount = 2;

    // hystrix 默认超时时间为1s，测试自己设置默认超时时间。
    @Test
    public void testDefaultTimeoutShort() {

        // 默认超时时间长
        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            2000);

        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetFail);
        }
    }

    // 测试超时时间长
    @Test
    public void testDefaultTimeoutLong() {

        // 默认超时时间长
        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            3000);
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(2000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetSucc);
        }

    }

    @Test
    public void testDefaultTimeoutLongToShort() {

        // 默认超时时间长
        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            4000);
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetSucc);
        }

        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            2000);
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetFail);
        }
    }

    //
    @Test
    public void testDefaultTimeoutLongToShort2() {

        // 默认超时时间长
        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            2000);
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetFail);
            Assert.assertTrue(command.getProperties().executionTimeoutInMilliseconds().get() == 2000);
        }

        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command." + CommandKey + ".execution.isolation.thread.timeoutInMilliseconds",
            "4000");
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetSucc);
        }

        ConfigurationManager.getConfigInstance().setProperty(
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
            2000);
        for (int i = 0; i < loopCount; i++) {
            TimeOutCommandTime command = new TimeOutCommandTime(3000);
            String result = command.execute();
            Assert.assertEquals(result, TimeOutCommandTime.RetSucc);
            Assert.assertTrue(command.getProperties().executionTimeoutInMilliseconds().get() == 4000);
        }
    }

    static class TimeOutCommandTime extends HystrixCommand<String> {

        public static String RetSucc = "ok";
        public static String RetFail = "fail";
        private Integer sleepMilliSecond;

        public TimeOutCommandTime(Integer sleepMilliSecond) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup")).andCommandKey(HystrixCommandKey.Factory.asKey("timeout"))
                .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter()
                        .withRequestLogEnabled(true)
                ));
            this.sleepMilliSecond = sleepMilliSecond;
        }

        @Override
        protected String getFallback() {
            return RetFail;
        }

        @Override
        protected String run() throws Exception {
            TimeUnit.MILLISECONDS.sleep(sleepMilliSecond);
            return RetSucc;
        }
    }

}
