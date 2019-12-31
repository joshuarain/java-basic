package com.zhl.basic.rabbit.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Lenovo
 * @title: EmitLogDirect
 * @projectName basic
 * @description: TODO
 * @date 2019/11/27 15:36
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.56.102");
        factory.setPort(5672);
        factory.setUsername("joshuarain");
        factory.setPassword("zhljx@916");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            //getSeverity(argv);
            String severity = "info";
            //getMessage(argv);
            String message = "zhl";

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }
}
