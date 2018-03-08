<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		var userId = "<%=request.getParameter("userId")%>";
		$(function(){
			//调整窗口高度自适应
			$("#authDiv").height($(document).height()-50);
			//加载资源权限树
			$('#resourceTree').tree({
			    url:'resource.do?method=loadAllTree&userId='+userId,
			    checkbox:true
			});
			//加载资源权限树
			$('#roleTree').tree({
			    url:'role.do?method=loadRoleTree&userId='+userId,
			    checkbox:true
			});
			
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	saveResourceAuthorize();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	$('#resourceTree').tree('reload');
				$('#roleTree').tree('reload');
		    });
		})
		
		/**
		 * 获取勾选的资源权限数据并保存
		 */
		function saveResourceAuthorize() {
			var checkedNodes = $('#resourceTree').tree('getChecked');
			var indeterminateNodes = $('#resourceTree').tree('getChecked', 'indeterminate');
			var menuCodes = "";
			//处理所有的选定项
			if (checkedNodes) {
				$.each(checkedNodes, function(i, node){
					if (menuCodes) {
						menuCodes = menuCodes + "," + node.id;
					} else {
						menuCodes = node.id;
					}
				});
			}
			//处理所有的不定选择项
			if (indeterminateNodes) {
				$.each(indeterminateNodes, function(i, node){
					if (menuCodes) {
						menuCodes = menuCodes + "," + node.id;
					} else {
						menuCodes = node.id;
					}
				});
			}
			//ajax提交数据
			$$.openProcessingDialog('开始保存资源权限......');
			$.ajax({
			   type: "POST",
			   url: "resource.do?method=insertUserOrRoleAuth",
			   dataType:"json",
			   data: {"menuCodes":menuCodes,"userRoleId":userId,"authType":2},
			   success: function(data){
			   	 $$.closeProcessingDialog();
			   	 saveRoleAuthorize(data.result);
			   }
			});
		}
		
		/**
		 * 获取勾选的角色权限数据并保存
		 */
		function saveRoleAuthorize(saveResourceAuthorizeResult) {
			$$.openProcessingDialog('开始保存角色权限......');
			var checkedNodes = $('#roleTree').tree('getChecked');
			var indeterminateNodes = $('#roleTree').tree('getChecked', 'indeterminate');
			var roleIds = "";
			//处理所有的选定项
			if (checkedNodes) {
				$.each(checkedNodes, function(i, node){
					if (roleIds) {
						roleIds = roleIds + "," + node.id;
					} else {
						roleIds = node.id;
					}
				});
			}
			//处理所有的不定选择项
			if (indeterminateNodes) {
				$.each(indeterminateNodes, function(i, node){
					if (roleIds) {
						roleIds = roleIds + "," + node.id;
					} else {
						roleIds = node.id;
					}
				});
			}
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: "role.do?method=insertRoleUserMap",
			   dataType:"json",
			   data: {"roleIds":roleIds,"userId":userId},
			   success: function(data){
			   	 $$.closeProcessingDialog();
			   	 if (data.result && saveResourceAuthorizeResult) {
			   	 	$$.showJcdfMessager('提示消息',"资源和角色权限都保存成功！",'info');
			   	 } else if (data.result && !saveResourceAuthorizeResult) {
			   	 	$$.showJcdfMessager('提示消息',"资源权限保存失败，角色权限保存成功！",'warning');
			   	 } else if (!data.result && saveResourceAuthorizeResult) {
			   	 	$$.showJcdfMessager('提示消息',"资源权限保存成功，角色权限保存失败！",'warning');
			   	 } else {
			   	 	$$.showJcdfMessager('提示消息',"资源和角色权限都保存失败！",'warning');
			   	 } 
			   }
			});
		}
	</script>
  </head>
  
  <body style="background-color: white;">
  		<div id="authDiv" style="overflow: auto;">
	   		<div id="tt" class="easyui-tabs">
			    <div title="资源授权">
			        <ul id="resourceTree"></ul>
			    </div>
			    <div title="分配角色" data-options="selected:true">
			        <ul id="roleTree"></ul>
			    </div>
			</div>
		</div>
	    
	    <div style="position: absolute;bottom:0px;right:0px;background-color: #F4F4F4;height: 40px;width: 100%;text-align: right;">
	   		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
	   		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	   	</div>
  </body>
</html>