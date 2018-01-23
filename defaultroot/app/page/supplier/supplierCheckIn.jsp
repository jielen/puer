<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbSignup"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbSignup" %>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail" %>
<%@page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html lang="true">
<head>
	<title>供应商签到登记表</title>
	<meta http-equiv=Content-Type content="text/html; charset=gb2312">
	<link href="../../css/supplierCheckIn.css" type="text/css" rel="Stylesheet" />
 <style>
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:.75in .7in .75in .7in;
	mso-header-margin:.3in;
	mso-footer-margin:.3in;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
-->
</style>
 
</head>
<body lang=ZH-CN
	style='tab-interval: 21.0pt; text-justify-trim: punctuation'>
	<div class=Section1 style='layout-grid: 15.6pt'>
<table border=0 cellpadding=0 cellspacing=0 width=954 style='border-collapse:
 collapse;table-layout:fixed;width:717pt'>
 <col width=55 style='mso-width-source:userset;mso-width-alt:1760;width:41pt'>
 <col width=113 style='mso-width-source:userset;mso-width-alt:3616;width:85pt'>
 <col width=72 style='width:54pt'>
 <col width=98 style='mso-width-source:userset;mso-width-alt:3136;width:74pt'>
 <col width=72 style='width:54pt'>
 <col width=97 style='mso-width-source:userset;mso-width-alt:3104;width:73pt'>
 <col width=105 style='mso-width-source:userset;mso-width-alt:3360;width:79pt'>
 <col width=111 style='mso-width-source:userset;mso-width-alt:3552;width:83pt'>
 <col width=133 style='mso-width-source:userset;mso-width-alt:4256;width:100pt'>
 <col width=98 style='mso-width-source:userset;mso-width-alt:3136;width:74pt'>
 <tr height=26 style='mso-height-source:userset;height:19.5pt'>
  <td colspan=10 height=26 class=xl72 width=954 style='height:19.5pt;
  width:717pt'>西安市市级单位政府采购中心标准格式表2</td>
 </tr>
 <tr height=62 style='mso-height-source:userset;height:46.5pt'>
  <td colspan=10 height=62 class=xl73 style='height:46.5pt'>供应商签到登记表</td>
 </tr>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td colspan=6 height=40 class=xl75 style='height:30.0pt'>项目名称：西安市采购中心</td>
  <td colspan=4 class=xl74>收件人：</td>
 </tr>
 <tr height=44 style='mso-height-source:userset;height:33.0pt'>
  <td rowspan=2 height=94 class=xl65 style='height:70.5pt'>序号</td>
  <td rowspan=2 class=xl65>供应商名称</td>
  <td rowspan=2 class=xl65>标段</td>
  <td colspan=4 class=xl65 style='border-left:none'>签到、递交文件记录</td>
  <td rowspan=2 class=xl65>授权代理人</td>
  <td colspan=2 class=xl65 style='border-left:none'>备注</td>
 </tr>
 <tr height=50 style='height:37.5pt'>
  <td height=50 class=xl65 style='height:37.5pt;border-top:none;border-left:
  none'>签到时间</td>
  <td class=xl65 style='border-top:none;border-left:none'>保证金</td>
  <td class=xl65 style='border-top:none;border-left:none'>资质文件</td>
  <td class=xl66 width=105 style='border-top:none;border-left:none;width:79pt'>投标（响应）文件</td>
  <td class=xl65 style='border-top:none;border-left:none'>快捷联络方式</td>
  <td class=xl65 style='border-top:none;border-left:none'>退件签字</td>
 </tr>
 <%
 List projList = (List)request.getAttribute("supplierList");
 for(int i = 0; i < projList.size(); i++){
    ZcEbSignup zcEbSignup = (ZcEbSignup)projList.get(i);
    List packList = zcEbSignup.getSignupPacks();
    for(int j = 0; j < packList.size(); j++){
      ZcEbSignupPackDetail pack = (ZcEbSignupPackDetail)packList.get(j);
 %>
      <tr height=40 style='mso-height-source:userset;height:30.0pt'>
	  <td height=40 class=xl69 style='height:30.0pt;border-top:none'><%=j + 1 %></td>
	  <td class=xl67 style='border-top:none;border-left:none'><%=zcEbSignup.getProviderName() %></td>
	  <td class=xl67 style='border-top:none;border-left:none'><%=pack.getPackName() %></td>
	  <td class=xl68 style='border-top:none;border-left:none'><%=pack.getIsPayGuarantee() %></td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
	  <td class=xl68 style='border-top:none;border-left:none'>　</td>
 </tr>
 <%      
    }
 }
  %>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td height=40 class=xl69 style='height:30.0pt;border-top:none'>1</td>
  <td class=xl67 style='border-top:none;border-left:none'>　</td>
  <td class=xl67 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
  <td class=xl68 style='border-top:none;border-left:none'>　</td>
 </tr>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td height=40 class=xl69 style='height:30.0pt;border-top:none'>2</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
 </tr>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td height=40 class=xl69 style='height:30.0pt;border-top:none'>3</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
 </tr>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td height=40 class=xl69 style='height:30.0pt;border-top:none'>4</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
 </tr>
 <tr height=40 style='mso-height-source:userset;height:30.0pt'>
  <td height=40 class=xl69 style='height:30.0pt;border-top:none'>5</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl70 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
  <td class=xl71 style='border-top:none;border-left:none'>　</td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=55 style='width:41pt'></td>
  <td width=113 style='width:85pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=98 style='width:74pt'></td>
  <td width=72 style='width:54pt'></td>
  <td width=97 style='width:73pt'></td>
  <td width=105 style='width:79pt'></td>
  <td width=111 style='width:83pt'></td>
  <td width=133 style='width:100pt'></td>
  <td width=98 style='width:74pt'></td>
 </tr>
 <![endif]>
</table>



		


		
	</div>
</body>
</html:html>