package com.ray.base.base;

import java.io.Serializable;

public class QueryForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer start = 0;
	private Integer limit = 0;
	private Object params = null;
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return (limit + start);
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}

}
