package com.ray.power.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ray.power.menu.model.MenuVO;

public interface UserDataRoleDao {
	
	public List<MenuVO> findMenuByRoleId(@Param("roleid")Integer roleid);
	
	public List<MenuVO> findChildMenuByRoleIdAndMenuId( @Param("roleid") Integer roleid ,  @Param("menuid") Integer menuid );
	
	@Select("select GROUP_CONCAT(department_id) from ray_user_data where user_id=#{user_id}")
	public String findUserDepartmentIds( @Param("user_id")Integer user_id );
	
}
