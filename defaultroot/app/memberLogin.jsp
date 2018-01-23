<%@ page language="java" pageEncoding="utf-8"%>
<%
String svUserID = (String)request.getSession().getAttribute("svUserID");
String svUserName = (String)request.getSession().getAttribute("svUserName");
String caLoginOK = (String)request.getSession().getAttribute("CALoginOK");
String path=application.getRealPath(request.getRequestURI());  
//String dir=new File(path).getParent();  
String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);   
//out.println("web URL 路径:"+realPath1);  
String sendPath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/portal/memberLogin.jsp?userId=" + svUserID + "&userName=" + svUserName + "&CALoginOK=" + caLoginOK;
out.println(sendPath);  
response.sendRedirect(sendPath);
//return;
%>  


