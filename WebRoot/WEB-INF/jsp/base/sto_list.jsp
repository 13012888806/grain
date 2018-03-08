<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>入库信息</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<!-- <script type="text/javascript" src="js/sto.js"></script> -->
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#stoDatagrid').datagrid('options').queryParams=params;
				$('#stoDatagrid').datagrid('load');
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
			$('#stoDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'sto.do?method=pageQuery',
				idField:'ID',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'CUS_NM',title:'客户名称',width:$$.fillsize(0.1),align:'center'},
					{field:'WS_NM',title:'仓库名称',width:$$.fillsize(0.15),align:'center'},
					{field:'VAR_NM',title:'品种名称',width:$$.fillsize(0.25),align:'center'},
					{field:'CNT',title:'数量',width:$$.fillsize(0.1),align:'center'},
					{field:'CNT_UNIT',title:'单位编码',width:$$.fillsize(0.1),align:'center'},
					{field:'WGT',title:'单位重量',width:$$.fillsize(0.1),align:'center'},
					{field:'TOTAL_WGT',title:'总重量',width:$$.fillsize(0.1),align:'center'},
					{field:'WGT_UNIT',title:'重量单位',width:$$.fillsize(0.1),align:'center'},
					{field:'SUP_CD',title:'供应商编码',width:$$.fillsize(0.1),align:'center'},
					{field:'STORAGE_TPY',title:'入库种类',width:$$.fillsize(0.1),align:'center'},
					
					{field:'RMK',title:'备注',width:$$.fillsize(0.1),align:'center'},
					{field:'IN_NM',title:'入库人',width:$$.fillsize(0.1),align:'center'},
					{field:'IN_DT',title:'入库时间',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_CD',title:'录入人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_NM',title:'录入人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.1),align:'center'},
					
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_NM',title:'修改人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.1),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("stoDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/** 新增弹出框 **/
		function addSto(){
			$('#addStoDialog').css('display','');
			$('#addStoDialog').dialog({
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
						$('#addStoDialog').dialog('close');
					}
				}],
				onOpen:function() {
					$$.fillDialogWidthAndHeight("addStoDialog", 0.95,470,750);
				},
				onClose:function(){
					clearAddFrom();
					$("#search_button").click();
				}
			});
		}
		
		/** 新增弹出层提交数据到后台 **/
		function addCommit(){
			if(!$("#addStoForm").form('validate')){
		      return false;
		    }
			var sto = $$.serializeToJson("#addStoForm");
			if (!sto)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/sto.do?method=insertSto',
			   dataType:"json",
			   data: sto,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#addStoDialog').dialog('close');
			   }
			});
		}
		/** 清空添加Form表单 **/
		function clearAddFrom(){
			$("#addStoForm").form('clear');
		}
		
		/** 修改弹出框 **/
		function updSto(){
			var selectRow = $$.getSelectIds("stoDatagrid", "请选择你要编辑的记录！", "id");
			var selectedData = $("#stoDatagrid").datagrid("getChecked");
			if (selectRow) {
				if(selectedData.length > 1){
					if(selectedData.length > 1){
						$.messager.alert('提示消息','只能同时编辑一条数据');
					}
					return;
				}else{
					$('#updStoDialog').css('display','');
					$('#updStoDialog').dialog({
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
								$('#updStoDialog').dialog('close');
							}
						}],
						onOpen:function() {
							clearUpdForm();
							fillData(selectedData[0]);
							$$.fillDialogWidthAndHeight("updStoDialog", 0.95,470,750);
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
			$("#updStoForm").form('load',selectRow);
		}
		
		/** 修改弹出层提交数据到后台 **/
		function updCommit(){
			if(!$("#updStoForm").form('validate')){
		      return false;
		    }
			var sto = $$.serializeToJson("#updStoForm");
			if (!sto)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/sto.do?method=updSto',
			   dataType:"json",
			   data: sto,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#updStoDialog').dialog('close');
			   }
			});
		}
		 
		/*** 清空修改Form表单 ***/
		function clearUpdForm(){
			$("#updStoForm").form('clear');
		}
		/**
		 *	删除所选记录
		 */
		function deleteStoInfo(){
			var selectRow = $$.getSelectIds("stoDatagrid", "请选择你要删除的记录！", "ID");
			if (selectRow) {
				$.messager.confirm('提示消息',"确定删除选中的数据吗？",function(r){
					if(r){
						$$.openProcessingDialog();
						var url = 'sto.do?method=deleteByIds';
						var ids = selectRow.ids;
						var params = {"ids":ids};
						$.post(url,params,function(data){
							$$.closeProcessingDialog();
								var rd = $.parseJSON(data);
								$.messager.alert('提示消息',rd.msg,'info',function(){
									$('#stoDatagrid').datagrid('unselectAll');
									$('#stoDatagrid').datagrid('reload');
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
		     	<a href="#" onclick="addSto()" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020102">
		    	<a href="#" onclick="updSto()" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="020103">
			 	<a href="#" onclick="deleteStoInfo()" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="stoDatagrid" tagType="datagrid">
    	
    </table>
    <!-- 新增弹出层开始 -->
	<div id="addStoDialog" style="display: none;">
   		<form action="#" id="addStoForm" style="display: inline;" method="post">
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户：</td>
		    			<td>
		    				<input id="CUS_CD_ADD" class="easyui-combobox" name="CUS_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'cus.do?method=queryCus'"
						    />
	    				</td>
	    				<td class="bule" align="right">仓库：</td>
					   <td>
					   		<input id="WS_CD_ADD" class="easyui-combobox" name="WS_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'wh.do?method=queryWh'"
						    />
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">品种：</td>
		    			<td>
		    				<input id="VAR_CD_ADD" class="easyui-combobox" name="VAR_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'var.do?method=queryVar'"
						    />
	    				</td>
	    				<td class="bule" align="right">数量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CNT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">数量单位：</td>
		    			<td>
		    				<input id="CNT_UNIT_ADD" class="easyui-combobox" name="CNT_UNIT" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        data: [{'id':'1','text':'袋'},{'id':'2','text':'千克'}]"
						    />
	    				</td>
	    				<td class="bule" align="right">重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">总重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TOTAL_WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">重量单位：</td>
		    			<td>
		    				<input id="WGT_UNIT_ADD" class="easyui-combobox" name="WGT_UNIT" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        data: [{'id':'1','text':'袋'},{'id':'2','text':'千克'}]"
						    />
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">供应商：</td>
		    			<td>
		    				<input class="easyui-textbox" name="SUP_CD" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">入库种类：</td>
		    			<td>
		    				<input class="easyui-textbox" name="STORAGE_TPY" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">备注：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="RMK" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 新增弹出层结束 -->
   	<!-- 修改弹出层开始 -->
	<div id="updStoDialog" style="display: none;">
   		<form action="#" id="updStoForm" style="display: inline;" method="post">
   			<input type="hidden" name="ID" />
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户：</td>
		    			<td>
		    				<input id="CUS_CD_UPD" class="easyui-combobox" name="CUS_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'cus.do?method=queryCus'"
						    />
	    				</td>
	    				<td class="bule" align="right">仓库：</td>
					   <td>
					   		<input id="WS_CD_UPD" class="easyui-combobox" name="WS_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'wh.do?method=queryWh'"
						    />
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">品种：</td>
		    			<td>
		    				<input id="VAR_CD_UPD" class="easyui-combobox" name="VAR_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'var.do?method=queryVar'"
						    />
	    				</td>
	    				<td class="bule" align="right">数量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CNT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">数量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CNT_UNIT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">总重量：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TOTAL_WGT" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">重量单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="WGT_UNIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">供应商：</td>
		    			<td>
		    				<input class="easyui-textbox" name="SUP_CD" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">入库种类：</td>
		    			<td>
		    				<input class="easyui-textbox" name="STORAGE_TPY" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">备注：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="RMK" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 修改弹出层结束 -->
   	
  </body>
</html>
