/**
 * HelloWorldDumpTest.java ����7:14:45 2012-9-10
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package org.objectweb.asm.demo;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * <p></p>
 * 
 * @author dixingxing	
 * @date 2012-9-10
 */
public class HelloWorldDumpTest {


	@Test
	public void test() throws Exception {
		//�����ֽ���
		byte[] byteCodes = HelloWorldDump.dump();
		// �����ֽ�����Class����
		Class<?> clazz = AsmUtils.defineClass("org.objectweb.asm.demo.HelloWorld", byteCodes, Thread.currentThread().getContextClassLoader());
		// ���������ɵ�Class������ֻ��ͨ��������÷�����
		Method method = clazz.getMethod("sayHello");
		method.invoke(clazz.newInstance());
	}
	

}
