<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcEbProCompletion"%>
<%@page import="com.ufgov.zc.server.zc.web.ylbTable.*"%>
<%!
private String getProjectNatureName(String s){
	    if("A".equals(s)){
		    return "货物类";
		}else if("B".equals(s)){
			return "工程类";
		}else if("C".equals(s)){
			return "服务类";
		}
		return "";
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
	FONT-SIZE: 10pt; COLOR: #000000; LINE-HEIGHT: 150%; FONT-FAMILY: "宋体";border-color:#000000;
}
TD{
 border-color:#000000;
}
.table_title{
	text-align:right;
	font-weight:900;
	background-color: #cccccc;
}
.table_title2{
	text-align:right;
	font-weight:900;
	background-color: #cccccc;
}
.PertainFont{ font-family: 黑体; font-size: 12pt; line-height:150% }
.blackc{
    font-weight:900;
}
th{
height:10px
}
</style>
</HEAD>
<%			
	ChangeRMB changeRmb=new ChangeRMB();
	ZcEbProCompletion comp = (ZcEbProCompletion)request.getAttribute("comp");
	SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
%>
<BODY>
 <p class="pertainFont" align="center">陕西省政府采购竣工报告</p>
<TABLE  border=1 width="80%" cellspacing="0" cellpadding="0" align="center">
<TR>
	<TD class="table_title" width="20%">报告时间</TD>
	<TD align="center" width="30%"><%=simDateFormat.format(comp.getReportDate())%></TD>
	<TD class="table_title" width="20%">竣工单编号</TD>
	<TD align="center" width="30%"><%=comp.getCompletionCode()%></TD>
</TR>
<TR>
	<TD class="table_title">采购单位</TD>
	<TD align="center"><%=comp.getZcCoName()%></TD>
	<TD class="table_title">采购时间</TD>
	<TD align="center"><%=simDateFormat.format(comp.getCaiGouTime())%></TD>
</TR>
<TR>
	<TD class="table_title">项目名称</TD>
	<TD align="center"><%=comp.getProName()%></TD>
	<TD class="table_title">项目性质</TD>
	<TD align="center"><%=getProjectNatureName(comp.getProjectNature())%></TD>
</TR>
<TR>
	<TD class="table_title">项目内容</TD>
	<TD align="center"><%=comp.getProContent()%></TD>
	<TD class="table_title">批办单编号</TD>
	<TD align="center"><%=comp.getEntrustCode()%></TD>
</TR>
<TR>
	<TD class="table_title">采购方式</TD>
	<TD align="center"><%=comp.getCaiGouType()%></TD>
	<TD class="table_title">资金来源</TD>
	<TD align="center"><%=comp.getMoneySource()==null?"":comp.getMoneySource()%></TD>
</TR>
<TR>
	<TD class="table_title">资金结算方式</TD>
	<TD align="center"><%=comp.getMoneyCloseAccount() == null?"":comp.getMoneyCloseAccount()%></TD>
	<TD class="table_title">市场价(元)</TD>
	<TD align="right"><%=comp.getMarketPrice()%></TD>
</TR>
<TR>
	<TD class="table_title">计划金额(元)</TD>
	<TD align="right"><%=comp.getPlanMoney()%></TD>
	<TD class="table_title">实际采购额(元)</TD>
	<TD align="right"><%=comp.getActualStockMoney()%></TD>
</TR>
<TR>
	<TD class="table_title">实际节约额(元)</TD>
	<TD align="right"><%=comp.getActualSaveMoney()%></TD>
	<TD class="table_title">实际节约率%</TD>
	<TD align="center"><%=comp.getActualSaveRate()%></TD>
</TR>
<TR>
	<TD class="table_title">中标单位</TD>
	<TD align="center" colspan="3"><%=comp.getBidDepart()%></TD>
</TR>
<TR>
	<TD class="table_title">投标（报价）单位</TD>
	<TD align="center" colspan="3"><%=comp.getTenderDepart()%></TD>
</TR>
<TR>
	<TD class="table_title">承办意见</TD>
	<TD align="center" colspan="3"><%=comp.getChengbanMsg() == null?"":comp.getChengbanMsg()%></TD>
</TR>

</TABLE>

</BODY>
</HTML>
