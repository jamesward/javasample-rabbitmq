package com.wabbit.silly;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: sclasen
 * Date: 6/5/11
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
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
