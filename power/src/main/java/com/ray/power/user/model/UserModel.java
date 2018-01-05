package com.ray.power.user.model;

import com.ray.base.base.model.BaseModelDO;

public class UserModel extends BaseModelDO{
	
	/**
	 * 账号
	 */
	private static final long serialVersionUID = 5934633292183694951L;
	
	private Integer id;//（账户编码）
	private Integer crm_employee_id;//（员工编码）
	private Integer crm_center_id;//（片区/中心）
	private Integer crm_depment_id;//（部门编码）
	private String crm_unm;//(账号名)
	private String crm_upwd; //(账号密码)
	private String crm_dupwd; //(默认账号密码)
	private String crm_state;//（是否启用）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCrm_employee_id() {
		return crm_employee_id;
	}
	public void setCrm_employee_id(Integer crmEmployeeId) {
		crm_employee_id = crmEmployeeId;
	}
	public Integer getCrm_center_id() {
		return crm_center_id;
	}
	public void setCrm_center_id(Integer crmCenterId) {
		crm_center_id = crmCenterId;
	}
	public Integer getCrm_depment_id() {
		return crm_depment_id;
	}
	public void setCrm_depment_id(Integer crmDepmentId) {
		crm_depment_id = crmDepmentId;
	}
	public String getCrm_unm() {
		return crm_unm;
	}
	public void setCrm_unm(String crmUnm) {
		crm_unm = crmUnm;
	}
	public String getCrm_upwd() {
		return crm_upwd;
	}
	public void setCrm_upwd(String crmUpwd) {
		crm_upwd = crmUpwd;
	}
	public String getCrm_dupwd() {
		return crm_dupwd;
	}
	public void setCrm_dupwd(String crmDupwd) {
		crm_dupwd = crmDupwd;
	}
	public String getCrm_state() {
		return crm_state;
	}
	public void setCrm_state(String crmState) {
		crm_state = crmState;
	}
	
}
