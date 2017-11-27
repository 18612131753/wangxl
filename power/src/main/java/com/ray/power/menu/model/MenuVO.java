package com.ray.power.menu.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class MenuVO extends MenuTree implements Serializable {

	private static final long serialVersionUID = -5667847570264993501L;

	// 子菜单
	private List<MenuVO> childMenus;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<MenuVO> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuVO> childMenus) {
		this.childMenus = childMenus;
	}

}
