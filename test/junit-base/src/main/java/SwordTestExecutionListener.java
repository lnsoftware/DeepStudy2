import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.lang.reflect.Method;

public class SwordTestExecutionListener implements TestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
// TODO Auto-generated method stub
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
// TODO Auto-generated method stub
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        SwordTestData swordTestData = testContext.getTestMethod().getAnnotation(SwordTestData.class);
        System.out.println("SwordTestData=" + swordTestData);
        if (swordTestData == null)
            return;
        long tedaCaseId = swordTestData.tedaCaseId();
        beforeTestMethodProcess(testContext, tedaCaseId);
    }

    // 实现对注解方法测试数据的赋值处理
    private void beforeTestMethodProcess(TestContext testContext, long tedaCaseId) {
        Method m = testContext.getTestMethod();
        String result = "beforeTestMethod: " + m.getName() + " " + tedaCaseId;
        System.out.println(result);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        SwordTestData swordTestData = testContext.getTestMethod().getAnnotation(SwordTestData.class);
        System.out.println("SwordTestData=" + swordTestData);
        if (swordTestData == null)
            return;
        long tedaCaseId = swordTestData.tedaCaseId();
        afterTestMethodProcess(testContext, tedaCaseId);
    }

    private void afterTestMethodProcess(TestContext testContext, long tedaCaseId) {
        Method m = testContext.getTestMethod();
        String result = "afterTestMethod: " + m.getName() + " " + tedaCaseId;
        System.out.println(result);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
// TODO Auto-generated method stub
    }
}