package com.ray.power.util;

import java.util.List;

public class PageControl<T> {
	
	private List<T> pageList;   //数据信息
	
	private Integer totle;
	private Integer end;
	
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
	public List<T> getPageList(int start,int limit) {
		totle = pageList.size();
		end = start + limit;
		if(totle > start && end <= totle) {
			return pageList.subList(start, limit);
		}else if(totle > start && end > totle) {
			return pageList.subList(start, totle);
		}
		return pageList;
	}
	
	public Integer getTotle() {
		return totle;
	}
}