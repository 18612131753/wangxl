<%@ page language="java"  pageEncoding="UTF-8"%>
<!-- 弹出窗口用的DIV -->
<div id="div_for_dialog"></div>
<!-- 弹出图片用的DIV -->
<div id="div_for_workflow_img"></div>

<!-- 单选部门树 - 开始 -->
<div id="department_tree_div" class="search_tree">
	<div><button id="department_tree_clear">全部</button></div>
   	<ul id="department_tree"></ul>
</div>

<div id="userdepartment_tree_div" class="search_tree">
</div>

<div id="default_name" class="search_tree_default_name">
</div>

<script type="text/javascript">
	/**
	* 绑定党支部下拉框
	**/
	function bindDepartmentComboBox(
		jg1_id , jg1_val,
		jg2_id , jg2_val,
		jg3_id , jg3_val
	){
		var jg1_list = [];
    	for(var i=0;i<DepartmentTreeList.length;i++){
    		if(DepartmentTreeList[i].id.length == 3){
    			jg1_list.push(DepartmentTreeList[i]);
    		}
    	}
		$('#'+jg2_id).val('').omCombo({width:'170px',emptyText:'请选择!'});
		$('#'+jg3_id).val('').omCombo({width:'190px',emptyText:'请选择!'});
		$('#'+jg1_id).omCombo({
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
	       		$('#'+jg2_id).omCombo('setData',[]);
	       		$('#'+jg3_id).omCombo('setData',[]);
	       		$('#'+jg3_id).val('').omCombo({emptyText:'请选择!'});
	       		var jg2_list = [];
	       		for(var i=0;i<DepartmentTreeList.length;i++){
	       			if( DepartmentTreeList[i].parent_id == newValue ){
	       				jg2_list.push(DepartmentTreeList[i]);
	       			}
	       		}
	       		$('#'+jg2_id).omCombo({
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
					listMaxHeight:85,
					onValueChange : function(target, newValue, oldValue, event) {
						$('#'+jg3_id).omCombo('setData',[]);
						var jg3_list = [];
						for(var i=0;i<DepartmentTreeList.length;i++){
							if( DepartmentTreeList[i].parent_id== newValue ){
			       				jg3_list.push(DepartmentTreeList[i]);
			       			}		
						}
						$('#'+jg3_id).omCombo({
							dataSource:jg3_list,
							optionField:function(data,index){
								return data.name;
							},
							valueField : 'id',
							inputField : 'name',
							emptyText:'请选择!',
							editable:false,
							disabled:false,
							lazyLoad:true,
							value:jg3_val,
							listMaxHeight:85
						});	
					}
				});
	       		parent_default_val = '';
				child_default_val = '';
	       	}
	   	});
	}
</script>
<!-- 单选部门树 - 结束 -->

<script type="text/javascript">
	
	/**
	 * 设置用户中心树的默认值
	 * input_id 输入框ID
	 * default_val 中心默认值
	 * default_val_name 中心默认值名称
	 **/
	function setUserDeptTreeDefultVal(input_id, default_val, default_val_name) {
		if( default_val != null && default_val_name.length >1 ){  //去除没有名字的情况
			$("#"+input_id).val(default_val);
			$("#"+input_id+"_value").val(default_val_name);
		}
	}

	/**
	 *  绑定 用户中心树
	 *  @Param input_id 输入框ID
	 *  @Param crm_menu_id 菜单ID(-1显示全部中心树)
	 *  @Param default_val 中心默认值（可选,默认为""）
	 *  @Param isClear 是否带清空框(可选，默认为清空)
	 *  @Param callBackfunc 回调函数
	 **/
	function bindUserDeptTree(input_id, crm_menu_id, default_val, isClear,callBackfunc) {
		var clear_id = input_id+"_clear";
		var clear_name = "全部";
		var clear_buttonIcon = "${buttonRemoveIcons}";
		var tree_div_id = "userdepartment_tree_div";//input_id+"_div";
		var tree_id = input_id+"_tree";
		var trigger_id = input_id+"_trigger"; //下拉按钮
		var tree_value_id = input_id+"_value";//存放树的节点ID
		// 树输入框样式
		if (isClear == null) {
			isClear = true;
		}
		if (isClear==true) {
			clear_name = "清空";
			clear_buttonIcon = "${buttonRemoveIcons}";
		}
		var inputUI = $("#"+input_id);
		inputUI.after(
			"<span class='om-combo om-widget om-state-default'>"+
			"<input type='text' id='"+tree_value_id+"' readonly='readonly'/>"+
			"<span id='"+trigger_id+"' class='om-combo-trigger'></span>"+
			"</span>");
	
		var isFist = true;
		// 树加载方法
		var func = function() {
			var treeDivUI = $("#"+tree_div_id);
			treeDivUI.html("<div><button id='"+clear_id+"' type='button'>"+clear_name+"</button></div><ul id='"+tree_id+"'></ul>");
			inputUI.hide();
			// 清除按钮样式
			var clearUI = $("#"+clear_id);
				clearUI.omButton({
					icons : {left : clear_buttonIcon},
					width : 70
				});
				// 全选事件
				clearUI.unbind("click");
				clearUI.click(function(){
					$("#"+input_id).val("");
					if (isClear==null || isClear==true) {
						$("#"+tree_value_id).val("");
					} else {
						$("#"+tree_value_id).val("全选");
					}
					$("#"+tree_div_id).hide();
				});
			// 查询节点对应的路径名
			var getNodeText = function(node){
				if (node != null && node.text != null) {
					var ndata = node, text = ndata.text;
					var departmentTreeUl = $("#"+tree_id);
					ndata = departmentTreeUl.omTree("getParent",ndata);
		    		while(ndata){
		    			text = ndata.text +"-" +text;
		    			ndata = departmentTreeUl.omTree("getParent",ndata);
					}
		    		return text;
				}
				return "";
			};
			var departmentTreeUl = $("#"+tree_id);
			departmentTreeUl.omTree({
				dataSource :  "${contextPath}/userRole/showUserDeptTree?crm_menu_id="+crm_menu_id+"&is_clear="+true,
				simpleDataModel: true,
	            onSelect: function(nodedata){
					var nodedata_child = departmentTreeUl.omTree("getChildren",nodedata);
					//说明是子节点,子节点不让选择
					if( !( nodedata_child.length == 1 && nodedata_child[0].text == null) ){
						return false;
					}
					var text = getNodeText(nodedata);
		    		$("#"+input_id).val(nodedata.id);
					$("#"+tree_value_id).val(text);
					$("#"+tree_div_id).hide();
					if(callBackfunc != null && callBackfunc !=''){
						 window[callBackfunc](nodedata.id);
					}
	            },
				onBeforeSelect: function(nodedata){
				},
				onSuccess:function(data) {
					if (default_val != null && default_val != "") {// 默认值
						var target = $('#'+tree_id).omTree("findNode", "id", default_val);
						var text = getNodeText(target);
			    		$("#"+input_id).val(default_val);
						$("#"+tree_value_id).val(text);
					}
					var depPosition = $("#"+tree_value_id).offset();//input_id
					$("#"+tree_div_id).css({left:depPosition.left + "px",top:depPosition.top+20 +"px"}).hide();
					if (isFist==false) {
						$("#"+tree_div_id).show();
						var funcBodyDown = function(event) {
							if (!(event.target.id == tree_div_id || event.target.id == clear_id )) {
				   				$("#"+tree_div_id).hide();
				   				$("body").unbind("mousedown",funcBodyDown);
				   			}
						};
						$("body").bind("mousedown", funcBodyDown);
					}
					isFist = false;
				}
	        });
		};
		
		func();
		
		
		// 显示框事件
		var valueUI = $("#"+tree_value_id);
		valueUI.unbind("click");
		valueUI.bind("click", func);
		// 下拉按钮事件
		var triggerUI = $("#"+trigger_id);
		triggerUI.unbind("click");
		triggerUI.bind("click", func);
	}

	/**
	下了列表框
	input_id input输入id
	type_id 数据字典类型id
	default_val 默认值
	func 当值变化时的回调函数 
	headerTitle：头显示内容
	*/
	function bindDropList(input_id, type_id,default_val, func, headerTitle,isEditable) {
		if (default_val == null) {
			default_val = "";
		}
		$("#"+input_id).val(default_val);
		$('#'+input_id).omCombo({
	        dataSource:'${contextPath}/dicdata/queryForList?type_id='+type_id,
	        optionField:function(data,index){
	            return data.crm_nm;
	        },
	        valueField : 'id',
	        inputField : 'crm_nm',
	        emptyText:'请选择!',
	        editable:isEditable,
	        lazyLoad:true,
	        autoFilter : true,
	        listMaxHeight:125,
	        //width:110,
	        value:default_val,
	        onSuccess:function(data, textStatus, event){
	        	if (headerTitle != null && data != null && data.length > 0) {
	        		var el = {'id':'','crm_nm':''+headerTitle};
	        		for (var i=0;i<data.length; i++) {
	        			var temp = data[i];
	        			data[i] = el;
	        			el = temp;
	        		}
	        		data[data.length] = el;
	        	}
	        },
	        onValueChange : func
    	});
	}
	/**
	绑定下拉列表
	*/
	function newComboBox(config){
		$('#'+config.id).omCombo({
	           dataSource:(config.dataSource?config.dataSource:"${contextPath}/dic/comboData/"+config.dicCode+(config.ALLOWEMPTY?"/true":"")),
	           optionField:function(data,index){
	         		return data.name;
			   },
			   valueField:'id',
			   inputField:'name',
			   emptyText:'请选择...',
			   editable:(config.editable?true:false),
			   lazyLoad:(config.lazyLoad?true:false),
			   listMaxHeight:(config.listMaxHeight?config.listMaxHeight:100),
			   value:(config.value?config.value:''),
			   //filterStrategy:'anywhere',//(config.filterStrategy?config.filterStrategy:''),//'anywhere','last','first'
			   onValueChange:function(target, newValue, oldValue, event){
				   if(config.onValueChange){
					  config.onValueChange(target, newValue, oldValue, event);
				   }
			   }
	    });
	}
	/**
	绑定过滤下拉列表,只显示指定类型的部分数据字典
	noContentIds 为要过滤掉的ID字符串，多个ID用 ; 号（分号）隔开
	*/
	function newComboFilterBox(config){
		$('#'+config.id).omCombo({
	           dataSource:(config.dataSource?config.dataSource:"${contextPath}/dic/comboFilterData/"+config.dicCode+(config.ALLOWEMPTY?"/true":"")+"?noContentIds="+config.noContentIds),
	           optionField:function(data,index){
	         		return data.name;
			   },
			   valueField:'id',
			   inputField:'name',
			   emptyText:'请选择...',
			   editable:(config.editable?true:false),
			   lazyLoad:(config.lazyLoad?true:false),
			   listMaxHeight:(config.listMaxHeight?config.listMaxHeight:100),
			   value:(config.value?config.value:''),
			   //filterStrategy:'anywhere',//(config.filterStrategy?config.filterStrategy:''),//'anywhere','last','first'
			   onValueChange:function(target, newValue, oldValue, event){
				   if(config.onValueChange){
					  config.onValueChange(target, newValue, oldValue, event);
				   }
			   }
	    });
	}
	/**
	绑定城市下拉列表
	*/
	function newComboCityBox(config){
		$('#'+config.id).omCombo({
	           dataSource:(config.dataSource?config.dataSource:"${contextPath}/dicdata/queryForCity"),
	           optionField:function(data,index){
	         		return data.name;
			   },
			   valueField:'id',
			   inputField:'name',
			   emptyText:'请选择...',
			   lazyLoad:(config.lazyLoad?true:false),
			   listMaxHeight:(config.listMaxHeight?config.listMaxHeight:100),
			   value:(config.value?config.value:''),
			   filterStrategy : 'first',
			   onValueChange:function(target, newValue, oldValue, event){
				   if(config.onValueChange){
					  config.onValueChange(target, newValue, oldValue, event);
				   }
			   }
	    });
	}
	/**
	下了列表框 - 自定义宽度的，用户弹出窗
	input_id input输入id
	type_id 数据字典类型id
	default_val 默认值
	func 当值变化时的回调函数 
	headerTitle：头显示内容
	*/
	function bindDropListForWidth(input_id, type_id,default_val, func, headerTitle ,width) {
		if (default_val == null) {
			default_val = "";
		}
		$("#"+input_id).val(default_val);
		$('#'+input_id).omCombo({
	        dataSource:'${contextPath}/ddData/getDataSources?id='+type_id,
	        optionField:function(data,index){
	            return data.name;
	        },
	        valueField : 'id',
	        inputField : 'name',
	        emptyText:'请选择!',
	        editable:true,
	        lazyLoad:true,
	        autoFilter : true,
	        listMaxHeight:85,
	        width:110,
	        value:default_val,
	        onSuccess:function(data, textStatus, event){
	        	if (headerTitle != null && data != null && data.length > 0) {
	        		var el = {'id':'','name':''+headerTitle};
	        		for (var i=0;i<data.length; i++) {
	        			var temp = data[i];
	        			data[i] = el;
	        			el = temp;
	        		}
	        		data[data.length] = el;
	        	}
	        },
	        onValueChange : func
    	});
	}
	/**
	静态下拉列表
	input_id input框id
	dataSource 数据源json串 例如[{text : '启用', value : 0},{text : '停用', value : 1}]
	default_val 默认值
	func 当选择框的值改变时调用的回调函数
	*/
	function bindStaticDropList(input_id, dataSource,default_val, func) {
		$('#'+input_id).omCombo({
	        dataSource:dataSource,
	        emptyText:'请选择!',
	        editable:false,
	        lazyLoad:true,
	        listMaxHeight:85,
	        value:default_val,
	        onValueChange : function(target, newValue, oldValue, event){
	        	if($.isFunction(func)){
	        		func(target, newValue, oldValue, event);
	        	}
	        }
    	});
	}

	//数据字典取值方法，传递俩个参数，分别是数据字典类型id和数据id
    function getDataName(id1,id2){
		var name = "";
		$.ajax({
			type : "POST",
			url : '${contextPath}/ddData/getDataName?id1='+ id1 + '&id2='+id2,
			async : false,
			dataType : "json",
			success : function(data, textStatus) {
				name = data;
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			}
		});
		return name.dataName;
	}

	/** 
	 *  绑定公共的弹出输入框
	 *  @Param dialogId 对话框id
	 *  @Param width
	 *  @Param height
	 *  @Param title 对话框标题
	 *  @Param yesFunc  确定按钮回调函数 function(){}
	 **/
	function bindInputDialog(dialogId, width, height, title, yesFunc, yesBtnTitle) {
		if (yesFunc != null) {
			if (yesBtnTitle == null) {
				yesBtnTitle = "保存";
			}
			// 对话框初始化
			$( "#"+dialogId).omDialog({
				title:''+title,
				autoOpen: true,
				height: 'auto',
				width: width,
				modal: true,
				resizable:false,
				onClose :function(){
					//销毁DIV
					$( "#"+dialogId).remove();
				},
				buttons : [
					{ text : ""+yesBtnTitle,id:dialogId+'_submit',click : yesFunc}, 
					{ text : "取消",id:dialogId+'_colse',click : function () {$( "#"+dialogId).omDialog('close'); } }
				]
			});
			$( "#"+dialogId+'_submit').omButton({
				icons : { left : '${windowSubmitIcons}'}
	       	 });
			 $( "#"+dialogId+'_colse').omButton({
				icons : { left : '${windowCloseIcons}'}
	       	 });
		} else {
			// 无按钮的对话框
			$( "#"+dialogId).omDialog({
				title:''+title,
				autoOpen: true,
				height: 'auto',
				width: width,
				modal: true,
				resizable:false,
				onClose :function(){
					//销毁DIV
					$( "#"+dialogId).remove();
				}
			});
		}
	}
	 
	// 绑定查询框
	function bindSearchDialog(search_dialog, search_form, gridId, iwidth, iheight, searchFunc) {
		var search_submit = search_dialog + "_submit";
		var search_reset = search_dialog +"_reset";
		var search_close = search_dialog + "_colse";
		$("#"+search_dialog).omDialog({//consulter_search_panel_dialog
			title : '搜索',
			autoOpen : true,
			height : 'auto',
			width : iwidth,
			modal : true,
			resizable : false,
			onClose : function() {
			},
			buttons : [ {
				text : "搜索",
				id : ''+search_submit,
				click : function() {
					searchFunc();
					$( "#"+search_dialog).omDialog('close');
				}
			}, {
				text : "重置",
				id : ''+search_reset,
				click : function() {
					$("#"+search_form)[0].reset();
				}
			}, {
				text : "取消",
				id : ''+search_close,
				click : function() {
					$("#"+search_dialog).omDialog('close');
				}
			} ]
		});
		$("#"+search_submit).omButton({
			icons : {
				left : '${buttonSearchIcons}'
			}
		});
		$("#"+search_reset).omButton({
			icons : {
				left : '${buttonResetIcons}'
			}
		});
		$("#"+search_close).omButton({
			icons : {
				left : '${windowCloseIcons}'
			}
		});
	}
	
	
	/**
	 *  获取选择的Grid行
	 *  @Param sgridId 
	 **/
	function getSelections(sgridId) {
		var selections=$('#'+sgridId).omGrid('getSelections',true);
		if (selections.length == 0) {
     		$.omMessageBox.alert({content:'您还没有选择要操作的记录',onClose:function(v){}});
       		return null;
		}
		return selections;
	}
	
	/**
	 *  Ajax修改
	 *  @Param sgridId
	 *  @Param surl  修改请求url
	 *  @Param stype 修改请求type
	 *  @Param sdataType 修改请求dataType
	 *  @Param sdata 修改请求data
	 **/
	function update(sgridId, surl, stype, sdataType, ssync, sdata) {
		$.ajax({
			type: stype,
			dataType: sdataType,
			url: surl,
			sync: ssync,
			data: sdata,
			success:function(result) {
				if (result.result==0) {
					main_messageTip_show('操作失败');
				} else if (result.result==1) {
					$('#'+sgridId).omGrid('reload');
					main_messageTip_show('操作成功');
				} else if (result.result==2){
					main_messageTip_show('数据存在级联,不能操作');
				}
			},
			error:function(err) {
				main_messageTip_show(ERROR_MESSAGE);
			}
		});
	}
	 
	/**
	 *  Ajax真删除
	 *  @Param sgridId
	 *  @Param surl 删除请求url
	 **/
	function realDelete(sgridId, surl) {
		confirmWin(function(v){
			if (v) {// 确定删除
				SuperMan_show_bg();
				$.ajax({
					type:"POST",
					dataType:"json",
					url: surl,
					data:{},
					success:function(result) {
						SuperMan_hide_bg();
						if (result.result==0) {//失败
							main_messageTip_deleteError_show();
						} else if (result.result==1){//成功
							$('#'+sgridId).omGrid('reload');
							main_messageTip_deleteSuccess_show();
						} else if (result.result==2){//有级联，不得删除
							main_messageTip_deleteCantBe_show();
						}
					},
					error:function(err) {
						SuperMan_hide_bg();
						main_messageTip_show(ERROR_MESSAGE);
					}
				});
			}
		});
	}
	
	// 废弃
	function ajaxDiscard(sgridId, surl) {
		$.omMessageBox.confirm({
			title:'提示',
			content:'您确定要废弃此条记录？',
			onClose:function(v) {
				if (v) {
					SuperMan_show_bg();
					$.ajax({
						type:"POST",
						dataType:"json",
						url: surl,
						data:{},
						success:function(result) {
							SuperMan_hide_bg() ;
							if (result.result==1) {
								SuperMan_hide_bg();
								$('#'+sgridId).omGrid('reload');
								main_messageTip_show('废弃成功');
							} else {
								SuperMan_hide_bg();
								main_messageTip_show('不能废弃');
							}
						},
						error:function(err) {
							SuperMan_hide_bg() ;
							main_messageTip_show('返回错误');
						}
					});
				}
			}
		});
	}
	
	/****
	*时间格式转换方法
	*@param formatStr 时间格式
	*@param fdate 时间
	*/
	function formatDate(fdate,formatStr){
		 var fTime, fStr = 'ymdhis';
		 if (!formatStr)
		 	formatStr= "y-m-d h:i:s";
		 if (fdate)
		 	fTime = new Date(fdate);
		 else return fdate;
		 	//fTime = new Date();
		 var formatArr = [
		 fTime.getFullYear().toString(),
		 (fTime.getMonth()+1).toString().length==2?(fTime.getMonth()+1).toString():"0"+(fTime.getMonth()+1).toString(),
		 fTime.getDate().toString().length==2? fTime.getDate().toString():"0"+ fTime.getDate().toString(),
		 fTime.getHours().toString().length==2?fTime.getHours().toString():"0"+fTime.getHours().toString(),
		 fTime.getMinutes().toString().length==2?fTime.getMinutes().toString():"0"+fTime.getMinutes().toString(),
		 fTime.getSeconds().toString().length==2?fTime.getSeconds().toString():"0"+fTime.getSeconds().toString()
		 ];
		 for (var i=0; i<formatArr.length; i++)
		 {
		  formatStr = formatStr.replace(fStr.charAt(i), formatArr[i]);
		 }
		 return formatStr;
	}
	/**
	确认窗口，
	@param func 回调函数 参数v点击确定是true ，点击取消是false
	*/
	function confirmWin(func){
		$.omMessageBox.confirm({
           title:'提示',
           content:'您确定要删除此条记录？',
           onClose:func
       });
	}
	
	function confirmWinDeploy(func){
				$.omMessageBox.confirm({
		           title:'提示',
		           content:'您确定要部署此工作流？',
		           onClose:func
		       });
	}

	/**
	*alert窗口
	*text 显示的内容
	*fun 点击确定的回调函数  
	*/
	function opAlert(text,fun){
		$.omMessageBox.alert({
           content:text,
           onClose:fun
       });
	}

	/**
	 * 多选框单选的实现
	 * @param {Object} spanId 绑定的span的ID
	 * @param {Object} textName checkBox的name值
	 * @param {Object} checkBoxItemJsonStr 选项,以json串传入,如[{'value':'1','text':'男'},{'value':'0','text':'女'}]
	 * @param {Object} defaultVal  默认选中值
	 * @param {Object} callBackfunc  回调函数
	 * @memberOf {TypeName} 
	 */
	function bindSelectOneCheckBox(spanId, textName,checkBoxItemJsonStr, defaultVal,callBackfunc,isDisable) {
		 //将字符串转为json对象
		var checkBoxItems = eval(checkBoxItemJsonStr);
		 //保留checkBox组被选定的值
		var func = function() {
			$("input[name='"+textName+"']").attr("checked",false);
			$(this).attr("checked", true);
			if(callBackfunc != null && callBackfunc !=''){
				window[callBackfunc]($(this).attr("value"));
			}
		};
		var str = "";
		for(var i = 0; i < checkBoxItems.length;i++){//遍历选项
			var id = spanId+"_input_"+i ;
			if(isDisable == 1){
				if(defaultVal != null && checkBoxItems[i].value == defaultVal){//设置默认值
					str +='<input disabled id="'+id+'" type="checkbox" checked="true" name="'+textName+'" value="'+checkBoxItems[i].value+'"/>'+checkBoxItems[i].text ;
				} else {
					str +='<input disabled id="'+id+'" type="checkbox" name="'+textName+'" value="'+checkBoxItems[i].value+'"/>'+checkBoxItems[i].text ;
				}
			}else{
				if(defaultVal != null && checkBoxItems[i].value == defaultVal){//设置默认值
				str +='<input id="'+id+'" type="checkbox" checked="true" name="'+textName+'" value="'+checkBoxItems[i].value+'"/>'+checkBoxItems[i].text ;
				} else {
					str +='<input id="'+id+'" type="checkbox" name="'+textName+'" value="'+checkBoxItems[i].value+'"/>'+checkBoxItems[i].text ;
				}
			}
			
		}
		$("#"+spanId).html(str);
		for(var i = 0; i < checkBoxItems.length;i++){
			var id = spanId+"_input_"+i ;
			$("#"+id).bind("click",func);//给选项绑定事件
		}
	}
	
	
	/*** 
	*级联下拉列表
	* @super_band_id 父下拉列表ID
	* @super_defaultValue 父下拉框默认值
	* @sub_band_id   子下拉列表ID
	* @sub_defaultValue 父下拉框默认值
	* @super_data_url 父下拉列表数据源请求url
	* @sub_data_url 子下拉列表数据源请求url
	* @func 回调函数
	*/
	
	function cascadeDropList(super_band_id,super_defaultValue,sub_band_id,sub_defaultValue,super_data_url,sub_data_url,func){
		var sign = false;
		//ajax去后台取数据
		var subMap;
		$.ajax({
			type : "POST",
			url : sub_data_url,
			async : false,
			dataType : "json",
			success : function(data, textStatus) {
				subMap = data;  
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			}
		});
		$('#'+sub_band_id).val('').omCombo({ emptyText:'请选择!' });
		$('#'+super_band_id).omCombo({
            dataSource : super_data_url,
            optionField:function(data,index){
            return data.name;
	        },
	        valueField : 'id',
	        inputField : 'name',
	        emptyText:'请选择!',
	        editable:true,
	        lazyLoad:true,
	        value:super_defaultValue,
	        listMaxHeight:80,
            onValueChange : function(target, newValue, oldValue, event) {
               //根据第1个combo的当前值算出第2个combo的值
               var records = getSubRecords(newValue);
               //将算出的值设置为第2个combo的数据源
              $('#'+sub_band_id).val('').omCombo('setData',[]);//当父类型改变时子类型跟着变化
               if(records.length==1){//如果子下拉列表只有一个数据直接显示
               		for(i=0;i<records.length;i++){
	             		if(sub_defaultValue == records[i].id ){
	             			sign = true;
	             		}
	             	}
	             	if(sign == false){
	             		sub_defaultValue = null;
	             	}
                	$('#'+sub_band_id).val('').omCombo({
                 	dataSource : records,
		            optionField:function(data,index){
			            return data.name;
			        },
			        valueField : 'id',
			        inputField : 'name',
			        value:records[0].id,
			        emptyText:'请选择!'
                	});
               }else{
               
	             	for(i=0;i<records.length;i++){
	             		if(sub_defaultValue == records[i].id ){
	             			sign = true;
	             		}
	             	}
	             	if(sign == false){
	             		sub_defaultValue = null;
	             	}

	                 $('#'+sub_band_id).val('').omCombo({
	                 	dataSource : records,
			            optionField:function(data,index){
				            return data.name;
				        },
				        valueField : 'id',
				        inputField : 'name',
				        value:sub_defaultValue,
				        emptyText:'请选择!'
	                });
               }
        		if ($.isFunction(func)) {
					func();
				}
				sign = false;
           }
        });

		//返回级联子列表的数据
		function getSubRecords(sub_id){
			var jsonData;
			$.each(subMap,function(key,values){
				if(key == sub_id){
					jsonData = values;
				}
			 });    
			return jsonData;
		}
	
	}
	
		// 获取选择的Grid行
	function getSelectionId(sgridId) {
		var toDeleteRecordID = "";
		var selections=$('#'+sgridId).omGrid('getSelections',true);
		if (selections.length == 0) {
     		$.omMessageBox.alert({content:'您还没有选择要操作的记录',onClose:function(v){}});
       		return toDeleteRecordID;
		}
		var toDeleteRecordID=selections[0].id;
		return toDeleteRecordID;
	}
	/**
	 * 回车提交表单
	 * @param {Object} dialog 绑定事件的窗口ID
	 * @param {Object} button 按回车键要执行的哪个按钮的click事件
	 */
	function bindEnterSubmit(dialog,button){
		$('#'+dialog).attr('tabindex', 1).keyup(function(e){
        	if(e.keyCode == 13){
      			if( $('#'+dialog).parent().css("display") != "none" && $('#'+dialog).css("display") != "none"){ //如果窗口隐藏，则不提交
      				if($('#'+button).attr("disabled")=="disabled"){
      					
      				}else{
      					$('#'+button).click();
      				}
      				
      			}
        	}
        });
		$('#'+dialog).css("outline","none");
	}

	/***
	*绑定补齐学生名称方法
	*/
	function defaultName(node_id,crm_menu_id){
		var name_node = $('#' + node_id);	    
 		var index = 0;
 		$("body").bind("mousedown", function (event) {
			if (!(event.target.id == "default_name" || event.target.id == node_id  || event.target.id == "default_table" || $(event.target).parents("#default_table").length>0)) {
				$("#default_name").hide();
			}
		});
		$("#" + node_id).keyup(function (event) {//上下键获取焦点
			var trs = $("#default_table").find("tr");
			var maxIndex = trs.length;
            var key = event.keyCode;
			var tr,choose_tr;
            if (key == 38) {/*向上按钮*/
                index--;
                if (index < 1) index = maxIndex; //到顶了,
	            for(var i = 1;i<maxIndex+1;i++){
	 				tr = $("#default_table").find("tr:eq("+(i-1)+")");
	 				tr.removeAttr("class");
	 				if(i==index){
	 					choose_tr = tr;
	 					tr.attr("class","default_name_tr");
	 				}
	 			}
	 			    var content = choose_tr.find("td").html();
					name_node.val(content);
            } else if (key == 40) {/*向下按钮*/
                index++;
                if (index > maxIndex) index = 1; //到底了
	            for(var i = 1;i<maxIndex+1;i++){
	 				tr = $("#default_table").find("tr:eq("+(i-1)+")");
	 				tr.removeAttr("class");
	 				if(i==index){
	 					choose_tr = tr;
	 					tr.attr("class","default_name_tr");
	 				}
	 			}
	 			var content = choose_tr.find("td").html();
				name_node.val(content);
            }else if (key == 13) {/*回车确定*/
            	$("#default_name").hide();
            } else{
            $.ajax({
				type:"POST",
				dataType:"json",
				url: "${contextPath}/student/findDefaultNameStudent?crm_menu_id="+crm_menu_id,
				data:{student_name:name_node.val()},
				success:function(result) {
					if(result.list.length > 0){
						createTable(result.list,node_id);
						$("#default_name").css({left:name_node.offset().left + "px",top:name_node.offset().top+20 +"px"}).show();
					}else{
						$("#default_name").hide();
					}
				},
				error:function(err) {
					main_messageTip_show('系统异常');
				}
			});
            }
        }); 
		
	}
	function createTable(list,node_id){
		var con = "";
		for(var i =0;i<list.length;i++){
			con +="<tr><td class='default_name_hover' onclick='clikcChoose(this,\""+ node_id +"\")'>"+list[i]+"</td></tr>";
		}
		var innerContent = "<table style='text-align:left;' id='default_table' width='100%'>"+con+"</table>";
		$("#default_name").html(innerContent);
	}
	function clikcChoose(obj,node_id){
		$('#' + node_id).val(obj.innerHTML);
		$("#default_name").hide();
	}
	function bindDialogScroll(dialogId){
		$("#"+dialogId).scroll(function(){
			$(".om-droplist").css('display','none');
			$("#"+dialogId).focus();
		});
	}
</script>