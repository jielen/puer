<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.MenugoodsForm"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
  String tempCatalogueId = ((MenugoodsForm)request.getAttribute("menugoodsForm")).getCatalogueId();
  String spSearchUrl = request.getContextPath()+"/portal/page/menugoods/spSearch.do";
%>
<html:html lang="true">
<head>
<title>协议定点采购</title>
<!-- 定义语言编码 -->
<meta http-equiv="content-type" content="index/html; charset=gbk" />
<!-- 为搜索引擎准备的信息 -->
<meta name="keywords" content="西安市政府采购,政府采购,采购公告,采购动态,采购知识,采购资讯,代理机构,协议定点采购,政府采购供应商" name="keywords" />
<meta name="description" content="西安市政府采购网" />
<!-- 页头 -->
<%@include file="/portal/page/header.jsp"%>
<!-- 调用交替样式表 -->
<link id="skin_style22"  href="../../css/content.css" type="text/css" rel="stylesheet" />
<link id="skin_style90" href="../../css/content1.css" type="text/css" rel="stylesheet" />
<link id="skin_style92" href="../../css/content2.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../../js/skin.js"></script>
<script type="text/javascript" src="../../js/goodsSearchPage.js"></script>
<script language="JavaScript"  type="text/javascript">
//单击品牌显示型号，单击后变色
var sbrandId;//全局型号变量，在图片显示和列表显示切换时有用
var lastObj; //上一个被点击的品牌 
function showXh(obj,brandId,spzlId,xySpYear,xySpTime){
	sbrandId=brandId;
	if(lastObj!=null){
		lastObj.style.color=obj.style.color;
	} else{
		lastObj=document.getElementById("allBrand");
		lastObj.style.color=obj.style.color;
	}
	lastObj=obj;
	obj.style.color="#990400";
	/*
	var ordersValue;
	var obj = document.all?getElementsByName("orders"):document.getElementsByName("orders");
	for (var i=0; i<obj.length; i++){
		if(obj[i].checked == true){
			ordersValue = obj[i].value;
			break;
		}
	}
	*/
	initOrderImg();
	document.getElementById("spSearchForm").reset();
	var url = "<%=spSearchUrl%>?toImg="+toImg+"&catalogueId=<%=tempCatalogueId.toString()%>&brandId="+brandId+"&xySpYear="+xySpYear+"&xySpTime="+xySpTime;
	document.getElementById("searchRseult").src=url;
	changeURL(url);
}

//商品搜索
function spSearch(spzlId,xySpYear,xySpTime) {
	var keyWords = document.getElementById('someWords').value;
	var Youhl1 = document.getElementById('Youhl1').value;
	var Youhl2 = document.getElementById('Youhl2').value;
	var XyPrice1 = document.getElementById('XyPrice1').value;
	var XyPrice2 = document.getElementById('XyPrice2').value;

	//以下开始对输入的字符的合法性进行判断
	var patrn=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;\'\,.<>?]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;\'\,.<>?]{0,19}$/;
  	if ("" != keyWords && !patrn.exec(keyWords)) {
  		alert("关键字中包含非法字符!");
  		document.spSearchForm.someWords.select();
  		return false;
   	}
	if("" != XyPrice1 && XyPrice1.search("^-?\\d+(\\.\\d+)?$")!=0){
		alert("请输入一个数字!");
		document.spSearchForm.XyPrice1.select();
		return false;
	}
	if("" != XyPrice2 && XyPrice2.search("^-?\\d+(\\.\\d+)?$")!=0){
		alert("请输入一个数字!");
		document.spSearchForm.XyPrice2.select();
		return false;
	}				
	if("" != Youhl1 && Youhl1.search("^-?\\d+(\\.\\d+)?$")!=0){
		alert("请输入一个数字!");
		document.spSearchForm.Youhl1.select();
		return false;
	}
	if("" != Youhl2 && Youhl2.search("^-?\\d+(\\.\\d+)?$")!=0){
		alert("请输入一个数字!");
		document.spSearchForm.Youhl2.select();
		return false;
	}
	//判断结束
	var obj = document.all?getElementsByName("orders"):document.getElementsByName("orders");
	var orders;
	var ordersValue="";
	for (var i=0; i<obj.length; i++){
		if(obj[i].checked == true){
			ordersValue = obj[i].value;
			orders=i+1;
			orderPic(orders);
			break;
		}
	}
	document.getElementById('searchRseult').src="<%=spSearchUrl%>?toImg="+toImg+"&catalogueId=<%=tempCatalogueId.toString()%>&xySpYear="+xySpYear+"&xySpTime="+xySpTime
			+"&keyWords="+keyWords
			+"&youhl1="+Youhl1+"&youhl2="+Youhl2
			+"&xyPrice1="+XyPrice1+"&xyPrice2="+XyPrice2
			+"&orders="+ordersValue;
	var brandlist=document.all?getElementsByName("brand"):document.getElementsByName("brand");
	for(i=1;i<brandlist.length;i++){
		brandlist[i].style.color="";
	}
	brandlist[0].style.color="#990400";
	//document.getElementById("contentXh").style.display="none";
	//document.getElementById("xyTimes").innerHTML=""; //期次年置空
	document.getElementById("xyStartDate").innerHTML="";//协议起始时间置空
	document.getElementById("xyEndDate").innerHTML="";//协议结束时间置空
	changeURL("<%=spSearchUrl%>?catalogueId=<%=tempCatalogueId.toString()%>&xySpYear="+xySpYear+"&xySpTime="+xySpTime
			+"&keyWords="+keyWords
			+"&youhl1="+Youhl1+"&youhl2="+Youhl2
			+"&xyPrice1="+XyPrice1+"&xyPrice2="+XyPrice2);
	return true;
}

var orderURL="<%=spSearchUrl%>?catalogueId=<%=tempCatalogueId.toString()%>&xySpYear=2010&xySpTime=1"; //排序图标的URL 
function changeURL(theUrl){
	orderURL = theUrl;
}
</script>
</head>
<body onload="init();">
<!-- LOGO -->
<%@include file="/portal/page/logo.jsp"%>
  <div class="content">
  <div class="Products_left">
   <div id="tigtag2_content2_left" style="width:190px">
   <div id="tigtag2_content2_left1" style="width:190px"><div id="tigtag2_rightnew"> 协议定点</div></div>
    <!-- 品目列表 -->
    <logic:iterate id="catalogueMap" name="menugoodsForm" property="menugoods.catalogues" scope="request">
	<ul>
	  <div><h1><bean:write name="catalogueMap" property="key" scope="page" /></h1></div>
	  <logic:iterate id="catalogue" name="catalogueMap" property="value" type="com.ufgov.zc.common.zc.model.ZcBCatalogue" scope="page">
	    <li><nobr><html:link action="/portal/page/menugoods/listGoodsAndBrandsInMenu.do" paramId="catalogueId" paramName="catalogue" paramProperty="zcCatalogueCode"><bean:write name="catalogue" property="zcTypeName" /></html:link></nobr></li>
	  </logic:iterate>
	  </ul>
	</logic:iterate>
   </div>
  </div>
  <div class="Products_right">
 		<!-- 商品列表-->
		<div class="filter-box">
		<ul>
		<form name="form1" method="post" action="">
		  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
		      <td width="200">
		      <select class="filter_select" name="select" onChange="location='/new/menugoods/menugoods_listGoodsAndBrandsInMenu.do?spzlId=8af652091004da74011004df42990006&menuId=A030201'+this.value;this.disabled='disabled'">
				<option value="&xySpYear=2010&xySpTime=1"  selected="selected">2012年第1期</option>
				<option value="&xySpYear=2009&xySpTime=1" >2011年第1期</option>
				<option value="&xySpYear=2009&xySpTime=1" >2010年第1期</option>
				<option value="&xySpYear=2009&xySpTime=1" >2009年第1期</option>
				<option value="&xySpYear=2008&xySpTime=1" >2008年第1期</option>
				<option value="&xySpYear=2007&xySpTime=1" >2007年第1期</option>
			  </select></td>
			  <td nowrap="nowrap">&nbsp;有效期:2012-01-01 至 2012-12-31</td>
		      <td class="filter_wz">
		        <logic:notEmpty name="menugoodsForm" property="menugoods.cataloguesTitle">
		        <logic:iterate id="catalogueTitle" name="menugoodsForm" property="menugoods.cataloguesTitle" type="com.ufgov.zc.common.zc.model.ZcBCatalogue" scope="request">
		          	  <logic:equal value='<%=tempCatalogueId.toString()%>' name="catalogueTitle" property="zcCatalogueCode"><strong><font color="#FF0000"><bean:write name="catalogueTitle" property="zcTypeName" /></font></strong>|</logic:equal>
		          	  <logic:notEqual value='<%=tempCatalogueId.toString()%>' name="catalogueTitle" property="zcCatalogueCode"><html:link action="/portal/page/menugoods/listGoodsAndBrandsInMenu.do" paramId="catalogueId" paramName="catalogueTitle" paramProperty="zcCatalogueCode"><bean:write name="catalogueTitle" property="zcTypeName" /></html:link>|</logic:notEqual>
		        </logic:iterate>
		        </logic:notEmpty>
			  </td>
		    </tr>
		  </table>
		  </form>
		  </ul>
		  </div> 
  		<div class="f_box" >
		<div class="f_box1"><font size="2">按品牌选择：</font></div>
			<ul>
			<div id="Showlist">
			    <logic:notEmpty name="menugoodsForm" property="menugoods.brands">
			    <li><div id="allBrand" class="brand" name="brand" onclick="showXh(this,'','','2012','1')" style="cursor:pointer;color:#990400" >全部品牌</div></li>
			    <logic:iterate id="brand" name="menugoodsForm" property="menugoods.brands" type="com.ufgov.zc.common.zc.model.ZcBBrand" scope="request">
				    <li><div class="brand" name="brand" onclick="showXh(this,'<bean:write name="brand" property="zcBraCode" />','','2012','1')" style="cursor:pointer" ><bean:write name="brand" property="zcBraName" />(<bean:write name="brand" property="countNum" />)</div></li>
			    </logic:iterate>
			    </logic:notEmpty>
			</div>
			</ul>
  		</div>
  		<!--  
		  <div id="contentXh" style="display:none;">		
		      <iframe id="xh" name="xh" width="100%" onload="this.height=100" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" border="0" framespacing="0"></iframe>
		  </div>
		-->  
		  <div class="filter-box1">
		  <ul>
		  <form name="spSearchForm" id="spSearchForm" action="" method="post" target="searchRseult">
		  <li>
		  <label for="name">关键字<input name="keyWords" tabindex="1" type="text" id="someWords" class="inputt" size="48" maxlength="48" /><storng>(可按名称、品牌或型号模糊查询)</storng></label>
		  </li>
		  <li>
		    <label for="name">协议价<input name="XyPrice1" tabindex="2" type="text" id="XyPrice1" class="inputt" size="6">至<input name="XyPrice2" tabindex="3" type="text" id="XyPrice2" class="inputt" size="6">元</label>
			<label for="name">优惠率<input name="Youhl1" tabindex="4" type="text" id="Youhl1" class="inputt" size="4">至<input name="Youhl2" tabindex="5" type="text" id="Youhl2" class="inputt" size="4">%</label>
		  	</li>
		  <li> 
		   <label for="name"><input type="radio" name="orders" tabindex="8" value="ZC_BRA_NAME" />品牌排序&nbsp;&nbsp;</label>
		   <label for="name"><input type="radio" name="orders" tabindex="9" value="ZC_MER_M_PRICE asc" />协议价低-&gt;高&nbsp;&nbsp;</label> 
		   <label for="name"><input type="radio" name="orders" tabindex="10" value="ZC_MER_M_PRICE desc" />协议价高-&gt;低&nbsp;&nbsp;</label>
		   <label for="name" style="margin-left:50px;display:inline;"><input type="button" value="查询" tabindex="11" onclick="spSearch('8af652091004da74011004df42990006','2010','1');" style="cursor:pointer;"/>&nbsp;<input type="button" value="重置" tabindex="12" onclick="spSearchForm.reset();" style="cursor:pointer;"/></label></li>
		  </form>
		  </ul>
		 </div>
		<div class="filter-box2">
			<li>
				<div style="FLOAT: left;padding-top:5px;"><input id="ImgTrue" onclick="!this.checked;switchShowMod('true');" type="checkbox" name="ImgMod" title="大图" /></div>
				<span><div align="left"><img style="CURSOR: pointer" onclick="switchShowMod('true');" title="大图" src="../../img/qb2.gif" /></div></span>
				<div style="FLOAT: left;padding-top:5px;"><input id="ImgFalse" onclick="!this.checked;switchShowMod('false');" type="checkbox" name="ImgMod" title="列表" /></div>
				<span><div align="left"><img style="CURSOR: pointer" onclick="switchShowMod('false');" title="列表" src="../../img/qb3.gif" /></div></span>
				<div style="FLOAT: left;padding-top:5px;"><input id="ImgNo" onclick="this.checked=!this.checked;switchShowMod('no');" type="checkbox" name="ImgMod" title="简表" /></div>
				<span><img style="CURSOR: pointer" onclick="switchShowMod('no');" title="简表"src="../../img/jd.gif" /></span>
				<div style="FLOAT: left;">有效期：<font id="xyStartDate"></font>至<font id="xyEndDate"></font></div>
				<div style="FLOAT: left;padding-top:5px;padding-left:3px;"><select class="filter_select" onchange="orderPic(this.value);" name="selectOrder"><option id="order1" value="1">按品牌排序</option><option id="order2" value="2">按协议价升序</option><option id="order3" selected="selected" value="3">按协议价降序</option></select></div>
				<div style="FLOAT: left;padding-top:5px;padding-left:3px;"><img style="CURSOR: pointer" id="pic1" onclick="orderPic('1');" title="品牌排序" src="../../img/pl11.gif" /></div>
				<div style="FLOAT: left;padding-top:5px;padding-left:3px;"><img style="CURSOR: pointer" id="pic2" onclick="orderPic('2');" title="协议价升序" src="../../img/pl21.gif" /></div>
				<div style="FLOAT: left;padding-top:5px;padding-left:3px;"><img style="CURSOR: pointer" id="pic3" onclick="orderPic('3');" title="协议价降序" src="../../img/pl31.gif" /></div>
			</li>
		</div>
		<!-- 商品列表-->
		<iframe id="searchRseult" name="searchRseult" width="100%" height="550" marginwidth="0" marginheight="0" src="" frameborder="0" scrolling="no"></iframe></div>
		<!-- 商品列表结束-->
		<script language="JavaScript" type="text/javascript">
			document.getElementById('searchRseult').src=orderURL+"&toImg="+toImg;
			chooseOne(toImg);
		</script>
  </div>
  <div style="clear:both"></div>
<!-- 页尾 -->
<%@include file="/portal/page/footer.jsp"%>
</body>
</html:html>