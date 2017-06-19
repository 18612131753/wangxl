package com.ray.redis.a5_rediscluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyJedisCluster {

	public static void main(String[] args) {
		r_string();
//		r_list();
//		r_set();
//		r_sortset();
//		r_map();
	}
	
	//普通字符串
	public static void r_string(){
		RedisUtlis.JEDIS.set("a", "000");
		RedisUtlis.JEDIS.setrange( "a", 2, "123");  //从a的第二个位置添加字符串“123”
		System.out.println( "获取普通对象：" + RedisUtlis.JEDIS.get("a") );
		//RedisUtlis.JEDIS.del("a");
	}
	
	//操作LIST
	public static void r_list(){
		RedisUtlis.JEDIS.lpush("list", "1");
		RedisUtlis.JEDIS.rpush("list", "2");
		RedisUtlis.JEDIS.rpush("list", "3");
		System.out.println( "输出LIST：" + RedisUtlis.JEDIS.lrange("list",0,-1));
		RedisUtlis.JEDIS.lpop("list") ;
		RedisUtlis.JEDIS.rpop("list") ;
		System.out.println( "输出LIST：" + RedisUtlis.JEDIS.lrange("list",0,-1));
		RedisUtlis.JEDIS.del("list");
	}
	
	//操作SET
	public static void r_set(){
		RedisUtlis.JEDIS.sadd("set", "1");
		RedisUtlis.JEDIS.sadd("set", "2");
		RedisUtlis.JEDIS.sadd("set", "3");
		RedisUtlis.JEDIS.sadd("set", "3");
		System.out.println( "输出set：" + RedisUtlis.JEDIS.smembers("set"));
		System.out.println( "输出set的长度：" + RedisUtlis.JEDIS.scard("set"));
		RedisUtlis.JEDIS.del("set");
	}
	
	//操作sort_SET
	public static void r_sortset(){
		RedisUtlis.JEDIS.zadd("ss", 1, "a");
		RedisUtlis.JEDIS.zadd("ss", 2, "b");
		RedisUtlis.JEDIS.zadd("ss", 3, "c");
		System.out.println( "输出set的长度：" + RedisUtlis.JEDIS.zrange("ss", 0, -1));
		RedisUtlis.JEDIS.del("ss");
	}
		
	//操作MAP
	public static void r_map(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "jack");
		map.put("age", "11");
		map.put("password", "mima");
		RedisUtlis.JEDIS.hmset( "map" ,map );
		List<String> list = RedisUtlis.JEDIS.hmget("map","name","age","password");
		System.out.print( "输出map：");
		for(String s : list){
			System.out.print( s + "\t" );
		}
		System.out.println();
		RedisUtlis.JEDIS.del("map");
	}
}
