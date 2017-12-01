package com.ray.power.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.menu.dao.MenuDao;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.util.GridDataModel;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao dao;

	public List<MenuTree> findAllDdMenu() {
		return dao.findAllDdMenu();
	}

	public GridDataModel<MenuGridModelVO> query(MenuForm form) {
		GridDataModel<MenuGridModelVO> gridmdl = new GridDataModel<MenuGridModelVO>();
		List<MenuGridModelVO> list = dao.query(form);
		gridmdl.setRows(list);
		gridmdl.setTotal(dao.queryCount(form));
		return gridmdl ;
	}

}
