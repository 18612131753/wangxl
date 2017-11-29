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
import com.ray.power.user.model.UserModel;

public interface UserDao {

	@Select("select pu.userid,pu.loginname,pu.password,pu.state,pu.isadmin,pu.roleid from power_user pu "
			+ "where pu.loginname = #{loginname}")
	public UserSession getUserSession(@Param("loginname") String loginname);

	@Update("UPDATE base_user SET update_tm=sysdate,update_id=#{mdl.update_id},crm_upwd=#{mdl.crm_upwd},crm_dupwd=#{mdl.crm_dupwd} WHERE id=#{mdl.id} and (crm_upwd=#{oldPwd} or crm_dupwd=#{oldPwd})")
	public void updatePwd(@Param("mdl") UserModel model, @Param("oldPwd") String oldPwd);

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

}