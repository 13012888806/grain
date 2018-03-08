<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>客户级别管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<!-- <script type="text/javascript" src="js/cl.js"></script> -->
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#clDatagrid').datagrid('options').queryParams=params;
				$('#clDatagrid').datagrid('load');
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
			$('#clDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'cl.do?method=pageQuery',
				idField:'ID',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'VAR_CD',title:'品种名称',width:$$.fillsize(0.1),align:'center'},
					{field:'CL_CD',title:'级别编号',width:$$.fillsize(0.15),align:'center'},
					{field:'CL_NM',title:'级别名称',width:$$.fillsize(0.25),align:'center'},
					{field:'PRC',title:'价格',width:$$.fillsize(0.1),align:'center'},
					{field:'UNIT_CD',title:'单位编码',width:$$.fillsize(0.1),align:'center'}
					,
					{field:'ADJUST_LIMIT',title:'调整额',width:$$.fillsize(0.1),align:'center'},
					{field:'DISCOUNT_RATE',title:'折扣率',width:$$.fillsize(0.1),align:'center'},
					{field:'FINAL_PRC',title:'成交价',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_CD',title:'录入人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_NM',title:'录入人姓名',width:$$.fillsize(0.1),align:'center'},
					
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_NM',title:'修改人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.1),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("clDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/** 新增弹出框 **/
		function addCl(){
			$('#addClDialog').css('display','');
			$('#addClDialog').dialog({
				title:"新增客户级别",
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
						$('#addClDialog').dialog('close');
					}
				}],
				onOpen:function() {
					$$.fillDialogWidthAndHeight("addClDialog", 0.95,470,750);
				},
				onClose:function(){
					clearAddFrom();
					$("#search_button").click();
				}
			});
		}
		
		/** 新增弹出层提交数据到后台 **/
		function addCommit(){
			if(!$("#addClForm").form('validate')){
		      return false;
		    }
			var cl = $$.serializeToJson("#addClForm");
			if (!cl)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/cl.do?method=insertCl',
			   dataType:"json",
			   data: cl,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#addClDialog').dialog('close');
			   }
			});
		}
		/** 清空添加Form表单 **/
		function clearAddFrom(){
			$("#addClForm").form('clear');
		}
		
		/** 修改弹出框 **/
		function updCl(){
			var selectRow = $$.getSelectIds("clDatagrid", "请选择你要编辑的记录！", "id");
			var selectedData = $("#clDatagrid").datagrid("getChecked");
			if (selectRow) {
				if(selectedData.length > 1){
					if(selectedData.length > 1){
						$.messager.alert('提示消息','只能同时编辑一条数据');
					}
					return;
				}else{
					$('#updClDialog').css('display','');
					$('#updClDialog').dialog({
						title:"修改客户级别",
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
								$('#updClDialog').dialog('close');
							}
						}],
						onOpen:function() {
							clearUpdForm();
							fillData(selectedData[0]);
							$$.fillDialogWidthAndHeight("updClDialog", 0.95,470,750);
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
			$("#updClForm").form('load',selectRow);
		}
		
		/** 修改弹出层提交数据到后台 **/
		function updCommit(){
			if(!$("#updClForm").form('validate')){
		      return false;
		    }
			var cl = $$.serializeToJson("#updClForm");
			if (!cl)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/cl.do?method=updCl',
			   dataType:"json",
			   data: cl,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#updClDialog').dialog('close');
			   }
			});
		}
		 
		/*** 清空修改Form表单 ***/
		function clearUpdForm(){
			$("#updClForm").form('clear');
		}
		/**
		 *	删除所选记录
		 */
		function deleteClInfo(){
			var selectRow = $$.getSelectIds("clDatagrid", "请选择你要删除的记录！", "ID");
			if (selectRow) {
				$.messager.confirm('提示消息',"确定删除选中的数据吗？",function(r){
					if(r){
						$$.openProcessingDialog();
						var url = 'cl.do?method=deleteByIds';
						var ids = selectRow.ids;
						var params = {"ids":ids};
						$.post(url,params,function(data){
							$$.closeProcessingDialog();
								var rd = $.parseJSON(data);
								$.messager.alert('提示消息',rd.msg,'info',function(){
									$('#clDatagrid').datagrid('unselectAll');
									$('#clDatagrid').datagrid('reload');
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
				    <td width="13%" class="bule">级别名称：</td>
				    <td width="37%">
				    	<input name="CL_NM" type="text" style="width:150px">
				    </td>
				    <td width="13%" class="bule">品种：</td>
				    <td>
				    	<input id="VAR_CD_QUR" class="easyui-combobox" name="VAR_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        url: 'var.do?method=queryVar'"
						    />
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
		     
		     <jcdf:auth code="020701">
		     	<a href="#" onclick="addCl()" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020702">
		    	<a href="#" onclick="updCl()" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="020703">
			 	<a href="#" onclick="deleteClInfo()" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="clDatagrid" tagType="datagrid">
    	
    </table>
    <!-- 新增弹出层开始 -->
	<div id="addClDialog" style="display: none;">
   		<form action="#" id="addClForm" style="display: inline;" method="post">
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">品种名称：</td>
		    			<td>
		    				<input id="VAR_CD_ADD" class="easyui-combobox" name="VAR_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'var.do?method=queryVar'"
						    />
	    				</td>
	    				<td class="bule" align="right">级别名称：</td>
					   <td>
					   		<input class="easyui-textbox" name="CL_NM" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">价格：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PRC" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="UNIT_CD" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
	    				<td class="bule" align="right">折扣率：</td>
		    			<td>
		    				<input class="easyui-textbox" name="DISCOUNT_RATE" data-options="required:true"/>
	    				</td>
		    			<td class="bule" align="right">调整额：</td>
		    			<td>
		    				<input class="easyui-textbox" name="ADJUST_LIMIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">成交价：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="FINAL_PRC" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 新增弹出层结束 -->
   	<!-- 修改弹出层开始 -->
	<div id="updClDialog" style="display: none;">
   		<form action="#" id="updClForm" style="display: inline;" method="post">
   			<input type="hidden" name="ID" />
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">品种名称：</td>
		    			<td>
		    				<input id="VAR_CD_UPD" class="easyui-combobox" name="VAR_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'var.do?method=queryVar'"
						    />
	    				</td>
	    				<td class="bule" align="right">级别名称：</td>
					   <td>
					   		<input class="easyui-textbox" name="CL_NM" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">价格：</td>
		    			<td>
		    				<input class="easyui-textbox" name="PRC" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">单位：</td>
		    			<td>
		    				<input class="easyui-textbox" name="UNIT_CD" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
	    				<td class="bule" align="right">折扣率：</td>
		    			<td>
		    				<input class="easyui-textbox" name="DISCOUNT_RATE" data-options="required:true"/>
	    				</td>
		    			<td class="bule" align="right">调整额：</td>
		    			<td>
		    				<input class="easyui-textbox" name="ADJUST_LIMIT" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">成交价：</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" name="FINAL_PRC" data-options="required:true"/>
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 修改弹出层结束 -->
   	
  </body>
</html>
