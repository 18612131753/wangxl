<%@ page language="java" pageEncoding="UTF-8"%>
<div style="float:left;position:absolute;left:5px;top:10px;">
	<img src="${imagesPath}/title/t_title.png" />
</div>
<div style="float:left;position:absolute;right:15px;top:15px;">
	<table>
		<tr>
			<td>
				<span title="你好，${loginUser.loginname}" style="color:#FFFFFF;">
					 -- 
					${loginUser.loginname}（<a style="color:#FFFFFF;" href="#" onclick="main_edit_employee();">${loginUser.loginname}</a>）
				</span>
			</td>
			<td><a id="modifyQuestionBtn" class="north-panel-a">意见反馈</a></td>
			<td><a id="modifyPasswordBtn" class="north-panel-a">修改密码</a></td>
			<td><a id="modifyLogoutBtn"   class="north-panel-a">退出系统</a></td>
		</tr>
	</table>
</div>
