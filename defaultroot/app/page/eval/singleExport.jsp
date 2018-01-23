<%@page contentType="application/msword;charset=UTF-8"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ufgov.zc.server.zc.web.action.evalreport.dto.*"%>
<%
response.setHeader("Content-disposition","attachment;filename=result.doc");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:m="http://schemas.microsoft.com/office/2004/12/omml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office">

<head>
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>单一来源采购结果报告</title>
<style type="text/css">
 p.MsoNormal
	{margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman","serif";
		margin-left: 0cm;
	margin-right: 0cm;
	margin-top: 0cm;
}
.style2 {
	margin-right: 82px;
}
.xhx{
	border-bottom:1px #000000 solid;
	vertical-align:bottom;
}
.mainInforTitle{
	vertical-align:bottom;
	text-align: right;
	font-size: 16.0pt; mso-bidi-font-size: 12.0pt; font-family: 宋体; mso-ascii-font-family: &quot;Times New Roman&quot;; mso-hansi-font-family: &quot;Times New Roman&quot;; mso-bidi-font-family: &quot;Times New Roman&quot;; color: black; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA
}
.style3 {
	text-align: center;
}
#chTable td{
    text-align: center;
}
#expertTable td{
    text-align: center;
}
</style>
</head>
<%!
    private String getNotNullVal(Object v){
		return v == null ? "&nbsp;":v.toString();
	}
%>
<%
    ProjReportDto projReportDto = (ProjReportDto)request.getAttribute("proj");
%>
<body>

<p class="MsoNormal" align="center" style="text-align: center">
<b style="mso-bidi-font-weight: normal">
<span style="font-size: 36.0pt; mso-bidi-font-size: 12.0pt; font-family: 方正姚体; mso-ascii-font-family: &quot;Times New Roman&quot;; color: black">
单一来源采购结果报告</span></b><b style="mso-bidi-font-weight: normal"><span lang="EN-US" style="font-size: 36.0pt; mso-bidi-font-size: 12.0pt; mso-fareast-font-family: 方正姚体; color: black"><o:p></o:p></span></b></p>
<table style="width: 500px" align="center" border="0"  class="style2">
	<tr>
		<td style="width: 40%; height: 74px" class="mainInforTitle"></td>
		<td style="height: 74px"></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 50px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		文件编号</b></td>
		<td style="height: 25px" class="xhx"><%=getNotNullVal(projReportDto.getViewProjCode())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 84px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		项目名称</b></td>
		<td style="height: 84px" class="xhx"><%=getNotNullVal(projReportDto.getProjName())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 78px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		
		项目负责人</b></td>
		<td style="height: 78px" class="xhx"><%//=getNotNullVal(projReportDto.getManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 78px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		
		科室负责人</b></td>
		<td style="height: 78px" class="xhx"><%//=getNotNullVal(projReportDto.getKsManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 87px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		
		监 审 科</b></td>
		<td style="height: 87px" class="xhx"><%//=getNotNullVal(projReportDto.getJskManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 92px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		
		中心领导</b></td>
		<td style="height: 92px" class="xhx"><%//=getNotNullVal(projReportDto.getZxLeader())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 88px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">
		
		<nobr>采购人签字（盖章）</nobr></b></td>
		<td style="height: 88px" class="xhx">&nbsp;</td>
	</tr>
	<tr>
		<td style="height: 133px;" colspan="2" class="style3" valign="bottom">
		<b style="mso-bidi-font-weight:normal">
		<span style="font-size:16.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-bidi-font-family:
&quot;Times New Roman&quot;;color:black;mso-font-kerning:1.0pt;mso-ansi-language:EN-US;
mso-fareast-language:ZH-CN;mso-bidi-language:AR-SA">西安市市级单位政府采购中心</span></b></td>
	</tr>
	<tr>
		<td colspan="2" class="style3"><b style="mso-bidi-font-weight:normal">
		<span lang="EN-US" style="font-size:16.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;
mso-bidi-font-family:&quot;Times New Roman&quot;;color:black;mso-font-kerning:1.0pt;
mso-ansi-language:EN-US;mso-fareast-language:ZH-CN;mso-bidi-language:AR-SA"><%=projReportDto.getCurrentDate()%></span></b></td>
	</tr>
	<tr>
		<td style="width: 317px">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>

<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p><b>西安市市级单位政府采购中心：</b></p>
<p style="line-height: 150%">&nbsp;<%=projReportDto.getCoName()%>,<%=projReportDto.getProjName()%>政府采购项目（项目总预算<%=projReportDto.getBudgetSum()%>元，
其中：
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
	PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
%>
<%=pack.getPackName()%>段预算<%=pack.getBudgetSum()%>元
	<%if(i < projReportDto.getPackList().size() - 1){%>
	，
	<%
	}
}%>
），于<%=projReportDto.getOpenBidDate()%>在<%=projReportDto.getOpenBidAddress()%>采用单一来源采购的方式组织实施。谈判小组按照单一来源采购文件的要求，本着公开、公平、公正的原则，经过资质审查、询标、谈判等过程，对参与供应商的谈判响应文件进行了综合评审。具体报告如下：<br />
一、 谈判响应单位<br />
本次政府采购项目（<%=projReportDto.getViewProjCode()%>号），
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
	PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
	out.print(pack.getPackName()+"单一来源响应供应商为");
	for(int j = 0;j < pack.getSingupSupplier().size();j++){
		SupplierDto sup = (SupplierDto)(pack.getSingupSupplier().get(j));
%>
		<b><%=sup.getSupplierName()%></b>，
<%
	}
}
%>
。<br />

二、结论及成交价格<br />
谈判小组成员依照单一来源采购文件的资格审核和评审办法的要求，对响应供应商的谈判资格、谈判响应文件进行了综合评审，结论为完全符合采购文件的要求。谈判小组建议
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
	PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
	if(pack.getWinBidSupplierName() != null){
%>
	<b><%=pack.getWinBidSupplierName()%><b> 为<%=pack.getPackName()%>的成交供应商，成交价为<b><%=pack.getWinBidSum()%></b>元；
<%
    }else{
%>
	<%=pack.getPackName()%>还未确定中标供应商；
<%
	}
}
%>
。<br />
三、参会人员名单及谈判小组成员名单<br />
</p>
<table id="chTable" cellpadding="0" cellspacing="0" border="1" align="center" style="border-color:black;width: 600px;border-collapse:collapse;">
	<tr height="33">
		<td style="width: 40px" rowspan="20">参<br />
		会<br />
		人<br />
		员</td>
		<td>采购单位</td>
		<td>姓名</td>
		<td>职务／称</td>
	</tr>
<%
for(int i = 0;i < projReportDto.getCaiGouDanWeiRens().size();i++){
    PersonDto person = (PersonDto)(projReportDto.getCaiGouDanWeiRens().get(i));
%>
	<tr height="33">
		<td><%=i > 0 ? "" : projReportDto.getCoName()%>&nbsp;</td>
		<td><%=person.getUserName()%>&nbsp;</td>
		<td><%=getNotNullVal(person.getTechTitle())%>&nbsp;</td>
	</tr>
<%
}
if(projReportDto.getCaiGouDanWeiRens().size() == 0){
%>
	<tr height="33">
		<td><%=projReportDto.getCoName()%>&nbsp;</td>
		<td> &nbsp;</td>
		<td> &nbsp;</td>
	</tr>
	<tr height="33">
		<td> &nbsp;</td>
		<td> &nbsp;</td>
		<td> &nbsp;</td>
	</tr>
<%
}
%>
	<tr height="33">
		<td>采购代理机构</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr height="33">
		<td>西安市市级单位政府采购中心</td>
		<td>黄跃东</td>
		<td>副主任</td>
	</tr>
	<tr height="33">
		<td>&nbsp;</td>
		<td>宋全喜</td>
		<td>监审组长</td>
	</tr>
	<tr height="33"">
		<td>项目经办人</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
<%
for(int i = 0;i < projReportDto.getCaiGouZhongXinJbRens().size();i++){
    PersonDto person = (PersonDto)(projReportDto.getCaiGouZhongXinJbRens().get(i));
%>
	<tr height="33">
		<td>&nbsp;</td>
		<td><%=getNotNullVal(person.getUserName())%>&nbsp;</td>
		<td><%=getNotNullVal(person.getTechTitle())%>&nbsp;</td>
	</tr>
<%
}
if(projReportDto.getCaiGouZhongXinJbRens().size() == 0){
%>
	<tr height="33">
		<td>&nbsp;</td>
		<td> &nbsp;</td>
		<td> &nbsp;</td>
	</tr>
<%
}
%>
</table>

<table id="expertTable" cellpadding="0" cellspacing="0" border="1" align="center" style="border-color:black;width: 600px;border-collapse:collapse;">
	<tr height="33" >
		<td rowspan="50" style="width:36px">谈<br />
		判<br />
		小<br />
		组<br />
		成<br />
		员</td>
		<td style="height: 2px">姓 名</td>
		<td style="height: 2px">职务／称</td>
	</tr>
<%
for(int i = 0;i < projReportDto.getExperts().size();i++){
    PersonDto person = (PersonDto)(projReportDto.getExperts().get(i));
%>
	<tr height="33">
		<td><%=getNotNullVal(person.getUserName())%>&nbsp;</td>
		<td><%=getNotNullVal(person.getTechTitle())%>&nbsp;</td>
	</tr>
<%
}
%>
</table>

</body>

</html>
