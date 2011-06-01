package com.wabbit.silly;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;


public class Sender {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        TreeMap<String,String> env  = new TreeMap<String, String>(System.getenv());
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry.getKey()+"==>"+entry.getValue());
        }
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");


        AmqpTemplate template = ctx.getBean(AmqpTemplate.class);
        while(true){
            String msg = "Sent at:" + System.currentTimeMillis();
            byte[] body = msg.getBytes("UTF-8");
            template.send(new Message(body, new MessageProperties()));
            System.out.println("Sent:->"+msg);
            Thread.sleep(1000);
        }

    }
}
