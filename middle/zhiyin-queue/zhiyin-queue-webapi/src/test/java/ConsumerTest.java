import com.aliyun.openservices.ons.api.*;
import com.zhiyin.queue.config.QueueConfig;

import java.util.Properties;

public class ConsumerTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "CID_HG_TEST");
        properties.put(PropertyKeyConst.AccessKey,  QueueConfig.acckey);
        properties.put(PropertyKeyConst.SecretKey,  QueueConfig.seckey);
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("HG_TEST", "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("Receive: " + message);
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");
    }
}    
