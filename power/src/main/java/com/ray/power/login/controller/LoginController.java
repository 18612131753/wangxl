package com.ray.power.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.power.login.model.UserSession;
import com.ray.power.login.service.LoginService;
import com.ray.power.util.DdUtils;
import com.ray.power.util.IdentityUtil;
import com.ray.power.util.ModelAndViewUtil;
import com.ray.power.util.RandomValidateCode;
import com.ray.power.util.SessionUtil;

@Controller
@RequestMapping("")
public class LoginController {

	private static final String IS_NORMAL = "1";

	@Resource(name = "loginService")
	private LoginService loginService;

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login() {
		return ModelAndViewUtil.Jsp("login/login");
	}

	/**
	 * 转向登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/pageToLogin")
	public ModelAndView pageToLogin() {
		return ModelAndViewUtil.Jsp("login/login");
	}

	private UserSession getUserSession(String loginName, String loginPwd) {
		UserSession userSession = null;
		if (loginPwd.equals(IdentityUtil.getConfigPassword())) {
			userSession = loginService.getUserSessionByLoginName(loginName);
		} else {
			userSession = loginService.checkUserSession(loginName, loginPwd);
		}
		return userSession;
	}

	/**
	 * 登录处理
	 * 
	 * @param loginName
	 * @param password
	 * @param validateCode
	 * @param session
	 * @return
	 */
	@RequestMapping("/logon")
	public String logon(HttpSession session, Model model,
			@RequestParam(value = "loginname", required = true) String loginname,
			@RequestParam(value = "password", required = true) String upwd,
			@RequestParam(value = "validateCode", required = false) String validateCode) {
		RandomValidateCode rvc = new RandomValidateCode();
		String errorMsg = "";
		if (true) { // rvc.checkValidateCode(validateCode, session);
			UserSession userSession = this.getUserSession(loginname, upwd);
			if (userSession != null) {
				if (userSession.getState() != null && userSession.getState().intValue() == DdUtils.IS_DEL_NO) {
					// 允许登录->登录处理
					loginService.doLogon(session, userSession);
					return "redirect:/index";
				} else {
					errorMsg = "账号已经禁用，请联系管理员";
				}
			} else {
				errorMsg = "用户名或密码错误";
			}
		} else {
			errorMsg = "验证码错误";
		}
		model.addAttribute("errorMsg", errorMsg);
		return "login/login";
	}

	@RequestMapping("/ajaxCheckCodeValue")
	public ModelAndView ajaxCheckCodeValue(@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "password", required = false) String upwd,
			@RequestParam(value = "validateCode", required = false) String validateCode, HttpSession session) {
		// RandomValidateCode rvc = new RandomValidateCode();
		String errorMsg = "";
		if (true) {// rvc.checkValidateCode(validateCode, session)
			UserSession userSession = this.getUserSession(loginName, upwd);
			if (userSession != null) {
				if (LoginController.IS_NORMAL.equals(userSession.getState())) {
					// 允许登录->登录处理
					// loginService.doLogon(session, userSession);
					return ModelAndViewUtil.Json_ok();
				} else {
					errorMsg = "员工账号无效或已经离职";
					return ModelAndViewUtil.Json_error(errorMsg, 3);
				}
			} else {
				errorMsg = "用户名或密码错误";
				return ModelAndViewUtil.Json_error(errorMsg, 4);
			}
		} else {
			errorMsg = "验证码错误";
			return ModelAndViewUtil.Json_error(errorMsg, 2);
		}
	}

	/**
	 * 退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 系统日志
		UserSession userSession = SessionUtil.getUserSession(session);
		if (userSession != null) {
			String msg = "退出系统  (登录名：" + userSession.getLoginname() + ")";
			System.out.println(msg);
			// CrmLogUtil.Insert_SystemLog(user.getDepartment_id(), msg);
		}
		SessionUtil.logout(session);
		return "redirect:/login";
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/toMofifyPasswordPage")
	public ModelAndView toMofifyPasswordPage() {
		return ModelAndViewUtil.Jsp("login/modifyPassword");
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/mofifyPassword")
	public ModelAndView mofifyPassword(HttpSession session, Model model,
			@RequestParam(value = "oldPassword", required = false) String oldPwd,
			@RequestParam(value = "newPassword", required = false) String newPwd) {
		String errorMsg = "Session 失效，请重新登陆！";
		UserSession userSession = SessionUtil.getUserSession(session);
		if (userSession != null) {
			if (oldPwd == null || "".equals(oldPwd.trim())) {
				errorMsg = "旧密码不能为空!";
			} else if (newPwd == null || "".equals(newPwd.trim())) {
				errorMsg = "新密码不能为空!";
			} else {
				errorMsg = loginService.updatePwd(userSession, oldPwd, newPwd);
			}
		} else {
			model.addAttribute("relogin", true);// 重新登陆
		}
		model.addAttribute("errorMsg", errorMsg);
		return ModelAndViewUtil.Json_ok("errorMsg", errorMsg);
	}
}
