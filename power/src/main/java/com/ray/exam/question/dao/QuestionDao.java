package com.ray.exam.question.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;

public interface QuestionDao {

	public List<QuestionDO> query(@Param("form") QuestionForm form);
	public int queryCount(@Param("form")QuestionForm form);
	
	@Select("select * from exam_question where qid=#{qid}")
	public QuestionDO findQuestionById(@Param("qid")Integer qid);
	
	@Insert(
		"INSERT INTO exam_question ( type,title,level,image_url,answer,opt_a,opt_b,opt_c,opt_d,opt_e,opt_f,opt_g,opt_h,owner,state,cid,cdate,uid,udate) "+
		"VALUES( #{q.type},#{q.title},#{q.level},#{q.image_url},#{q.answer},#{q.opt_a},#{q.opt_b},#{q.opt_c},#{q.opt_d},"+
		"#{q.opt_e},#{q.opt_f},#{q.opt_g},#{q.opt_h},#{q.owner},#{q.state},#{q.cid},now(),#{q.uid},now())")
	@Options(useGeneratedKeys = true, keyProperty = "q.qid")
	public void saveQuestion(@Param("q")QuestionDO question) ;
	
	@Update(
		"UPDATE exam_question SET type=#{q.type},title=#{q.title},level=#{q.level},image_url=#{q.image_url},answer=#{q.answer},"+
		"opt_a=#{q.opt_a},opt_b=#{q.opt_b},opt_c=#{q.opt_c},opt_d=#{q.opt_d},opt_e=#{q.opt_e},opt_f=#{q.opt_f},opt_g=#{q.opt_g},opt_h=#{q.opt_h},"+
		"owner=#{q.owner},uid=#{q.uid},udate=now()) "+
		"where qid=#{q.qid}")
	public void updateQuestion(@Param("q")QuestionDO question) ;
	
	@Update("UPDATE exam_question SET state=0,uid=#{q.uid},udate=now() where qid=#{q.qid}")
	public void deleteQuestion(@Param("userid")Integer userid, @Param("qid")Integer qid);
}