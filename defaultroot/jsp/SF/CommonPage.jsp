<%@ page contentType="text/html; charset=GBK"%>
<html>
<head><title></title></head>
<body>
<%
  String panelClassName = request.getParameter("className");
  if(panelClassName == null || "".equals(panelClassName.trim())){
%>
    <b>菜单配置错误，没有添加【className】参数！<b/>
<%} else {%>
    <%@include file="/jsp/SF/Applet.jsp"%>
<%    
  }
%>
</body>
</html>