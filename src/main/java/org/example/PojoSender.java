package org.example;

import java.io.IOException;
import java.net.URISyntaxException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PojoSender {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        ConnectionFactory factory = RabbitFactoryUtil.getConnectionFactory();
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
