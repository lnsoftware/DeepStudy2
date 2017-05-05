package com.hg.mybatis;

import org.jctools.queues.QueueFactory;
import org.jctools.queues.spec.ConcurrentQueueSpec;
import org.jctools.queues.spec.Ordering;
import org.jctools.queues.spec.Preference;

import java.util.Queue;

/**
 * Created by wangqinghui on 2016/2/15.
 */
public class ddd {
    public static void main(String[] args){
        ConcurrentQueueSpec spec = new ConcurrentQueueSpec(1, 1, 4, Ordering.FIFO,
                Preference.NONE);

        Queue<Integer> q = QueueFactory.newQueue(spec);

       for(int i=0;i<100;i++){
           q.offer(i);
           System.out.println( q.peek() );
//           q.poll();

       }

        System.out.println( q.size() );

    }
}
