<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<!-- 搜索栏 -->
<div id="${tabCode}_search_panel" style="display:none;">
	<div id="${tabCode}_search_dialog" style="display:none;">
		<form id="${tabCode}_search_form" method="post">
		<table>
			<tr>
				<td class="td_right">机构全称：</td>
				<td class="td_left"><input type="text" id="${tabCode}_search_full_name"/></td>
			</tr>
			<tr>
				<td class="td_right">机构简称：</td>
				<td class="td_left"><input type="text" id="${tabCode}_search_short_name"/></td>
			</tr>
			<tr>
				<td class="td_right">状态：</td>
				<td class="td_left"><input type="text" id="${tabCode}_search_state"/></td>
			</tr>
		</table>
		</form>
	</div>
</div>
<!-- 按钮栏  -->
<div id="${tabCode}_buttonbar"></div>
<!-- 搜索栏  -->
<div id="${tabCode}_searchbar" class="grid_search_bg">
	<div>
		&nbsp;&nbsp;&nbsp;&nbsp;党支部：
		<input type="text" id="${tabCode}_search_jg1"/>
		<input type="text" id="${tabCode}_search_jg2"/>
		<input type="text" id="${tabCode}_search_jg3"/>
		<a id="${tabCode}_search_button">搜索</a>
	</div>
</div>
<div id="department_buttonbar_tree"></div>
<!-- 数据表格 -->
<table id="${tabCode}_grid"></table>

<script type="text/javascript">
var tabCode = "${tabCode}";
var PAGE_CONFIG={
	SEARCH_DIALOG:tabCode+"_search_dialog",
	SEARCH_FORM:tabCode+"_search_form",
	GRID:tabCode+"_grid",
	GRID_LIMIT:50,
	SAVE_OR_EDIT_URL:"${contextPath}/department/toSaveOrEditPage",
	DELETE_URL:"${contextPath}/department/delete"
};

//打开搜索窗口
function openSearchDialog(){
	$( "#"+PAGE_CONFIG.SEARCH_DIALOG).omDialog({
	 	title:'搜索<label id="'+PAGE_CONFIG.SEARCH_DIALOG+'_error" class="error" generated="true" style="display:none;"></label>',
		autoOpen: true,
		height: 'auto',
		width:500,
		modal: true,
		resizable:false,
		onClose :function(){
		},
	 	buttons : [
	 		{ text : "搜索",id:PAGE_CONFIG.SEARCH_DIALOG+'_submit',click :function(){
	 			$('#'+PAGE_CONFIG.GRID).omGrid({
					extraData : {
						full_name: $('#'+tabCode+'_search_full_name').val(),
						short_name:$('#'+tabCode+'_search_short_name').val(),
						state:$('#'+tabCode+'_search_state').val(),
					}
				});
	 			$( "#"+PAGE_CONFIG.SEARCH_DIALOG).omDialog('close');
	 		}}, 
			{ text:"重置",id:PAGE_CONFIG.SEARCH_DIALOG+'_reset',click:function(){$("#"+PAGE_CONFIG.SEARCH_FORM)[0].reset();}},
			{ text:"取消",id:PAGE_CONFIG.SEARCH_DIALOG+'_close',click:function(){$("#"+PAGE_CONFIG.SEARCH_DIALOG).omDialog('close');}}
		]
	 });
	 $("#"+PAGE_CONFIG.SEARCH_DIALOG+"_submit").omButton({icons:{left:'${buttonSearchIcons}'}});
     $("#"+PAGE_CONFIG.SEARCH_DIALOG+"_reset").omButton({icons:{left:'${buttonResetIcons}'}});
	 $("#"+PAGE_CONFIG.SEARCH_DIALOG+"_close").omButton({icons:{left:'${windowCloseIcons}'}});
}

//编辑
function dep_update_btn(){
	var rowSels = $('#'+PAGE_CONFIG.GRID).omGrid('getSelections',true);
	if (rowSels && rowSels.length == 0) {
		main_messageBox_pleaseSelectOne_alert();
		return false;
	}
    var rd_id=rowSels[0].id;
    main_ChangeDivContent("div_for_dialog",PAGE_CONFIG.SAVE_OR_EDIT_URL+"?id="+rd_id+"&new_or_edit=edit");
}

$(document).ready(function() {
	bindDepartmentComboBox(tabCode+'_search_jg1','000',tabCode+'_search_jg2','0',tabCode+'_search_jg3','0');
	
	// 计算表格高度
	var grid_height = CENTER_TAB_HEIGHT - BUTTON_BAR_HEIGHT - 28 ;
	//按钮区
	$('#'+tabCode+'_buttonbar').omButtonbar({
          	btns : [{
	          	label:"高级搜索",
				id:tabCode+"_buttonbar_search",
				icons : {left : '${buttonSearchIcons}'},
				onClick:openSearchDialog
			},{separtor:true},{
          		label:"新建",
				id:tabCode+"_buttonbar_save" ,
				icons : {left : '${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",PAGE_CONFIG.SAVE_OR_EDIT_URL+"?id=0&new_or_edit=create");
				}
			},{separtor:true},{
          		label:"修改",
				id:tabCode+"_buttonbar_update" ,
				icons : {left : '${buttonEditIcons}'},
				onClick:dep_update_btn
			},{separtor:true},{
				label:"删除",
				id:tabCode+"_buttonbar_delete" ,
				icons : {left : '${buttonRemoveIcons}'},
				onClick:function(){
					var selections=$('#'+PAGE_CONFIG.GRID).omGrid('getSelections',true);
					if (selections.length == 0) {
						main_messageBox_pleaseSelectOne_alert();
						return false;
					}
				    var rd_id=selections[0].id;
				    realDelete(PAGE_CONFIG.GRID,PAGE_CONFIG.DELETE_URL+"/"+rd_id);
				}
			},{separtor:true},{
           		label:"显示机构树",
				id:"department_buttonbar_showTree" ,
 				icons : {left : '${buttonTreeIcons}'},
				onClick:function(){
 					main_ChangeDivContent("department_buttonbar_tree","${contextPath}/department/toShowTree");
				}
 			},{separtor:true}]
	});
	//搜索按钮
	$("#${tabCode}_search_button").omButton({
		icons:{left:'${buttonSearchIcons}'},
		onClick:function(){
			$('#'+PAGE_CONFIG.GRID).omGrid({
				extraData : {
					jg1:$('#'+tabCode+'_search_jg1').val(),
					jg2:$('#'+tabCode+'_search_jg2').val(),
					jg3:$('#'+tabCode+'_search_jg3').val()
				}
			});
		}
	})
	//GRID数据区域
	$('#'+PAGE_CONFIG.GRID).omGrid({
		 method : 'POST',
		 limit : PAGE_CONFIG.GRID_LIMIT,
		 height : grid_height,
		 autoFit : false,
		 showIndex : true,
		 singleSelect : true,
		 loadingMsg : '数据查询中...',
		 dataSource:'${contextPath}/department/queryForList',
		 colModel : [
		 	 { header : '',width : 20,name:'null', align : 'center', 
			    renderer : function(colValue, rowData, rowIndex) {
					return '<input type="checkbox" class="'+tabCode+'_checkbox" id="'+tabCode+'_checkbox_'+rowData.id+'" value="'+rowData.id+'"/>';
                }
			 },
			 { header : '机构编码',name:'id',width:120},
			 { header : '机构简称', name : 'short_name',width : 180},
			 { header : '机构全称', name : 'full_name' ,width : 180},
			 { header : '上级机构名称',name : 'parent_name',width : 180},
			 { header : '状态',align : 'center', name : 'state',width : 70,renderer:function(value,rowData,rowIdex){return value == 1 ? "启用" : "<b style='color:red;'>停用</b>";}},
			 { header : '创建时间',name:'create_date',align:'center',width:150,renderer:function(value,rowData,rowIdex){return formatDate(value,"y-m-d h:i:s");}}
		],
		onRowClick:function(rowIndex,rowData,event){
			$('.'+tabCode+'_checkbox').attr('checked',false);
			$('#'+tabCode+'_checkbox_'+rowData.id).attr('checked','checked');
		},
		onRowDblClick:function(rowIndex,rowData,event){
			dep_update_btn();
	    }
	});
	//搜索状态
	$('#'+tabCode+"_search_state").omCombo({
		width:'120px',
       	dataSource:[{id:"",name:"全部"},{id:1,name:"启用"},{id:0,name:"停用"}],
        optionField:function(data,index){
           	return data.name;
       	},
       	valueField : 'id',
       	inputField : 'name',
       	emptyText:'请选择!',
       	editable:false,
        disabled:false,
       	lazyLoad:true,
       	listMaxHeight:85,
       	value:""
	});
});
</script>