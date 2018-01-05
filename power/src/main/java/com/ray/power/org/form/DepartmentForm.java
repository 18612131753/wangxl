package com.ray.power.org.form;

import com.ray.base.base.ServletForm;

public class DepartmentForm extends ServletForm{
	
	private String fullname;
	private String shortname;
	private Integer state ;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
