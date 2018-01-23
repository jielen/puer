<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.EvalResultPrintForm"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
   EvalResultPrintForm rp = (EvalResultPrintForm)request.getAttribute("evalResultPrintForm");
   Map detal = rp.getExpertDetal();
   Map expertCol = rp.getExpertCol();
%>
<html:html lang="true">
<head>
<title> 招标项目专家评分汇总表 </title>
<meta http-equiv="content-type" content="index/html; charset=gbk" />
<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
</head>
<body>
<table border=0 cellpadding=0 cellspacing=0 >
 <tr height=38 style='mso-height-source:userset;height:28.5pt'>
  <td height=38 class=xl74 width="100%" colspan="100" style='height:28.5pt;width:100%'> 招标项目专家评分汇总表 </td>
 </tr>
 <tr height=37 style='mso-height-source:userset;height:27.75pt'>
  <td height=37 class=xl65 colspan=2 style='height:27.75pt;mso-ignore:colspan'>项目名称：<bean:write name="evalResultPrintForm" property="zcEbProj.projName" />(<bean:write name="evalResultPrintForm" property="zcEbPack.packName" />)</td>
 </tr>
 <tr class=xl69 height=29 style='mso-height-source:userset;height:21.75pt'>
  <td class=xl71 height=29 width=30 style='height:21.75pt;width:40'>序号</td>
  <td class=xl70 width=120 style='border-left:none;width:380'><center>投标单位</center></td>
  <% for (Iterator iterator = expertCol.keySet().iterator(); iterator.hasNext();) {  %>
  <td class=xl71 style='border-left:none'><%out.print(expertCol.get(iterator.next()));%></td>
  <% }%>
  <td class=xl70 width=60 style='border-left:none'>平均分</td>
  <td class=xl71 width=70 style='border-left:none'>排序</td>
 </tr>
 <logic:iterate id="evalResultPrint" name="evalResultPrintForm" property="evalPackSumResult" type="com.ufgov.zc.common.zc.model.ZcEbPackEvalResult" scope="request" indexId="indexId">
 <tr height=34 style='mso-height-source:userset;height:26.1pt'>
  <td class=xl71 height=29 width=30 style='border-top:none;height:21.75pt;width:30pt'><center><%out.print(((Integer)indexId).intValue()+1);%></center></td>
  <td class=xl70  width=120 style='border-top:none;border-left:none;width:380'><bean:write name="evalResultPrint" property="providerName" /></td>
  <% for (Iterator iterator = expertCol.keySet().iterator(); iterator.hasNext();) {  %>
  <td class=xl70 style='border-top:none;border-left:none'><center><%out.print(detal.get(evalResultPrint.getProviderCode()+iterator.next()));%></center></td>
  <% }%>
  <td class=xl70 style='border-top:none;border-left:none'><center><b><bean:write name="evalResultPrint" property="evalScore" format="#.00"/></b></center></td>
  <td class=xl71 style='border-top:none;border-left:none'><center><%out.print(((Integer)indexId).intValue()+1);%></center></td>
 </tr>
 </logic:iterate>
 <tr height=20 style='mso-height-source:userset;height:12.5pt'>
  <td height=20 class=xl65 style='height:10.5pt'></td>
 </tr>
 <tr height=20 style='height:14.25pt'>
  <td height=20 class=xl65 colspan=3 style='height:14.25pt;mso-ignore:colspan' align="left">评标委员会（谈判小组）全体成员签字：</td>
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