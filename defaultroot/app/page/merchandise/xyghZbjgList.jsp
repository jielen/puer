<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.MenugoodsForm"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SpForm"%>
<%@page import="com.ufgov.zc.server.zc.ZcSUtil"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
  int countNum = ((SpForm)request.getAttribute("spForm")).getCountNum().intValue();
  String poCode = (String)request.getAttribute("poCode");
%>
<%
  String spSearchUrl = "/GB/portal/page/merchandise/XyghZbjgList.do"; 
  String message = (String)request.getAttribute("meaasge");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>协议供货招标项目列表</title>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/base.js"></script>
<script type="text/javascript" src="../../js/admin.js"></script>
<link href="../../css/webstyle.css" rel="stylesheet" type="text/css" />
<link href="../../css/admin.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript">

function ck(){
	var $idsChecked = $("#listTable input[name='ids']:checked");
	if ($idsChecked.size() > 0) {
		if(confirm("已经导入内网的数据将不能进行删除,未导入到内网的将被删除！\r\r您确定要删除这些数据吗？")){
			return true;
		}else{
			return false;	
		}
	} else {
		alert("请至少选择一条数据！");
		return false;
	}
}

//去掉String前后空格
function   String.prototype.Trim(){
	return   this.replace(/(^\s*)|(\s*$)/g,"");
}

</script>
	</head>
	<body class="list">
		<div class="bar">
				<input type="button" class="formButton" onclick="location.href='/GB/portal/page/merchandise/cgjh_zbjg.jsp'" value="新 增" />
				&nbsp;&nbsp;&nbsp;&nbsp;
		
			<input type="button" class="formButton" onclick="location.href='/GB/portal/page/merchandise/XyghZbjgList.do'" value="返 回" />
		</div>
		<div class="bar" align="center">
			<font color="blue" size="3"><B>协议供货招标项目列表</B></font>
		</div>
		<div class="body">
			<form id="listForm" action="/GB/portal/page/merchandise/DeleteXyghZbjg.do" method="post">

				<table id="listTable" class="listTable">
					<tr>
						<th class="check">
							<input type="checkbox" class="allCheck" />
						</th>
						<th>
							<span class="sort">项目编码</span>
						</th>
						<th>
							<span class="sort">项目名称</span>
						</th>
						<th>
							<span class="sort">年度</span>
						</th>
						<th>
							<span class="sort">状态</span>
						</th>
						<th>
							<span class="sort">生效时间</span>
						</th>
						<th>
							<span class="sort">失效时间</span>
						</th>
						<th>
							操作
						</th>
					</tr>
					<logic:notEmpty name="spForm">
						<logic:iterate id="sp" name="spForm"  property="xyghZbjgList" type="com.ufgov.zc.common.zc.model.ZcXyghZbjg" scope="request">
							<tr>
								<td>
									<input type="checkbox" name="ids" id="ids"
										value='<bean:write name="sp" property="zcProjId"/>' />
								</td>
								<td>
									<bean:write name="sp" property="zcProjId" />
								</td>
								<td>
									<bean:write name="sp" property="zcProjNa" />
								</td>
								<td>
									<bean:write name="sp" property="zcProjNd" />
								</td>
								<td>
									<bean:write name="sp" property="zcProjStatus" />
								</td>
								<td>
									<bean:write name="sp" property="zcBgnDate" format="yyyy-MM-dd"/>
								</td>
								<td>
									<bean:write name="sp" property="zcEndDate" format="yyyy-MM-dd"/>
								</td>
								<td>
									<a href="/GB/portal/page/merchandise/EditXyghzbjg.do?id=<bean:write name='sp' property='zcProjId' />"
										title="编辑">[编辑]</a>
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
					<div class="bodyBottom">
						<div class="delete">
							<input type="submit" id="deleteButton" class="formButton" onclick="return ck()" value="删  除"/>
						</div>
					</div>
			</form>
		</div>
		<div class="pagination" align="right">
			<% SpForm sp = (SpForm)request.getAttribute("spForm"); %>
			<%=sp.getPagination().getPaginationText()%>
		</div>
		<form action="<%=spSearchUrl %>?keyWords=<%=sp.getKeyWords()!=null?sp.getKeyWords() :"" %>" name="spForm" id="spForm" method="post"></form>
	</body>
</html>

