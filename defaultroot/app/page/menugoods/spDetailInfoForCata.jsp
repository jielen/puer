<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gbk">
<link href="../../css/content.css" type="text/css" rel="stylesheet">
<style type=text/css>TD {
	TEXT-INDENT: 15px
}
</style>
<script type="text/javascript">
var highlightcolor='#fefbdc';//鼠标经过背景色
var clickcolor='#e0efff';//鼠标选中背景色
</script>
<script src="../../js/tableHightLight.js" type="text/javascript"></script>
<body style="width:100%">
<table onMouseOver="changeto();" onMouseOut="changeback();" cellSpacing=1 cellPadding=0 width="100%" bgColor="#a8cefe">
  <tbody>
  <%
     com.ufgov.zc.server.zc.web.form.SpForm spForm = (com.ufgov.zc.server.zc.web.form.SpForm)request.getAttribute("spForm");
     String remark = spForm.getZcBMerchandise().getZcRemark();
     String collocate = spForm.getZcBMerchandise().getZcMerCollocate();
     String zcIsJnjs = spForm.getZcBMerchandise().getZcIsJnjs();
     String zcMerIsLshb = spForm.getZcBMerchandise().getZcMerIsLshb();
  %>
<tr id="nc">
	<td bgColor=#f3f8ff height=30 width="20%" align=middle><font color="#2c62c4"><b>属性名称</b></font> </td>
	<td bgColor=#f3f8ff height=30 width="80%" align=middle><font color="#2c62c4"><b>属性详细配置值</b></font> </td>
</tr>
<logic:notEmpty name="spForm" property="zcCatalogueProp">
	<logic:iterate id="sp"  name="spForm" property="zcCatalogueProp" type="com.ufgov.zc.common.zc.model.ZcBMerCatalogueProp" scope="request">
		  <TR>
		   <TD align=center width=180 bgColor=#f3f8ff height=30><FONT color=#2c62c4><b><bean:write name="sp" property="zcCataPropChName"/>：</b></FONT></TD>
		   <TD align=left bgColor=#FDFEFF><bean:write name="sp" property="zcCataPropValue"/></TD>
		 </TR>
	</logic:iterate>
</logic:notEmpty>
<logic:empty name="spForm" property="zcCatalogueProp">
	<TR>
	 <TD align=right width=180 bgColor=#f3f8ff height=30><FONT color=#2c62c4><b>暂无记录</b></FONT></TD>
	 <TD align=left bgColor=#FDFEFF>暂无记录</TD>
	</TR>
</logic:empty>
</tbody>
</table>
</body>
</html>
