<%@ page contentType="text/html; charset=GBK" %>
<%@page import="com.anyi.gp.debug.DataSourceWrapper"%>
<%
	//org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("WF");
	//log.info(request);
	
	//session.setAttribute(com.anyi.gp.pub.SessionUtils.CURRENT_USER_TOKEN, null);
    //session.removeAttribute(com.anyi.gp.pub.SessionUtils.CURRENT_USER_TOKEN);
	DataSourceWrapper.setCurrentUser(null);
	session.invalidate();

%>