package com.wabbit.silly;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import static java.lang.System.getenv;


public class Sender {

    public static void main(String[] args) throws IOException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(getenv("RABBIT_USER"));
        factory.setPassword(getenv("RABBIT_PASSWORD"));
        factory.setVirtualHost(getenv("RABBIT_VHOST"));
        factory.setHost(getenv("RABBIT_HOST"));
        factory.setPort(Integer.parseInt(getenv("RABBIT_PORT")));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "silly-wabbit-exchange";
        String queueName = "silly-wabbit-queue";
        String routingKey = "silly-wabbit-key";
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        while (true) {
            String msg = "Sent at:" + System.currentTimeMillis();
            byte[] body = msg.getBytes("UTF-8");
            channel.basicPublish(exchangeName, routingKey, null, body);
            System.out.println("Sent:->" + msg);
            Thread.sleep(1000);
        }

    }
}
