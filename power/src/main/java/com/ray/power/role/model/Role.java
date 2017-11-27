package com.ray.power.role.model;

import java.util.Date;

public class Role {

	private Integer id ;
	private String name ;
	private Integer role_type; //0=个人权限 1=支部 2=总支 3=全部总支 4=管理员
	private Integer create_id;
	private Date create_date ;
	private Integer update_id;
	private Date update_date ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRole_type() {
		return role_type;
	}
	public void setRole_type(Integer roleType) {
		role_type = roleType;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer createId) {
		create_id = createId;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	public Integer getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(Integer updateId) {
		update_id = updateId;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}

}
