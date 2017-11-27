package com.ray.power.login.service;

import javax.servlet.http.HttpSession;

import com.ray.power.login.model.UserSession;



public interface LoginService {
	//获取账号（按账号名称）
	UserSession getUserSessionByLoginName(String loginName);
	//获取检查账号（按账号名称）
	UserSession checkUserSession(String loginName, String loginPwd);
	//登陆成功->初始化 UserSession
	void doLogon(HttpSession session, UserSession userSession);
	//密码更新
	String updatePwd(UserSession userSession, String oldPwd, String newPwd);
}
