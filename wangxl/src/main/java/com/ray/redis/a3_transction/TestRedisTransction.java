package com.ray.redis.a3_transction;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * redis事务API
 * */
public class TestRedisTransction {

	private static final String SERVER_ADDRESS = "192.168.192.133"; // 服务器地址
	private static final Integer SERVER_PORT = 6379; // 端口
	private static JedisPool jp = null;
	
	public static void initpool() {
		JedisPoolConfig jpc = new JedisPoolConfig();
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		jpc.setMaxIdle(5);
		//pool的jedis的连接数
		jpc.setMaxTotal(200);
		
		// jp = new JedisPool(jpc, SERVER_ADDRESS, SERVER_PORT, 2000);
		
		// 拥有密码的配置
		jp = new JedisPool(jpc, SERVER_ADDRESS, SERVER_PORT, 2000 , "myredis");
	}
	
	public static void main(String[] args) {
		initpool();
		Jedis jedis = jp.getResource() ;
		jedis.select(1);
		
		Transaction multi = jedis.multi(); 
		multi.set("t1", "23432");  
		multi.lpush("list1","1","2");
		List<Object> exec = multi.exec();  
	    System.out.println("---"+exec); 
	       
//		String watch = jedis.watch("testabcd");  
//	    System.out.println(Thread.currentThread().getName()+"--"+watch); 

		jp.destroy();
	}
	
	
}
