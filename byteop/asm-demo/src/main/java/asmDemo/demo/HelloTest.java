package asmDemo.demo;

public class HelloTest {

	@org.junit.Test
	public void test() {

		try {

			ClassLoader classLoader = new MyClassLoad();
			// String code ="";
//			Class<?> exampleClass = classLoader.defineClass("Example", 0, 0, 0);
			System.out.println(HelloImp.class.getName());
			Class clazz = classLoader.loadClass(HelloImp.class.getName());
			Hello example22 = (Hello) clazz.newInstance();
			// Hello example22 = (Hello) exampleClass.newInstance();
			example22.seed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
