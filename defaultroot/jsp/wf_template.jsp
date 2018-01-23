<meta http-equiv="Content-Type" content="text/html;charset=gb2312"/>
<%@ page contentType="text/html; charset=gb2312"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.kingdrive.workflow.model.meta.TemplateModel"%>
<%@page import="com.kingdrive.workflow.service.impl.db.WFMetaServiceImpl"%>
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String op = request.getParameter("op");
Map temple = WFMetaServiceImpl.templateCache;
if(op != null){
    if("all".equals(op)){
	    temple.clear();
	}else{
	    temple.remove(new Long(op));
	}
}
%>

<table border="1" width="80%" cellpadding="0" cellspacing="0" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
<tr>
<td class="table_title" bgcolor="#cccccc">流程编号</td>
<td class="table_title" bgcolor="#cccccc">流程名称</td>
<td class="table_title" bgcolor="#cccccc"><a href="<%=basePath%>jsp/wf_template.jsp?op=all">清除所有</a></td>
</tr>
<%

    for (Iterator iterator = temple.values().iterator(); iterator.hasNext();) {
      TemplateModel t = (TemplateModel) iterator.next();
	  out.print("<tr><td>");
      out.print(t.getTemplateId());
	  out.print("</td><td>");
	  out.print(t.getName());
	  out.print("</td><td><a href=\"" + basePath + "jsp/wf_template.jsp?op=" + t.getTemplateId() + "\">清除</a></td><tr>");
    }
%>
</table>
</BODY>
</HTML>
