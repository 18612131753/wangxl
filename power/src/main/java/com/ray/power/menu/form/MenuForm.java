package com.ray.power.menu.form;

import com.ray.power.base.ServletForm;

public class MenuForm extends ServletForm{

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
