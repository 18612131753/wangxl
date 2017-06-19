package com.ray.mongo.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	
	/**
	 * 字符串转对象
	 * */
	public static <T> T strToObj(String text , Class<T> clazz){
		T obj = JSON.parseObject(text, clazz);
		return obj ; 
	}
	
	/**
	 * 对象转字符串
	 * */
	public static String objToStr(Object obj ){
		return  JSON.toJSONString(obj);
	}

	/**
	 * 目前只能获得一级目录下元素值
	 */
	public static String getValue(String json, String key) {
		JSONObject dataJson;
		try {
			dataJson = new JSONObject(json);
			return dataJson.getString(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	/**
	 * post json格式的数据
	 * */
	public static String postJsonString(String sendurl, String data) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(sendurl);
		StringEntity myEntity = new StringEntity(
			data,
			ContentType.APPLICATION_JSON
		);// 构造请求数据
		post.setEntity(myEntity);      // 设置请求体
		String responseContent = null; // 响应内容
		CloseableHttpResponse response = null;
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null)
					response.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (client != null)
						client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}
}
