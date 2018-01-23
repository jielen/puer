<%@page import ="com.anyi.gp.pub.SessionUtils"%>
<%
  String scheme = request.getScheme();
  String ip = request.getServerName();
  int port = request.getServerPort();
  String app = request.getContextPath();

  String webRoot = scheme + "://" + ip + ":" + port + app;
  String clientIP = request.getRemoteAddr();

  String token = request.getParameter("token");
  String transDate = (String)SessionUtils.getAttribute(request,"svTransDate");
  String userId = (String)SessionUtils.getAttribute(request,"svUserID");
  String coCode = (String)SessionUtils.getAttribute(request,"svCoCode");
  String orgCode = (String)SessionUtils.getAttribute(request,"svOrgCode");
  String orgPoCode = (String)SessionUtils.getAttribute(request,"svOrgPoCode");
  String poCode = (String)SessionUtils.getAttribute(request,"svPoCode");
  String accountId = (String)SessionUtils.getAttribute(request,"svAccountId");
  //System.out.println("token:"+ token+ "transDate:"+ transDate+ "userId:"+ userId+ "coCode:"+ coCode+ "orgCode:"+ orgCode+ "orgPoCode:"+ orgPoCode+ "accountId:"+ accountId);
  //System.out.println("webRoot:"+ webRoot);
  //System.out.println("clientIP:"+ clientIP);
%>
<body  TOPMARGIN ="0" LEFTMARGIN ="0" >
<object
    classid = "clsid:CAFEEFAC-0016-0000-0012-ABCDEFFEDCBA"
    codebase = "<%=webRoot%>/java/jre6u12.CAB#Version=6,0,120,4"
    WIDTH = "100%" HEIGHT = "100%" >
    <PARAM NAME = CODE VALUE = "com.ufgov.gk.client.applet.GkApplet.class" >
    <PARAM NAME = CODEBASE VALUE = "<%=webRoot%>/applet" >
    <PARAM NAME = ARCHIVE VALUE = "com.ufgov.smartclient.component_1.0.0.jar,
    com.ufgov.smartclient.laf_1.0.0.jar,log4j-1.2.8.jar,hessian-3.2.1.jar,
    gk_common.jar,gk_component.jar,gk_bb.jar,
    workflow-client.jar,jgraph.jar,jasperreports-0.6.7.jar,JoSQL-2.1-new.jar,gentlyWEB-utils-1.1.jar,encrypt.jar" >
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
    <PARAM NAME = "accountId" VALUE="<%=accountId%>">
    <PARAM name="com.ufgov.smartclient.component_1.0.0.jar,
    com.ufgov.smartclient.laf_1.0.0.jar,log4j-1.2.8.jar,hessian-3.2.1.jar,
    gk_common.jar,gk_component.jar,gk_bb.jar,
    workflow-client.jar,jgraph.jar,jasperreports-0.6.7.jar,JoSQL-2.1-new.jar,gentlyWEB-utils-1.1.jar,encrypt.jar">

    <comment>
	<embed
            type = "application/x-java-applet;jpi-version=1.6.0_12" \
            CODE = "com.ufgov.gk.client.applet.GkApplet.class" \
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
            accountId ="<%=accountId%>"
	    scriptable = false
	    pluginspage = "<%=webRoot%>/java/jre6u12.CAB#Version=6,0,120,4">
	    <noembed>
            
            </noembed>
	</embed>
    </comment>
</object>
</body>