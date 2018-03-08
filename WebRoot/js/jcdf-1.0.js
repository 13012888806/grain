jcdfObject = function() {
	/**
	 * 以下m和s json对象变量提前定义，服务于方法stringify
	 */
	var m = {'\b': '\\b','\t': '\\t','\n': '\\n','\f': '\\f','\r': '\\r','"' : '\\"','\\': '\\\\'};
    var s = {'boolean': function (x) {
                return String(x);
            },
            number: function (x) {
                return isFinite(x) ? String(x) : 'null';
            },
            string: function (x) {
                if (/["\\\x00-\x1f"]/.test(x)) {
                    x = x.replace(/(["\x00-\x1f\\"])/g, function(a, b) {
                        var c = m[b];
                        if (c) {
                            return c;
                        }
                        c = b.charCodeAt();
                        return '\\u00' + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
                    });
                }
                return '"' + x + '"';
            },
            object: function (x) {
                if (x) {
                    var a = [], b, f, i, l, v;
                    if (x instanceof Array) {
                        a[0] = '[';
                        l = x.length;
                        for (i = 0; i < l; i += 1) {
                            v = x[i];
                            f = s[typeof v];
                            if (f) {
                                v = f(v);
                                if (typeof v == 'string') {
                                    if (b) {
                                        a[a.length] = ',';
                                    }
                                    a[a.length] = v;
                                    b = true;
                                }
                            }
                        }
                        a[a.length] = ']';
                    } else if (x instanceof Object) {
                        a[0] = '{';
                        for (i in x) {
                            v = x[i];
                            f = s[typeof v];
                            if (f) {
                                v = f(v);
                                if (typeof v == 'string') {
                                    if (b) {
                                        a[a.length] = ',';
                                    }
                                    a.push(s.string(i), ':', v);
                                    b = true;
                                }
                            }
                        }
                        a[a.length] = '}';
                    } else {
                        return;
                    }
                    return a.join('');
                }
                return 'null';
            }
        };
    return {
    	/**
    	 *	将指定的json对象解析成字符串
    	 *	
    	 *	@param	v	将要被解析的json对象
    	 *
    	 *	@return json解析后的字符串
    	 */
    	stringify:function (v) {
	         var f = s[typeof v];
	         if (f) {
	             v = f(v);
	             if (typeof v == 'string') {
	                 return v;
	             }
	         }
	         return null;
	    },
	    /**
		 *	获取字符串的真实长度(一个中文占两个长度，英文或者数字占一个长度)
		 *
		 *	@param	str		获取实际长度的目标字符串
		 *
		 *	@return		目标字符串的实际长度
		 */
	    getTrueLenth:function(str) {
	    	return str.replace(/[^\x00-\xff]/g,"xx").length;
	    },
	    /**
		 *	重置指定表单中的内容
		 *
		 *	@param 	formId	将要清空内容的表单的id
		 *	@param	isClearHiddenInput	是否清空hidden隐藏域中的值，true：清空，false：不清空，不指定默认不清空
		 *
		 *	@return undefined	
		 */
	    resetContent:function(formId, isClearHiddenInput) {
	    	var clearForm = document.getElementById(formId);
			if (clearForm)clearForm.reset();
			if (isClearHiddenInput)$("#"+formId+" :input[type='hidden']").val("");
	    },
	    /**
		 *	取消选择(用于Easyui中的dataGrid)，囊括了easyui中的clearSelections和uncheckAll
		 *	为减少实际开发的代码，和后期维护的统一管理，建议采用该方法，而不直接操作easyui的对应方法
		 *
		 *	@param	dataTableId	将要取消所选数据记录的目标datagrid id
		 *
		 *	@return undefined
		 */
	    clearSelect:function(dataTableId) {
	    	$("#"+dataTableId).datagrid('clearSelections');
			$("#"+dataTableId).datagrid('uncheckAll');
	    },
	    /**
		 *	自适应表格的宽度处理(适用于Jquery Easy Ui中的dataGrid),
		 *	可以实现列表的各列宽度跟着浏览宽度的变化而变化。
		 *
		 *	@param	percent	当前列的列宽所占整个窗口宽度的百分比(以小数形式出现，如0.3代表30%)
		 *  @param	bodyWidth	总宽度，不提供，则默认采用当前页面宽度
		 *
		 *	@return	通过当前窗口和对应的百分比计算出来的具体宽度值
		 */
	    fillsize:function(percent,bodyWidth) {
	    	if(!bodyWidth) {
				bodyWidth = $(document).width();
			} else {
				bodyWidth = bodyWidth;
			}
			return parseInt(bodyWidth*percent);
	    },
	    /**
		 *	获取数据列表的总宽度
		 *
		 *	@param	minWidth	指定最小宽度，确保最后返回的结果一定大于等于该值，不指定的不考虑
		 *
		 *	@return	如果计算出的当前窗口总宽度小于minWidth，则返回minWidth，否则返回当前窗口总宽度
		 */
	    getDatagridWidth:function(minWidth){
	    	if(!minWidth)minWidth = 0;
			var width = ($(document).width()-1);
			return (minWidth > width) ? minWidth:width;
	    },
	    /**
		 *	获取数据列表的高度
		 *
		 *	@return	当前窗口的高度
		 */
	    getDatagridHeight:function(){
	    	return ($(document).height())
	    },
	    /**
		 * 获取所选记录的id
		 *
		 * @param	dataTableId	目标记录所在的列表table的id
		 * @param	errorMessage 错误的提示信息
		 * @param	type	getChecked或者getSelections，默认getChecked
		 * 
		 * @return 所选记录js对象
		 */
	    getSingleSelectRow:function(dataTableId, errorMessage, type){
	    	if (!type)type = "getChecked";
			var rows = $('#'+dataTableId).datagrid(type);
			if(rows && 1 == rows.length){
				return rows[0];
			}else{
				this.showJcdfMessager('提示消息',errorMessage,'warning');
				return null;
			}
	    },
	    /**
		 * 获取所选记录的id,多个id用逗号分隔
		 *
		 * @param	dataTableId	目标记录所在的列表table的id
		 * @param	idField	主键字段名
		 * @param	noOneSelectMessage	如果没有选择记录，或者指定必需选择一条而选择了多条时的提示信息
		 * @param	type	getChecked或者getSelections，默认getChecked
		 *
		 * @return 所选记录的id字符串(多个id用逗号隔开)和所选记录数的json对象,如果没有选择记录则提示消息，并返回null
		 */
	    getSelectIds:function(dataTableId, noOneSelectMessage, idField, type) {
	    	if (!type)type = "getChecked"; 
	    	var rows = $('#'+dataTableId).datagrid('getChecked');
			var num = rows.length;
			var ids = null;
			if(num  < 1){
				if (noOneSelectMessage)this.showJcdfMessager('提示消息',noOneSelectMessage,'warning');
				return null;
			}else{
				for(var i = 0; i < num; i=i+1){
					if(null == ids || i == 0){
						ids = rows[i][idField];
					} else {
						ids = ids + "," + rows[i][idField];
					}
				}
				return {"ids":ids,"num":num};
			}
	    },
	    /**
		 * 打开系统操作进度条窗口
		 *
		 * @param	processingMessage	显示在操作进度条窗口上的提示信息,不提供则使用默认值
		 * @param	closable	true：窗口可以关闭，false：窗口不可以关闭
		 * @param	modal	true:有模式窗口，false：无模式窗口，不指定默认为false
		 *
		 * @return undefined
		 */
	    openProcessingDialog:function(processingMessage, closable, modal) {
	    	if (!closable)closable=false;
			if (!modal)modal=true;
			//如果提供了进度条信息，则采用提供的，否则采用默认的！
			if (!processingMessage)processingMessage="正在处理，请稍候......";
			var processingDialogObject = document.getElementById("processingDialog");
			//如果不存在处理进度条窗口则创建
			if(!$("#processingDialog") || $("#processingDialog").length <= 0) {
				var html = '<div id="processingDialog" modal="'+modal+'" closable="'+closable+'" icon="icon-save" style="overflow-y:hidden;padding:5px;width:350px;height:100px;text-align:center">'
								+'<div id="processingDiv"><img src="'+basePath+'/images/loading.gif"/><font color="green" id="processingMessage"></font></div>'
						+'</div>';
				$("body").append(html);
			}
			$("#processingMessage").html(processingMessage);
			$("#processingDialog").dialog({
				title:"操作中...",
				onOpen:function() {
					$('#processingDialog').css('display','');
				}		
			});
	    },
	    /**
		 * 关闭系统操作进度条窗口
		 *
		 * @return undefined
		 */
	    closeProcessingDialog:function() {
	    	$('#processingDialog').dialog('close');
	    },
	    /**
		 * 自动调整弹出窗口的长和宽,以适应不同分辨率浏览器下的显示
		 *
		 * @param	dialogId	弹出窗口的id
		 * @param	widthRate	弹出窗口的宽度与浏览器所能提供的宽度(通常为弹出窗口所子页面对应的Iframe的宽度)的比例,在不同分辨率下使用该比率自动调整
		 * @param	maxHeight	弹出窗口的最大高度
		 * @param	maxWidth	弹出窗口的最大宽度
		 *
		 * @param	自动调节后的窗口宽度
		 */
	    fillDialogWidthAndHeight:function(dialogId, widthRate, maxHeight, maxWidth) {
	    	var currentBodyHeight = $(document).height();
			var currentBodyWidth = $(document).width();
			//当前iframe窗口的高宽比
			var heightToWidthRate =  currentBodyHeight/currentBodyWidth;
			var fillWidth = currentBodyWidth*widthRate;
			var fillHeight = fillWidth * heightToWidthRate;
			//如果当前iframe窗口按百分比计算出的宽度大于实际设置的最大值，则以最大值为准
			if (fillWidth >= maxWidth) {
				fillWidth = maxWidth;
			//如果计算出的宽度小于最大值则进一步调整
			} else {
				//如果当前窗口的宽度的95%大于设置的最大值，则自动调整到最大值，否则就取当前窗口的95%作为宽度
				if ((currentBodyWidth * (95/100)) > maxWidth) {
					fillWidth = maxWidth;
				} else {
					fillWidth = currentBodyWidth * (95/100);
				}
			}
			//如果当前iframe窗口按百分比计算出的高度值大于实际设置的最大值，则以最大值为准，否则进一步调整的方法与以上的宽度调整相同
			if (fillHeight >= maxHeight) {
				fillHeight = maxHeight;
			}
			//计算窗口左上角的坐标，使窗口居中
			var leftPos = (currentBodyWidth - fillWidth)/2;
			var topPos = (currentBodyHeight - fillHeight)/2;
			$('#'+dialogId).dialog("options").width=fillWidth;
			$('#'+dialogId).dialog("options").height=fillHeight;
			$('#'+dialogId).dialog("resize",{width:fillWidth,height:fillHeight,left:leftPos,top:topPos});
			return fillWidth;
	    },
	    /**
		 *	日期格式化函数，支持模式：YYYY/yyyy年MM月dd日hh小时mm分ss秒SSS毫秒
		 *
		 *	@param	date	日期，不指定，则取当前时间
		 *	@param	format	格式模式字符串
		 *
		 *	@reurn	格式化后的日期字符串
		 */
	    dateFormat:function(dateObject, format) {
	    	if (!dateObject)dateObject = new Date();
	    	var o = { 
				"M+" : dateObject.getMonth()+1, //month 
				"d+" : dateObject.getDate(), //day 
				"h+" : dateObject.getHours(), //hour 
				"m+" : dateObject.getMinutes(), //minute 
				"s+" : dateObject.getSeconds(), //second 
				"q+" : Math.floor((dateObject.getMonth()+3)/3), //quarter 
				"S" : dateObject.getMilliseconds() //millisecond 
			} 
			if(/(y+)/.test(format)) { 
				format = format.replace(RegExp.$1, (dateObject.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			} 
			for(var k in o) { 
				if(new RegExp("("+ k +")").test(format)) { 
					format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
				} 
			} 
			return format; 
	    },
	    /**
		 * 格式化表单内容为json字符串
		 *
		 *	@param	selector	容器筛选器
		 *	@param	notEmptyField	true：过滤掉内容为空的字段，否则空字段保留
		 *
		 *	@return	表单内容格式化后的json对象	
		 */
	    serializeToJson:function(selector, notEmptyField) {
	    	var obj = {};
		    $.each( $(selector).serializeArray(), function(i,o){
		        var n = o.name, v = $.trim(o.value);
		        if (!(notEmptyField && "" == v)) {
		        	obj[n] = (obj[n] === undefined) ? v : $.isArray(obj[n]) ? obj[n].concat(v) : [obj[n], v];
		        }
		    });
		    return obj;
	    },
	    /**
		 *	将json中的值填充到页面中，按field域进行填充
		 *
		 *	@param	selector	容器筛选器
		 *	@param	jsonObject	用于填充的json数据对象
		 *	@param	type	填充数据的方式，text：将数据填充到text域中，否则填充到value域中
		 *
		 *	@return	undefined	
		 */
	    serializeToForm:function(selector, jsonObject, type) {
	    	if(!jsonObject)return false;
		     for(var key in jsonObject) {
		     	var inputObj = $(selector).find("[field='"+key+"']");
		     	if (inputObj && inputObj.size() > 0 && null != jsonObject[key] && "null" != jsonObject[key]) {
		     		if (type && 'text'== type) {
		     			inputObj.text(jsonObject[key]);
		     		} else {
		     			inputObj.val(jsonObject[key]);
		     		}
		     	}
		     }
	    },
	    /**
		 *	列表数据导出下载
		 * 
		 *	@param queryWhereSelector	查询条件所在的父元素查找字符串(支持jquery的所有筛选通配符)
		 *	@param mapperId	后台配置的对应查询业务id
		 *	@param queryWhereJson	查询条件(json字符串)，与queryWhereSelector不可同时存在，如果同时存在则queryWhereJson优先
		 *
		 *	@return	undefined
		 */
	    exportDataToFile:function(queryWhereSelector, mapperId, queryWhereJson) {
	    	if(!queryWhereJson && queryWhereSelector) {
				queryWhereJson = $(queryWhereSelector).serializeToJson()
			}
			$.post(basePath+"/export.do?mapperId="+mapperId,queryWhereJson ? queryWhereJson:{},function(data){
				if (!data.result) {
					this.showJcdfMessager('提示消息',data.msg,'warning');
				} else {
					if(!$("#downloadIframe11") || $("#downloadIframe").length <= 0) {
						$("body").append('<iframe id="downloadIframe" frameborder="0" height="0" width="0"></iframe>');
					}
					$("#downloadIframe").attr('src', basePath+"/export.do?mapperId="+mapperId+"&fileTempName="+data.msg);
					$("#downloadIframe").hide();
				}
			},'json');
	    },
	    /**
		 *	弹出操作窗口，框架中所有弹窗中的内容都必需是一个单独的页面，通过url指定
		 *
		 *	@param	href	弹窗中将要显示的页面
		 *	@param	title	弹窗标题
		 *	@param	maxHeight	弹窗最大高度，如果超过浏览器窗口高度，则按照浏览器窗口的高宽比自动调节缩小
		 *	@param	maxWidht	弹窗最大宽度	如果超过浏览器窗口的宽度，则按照参数widthRate调整
		 *	@param	widthRate	窗口宽度与浏览器宽度百分比，框架会按照该比调整弹窗宽度到最接近maxWidht
		 *	
		 */
		openJcdfDialog:function(href, title, maxHeight, maxWidth, widthRate) {
			if (!widthRate) {
				widthRate = 0.98;
			}
			window.top.jcdfDialog(window.self.name, href, title, maxHeight, maxWidth, widthRate);
		},
		/**
		 *	刷新数据列表，如果采用了JCDF框架的弹窗，就必需采用JCDF的该方法进行列表刷新，因为JCDF的弹窗页面
		 *	与列表页面在两个iframe页面中，无法进行js直接调用。
		 *
		 *	@param	datagridId	将要属性的datagrid的id
		 */
		refreshJcdfDatagrid:function(datagridId, type) {
			//如果列表页面就在当前页面，则直接刷新，否则需要跨iframe进行列表刷新
			if ($("#"+datagridId).length > 0) {
				if ("queryAll" == type) {
					$('#'+datagridId).datagrid('options').queryParams="{}";
				}
				$('#'+datagridId).datagrid('uncheckAll');
				$('#'+datagridId).datagrid('unselectAll');
				$('#'+datagridId).datagrid('reload');
			} else {
				window.top.refreshJcdfDatagrid(datagridId, type);
			}
		},
		/**
		 *	窗口关闭，如果采用了JCDF框架的弹窗，就必须采用JCDF的该关闭方法
		 */
		closeJcdfDialog:function() {
			window.top.closeJcdfDialog();
		},
		/**
		 *	消息提示框，相关参数与Easyui的“$.messager.alert”保持一致，具体含义参考
		 *	Easyui的官方文档。
		 *	需要注意的是：这里的fn只能穿一个方法的名称和实参(如果有)，不允许包含方法的定义部分，
		 *	所有实际使用时，先定义好回调方法，然后这里直接传入方法名与实参的字符串表示，例如：
		 *	'deleteNoteById("'+deleteNotes.ids+'")'
		 */
		showJcdfMessager:function(title, msg, icon, fn) {
			window.top.showJcdfMessager(window.self.name, title, msg, icon, fn);
		},
		/**
		 *	操作确认框，相关参数与Easyui的“$.messager.confirm”保持一致，具体含义参考
		 *	Easyui的官方文档。
		 *	fn的注意事项与showJcdfMessager一样。
		 */
		showJcdfConfirm:function(title, msg, fn){
			window.top.showJcdfConfirm(window.self.name, title, msg, fn);
		},
		/**
		 *	弹出窗口中回调父窗口中的方法，由于弹出窗口和对应的列表页面一般都在两个
		 *	iframe页面中，无法直接进行js交互操作，这里提供回调方法，这样确保弹出中
		 *	可以直接调用父页面的方法进行业务的交互处理
		 *	
		 *	@param	fn	父窗口中对应方法名和实参的字符串表示
		 *	@param	fn	args 参数，个数不限制
		 *
		 *	@return	方法执行完的返回值,如果没有指定方法名则返回null
		 */
		invokeParentMethod:function(fn, args) {
			var methodStr = "";
			if (arguments.length > 0) {
				for (var i = 0; i < arguments.length; i=i+1) {
					methodStr = methodStr ? methodStr+',"'+arguments[i]+'"' : '"'+arguments[i]+'"';
				}
				return eval('window.top.invokeParentMethod('+methodStr+')');
			}
			return null;
		}
   };
}

/**
 *	全局监控ajax请求，处理session无效、无权限操作时，自动跳转到登录页面
 */
$(function(){
	$.ajaxSetup({
       cache : false,
       global : true,
       complete: function(req, status) {
       try{
           var reqObj = eval('('+req.responseText+")");
           //如果数据请求验证时，当前登录状态已经失效或者对应的请求资源(路径)没有权限
           if(reqObj && (reqObj.noLogin ||reqObj.noRight)){
           	   alert(reqObj.msg);
               window.location = basePath + '/index.jsp';
           }
         }catch(e){}
       }
   });
});

/**
 * 自动调节页面datagrid的宽度(当页面宽度变化时，自动调整以适应页面的变化)，当前仅支持一个页面仅有一个datagrid的应用
 * 使用方法：页面引入common.js，然后在datagrid标签上加属性：tagType="datagrid"即可
 * 
 */
var oldBodyWidth = 0;
var oldDatagridWidth = 0;
$(function(){
	oldBodyWidth = document.body.clientWidth;
	$(window).resize(function(){
	  	$.each($("[tagType='datagrid']"), function(i, v){
	  		try {
		  		var datagridId = $(this).attr("id");
		  		if (0 == oldDatagridWidth) {
			  		oldDatagridWidth = $('#'+datagridId).datagrid('options').width;
			  	}
				$("#"+datagridId).datagrid('resize',{width:(oldDatagridWidth+(document.body.clientWidth-oldBodyWidth))});
			} catch (ex) {
				
			}
	  	});
	});
});


/**
 *	初始化省
 *
 * @param	provinceSelectId	
 */
function city_initProvince(provinceSelectId) {
 	for(p=0;p<jsCity.length;p++){
		document.getElementById(provinceSelectId).add(new Option($.trim(jsCity[p][1]), $.trim(jsCity[p][2])));
	}
}

/**
 * 根据所选择的省，级联产生对应的市	
 *
 * @param	obj
 * @param	citydata
 * @param	selecttext
 * @param	objid
 */
function city_providerchage(currentObjId,selecttext,objid){
	var pcode = document.getElementById(currentObjId).value;
	var select = document.getElementById(objid);
	var temp;
	document.getElementById(objid).options.length = 0;
	document.getElementById(objid).add(new Option(selecttext));
	for(p=0;p<jsCity.length;p++){
		if(pcode == jsCity[p][2]){
			temp = jsCity[p][3];
			break;
		}
	}
	if (temp && temp.length > 0) {
		for(t=0;t<temp.length;t++){
			select.add(new Option($.trim(temp[t][1]), $.trim(temp[t][2])));
		}
	}
}

/**
 * 根据市查询对应的县、区
 * @param currentObjIdCity
 * @param selecttext
 * @param objid
 * @return
 */
function county_providerchage(currentObjIdCity,selecttext,objid){
	var citycode = document.getElementById(currentObjIdCity).value;
	var select = document.getElementById(objid);
	var temp;
	document.getElementById(objid).options.length = 0;
	document.getElementById(objid).add(new Option(selecttext));
	for(p=0;p<jsCity.length;p++){
		var arrCounty = jsCity[p][3];
		for(i=0;i<arrCounty.length;i++){
			if(citycode == arrCounty[i][2]){
				temp = arrCounty[i][3];
				break;
			}
		}
	}
	if (temp && temp.length > 0) {
		for(t=0;t<temp.length;t++){
			select.add(new Option(temp[t][1], temp[t][2]));
		}
	}
}

var jcdf = new jcdfObject();
var $$ = jcdf;