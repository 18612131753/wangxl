	var createAjax = function() {
	    var xhr = null;
	    try {
	        xhr = new ActiveXObject("microsoft.xmlhttp");
	    } catch (e1) {
	        try {
	            xhr = new XMLHttpRequest();
	        } catch (e2) {
	            window.alert("您的浏览器不支持ajax，请更换！");
	        }
	    }
	    return xhr;
	};
	var ajax = function(conf) {
	    var type = conf.type;
	    var url = conf.url;
	    var data = conf.data;
	    var dataType = conf.dataType;
	    var success = conf.success;
	    var failure = conf.failure;
	    var async = conf.async;                                                                                     
	    if (type == null){
	        type = "get";
	    }
	    if (dataType == null){
	        dataType = "text";
	    }
	    var xhr = createAjax();
	    xhr.open(type, url, false);
	    if (type == "GET" || type == "get") {
	        xhr.send(null);
	    } else if (type == "POST" || type == "post") {
	        xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	        xhr.send(data);
	    }
	    if(async){
	    	xhr.onreadystatechange = function() {
		        if (xhr.readyState == 4 && xhr.status == 200) {
		            if(dataType == "text"||dataType=="TEXT") {
		                if (success != null){
		                    success(xhr.responseText);
		                }
		            }else if(dataType=="xml"||dataType=="XML") {
		                if (success != null){
		                    success(xhr.responseXML);
		                }  
		            }else if(dataType=="json"||dataType=="JSON") {
		                if (success != null){
		                    success(eval("("+xhr.responseText+")"));
		                }
		            }
		        }else{
		        	failure(xhr);
		        }
	    	};
	    }else{

	    	if (xhr.readyState == 4 && xhr.status == 200) {
		            if(dataType == "text"||dataType=="TEXT") {
		                if (success != null){
		                    success(xhr.responseText);
		                }
		            }else if(dataType=="xml"||dataType=="XML") {
		                if (success != null){
		                    success(xhr.responseXML);
		                }  
		            }else if(dataType=="json"||dataType=="JSON") {
		                if (success != null){
		                    success(eval("("+xhr.responseText+")"));
		                }
		            }
		      }else{
	        	 failure(xhr);
	          }
	    }
	    
	};