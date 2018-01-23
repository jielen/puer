<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbRfqPack"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcXunJiaSummary"%>
<%@page import="com.ufgov.zc.server.zc.web.ylbTable.*"%>
<%!
private String getStustsName(String s){
	    if("0".equals(s)){
		    return "等待开标";
		}else if("1".equals(s)){
			return "开标成功";
		}else if("2".equals(s)){
			return "延期开标";
		}
		return "";
	}
private ZcXunJiaSummary getMinPriceProvider(List list){
	ZcXunJiaSummary min = null;
	for(int i = 0;i < list.size(); i++){
		ZcXunJiaSummary sum = (ZcXunJiaSummary)list.get(i);
		if(min == null){
		    min = sum;
			continue;
		}
		if(sum.getTotalPrice() == null){
		    sum.setTotalPrice(new BigDecimal("0"));
		}
		if(min.getTotalPrice().compareTo(sum.getTotalPrice()) > 0){
		    min = sum;
		}
	}
	return  min;
}
%>
<HTML>
<HEAD>
<TITLE>供应商询汇价总表</TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<style>
BODY {
	FONT-SIZE: 9pt; SCROLLBAR-HIGHLIGHT-COLOR: #f5f9ff; SCROLLBAR-SHADOW-COLOR: #828282; COLOR: #666666; SCROLLBAR-3DLIGHT-COLOR: #828282; SCROLLBAR-ARROW-COLOR: #797979; SCROLLBAR-TRACK-COLOR: #ececec; FONT-FAMILY: "宋体"; SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
}

TABLE {
	FONT-SIZE: 10pt; COLOR: #000000; LINE-HEIGHT: 150%; FONT-FAMILY: "宋体";
}
TD{
 --border-color:#000000;
}
.table_title{
    width:110px;
	text-align:right;
	font-weight:900;
	background-color: #cccccc;
}
.table_title2{
	font-weight:900;
	background-color: #cccccc;
}
.PertainFont{ font-family: 黑体; font-size: 12pt; line-height:150% }
.blackc{
    font-weight:900;
}
</style>
</HEAD>
<%			
	ChangeRMB changeRmb=new ChangeRMB();
	ZcEbRfqPack zcEbRfqPack = (ZcEbRfqPack)request.getAttribute("getPackInfor");
	List xjSummaryList = (List)request.getAttribute("getXjSummaryList");
	SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd");
	String strDate1 = "";
	String strDate2 = "";
	String strDate3 = "";
	if(zcEbRfqPack.getBidEndTime() != null){
		Date date1 = zcEbRfqPack.getBidEndTime();
		strDate1 = simDateFormat.format(date1);
	}
	if(zcEbRfqPack.getPlanOpenBidDate() != null){
		Date date2 = zcEbRfqPack.getPlanOpenBidDate();
		strDate2 = simDateFormat.format(date2);
	}
	if(zcEbRfqPack.getCreateDate() != null){
		Date date3 = zcEbRfqPack.getCreateDate();
		strDate3 = simFormat.format(date3);
	}
	ZcXunJiaSummary sumt = getMinPriceProvider(xjSummaryList);
	if(sumt != null){
	    zcEbRfqPack.setBidMinPrice(sumt.getTotalPrice());
		zcEbRfqPack.setBidMinPriceProvider(sumt.getProviderName());
	}
%>
<BODY>
 <p class="pertainFont" align="center">供应商询汇价总表</p>
<TABLE border="1" width="80%" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
<TR>
	<TD class="table_title" bgcolor="#cccccc">项目名称</TD>
	<TD align="center"><%=zcEbRfqPack.getProjName() == null ? "" : zcEbRfqPack.getProjName()%>(<%=zcEbRfqPack.getProjCode()%>)</TD>
	<TD class="table_title" width="130">标段名称</TD>
	<TD align="center"><%if(zcEbRfqPack.getPackName()==null)%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%else%><%=zcEbRfqPack.getPackName()%></TD>
	<TD class="table_title">标段状态</TD>
	<TD align="center"><%=getStustsName(zcEbRfqPack.getOpenBidStatus())%></TD>
</TR>
<TR>
	<TD class="table_title">已报价商家个数</TD>
	<TD align="center"><%=xjSummaryList == null ? 0 : xjSummaryList.size()%></TD>
	<TD class="table_title"><nobr>商家最低报价(元)</nobr></TD>
	<TD align="right"><%if(zcEbRfqPack.getBidMinPrice()==null)%>-<%else%><%=zcEbRfqPack.getBidMinPrice()%></TD>
	<TD class="table_title">最低报价商家</TD>
	<TD align="center"><%if(zcEbRfqPack.getBidMinPriceProvider()==null)%>-<%else%><%=zcEbRfqPack.getBidMinPriceProvider()%></TD>
</TR>
<TR>
	<TD class="table_title">投标截止时间</TD>
	<TD align="center"><%if(strDate1.equals(""))%>-<%else%><%=strDate1%></TD>
	<TD class="table_title">开标时间</TD>
	<TD align="center"><%if(strDate2.equals(""))%>-<%else %><%=strDate2 %></TD>
	<TD class="table_title">项目报告人</TD>
	<TD align="center"><%if(zcEbRfqPack.getCreator()==null)%>-<%else%><%=zcEbRfqPack.getCreator()%></TD>
</TR>
<TR>
	<TD class="table_title">单据创建时间</TD>
	<TD align="center"><%if(strDate3.equals(""))%>-<%else%><%=strDate3%></TD>
	<TD class="table_title">单据审核状态</TD>
	<TD align="center"><%if(zcEbRfqPack.getStatus()==null)%>-<%else%><%=zcEbRfqPack.getStatus()%></TD>
	<TD class="table_title">延期/废标原因</TD>
	<TD align="center"><%if(zcEbRfqPack.getReason()==null)%>-<%else%><%=zcEbRfqPack.getReason()%></TD>
</TR>
</TABLE>
<br/>
<TABLE  border="1" width="80%" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
<TR style="text-align:center">
	<TD class="table_title2" width="40">行号</TD>
	<TD class="table_title2" width="*" align="center"><center>供应商名称</center></TD>
	<TD class="table_title2" >总报价（元）</TD>
	<TD class="table_title2" >是&nbsp;否<br/>有现货</TD>
	<TD class="table_title2" >供货时间</TD>
	<TD class="table_title2" >报价时间</TD>
	<TD class="table_title2" >是否<br/>成交</TD>
	<TD class="table_title2" >联系人</TD>
	<TD class="table_title2" >联系电话</TD>
	<TD class="table_title2" >备&nbsp;&nbsp;注</TD>
</TR>
<%
for(int i = 0;i < xjSummaryList.size(); i++){
	ZcXunJiaSummary sum = (ZcXunJiaSummary)xjSummaryList.get(i);
	String strDate = "";
	if(sum.getGongHuoDate() != null){
		Date date = sum.getGongHuoDate();
		strDate = simFormat.format(date);
	}
	String strBjDate = "";
	if(sum.getBjDate() != null){
		Date date = sum.getBjDate();
		strBjDate = simFormat.format(date);
	}
%>
<TR>
	<TD align="center"><%=i+1%></TD>
	<TD align="center"><nobr><%=sum.getProviderName()%></nobr></TD>
	<TD align="right"><%=sum.getTotalPrice()%></TD>
	<TD align="center"><%="Y".equals(sum.getHaveXianHuo()) ? "是" : "否"%></TD>
	<TD align="center"><%if(strDate.equals(""))%>-<%else%><%=strDate%></TD>
	<TD align="center"><%if(strBjDate.equals(""))%>-<%else%><%=strBjDate%></TD>
	<TD align="center"><%="Y".equals(sum.getIsClosedDeal()) ? "是" : "否"%></TD>
	<TD align="center"><%=sum.getLinkMan() == null ? "-" : sum.getLinkMan()%></TD>
	<TD align="center"><%=sum.getLinkTel() == null ? "-" : sum.getLinkTel()%></TD>
	<TD align="center"><%=sum.getRemark() == null ? "-" : sum.getRemark()%></TD>
</TR>
<%
	}
%>
</TABLE>

<div style="width:89%;margin-left:10%;margin-top:20px;">
<b><span onclick="viewCtl(this,'optionTitle','optionspan')" style="cursor:hand">-</span><span id="optionTitle" style="FONT-SIZE: 10pt;">&nbsp;&nbsp;询价小组意见:</span></b><br/><br/>
<div id="optionspan"  style="width:100%;border:1px solid #000000;height:100px;padding:5px 5px 5px 5px">
<%=zcEbRfqPack.getRfqTeamOpinion()%>
</div>
</div>

<div style="width:89%;margin-left:10%;margin-top:20px;">
<b><span onclick="viewCtl(this,'optionTitle2','optionspan2')" style="cursor:hand">-</span><span id="optionTitle2" style="FONT-SIZE: 10pt;">&nbsp;&nbsp;组长意见:</span></b><br/><br/>
<div id="optionspan2"  style="width:100%;border:1px solid #000000;height:100px;padding:5px 5px 5px 5px">

</div>
</div>

<div style="width:89%;margin-left:10%;margin-top:20px;">
<b><span onclick="viewCtl(this,'optionTitle3','optionspan3')" style="cursor:hand">-</span><span id="optionTitle3" style="FONT-SIZE: 10pt;">&nbsp;&nbsp;主任意见:</span></b><br/><br/>
<div id="optionspan3"  style="width:100%;border:1px solid #000000;height:100px;padding:5px 5px 5px 5px">

</div>
</div>

</BODY>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
<!--
function viewCtl(obj,id,id2){
    var v = obj.innerText;
	if(v == '+'){
		obj.innerText = '-';
		document.getElementById(id).style.display = '';
		document.getElementById(id2).style.display = '';
	}else{
		obj.innerText = '+';
		document.getElementById(id).style.display = 'none';
		document.getElementById(id2).style.display = 'none';
	}
}
//-->
</SCRIPT>