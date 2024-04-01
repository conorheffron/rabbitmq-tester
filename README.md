# rabbitmq-tester
Tool for testing RabbitMQ instance with sample publisher and consumer of text messages.

# Program Arguments
```
queue-name "rabbit-mq-instance-url" "message-to-send-receive"
```

# Output
```
Java Queue - Message RabbitMQ Java Sent: 'message-to-send-receive'
{N|T} Waiting for messages.
Java Queue - Message Received 'message-to-send-receive'
```

## Technologies Used : JDK 1.8.0_42, Spring 3.1.1, Tomcat 7.0 and Maven 4.7
