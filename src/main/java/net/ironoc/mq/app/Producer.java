package net.ironoc.mq.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	
	public void publish(String message, String queueName, Connection connection) {
		try {
			// creating a queue named first_queue
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);
	
			// publishing the message (bytes)
			channel.basicPublish("", queueName, null, message.getBytes());
	
			System.out.println("Java Queue - Message RabbitMQ Java Sent: '" + message + "'");
	
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
