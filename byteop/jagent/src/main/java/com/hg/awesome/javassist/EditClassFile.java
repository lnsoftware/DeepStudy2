package com.hg.awesome.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class EditClassFile {

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
//		pool.insertClassPath("E:/");
//		CtClass cc = pool.get("com.hg.awesome.javassist.HelloForEdit");
		
		CtClass cc = pool.get("Foo");
		CtMethod cm = CtNewMethod.make("public void toInt(int i){i++;}", cc);
		cc.addMethod(cm);
		cc.writeFile("E:/");
		
		
		/*
	    CtClass[] param = new CtClass[1] ;
        param[0] = pool.get("java.lang.String") ;
        //得到方法
        CtMethod method = cc.getDeclaredMethod("getMessage",param);
        //插入新的代码
        method.insertBefore("{return \"no ,\" + $1;}");//$1表示第一个参数
        //method.setBody("{return $1;}");
        //保存到文件里
        cc.writeFile("E:/");//这里把.class文件再写回它原来所在地目录，如果没有这个参数，则会在当前项目的根目录生成新的.class文件
		*/
		
	      Class c = cc.toClass();
	      HelloForEdit s = (HelloForEdit) c.newInstance();
//        System.out.println(s);
	}

}
