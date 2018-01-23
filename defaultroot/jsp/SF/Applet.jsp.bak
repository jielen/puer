<%@page import ="com.anyi.gp.pub.SessionUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.LinkedList"%>
<%
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
  //System.out.println("token:"+ token+ "transDate:"+ transDate+ "userId:"+ userId+ "coCode:"+ coCode+ "orgCode:"+ orgCode+ "orgPoCode:"+ orgPoCode+ "accountId:"+ accountId);
  //System.out.println("webRoot:"+ webRoot);
  //System.out.println("clientIP:"+ clientIP);
  
  String urlArray="";
  int sum=0;
  Enumeration  pNames=request.getParameterNames();
  while(pNames.hasMoreElements()){  
     String key=(String)pNames.nextElement();
	 String value=request.getParameter(key);
     if(sum==0)
	   urlArray=key+"@"+value;
	 else
       urlArray=urlArray+","+key+"@"+value;

	 sum++;
  }
 
%>
<body TOPMARGIN ="0" LEFTMARGIN ="0" >

<body TOPMARGIN ="0" LEFTMARGIN ="0" >
<object classid="clsid:CAFEEFAC-0016-0000-0019-ABCDEFFEDCBA" codebase="<%=webRoot%>/java/jre-6u19-windows-i586.exe#Version=6,0,190,4" WIDTH="100%" HEIGHT="100%" >
    <PARAM NAME="CODE" VALUE="com.ufgov.zc.client.applet.local.LocalResourceApplet.class" >
    <PARAM NAME="CODEBASE" VALUE="<%=webRoot%>/localApplet" ><PARAM NAME="cache_option" VALUE="Plugin">
    <PARAM NAME="ARCHIVE" VALUE="localApplet.jar">
    <PARAM name="cache_archive" value="localApplet.jar">
    <PARAM NAME="cache_version" VALUE="4.3">
    <param name="type" value="application/x-java-applet;jpi-version=1.6.0_19">
    <param name="scriptable" value="false">
    <PARAM name="java_arguments" value="-Xms80m -Xmx1000m -Djnlp.packEnabled=true">
    <PARAM NAME="panelClassName" VALUE="<%=panelClassName%>">
    <PARAM NAME="token" VALUE="<%=token%>">
    <PARAM NAME="clientIP" VALUE="<%=clientIP%>">
    <PARAM NAME="transDate" VALUE="<%=transDate%>">
    <PARAM NAME="userId" VALUE="<%=userId%>">
    <PARAM NAME="coCode" VALUE="<%=coCode%>">
    <PARAM NAME="orgCode" VALUE="<%=orgCode%>">
    <PARAM NAME="orgPoCode" VALUE="<%=orgPoCode%>">
    <PARAM NAME="poCode" VALUE="<%=poCode%>">
    <PARAM NAME="webRoot" VALUE="<%=webRoot%>">
    <PARAM NAME="webIp" VALUE="<%=webIp%>">
    <PARAM NAME="accountId" VALUE="<%=accountId%>">
    <PARAM NAME="empCode" VALUE="<%=empCode%>">
    <PARAM NAME="empName" VALUE="<%=empName%>">
	<PARAM NAME="urlArray" VALUE="<%=urlArray%>">
    <comment>
	<embed
            type = "application/x-java-applet;jpi-version=1.6.0_19" \
            CODE = "com.ufgov.zc.client.applet.ZcApplet.class" \
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
            webIp ="<%=webIp%>" \
            accountId ="<%=accountId%>"\
            empCode ="<%=empCode%>" \
            empName ="<%=empName%>"\
			urlArray ="<%=urlArray%>"\
	    scriptable = false
	    pluginspage = "<%=webRoot%>/java/jre-6u19-windows-i586.exe#Version=6,0,190,4">
	</embed>    
    </comment>
</object>
</body>
</body>