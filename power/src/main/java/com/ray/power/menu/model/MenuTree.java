package com.ray.power.menu.model;

public class MenuTree {

	//菜单ID
	private Integer menuid ;
	//菜单描述名
	private String name ;
	//菜单请求URL
	private String url ;
	//属于父菜单ID
	private Integer pmenuid ;
	
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
	public Integer getPmenuid() {
		return pmenuid;
	}
	public void setPmenuid(Integer pmenuid) {
		this.pmenuid = pmenuid;
	}

}
