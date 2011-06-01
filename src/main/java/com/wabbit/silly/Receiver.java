package com.wabbit.silly;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;

public class Receiver {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
        AmqpTemplate template = ctx.getBean(AmqpTemplate.class);
        while (true) {
            Message msg = template.receive();
            if (msg != null) {
                System.out.println("Recieved:->" + new String(msg.getBody(), "UTF-8"));
            }
            Thread.sleep(500);
        }

    }
}
