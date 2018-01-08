package com.ray.exam.question.controller;

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

import com.ray.base.util.GridDataModel;
import com.ray.base.util.ModelAndViewUtil;
import com.ray.exam.question.model.QuestionDO;
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

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp(jspPath + "/index");
	}
	
	@RequestMapping("findAllQuestion")
	public ModelAndView findAllQuestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		GridDataModel<QuestionDO> gdm = new GridDataModel<QuestionDO>();
//		List<MenuTree> list = questionService.findAllDdMenu();
//		gdm.setRows(list);
		return ModelAndViewUtil.Json_ok(gdm);
	}



	@RequestMapping(value = "queryforlist", method = RequestMethod.POST)
	public ModelAndView queryforlist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MenuForm form) {
		GridDataModel<MenuGridModelVO> gdm = null;//questionService.query(form);
		return ModelAndViewUtil.Json_ok(gdm, "form");
	}


}