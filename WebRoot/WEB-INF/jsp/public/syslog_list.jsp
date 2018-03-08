<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!-- script type="text/javascript" src="<c:url value='/javascript/sys/syslog_list.js'/>"></script-->
<%--<script type="text/javascript" src="../javascript/sys/syslog_list.js"></script>

--%><!DOCTYPE html>
<html>
  <head>
    <title>用户管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			
			<%--	
			//新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	$.openJcdfDialog(basePath+'/auth/user.do?method=forwardAddJsp', '用户新增', 280, 500);
		    });
		    
		     //修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = getSingleSelectRow("logDatagrid", '请选择一条记录进行操作！');
				if (null != selectRow) {
					$.openJcdfDialog(basePath+'/auth/user.do?method=forwardEditJsp&userId='+selectRow.userId, '用户修改', 280, 500);
				}
		    });
		     //删除按钮单击事件
		    $("#deleteButton").bind("click", function(){
		    	var deleteNotes = getSelectIds("logDatagrid", "请选择你要删除的记录！", "userId");
		    	if(deleteNotes){
		    		$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
		    	}
		    });
		     //授权按钮单击事件
		    $("#authButton").bind("click", function(){
		    	var selectRow = getSingleSelectRow("logDatagrid", '请选择一条记录进行操作！');
		    	if (selectRow) {
		    		if(1 == selectRow.userType) {
		    			$.showJcdfMessager('提示消息',"超级管理员不允许执行授权操作！",'info');
		    		} else {
		    			$.openJcdfDialog(basePath+'/auth/user.do?method=forwardAuthJsp&userId='+selectRow.userId, '用户授权', 600, 500);
		    		}
		    	}
		    });
		    
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $("#searchForm").serializeToJson();
				$('#logDatagrid').datagrid('options').queryParams=params;
				$('#logDatagrid').datagrid('load');
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				resetContent("searchForm");
			});--%>
		}) 
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$('#logDatagrid').datagrid({
				height:getDatagridHeight(),
				width:getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'syslog.do?method=pageQuery',
				idField:'logId',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'logId',title:'日志编号',width:fillsize(0.1),align:'center'},
					{field:'logIp',title:'操作人IP',width:fillsize(0.1),align:'center'},
					{field:'userId',title:'用户编号',width:fillsize(0.2),align:'center'},
					{field:'userName',title:'用户名称',width:fillsize(0.2),align:'center'},
					{field:'moduleName',title:'模块名称',width:fillsize(0.1),align:'center'},
					{field:'operatoContent',title:'操作明细',width:fillsize(0.1),align:'center'},
					{field:'operatorTime',title:'日志记录时间',width:fillsize(0.1),align:'center'}
					//{field:'operatorTime',title:'日志记录时间',width:fillsize(0.25),align:'center',formatter:function(v, row){
					//	if(1 == v) {
					//		return "<font color='red'>超级管理员</font>";
					//	} else {
					//		return "普通用户";
					//	}
					//}}
				]],
				onBeforeLoad:function(){clearSelect("logDatagrid");},
				onDblClickRow:function(rowIndex, rowData){
				},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				queryParams:{},
				toolbar:'#menu'
			});
		}
	
	</script>
  </head>
  
  <body>
    
    <!-- 数据展示列表查询区 -->
   <div id="menu" style="padding:1px;height:auto">
        <div style="margin-bottom:1px;" class="tab">
        	<form action="#" name="searchForm" id="searchForm" style="display: inline;">
        		<table border="0" cellspacing="0" cellpadding="0" width="100%" style="border:1px solid #52B64C;">
				  <tr>
				    <td width="13%" class="bule">用户账号：</td>
				    <td width="37%">
				    	<input name="userId" type="text" style="width:150px">
				    </td>
				    <td width="13%" class="bule">用户名称：</td>
				    <td>
				    	<input name="userName" type="text" style="width:150px">
				    </td>
				  </tr>
				  <tr>
				  	<td align="center" colspan="4">
				  		<a id="search_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				  		&nbsp;&nbsp;&nbsp;&nbsp;
				  		<a id="clear_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
				  	</td>
				  </tr>
			 	</table>
		     </form>
		     
		  <%--   <jcdf:auth code="010101">
		     	<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="010102">
		    	<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="010103">
			 	<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
			 <jcdf:auth code="010104">
				<a href="#" id="authButton" class="easyui-linkbutton" iconCls="icon-ok" plain="true">授权</a>
			 </jcdf:auth>
        --%></div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="logDatagrid" tagType="datagrid">
    	
    </table>
  </body>
</html>