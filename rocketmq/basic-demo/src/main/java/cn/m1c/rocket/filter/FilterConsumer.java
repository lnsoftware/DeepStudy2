package cn.m1c.rocket.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 
 * @ClassName: FilterConsumer 
 * @Description: 消费者
 * @author: sunqz
 * @date: 2017-6-4 上午6:38:59
 */
public class FilterConsumer {
	public static void main(String[] args) throws InterruptedException,
			MQClientException, IOException {
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
				"ConsumerGroupNamecc4");
		
		consumer.setNamesrvAddr("192.168.1.244:9876;192.168.1.245:9876;192.168.1.242:9876;192.168.1.243:9876");
		
		// 使用Java代码，在服务器做消息过滤
		String filterCode = MixAll.file2String("D:/Workspaces/rocket-simple/src/main/java/com/sun/activemq/filter/MessageFilterImpl.java");
		
		System.out.println(filterCode);
		//有了filter不用指定tag了 其实tag也可以作为过滤
		consumer.subscribe("TopicFilter7",
				"com.sun.activemq.filter.MessageFilterImpl", filterCode);
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for(MessageExt mext : msgs) {
            		try {
						System.out.println("消费了一条消息："+new String(mext.getBody(),"utf-8")+mext.getUserProperty("SequenceId"));	
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//消费失败告诉mq重新发送继续消费
						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
					} 
            	}
            	 /*
            	  * 告诉mq消费成功 
            	  */
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		
		});
		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();
		System.out.println("Consumer Started.");
	}
}
