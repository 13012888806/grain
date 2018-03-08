<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
  <head>
    <title>客户信息管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<jsp:include page="../public/head-ui-134.jsp"></jsp:include>
	<script type="text/javascript" src="../js/jsCity.js"></script>
	<!-- <script type="text/javascript" src="js/cus.js"></script> -->
	<script type="text/javascript">
		$(function(){
			//页面加载时初始化datagrid列表并加载列表数据进行显示
			loadDatagrid();
		    //查询按钮单击事件绑定
			$("#search_button").bind("click",function(){
				var params = $$.serializeToJson("#searchForm");
				$('#cusDatagrid').datagrid('options').queryParams=params;
				$('#cusDatagrid').datagrid('load');
			});
			
			//清空按钮单击事件绑定
			$("#clear_button").bind("click",function(){
				$$.resetContent("searchForm");
			});
			city_initProvince("searchPro");
			city_initProvince("searchProEdit");
		})
		
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$('#cusDatagrid').datagrid({
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				nowrap: true,
				striped: true,
				url:'cus.do?method=pageQuery',
				idField:'ID',
				frozenColumns:[[
			        {field:'ck',checkbox:true,align:'center'}
				]],
				columns:[[
					{field:'CUS_CD',title:'客户编号',width:$$.fillsize(0.1),align:'center'},
					{field:'CUS_NM',title:'客户名称',width:$$.fillsize(0.15),align:'center'},
					{field:'CL_NM',title:'客户级别',width:$$.fillsize(0.25),align:'center'},
					{field:'CUS_ADDR',title:'客户地址',width:$$.fillsize(0.1),align:'center'},
					{field:'PROVINCE',title:'所属省',width:$$.fillsize(0.1),align:'center'},
					{field:'CITY',title:'所属市',width:$$.fillsize(0.1),align:'center'},
					{field:'COUNTY',title:'所属县/区',width:$$.fillsize(0.1),align:'center'},
					{field:'MOBILE',title:'手机',width:$$.fillsize(0.1),align:'center'},
					{field:'TEL',title:'固话',width:$$.fillsize(0.1),align:'center'},
					{field:'EMAIL',title:'电子邮件',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_CD',title:'录入人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_NM',title:'录入人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_NM',title:'修改人姓名',width:$$.fillsize(0.1),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.1),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("cusDatagrid");},
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				pageList: [10,15,20,30,50,100],
				toolbar:'#menu'
			});
		}
		
		/** 新增弹出框 **/
		function addCus(){
			$('#addCusDialog').css('display','');
			$('#addCusDialog').dialog({
				title:"新增客户信息",
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
						$('#addCusDialog').dialog('close');
					}
				}],
				onOpen:function() {
					$$.fillDialogWidthAndHeight("addCusDialog", 0.95,470,750);
				},
				onClose:function(){
					clearAddFrom();
					$("#search_button").click();
				}
			});
		}
		
		/** 新增弹出层提交数据到后台 **/
		function addCommit(){
			if(!$("#addCusForm").form('validate')){
		      return false;
		    }
		    var PROVINCE = $("#searchPro").find("option:selected").text();
		    $("#provinceNm").textbox('setValue',PROVINCE);
		    var CITY = $("#searchCity").find("option:selected").text();
		    $("#CITY_NM").textbox('setValue',CITY);
		    var COUNTY = $("#searchCounty").find("option:selected").text();
		    $("#COUNTY_NM").textbox('setValue',COUNTY);
			var cus = $$.serializeToJson("#addCusForm");
			if (!cus)return false;
			//如果数据验证通过(即数据合法)
			//$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   //url: basePath+'/base/cus.do?method=insertCus',
			   dataType:"json",
			   data: cus,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#addCusDialog').dialog('close');
			   }
			});
		}
		/** 清空添加Form表单 **/
		function clearAddFrom(){
			$("#addCusForm").form('clear');
		}
		
		/** 修改弹出框 **/
		function updCus(){
			var selectRow = $$.getSelectIds("cusDatagrid", "请选择你要编辑的记录！", "id");
			var selectedData = $("#cusDatagrid").datagrid("getChecked");
			if (selectRow) {
				if(selectedData.length > 1){
					if(selectedData.length > 1){
						$.messager.alert('提示消息','只能同时编辑一条数据');
					}
					return;
				}else{
					$('#updCusDialog').css('display','');
					$('#updCusDialog').dialog({
						title:"修改客户信息",
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
								$('#updCusDialog').dialog('close');
							}
						}],
						onOpen:function() {
							clearUpdForm();
							fillData(selectedData[0]);
							$$.fillDialogWidthAndHeight("updCusDialog", 0.95,470,750);
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
			$("#updCusForm").form('load',selectRow);
		}
		
		/** 修改弹出层提交数据到后台 **/
		function updCommit(){
			if(!$("#updCusForm").form('validate')){
		      return false;
		    }
			var cus = $$.serializeToJson("#updCusForm");
			if (!cus)return false;
			//如果数据验证通过(即数据合法)
			$$.openProcessingDialog();
			//ajax提交数据
			$.ajax({
			   type: "POST",
			   url: basePath+'/base/cus.do?method=updCus',
			   dataType:"json",
			   data: cus,
			   success: function(data){
				   	 $$.closeProcessingDialog();
				     if (data && data.result) {
						$$.showJcdfMessager('提示消息',data.msg,'info')
					 } else {
						$$.showJcdfMessager('提示消息',data.msg,'warning');
					 }
					 $$.closeJcdfDialog();
					 $('#updCusDialog').dialog('close');
			   }
			});
		}
		 
		/*** 清空修改Form表单 ***/
		function clearUpdForm(){
			$("#updCusForm").form('clear');
		}
		/**
		 *	删除所选记录
		 */
		function deleteCusInfo(){
			var selectRow = $$.getSelectIds("cusDatagrid", "请选择你要删除的记录！", "ID");
			if (selectRow) {
				$.messager.confirm('提示消息',"确定删除选中的数据吗？",function(r){
					if(r){
						$$.openProcessingDialog();
						var url = 'cus.do?method=deleteByIds';
						var ids = selectRow.ids;
						var params = {"ids":ids};
						$.post(url,params,function(data){
							$$.closeProcessingDialog();
								var rd = $.parseJSON(data);
								$.messager.alert('提示消息',rd.msg,'info',function(){
									$('#cusDatagrid').datagrid('unselectAll');
									$('#cusDatagrid').datagrid('reload');
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
				    <td width="13%" class="bule">客户名称：</td>
				    <td width="37%">
				    	<input name="CUS_NM" type="text" style="width:150px">
				    </td>
				    <td width="13%" class="bule">客户地址：</td>
				    <td>
				    	<input name="CUS_ADDR" type="text" style="width:150px">
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
		     
		     <jcdf:auth code="020801">
		     	<a href="#" onclick="addCus()" id="addButton" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		     </jcdf:auth>
		     <jcdf:auth code="020802">
		    	<a href="#" onclick="updCus()" id="editButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		     </jcdf:auth>
		     <jcdf:auth code="020803">
			 	<a href="#" onclick="deleteCusInfo()" id="deleteButton" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a>
			 </jcdf:auth>
        </div>
    </div>
    
    <!-- 数据展示列表区 -->
    <table id="cusDatagrid" tagType="datagrid">
    	
    </table>
    <!-- 新增弹出层开始 -->
	<div id="addCusDialog" style="display: none;">
   		<form action="#" id="addCusForm" style="display: inline;" method="post">
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户名称：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CUS_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">客户地址：</td>
					   <td>
					   		<input class="easyui-textbox" name="CUS_ADDR" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户级别：</td>
		    			<td>
		    				<input id="CL_CD_ADD" class="easyui-combobox" name="CL_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'cl.do?method=queryCl'"
						    />
	    				</td>
	    				<td class="bule" align="right">手机：</td>
		    			<td>
		    				<input class="easyui-textbox" name="MOBILE" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">固话：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TEL" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">电子邮件：</td>
		    			<td>
		    				<input class="easyui-textbox" name="EMAIL" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<!-- 
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
	    			 -->
	    			<tr>
	    				<td class="bule" align="right" >所属地址</td>
	    				<td colspan="3">
		    				 <select id="searchPro" name="PROVINCE" onchange="city_providerchage('searchPro','','searchCity');" style="width: 100px;">
						      	<option></option>
						      </select>
						      <input id="provinceNm" class="easyui-textbox" name="PROVINCE_NM" />
						      	省
						      <select id="searchCity" name="CITY" style="width:100px;" onchange="county_providerchage('searchCity','','searchCounty');">
						      	<option></option>
						      </select>
						      <input id="CITY_NM" class="easyui-textbox" name="CITY_NM" />
						      	市
						  	 <select id="searchCounty" name="COUNTY" style="width:100px;">
						      	<option></option>
						      </select>
						      <input id="COUNTY_NM" class="easyui-textbox" name="COUNTY_NM"/>
						      	县/区
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 新增弹出层结束 -->
   	<!-- 修改弹出层开始 -->
	<div id="updCusDialog" style="display: none;">
   		<form action="#" id="updCusForm" style="display: inline;" method="post">
   			<input type="hidden" name="ID" />
   			<div class="tab">
    			<table width="100%">
	    			<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户名称：</td>
		    			<td>
		    				<input class="easyui-textbox" name="CUS_NM" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">客户地址：</td>
					   <td>
					   		<input class="easyui-textbox" name="CUS_ADDR" data-options="required:true"/>
					   </td>
	    			</tr>
					<tr height="50px;">
		    			<td class="bule" align="right" width="150">客户级别：</td>
		    			<td>
		    				<input id="CL_CD_UPD" class="easyui-combobox" name="CL_CD" data-options="
						        valueField: 'id',
						        textField: 'text',
						        required:true,
						        url: 'cl.do?method=queryCl'"
						    />
	    				</td>
	    				<td class="bule" align="right">手机：</td>
		    			<td>
		    				<input class="easyui-textbox" name="MOBILE" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right">固话：</td>
		    			<td>
		    				<input class="easyui-textbox" name="TEL" data-options="required:true"/>
	    				</td>
	    				<td class="bule" align="right">电子邮件：</td>
		    			<td>
		    				<input class="easyui-textbox" name="EMAIL" data-options="required:true"/>
	    				</td>
	    			</tr>
	    			<tr height="50px;">
		    			<td class="bule" align="right" >所属地址</td>
	    				<td colspan="3">
		    				 <select id="searchProEdit" name="PROVINCE" onchange="city_providerchage('searchProEdit','','searchCityEdit');" style="width: 100px;">
						      	<option></option>
						      </select>
						      	省
						      <select id="searchCityEdit" name="CITY" style="width:100px;" onchange="county_providerchage('searchCityEdit','','searchCountyEdit');">
						      	<option></option>
						      </select>
						      	市
						  	 <select id="searchCountyEdit" name="COUNTY" style="width:100px;">
						      	<option></option>
						      </select>
						      	县/区
	    				</td>
	    			</tr>
    			</table>
    		</div>
    	</form>
   	</div>
   	<!-- 修改弹出层结束 -->
   	
  </body>
</html>
