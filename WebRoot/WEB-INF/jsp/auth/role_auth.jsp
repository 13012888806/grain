<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>角色授权</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		var roleId = "<%=request.getParameter("roleId")%>";
		$(function(){
			//调整窗口高度自适应
			$("#authDiv").height($(document).height()-50);
			//加载资源权限树
			$('#resourceTree').tree({
			    url:basePath+'/auth/resource.do?method=loadAllTree&roleId='+roleId,
			    checkbox:true
			});
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	authorize();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	$('#resourceTree').tree('reload');
		    });
		})
		
		/**
		 * 获取勾选的权限数据并保存
		 */
		function authorize() {
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
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/resource.do?method=insertUserOrRoleAuth",
			   dataType:"json",
			   data: {"menuCodes":menuCodes,"userRoleId":roleId,"authType":1},
			   success: function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			   }
			});
		}
	</script>
  </head>
  
  <body style="background-color: white;">
  		<div id="authDiv" style="overflow: auto;">
	   		<!-- 授权窗口页面 -->
		    <div id="authorizeDialog">
		    	<ul id="resourceTree"></ul>
		    </div>
		</div>
	    
	    <div style="position: absolute;bottom:0px;right:0px;background-color: #F4F4F4;height: 40px;width: 100%;text-align: right;">
	   		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
	   		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	   	</div>
  </body>
</html>