package com.ray.mongo;
//新版本
//package test;
//
//import org.springframework.data.authentication.UserCredentials;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import com.lenovo.lls.llslog.model.UrlLog;
//import com.lenovo.lls.util.MongodbUtil;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.WriteConcern;
//
//public class MongDBTest {
//
//	public static void main(String[] args) {
//		MongoClient mc = new MongoClient("10.103.241.3", 27017) ;
//		WriteConcern wc = new WriteConcern();
//		wc.
//		mc.setWriteConcern(writeConcern);
//		UserCredentials uc = new UserCredentials("lenove","lenove"); //密码
//		MongoTemplate mongoTemplate = new MongoTemplate( mc , "lenovo" ,uc);
//		
//		UrlLog urllog = new UrlLog();
//		urllog.setUdate("2017-00-00 00:00:00");
//		urllog.setUrl("testurl");
//		urllog.setUserid(1);
//		DBObject object = MongodbUtil.objToMongoJson( urllog );
//        mongoTemplate.insert(object, "urllog");
//        
////	    Query query = new Query();
////	    // ISSN为子文档的名字 issn_p为字段
////	    // 依此类推 可叠加
////	    query.addCriteria(Criteria.where("ISSN.issn_p").is("10"));
////	    Journal j = mt.findOne(query, Journal.class);
////	    System.out.println(j.toString());
//	}
//
//}
