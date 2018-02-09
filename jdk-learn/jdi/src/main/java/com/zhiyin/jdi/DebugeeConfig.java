package com.zhiyin.jdi;

/**
 * Created by wangqinghui on 2017/12/23.
 */
public class DebugeeConfig {

    public static class BreakpointConfig{
            public static final String BreakpointClassName = "com.zhiyin.devtools.demo.jdi.BreakpointUtil";
            public static final int BreakpointLineNum = 13;
            public static final String LocalVariableName = "random";
            public static final String ServerHost = "localhost";
            public static final String ServerPort = "5005";
    }


//    public static class HotswapConfig{
//        public static final String ClassFilePath = "/Users/hg/Github/DeepStudy2/devtools/jdi-demo/target/classes/com/zhiyin/devtools/demo/jdi/HotswapUtil.class";
//        public static final String ClassName = "com.zhiyin.devtools.demo.jdi.HotswapUtil";
//        public static final String ServerHost = "localhost";
//        public static final String ServerPort = "5005";
//    }

    public static class HotswapConfig{
        public static final String ClassFilePath = "/Users/hg/Github/DeepStudy2/devtools/jdi-demo/target/classes/com/zhiyin/devtools/demo/jdi/HotSpringUtil.class";
        public static final String ClassName = "com.zhiyin.devtools.demo.jdi.HotSpringUtil";
        public static final String ServerHost = "localhost";
        public static final String ServerPort = "5005";
    }


}
