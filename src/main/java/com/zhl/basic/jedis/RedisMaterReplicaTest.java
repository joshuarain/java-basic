package com.zhl.basic.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolAbstract;

/**
 * @author Lenovo
 * @date 2019/11/25 16:00
 */
public class RedisMaterReplicaTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        JedisPoolAbstract jedisPoolAbstract = new JedisPoolAbstract();
        jedis.setDataSource(jedisPoolAbstract);
        jedis.set("username","john");
        jedis.close();
    }
}
