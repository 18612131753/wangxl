package com.ray.power.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ray.power.org.form.DepartmentForm;
import com.ray.power.org.model.OrgDO;
import com.ray.power.org.model.OrgTree;

public interface DepartmentDao {

	@Select("select * from power_org where orgid=#{orgid}")
	public OrgDO findById(@Param("orgid")String orgid);
	
	@Select("select id,short_name as name ,parent_id from ray_department where id=#{id}")
	public OrgTree findDepTreeById(@Param("id")String id);
	
	@Select("select id,short_name as name ,parent_id "+
			"from ray_department where state=1 and id like concat(#{depRole},'%')")
	public List<OrgTree> findByDepRole(@Param("depRole")String depRole);
	/**
	 * 查询 机构
	 * @param form
	 * @return
	 */
	public List<OrgDO> query(@Param("form")DepartmentForm form);
	
	/**
	 * 查询 机构-count
	 * @param form
	 * @return
	 */
	public Integer queryCount(@Param("form")DepartmentForm form);

	/**
	 * 添加 机构
	 * @param model
	 */
	public void save(@Param("org")OrgDO dep) throws Exception;

	/**
	 * 获取子机构中的ID最大值
	 * @param pId
	 */
	@Select("select max(id) from ray_department where parent_id=#{pId}")
	public String findMaxIdByParId( @Param("pId")String pId );
	/**
	 * 更新 机构
	 * @param model
	 */
	
	public void update(@Param("org")OrgDO org);
	/**
	 * 获得第一级父机构
	 * @return
	 */
	@Select("select id,short_name as name ,parent_id as pid from ray_department where state=1")
	public List<OrgTree> findAllParentDepartment();
	/**
	 * 获得下级机构
	 * @param parent_id
	 * @return
	 */
	@Select("select id,full_name as name from ray_department where parent_id = #{parent_id}")
	public List<OrgTree> findChildDepartment(@Param("parent_id")String parent_id);
	
	@Select("delete from ray_department where id like concat( #{pid} ,'%')")
	public void deleteByPid(@Param("pid")String pid) throws Exception;
}
