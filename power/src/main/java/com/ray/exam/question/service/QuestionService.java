package com.ray.exam.question.service;

import com.ray.base.util.GridDataModel;
import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;

public interface QuestionService {
	
	public GridDataModel<QuestionDO> query(QuestionForm form);
	
	public QuestionDO findQuestionById( Integer qid );
	
	public void saveQuestion( QuestionDO question );
	
	public void updateQuestion( QuestionDO question );
	
	/** 通过问题ID,查找是否拥有相关的试卷 */
	public int findPaperByQuestionId( Integer qid );
	
	public void deleteQuestion(Integer userid , Integer qid );
}