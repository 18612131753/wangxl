package com.ray.power.org.service;

import com.ray.base.util.GridDataModel;
import com.ray.power.org.form.DepartmentForm;
import com.ray.power.org.model.OrgDO;

public interface OrgService {
	/**
	 * 通过ID查找 机构
	 * @param id
	 * @return
	 */
	public OrgDO findById(String id);
	
	/**
	 * 查找 机构
	 * @param queryForm
	 * @return
	 */
	public GridDataModel<OrgDO> query(DepartmentForm queryForm);
	
	/**
	 * 添加 机构
	 * @param RoomModel
	 * @return
	 */
	public Integer save(OrgDO model) throws Exception;
	
	public int deleteByPid(String pid );
	/**
	 * 编辑 机构
	 * @param RoomModel
	 * @return
	 */
	public void update(OrgDO model) throws Exception ;

}
