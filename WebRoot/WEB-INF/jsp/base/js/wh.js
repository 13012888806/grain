
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
					{field:'WS_CD',title:'仓库编号',width:$$.fillsize(0.3),align:'center'},
					{field:'WS_NM',title:'仓库名称',width:$$.fillsize(0.39),align:'center'},
					{field:'WS_ADDR',title:'仓库地址',width:$$.fillsize(0.25),align:'center'},
					{field:'LENGTH',title:'长',width:$$.fillsize(0.25),align:'center'},
					{field:'WIDTH',title:'宽',width:$$.fillsize(0.25),align:'center'},
					{field:'HEIGHT',title:'高',width:$$.fillsize(0.25),align:'center'},
					{field:'WS_TYP',title:'类型',width:$$.fillsize(0.25),align:'center'},
					{field:'WS_PRC',title:'价格',width:$$.fillsize(0.25),align:'center'},
					{field:'PAY_TYP',title:'付费方式',width:$$.fillsize(0.25),align:'center'},
					{field:'PROVINCE',title:'所属省',width:$$.fillsize(0.25),align:'center'},
					
					{field:'CITY',title:'所属市',width:$$.fillsize(0.25),align:'center'},
					{field:'COUNTY',title:'所属县/区',width:$$.fillsize(0.25),align:'center'},
					{field:'INPUT_DT',title:'录入时间',width:$$.fillsize(0.25),align:'center'},
					{field:'INPUT_CD',title:'录入人编号',width:$$.fillsize(0.25),align:'center'},
					{field:'INPUT_NM',title:'录入人姓名',width:$$.fillsize(0.25),align:'center'},
					{field:'MODIFY_CD',title:'修改人编号',width:$$.fillsize(0.25),align:'center'},
					{field:'MODIFY_NM',title:'修改人姓名',width:$$.fillsize(0.25),align:'center'},
					{field:'MODIFY_DT',title:'修改时间',width:$$.fillsize(0.25),align:'center'}
				]],
				onBeforeLoad:function(){$$.clearSelect("whDatagrid");},
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
			$.post("wh.do?method=deleteById",{"userIds":ids},function(data){
				$$.closeProcessingDialog();
				if (data && data.result) {
					$$.showJcdfMessager('提示消息',data.msg,'info');
					$$.refreshJcdfDatagrid("whDatagrid");
				} else {
					$$.showJcdfMessager('提示消息',data.msg,'warning');
				}
				$$.clearSelect("whDatagrid");
			},'json');
		}