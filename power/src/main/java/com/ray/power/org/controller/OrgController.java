package com.ray.power.org.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.base.DateEditor;
import com.ray.power.login.model.UserSession;
import com.ray.power.org.dao.DepartmentDao;
import com.ray.power.org.form.DepartmentForm;
import com.ray.power.org.model.OrgDO;
import com.ray.power.org.service.OrgService;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.ModelAndViewUtil;
import com.ray.power.util.SessionUtil;
/**
 * 机构管理
 * @author 
 *
 */
@Controller
@RequestMapping("/org")
public class OrgController {
	
	private final String tabCode = "org";//表名标示
	
	@Resource
	private DepartmentDao departmentDao ;
	
	@Resource(name = "orgService")
	private OrgService orgService;
	
	/**
	 * 绑定controller日期转换处理
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	@RequestMapping("")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model){
		return index(request,response,session,model);
	}
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model ){
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp("department/index");
	}
	
	@RequestMapping("queryForList")
	public ModelAndView queryForList(HttpServletRequest request,HttpServletResponse response, 
			HttpSession session, DepartmentForm queryForm){
		GridDataModel<OrgDO> gdm = orgService.query(queryForm);
		return ModelAndViewUtil.Json_ok(gdm,"departmentForm");
	}
	
	/**
	 * 跳转到添加或编辑页面
	 * @return
	 */
	@RequestMapping("toSaveOrEditPage")
	public ModelAndView toSaveOrEditPage(
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@RequestParam String id,@RequestParam String new_or_edit){
		OrgDO org = null;
		model.addAttribute("tabCode", tabCode);
		if("create".equalsIgnoreCase(new_or_edit)){
			model.addAttribute("new_or_edit", "create");
			model.addAttribute("action", "department/save");
		}else {
			org = orgService.findById(id);
			model.addAttribute("new_or_edit", "edit");
			model.addAttribute("dep", org);
			model.addAttribute("action","department/edit/"+org.getOrgid());
		}
		return ModelAndViewUtil.Jsp( "department/saveOrEdit" );
	}
	
	/**
	 * 添加 业务逻辑   
	 * @param request
	 * @param response
	 * @param mdl
	 * @return
	 */
	@RequestMapping("save")
	public ModelAndView save(
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			OrgDO org ){
		UserSession user = SessionUtil.getUserSession(session);
		org.setCid( user.getUserid() );
		org.setUid( user.getUserid() );
		try {
			orgService.save( org );
			return ModelAndViewUtil.Json_ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ModelAndViewUtil.Json_error();
		}
	}
	
	/***
	 * 编辑 业务逻辑
	 * @param request
	 * @param response
	 * @param mdl
	 * @return
	 */
	@RequestMapping("edit/{id}")
	public ModelAndView edit(HttpSession session,HttpServletRequest request, HttpServletResponse response,
			OrgDO org,@PathVariable String id){
		
			return ModelAndViewUtil.Json_error();
	}
	
	/**
	 * @return 0=失败 1=成功 2=存在级联，不得删除
	 * */
	@RequestMapping("delete/{pid}")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String pid){
		int bool = orgService.deleteByPid(pid);
		if( bool == 0 ) {
			return ModelAndViewUtil.Json_error();
		} else if( bool ==1 ){
			return ModelAndViewUtil.Json_ok();
		} else {
			return ModelAndViewUtil.Json_error2();
		}
	}
	
	@RequestMapping("toShowTree")
	public ModelAndView toShowTree(HttpServletRequest request, HttpServletResponse response){
		return ModelAndViewUtil.Jsp("department/showTree");
	}
	
	/***
	 * 查询用户权限范围内全部的树
	 * @param request
	 * @param response
	 * @param mdl
	 * @return
	 */
	@RequestMapping("findDepartmentTree")
	public ModelAndView findDepartmentTree(
			HttpSession session,
			HttpServletRequest request, HttpServletResponse response
	){
	//	UserSession userSession = SessionUtil.getUserSession(session);
	//	List<OrgTree> dt = departmentDao.findByDepRole(userSession.());
		
		return ModelAndViewUtil.Json_error();
	}
}
