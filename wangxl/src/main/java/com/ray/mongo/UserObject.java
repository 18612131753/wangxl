package com.ray.mongo;

import java.io.Serializable;
import java.util.Date;

import com.ray.mongo.util.RayDateUtils;

public class UserObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String _id ;
	private String name ;
	private int age ;
	private Date brithday ;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBrithday() {
		if( brithday != null ){
			return "ISODate(\""+RayDateUtils.dateToStr(this.brithday)+"\")";
		}
		return "";
	}
	
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	
}
