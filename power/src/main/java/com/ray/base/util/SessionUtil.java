package com.ray.base.util;

import javax.servlet.http.HttpSession;

import com.ray.power.login.model.UserSession;

public class SessionUtil {

	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";//放到session中的验证码key
	
	public static final String USER = "User";// 登录人
	
	/**
	 * 设置用户到session
	 * @param session
	 * @param user
	 */
	public static void saveUserSession(HttpSession session, UserSession userSession) {
		session.setAttribute(USER, userSession);
	}
	
	public static UserSession getUserSession(HttpSession session){
		return (UserSession)session.getAttribute(USER);
	}
	/**
	 * 退出
	 * @param session
	 */
	public static void logout(HttpSession session) {
		session.removeAttribute(USER);
	} 

}
