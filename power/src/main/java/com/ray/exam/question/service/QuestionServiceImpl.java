package com.ray.exam.question.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ray.base.base.model.ObejctSelector;
import com.ray.base.util.GridDataModel;
import com.ray.exam.question.dao.QuestionDao;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuDO;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Resource
	private QuestionDao dao;

	public List<MenuTree> findAllDdMenu() {
		return dao.findAllDdMenu();
	}

	public GridDataModel<MenuGridModelVO> query(MenuForm form) {
		GridDataModel<MenuGridModelVO> gridmdl = new GridDataModel<MenuGridModelVO>();
		List<MenuGridModelVO> list = dao.query(form);
		gridmdl.setRows(list);
		gridmdl.setTotal(dao.queryCount(form));
		return gridmdl ;
	}

	public List<ObejctSelector> findMenu1() {
		List<ObejctSelector> list = dao.findMenu1();
		return list ;
	}

	public MenuDO findMenuById(Integer menuid) {
		return dao.findMenuById(menuid);
	}

	public int saveMenu(MenuDO menu) {
		return dao.saveMenu(menu);
	}

	public int updateMenu(MenuDO menu) {
		return dao.updateMenu(menu);
	}

	public void deleteMenu(Integer userid , Integer menuid) {
		dao.updateMenuState(userid , menuid , 0 );
	}

	public int findMenuByChildId(int menuid) {
		return dao.findMenuByChildId( menuid);
	}

}
