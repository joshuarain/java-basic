package com.zhl.basic.rabbit.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lenovo
 * @title: HelloWorld
 * @projectName basic
 * @description: TODO
 * @date 2019/11/26 14:04
 */
public class HelloWorld {
    public static final String QUEUE_NAME = "product_queue";
    public static final String HOST = "192.168.56.102";
    public static final String USERNAME = "joshuarain";
    public static final String PASSWORD = "zhljx@916";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);

        try (Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel()){

            //声明队列，ui上会出现该队列，但无数据
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            channel.basicPublish("",QUEUE_NAME,null,"Rabbit MQ".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
