package com.ray.power.menu.service;

import java.util.List;

import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;
import com.ray.power.util.GridDataModel;

public interface MenuService {
	public List<MenuTree> findAllDdMenu();
	
	public GridDataModel<MenuGridModelVO> query(MenuForm form);
	
}