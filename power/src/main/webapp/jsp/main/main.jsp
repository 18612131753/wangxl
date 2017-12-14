<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	    <title>系统</title>
	    <link rel="shortcut icon"  href="${imagesPath}/title/s_title.png" type="image/x-icon" /><!-- odmis -->
	    <link rel="stylesheet"  type="text/css" href="${operamasksPath}/css/mycss/om-default.css" />
	    <link rel="stylesheet"  type="text/css" href="${cssPath}/opera.css" />
	    <link rel="stylesheet"  type="text/css" href="${cssPath}/main.css" />
	    <link rel="stylesheet"  type="text/css" href="${cssPath}/style.css" />
		
	    <script type="text/javascript" src="${jsPath}/main.js" ></script>
		<script type="text/javascript" src="${operamasksPath}/js/jquery.min.js" ></script>
	    <script type="text/javascript" src="${jsPath }/operamasks-ui.crm.min.js"></script>
		<script type="text/javascript" src="${jsPath }/json.js"></script>
		<script type="text/javascript" src="${jsPath }/iframepost.js"></script>
	</head>
	<body>
		<div id="north-panel" class="header"><%@ include file="./top.jsp"%></div>
    	<div id="center-panel">
    		<div id="center_tab" >
				<ul id="center_tab_ul">
				    <li><a href="#tab0" tabId="center_tab_zero" onclick="">&nbsp;&nbsp;首页&nbsp;&nbsp;</a></li>
				</ul>
				<div id="tab0" >
				<%@ include file="./welcome.jsp"%>
		        </div>
			</div>
    	</div>
    	<div id="west-panel"></div>
    	<div id="south-panel"><%@ include file="./footer.jsp"%></div>

		<%@ include file="./allUseDiv.jsp"%>
		<!-- 遮盖层 -->
		<div id="bnu_edu_bg" class="bnu_edu_bg">
			<div style="font-size:15px;color:#FFFFFF;position:absolute;top:40%;left:50%;">
				<div style="float:left"><img src="${imagesPath}/load.gif"/></div>
			</div>
		</div>
		
		<!-- 导出的ifream -->
		<iframe src="about:blank" name="crm_excel_iframe" id="crm_excel_iframe" style="display:none;"></iframe>
	</body>
	<script type="text/javascript">
		//树的节点
		var DepartmentTreeList ;
		/* 随时捕获窗口变化 ，修改TAB大小*/
		$(window).resize(function(){
			function changeCenteHeight(){
				var center_panel_heght = $('#center-panel').css('height');
				center_panel_heght = center_panel_heght.substring(0,center_panel_heght.length-2);
				CENTER_HEIGHT = center_panel_heght-4;  //.px
            	CENTER_TAB_HEIGHT = CENTER_HEIGHT-TAB_TITLE_HEIGHT;
				$('#center_tab').omTabs({height: 'fit'});
			}
			window.setTimeout(changeCenteHeight , 10);
		});
		$(document).ready(function() {  
//			$.ajax({
//				url:'${contextPath}/department/findDepartmentTree',
//				type:'POST',
//				dataType:'json',
//				async:false,
//				success:function(msg){
//					DepartmentTreeList = msg;
//				}
//			});
			
			//屏蔽左侧菜单的右击事件
        	document.getElementById("west-panel").oncontextmenu=function(event) {
			    if (document.all){
			    	window.event.returnValue = false;//for IE
			    }else {
			    	event.preventDefault();
			    } 
			};
           var myDate = new Date();
           // 修改密码框
           $("#modifyPasswordBtn").click(function(){
           		main_ChangeDivContent("div_for_dialog","${contextPath}/toMofifyPasswordPage/");
           });
           // 退出系统
           $("#modifyLogoutBtn").click(function(){
           		location.href='${contextPath}/logout' ;
           });
           // 意见反馈
           $("#modifyQuestionBtn").click(function(){
           		main_ChangeDivContent( "div_for_dialog","${contextPath}/question/toAddQuestion" );
           });
           $('body').omBorderLayout({
           	   panels:[{
           	        id:"north-panel",
           	        region:"north",
           	        header:false,
           	        resizable:false,
           	        collapsible:false, //可折叠
           	        height:47,
           	     	closable: false  //是否允许关闭
           	    },{
           	        id:"south-panel",
           	        region:"south",
           	        resizable:false,
           	        collapsible:true,
           	        height:0,
           	        header:false
           	    },{
           	        id:"center-panel",
           	     	header:false,
           	        region:"center",
           	        height:'fit'
           	    },{
           	        id:"west-panel",
           	        resizable:true,
           	        collapsible:true,
           	        title:"<marquee scrollamount='2' onMouseOut=this.start() onMouseOver=this.stop() id='welcomeInfo'>"+getWelcomeStr(myDate.getHours(),myDate.getMinutes())+"，${loginUser.loginname}，欢迎您使用组织工作系统。</marquee>",
           	        region:"west",
           	        expandToBottom : true, //延展到底部
           	        width:210
           	    }],
           	    hideCollapsBtn : true,
           	    spacing : 8
            });
            //加载左侧菜单树
            main_ChangeDivContent("west-panel","${contextPath}/index/left");
            //加载中心TAB
            $('#center_tab').omTabs({
            	border : true,
            	closable : [1],
            	tabMenu : true,
            	height:'fit',
	           	onBeforeClose : function(n,event) {
			        if(n==0){
			        	return false;
			        }
			    },
			    onBeforeCloseAll : function(event) {
			    	var count = $('#center_tab').omTabs('getLength');
			    	for(var index=1;index<count;index++){
			    		 $('#center_tab').omTabs('close',index);
			    		 index--;
			    		 count--;
			    	}
			     	return false;
			    }
            });

           //布局汇总，center中心的高度,main.jsp中计算赋值，全局使用
           CENTER_HEIGHT = $('#center-panel').css('height');  //.px
           CENTER_HEIGHT = CENTER_HEIGHT.substring(0,CENTER_HEIGHT.length-2);
           CENTER_TAB_HEIGHT = CENTER_HEIGHT-TAB_TITLE_HEIGHT;
        });
		function getWelcomeStr(hours,minute){
        	if(hours>=0 && hours<5){
        		return "凌晨好";
        	}else if(hours>=5 && hours<9){
        		return "早晨好";
        	}else if((hours>=9 && hours<11)||(hours==11 && minute <=30)){
        		return "上午好";
        	}else if((hours==11 && minute >30)||(hours==12 && minute <=30)){
        		return "中午好";
        	}else if((hours==12 && minute >30)||(hours>12 && hours <18)){
        		return "下午好";
        	}else if(hours>=18 && hours<24){
        		return "晚上好";
        	}
        }
    </script>
</html>