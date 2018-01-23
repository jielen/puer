<%@ page language="java" pageEncoding="GBK"%>
<style>
/*------Nav Panel主导航菜单--------------------*/
#navPan{width:1000px;height:29px;clear:both;background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/navBg.jpg) repeat ; margin-left:auto;margin-right:auto;}
#navPan ul{float:right; width:985px; margin:0 0 0 0px;padding:0 0 0 0px; height:29px; color:#fff;}
#navPan ul li{float:left;  height:29px;}
#navPan ul span{float:left;  height:9px; padding:6 0 0 0px;}
#navPan ul li a{font:12px/29px Arial, Helvetica, sans-serif;  height:29px; display:block; color:#004576;background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/nav_bg.gif) repeat-x 0 0 ; text-decoration:none; text-align:center; }
#navPan ul li a:hover{color:#f4f9fd;background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/main_right.jpg)}
#navPan ul li a.main{background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/main_right.jpg) no-repeat 0 0; width:100px; color:#fff;}
#navPan ul li a.main:hover{background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/navHover.jpg) no-repeat 0 0; width:100px; color:#f8931f;}
#navPan ul li a.hover{background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/navHover.jpg) no-repeat 0 0; width:100px; color:#f8931f;}
#loginPan{width:870px;height:42px;clear:both;background:url(<%=request.getScheme() %>://<%=request.getServerName()%>:<%=request.getServerPort()%>/portal/skin/image/loginBg.jpg) repeat ; margin-left:auto;margin-right:auto; }
</style>
<div>
      <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="1000" height="150">
      <param name="movie" value="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/skin/image/logo.swf" />
      <param name="quality" value="high" />
	  <param name="wmode" value="transparent" />
      <embed src="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/skin/image/logo.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="1000" height="150"></embed>
    </object>
</div>
<div id="navPan">
	<ul>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/index/index.html" class="main">首页</a>
		</li>   
		<li><span style="text-align:center">|</span></li>  
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/cggg/index.html" class="main">采购公告</a>
		</li>
		<li><span >|</span></li>  
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/zcfg/index.html" class="main">政策法规</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/cgzxun/index.html" class="main">采购资讯</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/cgzshi/index.html" class="main">采购知识</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/bszn/index.html" class="main">网上办事指南</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/zytscl/index.html" class="main">质疑和投诉处理</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/xxyd/index.html" class="main">学习园地</a>
		</li>
		<li><span >|</span></li>
		<li>
					<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/xzzq/index.html" class="main">下载专区</a>
		</li>
	</ul>
</div>