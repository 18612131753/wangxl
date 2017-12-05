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
					<span style="color: red;">*</span>父节点：</td>
				<td class="td_left">
					<input type="text" id="${tabCode}_form_pmenuid" name="pmenuid"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;"></span>名称：</td>
				<td class="td_left">
					<input type="text" id="${tabCode}_form_name" name="name"  value="${menu.name}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color: red;">*</span>排序：</td>
				<td class="td_left"><INPUT id="${tabCode}_form_ordernum" type="text" name="ordernum" value="${menu.ordernum}"/></td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="td_right">
					<span style="color:red;">*</span>URL：</td>
				<td class="td_left"><input id="${tabCode}_form_url" name="url" style="width:200px" value="${menu.url}"/></td>
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
			pmenuid : { required : true },
			name : { required : true , maxlength : 20},
			ordernum : { required : true ,digits:true},
			url: { required : true , maxlength : 40}
		},
		messages : {
			pmenuid : { required : "父节点必填" },
			name : { required : "必填" , maxlength :"长度不超过20个字符"},
			ordernum : { required : "必填",digits:"请输入数字"},
			url: { required : "必填" , maxlength : 40}
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

	//父节点
	$('#'+tabCode+'_form_pmenuid').omCombo({
        dataSource:'${contextPath}/menu/findMenu1/1',
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'必选',
        editable:false,
        lazyLoad:false,
        value:'${menu.pmenuid}',
        listMaxHeight:80
    });
});
</script>