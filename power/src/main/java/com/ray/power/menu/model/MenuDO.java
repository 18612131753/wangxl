package com.ray.power.menu.model;

import com.ray.power.base.model.BaseModelDO;

public class MenuDO extends BaseModelDO {

	private static final long serialVersionUID = 1L;
	
	private Integer menuid;
	private String name;
	private String url;
	private String pmenuid;
	private Integer ordernum;
	private Integer state ; //1=正常  0=删除
	
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPmenuid() {
		return pmenuid;
	}
	public void setPmenuid(String pmenuid) {
		this.pmenuid = pmenuid;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
