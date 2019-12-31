package com.zhl.basic.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author Lenovo
 * @date 2019/11/25 15:57
 */
public class RedisClusterTest {
    public static void main(String[] args) {
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("192.168.56.102",7000));
        jedisCluster.set("username","john");
        jedisCluster.close();
    }
}
