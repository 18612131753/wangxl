package com.ray.power.org.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ray.power.org.dao.DepartmentDao;
import com.ray.power.org.form.DepartmentForm;
import com.ray.power.org.model.OrgDO;
import com.ray.power.util.GridDataModel;

@Service("orgService")
@Transactional
public class OrgServiceImpl implements OrgService {

	@Resource
	private DepartmentDao dao;

	public OrgDO findById(String id) {
		return dao.findById(id);
	}

	public GridDataModel<OrgDO> query(DepartmentForm queryForm) {
		GridDataModel<OrgDO> gridmdl = new GridDataModel<OrgDO>();
		List<OrgDO> list = dao.query(queryForm);
		gridmdl.setRows(list);
		gridmdl.setTotal(dao.queryCount(queryForm));
		return gridmdl;
	}

	public Integer save(OrgDO org) throws Exception {
		dao.save(org);
		return org.getOrgid();
	}

	public void update(OrgDO org) throws Exception {
		org.setUdate(new Date());
		dao.update(org);
	}

	/**
	 * @return 0=失败 1=成功 2=存在级联，不得删除
	 */
	public int deleteByPid(String pid) {
		try {
			// 查询机构下面是否还有人
			int count = 0;
			if (count > 0)
				return 2;
			dao.deleteByPid(pid);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}