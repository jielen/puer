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
<%@page import="com.ufgov.zc.common.zc.model.ZcXunJiaDetail"%>
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
<%			
	ChangeRMB changeRmb=new ChangeRMB();
	ZcEbRfqPack zcEbRfqPack = (ZcEbRfqPack)request.getAttribute("getPackInfor");
	List xjSummaryList = (List)request.getAttribute("getXjSummaryList");
	List xjdList = (List)request.getAttribute("xjdList");
	System.out.println("test1:" + zcEbRfqPack);
	System.out.println("test1:" + xjSummaryList);
	SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat simDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
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
		strDate3 = simDateFormat2.format(date3);
	}
	ZcXunJiaSummary sumt = getMinPriceProvider(xjSummaryList);
	if(sumt != null){
	    zcEbRfqPack.setBidMinPrice(sumt.getTotalPrice());
		zcEbRfqPack.setBidMinPriceProvider(sumt.getProviderName());
	}
%>
<HTML>
<HEAD>
<TITLE> 供应商询价一览表 </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<style>
BODY {
	FONT-SIZE: 10pt; SCROLLBAR-HIGHLIGHT-COLOR: #f5f9ff; SCROLLBAR-SHADOW-COLOR: #828282; COLOR: #666666; SCROLLBAR-3DLIGHT-COLOR: #828282; SCROLLBAR-ARROW-COLOR: #797979; SCROLLBAR-TRACK-COLOR: #ececec; FONT-FAMILY: "宋体"; SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
}

TABLE {
	FONT-SIZE: 10pt; COLOR: #000000; LINE-HEIGHT: 150%; FONT-FAMILY: "宋体";border-color:#000000;
}

TR{
    height:20px
}

TD{
 --border-color:#000000;
 --border-right-width:0px;
 --border-left:1px solid #000000;
}
.table_title{
    width:110px;
	text-align:right;
	font-weight:900;
	background-color: #cccccc;
}
.PertainFont{ font-family: 黑体; font-size: 12pt; line-height:150% }
.blackc{
    font-weight:900;
}
.table_title2{
	text-align:right;
	font-weight:900;
	background-color: #cccccc;
}
.T_Sample {
  border-collapse: collapse;
  border: none;
}
</style>
</HEAD>

<BODY>
 <p class="pertainFont" align="center">供应商询价一览表</p>
<TABLE  border="1" width="80%" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
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
<br>
<%
for(int i = 0;i < xjSummaryList.size(); i++){
	ZcXunJiaSummary sum = (ZcXunJiaSummary)xjSummaryList.get(i);
%>
<TABLE border="1" width="80%" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
<tr >
<td colspan="100" class="blackc">供应商：<span style="width:200px;font-weight:100;"><%=sum.getProviderName() == null ? "-" : sum.getProviderName()%></span><span class="blackc">总价：</span><span style="width:120px;font-weight:100;"><nobr><%=changeRmb.doChange(sum.getTotalPrice().toString())%>&nbsp;&nbsp;</nobr></span><span class="blackc">报价提交时间：</span><span style="width:100px;font-weight:100;"><%=sum.getBjDate() == null ? "-" : simDateFormat2.format(sum.getBjDate())%></span><span class="blackc">供货时间：</span><span style="width:100px;font-weight:100;"><%=sum.getGongHuoDate() == null ? "-" : simDateFormat2.format(sum.getGongHuoDate())%></span><span class="blackc">成交状态：</span><span style="width:50px;font-weight:100;">
<%="Y".equals(sum.getIsClosedDeal()) ? "已成交" : "未成交"%>
<%
ArrayList detailsForCurr = new ArrayList();
String spCode = sum.getProviderCode();
for (int j = 0; j < xjdList.size(); j++) {
	ZcXunJiaDetail temp = (ZcXunJiaDetail)xjdList.get(j);
	  if (spCode.equals(temp.getProviderCode())) {
		detailsForCurr.add(xjdList.get(j));
	  }
}
%>
</span></td>
</tr>
<TR style="text-align:center">
	<TD width="40" class="table_title2">行号</TD>
	<TD class="table_title" align="center">商品名称</TD>
	<TD class="table_title" align="center">询价单号</TD>
	<TD class="table_title" align="center">商品价格(元)</TD>
	<TD class="table_title" align="center">商品数量</TD>
	<TD class="table_title" align="center">是否有现货</TD>
	<TD class="table_title" align="center">供货时间</TD>
	<TD class="table_title" align="center">备&nbsp;&nbsp;注</TD>
</TR>
<%
for (int t = 0; t < detailsForCurr.size(); t++) {
	ZcXunJiaDetail detail = (ZcXunJiaDetail)detailsForCurr.get(t);	
%>
<TR>
	<TD><%=t+1%></TD>
	<TD align="right"><%=detail.getSpName() == null ? "-" : detail.getSpName().toString()%></TD>
	<TD align="center"><%=detail.getXjCode() == null ? "-" : detail.getXjCode()%></TD>
	<TD align="right"><%=detail.getSpPrice() == null ? "&nbsp;0" : detail.getSpPrice().toString()%></TD>
	<TD align="center"><%=detail.getSpNum()%></TD>
	<TD align="center"><%="Y".equals(detail.getHaveXianHuo()) ? "是" : "否"%></TD>
	<TD align="center"><%=detail.getGongHuoDate() == null ? "-" : simDateFormat2.format(detail.getGongHuoDate())%></TD>
	<TD align="center"><%=detail.getRemark() == null ? "-" : detail.getRemark()%></TD>
</TR>
<%
	}	
%>
</TABLE>
<p/>
<%
	}
%>
</BODY>
</HTML>
