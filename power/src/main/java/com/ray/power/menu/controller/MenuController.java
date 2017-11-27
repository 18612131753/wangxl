package com.ray.power.menu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.menu.model.MenuTree;
import com.ray.power.menu.service.MenuService;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.ModelAndViewUtil;

@Controller
@RequestMapping("/ddmenu")
public class MenuController {
	
	//private final String tabCode = "menu";//表名标示
	
	@Resource(name = "menuService")
	private MenuService menuService;
	
	@RequestMapping("findAllMenu")
	public ModelAndView findAllMenu(
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpSession session){
		GridDataModel<MenuTree> gdm = new GridDataModel<MenuTree>();
		List<MenuTree> list = menuService.findAllDdMenu();
		gdm.setRows(list);
		return ModelAndViewUtil.Json_ok(gdm);
	}
}