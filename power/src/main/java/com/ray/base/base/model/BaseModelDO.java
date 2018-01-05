package com.ray.base.base.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModelDO implements Serializable{
	
	private static final long serialVersionUID = -6443225690149509522L;
	
	private Integer cid;//（创建人）
	private Date cdate;//（创建时间）
	private Integer uid;//（修正人）
	private Date udate;//（修正时间） 
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
}
