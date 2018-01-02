IframePost = function() {
    var setFrame = function(oFrm) {
        if (!oFrm.name || oFrm.name == "")
            oFrm.name = oFrm.id;
    },
    createForm = function(obj) {
        var oForm = document.createElement("form");
        oForm.id = "iframepost_forPost";
        oForm.method = "post";
        oForm.action = obj.url;
        oForm.target = obj.target.name;
        var oIpt, arrParams;
        arrParams = obj.params;
        for (var tmpName in arrParams) {
            oIpt = document.createElement("input");
            oIpt.type = "hidden";
            oIpt.name = tmpName;
            oIpt.value = arrParams[tmpName];
            oForm.appendChild(oIpt);
        }
        return oForm;
    },
	deleteForm = function() {
	    var oOldFrm = document.getElementById("iframepost_forPost");
	    if (oOldFrm) {
	        document.body.removeChild(oOldFrm);
	    }
	}
    return {
        //功能：给嵌套的Iframe界面Post值
        //参数：obj - 传递对象
        //     {url - 页面地址, target - Iframe对象, params - Post参数,{ 参数名1 : 值1, 参数名2 : 值2 } }
		 doPost: function(obj) {
            setFrame(obj.target);
            deleteForm();
            var oForm = createForm(obj);
            document.body.appendChild(oForm);
            oForm.submit();
            deleteForm();
        }
    }
}();