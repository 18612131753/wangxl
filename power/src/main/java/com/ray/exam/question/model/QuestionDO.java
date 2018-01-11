package com.ray.exam.question.model;

import com.ray.base.base.model.BaseModelDO;

public class QuestionDO extends BaseModelDO {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qid ;
	private Integer type ;
	private String  title; //题干
	private Integer level; //1=容易 2=中等 3=难度
	private String  image_url ;
	private String  answer;
	private String  opt_a;
	private String  opt_b;
	private String  opt_c;
	private String  opt_d ;
	private String  opt_e;
	private String  opt_f;
	private String  opt_g;
	private String  opt_h;
	private Integer owner;
	private Integer state ; //1=正常 0=删除
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOpt_a() {
		return opt_a;
	}
	public void setOpt_a(String opt_a) {
		this.opt_a = opt_a;
	}
	public String getOpt_b() {
		return opt_b;
	}
	public void setOpt_b(String opt_b) {
		this.opt_b = opt_b;
	}
	public String getOpt_c() {
		return opt_c;
	}
	public void setOpt_c(String opt_c) {
		this.opt_c = opt_c;
	}
	public String getOpt_d() {
		return opt_d;
	}
	public void setOpt_d(String opt_d) {
		this.opt_d = opt_d;
	}
	public String getOpt_e() {
		return opt_e;
	}
	public void setOpt_e(String opt_e) {
		this.opt_e = opt_e;
	}
	public String getOpt_f() {
		return opt_f;
	}
	public void setOpt_f(String opt_f) {
		this.opt_f = opt_f;
	}
	public Integer getOwner() {
		return owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}
	public String getOpt_g() {
		return opt_g;
	}
	public void setOpt_g(String opt_g) {
		this.opt_g = opt_g;
	}
	public String getOpt_h() {
		return opt_h;
	}
	public void setOpt_h(String opt_h) {
		this.opt_h = opt_h;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
