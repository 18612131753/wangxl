package com.ray.power.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.menu.dao.MenuDao;
import com.ray.power.menu.model.MenuTree;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao dao;

	public List<MenuTree> findAllDdMenu() {
		return dao.findAllDdMenu();
	}

}
