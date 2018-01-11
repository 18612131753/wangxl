<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>

<!-- 按钮栏  -->
<div id="${tabCode}_buttonbar"></div>
<!-- 搜索栏  -->
<div id="${tabCode}_searchbar" class="grid_search_bg">
	&nbsp;&nbsp;&nbsp;&nbsp;类型：<input type="text" id="${tabCode}_search_type" />
	&nbsp;&nbsp;&nbsp;&nbsp;难易级别：<input type="text" id="${tabCode}_search_level" />
	&nbsp;&nbsp;&nbsp;&nbsp;题目：<input type="text" id="${tabCode}_search_title" />
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
          		label:"新建单选题",
				id:tabCode+"_buttonbar_save" ,
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",'${contextPath}/question/toSaveOrEdit/create?type=1');
				}
			},{separtor:true},{
          		label:"新建多选题",
				id:tabCode+"_buttonbar_save" ,
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",'${contextPath}/question/toSaveOrEdit/create?type=2');
				}
			},{separtor:true},{
          		label:"新建问答题",
				id:tabCode+"_buttonbar_save" ,
				icons:{left:'${buttonAddIcons}'},
				onClick:function(){
					main_ChangeDivContent("div_for_dialog",'${contextPath}/question/toSaveOrEdit/create?type=3');
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
	$('#'+tabCode+'_search_type').omCombo({
        dataSource: [ {text : '全部', value : ''}, 
					  {text : '单选题', value : '1'}, 
					  {text : '多选题', value : '2'}, 
					  {text : '问答题', value : '3'} ],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'全部',
        editable:false,
        lazyLoad:true,
        listMaxHeight:90
    });
    $('#'+tabCode+'_search_level').omCombo({
        dataSource:[{text:'全部',value:''},{text:'容易',value:'1'},{text:'中等',value:'2'},{text:'困难',value:'3'}],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'全部',
        editable:false,
        lazyLoad:false,
        listMaxHeight:90
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
		 dataSource:'${contextPath}/question/queryforlist',
		 colModel:[
		 	 { header:'',width:20,name:'qid',align:'center',
			    renderer:function(colValue,rowData,rowIndex) {
					return '<input type="checkbox" class="'+tabCode+'_checkbox" id="'+tabCode+'_checkbox_'+colValue+'" value="'+colValue+'"/>';
                }
			 },
			 { header:'类型',name:'type',width:130,align:'center',renderer:function(value,rowData,rowIdex){
			 	var v = ''; 
			 	switch(value){
					case 1: v = '单选题';break;
					case 2: v = '多选题';break;
					case 3: v = '问答题';break;
				}
			 	return v;}},
			 { header:'难易级别',name:'level',width:180,align:'center',renderer:function(value,rowData,rowIdex){
			 	var v = ''; 
			 	switch(value){
					case 1: v = '容易';break;
					case 2: v = '中等';break;
					case 3: v = '困难';break;
				}
			 	return v;}},
			 { header:'题目',name:'title',width:200,align:'center' },
			 { header:'选项',width:200,align:'center' ,renderer:function(value,rowData,rowIdex){
			 	return 
			 		rowData.opt_a+" "+rowData.opt_b+" "+rowData.opt_c+" "+rowData.opt_d+" "+ 
			 		rowData.opt_e+" "+rowData.opt_f+" "+rowData.opt_g+" "+rowData.opt_h ;
			 }},
			 { header:'正确答案',name:'answer',width:100,align:'center',renderer:function(value,rowData,rowIdex){
			 	if( rowData.type == 3 ) return rowData.opt_a; //问答题
			 	return value;}},
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