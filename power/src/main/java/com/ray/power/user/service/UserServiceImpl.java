package com.ray.power.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.user.dao.UserDao;
import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserDO;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.util.GridDataModel;

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
		return dao.saveUser( user );
	}

}
