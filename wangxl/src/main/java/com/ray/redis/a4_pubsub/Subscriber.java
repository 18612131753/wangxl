package com.ray.redis.a4_pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 订阅者，消费者
 * */
public class Subscriber {
	
	public static JedisPool pool;
	static {
		JedisPoolConfig jedispool_config = new JedisPoolConfig();
		pool = new JedisPool(jedispool_config, "192.168.192.146", 6379,2000 );
	}

	public static void main(String[] args) throws InterruptedException {
		// 订阅一个频道
		Jedis jedis = pool.getResource();
		MyListener listener = new MyListener();
		jedis.subscribe( listener , "news.mess");
		//jedis.psubscribe( listener , "news.*");
	}

}
