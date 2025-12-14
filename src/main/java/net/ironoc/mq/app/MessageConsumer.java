package net.ironoc.mq.app;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumer {

	private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	public void consume(String queueName, Connection connection) {
		try {
			// creating a channel with first_queue
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);

			logger.info("{N|T} Waiting for messages.");

			// creating the Consumer that will receive a message and convert
			// to String
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					logger.info("Java Queue - Message Received '" + message + "'");
				}
			};
			// loop that waits for a message
			channel.basicConsume(queueName, true, consumer);
		} catch (Exception e) {
			logger.error("Unexpected exception occurred: ", e.getMessage());
		}
	}
}
