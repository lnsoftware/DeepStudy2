package runner;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
public class SwordRunner extends BlockJUnit4ClassRunner {
private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss_SSS");
private Class<?> clazz;
public SwordRunner(Class<?> klass) throws InitializationError {
super(klass);
this.clazz = klass;
}
@Override
protected Statement methodBlock(FrameworkMethod method) {
testLoggerProcess(method);
return super.methodBlock(method);
}
private void testLoggerProcess(FrameworkMethod method) {
Method testMethod = method.getMethod();
SwordTestLogger testLoggerAnno = testMethod.getAnnotation(SwordTestLogger.class);
if (testLoggerAnno == null)
return;
StringBuilder log = new StringBuilder(format.format(new Date()));
log.append(" ").append(testMethod.getDeclaringClass().getName()).append("#").append(testMethod.getName())
.append(": ").append(testLoggerAnno.log());// 加上注解变量log的值
System.out.println(log.toString());
}
// 拦截 BeforeClass 事件
protected Statement withBeforeClasses(final Statement statement) {
final Statement junitStatement = super.withBeforeClasses(statement);
return new Statement() {
@Override
public void evaluate() throws Throwable {
System.out.println("Before Class: " + clazz.getName());
junitStatement.evaluate();
}
};
}
// 拦截每一个方法的 Before 事件
protected Statement withBefores(final FrameworkMethod method, Object target, final Statement statement) {
final Statement junitStatement = super.withBefores(method, target, statement);
return new Statement() {
@Override
public void evaluate() throws Throwable {
System.out.println("Before before method: " + method.getName());
junitStatement.evaluate();
System.out.println("After before method: " + method.getName());
}
};
}
// 截获每一个测试方法的 after 事件
protected Statement withAfters(final FrameworkMethod method, Object target, final Statement statement) {
final Statement junitStatement = super.withAfters(method, target, statement);
return new Statement() {
@Override
public void evaluate() throws Throwable {
System.out.println("After method: " + method.getName());
junitStatement.evaluate();
}
};
}
// 截获测试类的 after 事件
protected Statement withAfterClasses(final Statement statement) {
final Statement junitStatement = super.withAfterClasses(statement);
return new Statement() {
@Override
public void evaluate() throws Throwable {
junitStatement.evaluate();
System.out.println("After Class: " + clazz.getName());
}
};
}
}