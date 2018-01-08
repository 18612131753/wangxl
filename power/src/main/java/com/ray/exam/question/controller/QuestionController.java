package com.ray.exam.question.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.base.base.model.ObejctSelector;
import com.ray.base.util.GridDataModel;
import com.ray.base.util.ModelAndViewUtil;
import com.ray.exam.question.service.QuestionService;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;

@Controller
@RequestMapping("/question")
public class QuestionController {

	private final String tabCode = "question";// 表名标示
	private final String jspPath = "question";// JSP 包名称

	private static Logger logger = LogManager.getLogger(QuestionController.class);

	@Resource(name = "questionService")
	private QuestionService questionService;

	@RequestMapping("findAllMenu")
	public ModelAndView findAllMenu(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		GridDataModel<MenuTree> gdm = new GridDataModel<MenuTree>();
//		List<MenuTree> list = questionService.findAllDdMenu();
//		gdm.setRows(list);
		return ModelAndViewUtil.Json_ok(gdm);
	}

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp(jspPath + "/index");
	}

	@RequestMapping(value = "queryforlist", method = RequestMethod.POST)
	public ModelAndView queryforlist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MenuForm form) {
		GridDataModel<MenuGridModelVO> gdm = null;//questionService.query(form);
		return ModelAndViewUtil.Json_ok(gdm, "form");
	}

	/**
	 * 获取一级菜单,下拉 0=搜索使用 + 全部 1=添加使用
	 */
	@RequestMapping(value = "/findMenu1", method = {RequestMethod.GET})
		public ModelAndView findMenu1(
				HttpServletRequest request, 
				HttpServletResponse response, 
				HttpSession session,
				@RequestParam(value = "type", required = true) int type
		){
			List<ObejctSelector> list = null;//menuService.findMenu1();
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
}