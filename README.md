![Proof HTML](https://github.com/conorheffron/rabbitmq-tester/actions/workflows/proof-html.yml/badge.svg)

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

## Technologies 
JDK 8 and Rabbit-MQ 5
