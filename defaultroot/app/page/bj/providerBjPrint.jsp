<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import ="com.anyi.gp.pub.SessionUtils"%>
<%@page import="com.ufgov.zc.server.zc.web.form.ProviderBjPrintForm"%>
<%@page import="com.ufgov.zc.common.zc.model.EvalPackProvider"%>
<%@page import="java.text.DecimalFormat" %>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbEcbjItem"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
	ProviderBjPrintForm bjForm = (ProviderBjPrintForm) request.getAttribute("providerBjPrintForm");
	int bjNo = bjForm.getBjNo();
	List providerList = bjForm.getProviderList();
	Map providerBjMap = bjForm.getProviderBjMap();
	SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
	DecimalFormat noformat = new DecimalFormat("0.00");
%>
<html:html lang="true">
<head>
<title>采购项目招标报价表</title>
<meta http-equiv="content-type" content="index/html; charset=UTF-8" />
<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
</head>
<body>
<table border=0 width="90%" cellpadding=0 cellspacing=0>
	<tr height=38 style='mso-height-source: userset; height: 28.5pt'>
		<td height=38 class=xl74 width="100%" colspan="100"
			style='height: 28.5pt; width: 100%'>
		<center>采购项目招标报价表</center>
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
	<tr>
		<td height=37 class=xl65 colspan=100
			style='height: 27.75pt; mso-ignore: colspan'>单位：元</td>
	</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0>
	<tr>
		<td rowspan=1 class=xl70 width=40>序号</td>
		<td rowspan=1 class=xl70 width=300 style='border-left: none;'><center>供应商名称</center></td>
		<%
			if (bjNo == 1) {
		%>
		<td rowspan=1 class=xl70 width=300 style='border-left: none;'><center> 总报价</center></td>
				<%
			} else {
					for (int i = 1; i < bjNo+1; i++) {
		%>
		<td class=xl70 style='border-left: none'><%out.print("第" +i+ "报价");%></td>
		<%
			}%>
		<td rowspan=1 class=xl70 width=120 style='border-left: none;'><center>最终报价</center></td>
	<%		}
		%>

		<td rowspan=1 class=xl70 width=120 style='border-left: none;'><center>供货日期(工期)</center></td>
		
	</tr>
		<%
			for (int j = 0; j < providerList.size(); j++) {
				EvalPackProvider provider=(EvalPackProvider)providerList.get(j);
		%>
		<tr class=xl69 height=29
		style='mso-height-source: userset; height: 21.75pt'>
		<td class=xl71 rowspan=1 colspan=1 style='border-top: none;'><%=(j + 1)%></td>
		<td class=xl71 rowspan=1 colspan=1 style='border-left: none; border-top: none;'><%=provider.getProviderName()%></td>
	
		<%
		List bjList=(List)providerBjMap.get(provider.getProviderName());
		
		if(bjList==null||bjList.size()==0){
		  if(bjNo==1){
		    %>
				<td class=xl71 rowspan=1 colspan=1
				style='border-left: none; border-top: none;'><%="--" %></td>	
			<%	
		  }else{
		  	for(int k=0;k<bjNo;k++){
					%>
					<td class=xl71 rowspan=1 colspan=1
					style='border-left: none; border-top: none;'><%= "--" %></td>	
				<%	}
		  	
		  %>	
		  <td class=xl71 rowspan=1 colspan=1
				style='border-left: none; border-top: none;'><%= "--" %></td>
	
			<% 		
		  }
		  %>
		   <td class=xl71 rowspan=1 colspan=1
			style='border-left: none; border-top: none;'><%= "--" %></td>
			<% 
		}else{
		ZcEbEcbjItem item2=(ZcEbEcbjItem)bjList.get(bjList.size()-1);
		
			if(bjNo==1){
				%>
				<td class=xl71 rowspan=1 colspan=1
				style='border-left: none; border-top: none;'><%=item2.getBjSum() == null ? "&--;" :noformat.format(item2.getBjSum().doubleValue())%></td>	
			<%	
			  
			}else{
				for(int k = 0; k < bjNo; k++){
					if(k < bjList.size()){
					ZcEbEcbjItem item=(ZcEbEcbjItem)bjList.get(k);
					%>
					<td class=xl71 rowspan=1 colspan=1
					style='border-left: none; border-top: none;'><%=item.getBjSum() == null ? "&--;" :noformat.format(item.getBjSum().doubleValue())%></td>	
				<%	
						}else{
					%>
					<td class=xl71 rowspan=1 colspan=1
					style='border-left: none; border-top: none;'>--</td>	
				<%	
					    }
					}
				if(bjList.size()>=1&&bjNo>=2){
			%>
			<td class=xl71 rowspan=1 colspan=1
				style='border-left: none; border-top: none;'><%=item2.getBjSum() == null ? "--" :noformat.format(item2.getBjSum().doubleValue())%></td>
			<%	} 
			}
		%>
		<td class=xl71 rowspan=1 colspan=1
			style='border-left: none; border-top: none;'><%=item2.getPromiseWorkDate() == null ? "--" :item2.getPromiseWorkDate()%></td>		
	
	<% 
		}%>
		</tr>
		<%
			}
		%>
	
<tr height=20 style='mso-height-source:userset;height:12.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left"> </td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时 间：
  <%if(bjForm.getOpenBidDate()!=null) {%> 
    <%=df.format(bjForm.getOpenBidDate()) %>  
 <% }
  %>
  </td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
</table>
</body>
</html:html>