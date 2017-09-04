/**
 * 表单数据格式
 */
package ali.rule.util;

import java.io.Serializable;
import java.util.List;

public class GridDataModel<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int page =1;   //当前页
	private int total=0;   //一共多少页
	private int count ;    //一共多少条数据
	
	private List<T> rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	/**
	 * @param count  : 总数据数
	 * @param rowNum : 每页多少条数据
	 * */
	public void setTotal(int count , int rowNum) {
		if( count%rowNum == 0 ){
			this.total =count/rowNum ;
		} else{
			this.total =count/rowNum+1 ;
		}
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
