<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@page import="com.ray.power.login.model.UserSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	UserSession user = com.ray.base.util.SessionUtil.getUserSession(request.getSession());
	if(user == null){
		//重定向登录界面
		System.out.println("重定向登录界面");
%>
		<script type="text/javascript">
			location.href = "<%=basePath %>index";
		</script>
<%
	}else{
		request.getSession().setAttribute("user",user);
	}
%>
<c:set var="basePath" value="<%=basePath1 %>"></c:set>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<c:set var="jsPath" value="${contextPath}/script/js"/>
<c:set var="cssPath" value="${contextPath}/script/css"/>
<c:set var="imagesPath" value="${contextPath}/script/images"/>
<c:set var="jspPath" value="${contextPath}/jsp"/>
<c:set var="helpPath" value="${contextPath}/help"/>
<c:set var="operamasksPath" value="${contextPath}/operamasks-ui-2.0"/>
<!-- 窗口的确定，取消按钮图片 -->
<!-- 保存 -->
<c:set var="windowSubmitIcons" value="${imagesPath}/save.png"/>
<c:set var="windowCloseIcons" value="${imagesPath}/win_close.png"/>
<!-- 按钮图片 开始 -->
<!-- 搜索 -->
<c:set var="buttonSearchIcons" value="${imagesPath}/search.png"/>
<!-- 添加 -->
<c:set var="buttonAddIcons" value="${imagesPath}/add.png"/>
<!-- 刷新 -->
<c:set var="buttonRefreshIcons" value="${imagesPath}/refresh.png"/>
<!-- 修改 -->
<c:set var="buttonEditIcons" value="${imagesPath}/edit.png"/>
<!-- 查询 -->
<c:set var="buttonSearchIcons" value="${imagesPath}/search.png"/>
<!-- 树 -->
<c:set var="buttonTreeIcons" value="${imagesPath}/tree.png"/>
<!-- 删除 -->
<c:set var="buttonRemoveIcons" value="${imagesPath}/remove.png"/>
<!-- 还原 -->
<c:set var="buttonReduceIcons" value="${imagesPath}/reduce.png"/>
<!-- 密码 -->
<c:set var="buttonPasswordIcons" value="${imagesPath}/password.png"/>
<!-- 重置 -->
<c:set var="buttonResetIcons" value="${imagesPath}/reset.png"/>
<!-- 数据配置 -->
<c:set var="buttonDataconfigIcons" value="${imagesPath}/dataconfig.png"/>
<!-- 维护 -->
<c:set var="buttonDefendIcons" value="${imagesPath}/defend.png"/>
<!-- 打印 -->
<c:set var="buttonPrintIcons" value="${imagesPath}/print.png"/>
<!-- 打印 -->
<c:set var="buttonSendIcons" value="${imagesPath}/send.png"/>
<!-- 上传 -->
<c:set var="buttonUploadIcons" value="${imagesPath}/upload.png"/>
<!-- 下载 -->
<c:set var="buttonDownloadIcons" value="${imagesPath}/download.png"/>
<!-- 废弃 -->
<c:set var="buttonScrapIcons" value="${imagesPath}/scrap.png"/>
<!-- 批量导入-->
<c:set var="buttonBatchImportIcons" value="${imagesPath}/upload.png"/>
<!-- 批量保存-->
<c:set var="buttonBatchSaveIcons" value="${imagesPath}/upload.png"/>
<!-- 帮助-->
<c:set var="buttonHelpIcons" value="${imagesPath}/help.png"/>
<!-- 分配-->
<c:set var="buttonManageIcons" value="${imagesPath}/manage.png"/>
<!-- 发送-->
<c:set var="buttonSpendIcons" value="${imagesPath}/spend.png"/>

<!-- 按钮图片 结束 -->

<!-- 学员快搜-->
<c:set var="studentRightPng" value="${imagesPath}/rightsearch/student.png"/>
<!-- 咨询快搜-->
<c:set var="consulterRightPng" value="${imagesPath}/rightsearch/consulter.png"/>