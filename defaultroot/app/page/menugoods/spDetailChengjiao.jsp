<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SpForm"%>
<%@page import="com.ufgov.zc.server.zc.ZcSUtil"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gbk">
<link href="../../css/content.css" type="text/css" rel="stylesheet">
<style type="text/css">
 a:link { color: #FF0000; text-decoration: underline} 
 a:visited { color: #FF0000; text-decoration: underline} 
 a:hover { color: #FF0000; text-decoration: underline} 
 a:active { color: #FF0000; text-decoration: underline}
 .b_l2{text-align:left;padding-left:15px;}
</style>
<script type="text/javascript">
var highlightcolor='#fefbdc';//鼠标经过背景色
var clickcolor='#e0efff';//鼠标选中背景色
</script>
<script src="../../js/tableHightLight.js" type="text/javascript"></script>

</head>
<body style="width:100%">
<table onMouseover="changeto()" onMouseout="changeback()" width="100%" class="itable" id="supplier" bgColor="#a8cefe" border="0" cellSpacing="1" cellPadding="0" valign="top" name="supplier">
<tr id="nc">
<td bgColor=#f3f8ff height=30 width="22%" align=center><font color="#2c62c4"><b>采购单位名称</b></font> </td>
<td bgColor=#f3f8ff height=30 width="8%" align=center><font color="#2c62c4"><b>采购数量</b></font> </td>
<td bgColor=#f3f8ff height=30 width="10%" align=center><font color="#2c62c4"><b>采购单价（元） </b></font></td>
<td bgColor=#f3f8ff height=30 width="10%" align=center><font color="#2c62c4"><b>是否正常 </b></font></td>
</tr>
<logic:notEmpty name="spForm" property="zcEbChengjiaoList">
	<logic:iterate id="zcEbChengjiao" name="spForm" property="zcEbChengjiaoList" type="com.ufgov.zc.common.zc.model.ZcBMerChengjiao" scope="request" indexId="indexId">
		<tr>
			<td bgColor=#FDFEFF height=26 align=center class="b_l2">
			   <label title="<bean:write name="zcEbChengjiao" property="zcCoName" />"><bean:write name="zcEbChengjiao" property="zcCoName" /></label>
			</td>
			<td bgColor=#FDFEFF height=26 align="center">
			  <label title="<bean:write name="zcEbChengjiao" property="zcCgNum" />"><bean:write name="zcEbChengjiao" property="zcCgNum" /></label>
			</td>
			<td bgColor=#FDFEFF height=26 align="left" class="b_l2">
			    <label title="<bean:write name="zcEbChengjiao" property="zcMerPrice" />"><bean:write name="zcEbChengjiao" property="zcMerPrice" /></label>
			</td>
			<td bgColor=#FDFEFF height=26 align="center">正常 </td>
		</tr>
	</logic:iterate>
</logic:notEmpty>
<logic:empty name="spForm" property="zcEbChengjiaoList">
	<tr>
		<td bgColor=#FDFEFF height=26 align=left class="b_l2">暂无记录</td>
		<td bgColor=#FDFEFF height=26 align="center">暂无记录</td>
		<td bgColor=#FDFEFF height=26 align="left" class="b_l2">暂无记录</td>
		<td bgColor=#FDFEFF height=26 align="center">暂无记录 </td>
	</tr>
</logic:empty>
</table>
</body>
</html>