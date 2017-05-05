package com.hg.mybatis;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
 
import com.google.common.collect.EvictingQueue;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyArrayBlockingQueueVsEvictingQueue {
 
	public static void main(String[] args) {
 
		// Test EvicktingQueue with size 10
		CrunchifyEvictingQueue();
 
		log("\n============= New LINE ==============\n");
 
		// Test ArrayBlockingQueue with size 10
		CrunchifyArrayBlockingQueue();
	}
 
	private static void CrunchifyArrayBlockingQueue() {
		ArrayBlockingQueue<String> crunchifyQueue = new ArrayBlockingQueue<String>(10);
 
		String crunchifyMsg = "This is ArrayBlockingQueue - by Crunchify";
		try {
			// We are looping for 15 times - Error once queue full
			for (int i = 1; i <= 15; i++) {
				crunchifyQueue.add(crunchifyMsg + i);
				log("ArrayBlockingQueue size: " + crunchifyQueue.size());
			}
		} catch (Exception e) {
			log("\nException Occurred: ");
			e.printStackTrace();
		}
 
	}
 
	private static void CrunchifyEvictingQueue() {
		Queue<String> crunchifyQueue = EvictingQueue.create(10);
 
		String crunchifyMsg = "This is EvictingQueue - by Crunchify";
		try {
			// We are looping for 15 times - No error after queue full.
			// Instead it will remove element from queue in FIFO order
			for (int i = 1; i <= 15; i++) {
				crunchifyQueue.add(crunchifyMsg + i);
 
				log("EvictingQueue size: " + crunchifyQueue.size());
			}
		} catch (Exception e) {
			log("Exception Occurred: " + e);
		}
 
	}
 
	private static void log(String crunchifyText) {
		System.out.println(crunchifyText);
 
	}
}