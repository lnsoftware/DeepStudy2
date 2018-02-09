package com.zhiyin.spring.aop.demo4.intercept;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切面
 * @author guo
 *
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Component
public class MyInterceptor {
	/*
	 * A pointcut declaration has two parts: a signature comprising a name and any parameters, and a pointcut expression that determines exactly which method executions we are interested in. 
	 */
	// 拦截PersonServiceImpl类中所有方法,任意参数,任意返回值类型.
	@Pointcut("execution (* com.zhiyin.spring.aop.demo4.service.impl.PersonServiceImpl.*(..))")
	public void anyMethod(){ 
	}
	
	// 前置通知
	@Before("anyMethod() && args(username)")
	public void before(String username){ // 参数名要和切入点表达式中的参数名一致
		System.out.println("前置通知:" + username);
	}
	
	// 后置通知
	@AfterReturning(pointcut="anyMethod()",returning="result")
	public void afterReturning(String result){
		System.out.println("后置通知:" + result);
	}
	
	// 例外通知
	@AfterThrowing(pointcut="anyMethod()",throwing="e")
	public void afterThrowing(Exception e){
		System.out.println("例外通知:" + e.getLocalizedMessage());
	}

	// 最终通知
	@After("anyMethod()")
	public void after(){
		System.out.println("最终通知");
	}
	
	// 环绕通知
	@Around("anyMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		//if(用户是否有权限){
			System.out.println("环绕通知进入");
			Object result = pjp.proceed(); // 必须执行这个方法,否则业务方法不会被执行
			System.out.println("环绕通知退出");
			return result;
		//}
	}
}
