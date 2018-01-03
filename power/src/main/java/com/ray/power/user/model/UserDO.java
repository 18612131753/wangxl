package com.ray.power.user.model;

import com.ray.power.base.model.BaseModelDO;

public class UserDO extends BaseModelDO {

	private static final long serialVersionUID = 1L;
	
	private Integer userid;
	private String  loginname;
	private String  password;
	private Integer roleid;
	private Integer isadmin;
	private Integer state; //1=正常 0=停用
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
