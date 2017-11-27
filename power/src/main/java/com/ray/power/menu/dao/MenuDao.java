package com.ray.power.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ray.power.menu.model.MenuTree;

public interface MenuDao {

	@Select("select * from ray_menu order by parent_id ,order_num asc")
	public List<MenuTree> findAllDdMenu();
	
}