package com.ray.power.org.model;

public class OrgTree {
	
	private Integer id;
	private String name;
	private Integer parent_id ;
	
	public OrgTree(){
		
	}
	public OrgTree(Integer id , String name){
		this.id=id ;
		this.name=name ;
	}
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
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

}
