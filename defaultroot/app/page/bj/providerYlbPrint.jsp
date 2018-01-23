<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.ufgov.zc.server.zc.web.form.ProviderBjPrintForm"%>
<%@page import="com.ufgov.zc.common.zc.model.EvalPackProvider"%>
<%@page import="com.ufgov.zc.server.zc.web.ylbTable.entity.*"%>
<%@page import="com.ufgov.zc.server.zc.web.ylbTable.*"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbEcbjItem"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
	ProviderBjPrintForm bjForm = (ProviderBjPrintForm) request
			.getAttribute("providerBjPrintForm");
	ChangeRMB changeRmb=new ChangeRMB();
	int bjNo = bjForm.getBjNo();
	List providerList = bjForm.getProviderList();
	Map providerBjMap = bjForm.getProviderBjMap();
	Map providerYlbMap=bjForm.getProviderYlbMap();
	DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
%>

<%@page import="java.text.SimpleDateFormat"%><html:html lang="true">
<head>
<title>采购项目招标报价明细表</title>
<meta http-equiv="content-type" content="index/html; charset=UTF-8" />
<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
</head>
<body>
<table border=0 width="90%" cellpadding=0 cellspacing=0>
	<tr height=38 style='mso-height-source: userset; height: 28.5pt'>
		<td height=38 class=xl74 width="100%" colspan="100"
			style='height: 28.5pt; width: 100%'>
		<center>采购项目招标报价明细表</center>
		</td>
	</tr>
</table>
<table border=0 cellpadding=0 cellspacing=0>
	<tr height=37 style='mso-height-source: userset; height: 27.75pt'>
		<td height=37 class=xl65 colspan=100
			style='height: 27.75pt; mso-ignore: colspan'>项目名称：<bean:write
			name="providerBjPrintForm" property="zcEbProj.projName" /></td>
	</tr>
	<tr>
		<td height=37 class=xl65 colspan=100
			style='height: 27.75pt; mso-ignore: colspan'>标段：<bean:write
			name="providerBjPrintForm" property="zcEbPack.packName" /></td>		
	</tr>
</table>
<br></br>
<%
for (int j = 0; j < providerList.size(); j++) {
	EvalPackProvider provider=(EvalPackProvider)providerList.get(j);	
	%>	
	<table>
	<tr  height=37  style='mso-height-source: userset; height: 27.75pt'>
		<td height=37 class=xl65 colspan=100
			style='height: 27.75pt; mso-ignore: colspan'><b>供应商：<%=provider.getProviderName() %></b></td>
		<!-- <td height=37 class=xl65 colspan=100
			style='height: 27.75pt; mso-ignore: colspan'>报价信息</td> -->
	</tr>
	<tr>
	<td><%
		List ylbTableList=(List)providerYlbMap.get(provider.getProviderName());
		List bjList=(List)providerBjMap.get(provider.getProviderName());

		for(int i=0;i<ylbTableList.size();i++){
			XmlTable xmlTable=(XmlTable)ylbTableList.get(i);
			ZcEbEcbjItem item=(ZcEbEcbjItem)bjList.get(i);
			
			%>
			<table border=0 cellpadding=0 cellspacing=0>
			<tr >
			<%
				if(ylbTableList.size()>1){%>
				<td class=xl72 >第<%=(i+1)%>次报价<td>	
				<% 	
				}
			%>
			  <td class=xl72 ><b>总报价：<%=item.getBjSum()%>(元)</b><td>
			   <td class=xl72 ><b>大写：<%=	changeRmb.doChange(item.getBjSum().toString())%></b><td>
			<tr>
			<tr style='mso-height-source: userset;background-color:#ccc;'>
			<%XmlTableColumn firstColumn=(XmlTableColumn)xmlTable.getColumns().get(0);%>
			<td class=xl71 rowspan=1 colspan=1><b><%=firstColumn.getCaption()%></b></td>
			<%	
			for(int m=1;m<xmlTable.getColumns().size();m++){
				XmlTableColumn column=(XmlTableColumn)xmlTable.getColumns().get(m);
				%>
			<td class=xl71 rowspan=1 colspan=1
			style='border-left: none;'><b><%=column.getCaption()%></b></td>	
			<%
				}
			%>
			</tr>
			<%
			for(int k=0;k<xmlTable.getRows().size();k++){
				XmlTableRow row=(XmlTableRow)xmlTable.getRows().get(k);
				%>
			<tr  style='mso-height-source: userset; '>
				<% 
				List cells=row.getCells();%>
				<td class=xl71 rowspan=1 colspan=1
				style=' border-top: none;'><%=(k+1)%></td>
			<%
				for(int n=1;n<cells.size();n++){
				XmlTableCell cell=(XmlTableCell)cells.get(n);
			%>
			<td class=xl71 rowspan=1 colspan=1
			style='border-left: none; border-top: none;'><%=cell.getValue()==null||"".equals(cell.getValue()) ? "&nbsp;":cell.getValue()%>
			</td>	
			<%
				}
			%>
			</tr>
			<%
			}
			%>
			</table>	
		<% 
		}
		%>	
	</td>
	</tr>
	</table>
	<br></br>
<%
}
%>
<br></br>
<table>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left"> </td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时 间：
  <%=df.format(bjForm.getOpenBidDate()) %>
  </td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
</table>
</body>
</html:html>