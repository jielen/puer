<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新增协议供货招标项目页面</title>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.tools.js"></script>
<script type="text/javascript" src="../../js/jquery.metadata.js"></script>
<script type="text/javascript" src="../../js/jquery.validate.js"></script>
<script type="text/javascript" src="../../js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="../../js/jquery.validate.cn.js"></script>
<script type="text/javascript" src="../../js/jquery.livequery.js"></script>
<script type="text/javascript" src="../../js/jquery.datepicker.js"></script>
<script type="text/javascript" src="../../js/base.js"></script>
<script type="text/javascript" src="../../js/admin.js"></script>
<script language="javascript" src="./../../js/DateTimeCalendar.js"></script>
<link href="../../css/webstyle.css" rel="stylesheet" type="text/css" />
<link href="../../css/admin.css" rel="stylesheet" type="text/css" />
<link href="../../css/jquery.datepicker.css" rel="stylesheet" type="text/css" />

</head>



<body class="input">
	<div class="bar">
		<input type="button" class="formButton" onclick="location.href='/GB/portal/page/merchandise/XyghZbjgList.do'" value="返 回" />
	</div>
	<div class="bar" align="center">
		<font color="blue" size="3"><B>新增协议供货招标项目</B></font>
	</div>
	<div class="body">
		<form id="validateForm" action="/GB/portal/page/merchandise/SaveXyghZbjg.do?index=add" method="post">
			<table class="inputTable">
				<tr>
					<th>
						项目编码:
					</th>
					<td>
						<input type="text" name="zcProjId" value="自动编码"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						项目名称:
					</th>
					<td>
						<input type="text" name="zcProjNa" class="formText {required: true, messages: {required: '请填写项目名称!'}}" value="" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						年度:
					</th>
					<td>
						<input type="text" name="zcProjNd" class="formText {required: true, digits: true, messages: {required: '请填写年度!'}}" value="" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						状态:
					</th>
					<td>
						<label><input type="radio" name="zcProjStatus" value="02" checked="checked" />启用</label>
						<label><input type="radio" name="zcProjStatus" value="01" />关闭</label>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						生效时间:
					</th>
					<td>
						<input type="text" name="zcBgnDate" title="生效时间" class="formText {required: true, messages: {required: '请选择生效时间!'}}" readonly="true"
									onclick="calendar();" onBlur="notnull('zcBgnDate',false)"  />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						失效时间:
					</th>
					<td>
						<input type="text" name="zcEndDate" title="失效时间" class="formText {required: true, messages: {required: '请选择失效时间!'}}" readonly="true"
									onclick="calendar();" onBlur="notnull('zcEndDate',false)"  />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="保 存" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>

