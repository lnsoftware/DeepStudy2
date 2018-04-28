package asmDemo.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

public class Test {
	private final static Integer inter1 = new Integer(3);
	private int inter2 = 0;

	public static void testPrintClass() throws Exception {

		ClassPrinter cp = new ClassPrinter();
		ClassReader cr;
		try {
			cr = new ClassReader("asmDemo.demo.Test");
			cr.accept(cp, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			testPrintClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
