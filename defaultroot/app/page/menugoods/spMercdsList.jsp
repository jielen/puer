<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SpForm"%>
<%@page import="com.ufgov.zc.server.zc.ZcSUtil"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.ufgov.zc.server.zc.dao.pagination.*"%>
<%@page import ="com.anyi.gp.pub.SessionUtils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
  int countNum = ((SpForm)request.getAttribute("spForm")).getCountNum().intValue();

  String spSearchUrl = request.getContextPath()+"/portal/page/menugoods/spSearch.do";
  
  String spDetailsUrl = request.getContextPath()+"/portal/page/menugoods/spDetails.do";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<!-- 调用交替样式表 -->
<link id="skin_style" href="../../css/content1.css" type="text/css" rel="stylesheet">
<link href="../../css/products.css" type="text/css" rel="stylesheet">
<script language="javascript" type="text/javascript">
function showDetails(id){
  window.open("<%=spDetailsUrl%>?zcMerCode="+id+"&xySpYear=2010&xySpTime=1");
}

function frameSkin(){
  if (top.thisskin){
	var css=top.thisskin;
  }
  if (top.thisskin!=null && top.thisskin!=''){
    //  alert(top.thisskin);
	document.getElementById('skin_style').href="../../css/content1.css";
  }
}

var highlightcolor='#f9f2d4';//鼠标经过背景色
var clickcolor='#ffe0e9';//鼠标选中背景色
var lastSource;//保存上次选中的对象
var lastClassName;//移除的类名
var last2ClassName;//移除的类名
var replaceClassName = "row3";//替换的类名
FixPrototypeForGecko();

function  changeto(event){
	event = (event)?event:window.event;
	try{
		source=event.srcElement?event.srcElement:event.target;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
		while(source.tagName!="TD")
			source=source.parentElement?source.parentElement:source.parentNode;
		source=source.parentElement?source.parentElement:source.parentNode;
		if  (source.style.backgroundColor!=highlightcolor&&source.id!="nc"&&source.style.backgroundColor!=clickcolor){
			if(source.className=="row1")
			source.className = replaceClassName;
			source.style.backgroundColor=highlightcolor;
		}
	}catch (ex){}
}

function  changeback(event){
	event = (event)?event:window.event;
	try{
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
		return
		if  (event.toElement!=source&&source.style.backgroundColor!=clickcolor){
			//source.style.backgroundColor=originalcolor
			if(source.className==replaceClassName)
			source.className = "row1";
			source.style.backgroundColor="";
		}
	}catch (ex){}
}

//创建兼容 IE/FireFox 的 event 及 event 的 srcElement、fromElement、toElement 属性----start   
function  FixPrototypeForGecko()  {
  if(!!window.find){
    HTMLElement.prototype.contains = function(B){
     return this.compareDocumentPosition(B) - 19 > 0
    }
    HTMLElement.prototype.__defineGetter__("runtimeStyle",element_prototype_get_runtimeStyle);     
    window.constructor.prototype.__defineGetter__("event",window_prototype_get_event);     
    Event.prototype.__defineGetter__("srcElement",event_prototype_get_srcElement);     
    Event.prototype.__defineGetter__("fromElement",  element_prototype_get_fromElement);     
    Event.prototype.__defineGetter__("toElement", element_prototype_get_toElement);
  }     
}

function  element_prototype_get_runtimeStyle() { return  this.style; }
function  window_prototype_get_event() { return  SearchEvent(); }
function  event_prototype_get_srcElement() { return  this.target; }
function element_prototype_get_fromElement() {
  var node;
  if(this.type == "mouseover") node = this.relatedTarget;
  else if (this.type == "mouseout") node = this.target;
  if(!node) return;
  while (node.nodeType != 1)
      node = node.parentNode;
  return node;
}

function  element_prototype_get_toElement() {
    var node;
    if(this.type == "mouseout")  node = this.relatedTarget;
    else if (this.type == "mouseover") node = this.target;
    if(!node) return;
    while (node.nodeType != 1)
     node = node.parentNode;
    return node;
}

function  SearchEvent() {
  if(document.all) return  window.event;
  func = SearchEvent.caller;
  while(func!=null){
      var  arg0=func.arguments[0];
      if(arg0 instanceof Event) {
          return  arg0;
      }
     func=func.caller;
  }
  return null;
}
</script>
<style type="text/css">
.row3 {
	FONT-SIZE: 12px; BACKGROUND: #ffffff; HEIGHT: 29px
}
</style>
</head>
<body onload="frameSkin();">
<!-- <SCRIPT src="../../js/compare.js" type=text/javascript></SCRIPT> -->

<div class="filter-box5">
<a href=<%=request.getContextPath()+"/portal/page/menugoods/spMercdAdd.do" %>>新增</a>
<table class="list" onmouseover="changeto()" style="DISPLAY: inline" onmouseout="changeback()" cellSpacing="0" cellPadding="0" width="100%" border="0">
  <tbody>
  <tr class="headtr" id="nc">
    <td width="30">比较</td>
    <td width="190">商品名称</td>
    <td width="90">品目</td>
    <td width="80">品牌</td>
    <td width="130">型号</td>
    <td width="70">市场价(元)</td>
    <td width="70">协议价(元)</td>
    <td width="50">优惠率</td>
    <td width="65">成交量</td>
  </tr>
  <logic:iterate id="sp" name="spForm" property="merchandiseList" type="com.ufgov.zc.common.zc.model.ZcBMerchandise" scope="request" indexId="indexId">
  <tr class="row<% if(((Integer)indexId).intValue()%2==0){out.print("0");}else{out.print("1");}%>">
    <td class="t2" width="30"><input type=checkbox></td>
    <% pageContext.setAttribute("zcMerName",ZcSUtil.substring(sp.getZcMerName(),28,".."));%>
    <td class="t3" width="190"><font title="<bean:write name="sp" property="zcMerName" />" style="CURSOR: pointer" onclick="showDetails('<bean:write name="sp" property="zcMerCode" />');"><bean:write name="zcMerName" scope="page"/></font></td>
    <td class="t2" width="90"><bean:write name="sp" property="zcCatalogueName" /></td>
    <td class="t2" width="80"><font title="<bean:write name="sp" property="zcBraName" />"><bean:write name="sp" property="zcBraName" /></font></td>
    <% pageContext.setAttribute("zcMerSpec",ZcSUtil.substring(sp.getZcMerSpec(),18,".."));%>
    <td class="t5" width="130"><font title="<bean:write name="sp" property="zcMerSpec" />"><bean:write name="zcMerSpec" scope="page"/></font></td>
    <td class="t2" width="70">￥<bean:write name="sp" property="zcMerTax" format="#.00"/></td>
    <td class="t2" width="70">￥<bean:write name="sp" property="zcMerMPrice" format="#.00"/></td>
    <td class="t2" width="50"><bean:write name="sp" property="zcYhl" format="0.00"/>%</td>
    <td class="t5" width="65">&nbsp;&nbsp;&nbsp;<% if(sp.getZcCjsl() == null || 0 == sp.getZcCjsl().intValue()){out.print("暂无");}else{out.print( sp.getZcCjsl().intValue());} %></td>
  	<td class="t2" width="50"><a href=<%=request.getContextPath()+"/portal/page/menugoods/spMercdEdit.do?zcMerCode=" %><bean:write name="sp" property="zcMerCode" />>修改</a></td>
  </tr>
  </logic:iterate>
  </tbody>
</table>
</div>
         <% SpForm sp = (SpForm)request.getAttribute("spForm"); %>
<div class="pagination" align="right">
<%=sp.getPagination().getPaginationText()%>
</div>
<form action="<%=spSearchUrl %>?catalogueId=<%=sp.getCatalogueId()%>&brandId=<%=sp.getBrandId()!=null?sp.getBrandId():"" %>&order=<%=sp.getOrders()!=null?sp.getOrders():"" %>&keyWords=<%=sp.getKeyWords()!=null?sp.getKeyWords() :"" %>" name="spForm" id="spForm" method="post">				
</form>
</body>
</html>
