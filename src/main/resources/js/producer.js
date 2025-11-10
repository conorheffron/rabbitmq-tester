const amqp = require('amqplib');

async function sendMessage() {
    const connection = await amqp.connect('amqp://localhost');
    const channel = await connection.createChannel();
    
    const queue = 'net.ironoc.mq.test3';
    const message = 'Hello, RabbitMQ! from JS script';

    await channel.assertQueue(queue, { durable: false });
    channel.sendToQueue(queue, Buffer.from(message));

    console.log(`Sent: ${message}`);

    setTimeout(() => {
        connection.close();
        process.exit(0);
    }, 500);
}

sendMessage().catch(console.error);
