<%@ page language="java" pageEncoding="UTF-8"%>
<div style="float:right;margin-right: 10px; height:100%;text-align: center;font-size: 20px;color: #6f7075;font-weight: 600;line-height: 47px;">
	<!-- <img src="${imagesPath}/title/t_title.png" />  -->
	<span class="north-panel-a">
		${loginUser.loginname}（<a class="north-panel-a" href="#" onclick="main_edit_employee();">${loginUser.loginname}</a>）
	</span>
	<span><a id="modifyQuestionBtn" class="north-panel-a">意见反馈</a></span>
	<span><a id="modifyPasswordBtn" class="north-panel-a">修改密码</a></span>
	<span><a id="modifyLogoutBtn"   class="north-panel-a">退出系统</a></span>
</div>
