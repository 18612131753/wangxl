<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>

<!-- 按钮栏  -->
<div id="${tabCode}_buttonbar"></div>
<!-- 搜索栏  -->
<div id="${tabCode}_searchbar" class="grid_search_bg">
	&nbsp;&nbsp;&nbsp;&nbsp;用户角色：<input type="text" id="${tabCode}_search_role" />
	&nbsp;&nbsp;&nbsp;&nbsp;登录名：<input type="text" id="${tabCode}_search_loginname" />
	&nbsp;&nbsp;&nbsp;&nbsp;状态：<input type="text" id="${tabCode}_search_state" />
	<a id="${tabCode}_search_button">搜索</a>
</div>
<!-- 数据表格 -->
<table id="${tabCode}_grid"></table>

<script type="text/javascript">
var tabCode = "${tabCode}";
$(document).ready(function() {
	// 计算表格高度
	var grid_height = CENTER_TAB_HEIGHT - BUTTON_BAR_HEIGHT - 28 ;
	
	//按钮区
	$('#'+tabCode+'_buttonbar').omButtonbar({
          	btns:[{separtor:true},{
          		label:"新建",
				id:tabCode+"_buttonbar_save" ,
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",'${contextPath}/user/queryforlist');
				}
			},{separtor:true},{
          		label:"修改",
				id:tabCode+"_buttonbar_update" ,
				icons:{left:'${buttonEditIcons}'},
				onClick:update_btn
			},{separtor:true},{
          		label:"删除",
				id:tabCode+"_buttonbar_delete" ,
				icons:{left:'${buttonRemoveIcons}'},
				onClick:delete_btn
			},{separtor:true}]
	});
	// 搜索区域
	$('#'+tabCode+'_search_role').omCombo({
        dataSource:'${contextPath}/role/findRoleCombo?type=0',
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'全部',
        editable:false,
        lazyLoad:true,
        listMaxHeight:80
    });
    $('#'+tabCode+'_search_state').omCombo({
        dataSource:[{text:'全部',value:''},{text:'正常',value:'1'},{text:'停用',value:'0'}],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'全部',
        editable:false,
        lazyLoad:false,
        listMaxHeight:80
    });
    $('#'+tabCode+'_search_button').omButton({
		icons:{left:'${buttonSearchIcons}'},
		onClick:function(){
			$('#'+tabCode+'_grid').omGrid({
				extraData : {
					loginname:$('#'+tabCode+'_search_loginname').val(),
					roleid:$('#'+tabCode+'_search_role').omCombo("value"),
					state:$('#'+tabCode+'_search_state').omCombo("value")
				}
			});
		}
	})
	//GRID数据区域
	$('#'+tabCode+'_grid').omGrid({
		 method:'POST',
		 limit:ALL_LIMIT,
		 height:grid_height,
		 autoFit:false,
		 showIndex:false,
		 singleSelect:true,
		 loadingMsg:'数据查询中...',
		 editMode:'insert',
		 dataSource:'${contextPath}/user/queryforlist',
		 colModel:[
		 	 { header:'',width:20,name:'userid',align:'center',
			    renderer:function(colValue,rowData,rowIndex) {
					return '<input type="checkbox" class="'+tabCode+'_checkbox" id="'+tabCode+'_checkbox_'+colValue+'" value="'+colValue+'"/>';
                }
			 },
			 { header:'登录名',name:'loginname',width:120,align:'center'},
			 { header:'用户角色',name:'rolename',width:100,align:'center' },
			 { header:'是否管理员',name:'isadmin',width:100,align:'center' ,
			 	renderer:function(colValue,rowData,rowIndex) {
					return (colValue == 1) ? "是" : "否";
                }},
			 { header:'账号状态',name:'state',width:100,align:'center' ,
			 	renderer:function(colValue,rowData,rowIndex) {
					return (colValue == 1) ? "正常" : "停用";
                }},
			 { header:'创建时间',name:'cdate',width:150,align:'center' },
			 { header:'更新时间',name:'udate',width:150,align:'center' }
		],
		onRowClick:function(rowIndex,rowData,event){
			//保存用户的选中状态
			$('.'+tabCode+'_checkbox').attr('checked',false);
			$('#'+tabCode+'_checkbox_'+rowData.userid).attr('checked','checked');
		},
		onRowDblClick:function(rowIndex,rowData,event){
			update_btn();
	    }
	});
});
//搜索
function doSearch(){
	$('#'+tabCode+'_grid').omGrid({
		extraData:{
			crm_nm:$.trim($('#'+tabCode+'_search_crm_nm').val()),
			create_start_time:$.trim($('#'+tabCode+'_search_create_start_time').val()),
			create_end_time:$('#'+tabCode+'_search_create_end_time').val(),
			update_start_time:$('#'+tabCode+'_search_update_start_time').val(),
			update_end_time:$('#'+tabCode+'_search_update_end_time').val()
		}
	});
}

//编辑
function update_btn(){
	var rowSels = $('#'+tabCode+'_grid').omGrid('getSelections',true);
	if (rowSels && rowSels.length == 0) {
		main_messageBox_pleaseSelectOne_alert();
		return false;
	}
    var rd_id=rowSels[0].id;
    main_ChangeDivContent("div_for_dialog",'${contextPath}/user/queryforlist' );
}
//删除
function delete_btn(){
	var selections=$('#'+tabCode+'_grid').omGrid('getSelections',true);
	if (selections.length == 0) {
		main_messageBox_pleaseSelectOne_alert();
		return false;
	}
    var rd_id=selections[0].id;
    realDelete(PAGE_CONFIG.GRID,PAGE_CONFIG.DELETE_URL+"/"+rd_id);
}
</script>