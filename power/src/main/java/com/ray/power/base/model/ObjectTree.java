/**
 * 生成树的对象
 */
package com.ray.power.base.model;

import java.io.Serializable;

/** 
 * 2013-1-8
 * @author wxl 
 */

public class ObjectTree implements Serializable {

	private static final long serialVersionUID = -5667847570264993501L;

	private Integer id ;
	private String text ;
	private Integer pid ;
	private Boolean expanded = false ;

	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
