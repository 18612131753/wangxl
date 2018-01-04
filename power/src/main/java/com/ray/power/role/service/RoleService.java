package com.ray.power.role.service;

import java.util.List;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.role.model.RoleDO;

public interface RoleService {
	
	public RoleDO findById( Integer id );
	
	public void save( RoleDO role );
	public void update( RoleDO role );
	public void deleteById(Integer id );
	public List<RoleDO> findAllRole();
	
	/**
	 * 根据权限ID查询用户数
	 * */
	public int findUserNumByRoleId(Integer rid);
	
	/**
	 * 根据角色ID获取菜单
	 * */
	public List<Integer> findRoleMenuById(Integer id);
	
	/**
	 * 更新角色和菜单
	 * @param userid 创建人id
	 * @param rid  角色ID
	 * @param mids 菜单ID
	 * */
	public void updateRoleMenu(Integer userid ,Integer rid ,String mids );
	
	public List<ObejctSelector>  findAllRoleSelector();
}
