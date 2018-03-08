//记录窗口中间内容展示区的高度，便于后面所欲iframe的统一控制
var centerHeight = 0;
//进入首页默认初始化tab
$(function(){
	centerHeight = $("#welcomeTab").parent().height()-4;
	$('#centerTab').tabs({
		cache:false,
		onLoad:function(panel){
			var plugin = panel.panel('options').title;
		},
		onClose:function() {
			return false;
		},
		onSelect:function(title,index) {
		}
	});
});
//以tab的形式打开一个模块
var index = 0;
function openNewTab(id){
	index = index+1;
	var title = $("#"+id).text();
	var href = $("#"+id).attr("link");
	if ($('#centerTab').tabs('exists',title)){
		$('#centerTab').tabs('select', title);
	} else {
		$('#centerTab').tabs('add',{
			id:id,
			title:title,
			content:'<iframe id="" name="iframe'+index+'" width="100%" height="'+centerHeight+'" src="'+href+'" frameborder="0" scrolling="auto" marginheight="0" marginwidth="0"></iframe>',
			closable:true
		});
	}
}
//打开对话框
var sonFrameName = null;
function jcdfDialog(frameName, href, title, maxHeight, maxWidth, widthRate) {
	sonFrameName = frameName;
	var dialogDiv = $("#jcdfDiglogDiv");
	if(!dialogDiv || dialogDiv.length <= 0) {
		var html = '<div id="jcdfDiglogDiv" style="display: none;">'+
			'<iframe id="jcdfDiglogDivIframe" name="" width="100%" height="200" src="" frameborder="0" scrolling="auto" marginheight="0" marginwidth="0"></iframe>'+
			'</div>';
		$("body").append(html);
	}
	$("#jcdfDiglogDivIframe").attr('src', href)
	$('#jcdfDiglogDiv').css('display','');
	$('#jcdfDiglogDiv').dialog({
		title:title,
		modal:true,
		maximizable:true,
		resizable:true,
		closed: false,
		onOpen:function() {
			$$.fillDialogWidthAndHeight("jcdfDiglogDiv", widthRate, maxHeight, maxWidth);
			$("#jcdfDiglogDivIframe").height($("#jcdfDiglogDivIframe").parent().height()-4);
		},
		onResize:function() {
			$("#jcdfDiglogDivIframe").height($("#jcdfDiglogDivIframe").parent().height()-4);
		},
		onClose:function() {
			$("#jcdfDiglogDivIframe").attr('src', 'about:blank');
		}
	});
}
//刷新
function refreshJcdfDatagrid(datagridId, type){
	window.frames[sonFrameName].$$.refreshJcdfDatagrid(datagridId);
	window.frames[sonFrameName].$$.flashTable(datagridId);
}
//关闭窗口
function closeJcdfDialog() {
	$('#jcdfDiglogDiv').dialog('close');
}
//弹出消息提示框
function showJcdfMessager(frameName, title, msg, icon, fn) {
	title = !title ? "提示消息" : title;
	msg = !msg ? "?":msg;
	icon = !icon ? "info":icon;
	$.messager.alert(title, msg, icon, function(){
		if(fn)eval('window.frames["'+frameName+'"].'+fn);
	});	
}
//弹出确认消息框
function showJcdfConfirm(frameName, title, msg, fn) {
	title = !title ? "确认" : title;
	msg = !msg ? "?" : msg;
	$.messager.confirm(title, msg, function(r){
		if (r && fn){
			eval('window.frames["'+frameName+'"].'+fn);
		}
	});
}
//回调函数
function invokeParentMethod(fn, args) {
	var argsStr = "";
	var methodStr = "";
	if (arguments.length > 1) {
		for (var i = 1; i < arguments.length; i=i+1) {
			argsStr = argsStr ? argsStr+',"'+arguments[i]+'"' : '"'+arguments[i]+'"';
		}
		if (argsStr) {
			methodStr = arguments[0]+"("+argsStr+")";
		} else {
			methodStr = methodStr[0];
		}
		eval('window.frames["'+sonFrameName+'"].'+methodStr);
	}
}

/**
 *	系统密码修改和退出功能控制
 */	
$(function(){
	//退出系统
	$("#logout").bind('click', function(){
		$.messager.confirm('提示消息', "确定退出?", function(r){
			if (r) {
				window.location = basePath+"/login.do?method=loginOut";
			}
		});
	});
	
	//修改密码
	$("#changePass").bind('click', function(){
		openChangePassDialog();
	})
});

/**
 *	弹出密码修改窗口
 */
function openChangePassDialog() {
	$('#changePassDialog').css('display','');
	$$.resetContent("changePassForm");
	$('#changePassDialog').dialog({
		title:'修改密码',
		modal:true,
		width:480,
		height:280,
		buttons:[{
			text:'修改',
			iconCls:'icon-ok',
			handler:function() {
				submitChangePass();
			}
		},{ 
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#changePassDialog').dialog('close');
			}
		}]
	});
}

/**
 * 保存新密码
 */
function submitChangePass() {
	if(!$("#changePassForm").form('validate')){
      return false;
    }
	var pass = $$.serializeToJson("#changePassForm");
	if (!pass)return false;
	pass['oldPass'] = encodeURIComponent(b64_md5(pass['oldPass']));
	pass['newPass'] = encodeURIComponent(b64_md5(pass['newPass']));
	pass['confirmNewPass'] = encodeURIComponent(b64_md5(pass['confirmNewPass']));
	//如果数据验证通过(即数据合法)
	$$.openProcessingDialog();
	//ajax提交数据
	$.ajax({type: "POST",url: "auth/user.do?method=changePass",cache:false,dataType:"json",data:pass,
	   success: function(data){
	   	 $$.closeProcessingDialog();
	     if (null != data && data.result) {
	     	$('#changePassDialog').dialog('close');
	     	alert(data.msg);
			window.location = basePath+"/login.do?method=loginOut";
		 } else {
			$.messager.alert('提示消息',data.msg,'warning');
		 }
	   }
	});
}