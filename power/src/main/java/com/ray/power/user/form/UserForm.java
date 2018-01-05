package com.ray.power.user.form;

import com.ray.base.base.ServletForm;

public class UserForm extends ServletForm{

	private String loginname ;
	private Integer roleid ;
	private Integer state ;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
