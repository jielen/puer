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
<div style="text-align: left;height:20px;padding-top:5px;"><b>生产厂商：</b></div>
<table onMouseover="changeto()" onMouseout="changeback()" width="100%" bgColor="#a8cefe" border="0" cellSpacing="1" valign="top">
<TR>
<TD bgColor=#f3f8ff height=22 width="15%" align=middle><FONT color="#2c62c4"><b>名称</b></FONT> </TD>
<TD bgColor=#FDFEFF height=26 width="35%" align=middle><bean:write name="spForm" property="zcBBrand.zcZbsName" /></TD>
<TD bgColor=#f3f8ff height=22 width="15%" align=middle><FONT color=#2c62c4><b>经营地址</b></FONT> </TD>
<TD bgColor=#FDFEFF height=26 width="35%" align=middle><bean:write name="spForm" property="zcBBrand.zcLinkAdd" /></TD>
</TR>
<TR>
<TD bgColor=#f3f8ff height=22 width="15%" align=middle><FONT color=#2c62c4><b>联系人</b></FONT> </TD>
<TD bgColor=#FDFEFF height=26 width="35%" align=middle><bean:write name="spForm" property="zcBBrand.zcLinkman" /></TD>
<TD bgColor=#f3f8ff height=22 width="15%" align=middle><FONT color=#2c62c4><b>联系电话</b></FONT> </TD>
<TD bgColor=#FDFEFF height=26 width="35%"align=middle><bean:write name="spForm" property="zcBBrand.zcLinkmanTel" /></TD>
</TR>
</table>
<div style="text-align: left;padding-top:10px;height:25px;"><b>供货商：</b></div>
<table onMouseover="changeto()" onMouseout="changeback()" width="100%" class="itable" id="supplier" bgColor="#a8cefe" border="0" cellSpacing="1" cellPadding="0" valign="top" name="supplier">
<tr id="nc">
<td bgColor=#f3f8ff height=30 width="22%" align=middle><font color="#2c62c4"><b>名称</b></font> </td>
<td bgColor=#f3f8ff height=30 width="8%" align=middle><font color="#2c62c4"><b>满意度</b></font> </td>
<td bgColor=#f3f8ff height=30 width="10%" align=middle><font color="#2c62c4"><b>联系人 </b></font></td>
<td bgColor=#f3f8ff height=30 width="11%" align=middle><font color="#2c62c4"><b>联系电话</b></font> </td>
<td bgColor=#f3f8ff height=30 width="19%" align=middle><font color="#2c62c4"><b>经营地址</b></font> </td>
<td bgColor=#f3f8ff height=30 width="7%" align=middle><font color="#2c62c4"><b>是否缺货</b></font> </td>
</tr>
<logic:iterate id="supplier" name="spForm" property="suppliers" type="com.ufgov.zc.common.zc.model.ZcEbSupplier" scope="request" indexId="indexId">
 <tr>
    <td bgColor=#FDFEFF height=26 align=left class="b_l2">
        <%  String url = supplier.getUrl();
            if(url != null && !"".equals(url.trim())){ %>
          <a title="<bean:write name="supplier" property="name" />" href="<%=url%>" target="_blank"><bean:write name="supplier" property="name" /></a>
        <% } else {%>
          <label title="<bean:write name="supplier" property="name" />"><bean:write name="supplier" property="name" /></label>
        <% } %>
    </td>
    <td bgColor=#FDFEFF height=26 align="center">暂无评价</td>
    <td bgColor=#FDFEFF height=26 align="center"><bean:write name="supplier" property="linkMan" /></td>
    <td bgColor=#FDFEFF height=26 align="center">
      <% pageContext.setAttribute("phone",ZcSUtil.substring(supplier.getPhone(),14,".."));%>
      <label title="<bean:write name="supplier" property="phone" />"><bean:write name="phone" scope="page"/></label>
    </td>
    <td bgColor=#FDFEFF height=26 align="left" class="b_l2">
        <% pageContext.setAttribute("address",ZcSUtil.substring(supplier.getAddress(),36,".."));%>
        <label title="<bean:write name="supplier" property="address" />"><bean:write name="address" scope="page"/></label>
    </td>
    <td bgColor=#FDFEFF height=26 align="center">正常 </td>
  </tr>
</logic:iterate>
</table>
</body>
</html>