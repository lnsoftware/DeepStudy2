package asmDemo.demo;

import java.lang.reflect.Field;

import com.zhiyin.asm.demo.method.AddFieldAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.util.CheckClassAdapter;

public class AddFieldValueAdapterTest extends ClassLoader {
	
	public static void test() {
		ClassReader cr;
		try {
			 cr = new ClassReader("asmDemo.demo.HasFieldClass");
			ClassWriter cw = new ClassWriter(cr,0);
			cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "HasFieldClass", null, "java/lang/Object", null);
			Method m = Method.getMethod("void <init> ()");
			GeneratorAdapter mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
			mg.loadThis();
			mg.invokeConstructor(Type.getType(Object.class), m);
			mg.returnValue();
			mg.endMethod();
			CheckClassAdapter ca1 = new CheckClassAdapter(cw);
			AddFieldAdapter af = new AddFieldAdapter(ca1, Opcodes.ACC_PUBLIC, "field", "I");

			CheckClassAdapter ca = new CheckClassAdapter(cw);
			System.out.println("===== 111");
			AddFieldValueAdapter addFieldValueAdapter = new AddFieldValueAdapter(ca, "field", 1);
			System.out.println("===== 2222");
			byte[] code = cw.toByteArray();
//			FileOutputStream fos = new FileOutputStream("/Users/imac/HELL.class");
//			fos.write(code);
//			fos.close();
			// ClassLoader clazz = new MyClassLoad();
			AddFieldValueAdapterTest add = new AddFieldValueAdapterTest();
			Class<?> clazz = add.defineClass("HasFieldClass", code, 0, code.length);
			System.out.println("===== 333");
			Object obj = clazz.newInstance();
			// clazz.defineClass("Example", code, 0, code.length);
			Field[] fs = clazz.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				System.out.println("===== "+fs[i].getName());

			}
			Integer inde = clazz.getField("field").getInt(obj);
			System.out.println("======" + inde);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}
