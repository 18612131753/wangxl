package com.ray.redis.a1_api;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class TestPool {
	private static final String SERVER_ADDRESS = "192.168.192.133"; // 服务器地址
	private static final Integer SERVER_PORT = 6379; // 端口
	private static final String PASSWORD = "myredis" ; //密码
	
	private static JedisPool jp = null;

	public static void initpool() {
		JedisPoolConfig jpc = new JedisPoolConfig();
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		jpc.setMaxIdle(5);
		//pool的jedis的连接数
		jpc.setMaxTotal(200);
		jp = new JedisPool(jpc, SERVER_ADDRESS, SERVER_PORT, 2000,PASSWORD);
	}

	public static void dispool() {
		jp.destroy();
	}

	public static void main(String[] args) {
		initpool();
		Jedis jedis = jp.getResource();
		try {
			jedis.set("foo", "bar");
			String foobar = jedis.get("foo");
			System.out.println(foobar);
		} catch (JedisConnectionException e) {
			if (null != jedis) {
				// jp.returnBrokenResource(jedis);  //老语法，进入和returnResourceObject一样
				jp.returnResourceObject(jedis);
				jedis = null;
			}
		} finally {
			if (null != jedis)
				jp.returnResourceObject(jedis);
		}
		dispool();
	}
}
