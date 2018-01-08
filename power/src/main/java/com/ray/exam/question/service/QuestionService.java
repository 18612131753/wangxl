package com.ray.exam.question.service;

import com.ray.base.util.GridDataModel;
import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;

public interface QuestionService {
	
	public GridDataModel<QuestionDO> query(QuestionForm form);
	
}