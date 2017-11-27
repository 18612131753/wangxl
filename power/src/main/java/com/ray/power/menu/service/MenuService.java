package com.ray.power.menu.service;

import java.util.List;

import com.ray.power.menu.model.MenuTree;

public interface MenuService {
	public List<MenuTree> findAllDdMenu();
}