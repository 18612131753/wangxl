package com.ray.power.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ray.power.base.model.ObejctSelector;
import com.ray.power.role.model.Role;


public interface RoleDao {
	
	@Select("SELECT roleid as value , name as text FROM power_role ")
	public List<ObejctSelector> findAllRoleSelector();

	@Select("SELECT * FROM ray_role ORDER BY id")
	public List<Role> findAllRole();
	
	@Select("SELECT count(*) FROM ray_user WHERE role_id=#{roleid}")
	public Integer findUserNumByRoleId( @Param("roleid")Integer roleid);
	
	@Insert(
		"INSERT INTO ray_role(id,name,role_type,create_id,create_date,update_id,update_date)"+
		"VALUES (seq_role.Nextval,#{role.name},#{role.role_type},#{role.create_id},sysdate,#{role.update_id},sysdate )"
	)
	public void save( @Param("role")Role role );
	
	@Update(
		"UPDATE ray_role SET name=#{role.name},role_type=#{role.role_type},"+
		"update_id=#{role.update_id},update_date=sysdate WHERE id=#{role.id}"
	)
	public void update( @Param("role")Role role );
	
	@Delete("DELETE FROM ray_role WHERE id=#{id}")
	public void deleteById(@Param("id")Integer id );
	
	@Delete("DELETE FROM ray_role_menu WHERE role_id=#{id}")
	public void deleteRoleMenuByRoleId( @Param("id")Integer id  );
	
	@Select("SELECT * FROM ray_role WHERE id=#{id}")
	public Role findById(@Param("id")Integer id);
	
	@Select("SELECT rrm.menu_id FROM ray_role_menu rrm "+
			"INNER JOIN ray_menu menu ON menu.id=rrm.menu_id "+
		    "WHERE rrm.role_id=#{id} AND menu.parent_id !=0")
	public List<Integer> findRoleMenuById(@Param("id")Integer id);
	
	@Insert("INSERT INTO ray_role_menu(role_id,menu_id) VALUES( #{rid},#{mid})")
	public void saveRoleMenu(@Param("rid")Integer rid , @Param("mid")Integer mid );
	
	
}
