<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp"%>

<c:forEach items="${menuList}" var="pMenu">
	<div id="p_menu_${pMenu.menuid}" name="${pMenu.name}" class="p_menu">
		<c:forEach items="${pMenu.childMenus}" var="cMenu">
			<div id="c_menu_${cMenu.menuid}" url="${cMenu.url}" menuId="${cMenu.menuid}"
				class="c_menu">${cMenu.name}</div>
		</c:forEach>
	</div>
</c:forEach>


<script type="text/javascript">
	$(document).ready(function() {
		//tab被激活时，左边菜单栏对应项选中
		$('#center_tab').omTabs({
			onActivate : function(n, event) {
				var tabId = $('#center_tab').omTabs('getAlter', n);
				$(".c_menu").removeClass('left-color-active');
				$("#c_menu_" + tabId.substring(11)).parent().omPanel({
					collapsed : false
				}); //父菜单呈开启状态
				$("#c_menu_" + tabId.substring(11)).addClass('left-color-active');
			}
		});
		var left_num = 0;
		$(".p_menu").each(function() {
			var me = $(this);
			var collapsed_value = false;
			//默认打开第一个
			//if( left_num == 0 ){
			//	collapsed_value =false ;
			//	left_num++;
			//}
			me.omPanel({
				title : '<b>' + me.attr("name") + '</b>',
				collapsed : collapsed_value, //组件创建后为收起状态
				collapsible : true, //渲染收起与展开按钮
				// 面板收缩和展开的时候重新计算自定义滚动条是否显示
				onCollapse : function() {
					$("#west-panel").omScrollbar("refresh");
				},
				onExpand : function() {
					$("#west-panel").omScrollbar("refresh");
				}
			});
		});

		$(".c_menu").click(function() {
			var me = $(this);
			var me_url = me.attr("url");
			var me_menuId = me.attr("menuId");
			//获取页签的总数
			var total = $('#center_tab').omTabs('getLength');

			for (var i = 1; i < total; i++) {
				//获取第i个页签的tabId
				var tabId = $('#center_tab').omTabs('getAlter', i);
				if (tabId == 'center_tab_' + me_menuId) {
					$('#center_tab').omTabs('activate', i);
					return;
				}
			// 关闭其他的tab
			// $('#center_tab').omTabs('close', i);
			}

			//在第一个页签的位置新增一个页签,该页签的内容是远程数据
			$('#center_tab').omTabs('add', {
				index : total + 1,
				title : me.html(),
				tabId : 'center_tab_' + me_menuId,
				content : '1',
				url : '${contextPath}' + me_url,
				closable : true
			});

		});
	});
</script>
