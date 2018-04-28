package com.lee.demo.javassit;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;

/**
 * Use javassist to dynamically generate a class, write it to .class file, read it and invoke methods
 * 
 * @author hzlifan
 *
 */
public class JavassistTest {

	private static final String CLASS_FULL_NAME = "com.lee.demo.Foo";

	private static final String OUTPUT_DIR_NAME = "output";

	public void test() throws Exception {
		CtClass clazz = generateClass();
		//invokeMethod(clazz.toClass());

		writeClassToFile(clazz);
		readClassFromFileAndInvokeMethod();
	}

	/**
	 * Dynamically generate a class
	 * 
	 * @return
	 * @throws Exception
	 */
	private CtClass generateClass() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass clazz = pool.makeClass(CLASS_FULL_NAME);

		// add an int field
		CtField field1 = new CtField(CtClass.intType, "id", clazz);
		field1.setModifiers(Modifier.PRIVATE);
		clazz.addField(field1);

		// add a String field
		CtField field2 = new CtField(pool.get("java.lang.String"), "name",
				clazz);
		field2.setModifiers(Modifier.PROTECTED);
		clazz.addField(field2);

		// create a constructor
		CtConstructor con = new CtConstructor(new CtClass[] { CtClass.intType,
				pool.get("java.lang.String") }, clazz);
		con.setBody("{this.id=$1; this.name=$2;}");
		clazz.addConstructor(con);

		// create a method
		CtMethod method = CtNewMethod.make("public void test(){}", clazz);
		method.insertBefore("System.out.println(id + \" \" + name);");
		clazz.addMethod(method);

		return clazz;
	}

	/**
	 * Create an instance, and call a method
	 * 
	 * @param clazz
	 * @throws Exception
	 */
	private void invokeMethod(Class<?> clazz) throws Exception {
		Constructor<?> con = clazz.getDeclaredConstructor(new Class[] {
				int.class, String.class });
		Object obj = con.newInstance(new Object[] { 24, "fuck" });  // call a constructor
		clazz.getMethod("test").invoke(obj);  // call a method
	}

	/**
	 * Write the bytecode of a class to .class file
	 * 
	 * @param clazz
	 * @throws Exception
	 */
	private void writeClassToFile(CtClass clazz) throws Exception {
		clazz.writeFile(OUTPUT_DIR_NAME);
	}

	/**
	 * Read class from .class file and invoke methods
	 * 
	 * @throws Exception
	 */
	private void readClassFromFileAndInvokeMethod() throws Exception {
		URLClassLoader loader = new URLClassLoader(new URL[] { new URL("file:"
				+ OUTPUT_DIR_NAME + "/") });  // must put a "/" at last to specify a dir
		Class<?> clazz = loader.loadClass(CLASS_FULL_NAME);
		invokeMethod(clazz);
		loader.close();
	}

	public static void main(String[] args) throws Exception {
		JavassistTest test = new JavassistTest();
		test.test();
	}

}
