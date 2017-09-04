package com.ray.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 *  
 * */
public class Day04Test {

	public static void main(String[] args ){
		MongoClient mc = new MongoClient("192.168.192.133",27017);
		MongoDatabase md = mc.getDatabase("test");
		MongoCollection<Document> mcc = md.getCollection("user");
		
		Document student = new Document();
		student.append("name", "Lucy");
		//mcc.insertOne(student);
		
		UserObject uo = new UserObject();
		uo.setAge(12);
		uo.setName( "namedate" );
		uo.setBrithday( new Date());
		
		UserObject uo1 = new UserObject();
		uo1.setAge(12);
		uo1.setName( "namedate1" );
		uo1.setBrithday( new Date());
		
		UserObject uo2 = new UserObject();
		uo2.setAge(12);
		uo2.setName( "namedate2" );
		uo2.setBrithday( new Date());
		//mcc.insertOne( Document.parse(  JsonUtil.objToStr(uo)) );
		
		List<UserObject> list = new ArrayList<UserObject>();
		list.add( uo );
		list.add( uo1 );
		list.add( uo2 );
		
		UserClass uc = new UserClass();
		uc.setName("班级");
		uc.setList( list );
		
		//mcc.insertOne( Document.parse(  JsonUtil.objToStr(uc))  );

		FindIterable<Document> fi = mcc.find( Filters.eq("name", "Lucy") ) ;
		
		//FindIterable<Document> fi = mcc.find(student);
		//FindIterable<Document> iter = mcc.find(Filters.eq("name", "Lucy"));
		
		MongoCursor<Document> cur = fi.iterator();
	    while (cur.hasNext()) {
	    	System.out.println(cur.next());
	    }
		mc.close();
	}
}
