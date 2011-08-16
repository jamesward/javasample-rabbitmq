package com.heroku.javasamplerabbitmq;

import java.net.URI;
import java.net.URISyntaxException;

import com.rabbitmq.client.ConnectionFactory;

public class ConnectionFactoryUtil {

    public static ConnectionFactory getConnectionFactory(String url) throws URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();

        URI dbUri = new URI(url);
        factory.setUsername(dbUri.getUserInfo().split(":")[0]);
        factory.setPassword(dbUri.getUserInfo().split(":")[1]);
        factory.setHost(dbUri.getHost());
        factory.setPort(dbUri.getPort());
        factory.setVirtualHost(dbUri.getPath().substring(1));

        return factory;
    }

}
