package com.ray.redis.a4_pubsub;

import redis.clients.jedis.JedisPubSub;

public class MyListener extends JedisPubSub {

	// 取得订阅的消息后的处理
	public void onMessage(String channel, String message) {
		System.out.println("onMessage读取信息："+channel + "=" + message);
	}

	// 取得按表达式的方式订阅的消息后的处理
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("pOnPMessage读取信息："+pattern + "=" + channel + "=" + message);
	}
	
	/** 初始化订阅时候的处理
	 *  channel：监听的channel
	 *  subscribedChannels：符合条件的channel数量
	 */
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("onSubscribe初始化订阅:"+channel + "=" + subscribedChannels);
	}
	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out.println("pOnSubscribe初始化订阅:"+pattern + "=" + subscribedChannels);
	}

	// 取消订阅时候的处理
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("onUnsubscribe取消化订阅:"+channel + "=" + subscribedChannels + "#########");
	}
	// 取消按表达式的方式订阅时候的处理
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		System.out.println("pOnPUnsubscribe取消化订阅:"+pattern + "=" + subscribedChannels + "@@@@@@@@@");
	}
}
