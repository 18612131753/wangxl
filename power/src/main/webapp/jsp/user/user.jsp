	<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!-- 搜索栏 -->
<div id="userrole_search_panel" style="display:none;">
	<div id="userrole_search_dialog" style="display:none;"><!-- class="tab_panel_search_content" -->
		<form id="userrole_search_form"  method="post">
		<table>
			<tr>
				<td class="td_right">登录名：</td>
				<td class="td_left"><input type="text" id="userrole_search_login_name" /></td>
			</tr>
			<tr>
				<td class="td_right">姓名：</td>
				<td class="td_left"><input type="text" id="userrole_search_employee_name" /></td>
			</tr>
			<tr>
				<td class="td_right">角色：</td>
				<td class="td_left"><input type="text" id="userrole_search_role" /></td>
			</tr>
			<tr>
				<td class="td_right">状态：</td>
				<td class="td_left"><input type="text" id="userrole_search_isUseState" /></td>
			</tr>
			<tr>
				<td class="td_right">员工状态：</td>
				<td class="td_left"><input type="text" id="userrole_search_state" /></td>
			</tr>
			<tr>
				<td class="td_right">创建时间：</td>
				<td class="td_left"><input type="text" id="userrole_search_create_start_time"/></td>
				<td class="td_right">至</td>
				<td class="td_left"><input type="text" id="userrole_search_create_end_time"/></td>
			</tr>
			<tr>
				<td class="td_right">更新时间：</td>
				<td class="td_left"><input type="text" id="userrole_search_update_start_time"/></td>
				<td class="td_right">至</td>
				<td class="td_left"><input type="text" id="userrole_search_update_end_time"/></td>
			</tr>
		</table>
		</form>
	</div>
</div>

<!-- 按钮栏  -->
<div id="userrole_buttonbar"></div>
<!-- 按钮栏  -->
<div id="userrole_searchbar" style="background:#BECBDC;height:30px;line-height:30px;overflow:hidden;">
	<select style="width:120px">
		<option value="0">视图</option>
		<option value="1">今日新建</option>
		<option value="2">7日内新建</option>
	</select>
	账号：<input type="text" id="user_srarch_name" />
	<input type="button" value="搜 索"/>
</div>
<!-- 数据表格 -->
<table id="userrole_grid" ></table>
		
<script type="text/javascript">
	$(document).ready(function() {
		// 计算表格高度
		var grid_height = CENTER_TAB_HEIGHT - BUTTON_BAR_HEIGHT - 34 ;
		
		//状态下拉菜单初始化
		bindStaticDropList("userrole_search_state",[{text:'全部', value:''},{text : '在职', value : 2},{text : '离职', value : 3}],"",function(){
		});
		//是否启用
		bindStaticDropList("userrole_search_isUseState",[{text:'全部', value:''},{text : '启用', value : 10},{text : '停用', value : 11}],"",function(){
		});

	    // 日期
	    $('#userrole_search_create_start_time').omCalendar({dateFormat : "yy-mm-dd H:i:s",showTime : true});
	    $('#userrole_search_create_end_time').omCalendar({dateFormat : "yy-mm-dd H:i:s",showTime : true});
	    $('#userrole_search_update_start_time').omCalendar({dateFormat : "yy-mm-dd H:i:s",showTime : true});
	    $('#userrole_search_update_end_time').omCalendar({dateFormat : "yy-mm-dd H:i:s",showTime : true});

		//按钮区
		$('#userrole_buttonbar').omButtonbar({
           	btns : [{separtor:true},{
           		label:"高级搜索",/* 搜索 */
				id:"userrole_buttonbar_search",
 				icons : {left : '${buttonSearchIcons}'},
				onClick:function(){
 					 $( "#userrole_search_dialog").omDialog({
					 	title:'搜索<label id="userrole_search_dialog_error" class="error" generated="true" style="display:none;"></label>',
						autoOpen: true,
						height: 'auto',
						width:500,
						modal: true,
						resizable:false,
						onClose :function(){
						},
					 	buttons : [
							{ text : "搜索",id:'userrole_search_submit',click : function () {
									//搜索
									$('#userrole_grid').omGrid({
										extraData : {
											login_name : $.trim($('#userrole_search_login_name').val()),
											employee_name : $.trim($('#userrole_search_employee_name').val()) ,
											role_id : $.trim($('#userrole_search_role').val()),
											employee_state :  $.trim($('#userrole_search_state').val()) ,
											isUseState :  $.trim($('#userrole_search_isUseState').val()) ,
											department_id :  $.trim($('#userrole_search_department_id').val()),
											create_start_time:$.trim($('#userrole_search_create_start_time').val()),
											create_end_time:$('#userrole_search_create_end_time').val(),
											update_start_time:$('#userrole_search_update_start_time').val(),
											update_end_time:$('#userrole_search_update_end_time').val()
										}
									});
									$( "#userrole_search_dialog").omDialog('close');
								} 
							}, 
							{ text : "重置",id:'userrole_search_rest',click: function () {$("#userrole_search_form")[0].reset(); } },
							{ text : "取消",id:'userrole_search_colse',click : function () {$( "#userrole_search_dialog").omDialog('close'); } }
						]
					 });
					 $("#userrole_search_submit").omButton({
						icons : { left : '${buttonSearchIcons}'}
			       	 });
			       	 $("#userrole_search_rest").omButton({
						icons : { left : '${buttonResetIcons}'}
			       	 });
					 $("#userrole_search_colse").omButton({
						icons : { left : '${windowCloseIcons}'}
			       	 });
				}
 			},{separtor:true},{
           		label:"新建",
				id:"userrole_buttonbar_add" ,
				//disabled : Boolean("${button.button_1==false?'':1}"),
 				icons : {left : '${buttonAddIcons}'},
				onClick:function(){
            		main_ChangeDivContent("div_for_dialog","${contextPath}/userRole/toAddUserRole");
				}
 			},{separtor:true},{
           		label:"修改",
				id:"userrole_buttonbar_update" ,
				//disabled : Boolean("${button.button_2==false?'':1}"),
 				icons : {left : '${buttonEditIcons}'},
				onClick:function(){
 					var selections=$('#userrole_grid').omGrid('getSelections',true);
					if (selections.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return false;
					}
                	var toDeleteRecordID=selections[0].id;
            		main_ChangeDivContent("div_for_dialog","${contextPath}/userRole/toUpdateUserRole/"+toDeleteRecordID);
				}
 			},{separtor:true},{
           		label:"删除",/* 删除 */
				id:"userrole_buttonbar_delete" ,
				//disabled : Boolean("${button.button_3==false?'':1}"),
 				icons : {left : '${buttonRemoveIcons}'},
				onClick:function(){
					var selections=$('#userrole_grid').omGrid('getSelections',true);
					if (selections.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return false;
					}
                	var toDeleteRecordID=selections[0].id;
                	realDelete("userrole_grid","${contextPath}/userRole/deleteUserRole?id="+toDeleteRecordID);
				}
 			},{separtor:true},{
           		label:"导出",
				id:"userrole_buttonbar_excel" ,
 				icons : {left : '${buttonBatchImportIcons}'},
				onClick:function(){
 					var json = JSON.stringify($('#userrole_grid').omGrid('getData').rows);
 					IframePost.doPost({ 
 						url: "${contextPath}/user/excelUser", 
 						target: $(window.frames["crm_excel_iframe"].document) , 
 						params: {
 							json:json
 						} 
 					});
				}
 			},{separtor:true},{
         			label:"帮助",
         			disabled:false,
					id:"userrole_buttonbar_help" ,
					icons : {left : '${buttonHelpIcons}'},
					onClick:function(){
						main_openWindow("${helpPath}/r_13_6.html");
					}
				}]
		});
		
		 $("#department_search_submit").omButton({
			icons : { left : '${buttonSearchIcons}'}
       	 });
       	 $("#department_search_rest").omButton({
			icons : { left : '${buttonResetIcons}'}
       	 });
		 $("#department_search_colse").omButton({
			icons : { left : '${windowCloseIcons}'}
       	 });

		//grid数据区域
		$('#userrole_grid').omGrid({
			 method : 'POST',
			 limit : 20 ,
			 height : grid_height,
			 autoFit : false,
			 showIndex : false,
			 singleSelect : true,
			 loadingMsg : '数据查询中...',
			 editMode:"insert",
			 dataSource:'${contextPath}/user/findUser',
			 colModel : [
			 	 { header : '',width : 20, name : 'id', align : 'center', 
				   renderer : function(colValue, rowData, rowIndex) {
						return '<input type="checkbox" class="user_role_checkbox" id="user_role_checkbox_'+colValue+'" value="'+colValue+'"/>';
                  	}
				 },
				 { header : '登录名', name : 'login_name',width : 150,align : 'center'},
				 { header : '密码', width : 300, name : 'password', align : 'center' },
				 { header : '创建时间',width : 150, name : 'create_date', align : 'center',renderer:function(value,rowData,rowIndex){ return formatDate(value);} }
			],
			onRowClick:function(rowIndex,rowData,event){
				//保存用户的选中状态
				$('.user_role_checkbox').attr('checked',false);
				$('#user_role_checkbox_'+rowData.id).attr('checked','checked');
			}
		});
		
		//表格单行双击事件
		$('#userrole_grid').omGrid({
			onRowDblClick:function(rowIndex,rowData,event){
				//是否有修改权限
				if( '${button.button_2}'==false ){
					//将选择的记录的id传递到后台去并执行部署操作
					var selections=$('#userrole_grid').omGrid('getSelections',true);
                	var toDeleteRecordID=selections[0].id;
                	main_ChangeDivContent("div_for_dialog","${contextPath}/userRole/toUpdateUserRole/"+toDeleteRecordID);
				}
		   }
		});
	});
</script>