<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ufgov.zc.common.system.constants.ZcSettingConstants" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
	width:100%;
	height:50px;
}
-->
</style>
</head>
<%
Map rt = (Map)request.getAttribute("proj");
String cgType = (String)(rt.get("PUR_TYPE"));
String cgTypeName = null;
	if(ZcSettingConstants.PITEM_OPIWAY_GKZB.equals(cgType)){
		cgTypeName = "公开招标";
	}else if(ZcSettingConstants.PITEM_OPIWAY_JZXTP.equals(cgType)){
		cgTypeName = "竞争性谈判";
	}else if(ZcSettingConstants.PITEM_OPIWAY_YQZB.equals(cgType)){
		cgTypeName = "邀请招标";
	}else if(ZcSettingConstants.PITEM_OPIWAY_XJ.equals(cgType)){
		cgTypeName = "询价";
	}else if(ZcSettingConstants.PITEM_OPIWAY_DYLY.equals(cgType)){
		cgTypeName = "单一来源";
	}else if(ZcSettingConstants.PITEM_OPIWAY_QT.equals(cgType)){
		cgTypeName = "其他";
	}else if(ZcSettingConstants.PITEM_OPIWAY_XYGH.equals(cgType)){
		cgTypeName = "协议供货二次竞价";
	}else{
	    cgTypeName = "--";
	}
%>
<body>
   
     <div align="center" class="STYLE1">西安市市级单位政府采购中心内部采购任务执行单</div>
   
<table width="739" height="305" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" style="border-bottom:0px">
  <tr>
    <td width="163"><div align="center">项目编号</div></td>
    <td width="174"><%=rt.get("PROJ_CODE") == null ? "&nbsp;" : rt.get("PROJ_CODE")%></td>
    <td width="155"><div align="center">中心编号</div></td>
    <td width="*"><%=rt.get("SN") == null ? "&nbsp;" : rt.get("SN")%></td>
  </tr>
  <tr>
    <td><div align="center">项目名称</div></td>
    <td colspan="3"><%=rt.get("PROJ_NAME") == null ? "&nbsp;" : rt.get("PROJ_NAME")%></td>
  </tr>
  <tr>
    <td><div align="center">采购单位</div></td>
    <td colspan="3"><%=rt.get("CO_NAME") == null ? "&nbsp;" : rt.get("CO_NAME")%></td>
  </tr>
  <tr>
    <td><div align="center">联系人</div></td>
    <td><%=rt.get("CO_PERSON") == null ? "&nbsp;" : rt.get("CO_PERSON")%></td>
    <td><div align="center">联系电话</div></td>
    <td><%=rt.get("CO_PHONE") == null ? "&nbsp;" : rt.get("CO_PHONE")%></td>
  </tr>
  <tr>
    <td><div align="center">采购方式</div></td>
    <td><%=cgTypeName%></td>
    <td><div align="center">下达时间</div></td>
    <td><%=rt.get("OP_TIME") == null ? "&nbsp;" : rt.get("OP_TIME")%></td>
  </tr>
  <tr>
    <td><div align="center">采购预算</div></td>
    <td align="right"><%=rt.get("CG_BUDGET") == null ? "&nbsp;" : rt.get("CG_BUDGET")%></td>
    <td><div align="center">实际采购金额</div></td>
    <td align="right"><%=rt.get("SJCG_MONEY") == null ? "&nbsp;" : rt.get("SJCG_MONEY")%></td>
  </tr>
  <tr>
    <td><div align="center">采购处联系人</div></td>
    <td><%=rt.get("CGC_PERSON") == null ? "&nbsp;" : rt.get("CGC_PERSON")%></td>
    <td><div align="center">联系电话</div></td>
    <td><%=rt.get("CGC_PHONE") == null ? "&nbsp;" : rt.get("CGC_PHONE")%></td>
  </tr>
</table>
<table width="739" height="305" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" style="border-top:0px">
  <tr>
    <td width="163"><div align="center">一科项目经办人</div></td>
    <td width="*"><%=rt.get("EDIT_FILE_PERSON") == null ? "&nbsp;" : rt.get("EDIT_FILE_PERSON")%></td>
  </tr>
  <tr>
    <td><div align="center">招标公告时间</div></td>
    <td><%=rt.get("BID_REPORT_TIME") == null ? "&nbsp;" : rt.get("BID_REPORT_TIME")%></td>
  </tr>
  <tr>
    <td><div align="center">二科项目经办人</div></td>
    <td><%=rt.get("OPEN_BID_PERSON") == null ? "&nbsp;" : rt.get("OPEN_BID_PERSON")%></td>
  </tr>
  <tr>
    <td><div align="center">中标公告时间</div></td>
    <td><%=rt.get("WIN_REPORT_TIME") == null ? "&nbsp;" : rt.get("WIN_REPORT_TIME")%></td>
  </tr>
  <tr>
    <td><div align="center">档案交接人</div></td>
    <td><%=rt.get("HANDOVER_PERSON") == null ? "&nbsp;" : rt.get("HANDOVER_PERSON")%></td>
  </tr>
  <tr>
    <td><div align="center">档案交接日期</div></td>
    <td><%=rt.get("HANDOVER_DATE") == null ? "&nbsp;" : rt.get("HANDOVER_DATE")%></td>
  </tr>
  <tr>
    <td colspan="2"><div align="left">备注：<%=rt.get("MEMO") == null ? "&nbsp;" : rt.get("MEMO")%></div></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>
