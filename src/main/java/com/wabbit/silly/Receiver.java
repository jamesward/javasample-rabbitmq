package com.wabbit.silly;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;

import static java.lang.System.getenv;

public class Receiver {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(getenv("RABBITMQ_USER"));
        factory.setPassword(getenv("RABBITMQ_PASSWORD"));
        factory.setVirtualHost(getenv("RABBITMQ_VHOST"));
        factory.setHost(getenv("RABBITMQ_HOST"));
        factory.setPort(Integer.parseInt(getenv("RABBITMQ_PORT")));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "silly-wabbit-exchange";
        String queueName = "silly-wabbit-queue";
        String routingKey = "silly-wabbit-key";
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        while (true) {
            GetResponse response = channel.basicGet(queueName,true);
            if (response != null) {
                System.out.println("Recieved:->" + new String(response.getBody(), "UTF-8"));
            }
            Thread.sleep(500);
        }

    }
}
