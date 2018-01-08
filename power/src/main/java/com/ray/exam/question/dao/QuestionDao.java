package com.ray.exam.question.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;

public interface QuestionDao {

	public List<QuestionDO> query(@Param("form") QuestionForm form);
	public int queryCount(@Param("form")QuestionForm form);
	
}