package com.ray.power.menu.form;

import com.ray.power.base.ServletForm;

public class MenuForm extends ServletForm{

	private String  name ;
	private Integer pmenuid ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPmenuid() {
		return pmenuid;
	}
	public void setPmenuid(Integer pmenuid) {
		this.pmenuid = pmenuid;
	}

}
