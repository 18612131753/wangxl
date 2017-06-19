package com.ray.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.ray.mongo.util.JsonUtil;

public class MDB {
	
	public MongoClient mc ;
	public MongoDatabase db;
	public MongoCollection<Document> users;
	
	@Before
    public void init() {
        try {
            mc = new MongoClient("192.168.192.133" , 27017);
        }catch (MongoException e) {
            e.printStackTrace();
        }
        //获取user DB；如果默认没有创建，mongodb会自动创建
        db = mc.getDatabase("test");
        //获取users DBCollection；如果默认没有创建，mongodb会自动创建
        users = db.getCollection("user");
    }
	
	@After
	public void destory() {
	    if (mc != null)
	        mc.close();
	    mc = null;
	    db = null;
	    users = null;
	    System.gc();
	}
	
	@Test
	//打印全部DB
	public void printAllDb(){
        //查询所有的Database
        for (String name : this.mc.getDatabaseNames()) {
            System.out.println("dbName: " + name);
        }
	}
	
	@Test
	//查询全部
	public void queryAll() {
		System.out.println("查询users的所有数据：" +users.count());
	    //db游标
	    FindIterable<Document> fi = users.find();
	    MongoCursor<Document> cur = fi.iterator();
	    while (cur.hasNext()) {
	    	System.out.println(cur.next());
	    }
	}
	
	@Test
	//添加USER
	public void insert() {
	    //先查询所有数据
	    Document user = new Document();
	    user.append("name", "hoojo");
	    user.append("age", 24);
	    //扩展字段，随意添加字段，不影响现有数据,getN()获取影响行数
	    user.append("sex", "男");
	    user.append("brithday", new Date());
	    user.append("school",1);
	    users.insertOne(user);
	     
	    //查询下数据，看看是否添加成功
	    queryAll();
	}
	
	@Test
	//插入对象
	public void insertObject(){
		
//		UserObject u = new UserObject();
//		u.setName("jim");
//		u.setBrithday( new Date());
//		u.setAge(18);
//		String jsonstr = JsonUtil.objToStr(u);
//		System.out.println( jsonstr );
//		Document doc = Document.parse(jsonstr);
//		users.insertOne(doc);
		
		UserObject u1 = new UserObject();
		u1.setName(null);
		u1.setBrithday( new Date());
		u1.setAge(18);
		UserObject u2 = new UserObject();
		u2.setName(null);
		u2.setBrithday( new Date());
		u2.setAge(18);
		List<UserObject> list = new ArrayList<UserObject>();
		list.add(u1);
		list.add(u2);
		UserClass uc = new UserClass();
		uc.setList( list );
		uc.setName("ceshi");
		String jsonstr = JsonUtil.objToStr(uc);
		Document doc = Document.parse(jsonstr);
		users.insertOne(doc);
	}
	
	@Test
	//添加many
	public void insertmany(){
		 //添加List集合
	    List<Document> list = new ArrayList<Document>();
	    Document user2 = new Document("name", "lucy");
	    user2.append("age", 24);
	    list.add(user2);
	    //添加List集合
	    users.insertMany(list);
	    //查询下数据，看看是否添加成功
	    queryAll();
	}
	
	@Test
	public void remove() {
		users.deleteOne(Filters.eq("name", "Jim"));
	    queryAll();
	}
}
