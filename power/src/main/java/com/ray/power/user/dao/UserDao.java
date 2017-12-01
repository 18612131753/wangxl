package com.ray.power.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ray.power.login.model.UserSession;
import com.ray.power.user.form.UserForm;
import com.ray.power.user.model.UserDO;
import com.ray.power.user.model.UserGridModelVO;

public interface UserDao {

	@Select("select pu.userid,pu.loginname,pu.password,pu.state,pu.isadmin,pu.roleid from power_user pu "
			+ "where pu.loginname = #{loginname}")
	public UserSession getUserSession(@Param("loginname") String loginname);

	public List<UserGridModelVO> query(@Param("form") UserForm queryForm);

	public int queryCount(@Param("form") UserForm queryForm);

	@Select("select pu.userid,pu.loginname,pu.roleid,pu.state,pu.isadmin from power_user pu "
			+ "where pu.userid = #{userid}")
	public UserDO findUserById(@Param("userid") Integer userid);
	
	@Insert(
		"INSERT INTO power_user ( loginname, password,roleid,isadmin,state,cid,cdate,uid,udate ) "+
		"VALUES( #{user.loginname},#{user.password},#{user.roleid},#{user.isadmin},#{user.state},#{user.cid},now(),#{user.uid},now())")
	@Options(useGeneratedKeys = true, keyProperty = "user.userid")
	public int saveUser(@Param("user")UserDO user) throws Exception;
	
	@Update(
		"UPDATE power_user SET loginname=#{user.loginname},roleid=#{user.roleid},isadmin=#{user.isadmin},state=#{user.state},uid=#{user.uid},udate=now() "+
		"where userid=#{user.userid}"
	)
	public int updateUser(@Param("user")UserDO user)throws Exception ;
	
	@Update("UPDATE power_user SET password=#{user.password},uid=#{user.uid},udate=now() where userid=#{user.userid}")
	public void updatePwd( @Param("user")UserDO user );

}