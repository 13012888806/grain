<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/jcdf.tld" prefix="jcdf"%>
<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<!-- 导入框架css和js库 -->
		<jsp:include page="head-ui-134.jsp"></jsp:include>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/MD5+BASE64Encoder.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jcdf-index-1.0.js"></script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="height: 40px; background: #B3DFDA; padding: 3px; text-align: center; font-size: 20px;">
			仓库管理系统
			<div style="position: absolute; right: 10px; top: 10px;">
				当前登录用户：${loginUser.userName} （${loginUser.userId}），
				<a id="changePass" href="#" style="margin-right: 10px;">修改密码</a>
				<a id="logout" href="#">退出</a>
			</div>
		</div>

		<!-- -------------------系统功能菜单展示区-------------------------------------- -->
		<div data-options="region:'west',split:true,title:'系统菜单'" style="width: 200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<jcdf:auth code="01">
					<div title="权限管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="0101">
								<li>
									<a id="yhgl" href="#" link="auth/user.do?method=view" onclick="openNewTab('yhgl')">用户管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0102">
								<li>
									<a id="jsgl" href="#" link="auth/role.do" onclick="openNewTab('jsgl')">角色管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0103">
								<li>
									<a id="zygl" href="#" link="auth/resource.do" onclick="openNewTab('zygl')">资源管理</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
				<jcdf:auth code="02">
					<div title="基础信息管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="0201">
								<li>
									<a id="wh" href="#" link="base/wh.do" onclick="openNewTab('wh')">仓库信息管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0202">
								<li>
									<a id="unit" href="#" link="base/unit.do" onclick="openNewTab('unit')">单位管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0203">
								<li>
									<a id="cg" href="#" link="base/cg.do" onclick="openNewTab('cg')">种类管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0204">
								<li>
									<a id="var" href="#" link="base/var.do" onclick="openNewTab('var')">品种管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0205">
								<li>
									<a id="sl" href="#" link="base/sl.do" onclick="openNewTab('sl')">供应商级别管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0206">
								<li>
									<a id="sup" href="#" link="base/sup.do" onclick="openNewTab('sup')">供应商信息管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0207">
								<li>
									<a id="cl" href="#" link="base/cl.do" onclick="openNewTab('cl')">客户级别管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0208">
								<li>
									<a id="cus" href="#" link="base/cus.do" onclick="openNewTab('cus')">客户信息管理</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
				<jcdf:auth code="03">
					<div title="库存信息管理" style="padding: 1px" data-options="iconCls:'icon-007'">
						<ul class="easyui-tree">
							<jcdf:auth code="0301">
								<li>
									<a id="sto" href="#" link="base/sto.do" onclick="openNewTab('sto')">入库信息管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0302">
								<li>
									<a id="lib" href="#" link="base/lib.do" onclick="openNewTab('lib')">出库信息管理</a>
								</li>
							</jcdf:auth>
							<jcdf:auth code="0303">
								<li>
									<a id="stolib" href="#" link="base/stolib.do" onclick="openNewTab('stolib')">库存信息汇总</a>
								</li>
							</jcdf:auth>
						</ul>
					</div>
				</jcdf:auth>
			</div>
		</div>

		<!-- ---------------------首页页脚版本信息展示区(上海怡钎信息科技有限公司)------------------------------------- -->
		<div data-options="region:'south',border:false"
			style="height: 50px; background: #A9FACD; padding: 10px; text-align: center; font-size: 20px;">
			Copyright©&nbsp;上海怡钎信息科技有限公司&nbsp;&nbsp;Versions：2016-07-05(V2.0)
		</div>

		<!-- ---------------------首页中间内容展示区------------------------------------- -->
		<div data-options="region:'center'">
			<div id="centerTab" class="easyui-tabs" fit="true" border="false" plain="true">
				<div id="welcomeTab" title="welcome" href="welcome.jsp"></div>
			</div>
		</div>

		<!-- 密码修改窗口页面 -->
		<div id="changePassDialog" style="display: none;" class="tab">
			<form action="#" id="changePassForm" style="display: inline;">
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="bule" align="right" width="35%">
							原密码：
						</td>
						<td align="left">
							<input type="password" name="oldPass" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']" />
						</td>
					</tr>
					<tr>
						<td class="bule" align="right">
							新密码：
						</td>
						<td align="left">
							<input id="newPass" type="password" name="newPass" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]']" />
						</td>
					</tr>
					<tr>
						<td class="bule" align="right">
							新密码确认：
						</td>
						<td align="left">
							<input type="password" name="confirmNewPass" class="easyui-validatebox" data-options="required:true,validType:['maxLength[25]','same[\'newPass\']']" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
