<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>角色管理</title>
	<meta charset="UTF-8" />
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
			
			//新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	$$.openJcdfDialog(basePath+'/auth/role.do?method=forwardAddJsp', '角色新增', 280, 500);
		    });
		    
		     //修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("roleDatagrid", '请选择一条记录进行操作！');
				if (null != selectRow) {
					$$.openJcdfDialog(basePath+'/auth/role.do?method=forwardEditJsp&roleId='+selectRow.roleId, '角色修改', 280, 500);
				}
		    });
		     //删除按钮单击事件
		    $("#deleteButton").bind("click", function(){
		    	var deleteNotes = $$.getSelectIds("roleDatagrid", "请选择你要删除的记录！", "roleId");
		    	if(deleteNotes){
		    		$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteNoteById("'+deleteNotes.ids+'")');
		    	}
		    });
		     //授权按钮单击事件
		    $("#authButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("roleDatagrid", '请选择一条记录进行操作！');
		    	if (selectRow) {
		    		$$.openJcdfDialog(basePath+'/auth/role.do?method=forwardAuthJsp&roleId='+selectRow.roleId, '角色授权', 600, 500);
		    	}
		    });
		    
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#roleDatagrid').datagrid('options').queryParams=params;
				$('#roleDatagrid').datagrid('load');
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$('#roleDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:basePath+'/auth/role.do?method=pageQuery',
				idField:'roleId',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'roleName',title:'角色名称',width:$$.fillsize(0.4),align:'center'},
					{field:'roleMark',title:'角色备注',width:$$.fillsize(0.55),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("roleDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/**
		 *	删除所选记录
		 */
		function deleteNoteById(ids){
			$$.openProcessingDialog();
			$.post(basePath+'/auth/role.do?method=deleteById',{"roleIds":ids},function(data){
				$$.closeProcessingDialog();
				if (null != data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');	
					$$.refreshJcdfDatagrid("roleDatagrid");
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("roleDatagrid");
			},'json');
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
				    <td width="13%" class="bule">角色名称：</td>
				    <td>
				    	<input name="roleName" type="text" style="width:150px">
				    </td>
				  </tr>
				  <tr>
				  	<td align="center" colspan="2">
				  		<a id="search_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				  		&nbsp;&nbsp;&nbsp;&nbsp;
				  		<a id="clear_button" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">清空</a>
				  	</td>
				  </tr>
			 	</table>
		     </form>
		    <jcdf:auth code="010201">
				<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			</jcdf:auth>
			<jcdf:auth code="010202">
				<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			</jcdf:auth>
			<jcdf:auth code="010203">
				<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			</jcdf:auth>
			<jcdf:auth code="010204">
				<a href="#" id="authButton" class="easyui-linkbutton" iconCls="icon-ok" plain="true">授权</a>
			</jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="roleDatagrid" tagType="datagrid">
    	
    </table>
  </body>
</html>
