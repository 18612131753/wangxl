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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.base.base.model.ObejctSelector;
import com.ray.base.util.GridDataModel;
import com.ray.base.util.ModelAndViewUtil;
import com.ray.base.util.SessionUtil;
import com.ray.power.login.model.UserSession;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuDO;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;
import com.ray.power.menu.service.MenuService;

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
	public ModelAndView queryforlist(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session,
			MenuForm form
	){
		GridDataModel<MenuGridModelVO> gdm = menuService.query(form);
		return ModelAndViewUtil.Json_ok(gdm, "form");
	}
	
	/**
	 * 获取一级菜单,下拉
	 * 0=搜索使用 + 全部
	 * 1=添加使用
	 * */
	@RequestMapping(value = "/findMenu1", method = {RequestMethod.GET})
	public ModelAndView findMenu1(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session,
			@RequestParam(value = "type", required = true) int type
	){
		List<ObejctSelector> list = menuService.findMenu1();
		if( type == 0 ){
			ObejctSelector os = new ObejctSelector();
			os.setText("全部");
			os.setValue("");
			list.add(0, os);
		} else if(type == 1 ){
			ObejctSelector os = new ObejctSelector();
			os.setText("# 一级菜单 #");
			os.setValue("0");
			list.add(0, os);
		}
		return ModelAndViewUtil.Json_Array(list);
	}
	
	/**
	 * 跳转到添加或编辑页面
	 * @return
	 */
	@RequestMapping("toSaveOrEdit/{new_or_edit}")
	public ModelAndView toSaveOrEdit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("new_or_edit") String new_or_edit ,
			@RequestParam(value="menuid", required=false)  Integer menuid){
		model.addAttribute("tabCode", tabCode);
		MenuDO menu = null ;
		if("create".equalsIgnoreCase(new_or_edit)){
			model.addAttribute("new_or_edit", "create");
			model.addAttribute("action","menu/save");
			model.addAttribute("menu", menu);
		} else {
			menu = menuService.findMenuById( menuid );
			model.addAttribute("new_or_edit", "edit");
			model.addAttribute("menu", menu);
			model.addAttribute("action","menu/edit/"+menu.getMenuid());
		}
		return ModelAndViewUtil.Jsp( "menu/saveOrEdit" );
	}
	
	@RequestMapping("save")
	public ModelAndView save (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			MenuDO menu
	){
		UserSession su = SessionUtil.getUserSession(session);
		menu.setCid( su.getUserid() );
		menu.setUid( su.getUserid() );
		menuService.saveMenu(menu) ;
		return ModelAndViewUtil.Json_ok();
	}
	
	@RequestMapping("edit/{menuid}")
	public ModelAndView edit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("menuid") Integer menuid ,
			MenuDO menu
	){
		UserSession su = SessionUtil.getUserSession(session);
		menu.setMenuid( menuid );
		menu.setUid( su.getUserid() );
		menuService.updateMenu(menu) ;
		return ModelAndViewUtil.Json_ok();
	}
	
	@RequestMapping("delete/{menuid}")
	public ModelAndView delete (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("menuid") Integer menuid
	){
		UserSession su = SessionUtil.getUserSession(session);
		int count = menuService.findMenuByChildId(menuid);
		//存在级联，不能删除
		if( count >0 ){
			return ModelAndViewUtil.Json_error2();
		}
		menuService.deleteMenu(su.getUserid() , menuid);
		return ModelAndViewUtil.Json_ok();
	}
}