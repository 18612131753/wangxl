package com.ray.power.menu.service;

import java.util.List;

import com.ray.base.base.model.ObejctSelector;
import com.ray.base.util.GridDataModel;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuDO;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;

public interface MenuService {
	public List<MenuTree> findAllDdMenu();

	public GridDataModel<MenuGridModelVO> query(MenuForm form);

	/** 获取一级菜单 */
	public List<ObejctSelector> findMenu1();
	
	public MenuDO findMenuById( Integer menuid );
	
	public int saveMenu(MenuDO menu) ;
	
	public int updateMenu(MenuDO menu) ;
	
	/** 查找是否存在未删除的子节点 */
	public int findMenuByChildId (int menuid);
	
	public void deleteMenu(Integer userid , Integer menuid);
}