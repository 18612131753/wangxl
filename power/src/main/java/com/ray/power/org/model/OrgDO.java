package com.ray.power.org.model;

import com.ray.power.base.model.BaseModelDO;

public class OrgDO extends BaseModelDO{

	private static final long serialVersionUID = -3310223355314072151L;

	private Integer orgid ; //主键
	// 父节点
	private Integer porgid ;
	// 机构全称
	private String fullname ;
	//机构简称
	private String shortname ;
	// 状态
	private Integer state ; //1=正常 0=停用
	
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public Integer getPorgid() {
		return porgid;
	}
	public void setPorgid(Integer porgid) {
		this.porgid = porgid;
	}
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
