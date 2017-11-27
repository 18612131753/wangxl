package com.ray.power.role.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.role.dao.RoleDao;
import com.ray.power.role.model.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao dao;

	public List<Role> findAllRole() {
		return dao.findAllRole();
	}

	public int findUserNumByRoleId(Integer rid) {
		return dao.findUserNumByRoleId(rid);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		dao.deleteRoleMenuByRoleId(id);
	}

	public Role findById(Integer id) {
		return dao.findById(id);
	}

	public void save(Role role) {
		dao.save(role);
	}

	public void update(Role role) {
		dao.update(role);
	}

	public List<Integer> findRoleMenuById(Integer id) {
		return dao.findRoleMenuById(id);
	}

	public void updateRoleMenu(Integer rid, String mids) {
		dao.deleteRoleMenuByRoleId(rid);
		String[] marr = mids.split(",");
		for (String menuId : marr) {
			dao.saveRoleMenu(rid, Integer.valueOf(menuId));
		}
	}

	public List<ObejctSelector> findAllRoleSelector() {
		return dao.findAllRoleSelector();
	}
}
