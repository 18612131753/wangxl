package com.ray.redis.a5_rediscluster;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisUtlis {

	private static RedisUtlis instance = new RedisUtlis();
	
	public static JedisCluster JEDIS;
	//ShardedJedis
	private RedisUtlis(){
		//redis配置
		try{
			Properties redis_config = new Properties();
			File f = new File( System.getProperty("user.dir")+"/src/main/java/com/ray/redis/a5_rediscluster/redis.properties");
			redis_config.load( new FileInputStream(f) );
			GenericObjectPoolConfig pool = new GenericObjectPoolConfig();
			pool.setMaxTotal(Integer.valueOf(redis_config.getProperty("redis_pool_MaxTotal")));//最大连接数
			pool.setMaxIdle(Integer.valueOf(redis_config.getProperty("redis_pool_MaxIdle")));  //最大空闲数
			pool.setMinIdle(Integer.valueOf(redis_config.getProperty("redis_pool_MinIdle")));  //最小空闲数
			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object obj : redis_config.keySet()) {
				if(obj.toString().indexOf("redis_cluster")>-1){
					String[] cluster = redis_config.getProperty(obj.toString()).split(":");
					haps.add( new HostAndPort( cluster[0], Integer.valueOf(cluster[1])) );
				}
			}
			RedisUtlis.JEDIS = new JedisCluster( haps , pool );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static RedisUtlis getInstance(){
		return instance ;
	}


}
