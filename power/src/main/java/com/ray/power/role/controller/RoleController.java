package com.ray.power.role.controller;

import java.util.Date;
import java.util.List;

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
import com.ray.power.base.model.ObejctSelector;
import com.ray.power.login.model.UserSession;
import com.ray.power.role.model.Role;
import com.ray.power.role.service.RoleService;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.ModelAndViewUtil;
import com.ray.power.util.SessionUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	private final String tabCode = "role";//表名标示
	
	@Resource(name = "roleService")
	private RoleService roleService;
	
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
		return ModelAndViewUtil.Jsp("role/index");
	}
	
	@RequestMapping("findAllRole")
	public ModelAndView findAllRole(
			HttpServletRequest request,
			HttpServletResponse response, 
			HttpSession session){
		GridDataModel<Role> gdm = new GridDataModel<Role>();
		List<Role> list = roleService.findAllRole();
		gdm.setRows(list);
		return ModelAndViewUtil.Json_ok(gdm);
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
			@RequestParam Integer id,
			@RequestParam String new_or_edit){
		Role role = null;
		model.addAttribute("tabCode", tabCode);
		if("create".equalsIgnoreCase(new_or_edit)){
			model.addAttribute("new_or_edit", "create");
			model.addAttribute("action", "role/save");
		} else {
			role = roleService.findById(id);
			model.addAttribute("new_or_edit", "edit");
			model.addAttribute("role", role);
			model.addAttribute("action","role/edit/"+role.getId());
		}
		return ModelAndViewUtil.Jsp( "role/saveOrEdit" );
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
			Role role ){
		UserSession user = SessionUtil.getUserSession(session);
		role.setCreate_id( user.getUserid() );
		role.setUpdate_id( user.getUserid() );
		try {
			roleService.save( role );
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
			Role role ,@PathVariable Integer id){
		role.setId( id );
		UserSession user = SessionUtil.getUserSession(session);;
		role.setUpdate_id( user.getUserid() );
		try {
			roleService.update( role );
			return ModelAndViewUtil.Json_ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ModelAndViewUtil.Json_error();
		}
	}
	
	/**
	 * @return 0=失败 1=成功 2=存在级联，不得删除
	 * */
	@RequestMapping("delete/{id}")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer id){
		try {
			//查询角色下面是否还有人
			int count = roleService.findUserNumByRoleId( id );
			if( count > 0 ) return ModelAndViewUtil.Json_error2();
			roleService.deleteById(id);
			return ModelAndViewUtil.Json_ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ModelAndViewUtil.Json_error();
		}
	}
	
	/**
	 * 查询角色关联的菜单
	 * */
	@RequestMapping("findRoleMenu/{id}")
	public ModelAndView findRoleMenu(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer id){
		List<Integer> list = roleService.findRoleMenuById(id);
		return ModelAndViewUtil.Json_ok( list );
	}
	
	/**
	 * 更新角色关联菜单
	 * @param rid 角色ID
	 * */
	@RequestMapping("updateRoleMenu/{rid}")
	public ModelAndView updateRoleMenu(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer rid , 
			String menus
	){
		System.out.println( menus );
		roleService.updateRoleMenu(rid , menus);
		return ModelAndViewUtil.Json_ok( );
	}
	
	/**
	 * 角色下拉
	 * 0=搜索使用 + 全部
	 * 1=添加使用
	 * */
	@RequestMapping("findRoleCombo")
	public ModelAndView findRoleCombo(
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@RequestParam(value = "type", required = true) int type
			){
		List<ObejctSelector> list = roleService.findAllRoleSelector();
		if( type == 0 ){
			ObejctSelector os = new ObejctSelector();
			os.setText("全部");
			os.setValue("");
			list.add(0, os);
		}
		return ModelAndViewUtil.Json_Array(list);
	}
}
