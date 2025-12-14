package net.ironoc.mq;

import net.ironoc.mq.app.Config;
import net.ironoc.mq.app.MessageConsumer;
import net.ironoc.mq.app.Producer;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		String queueName = args[0];
        logger.info("Queue name is {}", queueName);
		String uriStr = args[1];
        logger.info("Connection URI is {}", uriStr);
		String message = args[2];
        logger.info("Message Content is {}", message);
		
		Config config = new Config();
        URI uri = URI.create(uriStr);
		
		Producer producer = new Producer();
		Connection producerConnection = config.getConnection(uri);
		producer.publish(message, queueName, producerConnection);
		
		MessageConsumer consumer = new MessageConsumer();
		Connection consumerConnection = config.getConnection(uri);
		try {
			producerConnection.close();
			consumer.consume(queueName, consumerConnection);
		} catch (Exception e) {
			logger.error("Unexpected error occurred closing producer connection to MQ instance", e);
		}
	}
}
