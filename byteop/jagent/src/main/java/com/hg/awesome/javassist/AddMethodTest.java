package com.hg.awesome.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;


/**
 * javassist添加方法
 * http://blog.csdn.net/houpengfei111/article/details/8593846
 * @author wangqinghui
 *
 */
public class AddMethodTest {
   public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException{
	   
	   CtClass ctClass=ClassPool.getDefault().get("com.hg.awesome.javassist.AddMethodDemo");
	   String oldName="forJavassistTest";
	   CtMethod ctMethod=ctClass.getDeclaredMethod(oldName);
	   String newName=oldName+"$impl";
	   ctMethod.setName(newName);
	   CtMethod newMethod=CtNewMethod.copy(ctMethod,"forJavassistTest",ctClass, null);
	   StringBuffer sb=new StringBuffer();
	   sb.append("{System.out.println(\"22222222\");\n")
	     .append(newName+"($$);\n")
	     .append("System.out.println(\"11111111111\");\n}");
	   newMethod.setBody(sb.toString()); 
	   
	 
       //增加新方法  
       ctClass.addMethod(newMethod);  
      //类已经更改，注意不能使用A a=new A();，因为在同一个classloader中，不允许装载同一个类两次  
       AddMethodDemo a=(AddMethodDemo)ctClass.toClass().newInstance();  
       a.forJavassistTest();
   }
   
}




