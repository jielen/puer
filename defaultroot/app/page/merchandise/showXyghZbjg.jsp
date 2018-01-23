<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcXyghZbjg"%>
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
<title>编辑协议供货招标项目</title>
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
		<font color="blue" size="3"><B>编辑协议供货招标项目</B></font>
	</div>
	<% 
		ZcXyghZbjg zcXyghZbjg = (ZcXyghZbjg)request.getAttribute("zcXyghZbjg");
	%>
	<div class="body">
		<form id="validateForm" action="/GB/portal/page/merchandise/SaveXyghZbjg.do?index=edit" method="post">
			<table class="inputTable">
				<tr>
					<th>
						项目编码:
					</th>
					<td>
						<bean:define id="beanWriteValue" name="zcXyghZbjg" property="zcProjId"/>
						<input type="text" name="zcProjId" value="<%=beanWriteValue.toString() %>"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						项目名称:
					</th>
					<td>
						<bean:define id="beanWriteValue" name="zcXyghZbjg" property="zcProjNa"/>
						<input type="text" name="zcProjNa" class="formText {required: true, messages: {required: '请填写项目名称!'}}" value="<%=beanWriteValue.toString() %>" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						年度:
					</th>
					<td>
						<bean:define id="beanWriteValue" name="zcXyghZbjg" property="zcProjNd"/>
						<input type="text" name="zcProjNd" class="formText {required: true, digits: true, messages: {required: '请填写年度!'}}" value="<%=beanWriteValue.toString() %>" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						状态:
					</th>
					<td>
						<% if ( zcXyghZbjg.getZcProjStatus()!= null && zcXyghZbjg.getZcProjStatus().equals("02") ){%>
							<label><input type="radio" name="zcProjStatus" value="02" checked="checked" />启用</label>
							<label><input type="radio" name="zcProjStatus" value="01" />关闭</label>
						<%} else{%>
							<label><input type="radio" name="zcProjStatus" value="02" />启用</label>
							<label><input type="radio" name="zcProjStatus" value="01" checked="checked" />关闭</label>
						<%} %>
					</td>
				</tr>
				<tr>
					<th>
						生效时间:
					</th>
					<td>
						<bean:define id="beanWriteValue" name="zcXyghZbjg" property="zcBgnDateStr"/>
						<input type="text" value="<%=beanWriteValue.toString() %>" name="zcBgnDate" format="yyyy-MM-dd" title="生效时间" class="formText {required: true, messages: {required: '请选择生效时间!'}}" readonly="true"
									onclick="calendar();" onBlur="notnull('zcBgnDate',false)"  />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						失效时间:
					</th>
					<td>
						<bean:define id="beanWriteValue" name="zcXyghZbjg" property="zcEndDateStr"/>
						<input type="text" value="<%=beanWriteValue.toString() %>" name="zcEndDate" format="yyyy-MM-dd" title="失效时间" class="formText {required: true, messages: {required: '请选择失效时间!'}}" readonly="true"
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

