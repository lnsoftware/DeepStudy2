package pl.halun.demo.bytebuddy.plain.examples;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Modifier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassCreationExample {

	private Class<? extends Object> createNewClass() throws Exception {

		DynamicType.Unloaded<Object> build =  new ByteBuddy()
			.subclass(Object.class)
			.name("pl.halun.example.BbGeneratedClass")
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

			.defineMethod("toString", String.class, Modifier.PUBLIC)
				.intercept(FieldAccessor.ofField("name"))
			.make();

        build.saveIn(new File("c:/temp"));

        Class<?> dynamicType = build.load(getClass().getClassLoader()).getLoaded();

		return dynamicType;
//		assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
//				.saveIn(new File("c:/tmp"))
//		dynamicType.load(getClass().getClassLoader()).getLoaded();
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

