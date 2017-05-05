package com.zhiyin.event.producer;


import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.zhiyin.event.core.factory.EventBuilderFactory;
import com.zhiyin.event.core.EventEntity;

public class EventProducerTest   {

	@Test
	public void testPublish() throws InterruptedException {
		
		EventProducer instance = EventProducer.getInstance("failover:(tcp://localhost:61616)?jms.prefetchPolicy.queuePrefetch=1");

		EventEntity event = EventBuilderFactory.buildContentAddEvent();
		instance.publish(event);
		TimeUnit.SECONDS.sleep(10);
	}
	
	

	@Test
	public void testPublishFull() throws InterruptedException {
		int num=1000;
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		System.out.println("开始 "+startMili);
	
		
		EventProducer instance = EventProducer.getInstance("failover:(tcp://localhost:61616)?jms.prefetchPolicy.queuePrefetch=1");

		for(int i=0; i<num ;i++){			
			EventEntity event = EventBuilderFactory.buildContentAddEvent();
			instance.publish(event);
		}
		long endMili=System.currentTimeMillis();
		System.out.println(endMili - startMili);

	}

}
