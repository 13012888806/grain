<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>按钮资源新增</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		var parentMenuCode = "<%=java.net.URLDecoder.decode(request.getParameter("parentMenuCode"), "utf-8")%>";
		var parentMenuName = "<%=java.net.URLDecoder.decode(request.getParameter("parentMenuName"), "utf-8")%>";
		$(function(){
			//初始化部分文本域
			$("#addForm input[name='parentMenuCode']").val(parentMenuCode);
			$("#addForm span[field='parentMenuName']").text(parentMenuName);
			
			//保存按钮单击事件
		    $("#addButton").bind("click", function(){
		    	submitAddData();
		    });
		    //重置按钮单击事件
		    $("#resetButton").bind("click", function(){
		    	$$.resetContent("addForm");
		    });
		})
		
		/**
		 * 保存新增按钮资源信息
		 */
		function submitAddData() {
			if(!$("#addForm").form('validate')){
		      return false;
		    }
			var button = $$.serializeToJson("#addForm");
			if (!button)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/resource.do?method=insertMenu",
			   dataType:"json",
			   data: button,
			   success: function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info')
					$$.closeJcdfDialog();
					$$.refreshJcdfDatagrid("resourceDatagrid");
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			   }
			});
		}
	</script>
  </head>
  
  <body style="background-color: white;">
	   	<form action="#" id="addForm" style="display: inline;">
    		<input type="hidden" name="parentMenuCode" field="parentMenuCode"/>
    		<input type="hidden" name="menuType" field="menuType" value="2"/>
    		<table width="99%" border="0" cellspacing="0" cellpadding="0">
   				<tr>
   					<td class="bule" align="right" width="35%">
   						所属菜单：
   					</td>
   					<td align="left">
   						<span field="parentMenuName"></span>&nbsp;
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right" width="35%">
   						功能按钮编码：
   					</td>
   					<td align="left">
   						<input type="text" name="menuCode" field="menuCode" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']"/>
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right">
   						功能按钮名称：
   					</td>
   					<td align="left">
   						<input type="text" name="menuName" field="menuName" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']"/>
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right">
   						功能按钮备注：
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