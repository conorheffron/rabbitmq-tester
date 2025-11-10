package net.ironoc.mq;

import net.ironoc.mq.app.Config;
import net.ironoc.mq.app.MessageConsumer;
import net.ironoc.mq.app.Producer;
import com.rabbitmq.client.Connection;

import java.net.URI;

public class Main {

	public static void main(String[] args) {
		String queueName = args[0];
        System.out.println(String.format("Queue name is %s", queueName));
		String uriStr = args[1];
        System.out.println(String.format("Connection URI is %s", uriStr));
		String message = args[2];
        System.out.println(String.format("Message Content is %s", message));
		
		Config config = new Config();
        URI uri = URI.create(uriStr);
		
		Producer producer = new Producer();
		Connection producerConnection = config.getConnection(uri);
		producer.publish(message, queueName, producerConnection);
		
		MessageConsumer consumer = new MessageConsumer();
		Connection consumerConnection = config.getConnection(uri);
		consumer.consume(queueName, consumerConnection);

		try {
			producerConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
