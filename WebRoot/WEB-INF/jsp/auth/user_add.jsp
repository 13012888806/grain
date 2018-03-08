<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户新增</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
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
		 * 保存新增航班信息
		 */
		function submitAddData() {
			if(!$("#addForm").form('validate')){
		      return false;
		    }
			var user = $$.serializeToJson("#addForm");
			if (!user)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+"/auth/user.do?method=insert",
			   dataType:"json",
			   data: user,
			   success: function(data){
			   	 $$.closeProcessingDialog();
			     if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info')
					$$.closeJcdfDialog();
					$$.refreshJcdfDatagrid("userDatagrid");
				 } else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				 }
			   }
			});
		}
	</script>
  </head>
  
  <body style="background-color: white;">
	   	<form action="#" id="addForm" style="display: inline;" class="tab">
	   		<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="bule" align="right" width="20%">
						用户账号：
					</td>
					<td align="left">
						<input type="text" name="userId" field="userId" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]','username']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						用户名称：
					</td>
					<td align="left">
						<input type="text" name="userName" field="userName" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']"/>
					</td>
				</tr>
				<tr>
					<td class="bule" align="right">
						密码：
					</td>
					<td align="left">
						默认为：<%=com.xinlin.app.util.StaticVar.DEFAULT_PASSWORD%>，新用户及时登录，自行修改。
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