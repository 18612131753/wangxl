package com.ray.power.login.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ray.base.util.MD5Util;
import com.ray.base.util.SessionUtil;
import com.ray.power.login.model.UserSession;
import com.ray.power.menu.model.MenuVO;
import com.ray.power.org.dao.DepartmentDao;
import com.ray.power.user.dao.UserDao;
import com.ray.power.user.dao.UserDataRoleDao;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static Logger logger = LogManager.getLogger(LoginServiceImpl.class);

	@Resource
	private UserDao userDao;

	@Resource
	private UserDataRoleDao userDataRoleDao;

	@Resource
	private DepartmentDao departmentDao;

	public UserSession checkUserSession(String loginName, String loginPwd) {
		if (loginName != null && loginPwd != null) {
			String pwdMd5 = MD5Util.getStringMD5(loginPwd);
			UserSession userSession = userDao.getUserSession(loginName);// 查询用户基本信息
			if( userSession == null || userSession.getPassword() == null ) return null ;
			
			if ( userSession.getPassword().equals(pwdMd5) ) {
				return userSession;
			}
		}
		return null;
	}

	public UserSession getUserSessionByLoginName(String loginName) {
		return userDao.getUserSession(loginName);// 查询用户基本信息
	}

	public void doLogon(HttpSession session, UserSession userSession) {
		//获取菜单权限
		List<MenuVO> menus = userDataRoleDao.findMenuByRoleId(userSession.getRoleid());
		userSession.setMenus(menus);
		// 自身和子机构全部取出
		// List<OrgTree> dt = departmentDao.findByDepRole(userSession.getOrgs());
		
		// userSession.setDepartments(dt);
		SessionUtil.saveUserSession(session, userSession);

		// 系统日志
		// String msg = "登录 (登录名："+userSession.getLogin_name()+")";
		// System.out.println(msg);
		// CrmLogUtil.Insert_SystemLog(userSession.getDepmentId(), msg);
	}

	public String updatePwd(UserSession userSession, String oldPwd, String newPwd) {
//		String oldPwdMd5 = MD5Util.getStringMD5(oldPwd);
//		if (!(oldPwdMd5.equals(userSession.getPassword()) || oldPwdMd5.equals(userSession.getPassword()))) {
//			return "旧密码输入错误！";
//		}
//		String newPwdMd5 = MD5Util.getStringMD5(newPwd);
//		UserModel model = new UserModel();
//		model.setUid(userSession.getId());
//		model.setCrm_upwd(newPwdMd5);
//		model.setCrm_dupwd("");
//		model.setId(userSession.getId());
//		userDao.updatePwd(model, oldPwdMd5);
//		String errorMsg = "1";
//		// 日志
		String msg = "修改密码  (登录名：" + userSession.getLoginname() + ")";
		System.out.println(msg);
		// CrmLogUtil.Insert_SystemLog(oldUser.getDepartment_id(), msg);
		return msg;
	}
}
