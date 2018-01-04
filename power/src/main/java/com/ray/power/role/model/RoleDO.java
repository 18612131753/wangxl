package com.ray.power.role.model;

import java.util.Date;

public class RoleDO {

	private Integer roleid ;
	private String  name ;
	private Integer role_type; //角色类型
	private Integer cid;
	private Date    cdate ;
	private Integer uid;
	private Date    udate ;
	
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
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
	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}

}
