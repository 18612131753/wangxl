package com.ray.power.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.login.model.UserSession;
import com.ray.power.menu.model.MenuVO;
import com.ray.power.util.ModelAndViewUtil;
import com.ray.power.util.SessionUtil;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("")
	public ModelAndView main(HttpSession session, Model model){
		UserSession loginUser = SessionUtil.getUserSession(session);
		// List<OrgTree> list = loginUser.getOrgs();
		// model.addAttribute("departmentTree", list.toArray());
		model.addAttribute("loginUser", loginUser);
		return ModelAndViewUtil.Jsp("main/main"); //jsp/main.jsp
	}
	
	/**
	 * 左侧菜单
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("left")
	public ModelAndView left( HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model ){
		List<MenuVO> list = SessionUtil.getUserSession(session).getMenus();
		model.addAttribute("menuList", list);
		return ModelAndViewUtil.Jsp("main/left");
	}
	
}
