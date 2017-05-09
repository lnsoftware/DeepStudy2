package com.hg.dubbo.study.filter;

import java.util.Arrays;
import java.util.List;

interface Filter{
    public int invoke(Invoker invoker);
}


class Filter1 implements Filter{

    public int invoke(Invoker invoker) {
        System.out.println("Filter1");
        invoker.invoke();
        return 0;
    }
}

class Filter2 implements Filter{

    public int invoke(Invoker invoker) {
        System.out.println("Filter2");
        return invoker.invoke();
    }
}

interface Invoker{  
    public int invoke();
}


public class FilterInvokdfChainDemo {
    public static void main(String[] args) {
        List<Filter> filters = Arrays.asList(new Filter1(),new Filter2());

        Invoker last = new Invoker() {
            public int invoke() {
                System.out.println("invoker");
                return 0;
            }
        };

        for(int i = filters.size() - 1; i >= 0; i--) {
            // 获取filter
            final Filter filter = filters.get(i);
            final Invoker next = last;

            // 更新last
            last = new Invoker() {
                public int invoke() {
                    return filter.invoke(next);
                }
            };
        }

        last.invoke();
    }
}