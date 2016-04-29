package com.dexter.cache.redis;

import redis.clients.jedis.Jedis;

public class RedisJava {
   public static void main(String[] args) {
      //连接本地的 Redis 服务
      Jedis jedis = new Jedis("172.16.10.71",6379);
//      jedis.auth("123456");  
      //查看服务是否运行
      System.out.println("Server is running: "+jedis.ping());
    //设置 redis 字符串数据
      jedis.set("w3ckey", "Redis tutorial");
     // 获取存储的数据并输出
     System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));
 }
}