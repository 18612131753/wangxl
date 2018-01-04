<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!-- 角色栏  -->
<div style="float:left;width:50%;">
	<div id="${tabCode}_role">
		<div id="${tabCode}_role_buttonbar" style="margin-top:3px;margin-bottom:2px"></div>
		<table id="${tabCode}_role_grid" ></table>
	</div>
</div>

<!-- 菜单栏  -->
<div style="float:left;width:50%;">
	<div id="${tabCode}_menu">
		<div id="${tabCode}_menu_buttonbar" style="margin-top:3px;margin-bottom:2px"></div>
		<div class="div_language">
			<span style="margin:0 0 0 5px">菜单关联的角色：</span>
			<span style="color:red" id="${tabCode}_menu_rolename"></span>
		</div>
		<div id="${tabCode}_menu_showtree" style="overflow:auto;">
			<ul id="${tabCode}_menutree"></ul>
		</div>
	</div>
</div>

<script type="text/javascript">
var tabCode = "${tabCode}";
$(document).ready(function() {
//角色 
	$('#'+tabCode+'_role').omPanel({
		title:'<span style="margin:0 0 0 10px">角色</span>'
	});
	$('#'+tabCode+'_role_buttonbar').omButtonbar({
		btns:[{separtor:true},{
        		label:"新建",
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog","${contextPath}/role/toSaveOrEditPage/create");
				}
			},{separtor:true},{
        		label:"修改",
				icons:{left:'${buttonEditIcons}'},
				onClick:function(){
					var rowSels = $('#'+tabCode+'_role_grid').omGrid('getSelections',true);
					if (rowSels && rowSels.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return ;
					}
				    var rd_id=rowSels[0].roleid;
					main_ChangeDivContent("div_for_dialog","${contextPath}/role/toSaveOrEditPage/edit?roleid="+rd_id);
				}
			},{separtor:true},{
        		label:"删除",
				icons:{left:'${buttonRemoveIcons}'},
				onClick:function(){
					var rowSels = $('#'+tabCode+'_role_grid').omGrid('getSelections',true);
					if (rowSels && rowSels.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return ;
					}
				    var rd_id=rowSels[0].roleid;
				    //删除角色
				    realDelete( tabCode+'_role_grid',"${contextPath}/role/delete/"+rd_id);
				}
			},{separtor:true},{
        		label:"刷新",
				icons:{left:'${buttonRefreshIcons}'},
				onClick:function(){
					$('#'+tabCode+'_role_grid').omGrid('reload');
				}
			},{separtor:true}]
	});
	var role_grid_height = CENTER_HEIGHT - BUTTON_BAR_HEIGHT - 50 ;
	$('#'+tabCode+'_role_grid').omGrid({
		method:'POST',
		loadingMsg:'数据查询中...',
		showIndex:true,
		height:role_grid_height,
		limit:0,  //隐藏分页条
		singleSelect:true,
		dataSource:'${contextPath}/role/findAllRole',
		colModel:[
			 { header:'',width:20, name:'roleid', align:'center', 
			   renderer:function(colValue, rowData, rowIndex) {
					return '<input type="checkbox" class="roleaccess_role_checkbox" id="roleaccess_role_checkbox_'+colValue+'" value="'+colValue+'"/>';
                 	}
			 },
			 { header:'角色名称', name:'name',width:130,align:'center' },
			 { header:'角色类型', name:'role_type',width:130,align:'center'},
			 { header:'更新时间', name:'udate',width:160,align:'center',renderer:function(value,rowData,rowIdex){return formatDate(value,"y-m-d h:i:s");}}
		],
		onRowClick:function(rowIndex,rowData,event){
			//保存角色的选中状态
			$('.roleaccess_role_checkbox').attr('checked',false);
			$('#roleaccess_role_checkbox_'+rowData.roleid).attr('checked','checked');
			role_ajaxMenu( rowData.roleid ,rowData.name);
		}
	});
//菜单
	var menu_tree_height = CENTER_HEIGHT - BUTTON_BAR_HEIGHT - 77 ;
	$('#'+tabCode+'_menu_showtree').css("height",menu_tree_height);
	$('#'+tabCode+'_menu').omPanel({
		title:'菜单'
	});
	$('#'+tabCode+'_menu_buttonbar').omButtonbar({
		btns:[{separtor:true},{
        		label:"保存",
				icons:{left:'${windowSubmitIcons}'},
				onClick:function(){
					SuperMan_show_bg();
					var rowSels = $('#'+tabCode+'_role_grid').omGrid('getSelections',true);
					if (rowSels && rowSels.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return ;
					}
					var mlist = $('#'+tabCode+'_menutree').omTree('getChecked',true);
					var menus = [];
					for( var i=0;i<mlist.length;i++){
						menus.push(mlist[i].id);
						if (mlist[i].pid!=0 && menus.indexOf(mlist[i].pid) == -1) 
							menus.push(mlist[i].pid);
					}
					var str_menus = "";
					for( var i=0;i<menus.length;i++){
						str_menus+=menus[i]+",";
					}
					if( str_menus.length >0 ) 
						str_menus = str_menus.substring(0,str_menus.length-1);
					$.ajax({
						type:"POST",
						url: "${contextPath}/role/updateRoleMenu/"+rowSels[0].roleid ,
						dataType : "json",
						data:{
							menus : str_menus
						},
						type:"post",
						success: function(data){
							main_messageTip_updateSuccess_show();
							SuperMan_hide_bg();
						},
						error:function(date){
							SuperMan_hide_bg();
						}
					});
				}
			},{separtor:true}]
	});
	$.ajax({
		url: "${contextPath}/menu/findAllMenu" ,
		type:"POST",
		success: function(data){
			if( data.result ==1 ){
				var dTree = [] ;
				for(var i=0;i<data.rows.length;i++){
					dTree.push({
						id:data.rows[i].menuid,
						text:data.rows[i].name,
						pid:data.rows[i].pmenuid
					});
				}
				$('#'+tabCode+'_menutree').omTree({
					dataSource :dTree ,
					simpleDataModel: true,
					cascadeCheck:true,
					showCheckbox:true
		        });
				$('#'+tabCode+'_menutree').omTree('expandAll');
			}
		}
	});
});
//点击角色栏
function role_ajaxMenu( roleId ,roleName){
	//AJAX取出关联菜单
	$.ajax({
		type:'POST',
		url:'${contextPath}/role/findRoleMenu/'+roleId ,
		dataType:'json',
		success:function(msg){
			if( msg.result == 1 ){
				$('#'+tabCode+'_menutree').omTree('checkAll',false);
				var list_menu = msg.list ;
				for( var i=0;i<list_menu.length;i++ ){
					var tar = $('#'+tabCode+'_menutree').omTree('findNode','id',list_menu[i]);
					$('#'+tabCode+'_menutree').omTree('check',tar);
				}
				$('#'+tabCode+'_menu_rolename').html( roleName );
			}
		}
	});
}
</script>