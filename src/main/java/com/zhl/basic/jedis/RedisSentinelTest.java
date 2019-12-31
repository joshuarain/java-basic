package com.zhl.basic.jedis;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lenovo
 * @date 2019/11/25 16:07
 */
public class RedisSentinelTest {
    public static void main(String[] args) {
        Set<String> hostPorts = new HashSet<>(Arrays.asList("192.168.56.102:26376","192.168.56.102:26377","192.168.56.102:26378"));
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster",hostPorts,genericObjectPoolConfig);
        Jedis jedis = jedisSentinelPool.getResource();

        jedis.set("version","1.0");

        jedis.close();
    }
}
