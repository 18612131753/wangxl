package com.ray.redis.a1_api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedis {

	private static final String SERVER_ADDRESS = "192.168.192.133"; // 服务器地址
	private static final Integer SERVER_PORT = 6379; // 端口
	private static final String PASSWORD = "myredis" ; //密码
	
	private Jedis jedis;

	/**
	 * 直接链接
	 */
	@Before
	public void init() {
		System.out.println("===========================init=================================");
		jedis = new Jedis(SERVER_ADDRESS, SERVER_PORT);
		// 配置密码
		jedis.auth( PASSWORD ) ;
		// 选择第一个数据库
		jedis.select(3);
	}
	/**
	 * 关闭链接
	 */
	@After
	public void dis() {
		System.out.println("===========================dis=================================");
		jedis.disconnect();
	}
	/**
	 * 操作普通键值对
	 */
	@Test
	public void testString() {
		System.out
				.println("========================testString=============================");
		jedis.set("name", "zhaoliu1");// 存入1个key=name value=zhaoliu的键值对
		String value = jedis.get("name"); // 获取key=name的值
		System.out.println(value);
	}

	/**
	 * 操作TTL，定时是删除
	 */
	@Test
	public void testTTL() {
		System.out
				.println("========================testTTL=============================");
		jedis.set("ttl", "zhaoliu");
		jedis.expire("ttl", 3);  //单位秒
		String value = jedis.get("name"); // 获取key=name的值
		System.out.println(value);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		value = jedis.get("ttl"); // 获取key=name的值
		System.out.println(value);
	}

	/**
	 * 操作计数器
	 */
	@Test
	public void testincr() {
		System.out
				.println("=======================testincr==============================");
		long value = jedis.incr("num");
		System.out.println(value);
		value = jedis.incr("num");
		System.out.println(value);
		value = jedis.incr("num");
		System.out.println(value);
		value = jedis.incrBy("num", 100);
		System.out.println(value);
		value = jedis.decr("num");
		System.out.println(value);
		value = jedis.decrBy("num", 100);
		System.out.println(value);
	}

	/**
	 * 操作List
	 */
	@Test
	public void testList() {
		System.out
				.println("=======================testList==============================");
		// 删除key
		jedis.del("students");

		// 将zhangsan加入到students的末尾
		jedis.lpush("students", "zhangsan");
		jedis.lpush("students", "x1");
		jedis.lpush("students", "x2");
		// 将zhaoliu 加入students数组的结尾,如果该元素是第一个元素，那么会自动创建students数组
		jedis.rpush("students", "zhaoliu");
		// 移除students的第一个元素
		jedis.lpop("students");
		// 移除students的最后一个元素
		jedis.rpop("students");
		// 移除制定的元素,第二个参数表示要移除的个数，因为list中是允许出现重复元素的
		jedis.lrem("students", 1, "zhangsan");
		// 获取students数组的所有元素
		List<String> students = jedis.lrange("students", 0, -1);
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
	}

	/**
	 * 操作Set
	 */
	@Test
	public void testSet() {
		// 删除key
		jedis.del("teachers");
		System.out
				.println("======================testSet===============================");
		// 添加元素
		jedis.sadd("teachers", "zhangsan");
		jedis.sadd("teachers", "lisi");
		jedis.sadd("teachers", "wangwu");
		// 判断Set是否包含制定元素
		Boolean b1 = jedis.sismember("teachers", "wangwu");
		Boolean b2 = jedis.sismember("teachers", "xxxxx");
		System.out.println(b1 + "   " + b2);
		// 获取Set内所有的元素
		Set<String> members = jedis.smembers("teachers");
		Iterator<String> it = members.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// jedis.sunion(keys...) 可以将多个Set合并成1个并返回合并后的Set
	}

	/**
	 * 操作带排序功能的Set
	 */
	@Test
	public void testSortSet() {
		// 删除key
		jedis.del("emps");
		System.out
				.println("========================testSortSet=============================");
		// 添加元素，会根据第二个参数排序
		jedis.zadd("emps", 5, "aaa");
		jedis.zadd("emps", 1, "bbbb");
		jedis.zadd("emps", 8, "ccc");
		jedis.zadd("emps", 2, "ddd");
		// 获取所有元素
		Set<String> emps = jedis.zrange("emps", 0, -1);
		Iterator<String> it = emps.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		jedis.zincrby("emps", 1000, "aaa");// aaa的score加 1000
		emps = jedis.zrangeByScore("emps", 4, 10000,0,-1);//返回4-1w分的所有值
		it = emps.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * 存入对象,使用Map作为对象
	 */
	@Test
	public void testHash() {
		// 删除key
		jedis.del("car:01");
		System.out
				.println("========================testHash=============================");
		Map<String, String> car = new HashMap<String, String>();
		car.put("COLOR", "red");
		car.put("SIZE", "2T");
		car.put("NUM", "100");
		car.put("NO", "京8888");
		// 存入对象，使用car:01当作key，是为了方便和其他car区分。比如car:02
		jedis.hmset("car:01", car);
		// 在指定key上加9
		jedis.hincrBy("car:01", "NUM", 9);
		// 获取整个对象
		Map<String, String> result = jedis.hgetAll("car:01");
		Iterator<Entry<String, String>> it = result.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			System.out.println("key:" + entry.getKey() + " value:"
					+ entry.getValue());
		}
		// 也可以获取制定的属性
		String no = jedis.hget("car:01", "NO");
		System.out.println("NO:" + no);
	}

	public static void main(String[] args) {
		TestRedis t = new TestRedis();
		t.init();
		t.testString();
		t.testTTL();
		t.testincr();
		t.testList();
		t.testSet();
		t.testSortSet();
		t.testHash();
		t.dis();
	}
}
