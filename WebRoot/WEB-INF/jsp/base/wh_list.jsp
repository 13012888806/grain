<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>仓库信息管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<!-- <script type="text/javascript" src="js/wh.js"></script> -->
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#whDatagrid').datagrid('options').queryParams=params;
				$('#whDatagrid').datagrid('load');
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
			$('#whDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'wh.do?method=pageQuery',
				idField:'ID',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'WS_CD',title:'仓库编号',width:$$.fillsize(0.1),align:'center'},
					{field:'WS_NM',title:'仓库名称',width:$$.fillsize(0.15),align:'center'},
					{field:'WS_ADDR',title:'仓库地址',width:$$.fillsize(0.25),align:'center'},
					{field:'LENGTH',title:'长',width:$$.fillsize(0.1),align:'center'},
					{field:'WIDTH',title:'宽',width:$$.fillsize(0.1),align:'center'},
					{field:'HEIGHT',title:'高',width:$$.fillsize(0.1),align:'center'},
					{field:'WS_TYP',title:'类型',width:$$.fillsize(0.1),align:'center'},
					{field:'WS_PRC',title:'价格',width:$$.fillsize(0.1),align:'center'},
					{field:'PAY_TYP',title:'付费方式',width:$$.fillsize(0.1),align:'center'},
					{field:'PROVINCE',title:'所属省',width:$$.fillsize(0.1),align:'center'},
					
					{field:'CITY',title:'所属市',width:$$.fillsize(0.1),align:'center'},
					{field:'COUNTY',title:'所属县/区',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_CD',title:'录入人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_NM',title:'录入人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_NM',title:'修改人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.1),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("whDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/** 新增弹出框 **/
		function addWh(){
			$('#addWhDialog').css('display','');
			$('#addWhDialog').dialog({
				title:"新增仓库信息",
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
						$('#addWhDialog').dialog('close');
					}
				}],
				onOpen:function() {
					$$.fillDialogWidthAndHeight("addWhDialog", 0.95,470,750);
				},
				onClose:function(){
					clearAddFrom();
					$("#search_button").click();
				}
			});
		}
		
		/** 新增弹出层提交数据到后台 **/
		function addCommit(){
			if(!$("#addWhForm").form('validate')){
		      return false;
		    }
			var wh = $$.serializeToJson("#addWhForm");
			if (!wh)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/wh.do?method=insertWh',
			   dataType:"json",
			   data: wh,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#addWhDialog').dialog('close');
			   }
			});
		}
		/** 清空添加Form表单 **/
		function clearAddFrom(){
			$("#addWhForm").form('clear');
		}
		
		/** 修改弹出框 **/
		function updWh(){
			var selectRow = $$.getSelectIds("whDatagrid", "请选择你要编辑的记录！", "id");
			var selectedData = $("#whDatagrid").datagrid("getChecked");
			if (selectRow) {
				if(selectedData.length > 1){
					if(selectedData.length > 1){
						$.messager.alert('提示消息','只能同时编辑一条数据');
					}
					return;
				}else{
					$('#updWhDialog').css('display','');
					$('#updWhDialog').dialog({
						title:"修改仓库信息",
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
								$('#updWhDialog').dialog('close');
							}
						}],
						onOpen:function() {
							clearUpdForm();
							fillData(selectedData[0]);
							$$.fillDialogWidthAndHeight("updWhDialog", 0.95,470,750);
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
			$("#updWhForm").form('load',selectRow);
		}
		
		/** 修改弹出层提交数据到后台 **/
		function updCommit(){
			if(!$("#updWhForm").form('validate')){
		      return false;
		    }
			var wh = $$.serializeToJson("#updWhForm");
			if (!wh)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/wh.do?method=updWh',
			   dataType:"json",
			   data: wh,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#updWhDialog').dialog('close');
			   }
			});
		}
		 
		/*** 清空修改Form表单 ***/
		function clearUpdForm(){
			$("#updWhForm").form('clear');
		}
		/**
		 *	删除所选记录
		 */
		function deleteWhInfo(){
			var selectRow = $$.getSelectIds("whDatagrid", "请选择你要删除的记录！", "ID");
			if (selectRow) {
				$.messager.confirm('提示消息',"确定删除选中的数据吗？",function(r){
					if(r){
						$$.openProcessingDialog();
						var url = 'wh.do?method=deleteByIds';
						var ids = selectRow.ids;
						var params = {"ids":ids};
						$.post(url,params,function(data){
							$$.closeProcessingDialog();
								var rd = $.parseJSON(data);
								$.messager.alert('提示消息',rd.msg,'info',function(){
									$('#whDatagrid').datagrid('unselectAll');
									$('#whDatagrid').datagrid('reload');
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
		     	<a href="#" onclick="addWh()" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020102">
		    	<a href="#" onclick="updWh()" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="020103">
			 	<a href="#" onclick="deleteWhInfo()" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="whDatagrid" tagType="datagrid">
    	
    </table>
    <!-- 新增弹出层开始 -->
	<div id="addWhDialog" style="display: none;">
   		<form action="#" id="addWhForm" style="display: inline;" method="post">
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">仓库名称：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">仓库地址：</td>
					   <td>
					   		<input class="easyui-textbox" name="WS_ADDR" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">长：</td>
		    			<td>
		    				<input class="easyui-textbox" name="LENGTH" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">宽：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WIDTH" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">高：</td>
		    			<td>
		    				<input class="easyui-textbox" name="HEIGHT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">类型：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_TYP" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">价格：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_PRC" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">付费方式：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PAY_TYP" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">所属省：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PROVINCE" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">所属市：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CITY" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">所属县/区：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="COUNTY" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 新增弹出层结束 -->
   	<!-- 修改弹出层开始 -->
	<div id="updWhDialog" style="display: none;">
   		<form action="#" id="updWhForm" style="display: inline;" method="post">
   			<input type="hidden" name="ID" />
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">仓库名称：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">仓库地址：</td>
					   <td>
					   		<input class="easyui-textbox" name="WS_ADDR" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">长：</td>
		    			<td>
		    				<input class="easyui-textbox" name="LENGTH" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">宽：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WIDTH" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">高：</td>
		    			<td>
		    				<input class="easyui-textbox" name="HEIGHT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">类型：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_TYP" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">价格：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WS_PRC" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">付费方式：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PAY_TYP" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">所属省：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PROVINCE" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">所属市：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CITY" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">所属县/区：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="COUNTY" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 修改弹出层结束 -->
   	
  </body>
</html>
