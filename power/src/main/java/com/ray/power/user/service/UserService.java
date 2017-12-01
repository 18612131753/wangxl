package com.ray.power.user.service;

import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserDO;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.util.GridDataModel;

public interface UserService {

	public GridDataModel<UserGridModelVO>  query(UserForm queryForm);
	
	public UserDO findUserById( Integer userid );
	
	public int saveUser(UserDO user) throws Exception;
	
	public void updateUser(UserDO user) throws Exception ;
}
