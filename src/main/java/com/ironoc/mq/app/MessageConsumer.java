package com.ironoc.mq.app;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageConsumer {

	public void consume(String queueName, Connection connection) {
		try {
			// creating a channel with first_queue
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);

			System.out.println("{N|T} Waiting for messages.");

			// creating the Consumer, that will be receive a message and convert
			// to String
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println("Java Queue - Message Received '" + message + "'");
				}
			};
			// loop that waits for message
			channel.basicConsume(queueName, true, consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
