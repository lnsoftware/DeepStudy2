//package com.hg.awesome.javassist;
//
//import javassist.bytecode.analysis.ControlFlow;
//import javassist.bytecode.analysis.ControlFlow.Block;
//
//public class sssss {
//
//	public void ma(){
//		ControlFlow flow=new ControlFlow(m); //m is the CtMethod currently being instrumented
//		Block[] blockArray=flow.basicBlocks();
//		for(Block thisbb : blockArray){
//		 //Dynamically Update Method Statistics
//		 String blockUpdate=new String();
//		 String thisbbIndex=Integer.toString(thisbb.index());
//		 blockUpdate+=mse+".setBlockIndex("+thisbbIndex+"); ";
//		 blockUpdate="{ " + blockUpdate + "} ";
//		 //Insert
//		 int pos=m.getMethodInfo().getLineNumber(thisbb.position()); //Source code line position from binary line position
//		 System.out.print("At "+pos+": "+blockUpdate);
//		 int n=m.insertAt(pos, blockUpdate);
//		 System.out.println(" -> "+n);
//		}
//	}
//}
