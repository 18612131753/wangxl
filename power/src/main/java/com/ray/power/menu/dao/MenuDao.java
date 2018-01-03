package com.ray.power.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.menu.form.MenuForm;
import com.ray.power.menu.model.MenuDO;
import com.ray.power.menu.model.MenuGridModelVO;
import com.ray.power.menu.model.MenuTree;

public interface MenuDao {

	@Select("select * from ray_menu order by parent_id ,order_num asc")
	public List<MenuTree> findAllDdMenu();
	
	public List<MenuGridModelVO> query(@Param("form") MenuForm form);
	public int queryCount(@Param("form")MenuForm form);
	
	@Select("select menuid as value , name as text from power_menu where pmenuid=0 order by ordernum asc")
	public List<ObejctSelector> findMenu1();
	
	@Select("select * from power_menu where menuid=#{menuid}")
	public MenuDO findMenuById( @Param("menuid")Integer menuid );
	
	@Insert(
		"INSERT INTO power_menu ( name, url,pmenuid,ordernum,cid,cdate,uid,udate ) "+
		"VALUES( #{menu.name},#{menu.url},#{menu.pmenuid},#{menu.ordernum},#{menu.cid},now(),#{menu.uid},now())")
	@Options(useGeneratedKeys = true, keyProperty = "menu.menuid")
	public int saveMenu(@Param("menu")MenuDO menu) ;
	
	@Update(
		"UPDATE power_menu SET name=#{menu.name},url=#{menu.url},pmenuid=#{menu.pmenuid},ordernum=#{menu.ordernum},uid=#{menu.uid},udate=now() "+
		"where menuid=#{menu.menuid}"
	)
	public int updateMenu(@Param("menu")MenuDO menu) ;
	
	@Select("select count(*) from power_menu where pmenuid=#{menuid} and state=1")
	public int findMenuByChildId(@Param("menuid")int menuid);
	
	@Update(
		"UPDATE power_menu SET state=#{state},uid=#{userid},udate=now() "+
		"where menuid=#{menuid}"
	)
	public void updateMenuState(@Param("userid")Integer userid , @Param("menuid")Integer menuid, @Param("state")Integer state) ;
}