<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>出库信息管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<!-- <script type="text/javascript" src="js/lib.js"></script> -->
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#libDatagrid').datagrid('options').queryParams=params;
				$('#libDatagrid').datagrid('load');
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
			$('#libDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'lib.do?method=pageQuery',
				idField:'ID',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'WS_CD',title:'仓库',width:$$.fillsize(0.1),align:'center'},
					{field:'VAR_CD',title:'品种',width:$$.fillsize(0.15),align:'center'},
					{field:'OUT_CNT',title:'出库数量',width:$$.fillsize(0.25),align:'center'},
					{field:'CNT_UNIT',title:'数量单位',width:$$.fillsize(0.1),align:'center'},
					{field:'WGT',title:'单位重量',width:$$.fillsize(0.1),align:'center'},
					
					{field:'WGT_UNIT',title:'重量单位',width:$$.fillsize(0.1),align:'center'},
					{field:'TOTAL_WGT',title:'总重量',width:$$.fillsize(0.1),align:'center'},
					{field:'CUS_CD',title:'客户编码',width:$$.fillsize(0.1),align:'center'},
					{field:'OUT_NM',title:'出库人',width:$$.fillsize(0.1),align:'center'},
					{field:'OUT_DT',title:'出库时间',width:$$.fillsize(0.1),align:'center'},
					
					{field:'INPUT_CD',title:'录入人编码',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_NM',title:'录入人',width:$$.fillsize(0.1),align:'center'},
					
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_NM',title:'修改人',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.1),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("libDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/** 新增弹出框 **/
		function addLib(){
			$('#addLibDialog').css('display','');
			$('#addLibDialog').dialog({
				title:"新增出库信息",
				modal:true,
				maximizable:true,
				resizable:true,
				buttons:[{
					text:'提交',
					disabled:false,
					iconCls:'icon-ok',
					handler:function(){
						addCommit();
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$('#addLibDialog').dialog('close');
					}
				}],
				onOpen:function() {
					$$.fillDialogWidthAndHeight("addLibDialog", 0.95,470,750);
				},
				onClose:function(){
					clearAddFrom();
					$("#search_button").click();
				}
			});
		}
		
		/** 新增弹出层提交数据到后台 **/
		function addCommit(){
			if(!$("#addLibForm").form('validate')){
		      return false;
		    }
			var lib = $$.serializeToJson("#addLibForm");
			if (!lib)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/lib.do?method=insertLib',
			   dataType:"json",
			   data: lib,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#addLibDialog').dialog('close');
			   }
			});
		}
		/** 清空添加Form表单 **/
		function clearAddFrom(){
			$("#addLibForm").form('clear');
		}
		
		/** 修改弹出框 **/
		function updLib(){
			var selectRow = $$.getSelectIds("libDatagrid", "请选择你要编辑的记录！", "id");
			var selectedData = $("#libDatagrid").datagrid("getChecked");
			if (selectRow) {
				if(selectedData.length > 1){
					if(selectedData.length > 1){
						$.messager.alert('提示消息','只能同时编辑一条数据');
					}
					return;
				}else{
					$('#updLibDialog').css('display','');
					$('#updLibDialog').dialog({
						title:"修改出库信息",
						modal:true,
						maximizable:true,
						resizable:true,
						buttons:[{
							text:'提交',
							disabled:false,
							iconCls:'icon-ok',
							handler:function(){
								updCommit();
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$('#updLibDialog').dialog('close');
							}
						}],
						onOpen:function() {
							clearUpdForm();
							fillData(selectedData[0]);
							$$.fillDialogWidthAndHeight("updLibDialog", 0.95,470,750);
						},
						onClose:function(){
							clearUpdForm();
							$("#search_button").click();
						}
					});
				}
			}
		}
		
		//点击编辑的时候给form表单赋值
		function fillData(selectRow){
			$("#updLibForm").form('load',selectRow);
		}
		
		/** 修改弹出层提交数据到后台 **/
		function updCommit(){
			if(!$("#updLibForm").form('validate')){
		      return false;
		    }
			var lib = $$.serializeToJson("#updLibForm");
			if (!lib)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/lib.do?method=updLib',
			   dataType:"json",
			   data: lib,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#updLibDialog').dialog('close');
			   }
			});
		}
		 
		/*** 清空修改Form表单 ***/
		function clearUpdForm(){
			$("#updLibForm").form('clear');
		}
		/**
		 *	删除所选记录
		 */
		function deleteLibInfo(){
			var selectRow = $$.getSelectIds("libDatagrid", "请选择你要删除的记录！", "ID");
			if (selectRow) {
				$.messager.confirm('提示消息',"确定删除选中的数据吗？",function(r){
					if(r){
						$$.openProcessingDialog();
						var url = 'lib.do?method=deleteByIds';
						var ids = selectRow.ids;
						var params = {"ids":ids};
						$.post(url,params,function(data){
							$$.closeProcessingDialog();
								var rd = $.parseJSON(data);
								$.messager.alert('提示消息',rd.msg,'info',function(){
									$('#libDatagrid').datagrid('unselectAll');
									$('#libDatagrid').datagrid('reload');
							});
						});
					}
				});
			}
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
				    <td width="13%" class="bule">仓库名称：</td>
				    <td width="37%">
				    	<input name="WS_NM" type="text" style="width:150px">
				    </td>
				    <td width="13%" class="bule">仓库地址：</td>
				    <td>
				    	<input name="WS_ADDR" type="text" style="width:150px">
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
		     
		     <jcdf:auth code="020101">
		     	<a href="#" onclick="addLib()" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020102">
		    	<a href="#" onclick="updLib()" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="020103">
			 	<a href="#" onclick="deleteLibInfo()" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="libDatagrid" tagType="datagrid">
    	
    </table>
    <!-- 新增弹出层开始 -->
	<div id="addLibDialog" style="display: none;">
   		<form action="#" id="addLibForm" style="display: inline;" method="post">
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">仓库：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_CD" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">品种：</td>
					   <td>
					   		<input class="easyui-textbox" name="VAR_CD" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">出库数量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_CNT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">数量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CNT_UNIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">重量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT_UNIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">总重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TOTAL_WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">客户：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CUS_CD" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">出库人：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">出库时间：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_DT" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 新增弹出层结束 -->
   	<!-- 修改弹出层开始 -->
	<div id="updLibDialog" style="display: none;">
   		<form action="#" id="updLibForm" style="display: inline;" method="post">
   			<input type="hidden" name="ID" />
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">仓库：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_CD" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">品种：</td>
					   <td>
					   		<input class="easyui-textbox" name="VAR_CD" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">出库数量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_CNT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">数量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CNT_UNIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">重量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT_UNIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">总重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TOTAL_WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">客户：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CUS_CD" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">出库人：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">出库时间：</td>
		    			<td>
		    				<input class="easyui-textbox" name="OUT_DT" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 修改弹出层结束 -->
   	
  </body>
</html>
