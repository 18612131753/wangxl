/**
 * 表单数据格式
 */
package com.ray.base.util;

import java.util.List;

/** 
 * 2013-1-7
 * @author wxl 
 */
public class GridDataModel<T> {
	
	private int total = 0;     //数据总数
	private List<T> rows ;    //结果集

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
