package com.ironoc.mq;

import com.ironoc.mq.app.Config;
import com.ironoc.mq.app.MessageConsumer;
import com.ironoc.mq.app.Producer;
import com.rabbitmq.client.Connection;

public class Main {

	public static void main(String[] args) {
		String queueName = args[0];
		String uri = args[1];
		
		Config config = new Config();
		
		Producer producer = new Producer();
		Connection producerConnection = config.getConnection(uri);
		producer.publish(queueName, producerConnection);
		
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
