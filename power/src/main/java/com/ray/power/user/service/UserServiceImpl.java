package com.ray.power.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.user.dao.UserDao;
import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserDO;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.util.GridDataModel;
import com.ray.power.util.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao dao;

	public GridDataModel<UserGridModelVO> query(UserForm queryForm) {
		GridDataModel<UserGridModelVO> gridmdl = new GridDataModel<UserGridModelVO>();
		List<UserGridModelVO> list = dao.query(queryForm);
		gridmdl.setRows(list);
		gridmdl.setTotal(dao.queryCount(queryForm));
		return gridmdl;
	}

	public UserDO findUserById(Integer userid) {
		return dao.findUserById(userid);
	}

	public int saveUser(UserDO user) throws Exception{
		user.setPassword( MD5Util.getStringMD5(user.getPassword()) );
		return dao.saveUser( user );
	}

	public void updateUser(UserDO user) throws Exception {
		//1. 更新信息
		dao.updateUser( user );
		//2. 如果有密码，修改密码
		if( user.getPassword() !=null || user.getPassword().length()>0){
			user.setPassword( MD5Util.getStringMD5(user.getPassword()) );
			dao.updatePwd(user);
		}
	}

}
