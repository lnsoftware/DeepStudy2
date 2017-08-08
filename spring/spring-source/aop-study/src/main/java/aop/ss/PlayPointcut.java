//package aop.ss;
//
//public class PlayPointcut implements Pointcut {
// public ClassFilter getClassFilter() {
//  return new PlayClassFilter();
// }
// public MethodMatcher getMethodMatcher() {
//  return new PlayMethodMatcher();
// }
//}
////PlayClassFilter的定义
//class PlayClassFilter implements ClassFilter {
// public boolean matches(Class clazz) {
//  if(clazz.getSimpleName().equals("Play"))
//   return true;
//  return false;
// }
//}
////PlayMethodMatcher的定义
//class PlayMethodMatcher implements MethodMatcher {
// public boolean isRuntime() {
//  return true;
// }
// public boolean matches(Method method, Class c) {
//  if(c.getSimpleName().equals("Play")&&method.getName().contains("Service"))
//   return true;
//  return false;
// }
// public boolean matches(Method method, Class c, Object[] args) {
//  if(c.getSimpleName().equals("Play")&&method.getName().contains("Service"))
//   return true;
//  return false;
// }
//}
//