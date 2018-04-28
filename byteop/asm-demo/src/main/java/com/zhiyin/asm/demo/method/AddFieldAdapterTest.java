package com.zhiyin.asm.demo.method;

import java.io.FileOutputStream;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;

public class AddFieldAdapterTest {

	String path = "User";
	String path2 = "/Users/hg/Github/DeepStudy2/byteop/asm-demo/target/classes/User2.class";

	@Test
	public void test() throws Exception {
		ClassReader cr = new ClassReader(path);
		ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
//		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "HELL", null, "java/lang/Object", null);
//		Method m = Method.getMethod("void <init> ()");
//		GeneratorAdapter mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
//		mg.loadThis();
//		mg.invokeConstructor(Type.getType(Object.class), m);
//		mg.returnValue();
//		mg.endMethod();

		CheckClassAdapter ca = new CheckClassAdapter(cw);
		AddFieldAdapter af = new AddFieldAdapter(ca, Opcodes.ACC_PUBLIC, "field", "I");

		cr.accept(af,ClassReader.EXPAND_FRAMES);

		byte[] code = cw.toByteArray();

		FileOutputStream fos = new FileOutputStream(path2);
		fos.write(code);
		fos.close();
	}

}
