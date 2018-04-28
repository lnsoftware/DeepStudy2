package asmDemo.demo;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

public class AddFieldValueAdapter extends ClassVisitor {

	private String fName;

	private int fDesc;

	private boolean isFieldPresent;

	public AddFieldValueAdapter(ClassVisitor cv, String fName, int fDesc) {
		super(ASM4, cv);
		this.fName = fName;
		this.fDesc = fDesc;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		System.out.println("value====   "+value);
		if (name.equals(fName)) {
			// isFieldPresent = true;
			System.out.println("value====   "+value);
			value = fDesc;
		}
		return cv.visitField(access, name, desc, signature, value);
	}

	@Override
	public void visitEnd() {
		// if (!isFieldPresent) {
		// FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, null, null);
		// if (fv != null) {
		// fv.visitEnd();
		// }
		// }
		cv.visitEnd();
	}
}
