package com.zhl.basic.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lenovo
 * @date 2019/11/25 16:00
 */
public class RedisTest {
    Jedis jedis = new Jedis("192.168.56.102", 6376);

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.102", 6376);
        jedis.set("username", "john");
        jedis.auth("123456");
        jedis.close();
    }

    public void keyTest() throws UnsupportedEncodingException {
        // 清空数据
        System.out.println(jedis.flushDB());
        System.out.println(jedis.echo("hello"));

        // 判断key否存在
        System.out.println(jedis.exists("foo"));

        jedis.set("key", "values");
        jedis.set("key2", "values");
        // 判断是否存在
        System.out.println(jedis.exists("key"));

        // 如果数据库没有任何key，返回nil，否则返回数据库中一个随机的key。
        String randomKey = jedis.randomKey();
        System.out.println("randomKey: " + randomKey);

        // 设置60秒后该key过期
        jedis.expire("key", 60);

        // key有效毫秒数
        System.out.println(jedis.pttl("key"));

        // 移除key的过期时间
        jedis.persist("key");

        // 获取key的类型, "string", "list", "set". "none" none表示key不存在
        System.out.println("type: " + jedis.type("key"));

        // 导出key的值
        byte[] bytes = jedis.dump("key");
        System.out.println(new String(bytes));

        // 将key重命名
        jedis.renamenx("key", "keytest");
        // 判断是否存在
        System.out.println("key是否存在: " + jedis.exists("key"));
        // 判断是否存在
        System.out.println("keytest是否存在: " + jedis.exists("keytest"));

        // 查询匹配的key
        // KEYS       * 匹配数据库中所有 key 。
        // KEYS       h?llo 匹配 hello ， hallo 和 hxllo 等。
        // KEYS       h*llo 匹配 hllo 和 heeeeello 等。
        // KEYS       h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
        // 特殊符号用 \ 隔开。
        Set<String> set = jedis.keys("k*");
        System.out.println(set);

        // 删除key
        jedis.del("key");
        System.out.println(jedis.exists("key"));
    }


    public void listTest() {
        String key = "mylist";
        jedis.del(key);

        // 队列添加元素
        jedis.rpush(key, "aaaa");
        jedis.rpush(key, "aaaa");
        jedis.rpush(key, "bbbb");
        jedis.rpush(key, "cccc");
        jedis.rpush(key, "cccc");

        // 队列长度
        System.out.println("lenth: " + jedis.llen(key));

        // 打印队列，从索引0开始，到倒数第1个（全部元素）
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // 索引为1的元素
        System.out.println("index of 1: " + jedis.lindex(key, 1));

        // 设置队列里面一个元素的值，当index超出范围时会返回一个error。
        jedis.lset(key, 1, "aa22");
        System.out.println("index of 1: " + jedis.lindex(key, 1));

        // 从队列的右边入队一个元素
        jedis.rpush(key, "-2", "-1");// 先-2，后-1入队列
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // 从队列的左边入队一个或多个元素
        jedis.lpush(key, "second element", "first element");// 先second
        // element，后first
        // elementF入队列
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // 从队列的右边出队一个元素
        System.out.println(jedis.rpop(key));
        // 从队列的左边出队一个元素
        System.out.println(jedis.lpop(key));
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // count > 0: 从头往尾移除值为 value 的元素，count为移除的个数。
        // count < 0: 从尾往头移除值为 value 的元素，count为移除的个数。
        // count = 0: 移除所有值为 value 的元素。
        jedis.lrem(key, 1, "cccc");
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // 即最右边的那个元素也会被包含在内。 如果start比list的尾部下标大的时候，会返回一个空列表。
        // 如果stop比list的实际尾部大的时候，Redis会当它是最后一个元素的下标。
        System.out.println(jedis.lrange(key, 0, 2));
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));

        // 删除区间以外的元素
        System.out.println(jedis.ltrim(key, 0, 2));
        System.out.println("all elements: " + jedis.lrange(key, 0, -1));
    }


    public void testSet() {
        // 清空数据
        System.out.println(jedis.flushDB());
        String key = "myset";
        String key2 = "myset2";

        // 集合添加元素
        jedis.sadd(key, "aaa", "bbb", "ccc");
        jedis.sadd(key2, "bbb", "ccc", "ddd");

        // 获取集合里面的元素数量
        System.out.println(jedis.scard(key));

        // 获得两个集合的交集，并存储在一个关键的结果集
        jedis.sinterstore("destination", key, key2);
        System.out.println(jedis.smembers("destination"));

        // 获得两个集合的并集，并存储在一个关键的结果集
        jedis.sunionstore("destination", key, key2);
        System.out.println(jedis.smembers("destination"));

        // key集合中，key2集合没有的元素，并存储在一个关键的结果集
        jedis.sdiffstore("destination", key, key2);
        System.out.println(jedis.smembers("destination"));

        // 确定某个元素是一个集合的成员
        System.out.println(jedis.sismember(key, "aaa"));

        // 从key集合里面随机获取一个元素
        System.out.println(jedis.srandmember(key));

        // aaa从key移动到key2集合
        jedis.smove(key, key2, "aaa");
        System.out.println(jedis.smembers(key));
        System.out.println(jedis.smembers(key2));

        // 删除并获取一个集合里面的元素
        System.out.println(jedis.spop(key));

        // 从集合里删除一个或多个元素
        jedis.srem(key2, "ccc", "ddd");
        System.out.println(jedis.smembers(key2));
    }


    public void testSortSet() {
        // 清空数据
        System.out.println(jedis.flushDB());
        String key = "mysortset";

        Map<String, Double> scoreMembers = new HashMap<String, Double>();
        scoreMembers.put("aaa", 1001.0);
        scoreMembers.put("bbb", 1002.0);
        scoreMembers.put("ccc", 1003.0);

        // 添加数据
        jedis.zadd(key, 1004.0, "ddd");
        jedis.zadd(key, scoreMembers);

        // 获取一个排序的集合中的成员数量
        System.out.println(jedis.zcard(key));

        // 返回的成员在指定范围内的有序集合，以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
        // 负数下标，以-1表示最后一个成员，-2表示倒数第二个成员
        Set<String> coll = jedis.zrange(key, 0, -1);
        System.out.println(coll);

        // 返回的成员在指定范围内的逆序集合
        coll = jedis.zrevrange(key, 0, -1);
        System.out.println(coll);

        // 元素下标
        System.out.println(jedis.zscore(key, "bbb"));

        // 删除元素
        System.out.println(jedis.zrem(key, "aaa"));
        System.out.println(jedis.zrange(key, 0, -1));

        // 给定值范围内的成员数
        System.out.println(jedis.zcount(key, 1002.0, 1003.0));
    }


    public void testHash() {
        // 清空数据
        System.out.println(jedis.flushDB());
        String key = "myhash";
        Map<String, String> hash = new HashMap<String, String>();
        hash.put("aaa", "11");
        hash.put("bbb", "22");
        hash.put("ccc", "33");

        // 添加数据
        jedis.hmset(key, hash);
        jedis.hset(key, "ddd", "44");

        // 获取hash的所有元素(key值)
        System.out.println(jedis.hkeys(key));

        // 获取hash中所有的key对应的value值
        System.out.println(jedis.hvals(key));

        // 获取hash里所有元素的数量
        System.out.println(jedis.hlen(key));

        // 获取hash中全部的域和值,以Map<String, String> 的形式返回
        Map<String, String> elements = jedis.hgetAll(key);
        System.out.println(elements);

        // 判断给定key值是否存在于哈希集中
        System.out.println(jedis.hexists(key, "bbb"));

        // 获取hash里面指定字段对应的值
        System.out.println(jedis.hmget(key, "aaa", "bbb"));

        // 获取指定的值
        System.out.println(jedis.hget(key, "aaa"));

        // 删除指定的值
        System.out.println(jedis.hdel(key, "aaa"));
        System.out.println(jedis.hgetAll(key));

        // 为key中的域 field 的值加上增量 increment
        System.out.println(jedis.hincrBy(key, "bbb", 100));
        System.out.println(jedis.hgetAll(key));
    }


    public void testTransaction() {
        Transaction t = jedis.multi();
        t.set("hello", "world");
        Response<String> response = t.get("hello");

        t.zadd("foo", 1, "barowitch");
        t.zadd("foo", 0, "barinsky");
        t.zadd("foo", 0, "barikoviev");
        //  返回全部相应并以有序集合的方式返回
        Response<Set<String>> sose = t.zrange("foo", 0, -1);
        System.out.println(response);
        System.out.println(sose);
        // 此行注意，不能缺少
        t.exec();

        // Response.get() 可以从响应中获取数据
        String foolbar = response.get();

        // sose.get() 会立即调用set方法
        int soseSize = sose.get().size();
        System.out.println(foolbar);
        System.out.println(sose.get());
    }

    public void testTransactionPipeling() {
        //开一个管道
        Pipeline p = jedis.pipelined();

        p.set("fool", "bar");
        p.zadd("foo", 1, "barowitch");
        p.zadd("foo", 0, "barinsky");
        p.zadd("foo", 0, "barikoviev");
        Response<String> pipeString = p.get("fool");
        Response<Set<String>> sose = p.zrange("foo", 0, -1);
        System.out.println(pipeString);
        System.out.println(sose);
        //提交
        p.sync();

        System.out.println("==========");
        System.out.println(p.get("fool"));
        System.out.println(p.zrange("foo", 0, -1));

        int soseSize = sose.get().size();
        Set<String> setBack = sose.get();

        System.out.println(soseSize);
        System.out.println(setBack);
    }
}
