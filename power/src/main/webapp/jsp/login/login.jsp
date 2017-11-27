<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="contextPath" value="<%=request.getContextPath() %>"/>
<c:set var="imagesPath" value="${contextPath}/script/images"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>系统</title>
	<link rel="shortcut icon"  href="${ctx}/script/images/title/s_title.png" type="image/x-icon" />
	<link href="${ctx}/script/css/login.css" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${ctx}/script/js/ajax.js" ></script>
	<script type="text/javascript">
	    var xWithScroll=0;
	    var yWithScroll=0;
	    
		function getPageSizeWithScroll(){
		    if (window.innerHeight && window.scrollMaxY) {// Firefox  
		        yWithScroll = window.innerHeight + window.scrollMaxY;  
		        xWithScroll = window.innerWidth + window.scrollMaxX;  
		    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac  
		        yWithScroll = document.body.scrollHeight;  
		        xWithScroll = document.body.scrollWidth;  
		    } else { // works in Explorer 6 Strict, Mozilla (not FF) and Safari  
		        yWithScroll = document.body.offsetHeight;  
		        xWithScroll = document.body.offsetWidth;  
		    }
		    return [xWithScroll, yWithScroll];
		} 
	
		function login_show_bg() {
			//$("#crm_login_bg").css({left:0, top:65, width:'100%', height:size[1]-65}).show();
			var login_bg = document.getElementById("crm_login_bg");
			if (login_bg) {
				//login_bg.style.left=0;
				//login_bg.style.top=65;
				//login_bg.style.width=xWithScroll-200;
				//login_bg.style.height=yWithScroll-65-200;
				login_bg.style.display="block";
			}
		}
		function login_hide_bg() {
			login_bg.style.display="none";
		}
		
		function refreshMe(obj) {
			obj.src = "validateCode?t=" + Math.random();
		}
		
		function ajaxCheck(){
			//如果是IE7或IE6,弹窗提示并打开google浏览器下载页面
			if(document.all){
				if(navigator.userAgent.indexOf("MSIE 7.")>0 
					|| navigator.userAgent.indexOf("MSIE 6.")>0){
					alert("IE版本太低，推荐使用谷歌chrome浏览器");
					window.open("http://www.google.cn/intl/zh-CN/chrome/browser/");
					return false;
				}
			}
			var loginname = document.getElementById("loginname");
			var uPwd = document.getElementById("password");
			var validateCode = document.getElementById("validateCode");
			var errorMsg = document.getElementById("errorMsg");
			//初步页面校验
			if(loginname.value==''){
				errorMsg.innerHTML="用户名不能为空！";
				return false;
			}else if(uPwd.value==''){
				errorMsg.innerHTML="密码不能为空！";
				return false;
			}else if(validateCode.value==''){
				errorMsg.innerHTML="验证不能为空！";
				return false;
			}else{
				login_show_bg();
				return doCheck();
			}
		}
		
		function doCheck(){
			var sign = false;
			ajax({
				type:"POST",
				dataType:"json",
				async:true,
				url: "${ctx}/ajaxCheckCodeValue",
				data:'loginname='+loginname.value+'&password='+uPwd.value+'&validateCode=' +validateCode.value,
				success:function(result) {
					if(result.result==1){
						sign = true;
						login_hide_bg();
					}else{
						refreshMe(document.getElementById("validate"));
						document.getElementById("errorMsg").innerHTML=result.mess;
						if(result.result==0){
							uPwd.value = "";
						}
						validateCode.value = "";
						login_hide_bg();
						sign = false;
					}
				},
				failure:function(obj){
					document.getElementById("errorMsg").innerHTML="系统异常错误,请与管理员联系！";
					login_hide_bg();
					sign = false;
				}
			});
			return sign;
		}
	</script>
</head>
<body onload="getPageSizeWithScroll()">
	<div id="box">
		<div id="main" >
		<input type="hidden" id="sign"/>
			<form action="${ctx}/logon" method="post" onsubmit="return ajaxCheck();">
				<table width="300" border="0">
					<tr>
						<td width="150" height="28" align="right" valign="middle" class="t2"></td>
						<td colspan="3" align="left" valign="bottom">
							<label style="color:red;font-size:12px;" id="errorMsg">${errorMsg}</label>
						</td>
					</tr>
					<tr>
						<td width="150" height="28" align="right" valign="middle"
							class="t2"><span class="t1">用户名：</span></td>
						<td colspan="3" align="left" valign="middle"><span class="t1">
								<label> <input type="text" class="textfield" name="loginname" id="loginname" value="admin"/> </label> </span>
						</td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="t2"><span
							class="t1">密&nbsp;&nbsp;码：</span></td>
						<td colspan="3" align="left" valign="middle"><span class="t1">
								<label> <input type="password" class="textfield2" name="password" id="password" value="zs"/> </label> </span>
						</td>
					</tr>
					<tr>
						<td height="28" align="right" valign="middle" class="t2"><span
							class="t1">验证码：</span></td>
						<td align="left" valign="middle"><span class="t1"> <label>
									<input type="text" class="textfield3" name="validateCode" id="validateCode" value="zs"/> </label> </span></td>
						<td width="65" align="right" valign="middle">
						<img src="validateCode" id="validate" style="cursor:pointer;" 
							onclick="refreshMe(this);" width="90" height="24" /></td>
						<td width="155" align="left" valign="middle"></td>
					</tr>

				</table>
				<table width="200" border="0" class="but">
					<tr>
						<td width="90"><label><input type="submit" class="button" value="" /></label></td>
						<td width="100" align="left" valign="middle"><label><input type="reset" class="button2" value="" /></label></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="login_suggest_div">
			<span class="login_suggest_span">建议使用 <a href="http://www.google.cn/intl/zh-CN/chrome/browser/" target="view_window">谷歌chrome</a>,<a href="http://www.firefox.com.cn/download/" target="view_window">火狐 </a>,IE8以上版本浏览器</span>
		</div>
	</div>
	<!-- 遮盖层 -->
	<div id="crm_login_bg" style="left:0px;top:0px;display:none;background-color:#E6E6E6;filter:alpha(opacity=20);opacity:0.2;z-index:99999;position:absolute;width:100%;height:100%;">
		<div style="font-size:15px;color:#FFFFFF;position:absolute;top:40%;left:50%;">
			<div style="float:left"><img src="${imagesPath}/load.gif"/></div>
		</div>
	</div>
</body>
</html>
