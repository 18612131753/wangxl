package com.ray.exam.question.form;

import com.ray.base.base.ServletForm;

public class QuestionForm extends ServletForm{

	private Integer type ;
	private Integer level ;
	private String  title ;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
