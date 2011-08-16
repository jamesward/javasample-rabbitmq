package com.heroku.javasamplerabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.System.getenv;

public class PojoReceiver {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        ConnectionFactory factory = ConnectionFactoryUtil.getConnectionFactory(getenv("RABBITMQ_URL"));
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
