<%@ page language="java" import="com.tarena.crm.login.model.UserSession"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<% UserSession userSession = (UserSession)session.getAttribute("userSession"); %>
<div id="modifyPassword_dialog">
	<!-- 修改密码 -->
	<div>
		<span id="PWD_ErrorMsg" style="margin-left:80px;color:red;">${errorMsg}</span>
		<form id="modifyPassword_form" action="${contextPath}/mofifyPassword" method="post">
			<table>
				<tr>
					<td class="td_right"><span class="label">账号名：</span></td>
					<td class="td_left"><input type="text" name="login_name" value="<%=userSession.getUnm() %>" readonly="readonly" disabled="true" /></td>
				</tr>
				<tr>
					<td class="td_right"><span class="label">旧密码：</span></td>
					<td class="td_left"><input type="password" id="oldPassword" name="oldPassword"/></td>
				</tr>
				<tr>
					<td class="td_right"><span class="label">新密码：</span></td>
					<td class="td_left"><input id="modifyPassword_newPassword" type="password" name="newPassword"/></td>
				</tr>
				<tr>
					<td class="td_right"><span class="label">新密码确认：</span></td>
					<td class="td_left"><input type="password" name="newPassword2"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
	function save_pwd_btn(){
		if(!$("#modifyPassword_form").valid()) 
			return false;
		$('#modifyPassword_dialog_submit').omButton('disable');
		$("#modifyPassword_form").omAjaxSubmit({
			method:'POST',
			url :'${contextPath}/mofifyPassword',
			dataType:'json',
			success: function(responseText, jqForm, options){
				var pwd_msg = "";
				if( responseText.result == 1){
					if(responseText.errorMsg=='1'){
						pwd_msg='修改成功！';
						
						$("#modifyPassword_dialog").omDialog('close');
					}else{
						pwd_msg=''+responseText.errorMsg;
						$('#modifyPassword_dialog_submit').omButton('enable');
					}
				} else {
					pwd_msg='异常错误！';
				}
				$("#PWD_ErrorMsg").html(pwd_msg);
				//main_messageTip_show(pwd_msg);
			},
		  	error:function(){
				$('#modifyPassword_dialog_submit').omButton('enable');
				$("#PWD_ErrorMsg").html(ERROR_MESSAGE);
		  		main_messageTip_show(ERROR_MESSAGE);
		  	}
		});
	}
	$(document).ready(function() {
		//只能输入字母和数字
		jQuery.validator.addMethod("oldnewpasswordsame", function(value, element) { 
			var oldPassword = $("#oldPassword").val();
			if(value==oldPassword){
				return false;
			}
			return true; 
		}, "旧密码和新密码不能相同"); 
		//表单验证
		$("#modifyPassword_form").validate({
			 rules:{
			 	login_name  : { required:true,maxlength:20 },
			 	oldPassword : { required:true,maxlength:20 },
			 	newPassword : { required:true,minlength:6, maxlength:20,oldnewpasswordsame:true },
			 	newPassword2: { required:true,equalTo:"#modifyPassword_newPassword" }
			 },
			 messages : {
				login_name  : {  required:"请输入用户名",maxlength:"用户名不得超过20个字符" },
				oldPassword : {  required:"请输入旧密码",maxlength:"旧密码不得超过20个字符" },
				newPassword : {  required:"请输入新密码",minlength:"新密码不能少于6个字符",maxlength:"新密码不得超过20个字符" },
				newPassword2: {  required:"请再输入一遍新密码",equalTo:"两次密码不一致" }
			 }
		});
		$("#modifyPassword_dialog").omDialog({
			title: "修改密码",
			autoOpen: true,
			height: 'auto',
			width: 450,
			modal: true,
			resizable:false,
			closeOnEscape : false ,
			onBeforeClose:function(){ },
			onClose :function(){ $("#modifyPassword_dialog").remove(); },//销毁DIV
			buttons : [
				{ text : "保存", id:'modifyPassword_dialog_submit' , click:save_pwd_btn }, 
				{text : "取消", id:'modifyPassword_dialog_colse',click : function(){ $( "#modifyPassword_dialog").omDialog('close'); } }
			]
		});
		$("#modifyPassword_dialog_submit").omButton({
			icons : { left : '${windowSubmitIcons}'}
       	});
		$("#modifyPassword_dialog_colse").omButton({
			icons : { left : '${windowCloseIcons}'}
       	});
	});
	
</script>