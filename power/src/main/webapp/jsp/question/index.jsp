<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>

<!-- 按钮栏  -->
<div id="${tabCode}_buttonbar"></div>
<!-- 搜索栏  -->
<div id="${tabCode}_searchbar" class="grid_search_bg">
	&nbsp;&nbsp;&nbsp;&nbsp;父节点：<input type="text" id="${tabCode}_search_pmenu" />
	&nbsp;&nbsp;&nbsp;&nbsp;名称：<input type="text" id="${tabCode}_search_name" />
	<a id="${tabCode}_search_button">搜索</a>
</div>
<!-- 数据表格 -->
<table id="${tabCode}_grid"></table>

<script type="text/javascript">

$(document).ready(function() {
	var tabCode = "${tabCode}";
	// 计算表格高度
	var grid_height = CENTER_TAB_HEIGHT - BUTTON_BAR_HEIGHT - 30 ;
	
	//按钮区
	$('#'+tabCode+'_buttonbar').omButtonbar({
          	btns:[{
          		label:"新建",
				id:tabCode+"_buttonbar_save" ,
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",'${contextPath}/menu/toSaveOrEdit/create');
				}
			},{separtor:true},{
          		label:"修改",
				id:tabCode+"_buttonbar_update" ,
				icons:{left:'${buttonEditIcons}'},
				onClick: update_btn
			},{separtor:true},{
          		label:"删除",
				id:tabCode+"_buttonbar_delete" ,
				icons:{left:'${buttonRemoveIcons}'},
				onClick: delete_btn
			},{separtor:true}]
	});
	// 搜索区域
	$('#'+tabCode+'_search_pmenu').omCombo({
        dataSource:'${contextPath}/menu/findMenu1?type=0',
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
					name:$('#'+tabCode+'_search_name').val(),
					pmenuid:$('#'+tabCode+'_search_pmenu').omCombo("value")
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
		 dataSource:'${contextPath}/menu/queryforlist',
		 colModel:[
		 	 { header:'',width:20,name:'menuid',align:'center',
			    renderer:function(colValue,rowData,rowIndex) {
					return '<input type="checkbox" class="'+tabCode+'_checkbox" id="'+tabCode+'_checkbox_'+colValue+'" value="'+colValue+'"/>';
                }
			 },
			 { header:'角色名称',name:'name',width:130,align:'center'},
			 { header:'URL',name:'url',width:180,align:'center' },
			 { header:'排序',name:'ordernum',width:70,align:'center' },
			 { header:'父节点',name:'pmenuname',width:100,align:'center'},
			 { header:'创建时间',name:'cdate',width:160,align:'center',renderer:function(value,rowData,rowIdex){return formatDate(value,"y-m-d h:i:s");} },
			 { header:'更新时间',name:'udate',width:160,align:'center',renderer:function(value,rowData,rowIdex){return formatDate(value,"y-m-d h:i:s");} }
		],
		onRowClick:function(rowIndex,rowData,event){
			//保存用户的选中状态
			$('.'+tabCode+'_checkbox').attr('checked',false);
			$('#'+tabCode+'_checkbox_'+rowData.menuid).attr('checked','checked');
		},
		onRowDblClick:function(rowIndex,rowData,event){
			update_btn();
	    }
	});
	
	//搜索
/* 	function doSearch(){
		$('#'+tabCode+'_grid').omGrid({
			extraData:{
				crm_nm:$.trim($('#'+tabCode+'_search_crm_nm').val()),
				create_start_time:$.trim($('#'+tabCode+'_search_create_start_time').val()),
				create_end_time:$('#'+tabCode+'_search_create_end_time').val(),
				update_start_time:$('#'+tabCode+'_search_update_start_time').val(),
				update_end_time:$('#'+tabCode+'_search_update_end_time').val()
			}
		});
	} */
	
	//编辑
	function update_btn(){
		var rowSels = $('#'+tabCode+'_grid').omGrid('getSelections',true);
		if (rowSels && rowSels.length == 0) {
			main_messageBox_pleaseSelectOne_alert();
			return false;
		}
	    var rd_id=rowSels[0].menuid;
	    main_ChangeDivContent("div_for_dialog",'${contextPath}/menu/toSaveOrEdit/edit?menuid='+rd_id );
	}
	//删除
	function delete_btn(){
		var selections=$('#'+tabCode+'_grid').omGrid('getSelections',true);
		if (selections.length == 0) {
			main_messageBox_pleaseSelectOne_alert();
			return false;
		}
	    var rd_id=selections[0].menuid;
	    realDelete(tabCode+'_grid','${contextPath}/menu/delete/'+rd_id);
	}
});

</script>