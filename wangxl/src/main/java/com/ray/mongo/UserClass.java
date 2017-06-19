package com.ray.mongo;

import java.io.Serializable;
import java.util.List;

public class UserClass implements Serializable {

	private List<UserObject> list ;


	private String name ;
	
	public List<UserObject> getList() {
		return list;
	}

	public void setList(List<UserObject> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
