<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		var parentMenuName = "<%=java.net.URLDecoder.decode(request.getParameter("parentMenuName"), "utf-8")%>";
		var menuCode = "<%=java.net.URLDecoder.decode(request.getParameter("menuCode"), "utf-8")%>";
		$(function(){
			//加载修改数据
			loadEditData();
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	submitEditData();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	//重置新增窗口中的所有控件
				$$.resetContent("editForm");
				$("#editForm span[field='parentMenuName']").text(parentMenuName);
				//填充修改记录的历史数据
				$$.serializeToForm("#editForm", editHistoryData);
		    });
		})
		
		var editHistoryData = null;
		/**
		 * 加载航班历史数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/resource.do?method=getMenu",
			   dataType:"json",
			   data: {"menuCode":menuCode},
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (!data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						editHistoryData = data;
						$("#editForm span[field='parentMenuName']").text(parentMenuName);
						//填充修改记录的历史数据
						$$.serializeToForm("#editForm", editHistoryData);
					}
			   }
			});
		}
		
		/**
		 *	提交修改数据
		 */
		function submitEditData() {
		  	if(!$("#editForm").form('validate')){
		      return false;
		    }
		  	//获取表单数据
			var menu = $$.serializeToJson("#editForm");
			if (!menu)return false;
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/resource.do?method=updateMenu",
			   dataType:"json",
			   data: menu,
			   success: function(data){
			   		$$.closeProcessingDialog();
				    if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
						$$.closeJcdfDialog();
						//动态将新加的节点添加到树中
						$$.invokeParentMethod("afterMenuEditSuccess", menu["menuName"], menu["menuCode"]);
					} else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					}
				}
			});
		}
	</script>
  </head>

  <body style="background-color: white;">
	   	<form action="#" id="editForm" style="display: inline;" class="tab">
   			<table width="99%" border="0" cellspacing="0" cellpadding="0">
   				<tr>
   					<td class="bule" align="right" width="35%" id="parentMenuNameTxt">
   						父功能菜单：
   					</td>
   					<td align="left">
   						<span field="parentMenuName"></span>&nbsp;
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right" width="35%" id="menuCodeTxt">
   						功能菜单编码：
   					</td>
   					<td align="left">
   						<input type="text" name="menuCode" field="menuCode" class="easyui-validatebox" readonly="readonly" data-options="required:true,validType:['maxLength[25]']"/>
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right" id="menuNameTxt">
   						功能菜单名称：
   					</td>
   					<td align="left">
   						<input type="text" name="menuName" field="menuName" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']"/>
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right" id="menuMarkTxt">
   						功能菜单备注：
   					</td>
   					<td align="left">
   						<input type="text" name="menuMark" field="menuMark" class="easyui-validatebox" data-options="required:false,validType:['maxLength[100]']"/>
   					</td>
   				</tr>
   			</table>
   		</form>
	    
	    <div style="position: absolute;bottom:0px;right:0px;background-color: #F4F4F4;height: 40px;width: 100%;text-align: right;">
	   		<a id="addButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-top: 10px;">保存</a>
	   		<a id="resetButton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="margin-top:10px;">重置</a>
	   	</div>
  </body>
</html>