package com.zhiyin.jagent.agent.example.buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.Serializable;
import java.lang.reflect.Modifier;

public class ClassCreationExample {

	private Class<? extends Object> createNewClass() throws Exception {
		
		return new ByteBuddy()
			.subclass(Object.class)
			.name("com.halun.example.BbGeneratedClass")
			.implement(Runnable.class)
			.implement(Serializable.class)
			.serialVersionUid(1L)
			.defineField("name", String.class, Modifier.PRIVATE + Modifier.FINAL)
			
			.defineConstructor(Modifier.PUBLIC).withParameter(String.class)
				.intercept(
						MethodCall.invoke(Object.class.getConstructor())
							.onSuper()
                            .andThen(
                            		FieldAccessor.ofField("name")
                            			.setsArgumentAt(0)
                                    )
				)

			.defineMethod("run", void.class, Modifier.PUBLIC)
				.intercept(MethodDelegation.to(MethodCallInterceptor.class))
			.defineMethod("toString", String.class, Modifier.PUBLIC)
				.intercept(FieldAccessor.ofField("name"))

			.make().load(getClass().getClassLoader()).getLoaded();
	}

	public static void main(String[] args) throws Exception {
		ClassCreationExample example = new ClassCreationExample();
		Runnable runnable = (Runnable)example.createNewClass()
				.getDeclaredConstructor(String.class)
				.newInstance("object of new class");
		new Thread(runnable).start();
	}

}

/*
 
// This is equivalent class for Byte Buddy definition above
  
package pl.halun.example;

class BbGeneratedClass implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;
	
	public BbGeneratedClass(String name) {
		this.name = name;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getId());
		System.out.println(this);
	}
	
	public String toString() {
		return name;
	}
}
*/

