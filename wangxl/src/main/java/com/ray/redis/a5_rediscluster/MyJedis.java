package com.ray.redis.a5_rediscluster;

import redis.clients.jedis.Jedis;

public class MyJedis {

	//添加槽位
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("192.168.56.106",7007);
		
		//修改槽位
//		for( int i=0 ;i<1333;i++)
//			jedis.clusterAddSlots(i);
//		for( int i=5461 ;i<6795;i++)
//			jedis.clusterAddSlots(i);
//		for( int i=10923 ;i<12256;i++)
//			jedis.clusterAddSlots(i);

	}

}
