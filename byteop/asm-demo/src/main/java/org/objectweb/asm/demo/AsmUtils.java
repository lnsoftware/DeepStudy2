
package org.objectweb.asm.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AsmUtils {
	private static Method defineClass;
	private static final ProtectionDomain PROTECTION_DOMAIN;

	static {
		PROTECTION_DOMAIN = AccessController.doPrivileged(new PrivilegedAction<ProtectionDomain>() {
					public ProtectionDomain run() {
						return HelloWorldDumpTest.class.getProtectionDomain();
					}
				});

		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Class loader = Class.forName("java.lang.ClassLoader"); // JVM crash w/o this
					defineClass = loader.getDeclaredMethod("defineClass",
							new Class[] { String.class, byte[].class,
									Integer.TYPE, Integer.TYPE,
									ProtectionDomain.class });
					defineClass.setAccessible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public static Class<?> defineClass(String className, byte[] b,ClassLoader loader) {
		Object[] args = new Object[] { className, b, 0,
				b.length, PROTECTION_DOMAIN };
		try {
			return (Class<?>) defineClass.invoke(loader, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public static void dumpClass(String className,byte[] b){
		try {
			FileOutputStream fos = new FileOutputStream("./gen/"+className);
			fos.write(b);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
