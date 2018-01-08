var ALL_LIMIT = 50 ;     //全部分页
var CHECK_ALL_LIMIT = 50;     //审批全部分页
var ALL_LIMIT_20 = 20;//
var CENTER_HEIGHT ;
var CENTER_TAB_HEIGHT ;      //布局汇总，center取出tab的TITLE的高度-中心区域的高度
var TAB_TITLE_HEIGHT = 28;   //tab的title的高度，样式中定义
var BUTTON_BAR_HEIGHT = 40;   //按钮区域高度
var PANEL_TITILE_HEIGHT = 26;  //PANEL中title的高度
var ERROR_MESSAGE = '网络或数据异常,请刷新页面';
var ADD_SUCCESS = '新建成功';
var ADD_ERROR = '新建失败';
var UPDATE_SUCCESS = '修改成功';
var UPDATE_ERROR = '修改失败';
var UPDATE_CANT_BE = '数据存在级联,不能修改';
var DELETE_SUCCESS = '删除成功';
var DELETE_ERROR = '删除失败';
var DELETE_CANT_BE = '数据存在级联,不能删除';
var PLEASE_SELECT_ONE = '请至少选择一行记录';
String.prototype.replaceAll  = function(s1,s2){    
  return this.replace(new RegExp(s1,"gm"),s2);    
};
//右下角提示信息
function main_messageTip_show( content ){
	$.omMessageTip.show({
	    title : '提示',
	    content : content,
	    timeout : 1000
	});
}

function main_messageBox_alert(contentText){
	$.omMessageBox.alert({
        content: contentText
    });
}
//右下角提示信息 网络或数据异常,请刷新页面
function main_messageTip_systemError_show(){
	main_messageTip_show(ERROR_MESSAGE);
}
//右下角提示信息 新建成功
function main_messageTip_addSuccess_show(){
	main_messageTip_show(ADD_SUCCESS);
}
//右下角提示信息 新建失败
function main_messageTip_addError_show(){
	main_messageTip_show(ADD_ERROR);
}
//右下角提示信息修改成功
function main_messageTip_updateSuccess_show(){
	main_messageTip_show(UPDATE_SUCCESS);
}
//右下角提示信息修改失败
function main_messageTip_updateError_show(){
	main_messageTip_show(UPDATE_ERROR);
}

//右下角提示信息数据存在级联,不能修改
function main_messageTip_updateCantBe(){
	main_messageTip_show(UPDATE_CANT_BE);
}

//右下角提示信息删除成功
function main_messageTip_deleteSuccess_show(){
	main_messageTip_show(DELETE_SUCCESS);
}

//右下角提示信息删除失败
function main_messageTip_deleteError_show(){
	main_messageTip_show(DELETE_ERROR);
}

//右下角提示信息数据存在级联,不能删除
function main_messageTip_deleteCantBe_show(){
	main_messageTip_show(DELETE_CANT_BE);
}
//请至少选择一行记录
function main_messageBox_pleaseSelectOne_alert(){
	main_messageTip_show(PLEASE_SELECT_ONE);
}
function main_messageBox_Atleast_SelectOne_alert(contentText){
	$.omMessageBox.alert({
        content: contentText
    });
}
//替换DIV的HTML
function main_ChangeDivContent(divId,url){
	SuperMan_show_bg();
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		dataType : "html",
		success : function(result) {
			SuperMan_hide_bg() ;
			$("#" + divId).html(result);
		},
		error:function(){
			SuperMan_hide_bg() ;
			main_messageTip_systemError_show();
		}
	});
}

function main_openWindow(url){
	var nw = window.open (url,'_blank','height=400,width=750,top=100,left=270,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
}
document.onkeydown = function() {
	// Backspace (keyCode == 8)
	if (window.event) { //IE
		document.onkeydown = function() {
			var type = event.srcElement.type;
			if (event.keyCode == 8) {
				if (event.srcElement.readOnly == true) {
					return false;
				} else if (type != 'text' && type != 'password' && type != 'textarea') {
					return false;
				}
			}
		}
	} else { //Firefox
		document.onkeypress = function(event) {
			var type = event.target.type;
			if (event.keyCode == 8) {
				if (event.target.readOnly == true) {
					return false;
				} else if (type != 'text' && type != 'password' && type != 'textarea') {
					return false;
				}
			}
		}
	}
}
//遮盖层 -开始
	function getPageSizeWithScroll(){  
	    if (window.innerHeight && window.scrollMaxY) {// Firefox  
	        yWithScroll = window.innerHeight + window.scrollMaxY;  
	        xWithScroll = window.innerWidth + window.scrollMaxX;  
	    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac  
	        yWithScroll = document.body.scrollHeight;  
	        xWithScroll = document.body.scrollWidth;  
	    } else { // works in Explorer 6 Strict, Mozilla (not FF) and Safari  
	        yWithScroll = document.body.offsetHeight;  
	        xWithScroll = document.body.offsetWidth;  
	    }  
	    return [xWithScroll, yWithScroll];  
	} 

	function SuperMan_show_bg() {  
		//var de = document.documentElement;  
		//var size = getPageSizeWithScroll();  
		$("#bnu_edu_bg").css({
			left:0, top:0, width:'100%', height:'100%',
			background:'rgba(0,0,0,0.3)',zIndex:1000, position: 'fixed'
		}).show();   
	}
	function SuperMan_hide_bg() {
		var time = setTimeout(function(){$("#bnu_edu_bg").hide()},500);
	}
//遮盖层 -结束