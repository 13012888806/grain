<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>用户登录</title>
	<meta charset="UTF-8" />
	<jsp:include page="head-ui-134.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/MD5+BASE64Encoder.js"></script>
	<script type="text/javascript">
		if (window != top)top.location.href = location.href;
		//为回车事件绑定自动登录业务处理
		$(function(){
			$(window).keydown(function(event){
				if (event.keyCode == 13) {
					login();
				}
			});
		})
		
		/**
         * 验证登陆表单，并提交登陆数据
         */
        function login() {
        	if ("" == $.trim($("#userId").val()) || "" == $.trim($("#passtemp").val())) {
        		alert("账号密码不能为空！");
        		return false;
        	}
        	$("#userPass").val(encodeURIComponent(b64_md5($("#passtemp").val())));
        	$("#loginForm").submit();
        }
	</script>
  </head>
  
  <body>
  	
  	<div class="tab" style="text-align: center;">
	  	<form id="loginForm" action="${pageContext.request.contextPath}/login.do" method="post">
		  	<table style="margin-top: 170px;width: 400px;margin-left: 34%;">
		  		<tr>
		  			<td align="right">账号</td>
		  			<td>
		  				<input name="userId" id="userId" type="text" style="width: 200px;height: 40px;" class="easyui-validatebox" data-options="required:true,delay:10"
		  					value="<%=null == request.getAttribute("userId") ? "" : request.getAttribute("userId")%>"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td align="right">密码</td>
		  			<td>
		  				<input id="passtemp" type="password" style="width: 200px;height: 40px;" class="easyui-validatebox" data-options="required:true" value=""/>
		  				<input name="userPass" type="hidden" id="userPass"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td align="right" colspan="2">
		  				<input type="button" style="width: 70px;height: 40px;" value="登录" onclick="login()"/>
		  			</td>
		  		</tr>
		  	</table>
	  	</form>
	  	<div style="text-align: center;color: red;">
    		<%=null == request.getAttribute("msg") ? "&nbsp;" : request.getAttribute("msg") %>
    	</div>
  	</div>
    
  </body>
</html>
