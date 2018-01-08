package com.ray.exam.question.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.base.util.GridDataModel;
import com.ray.exam.question.dao.QuestionDao;
import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Resource
	private QuestionDao dao;

	public GridDataModel<QuestionDO> query(QuestionForm form) {
		GridDataModel<QuestionDO> gridmdl = new GridDataModel<QuestionDO>();
		List<QuestionDO> list = dao.query(form);
		gridmdl.setRows(list);
		gridmdl.setTotal(dao.queryCount(form));
		return gridmdl ;
	}
	
}
