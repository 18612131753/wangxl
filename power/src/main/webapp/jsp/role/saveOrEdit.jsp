<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>
<div id="${tabCode}_saveOrEdit_dialog">
	<form id="${tabCode}_saveOrEdit_form" method="post">
		<table>
			<tr>
				<td class="td_right"><span class="label"><span style="color: red;">*</span>角色名称：</span></td>
				<td class="td_left">
					<input type="text" id="${tabCode}_saveOrEdit_form_name" name="name" value="${role.name}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right"><span class="label"><span style="color: red;">*</span>角色类型：</span></td>
				<td class="td_left">
					<input type="text" id="${tabCode}_saveOrEdit_form_type" name="role_type"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
		</table>
	</form>
</div>
<div id="${tabCode}_choose_panel_dialog"></div>
<script type="text/javascript">
var new_or_edit = "${new_or_edit}";
var tabCode = "${tabCode}";
var FORM_PAGE_CONFIG={
	FORM_DIALOG:tabCode+"_saveOrEdit_dialog",
	FORM:tabCode+"_saveOrEdit_form"
};
//提交操作
function role_save(){
	if( !$("#"+FORM_PAGE_CONFIG.FORM).valid())
		return false;
	$("#"+tabCode+"_saveOrEdit_form_submit").omButton('disable');
	$("#"+FORM_PAGE_CONFIG.FORM).omAjaxSubmit({
		method : 'POST',
		url : '${contextPath}/${action}',
		dataType : 'json',
		success : function( responseText, jqForm, options) {
			if (responseText.result == 1) {
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog('close');
				$('#'+tabCode+'_role_grid').omGrid('reload');
				main_messageTip_show('操作成功');
			} else {
				main_messageTip_show('操作失败，请重新再试');
				$("#"+tabCode+"_saveOrEdit_form_submit").omButton('enable');
			}
		},
	  	error:function(){
			$("#"+tabCode+"_saveOrEdit_form_submit").omButton('enable');
	  		main_messageTip_systemError_show();
	  	}
	});
}
$(document).ready(function() {
	$("#"+tabCode+"_saveOrEdit_form_type").omCombo({
		emptyText:'请选择角色类型',
		editable:false,
		width:'160px',
		value:'${role.role_type}',
		dataSource:[
                {text:'个人',value:'0'},
                {text:'党支部',value:'1'},
                {text:'分党委',value:'2'},
                {text:'党总支',value:'3'},
                {text:'管理员',value:'4'}
        ]
	});
	$("#"+FORM_PAGE_CONFIG.FORM).validate({
		rules : {
			name : {
				required : true ,
				maxlength : 30 ,
				minlength : 3
			} ,
			type: {
				required : true
			}
		},
		messages : {
			name : {
				required : "请输入名称" ,
				maxlength : "不得超过30个字符" ,
				minlength : "不得少于3个字符"
			},
			type:{
				required : "必选"
			}
		},
		errorPlacement : function(error, element) { 
               if(error.html()){
                 $(element).parents().map(function(){
                     if(this.tagName.toLowerCase()=='td'){
                        var attentionElement = $(this).next().children().eq(0);
                        attentionElement.css('display','block');
  	                    attentionElement.next().html(error);
                     	attentionElement.next().css('display','block');
                     }
                 });
               }
        },
        showErrors: function(errorMap, errorList) {
               if(errorList && errorList.length > 0){
        	 	     $('.errorImg').hide();
                     $.each(errorList,function(index,obj){
	                       var msg = this.message;
	                       $(obj.element).parents().map(function(){
	                       if(this.tagName.toLowerCase()=='td'){
	                       		var attentionElement = $(this).next().children().eq(0);
	                            attentionElement.show();
	    	                    attentionElement.next().html(msg);
	                       }
	                    });
	                });
               }else{
                   $(this.currentElements).parents().map(function(){
                    if(this.tagName.toLowerCase()=='td'){
                        var attentionElement = $(this).next().children().eq(0);
	                     attentionElement.hide();
	                     attentionElement.next().hide();
                     }
                	});
               }
               this.defaultShowErrors();
           }
	});
	
 	$('.errorImg').bind('mouseover',function(){
		$(this).next().css('display','block');
	}).bind('mouseout',function(){
		$(this).next().css('display','none');
    });

	$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog({
		title : ("create"==new_or_edit)?"新建":"修改" ,
		autoOpen : true,
		height : 'auto',
		width : 400,
		modal : true,
		resizable : false,
		onClose : function() {
			$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).remove();//销毁DIV
		},
		buttons : [{
			text : "保存",
			id : tabCode+'_saveOrEdit_form_submit',
			click : role_save
		}, {
			text : "取消",
			id : tabCode+'_saveOrEdit_form_colse',
			click : function() {
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog('close');}
		} ]
	});

	$("#"+tabCode+"_saveOrEdit_form_submit").omButton({ icons : { left : '${windowSubmitIcons}' } });
	$("#"+tabCode+"_saveOrEdit_form_colse").omButton({ icons : { left : '${windowCloseIcons}' } });

	//选择父机构
	var jg1_list = [{id:"000" , name:"请选择" , parent_id:"0"}];
   	for(var i=0;i<DepartmentTreeList.length;i++){
   		if(DepartmentTreeList[i].id.length == 3 && DepartmentTreeList[i].id !='000' ){
   			jg1_list.push(DepartmentTreeList[i]);
   		}
   	}
   	var jg1_val = "";
   	if( "${jg1}".length>0 ){
   		//修改回填
   		jg1_list.shift(); //删除第一个"请选择"
   		jg1_val = "${jg1}" ;
   	} else {
   		//新建
   		jg1_val = jg1_list[0].id ;
   		//如果只有一个选项，就选择这个
	   	if( jg1_list.length == 2 ){
	   		jg1_list.shift(); //删除第一个
	   		jg1_val = jg1_list[0].id ;
	   	}
   	}

	$('#'+tabCode+"_saveOrEdit_form_jg1").omCombo({
		width:'120px',
       	dataSource:jg1_list,
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
       	value:jg1_val,
       	onValueChange : function(target, newValue, oldValue, event) {
       		$('#'+tabCode+"_saveOrEdit_form_jg2").omCombo('setData',[]);
       		var jg2_list = [{id:'000',name:"无",parent_id:newValue}];
       		for(var i=0;i<DepartmentTreeList.length;i++){
       			if( DepartmentTreeList[i].parent_id == newValue ){
       				jg2_list.push(DepartmentTreeList[i]);
       			}
       		}
       		var jg2_val = jg2_list[0].id ;
       		if( "${jg2}".length>0 ){
   				jg2_val = "${jg2}" ;
       		} else if( jg2_list.length == 2 ){
       			//如果只有一个选项，就选择这个
       			jg2_list.shift();
       			jg2_val = jg2_list[0].id ;
       		} else if( jg2_list.length > 2 ){
		   		jg2_val = "000" ;
		   	}
       		$('#'+tabCode+"_saveOrEdit_form_jg2").omCombo({
				dataSource:jg2_list,
				optionField:function(data,index){
					return data.name;
				},
				valueField : 'id',
				inputField : 'name',
				emptyText:'请选择!',
				editable:false,
				disabled:false,
				lazyLoad:true,
				value:jg2_val,
				listMaxHeight:85
			});
       	}
	});
	$('#'+tabCode+"_saveOrEdit_form_state").omCombo({
		width:'120px',
       	dataSource:[{id:1,name:"启用"},{id:0,name:"停用"}],
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
       	value:"${dep.state==null?1:dep.state}"
	});
});	
</script>