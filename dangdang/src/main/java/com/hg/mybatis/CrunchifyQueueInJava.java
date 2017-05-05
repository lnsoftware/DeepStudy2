package com.hg.mybatis;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wangqinghui on 2016/2/15.
 */

public class CrunchifyQueueInJava {

    static Queue<String> queue = new LinkedList<String>();
    private static CrunchifyQueueInJava queueInstance = null;
    public static CrunchifyQueueInJava getStreamInstance() {

        if (queueInstance == null) {
            queueInstance = new CrunchifyQueueInJava();
        }
        return queueInstance;
    }

    public Queue<String> get() {
        return queue;
    }

    // Inserts the specified element into this queue if it is possible to do so
    // immediately without violating capacity restrictions
    public void add(String value) {
        synchronized (queue) {
            queue.add(value);
        }
    }

    // Removes a single instance of the specified element from this collection
    public void remove(String value) {
        synchronized (queue) {
            queue.remove(value);
        }
    }

    // Retrieves and removes the head of this queue, or returns null if this
    // queue is empty.
    public String poll() {
        String data = queue.poll();
        return data;
    }

    // Returns true if this collection contains no elements
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Returns the number of elements in this collection. If this collection
    // contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE
    public int getTotalSize() {
        return queue.size();
    }
}