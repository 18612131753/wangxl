<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../commons/taglibs.jsp"%>
<div id="${tabCode}_form_dialog">
	<form id="${tabCode}_form" method="post" >
		<table>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>用户名：</td>
				<td class="td_left">
					<input type="text" id="${tabCode}_form_loginname" name="loginname" value="${euser.loginname}" />
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>密码：</td>
				<td class="td_left">
					<input type="password" id="${tabCode}_form_password" name="password" autocomplete="new-password" value="${euser.password}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>用户角色：</td>
				<td class="td_left"><INPUT id="${tabCode}_form_roleid" type="text" name="roleid" /></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color:red;">*</span>是否管理员：</td>
				<td class="td_left"><input id="${tabCode}_form_isadmin" name="isadmin"/></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>账号状态：</td>
				<td class="td_left">
				<INPUT id="${tabCode}_form_state" type="text" name="state"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
var new_or_edit = "${new_or_edit}";
var tabCode = "${tabCode}";
var FORM_PAGE_CONFIG={
	FORM_DIALOG:tabCode+"_form_dialog",
	FORM:tabCode+"_form"
};

//提交操作
var saving = false;

function btn_save(){
	if(saving || !$("#"+FORM_PAGE_CONFIG.FORM).valid())
		return false;
	saving=true;
	$('#'+tabCode+'_submit').omButton('disable');
	$("#"+FORM_PAGE_CONFIG.FORM).omAjaxSubmit({
		method : 'POST',
		url : '${contextPath}/${action}',
		dataType : 'json',
		success : function( responseText, jqForm, options) {
			if (responseText.result == 1) {
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog('close');
				$('#'+tabCode+'_grid').omGrid('reload');
				if("edit"==new_or_edit){
					main_messageTip_updateSuccess_show();
				}else if("create"==new_or_edit){
					main_messageTip_addSuccess_show();
				}
			} else {
				if("edit"==new_or_edit){
					main_messageTip_updateError_show();
				}else if("create"==new_or_edit){
					main_messageTip_addError_show();
				}
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG+"_error").html(responseText.mess);
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG+"_error").css('display','inline');
				$('#'+tabCode+'_form_submit').omButton('enable');
			}
			saving=false;
		},
	  	error:function(){
	  		main_messageTip_systemError_show();
	  		saving=false;
	  	}
	});
}
    
$(document).ready(function() {
	// 自定义组件
	
	//表单验证
	$("#"+FORM_PAGE_CONFIG.FORM).validate({
		rules : {
			loginname : { required : true, maxlength : 30 },
			isadmin : { required : true },
			state : { required : true },
			roleid : { required : true  }
		},
		messages : {
			loginname : { required : "用户名必填", maxlength : "长度不超过30个字符" },
			isadmin : { required : "必选" },
			state : { required : "必选"},
			roleid : { required : "必选" }
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
 	
	var dtitle = (("create"==new_or_edit)?"新建":"编辑")+"<label id=\""+FORM_PAGE_CONFIG.FORM_DIALOG+"_error\" class=\"error\" generated=\"true\" style=\"display:none;\"></label>";
	
	$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog({
		title : dtitle,
		autoOpen : true,
		height : 'auto',
		width : 450,
		modal : true,
		resizable : false,
		onClose : function() {
			$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).remove();//销毁DIV
		},
		buttons : [{
			text : "保存",
			id : tabCode+'_form_submit',
			click : btn_save
		}, {
			text : "取消",
			id : tabCode+'_form_colse',
			click : function() {
				$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog('close');}
		} ]
	});

	$("#"+tabCode+"_form_submit").omButton({ icons : { left : '${windowSubmitIcons}' } });
	$("#"+tabCode+"_form_colse").omButton({ icons : { left : '${windowCloseIcons}' } });

	//角色
	$('#'+tabCode+'_form_roleid').omCombo({
        dataSource:'${contextPath}/role/findRoleCombo?type=1',
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'必选',
        editable:false,
        lazyLoad:false,
        value:'${euser.roleid}',
        listMaxHeight:80
    });
    //是否管理员
    $('#'+tabCode+'_form_isadmin').omCombo({
       dataSource:[{text:'是',value:'1'},{text:'否',value:'0'}],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'必选',
        editable:false,
        lazyLoad:false,
        value:'${euser.isadmin}',
        listMaxHeight:80
    });
    //是否禁用
    $('#'+tabCode+'_form_state').omCombo({
       dataSource:[{text:'正常',value:'1'},{text:'停用',value:'0'}],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'必选',
        editable:false,
        lazyLoad:false,
        value:'${euser.state}',
        listMaxHeight:80
    });
	//bindUserDeptTree("user_add_department_id",'${crm_menu_id}', '${department.id}',true);
});
</script>