package com.ray.power.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.role.model.RoleDO;


public interface RoleDao {
	
	@Select("SELECT roleid as value , name as text FROM power_role ")
	public List<ObejctSelector> findAllRoleSelector();

	@Select("SELECT * FROM power_role")
	public List<RoleDO> findAllRole();
	
	@Select("SELECT count(*) FROM power_user WHERE roleid=#{roleid}")
	public Integer findUserNumByRoleId( @Param("roleid")Integer roleid);
	
	@Insert(
		"INSERT INTO power_role(name,role_type,cid,cdate,uid,udate)"+
		"VALUES (#{role.name},#{role.role_type},#{role.cid},now(),#{role.uid},now() )"
	)
	@Options(useGeneratedKeys = true, keyProperty = "role.roleid")
	public void save( @Param("role")RoleDO role );
	
	@Update(
		"UPDATE power_role SET name=#{role.name},role_type=#{role.role_type},"+
		"uid=#{role.uid},udate=now() WHERE roleid=#{role.roleid}"
	)
	public void update( @Param("role")RoleDO role );
	
	@Delete("DELETE FROM power_role WHERE roleid=#{roleid}")
	public void deleteById(@Param("roleid")Integer roleid );
	
	@Delete("DELETE FROM power_role_menu WHERE roleid=#{roleid}")
	public void deleteRoleMenuByRoleId( @Param("roleid")Integer roleid  );
	
	@Select("SELECT * FROM power_role WHERE roleid=#{roleid}")
	public RoleDO findById(@Param("roleid")Integer roleid);
	
	@Select("SELECT rrm.menuid FROM power_role_menu rrm "+
			"INNER JOIN power_menu menu ON menu.menuid=rrm.menuid "+
		    "WHERE rrm.roleid=#{roleid} AND menu.pmenuid !=0")
	public List<Integer> findRoleMenuById(@Param("roleid")Integer roleid);
	
	@Insert("INSERT INTO power_role_menu(roleid,menuid,cid,cdate,uid,udate) VALUES( #{rid},#{mid}, #{userid},now(), #{userid},now())")
	public void saveRoleMenu(@Param("userid")Integer userid,@Param("rid")Integer rid , @Param("mid")Integer mid );
	
	
}
