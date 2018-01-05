package com.ray.power.menu.model;

import com.ray.base.base.model.BaseModelDO;

public class MenuGridModelVO extends BaseModelDO {

	private static final long serialVersionUID = 1L;
	
	private Integer menuid;
	private String name;
	private String url;
	private String pmenuid;
	private String pmenuname;
	private Integer ordernum;
	
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
	public String getPmenuname() {
		return pmenuname;
	}
	public void setPmenuname(String pmenuname) {
		this.pmenuname = pmenuname;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

}
