package net.ironoc.mq.app;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);
	
	public Connection getConnection(URI uri) {
        ConnectionFactory factory = new ConnectionFactory();

		factory.setRequestedHeartbeat(30);
		factory.setConnectionTimeout(60000);
		Connection connection = null;
		try {
			factory.setUri(uri);
			connection = factory.newConnection();
		} catch (Exception e) {
			logger.info("RabbitMQ server is Down !");
			logger.info(e.getMessage());
		}
		return connection;
	}

}
