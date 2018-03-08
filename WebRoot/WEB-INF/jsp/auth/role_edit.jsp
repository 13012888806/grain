<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>角色修改</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
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
				//填充修改记录的历史数据
				$$.serializeToForm("#editForm", editHistoryData);
		    });
		})
		
		//从request域中获取roleId
		var roleId = "<%=request.getParameter("roleId")%>";
		var editHistoryData = null;
		/**
		 * 加载航班历史数据，用于修改
		 */
		function loadEditData() {
			$$.openProcessingDialog();
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/role.do?method=get",
			   dataType:"json",
			   data: {"roleId":roleId},
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (null == data || "" == data) {
						$$.showJcdfMessager('提示消息','数据加载失败!','warning');
					} else {
						editHistoryData = data;
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
			var role = $$.serializeToJson("#editForm");
			if (!role)return false;
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/role.do?method=update",
			   dataType:"json",
			   data: role,
			   success: function(data){
			   		$$.closeProcessingDialog();
				    if (data.result) {
				    	$.messager.alert('提示消息',data.msg,'info', function () {
							$$.closeJcdfDialog();
							$$.refreshJcdfDatagrid("roleDatagrid");
						});
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
    		<input type="hidden" name="roleId" field="roleId"/>
    		<table width="99%" border="0" cellspacing="0" cellpadding="0">
   				<tr>
   					<td class="bule" align="right" width="35%">
   						角色名称：
   					</td>
   					<td align="left">
   						<input type="text" name="roleName" field="roleName" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']"/>
   					</td>
   				</tr>
   				<tr>
   					<td class="bule" align="right">
   						角色备注：
   					</td>
   					<td align="left">
   						<input type="text" name="roleMark" field="roleMark" class="easyui-validatebox" data-options="required:false,validType:['maxLength[100]']"/>
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