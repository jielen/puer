<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
  String baseUrl = request.getContextPath();
  String spDetailInfoUrl = request.getContextPath()+"/portal/page/menugoods";
%>
<html>
<head>
<title>西安市政府采购</title>
<meta http-equiv="content-type" content="index/html; charset=gbk">
<meta content="西安市政府采购,政府采购,采购公告,采购动态,采购知识,采购资讯,代理机构,协议定点采购,政府采购供应商" name="keywords">
<meta name="description" content="西安市政府采购网" />
<link id="skin_style30" href="../../css/foot.css" type="text/css" rel="stylesheet" />
<link href="../../css/other.css" type="text/css" rel="stylesheet">
<style>
BODY {
	FONT-SIZE: 12px
}

TD {
	FONT-SIZE: 12px
}

A:link {
	COLOR: #000;
	TEXT-DECORATION: none
}

A:visited {
	FONT-WEIGHT: bold;
	COLOR: #f26522;
	TEXT-DECORATION: none
}

A:hover {
	FONT-WEIGHT: bold;
	COLOR: #f26522;
	TEXT-DECORATION: underline
}

A:active {
	FONT-WEIGHT: bold;
	COLOR: #f26522;
	TEXT-DECORATION: underline
}

.gyjg8 {
	BORDER-BOTTOM: #e0e0e0 1px dashed
}

.normal_tabpg {
	BACKGROUND-IMAGE: url(../../img/gyjg_mx.gif);
	PADDING-BOTTOM: 0px ! important;
	WIDTH: 90px;
	CURSOR: pointer
}

.select_tabpg {
	BACKGROUND-IMAGE: url(../../img/gyjg_m.gif);
	PADDING-BOTTOM: 0px ! important;
	WIDTH: 90px;
	CURSOR: pointer
}

.gg {
	FONT-SIZE: 14px;
	LINE-HEIGHT: 26px;
	BORDER-BOTTOM: #e0e0e0 1px solid;
	HEIGHT: 26px;
	TEXT-ALIGN: left
}

.gg TD {
	FONT-SIZE: 14px;
	LINE-HEIGHT: 26px;
	BORDER-BOTTOM: #e0e0e0 1px solid;
	HEIGHT: 26px
}

.g_h {
	BORDER-RIGHT: #eed97c 1px solid;
	BORDER-TOP: #eed97c 1px solid;
	FONT-SIZE: 14px;
	BACKGROUND: #fffceb;
	BORDER-LEFT: #eed97c 1px solid;
	LINE-HEIGHT: 30px;
	BORDER-BOTTOM: #eed97c 1px solid;
	HEIGHT: 30px;
	TEXT-ALIGN: left
}

.g_h1 {
	FONT-SIZE: 14px;
	BACKGROUND: #f4f8fd;
	LINE-HEIGHT: 38px;
	HEIGHT: 38px;
	TEXT-ALIGN: left
}

.yy TD {
	FONT-SIZE: 14px
}

.footer {
	TEXT-ALIGN: center
}
</style>
<script type="text/javascript">
	function clickTab(cur_tab) {
		var tab = document.getElementById("tab_1");
		var tds = tab.getElementsByTagName("TD");
		for ( var i = 0; i < tds.length; i++) {
			var tp = tds[i];
			var tabType = tp.getAttribute('tabType');
			var className = tp.className;
			if (tabType == "tp" && className == "select_tabpg") {
				tp.className = "normal_tabpg";
			}
		}
		cur_tab.className = "select_tabpg";
		var id = cur_tab.id;
		if (id == "tab_page1") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl+"/spDetailInfo.do"%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page3") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl+"/spDetailInfoForCata.do"%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page6") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page4") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page5") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl+"/spDetailSup.do"%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&brandId=<bean:write name="spForm" property="zcBMerchandise.zcBraCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page7") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		} else if (id == "tab_page8") {
			spdetails_iframe.location.replace("<%=spDetailInfoUrl+"/spDetailChengjiao.do"%>?zcMerCode=<bean:write name="spForm" property="zcBMerchandise.zcMerCode" />&xySpYear=2010&xySpTime=1");
		}
	}
	function initView() {
		if (document.getElementById("tab_page1") == null)
			return;
		var flag = "detail";
		if (flag.toLowerCase() == "detail") {
			var tabpg = document.getElementById("tab_page1");
			clickTab(tabpg);
		} else if (flag.toLowerCase() == "supplier") {
			var tabpg = document.getElementById("tab_page5");
			clickTab(tabpg);
		} else {
			var tabpg = document.getElementById("tab_page1");
			clickTab(tabpg);
		}
	}
	function showPriceHistory() {
		var url2 = "/new/menugoods/menugoods_showPriceHistory.do?id=4028c9962be80b1c012bf5ed49041889";
		window.open(url2,null,"height=200,width=300 ,top=300, left=400,toolbar=no,   menubar=no,   scrollbars=no,   resizable=no,   location=no,   status=yes");
	}
	function reSetIframeSpdetailsIframe() {
		var iframe = document.getElementById('spdetails_iframe');
		try {
			var bHeight = iframe.contentWindow.document.body.scrollHeight; //iframe内容高度
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height;
			if (bHeight != 0) {
				height = bHeight;
			} else {
				height = dHeight;
			}
			iframe.height = height;
		} catch (ex) {
		}
	}
	window.setInterval("reSetIframeSpdetailsIframe()", 200);//重复设置Iframe的高度
</script>
<script language="javascript" event="onload" for="window">
	if (document.addEventListener) {
		document.addEventListener("DOMContentLoaded", initView, false);
	} else {
		initView();
	}
</script>
<SCRIPT type=text/javascript>
(function() { 
	var _sn = ["base20100719", "pshow20110623"]; 
	var _su = "http://misc.360buyimg.com/201007/skin/df/"; 
	var _sw = screen.width; 
	var _se, _st; 
	for (i in _sn) {
		_se = document.createElement("link"); 
		_se.type = "text/css"; 
		_se.rel = "stylesheet"; 
		if (_sw >= 1280) { 
			_st = _su + _sn[i] + ".w.css?t=20110531"; 
		} else { 
			_st = _su + _sn[i] + ".css?t=20110531"; 
		} 
		_se.href = _st; 
		document.getElementsByTagName("head")[0].appendChild(_se); 
	} })()
</SCRIPT>
<SCRIPT type=text/javascript src="../../js/jquery-1.2.6.pack.js"></SCRIPT>
<SCRIPT type=text/javascript src="../../js/g.base.js"></SCRIPT>
<SCRIPT type=text/javascript src="../../js/jd.lib.js"></SCRIPT>
<SCRIPT type=text/javascript src="../../js/p.pshow20110623.js"></SCRIPT>
</head>
<body leftMargin="0" topMargin="0" marginheight="0" marginwidth="0">
<div align="center">
<!-- LOGO -->
<%@include file="/portal/page/logo.jsp"%> 
</div>
<table cellSpacing="0" cellPadding="0" width="980" align="center" border="0">
<tbody>
<tr>
<td>
<table cellSpacing="0" cellPadding="0" width="100%" align="center" border="0">
<tbody>
<tr>
<td><br/></td>
</tr>
</tbody>
</table>
</TD>
</TR>
<TR>
<TD></TD>
</TR>
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD>
<DIV align=center>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD width=278>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD vAlign=top background="" height=212>
<TABLE cellSpacing=0 cellPadding=0 width=245 align=center border=0>
<TBODY>
<TR>
<TD>
<TABLE cellSpacing=3 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<!-- 
<TD  align=right rowSpan=2>
<img src="../../img/no_pic.gif" onerror="this.src='../../img/no_pic.gif'" width="245" height="180" alt="产品图片">
</TD>
 -->
<td>
<logic:notEmpty name="spForm" property="asFileList">
	<DIV id=preview>
	<DIV id=spec-n1 class=jqzoom>
		<IMG alt="产品图片" src="../../img/no_pic.gif" 
		width=350 height=350>
	</DIV>
	
	<DIV id=spec-n5>
	<DIV id=spec-left class=control></DIV>
	<DIV id=spec-right class=control></DIV>
	<DIV id=spec-list>
		<logic:iterate id="sp"  name="spForm" property="asFileList" type="com.ufgov.zc.common.zc.model.ZcBMerPic" scope="request" indexId="num">
			<UL class=list-h>
			  <LI>
				  <IMG src="/GB/portal/page/merchandise/PictureDisplay.do?fileID=<bean:write name="sp" property="zcPicID"/>" alt="产品图片" width=50 height=50/>
			  </LI>
			</UL>
		</logic:iterate>
	</DIV>
	</DIV>
	</DIV><!--preview end-->
</logic:notEmpty>
<logic:empty name="spForm" property="asFileList">
	<DIV id=preview>
	<DIV id=spec-n1 class=jqzoom>
		<IMG alt="产品图片" src="../../img/no_pic.gif" 
		width=350 height=350>
	</DIV>
	
	<DIV id=spec-n5>
	<DIV id=spec-left class=control></DIV>
	<DIV id=spec-right class=control></DIV>
	<DIV id=spec-list>
		<UL class=list-h>
		  <LI>
			  <IMG alt="产品图片" 
			  src="../../img/no_pic.gif"
			  width=50 height=50>
		  </LI>
		  <LI>
			  <IMG alt="产品图片" 
			  src="../../img/no_pic.gif"
			  width=50 height=50>
		  </LI>
		  <LI>
			  <IMG alt="产品图片" 
			  src="../../img/no_pic.gif"
			  width=50 height=50>
		  </LI>
		  <LI>
			  <IMG alt="产品图片" 
			  src="../../img/no_pic.gif"
			  width=50 height=50>
		  </LI>
		  <LI>
			  <IMG alt="产品图片" 
			  src="../../img/no_pic.gif" 
			  width=50 height=50>
		  </LI>
		 </UL>
	</DIV>
	</DIV>
	</DIV><!--preview end-->
</logic:empty>
</td>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
<TD vAlign=top width=400>
<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
<TBODY>
<TR>
<TD class=gyjg8 height=30>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>商品名称：</b></FONT><bean:write name="spForm" property="zcBMerchandise.zcMerName" />
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD class=gyjg8 height=27>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>型&nbsp;&nbsp;号：</b></FONT><bean:write name="spForm" property="zcBMerchandise.zcMerSpec" />
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD class=gyjg8 height=27>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>协议价：</b></FONT><bean:write name="spForm" property="zcBMerchandise.zcMerTax" format="#.00" />&nbsp;元/台
<!-- 
<img align="middle" title="协议价变更记录" style="CURSOR: pointer" onClick="showPriceHistory();" height="15" src="../../img/jgls.gif" width="51">
 -->
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD class=gyjg8 height=27>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>市场价：</b></FONT><bean:write name="spForm" property="zcBMerchandise.zcMerMPrice" format="#.00" />&nbsp;元/台
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD class=gyjg8 height=27>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>优惠率：</b></FONT><bean:write name="spForm" property="zcBMerchandise.zcYhl" format="0.00" />%
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD class=gyjg8 height=27>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD width=7>
<IMG height=8 hspace=8 src="../../img/gyjg_tub.gif" width=8>
</TD>
<TD class=gy_hs align=left>
<FONT color=#2c62c4><b>是否缺货：</b></FONT>正常
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD height=27>
<TABLE cellSpacing=0 cellPadding=0 width="90%" align=left border=0>
<TBODY>
<TR align="center">
<TD>
<a href="http://www.sony.com.cn/" target="_blank"><img style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" height=25 src="../../img/gyjg_buy2.gif" width=100></a>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
<TD width=298>
<TABLE style="PADDING-LEFT: 30px; TEXT-ALIGN: left" cellSpacing=0 cellPadding=0 width="100%" background="../../img/gyjg_bj.gif" border=0>
<TBODY>
<TR>
<TD vAlign=bottom height=147>
<TABLE class=yy cellSpacing=0 cellPadding=0 width="80%" align=right border=0>
<TBODY>
<TR>
<TD>
<STRONG><FONT color=#2c62c4>客户满意</FONT>
</STRONG>
</TD>
</TR>
<TR>
<TD height=45>
<IMG src="../../img/gyjg_xxhei.gif" border=0>
<IMG src="../../img/gyjg_xxhei.gif" border=0>
<IMG src="../../img/gyjg_xxhei.gif" border=0>
<IMG src="../../img/gyjg_xxhei.gif" border=0>
<IMG src="../../img/gyjg_xxhei.gif" border=0>
</TD>
</TR>
<TR>
<TD height=45>
<STRONG><FONT color=#2c62c4>累计成交数量：</FONT><FONT color=#f7941d><% Integer zcCjsl = ((com.ufgov.zc.server.zc.web.form.SpForm)request.getAttribute("spForm")).getZcBMerchandise().getZcCjsl(); if(  zcCjsl == null || 0 == zcCjsl.intValue()){out.print("暂无");}else{out.print( zcCjsl.intValue());} %></FONT> </STRONG>
</TD>
</TR>
<TR>
<TD height=20></TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="97%" align=center border=0>
<TBODY>
<TR>
<TD>
<TABLE style="PADDING-BOTTOM: 3px" cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR>
<TD vAlign=top height=36>
<TABLE id=tab_1 cellSpacing=0 cellPadding=0 width="100%" border=0>
<TBODY>
<TR align="center" height=33>
<TD width=10>&nbsp;</TD>
<TD class="normal_tabpg" id="tab_page1" onClick="clickTab(this);" vAlign="bottom" tabType="tp"><b>基本信息</b></TD>
<TD class="normal_tabpg" id="tab_page3" onClick="clickTab(this);" vAlign="bottom" tabType="tp"><b>详细信息</b></TD>
<!--<TD class="normal_tabpg" id="tab_page6" onClick="clickTab(this);" vAlign="bottom" tabType="tp">商品描述</TD> -->
<!--<TD class="normal_tabpg" id="tab_page4" onClick="clickTab(this);" vAlign="bottom" tabType="tp">服务承诺</TD> -->
<TD class="normal_tabpg" id="tab_page5" onClick="clickTab(this);" vAlign="bottom" tabType="tp"><b>供货商</b></TD>
<!-- <TD class="normal_tabpg" id="tab_page7" onClick="clickTab(this);" vAlign="bottom" tabType="tp">商品比较</TD>  -->
<TD class="normal_tabpg" id="tab_page8" onClick="clickTab(this);" vAlign="bottom" tabType="tp"><b>成交信息</b></TD> 
<TD>&nbsp;</TD>
</TR>
<TR height=3>
<TD style="BACKGROUND-COLOR: #9bbcf3" colSpan=10></TD>
</TR>
<TR></TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD vAlign=top>
<iframe id="spdetails_iframe" name="spdetails_iframe" src="" frameBorder="0" width="980" scrolling="no"></iframe>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</DIV><br>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
<TR>
<TD>
<!--页面底部-->
<!-- 页尾 -->
<%@include file="/portal/page/footer.jsp"%>
</TD>
</TR>
</TBODY>
</TABLE>
</TD>
</TR>
</TBODY>
</table>
</body>
</html>