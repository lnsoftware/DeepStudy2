
package com.zhiyin.byteop.core;

public class OuterClass {

    private class InnerClass {

        private int x;

        public InnerClass(int x) {
            this.x = x;
            System.out.println("inner object created");
        }

    }
}