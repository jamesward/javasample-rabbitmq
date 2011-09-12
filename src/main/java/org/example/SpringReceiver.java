package org.example;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringReceiver {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        while (true) {
            Message response = rabbitTemplate.receive();
            if (response != null) {
                System.out.println("Spring Recieved:->" + new String(response.getBody(), "UTF-8"));
            }
            Thread.sleep(500);
        }
    }
}
