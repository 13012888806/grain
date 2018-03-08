<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>错误页面</title>
	<meta charset="UTF-8" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/plupload/jquery.min.js"></script>
  </head>
  <script type="text/javascript">
		var time = 0;
		var handler = function(){
			time = time + 1;
			$("#timeSpan").text("（"+time+"）");
			if(5 == time) {
				clearInterval(timer);
				top.location.href = '${pageContext.request.contextPath}/welcome.jsp';
			}
	    }
	    var timer = setInterval(handler , 1000);
	</script>
  <body>
  	
  	<div class="tab" style="text-align: center;">
		  	<table style="margin-top: 170px;margin-left: 10%;">
		  		<tr>
		  			<td align="center">
		  				<img src="${pageContext.request.contextPath}/images/error.png"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<div style="color: red;">
				    		<%=null == request.getAttribute("msg") ? "&nbsp;" : request.getAttribute("msg") %>
				    	</div>
				    	
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<div>
		  					系统<font color="red">5</font>秒<span id="timeSpan" style="font-size: 30px;color: red;"></span>后自动跳转到登录页面，点击”<a href="${pageContext.request.contextPath}/welcome.jsp">重新登录</a>”立即跳转。
				    	</div>
				    	
		  			</td>
		  		</tr>
		  	</table>
  	</div>
  </body>
</html>
