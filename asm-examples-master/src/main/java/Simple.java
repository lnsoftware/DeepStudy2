import org.objectweb.asm.ClassWriter;

public class Simple implements ClassGenerator {
 // Helpful constants
 private static final String GEN_CLASS_NAME = "GetterSetter";
 private static final String GEN_CLASS_STR = PKG_STR + GEN_CLASS_NAME;

 @Override
 public byte[] generateClass() {
   ClassWriter cw = new ClassWriter(0);
   CheckClassAdapter cv = new CheckClassAdapter(cw);
   // Visit the class header
   cv.visit(V1_7, ACC_PUBLIC, GEN_CLASS_STR, null, J_L_O, new String[0]);
   generateGetterSetter(cv);
   generateCtor(cv);
   cv.visitEnd();
   return cw.toByteArray();
 }

 private void generateGetterSetter(ClassVisitor cv) {
   // Create the private field myInt of type int. Effectively:
   // private int myInt;
   cv.visitField(ACC_PRIVATE, "myInt", "I", null, 1).visitEnd();

   // Create a public getter method
   // public int getMyInt();
   MethodVisitor getterVisitor = 
      cv.visitMethod(ACC_PUBLIC, "getMyInt", "()I", null, null);
   // Get ready to start writing out the bytecode for the method
   getterVisitor.visitCode();
   // Write ALOAD_0 bytecode (push the this reference onto stack)
   getterVisitor.visitVarInsn(ALOAD, 0);
   // Write the GETFIELD instruction, which uses the instance on
   // the stack (& consumes it) and puts the current value of the
   // field onto the top of the stack
   getterVisitor.visitFieldInsn(GETFIELD, GEN_CLASS_STR, "myInt", "I");
   // Write IRETURN instruction - this returns an int to caller.
   // To be valid bytecode, stack must have only one thing on it
   // (which must be an int) when the method returns
   getterVisitor.visitInsn(IRETURN);
   // Indicate the maximum stack depth and local variables this
   // method requires
   getterVisitor.visitMaxs(1, 1);
   // Mark that we've reached the end of writing out the method
   getterVisitor.visitEnd();

   // Create a setter
   // public void setMyInt(int i);
   MethodVisitor setterVisitor = 
       cv.visitMethod(ACC_PUBLIC, "setMyInt", "(I)V", null, null);
   setterVisitor.visitCode();
   // Load this onto the stack
   setterVisitor.visitVarInsn(ALOAD, 0);
   // Load the method parameter (which is an int) onto the stack
   setterVisitor.visitVarInsn(ILOAD, 1);
   // Write the PUTFIELD instruction, which takes the top two 
   // entries on the execution stack (the object instance and
   // the int that was passed as a parameter) and set the field 
   // myInt to be the value of the int on top of the stack. 
   // Consumes the top two entries from the stack
   setterVisitor.visitFieldInsn(PUTFIELD, GEN_CLASS_STR, "myInt", "I");
   setterVisitor.visitInsn(RETURN);
   setterVisitor.visitMaxs(2, 2);
   setterVisitor.visitEnd();
 }

 private void generateCtor(ClassVisitor cv) {
   // Constructor bodies are methods with special name 
   MethodVisitor mv = 
       cv.visitMethod(ACC_PUBLIC, INST_CTOR, VOID_SIG, null, null);
   mv.visitCode();
   mv.visitVarInsn(ALOAD, 0);
   // Invoke the superclass constructor (we are basically 
   // mimicing the behaviour of the default constructor 
   // inserted by javac)
   // Invoking the superclass constructor consumes the entry on the top
   // of the stack.
   mv.visitMethodInsn(INVOKESPECIAL, J_L_O, INST_CTOR, VOID_SIG);
   // The void return instruction
   mv.visitInsn(RETURN);
   mv.visitMaxs(2, 2);
   mv.visitEnd();
 }

 @Override
 public String getGenClassName() {
   return GEN_CLASS_NAME;
 }
}