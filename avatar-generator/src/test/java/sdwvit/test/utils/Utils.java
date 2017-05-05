package sdwvit.test.utils;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static final String accepted = "Condition [%s] was fulfilled";
    private static final String rejected = "Condition [%s] was not fulfilled";

    private static void logFulfilled(Logger logger, String taskDescription) {
        logger.info(String.format(accepted, taskDescription));
    }

    private static void logRejected(Logger logger, String taskDescription) {
        logger.error(String.format(rejected, taskDescription));
    }

    public static void assertTrue(Class clazz, String taskDescription, Boolean asserted) throws Exception {
        Logger logger = LoggerFactory.getLogger(clazz);
        try {
            Assert.assertTrue(taskDescription, asserted);
            logFulfilled(logger, taskDescription);
        } catch (Exception e) {
            logRejected(logger, taskDescription);
            throw new AssertionError(String.format(rejected, taskDescription), e.getCause());
        }
    }
}
