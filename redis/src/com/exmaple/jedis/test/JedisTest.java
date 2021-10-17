package com.exmaple.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis的测试类
 */
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test1(){
        //1.获取连接
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.操作
        jedis.set("username","zhangsan");
        //3.关闭连接
        jedis.close();
    }

    /**
     * string数据结构操作
     */
    @Test
    public void test2(){
        //1.获取连接
        Jedis jedis = new Jedis();//空参构造，默认"127.0.0.1", 6379
        //2.操作
        jedis.set("username","zhangsan");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法储存可以指定过期时间的 key value
        jedis.setex("activatecode",20,"fuck");
        //3.关闭连接
        jedis.close();
    }

    /**
     * hash数据结构操作
     */
    @Test
    public void test3(){
        Jedis jedis = new Jedis();//空参构造，默认"127.0.0.1", 6379
        jedis.hset("user","name","zhangsan");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");
        String username = jedis.hget("user","name");
        System.out.println(username);
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }
        jedis.close();
    }

    /**
     * list数据结构操作
     */
    @Test
    public void test4(){
        Jedis jedis = new Jedis();//空参构造，默认"127.0.0.1", 6379
        jedis.lpush("mylist","a","b","c");//从左边存
        jedis.rpush("mylist","a","b","c");//从右边存
        //cba abc
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //list弹出
        String element1 = jedis.lpop("mylist");
        assert "c".equals(element1);
        System.out.println(element1);
        String element2 = jedis.rpop("mylist");
        assert "c".equals(element2);
        System.out.println(element2);

        List<String> mylist1 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist1);

        jedis.close();
    }

    /**
     * set数据结构操作
     */
    @Test
    public void test5(){
        Jedis jedis = new Jedis();//空参构造，默认"127.0.0.1", 6379
        jedis.sadd("myset","java","php", "c++");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        jedis.close();
    }

    /**
     * sortedset数据结构操作
     */
    @Test
    public void test6(){
        Jedis jedis = new Jedis();//空参构造，默认"127.0.0.1", 6379
        jedis.zadd("mysortedset",10,"java");
        jedis.zadd("mysortedset",30,"c++");
        jedis.zadd("mysortedset",1,"php");

        Set<String> mysortedset = jedis.zrange("mysortedset",0,-1);
        System.out.println(mysortedset);
        jedis.close();
    }

    /**
     * Jedis连接池使用
     */
    @Test
    public void test7(){
        //0.创建一个配置对象
        JedisPoolConfig conig = new JedisPoolConfig();
        conig.setMaxTotal(50);
        conig.setMaxIdle(10);

        //1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(conig,"127.0.0.1",6379);

        //2.获取连接
        Jedis jedis = jedisPool.getResource();

        //3.使用
        jedis.set("haha","f**k");

        //4.关闭 归还到连接池中
        jedis.close();
    }
}
