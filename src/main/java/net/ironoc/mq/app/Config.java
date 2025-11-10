package net.ironoc.mq.app;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URI;

public class Config {
	
	public Connection getConnection(URI uri) {
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
