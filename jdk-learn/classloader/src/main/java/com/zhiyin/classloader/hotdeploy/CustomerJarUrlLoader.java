package com.zhiyin.classloader.hotdeploy;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerJarUrlLoader {


	private URLClassLoader classLoader;

	public CustomerJarUrlLoader(String filePath) {

		// filePath 是jar的绝对路径
        URL url;
		try {
			url = new URL("file:" + filePath);
			// 里面是一个url的数组，可以同时加载多个
			classLoader = new URLClassLoader(new URL[] { url });

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		List<Class> list = new ArrayList<>();
		while (true) {
			CustomerJarUrlLoader loader = new CustomerJarUrlLoader("/Users/hg/Github/DeepStudy2/jdk-learn/classloader/target/classes/test.jar");
			Class<?> clazz = loader.loadClass(HelloRequestProcessor.class.getName());
			System.out.println(clazz.getName());
			list.add(clazz);
			TimeUnit.SECONDS.sleep(3);
		}

	}
	public CustomerJarUrlLoader(String filePath,ClassLoader parent) {

		// filePath 是jar的绝对路径
		URL url;
		try {
			url = new URL("file:" + filePath);
			// 里面是一个url的数组，可以同时加载多个
			classLoader = new URLClassLoader(new URL[] { url },parent);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

//	@Override
	public Class<?> loadClass(String packageClassName) {
		Class myclass = null;
		try {
			myclass = classLoader.loadClass(packageClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return myclass;
	}

	public void addJar(String jarPath) {

		try {
			File file = new File(jarPath);
			URL url = file.toURI().toURL();

			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible();
			try {
				if (accessible == false) {
					method.setAccessible(true);
				}
				// 将当前类路径加入到类加载器中
				method.invoke(classLoader, url);
			} finally {
				method.setAccessible(accessible);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
