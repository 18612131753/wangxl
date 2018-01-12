<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../commons/taglibs.jsp"%>
<div id="${tabCode}_form_dialog">
	<form id="${tabCode}_form" method="post" >
		<input type="text" style="display:none;" name="type" value="1"/>
		<table>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span>题目：</td>
				<td class="td_left">
					<input type="text" id="${tabCode}_form_title" name="title" style="width:380px"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span>难度：</td>
				<td class="td_left">
					<input type="text" id="${tabCode}_form_level" name="level"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span><input type="radio" name="answer" value="A" checked/>选项A：
				</td>
				<td class="td_left">
					<INPUT id="${tabCode}_form_opt_a" type="text" style="width:380px" name="opt_a" value="${q.opt_a}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span><input type="radio" name="answer" value="B"/>选项B：
				</td>
				<td class="td_left">
					<INPUT id="${tabCode}_form_opt_b" type="text" style="width:380px" name="opt_b" value="${q.opt_b}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span><input type="radio" name="answer" value="C"/>选项C：
				</td>
				<td class="td_left">
					<INPUT id="${tabCode}_form_opt_c" type="text" style="width:380px" name="opt_c" value="${q.opt_c}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					<span style="color:red;">*</span><input type="radio" name="answer" value="D"/>选项D：
				</td>
				<td class="td_left">
					<INPUT id="${tabCode}_form_opt_d" type="text" style="width:380px" name="opt_d" value="${q.opt_d}"/>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<td class="td_right">
					正确答案：
				</td>
				<td class="td_left">
					<span style="color:red;" id="${tabCode}_form_answer">${q==null || q.answer==null ?"A":q.answer}</span>
				</td>
				<td width="50"><span class="errorImg"></span><span class="errorMsg"></span></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">    
$(document).ready(function() {
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
	// 难度
	$('#'+tabCode+'_form_level').omCombo({
        dataSource:[
			{text:'容易',value:'1'},
			{text:'中等',value:'2'},
			{text:'困难',value:'3'}
        ],
        optionField:function(data,index){
            return data.text;
        },
        emptyText:'必选',
        editable:false,
        lazyLoad:true,
        value:'${q.level}',
        listMaxHeight:80
    });
    
	//表单验证
	$("#"+FORM_PAGE_CONFIG.FORM).validate({
		rules : {
			title : { required : true },
			level : { required : true },
			opt_a : { required : true , maxlength : 200},
			opt_b : { required : true , maxlength : 200},
			opt_c : { required : true , maxlength : 200},
			opt_d : { required : true , maxlength : 200}
		},
		messages : {
			title : { required : "题目必填" },
			level : { required : "难度必选"},
			opt_a : { required : "内容必填" ,maxlength : "不得超过200字"},
			opt_b : { required : "内容必填" ,maxlength : "不得超过200字"},
			opt_c : { required : "内容必填" ,maxlength : "不得超过200字"},
			opt_d : { required : "内容必填" ,maxlength : "不得超过200字"}
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
 	// 选择正确答案
	$("input[name='answer']").click(function(){
		$('#'+tabCode+'_form_answer').html($(this).val());
	});
	var dtitle = (("create"==new_or_edit)?"新建-单选题":"编辑-单选题")+"<label id=\""+FORM_PAGE_CONFIG.FORM_DIALOG+"_error\" class=\"error\" generated=\"true\" style=\"display:none;\"></label>";
	
	$("#"+FORM_PAGE_CONFIG.FORM_DIALOG).omDialog({
		title : dtitle,
		autoOpen : true,
		height : 'auto',
		width : 600,
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

});
</script>