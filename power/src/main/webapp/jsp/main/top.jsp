<%@ page language="java" pageEncoding="UTF-8"%>
<div style="float:left; height: 100%;width: 100px;text-align: center;font-size: 20px;color: #6f7075;font-weight: 600;line-height: 47px;">
	<!-- <img src="${imagesPath}/title/t_title.png" />  -->
	管理系统
</div>
<div style="float:left;position:absolute;right:15px;top:13px;">
	<table>
		<tr>
			<td>
				<span title="你好，${loginUser.loginname}" class="north-panel-a">
					 -- 
					${loginUser.loginname}（<a class="north-panel-a" href="#" onclick="main_edit_employee();">${loginUser.loginname}</a>）
				</span>
			</td>
			<td><a id="modifyQuestionBtn" class="north-panel-a">意见反馈</a></td>
			<td><a id="modifyPasswordBtn" class="north-panel-a">修改密码</a></td>
			<td><a id="modifyLogoutBtn"   class="north-panel-a">退出系统</a></td>
		</tr>
	</table>
</div>
