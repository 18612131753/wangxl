package com.ray.power.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.role.dao.RoleDao;
import com.ray.power.role.model.RoleDO;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao dao;

	public List<RoleDO> findAllRole() {
		return dao.findAllRole();
	}

	public int findUserNumByRoleId(Integer rid) {
		return dao.findUserNumByRoleId(rid);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		dao.deleteRoleMenuByRoleId(id);
	}

	public RoleDO findById(Integer id) {
		return dao.findById(id);
	}

	public void save(RoleDO role) {
		dao.save(role);
	}

	public void update(RoleDO role) {
		dao.update(role);
	}

	public List<Integer> findRoleMenuById(Integer roleid) {
		return dao.findRoleMenuById(roleid);
	}

	public void updateRoleMenu(Integer userid ,Integer rid, String mids) {
		dao.deleteRoleMenuByRoleId(rid);
		String[] marr = mids.split(",");
		for (String menuId : marr) {
			dao.saveRoleMenu(userid ,rid, Integer.valueOf(menuId));
		}
	}

	public List<ObejctSelector> findAllRoleSelector() {
		return dao.findAllRoleSelector();
	}
}
