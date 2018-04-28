package asmDemo.demo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;

public class GeneralClassAdapter extends CheckClassAdapter {

	public GeneralClassAdapter(ClassVisitor cv) {
		super(cv);

	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

}
