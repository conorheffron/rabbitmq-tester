const amqp = require('amqplib');

async function receiveMessage() {
    const connection = await amqp.connect('amqp://localhost');
    const channel = await connection.createChannel();

    const queue = 'net.ironoc.mq.test3';
    await channel.assertQueue(queue, { durable: false });

    console.log(`Waiting for messages in ${queue}. To exit press CTRL+C`);
    
    channel.consume(queue, (msg) => {
        console.log(`Received: ${msg.content.toString()}`);
    }, { noAck: true });
}

receiveMessage().catch(console.error);
