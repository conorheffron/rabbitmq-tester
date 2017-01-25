package com.ironoc.mq.app;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Config {
	
	public Connection getConnection(String uri) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setRequestedHeartbeat(30);
		factory.setConnectionTimeout(60000);
		Connection connection = null;
		try {
			factory.setUri(uri);
			connection = factory.newConnection();
		} catch (Exception e) {
			System.out.println("RabbitMQ server is Down !");
			System.out.println(e.getMessage());
		}
		return connection;
	}

}
