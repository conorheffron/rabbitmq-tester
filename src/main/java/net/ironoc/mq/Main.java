package net.ironoc.mq;

import net.ironoc.mq.app.Config;
import net.ironoc.mq.app.MessageConsumer;
import net.ironoc.mq.app.Producer;
import com.rabbitmq.client.Connection;

import java.net.URI;

public class Main {

	public static void main(String[] args) {
		String queueName = args[0];
		String uriStr = args[1];
		String message = args[2];
		
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
