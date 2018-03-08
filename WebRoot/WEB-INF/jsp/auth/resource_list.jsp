<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>资源管理</title>
	<meta charset="UTF-8" />
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			//加载资源权限树
			$('#resourceTree').tree({
			    url:'resource.do?method=loadMenuTree',
			    onClick:function(node){
			    	dblClickNode = node;
			    	loadOrInitButtonResourceDatagrid();
			    }
			});
			//初始化按钮资源管理列表
			loadOrInitButtonResourceDatagrid();
			
			//“菜单资源”新增按钮单击事件
		    $("#addMenuButton").bind("click", function(){
		    	var node = $('#resourceTree').tree('getSelected');
		    	var parentMenuCode = node ? encodeURI(encodeURI(node.id)) : "";
		    	var parentMenuName = node ? encodeURI(encodeURI(node.text)) : "";
		    	$$.openJcdfDialog(basePath+'/auth/resource.do?method=forwardMenuAddJsp&parentMenuCode='+parentMenuCode+'&parentMenuName='+parentMenuName, '菜单资源新增', 300, 500);
		    });
			//“按钮资源”新增按钮单击事件
		    $("#addButton").bind("click", function(){
		    	var node = $('#resourceTree').tree('getSelected');
				if (!node) {
					$$.showJcdfMessager('提示消息','	请先在右侧菜单树中选择“所属菜单”，然后再进行该操作!','warning');
					return false;
				}
		    	var parentMenuCode = node ? encodeURI(encodeURI(node.id)) : "";
		    	var parentMenuName = node ? encodeURI(encodeURI(node.text)) : "";
		    	$$.openJcdfDialog(basePath+'/auth/resource.do?method=forwardButtonAddJsp&parentMenuCode='+parentMenuCode+'&parentMenuName='+parentMenuName, '按钮资源新增', 300, 500);
		    });
		    
		    //“菜单资源”修改按钮单击事件
		    $("#editMenuButton").bind("click", function(){
		    	var node = $('#resourceTree').tree('getSelected');
				if (!node) {
					$$.showJcdfMessager('提示消息','	请选择你要修改的功能节点!','warning');
					return false;
				} else {
					var parentNode = $('#resourceTree').tree('getParent',node.target);
					var parentMenuName = parentNode ? encodeURI(encodeURI(parentNode.text)) : "";
					var menuCode = encodeURI(encodeURI(node.id));
					$$.openJcdfDialog(basePath+'/auth/resource.do?method=forwardMenuEditJsp&menuCode='+menuCode+'&parentMenuName='+parentMenuName, '菜单资源修改', 280, 500);
				}
		    });
		    //“按钮资源”修改按钮单击事件
		    $("#editButton").bind("click", function(){
		    	var selectRow = $$.getSingleSelectRow("resourceDatagrid", '请选择一条记录进行操作！');
		    	var node = $('#resourceTree').tree('getSelected');
				if (null != selectRow) { 
					var parentMenuName = node ? encodeURI(encodeURI(node.text)) : "";
					var menuCode = encodeURI(encodeURI(selectRow.menuCode));
					$$.openJcdfDialog(basePath+'/auth/resource.do?method=forwardButtonEditJsp&menuCode='+menuCode+'&parentMenuName='+parentMenuName, '按钮资源修改', 280, 500);
				}
		    });
		    
		    //“菜单资源”删除按钮单击事件
		    $("#deleteMenuButton").bind("click", function(){
		    	var node = $('#resourceTree').tree('getSelected');
				if (!node) {
					$$.showJcdfMessager('提示消息','	请选择你要删除的功能节点!','warning');
					return false;
				} else if (!$('#resourceTree').tree('isLeaf', node.target)) {
					$$.showJcdfMessager('提示消息','	该功能节点有子节点，不允许删除，请选择叶子节点删除!','warning');
					return false;
				} else {
					$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteMenuNoteById("'+node.id+'")');
				}
		    });
		    //“按钮资源”删除按钮单击事件
		    $("#deleteButton").bind("click", function(){
		    	var deleteNotes = $$.getSelectIds("resourceDatagrid", "请选择你要删除的记录！", "menuCode");
				if (deleteNotes) { 
					$$.showJcdfConfirm("确认", "确定删除所选记录?", 'deleteButtonNoteById("'+deleteNotes.ids+'")');
				}
		    });
		    
		    //设置树所在的div的高度，超过高度则自动产生滚动条
		    $("#resoureTreeDiv").css("height", $$.getDatagridHeight());
		})
		
		/**
		 *	初始化或者刷新按钮资源列表
		 */
		var isInitDatagrid = false;
		function loadOrInitButtonResourceDatagrid() {
			if (isInitDatagrid) {
				$('#addButton').linkbutton('enable');
				$('#editButton').linkbutton('enable');
				$('#deleteButton').linkbutton('enable');
				$('#resourceDatagrid').datagrid('options').queryParams.parentMenuCode=dblClickNode.id;
				$('#resourceDatagrid').datagrid('load');
				return;
			}
			isInitDatagrid = true;
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			$('#resourceDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth()-300,
				nowrap: true,
				striped: true,
				url: basePath+'/auth/resource.do?method=pageQuery',
				idField:'menuCode',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'menuCode',title:'功能按钮编码',width:$$.fillsize(0.2,$$.getDatagridWidth()-300),align:'center'},
					{field:'menuName',title:'功能按钮名称',width:$$.fillsize(0.2,$$.getDatagridWidth()-300),align:'center'},
					{field:'menuMark',title:'功能按钮备注',width:$$.fillsize(0.5,$$.getDatagridWidth()-300),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("resourceDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				queryParams:{"parentMenuCode":"","menuType":2},
				toolbar:'#menu'
			});
		}
		
		function afterMenuAddSuccess(menuName, menuCode) {
			var parentNode = $('#resourceTree').tree('getSelected');
			//选择了父节点，则加到对应的父节点下面
			if (parentNode) {
				$('#resourceTree').tree('append', {
					parent: parentNode.target,
					data: [{
						id: menuCode,
						text: menuName + "(" + menuCode + ")"
					}]
				});
			//没有选择父节点，则作为根节点
			} else {
				$('#resourceTree').tree('append', {
					data: [{
						id: menuCode,
						text: menuName + "(" + menuCode + ")"
					}]
				});
			}
		}
		
		function afterMenuEditSuccess(menuName, menuCode) {
			var node = $('#resourceTree').tree('getSelected');
			//选择节点，修改节点信息
			if (node) {
				$('#resourceTree').tree('update', {
					target: node.target,
					id:menuCode,
					text: menuName+"("+menuCode+")"
				});
			}
		}
		
		/**
		 *	删除所选记录
		 */
		function deleteMenuNoteById(menuCodes){
			$$.openProcessingDialog();
			$.post("resource.do?method=deleteMenuById",{"menuCodes":menuCodes},function(data){
				$$.closeProcessingDialog();
				if (null != data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');	
					var node = $('#resourceTree').tree('getSelected');
					if(node)$('#resourceTree').tree('remove', node.target)
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("resourceDatagrid");
			},'json');
		}
		
		/**
		 *	删除所选记录
		 */
		function deleteButtonNoteById(menuCodes){
			$$.openProcessingDialog();
			$.post("resource.do?method=deleteMenuById",{"menuCodes":menuCodes},function(data){
				$$.closeProcessingDialog();
				if (null != data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');	
					$$.refreshJcdfDatagrid("resourceDatagrid");
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("resourceDatagrid");
			},'json');
		}
	</script>
  </head>
  
  <body>
    
    <!-- 列表菜单区 -->
   <div id="menu" style="padding:1px;height:auto">
   		<jcdf:auth code="010304">
			<a href="#" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true" data-options="disabled:true">新增</a>
		</jcdf:auth>
		<jcdf:auth code="010305">
			<a href="#" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-options="disabled:true">修改</a>
		</jcdf:auth>
		<jcdf:auth code="010306">
			<a href="#" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-options="disabled:true">删除</a>
		</jcdf:auth>
    </div>
    
   	<table border="0" cellspacing="0" cellpadding="0" width="100%">
    	<tr>
    		<td width="300" valign="top">
    			<div id="resoureTreeDiv" style="overflow: auto;">
	    			<div style="border-color: #dddddd;">
	    				<jcdf:auth code="010301">
		    				<a href="#" id="addMenuButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		    			</jcdf:auth>
		    			<jcdf:auth code="010302">
							<a href="#" id="editMenuButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
						</jcdf:auth>
						<jcdf:auth code="010303">
							<a href="#" id="deleteMenuButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
						</jcdf:auth>
	    			</div>
	    			<ul id="resourceTree"></ul>
    			</div>
    		</td>
    		<td valign="top">
    			 <!-- 数据展示列表区 -->
			    <table id="resourceDatagrid" tagType="datagrid"></table>
    		</td>
    	</tr>
    </table>
  </body>
</html>
