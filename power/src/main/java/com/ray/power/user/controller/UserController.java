package com.ray.power.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.base.DateEditor;
import com.ray.power.login.model.UserSession;
import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserDO;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.user.service.UserService;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.ModelAndViewUtil;
import com.ray.power.util.SessionUtil;

@Controller
@RequestMapping("/user") // 注：系统唯一请求标示
public class UserController {

	private final String jspPath = "user";// JSP 包名称
	private final String tabCode = "user";// 表名标示

	private static Logger logger = LogManager.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 绑定controller日期转换处理
	 * 
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	@RequestMapping("")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		return index(request, response, session, model);
	}

	@RequestMapping(value="index",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		logger.info("...用户管理");
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp(jspPath + "/index");
	}

	@RequestMapping(value = "queryforlist", method = RequestMethod.POST)
	public ModelAndView queryforlist(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			UserForm queryForm) {
		logger.info("queryforlist");
		logger.info(queryForm.getRoleid() +"#"+queryForm.getState()+"#"+queryForm.getLoginname());
		GridDataModel<UserGridModelVO> gdm = userService.query(queryForm);
		return ModelAndViewUtil.Json_ok(gdm, "userForm");
	}

	/**
	 * 跳转到添加或编辑页面
	 * @return
	 */
	@RequestMapping(value="toSaveOrEdit/{new_or_edit}",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toSaveOrEdit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("new_or_edit") String new_or_edit ,
			@RequestParam(value="userid", required=false)  Integer userid){
		model.addAttribute("tabCode", tabCode);
		UserDO user = null ;
		if("create".equalsIgnoreCase(new_or_edit)){
			model.addAttribute("new_or_edit", "create");
			model.addAttribute("action","user/save");
			model.addAttribute("euser", user);
		} else {
			user = userService.findUserById( userid );
			model.addAttribute("new_or_edit", "edit");
			model.addAttribute("euser", user);
			model.addAttribute("action","user/edit/"+user.getUserid());
		}
		return ModelAndViewUtil.Jsp( "user/saveOrEdit" );
	}

	@RequestMapping(value="save",method = {RequestMethod.POST})
	public ModelAndView save (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			UserDO user
	){
		UserSession su = SessionUtil.getUserSession(session);
		user.setCid( su.getUserid() );
		user.setUid( su.getUserid() );
		try {
			userService.saveUser(user) ;
		} catch (DuplicateKeyException e) {
			return ModelAndViewUtil.Json_error("用户名重复，添加失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ModelAndViewUtil.Json_ok();
	}
	
	@RequestMapping("edit/{userid}")
	public ModelAndView edit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("userid") Integer userid ,
			UserDO user
	){
		UserSession su = SessionUtil.getUserSession(session);
		user.setUid( su.getUserid() );
		user.setUserid( userid );
		try{
			userService.updateUser(user) ;
		} catch (DuplicateKeyException e) {
			return ModelAndViewUtil.Json_error("用户名重复，添加失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ModelAndViewUtil.Json_ok();
	}
	
	
	//例子：导出excel
	@RequestMapping("userexcel")
	public ModelAndView userexcel (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@RequestParam(value="userid")  Integer userid){
		model.addAttribute("tabCode", tabCode);
		
		UserDO user = userService.findUserById( userid );
		List<UserDO> list = new ArrayList<UserDO>();
		list.add( user );
		model.addAttribute("list", list);
		return ModelAndViewUtil.Jsp( "user/userexcel" );
	}
}
