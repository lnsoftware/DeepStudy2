//package com.zhiyin.cglib.cando;
//
//import net.sf.cglib.reflect.FastClass;
//import net.sf.cglib.reflect.FastMethod;
//import org.junit.Test;
//
///**
// * Created by hg on 2017/6/26.
// */
//public class FastClassTest2 {
//
//    @Test
//    public void testFastClass() throws Exception {
//        FastClass fastClass = FastClass.create(SampleBean.class);
//        FastMethod fastMethod = fastClass.getMethod(SampleBean.class.getMethod("getValue"));
//        MyBean myBean = new MyBean();
//        myBean.setValue("Hello cglib!");
//        assertTrue("Hello cglib!", fastMethod.invoke(myBean, new Object[0]));
//    }
//
//}
