package com.ray.redis.a2_sortset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 文字塔应用
 * */
public class TestRedisSortSet {

	private static final String SERVER_ADDRESS = "192.168.192.146"; // 服务器地址
	private static final Integer SERVER_PORT = 6379; // 端口
	private static final String PASSWORD = "myredis" ; //密码
	private static JedisPool jp = null;
	
	public static void initpool() {
		JedisPoolConfig jpc = new JedisPoolConfig();
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		jpc.setMaxIdle(5);
		//pool的jedis的连接数
		jpc.setMaxTotal(200);
		jp = new JedisPool(jpc, SERVER_ADDRESS, SERVER_PORT, 2000);
		//jp = new JedisPool(jpc, SERVER_ADDRESS, SERVER_PORT ,2000, PASSWORD );
	}
	
	public static void main(String[] args) {
		initpool();
		//1。 模拟历史搜索记录
		Jedis jedis = jp.getResource() ;
		jedis.select(3);
		String[] wordset ={"奥特曼","奥特曼打怪兽","奥特曼打boss","奥特曼打boss","奥特曼","奥特曼兄弟"} ; 
		for(int i=0;i<wordset.length;i++){
			zincrby( jedis , wordset[i] );
		}
		
		//2. 获取关键
		Set<String> set = jedis.zrevrange("奥特曼打" , 0, 4);
		int num = 0;
		for( String s :set ){
			System.out.println( (num++)+ ":" +s  +"   score:"+jedis.zscore("奥", s) );
		}
		
		jp.destroy();
	}
	
	//根据搜索关键字，保存scoreset
	public static void zincrby( Jedis jedis  ,String sss  ){
		String word ;
		for( int i=0 ; i<1 ;i++){
			for( int j=0;j<sss.length();j++){
				word = sss.substring(0, j+1);
				jedis.zincrby( word , 1, sss );
			}
		}
	}	

}
