package com.zhiyin.event.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author wxl24life
 *
 */
public class AmqProducerTool {
	private static Logger logger = LoggerFactory.getLogger(AmqProducerTool.class);
	ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
	private Connection connection;
	private Session session;
	private Queue queue;
	private MessageProducer producer;
	private final String brokerUrl;
	private final String queueName;
	private final int deliveryMode;
	
	public AmqProducerTool(String brokerUrl, String queueName) throws JMSException {
		this(brokerUrl, queueName, DeliveryMode.NON_PERSISTENT);
	}
	
	public AmqProducerTool(String brokerUrl, String queueName, int deliveryMode) throws JMSException  {
		this.brokerUrl = brokerUrl;
		this.queueName = queueName;
		this.deliveryMode = deliveryMode;
		cf.setBrokerURL(this.brokerUrl);
		getProducer().setDeliveryMode(this.deliveryMode);
		getConnection().start(); // if connection not started, this m
	}
	
	public void sendMessage(String messageString) throws JMSException {
		TextMessage textMessage = getSession().createTextMessage(messageString);
		getProducer().send(textMessage);
	}
	
	public void closeProducer() {
		try {
			if (producer != null) {
				producer.close(); // the producer is also closed 
			}
		} catch (JMSException e) {
			logger.error("session close exception", e);
		}
	}
	
	/**
	 * Connection-shared-mode
	 * Use this method only when you want to share the same connection among messages.
	 * If you chose to call this method after one message has been sent, note that 
	 * the connetion object is not closed, so it can be shared when you want to send 
	 * another message
	 * 
	 */
	public void closeSession() {
		try {
			if (session != null) {
				session.close(); // the producer is also closed 
				session = null;
				producer = null;
			}
		} catch (JMSException e) {
			logger.error("session close exception", e);
		}
	}
	
	/**
	 * If you want to create a new connection for each message, call this method 
	 * when a message has been sent.
	 */
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
				session = null;
				producer = null;
			}
		} catch (JMSException e) {
			logger.error("connection close exception", e);
		}
	}
	
	/**
	 * Not recommanded to call this method in most scenarios
	 */
	public void close() {
		try {
			if (session != null) 
				session.close();
			if (connection != null) 
				connection.close();
		} catch (JMSException e) {
			logger.error("close exception", e);
		}
	}

	public Connection getConnection() throws JMSException {
		if (connection == null) {
			connection = cf.createConnection();
		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Session getSession() throws JMSException {
		if (session == null) {
			session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
			if (session != null) {
				System.out.println("session created successfully...");
			}
		}
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Queue getQueue() throws JMSException {
		if (queue == null)
			queue = getSession().createQueue(queueName);
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public MessageProducer getProducer() throws JMSException {
		if (producer == null)
			producer = getSession().createProducer(getQueue());
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}
	
	public String getBrokerUrl() {
		return brokerUrl;
	}

	public String getQueueName() {
		return queueName;
	}

	public int getDeliveryMode() {
		return deliveryMode;
	}
}
