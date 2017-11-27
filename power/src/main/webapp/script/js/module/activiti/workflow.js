var ctx = "";
/*
function graphTrace(options) {
    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid')
    };
    var opts = $.extend(true, _defaults, options);

    // 获取图片资源
    var imageUrl = ctx + "/workflow/myactiviti/resource/process-instance?pid=" + opts.pid + "&type=image";
    $.getJSON(ctx + '/workflow/myactiviti/process/trace?pid=' + opts.pid, function(infos) {

        var positionHtml = "";

        // 生成图片
        var varsArray = new Array();
        $.each(infos, function(i, v) {
            var $positionDiv = $('<div/>', {
                'class': 'activity-attr'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 2),
                height: (v.height - 2),
                backgroundColor: 'black',
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });

            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });

            if (v.currentActiviti) {
                $border.addClass('ui-corner-all-12').css({
                    border: '3px solid red'
                });
            }
            positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
            varsArray[varsArray.length] = v.vars;
        });

        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }

        // 设置每个节点的data
        $('#workflowTraceDialog .activity-attr').each(function(i, v) {
            $(this).data('vars', varsArray[i]);
        });

        // 打开对话框
        $('#workflowTraceDialog').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            open: function() {
                $('#workflowTraceDialog').css('padding', '0.2em');
                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);

                // 此处用于显示每个节点的信息，如果不需要可以删除
                $('.activity-attr').qtip({
                    content: function() {
                        var vars = $(this).data('vars');
                        var tipContent = "<table class='need-border'>";
                        $.each(vars, function(varKey, varValue) {
                            if (varValue) {
                                tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
                            }
                        });
                        tipContent += "</table>";
                        return tipContent;
                    },
                    position: {
                        at: 'bottom left',
                        adjust: {
                            x: 3
                        }
                    }
                });
                // end qtip
            },
            width: document.documentElement.clientWidth * 0.7,
            height: document.documentElement.clientHeight * 0.8
        });

    });
}
*/

//处理使用js跟踪当前节点坐标错乱问题
function activityProcessImg(pid){
	$("#div_for_workflow_img").html( "<img src='" + ctx + '/workflow/process/trace/auto/' + pid + "' />" );
	$("#div_for_workflow_img" ).omDialog({
		title:"工作流程图",
		autoOpen : true, 
		modal: true,
		width : document.documentElement.clientWidth * 0.8,
		height : document.documentElement.clientHeight * 0.8
	});
};
/**
 * 完成任务
 * @param {Object} taskId
 */
function complete(taskId, variables, url, callback) {
    
	// 转换JSON为字符串
    var keys = "", values = "", types = "";
	if (variables) {
		$.each(variables, function() {
			if (keys != "") {
				keys += "★▓◎╬";
				values += "★▓◎╬";
				types += "★▓◎╬";
			}
			keys += this.key;
			values += this.value;
			types += this.type;
		});
	}
	// 发送任务完成请求
    $.post(url + taskId, {
        keys: keys,
        values: values,
        types: types
    },function(data) {
    	if ($.isFunction(callback)) {
			callback(data);
		}
    },'json');
}