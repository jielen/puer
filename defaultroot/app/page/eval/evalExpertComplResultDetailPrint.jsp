<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.EvalResultPrintForm"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbExpertEvalResult"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbFormulaItem"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
   EvalResultPrintForm rp = (EvalResultPrintForm)request.getAttribute("evalResultPrintForm");
   List formulaItemList=rp.getComplFormulaItemList();
   List evalResultList=rp.getEvalResultSCList();
   Map expertDetal = rp.getExpertDetal();
%>
<html:html lang="true">
<head>
<title> 招标项目专家符合性评审明细表 </title>
<meta http-equiv="content-type" content="index/html; charset=gbk" />
<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
</head>
<body>
<table border=0 width="90%" cellpadding=0 cellspacing=0 >
 <tr height=38 style='mso-height-source:userset;height:28.5pt'>
  <td height=38 class=xl74 width="100%" colspan="100" style='height:28.5pt;width:100%'><center> 招标项目专家符合性评审明细表 </center></td>
 </tr>
</table>
<table border=0 cellpadding=0 cellspacing=0> 
 <tr height=37 style='mso-height-source:userset;height:27.75pt'>
  <td height=37 class=xl65 colspan=100 style='height:27.75pt;mso-ignore:colspan'>项目名称：<bean:write name="evalResultPrintForm" property="zcEbProj.projName" /></td>
 </tr> 
  <tr>
   <td height=37 class=xl65 colspan=100 style='height:27.75pt;mso-ignore:colspan'>标段：<bean:write name="evalResultPrintForm" property="zcEbPack.packName" /></td>
  </tr>
 <tr height=37 style='mso-height-source:userset;height:27.75pt'>
  <td height=37 class=xl65 colspan=100 style='height:27.75pt;mso-ignore:colspan'>评审专家：<bean:write name="evalResultPrintForm" property="expertName" /></td>
 </tr>
</table>
 <table border=0 cellpadding=0 cellspacing=0 >
 <tr>
  <td rowspan=1 class=xl71 width=40>序号</td>
  <td rowspan=1 class=xl71 width=240 style='border-left:none;'><center>投标单位</center></td>
  <% 
     for (int j=0;j<formulaItemList.size();j++) {
       ZcEbFormulaItem zcEbFormulaItem = (ZcEbFormulaItem)formulaItemList.get(j);
  %>
  	<td class=xl70 width=40  rowspan=1 colspan=1 style='border-left:none'><%out.print(zcEbFormulaItem.getName());%>
   </td> 
   <%}%>	 
  <td rowspan=1 class=xl71 width=40 style='border-left:none'>符合性评审结果</td>
  <td rowspan=1 class=xl71 width=300 style='border-left:none'>不通过原因</td>
 </tr>
 <%
 for(int i=0;i<evalResultList.size();i++){
	 ZcEbExpertEvalResult result=(ZcEbExpertEvalResult)evalResultList.get(i);
	 %>
	 <tr class=xl69 height=29 style='mso-height-source:userset;height:21.75pt'>
	  <td class=xl71 rowspan=1 colspan=1 style='border-top: none;'><%=(i+1)%></td>
	  <td class=xl71 rowspan=1 colspan=1 style='border-left:none;border-top: none;'><%=result.getProviderName()%></td>
	 <% 
      for( int k=0;k<formulaItemList.size();k++){
          ZcEbFormulaItem zcEbFormulaItem = (ZcEbFormulaItem)formulaItemList.get(k);
		  String compliaceValue=((Map)expertDetal.get(result.getProviderCode())).get(zcEbFormulaItem.getItemCode()).toString();
		  %>  
		  <td class=xl71 rowspan=1 colspan=1 style='border-left:none;border-top: none;'><%="1".equals(compliaceValue) ? "&radic;"
		          : "&times;"%></td>		  
  <% 
     }
  %>
 	<td class=xl71 rowspan=1 colspan=1 style='border-left:none;border-top: none;'><b><%=("1").equals(result.getComplianceResult()) ? "通过"
          :"不通过"%></b></td>
   <td class=xl71 rowspan=1 colspan=1 style='border-left:none;border-top: none;'><b><%=("").equals(result.getUnPassReason())?"&nbsp;":result.getUnPassReason()%></b></td>
   </tr>
  <% 
     }
  %>

</table>

<table border=0 cellpadding=0 cellspacing=0 >
 <tr height=20 style='mso-height-source:userset;height:12.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">&nbsp;&nbsp;&nbsp;专家签字：</td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</td>
 </tr>
 <tr height=20 style='mso-height-source:userset;height:10.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <!-- 
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陕西省省级单位政府采购中心</td>
 </tr>
  -->
</table>
</body>
</html:html>