package com.ray.power.login.model;

import java.io.Serializable;
import java.util.List;

import com.ray.power.menu.model.MenuVO;
import com.ray.power.org.model.OrgTree;

public class UserSession implements Serializable {

	private static final long serialVersionUID = -2026206663276578078L;

	private Integer userid;// 主键
	private Integer roleid;// 角色ID
	private String loginname;// 账号名称
	private String password;// 账号密码
	private Integer isadmin;
	private Integer state; // 账号状态

	private List<MenuVO> menus;
	private List<OrgTree> orgs;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
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
	public List<MenuVO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}
	public List<OrgTree> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<OrgTree> orgs) {
		this.orgs = orgs;
	}

}
