package com.ray.redis.a4_pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 发布者，生产者
 * */
public class Publisher {
	
	public static JedisPool pool;
	static {
		JedisPoolConfig jedispool_config = new JedisPoolConfig();
		pool = new JedisPool(jedispool_config, "192.168.192.146", 6379,2000);
	}

	public static void main(String[] args) throws InterruptedException {
		// 发布一个频道
		Jedis jedis = pool.getResource();
		int i=1;
		while( true ){
			i++;
			System.out.println( "news.mess message"+ i);
			jedis.publish( "news.mess" , "message"+i);
			Thread.sleep(2000);
		}
	}
}
