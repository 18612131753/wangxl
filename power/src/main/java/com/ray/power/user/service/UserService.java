package com.ray.power.user.service;

import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserGridModelVO;
import com.ray.power.util.GridDataModel;

public interface UserService {

	public GridDataModel<UserGridModelVO>  query(UserForm queryForm);
	
}