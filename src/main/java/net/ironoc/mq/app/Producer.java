package net.ironoc.mq.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	public void publish(String message, String queueName, Connection connection) {
		try {
			// creating a queue named first_queue
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);
	
			// publishing the message (bytes)
			channel.basicPublish("", queueName, null, message.getBytes());
	
			logger.info("Java Queue - Message RabbitMQ Java Sent: '" + message + "'");
	
			channel.close();
		} catch (Exception e) {
			logger.error("Unexpected error occurred publishing message to MQ instance", e);
		}
	}
}
