<%@ page language="java" pageEncoding="UTF-8"%>
<div id="department_showTree_dialog">
	<ul id="department_showTree_dialog_ul"></ul>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#department_showTree_dialog").omDialog({
			title : "组织结构",
			autoOpen : true,
			height : 500,
			width : 650,
			modal : true,
			resizable : false,
			onClose :function(){
				$( "#department_showTree_dialog").remove();
			}
		});
		var dTree = [] ;
		for(var i=0;i<DepartmentTreeList.length;i++){
			if( DepartmentTreeList[i].id != '000' ){
				dTree.push({id:DepartmentTreeList[i].id,text:DepartmentTreeList[i].name,pid:DepartmentTreeList[i].parent_id});
			}
		}
		$("#department_showTree_dialog_ul").omTree({
			dataSource : dTree ,
			simpleDataModel: true
        });
	});
</script>