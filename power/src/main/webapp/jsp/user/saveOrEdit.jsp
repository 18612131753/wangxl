<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../commons/taglibs.jsp"%>
<div id="${tabCode}_form_dialog">
	<form id="${tabCode}_form" method="post">
		<table>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>教室名称：</td>
				<td class="td_left"><input type="text" id="${tabCode}_form_crm_nm" name="crm_nm" value="${mdl.crm_nm}"/></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>大区/中心：</td>
				<td class="td_left"><INPUT id="${tabCode}_form_crm_center_id" type="text" name="crm_center_id"  value="${mdl.crm_center_id}"/></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>容纳人数：</td>
				<td class="td_left"><input id="${tabCode}_form_crm_contain_num" name="crm_contain_num" value="${mdl.crm_contain_num}"/></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>地址：</td>
				<td class="td_left">
				<INPUT id="${tabCode}_form_crm_address" type="text" name="crm_address" value="${mdl.crm_address}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td></td>
				<td class="td_right">备注： </td>
				<td class="td_left">
					<textarea id="${tabCode}_form_crm_remarks" name = "crm_remarks" style="width: 100%">${mdl.crm_remarks}</textarea>
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
//var userStateDic = [{text:'有效',value:1},{text:'无效',value:0}];
//var userGenderDic = [{value:1,text:'男'},{value:0,text:'女'}];

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
				}else{
					
				}
			} else {
				if("edit"==new_or_edit){
					main_messageTip_updateError_show();
				}else if("create"==new_or_edit){
					main_messageTip_addError_show();
				}else{
					
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
			crm_nm : { required : true, maxlength : 20 },
			crm_center_id : { required : true },
			crm_contain_num : { required : true, maxlength : 5 },
			crm_address : { required : true , maxlength : 128 }
		},
		messages : {
			crm_nm : { required : "教室名称必填", maxlength : "长度不超过20个字符" },
			crm_center_id : { required : "大区/中心必填" },
			crm_contain_num : { required : "容纳人数必填", maxlength : "长度不超过5个字符" },
			crm_address : { required : "地址必填", maxlength : "长度不超过64个字符" }
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
 	
	var dtitle = (("edit"!=new_or_edit)?"新建":"编辑")+"<label id=\""+FORM_PAGE_CONFIG.FORM_DIALOG+"_error\" class=\"error\" generated=\"true\" style=\"display:none;\"></label>";
	
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
	
	//bindUserDeptTree("user_add_department_id",'${crm_menu_id}', '${department.id}',true);
});
</script>