<%@ page contentType="text/html; charset=GBK"%>
<%@page import ="com.anyi.gp.pub.SessionUtils"%>
<%
  String panelClassName = request.getParameter("className");
  String scheme = request.getScheme();
  String ip = request.getServerName();
  int port = request.getServerPort();
  String app = request.getContextPath();
  String webIp=scheme + "://" + ip + ":" + port;
  String webRoot = scheme + "://" + ip + ":" + port + app;
  String clientIP = request.getRemoteAddr();

  //String expertCode = request.getParameter("expertCode");
  //String expertName = request.getParameter("expertName");
  String token = request.getParameter("token");
  String transDate = (String)SessionUtils.getAttribute(request,"svTransDate");
  String userId = (String)SessionUtils.getAttribute(request,"svUserID");
  String coCode = (String)SessionUtils.getAttribute(request,"svCoCode");
  String orgCode = (String)SessionUtils.getAttribute(request,"svOrgCode");
  String orgPoCode = (String)SessionUtils.getAttribute(request,"svOrgPoCode");
  String poCode = (String)SessionUtils.getAttribute(request,"svPoCode");
  String accountId = (String)SessionUtils.getAttribute(request,"svAccountId");
  String empCode = (String)SessionUtils.getAttribute(request,"svEmpCode");
  String empName = (String)SessionUtils.getAttribute(request,"svEmpName");
  
  System.out.println("token:"+ token+ "transDate:"+ transDate+ "userId:"+ userId+ "coCode:"+ coCode+ "orgCode:"+ orgCode+ "orgPoCode:"+ orgPoCode+ "accountId:"+ accountId  
  +"empCode"+empCode);
  //System.out.println("webRoot:"+ webRoot);
  //System.out.println("clientIP:"+ clientIP);
%>
<body  TOPMARGIN ="0" LEFTMARGIN ="0" >
<object
    classid = "clsid:CAFEEFAC-0016-0000-0012-ABCDEFFEDCBA"
    codebase = "<%=webRoot%>/java/jre6u12.CAB#Version=6,0,120,4"
    WIDTH = "100%" HEIGHT = "100%" >
    <PARAM NAME = CODE VALUE = "com.ufgov.zc.client.applet.GkApplet.class" >
    <PARAM NAME = CODEBASE VALUE = "<%=webRoot%>/applet" >
    <PARAM NAME = ARCHIVE VALUE = "com.ufgov.smartclient.component_1.0.0.jar,
    com.ufgov.smartclient.laf_1.0.0.jar,gbclient.jar,log4j-1.2.8.jar,hessian-3.2.1.jar,
    workflow-client.jar,jgraph.jar,jasperreports-0.6.7.jar,JoSQL-2.1-new.jar,gentlyWEB-utils-1.1.jar,
    commons-lang.jar,commons-beanutils-1.5.jar,commons-collections.jar,commons-logging.jar,WordSupport.jar,xstream-1.3.1.jar,ant.jar,bsh-2.0b4.jar" >
    <PARAM name="cache_archive" value="com.ufgov.smartclient.component_1.0.0.jar,
    com.ufgov.smartclient.laf_1.0.0.jar,gbclient.jar,log4j-1.2.8.jar,hessian-3.2.1.jar,
    workflow-client.jar,jgraph.jar,jasperreports-0.6.7.jar,JoSQL-2.1-new.jar,gentlyWEB-utils-1.1.jar,
    commons-lang.jar,commons-beanutils-1.5.jar,commons-collections.jar,commons-logging.jar,WordSupport.jar,xstream-1.3.1.jar,ant.jar,bsh-2.0b4.jar">
    <param name = "type" value = "application/x-java-applet;jpi-version=1.6.0_12">
    <param name = "scriptable" value = "false">
    <PARAM name="java_arguments" value="-Xms80m -Xmx1000m -Djnlp.packEnabled=true">   
    <PARAM NAME = "panelClassName" VALUE="<%=panelClassName%>">
    <PARAM NAME = "token" VALUE="<%=token%>">
    <PARAM NAME = "clientIP" VALUE="<%=clientIP%>">
    <PARAM NAME = "transDate" VALUE="<%=transDate%>">
    <PARAM NAME = "userId" VALUE="<%=userId%>">
    <PARAM NAME = "coCode" VALUE="<%=coCode%>">
    <PARAM NAME = "orgCode" VALUE="<%=orgCode%>">
    <PARAM NAME = "orgPoCode" VALUE="<%=orgPoCode%>">
    <PARAM NAME = "poCode" VALUE="<%=poCode%>">
    <PARAM NAME = "webRoot" VALUE="<%=webRoot%>">
    <PARAM NAME = "webIp" VALUE="<%=webIp%>">
    <PARAM NAME = "accountId" VALUE="<%=accountId%>">
    <PARAM NAME = "empCode" VALUE="<%=empCode%>">
    <PARAM NAME = "empName" VALUE="<%=empName%>">
    <comment>
	<embed
            type = "application/x-java-applet;jpi-version=1.6.0_12" \
            CODE = "com.ufgov.zc.client.applet.GkApplet.class" \
            JAVA_CODEBASE = "<%=webRoot%>/applet" \
            ARCHIVE = "com.ufgov.smartclient.component_1.0.0.jar," \
            WIDTH = "100%" \
            HEIGHT = "100%" \
            panelClassName ="<%=panelClassName%>" \
            token ="<%=token%>" \
            clientIP ="<%=clientIP%>" \
            transDate ="<%=transDate%>" \
            userId ="<%=userId%>" \
            coCode ="<%=coCode%>" \
            orgCode ="<%=orgCode%>" \
            orgPoCode ="<%=orgPoCode%>" \
            poCode ="<%=poCode%>" \
            webRoot ="<%=webRoot%>" \
            accountId ="<%=accountId%>"\
            webRoot ="<%=webRoot%>" \
            webIp ="<%=webIp%>" \
            accountId ="<%=accountId%>"\
            empCode ="<%=empCode%>" \
            empName ="<%=empName%>"\
	    scriptable = false
	    pluginspage = "<%=webRoot%>/java/jre6u12.CAB#Version=6,0,120,4">
	    <noembed>
            
            </noembed>
	</embed>
    </comment>
</object>
</body>