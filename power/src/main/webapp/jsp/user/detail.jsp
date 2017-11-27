<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>教室管理</TITLE>
</HEAD>
<BODY>
	<div id="class_dialog" style="text-align: center;">
		
		<table  >
				
				<tr height="30px">
					<td class="td_right"><span class="label">所属中心：</span></td>
					<td class="td_left">${Classroom.department_name}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">教室名称：</span></td>
					<td class="td_left">${Classroom.name}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">楼层：</span></td>
					<td class="td_left">${Classroom.floor}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">房间号：</span></td>
					<td class="td_left">${Classroom.room_num}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">座位数：</span></td>
					<td class="td_left">${Classroom.seat_num}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">地址：</span></td>
					<td class="td_left">${Classroom.address}</td>
				</tr>
				<tr height="30px">
					<td class="td_right"><span class="label">创建人：</span></td>
					<td class="td_left">${Classroom.creator.name}</td>
					<td class="td_right"><span class="label">创建时间：</span></td>
					<td class="td_left">${Classroom.createDateStr}</td>
				</tr>

				<tr height="30px">
					<td class="td_right"><span class="label">更新人：</span></td>
					<td class="td_left">${Classroom.updator.name}</td>
					<td class="td_right"><span class="label">更新时间：</span></td>
					<td class="td_left">${Classroom.updateDateStr}</td>
				</tr>
		</table>
		
		
	</div>

</BODY>
<script type="text/javascript">
	 $(document).ready(function() {
		$("#class_dialog").omDialog({
			title : '查看',
			autoOpen : true,
			height : 'auto',
			width : 450,
			modal : true,
			resizable : false,
			onClose : function() {
				//销毁DIV
				$("#class_dialog").remove();
			},
			buttons : [{
				text : "关闭",
				id : 'detailClassroom_colse',
				click : function() {
					$("#class_dialog").omDialog('close');
				}
			} ]
		});

	$("#detailClassroom_colse").omButton({
			icons : {
				left : '${windowCloseIcons}'
			}
		});
	});
</script>
</HTML>
