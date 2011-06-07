package com.wabbit.silly;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: sclasen
 * Date: 6/5/11
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpringSender {


    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
        Matcher rabbitMqUrlMatcher = ctx.getBean("rabbitMqUrlMatcher", Matcher.class);
        System.out.println("user:"+rabbitMqUrlMatcher.group(1));
        System.out.println("password:"+rabbitMqUrlMatcher.group(2));
        System.out.println("host:"+rabbitMqUrlMatcher.group(3));
        System.out.println("port:"+rabbitMqUrlMatcher.group(4));
        System.out.println("vhost:"+rabbitMqUrlMatcher.group(5));
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        while(true){
            String msg = "Spring Sent at:" + System.currentTimeMillis();
            System.out.println(msg);
            byte[] body = msg.getBytes("UTF-8");
            rabbitTemplate.send(new Message(body, new MessageProperties()));
            Thread.sleep(1000);
        }
    }
}
