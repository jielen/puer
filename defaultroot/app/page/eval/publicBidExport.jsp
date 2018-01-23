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
<title>公开招标采购结果报告</title>
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
评标结果报告</span><span lang="EN-US" style="font-size: 36.0pt; mso-bidi-font-size: 12.0pt; mso-fareast-font-family: 方正姚体; color: black"><o:p></o:p></span></b></p>
<table style="width: 500px" align="center" border="0" class="style2">
	<tr>
		<td style="width: 40%; height: 74px" class="mainInforTitle"></td>
		<td style="height: 74px"></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 50px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">文件编号</b></td>
		<td style="height: 25px" class="xhx"><%=getNotNullVal(projReportDto.getViewProjCode())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 84px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">项目名称</b></td>
		<td style="height: 84px" class="xhx"><%=getNotNullVal(projReportDto.getProjName())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 78px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">项目负责人</b></td>
		<td style="height: 78px" class="xhx"><%//=getNotNullVal(projReportDto.getManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 78px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">科室负责人</b></td>
		<td style="height: 78px" class="xhx"><%//=getNotNullVal(projReportDto.getKsManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 87px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">监 审 科</b></td>
		<td style="height: 87px" class="xhx"><%//=getNotNullVal(projReportDto.getJskManager())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 92px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal">中心领导</b></td>
		<td style="height: 92px" class="xhx"><%//=getNotNullVal(projReportDto.getZxLeader())%></td>
	</tr>
	<tr>
		<td style="width: 317px; height: 88px" class="mainInforTitle">
		<b style="mso-bidi-font-weight: normal"><nobr>采购人签字（盖章）</nobr></b></td>
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
），由西安市市级单位政府采购中心采用公开招标的方式组织实施，于<%=projReportDto.getOpenBidDate()%>在<%=projReportDto.getOpenBidAddress()%>进行。
<%=projReportDto.getBidReportPublishDate()%>在陕西省政府采购网发出公告后，有<%=projReportDto.getSingupSupplierCount()%>家供应商前来登记并购买招标文件，其中
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
   PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
   out.print(pack.getPackName());
   out.print(":");
   out.print(pack.getSingupSupplierCount());
   out.print("家,分别是：");
   for(int j= 0;j < pack.getSingupSupplier().size();j++){ 
        SupplierDto supplier = (SupplierDto)pack.getSingupSupplier().get(j);
        out.print(supplier.getSupplierName());
	    out.print(",");
   }
   out.print(";");
}
%>
。投标截止时间前实际收到<%=projReportDto.getSubmitFileSupplierCount()%>家供应商的投标文件，其中：
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
   PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
   out.print(pack.getPackName());
   out.print(":");
   out.print(pack.getSubmitFileSupplierCount());
   out.print("家,分别是：");
   for(int j= 0;j < pack.getSingupSupplier().size();j++){ 
       SupplierDto supplier = (SupplierDto)pack.getSingupSupplier().get(j);
	   out.print(supplier.getSupplierName());
	   out.print(",");
   }
   out.print(";");
}
%>。（<b>如果投标截止时间结束后参加投标的供应商不足三家的，除采购任务取消情形外，应注明招标文件没有不合理条款、招标公告时间及程序符合规定，现场征求采购人意见同时报政府采购管理部门同意，就地转为竞争性谈判、询价或者单一来源方式采购。如果招标文件存在不合理条款、招标公告时间及程序不符合规定，应予废标，并依法重新招标。如在评标期间，出现符合专业条件的供应商或者对招标文件作出实质响应的供应商不足三家情形的，参照上述执行，政府采购法第四十三条</b>）评标委员会按照招标文件的要求，本着公开、公平、公正的原则，经过资质审查、综合评审打分，推荐了中标候选单位。具体情况如下：<br />
一、公开报价情况<br />
</p>
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
    PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
%>
<%=pack.getPackName()%>：  标段内容
<table id="chTable" cellpadding="0" cellspacing="0" border="1" align="center" style="border-color:black;width: 600px;border-collapse:collapse;">
	<tr>
		<td>投标供应商</td>
		<td>总报价（万元）</td>
		<td>交货期</td>
	</tr>
<%
    List suppList = pack.getSingupSupplier();
    for(int j = 0;j < suppList.size();j++){
        SupplierDto supplier = (SupplierDto)suppList.get(j);
%>	
	<tr>
		<td><%=supplier.getSupplierName()%></td>
		<td><%=supplier.getBidPrice()%>&nbsp;</td>
		<td><%=getNotNullVal(supplier.getDeliveryDate())%></td>
	</tr>
<%   }%>
</table>
<%
}
%>
<p>二、资质审查情况</p>
<p>根据招标文件的资格审核要求，评标委员会成员对<%=projReportDto.getPackCount()%>个标段<%=projReportDto.getSubmitFileSupplierCount()%>家投标供应商的投标资格进行了审核，均符合招标文件的资格要求。（<b>如有资格不符合招标文件要求的准确说明原因</b>）</p>
<p>三、综合评审打分情况</p>
<p>根据招标文件的综合评审要求, 评标委员会成员对各家供应商的投标文件进行了综合评审，并进行了打分。</p>
<p style="text-align:center">评标委员会评审总得分</p>
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
    PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
%>
<p><%=pack.getPackName()%>：标段内容</p>
<table id="chTable" cellpadding="0" cellspacing="0" border="1" align="center" style="border-color:black;width: 600px;border-collapse:collapse;">
	<tr>
		<td>投标供应商</td>
<%
    List packExperts = pack.getExperts();
    for(int j = 0;j < packExperts.size();j++){
        PersonDto expert = (PersonDto)packExperts.get(j);
%>		
		<td><%=expert.getUserName()%></td>
<%
    }
%>
		<td>总分</td>
		<td>平均分</td>
	</tr>
<%
    List suppList = pack.getSingupSupplier();
    for(int j = 0;j < suppList.size();j++){
        SupplierDto supplier = (SupplierDto)suppList.get(j);
%>
	<tr>
		<td><%=supplier.getSupplierName()%>&nbsp;</td>
		<%
		    for(int e = 0;e < packExperts.size();e++){         
		%>
		<td><%=supplier.getIndexExpertScore((PersonDto)packExperts.get(e))%>&nbsp;</td>
		<%
		    }
		%>
		<td><%=supplier.getTotalScore()%>&nbsp;</td>
		<td><%=supplier.getAverageScore()%>&nbsp;</td>
	</tr>
<%
    }
%>
</table>
<%
}
%>
<p>四、评标结论</p>
<p>综合以上情况，评标委员会成员依照招标文件和评审办法的要求，综合打分后排序如下：</p>
<%
for(int i = 0;i < projReportDto.getPackList().size();i++){
    PackReportDto pack = (PackReportDto)(projReportDto.getPackList().get(i));
%>
<dl>
<dt><%=pack.getPackName()%>：标段内容</dt>
<dd>第一名：
<%
for(int t = 0;t < pack.getFirstSuppliers().size();t++){
   SupplierDto sup = (SupplierDto)pack.getFirstSuppliers().get(t);
   out.print(sup.getSupplierName());
   out.print(";");
}
%>
</dd>
<dd>第二名：
<%
for(int t = 0;t < pack.getSecondSuppliers().size();t++){
   SupplierDto sup = (SupplierDto)pack.getSecondSuppliers().get(t);
   out.print(sup.getSupplierName());
   out.print(";");
}
%>
</dd>
<dd>第三名：
<%
for(int t = 0;t < pack.getThirdSuppliers().size();t++){
   SupplierDto sup = (SupplierDto)pack.getThirdSuppliers().get(t);
   out.print(sup.getSupplierName());
   out.print(";");
}
%>
</dd>
</dl>
<%
}
%>
<p><b>（准确说明第二、三名与第一名的差距情况）</b></p>
<p>五、参会人员名单及谈判小组成员名单</p>
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
<p align="right">西安市<%=projReportDto.getProjName()%>评标委员会</p>
<p align="right"><%=projReportDto.getCurrentDate()%></p>
</body>

</html>
