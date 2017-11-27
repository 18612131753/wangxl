package com.ray.power.user.model;

import java.util.Date;

public class User {
	private Integer id;//ID
	private Integer role_id;//角色ID
	private String login_name;//登陆名
	private String password;//登陆密码
	private Integer state;//状态
	private Integer creator_id;//创建人ID
	private Date create_date;//创建时间
	private Integer updator_id;//更新人ID
	private Date update_date;//更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String loginName) {
		login_name = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(Integer creatorId) {
		creator_id = creatorId;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	public Integer getUpdator_id() {
		return updator_id;
	}
	public void setUpdator_id(Integer updatorId) {
		updator_id = updatorId;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	
}
