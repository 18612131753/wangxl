package com.ray.power.menu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;
import com.ray.power.menu.service.MenuService;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.ModelAndViewUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private final String tabCode = "menu";//表名标示
	private final String jspPath = "menu";// JSP 包名称
	
	private static Logger logger = LogManager.getLogger(MenuController.class);

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
	
	@RequestMapping("index")
	public ModelAndView index(
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpSession session ,
			Model model){
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp(jspPath + "/index");
	}
	
	@RequestMapping(value = "queryforlist", method = RequestMethod.POST)
	public ModelAndView queryforlist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MenuForm form) {
		GridDataModel<MenuGridModelVO> gdm = menuService.query(form);
		return ModelAndViewUtil.Json_ok(gdm, "form");
	}
}