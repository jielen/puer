<%@ page language="java" pageEncoding="GBK"%>
<div id="container">
<link type="text/css" rel="stylesheet" href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/skin/css/PageNavigation.css" /><div>
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="1000" height="150">
      <param name="movie" value="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/skin/img/logo.swf" />
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
		<li><span >|</span></li>  
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