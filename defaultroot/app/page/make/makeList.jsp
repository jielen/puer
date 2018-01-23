<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SupplierFormBean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
	<head>
		<title>商品列表</title>
		<link  href="../../css/reset.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/foot.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/style.css" type="text/css" rel="stylesheet" />
		<style>
			body {font-size:12px;}
			td {font-size:12px;}
            footer{text-align: center;}
        </style>
        <script src="../../js/jquery-1.4.4.min.js"></script>
       
        <script>
        	$(function(){
        		$("#makeListTable tr:even").addClass("green");
        		
				$("#makeListTable tr").each(function(){
					$(this).hover(function() { 
					   $(this).addClass("black"); 
					}, function() { 
					   $(this).removeClass("black"); 
					}); 
				});

        		
        	})
        </script>
        
	</head>
<%
  SupplierFormBean supplierFormBean = (SupplierFormBean)request.getAttribute("SupplierFormBean");
  int countNum = ((SupplierFormBean)request.getAttribute("SupplierFormBean")).getCountNum().intValue();
  String status=supplierFormBean.getSupplier().getStatus();
  
%>	
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0">
<div align="center">
<!-- LOGO -->
<%@include file="/portal/page/logo.jsp"%> 
</div>
	<div class="inavegate">
		当前位置：<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/index/index.html"class="font01">首页</a>&nbsp;&nbsp;>>商品列表&nbsp;&nbsp;
	</div>
	<div class="imakelistTable">		
		<table id="makeListTable" border="0" align="center" cellpadding="0" cellspacing="0" class="font01">
			<col width="80">
			<col width="10">
			<col width="10">
			<col width="28">
			<col width="24">
			<col width="20">
			<col width="20">
			<tbody>
			<tr class="itemhead">
				<th>项目名称</th>
				<th>联系人</th>
				
				<th>项目预算</th>
				<th>申请自筹金额合计</th>
				<th>项目执行金额</th>
				<th>项目截止时间</th>
				<th>操作</th>
			</tr>
			<logic:iterate id="makeItem" name="SupplierFormBean" property="suList" type="com.ufgov.zc.common.zc.model.ZcPProMake" scope="request">   
			<tr>
				<td class="itemleft">
					<a href="<%=request.getContextPath() %>/portal/page/make/indexMakeShow.do?zcMakeCode=<bean:write name="makeItem" property="zcMakeCode" />"
						target="_blank" title="<bean:write name="makeItem" property="zcMakeName" />"
						class="font01"> <bean:write name="makeItem" property="zcMakeName" /> </a>
				</td>
				<td>
					<bean:write name="makeItem" property="zcMakeLinkman"/>
				</td>
				
				<td>
					<bean:write name="makeItem" property="zcMoneyBiSum"/>
				</td>
				<td>
					<bean:write name="makeItem" property="zcMoneyZcSum"/>
				</td>
				<td>
					<bean:write name="makeItem" property="zcMoneyBiYzx"/>
				</td>
				<td align="right" style="padding-right: 10px">
					<bean:write name="makeItem" property="zcXieYiEndDate" format="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="<%=request.getContextPath() %>/portal/page/make/indexMakeShow.do?zcMakeCode=<bean:write name="makeItem" property="zcMakeCode" />"  target="_blank" title="<bean:write name="makeItem" property="zcMakeName" />" class="font01">查看</a>
				</td>
			</tr>
            </logic:iterate>
            </tbody>
		</table>
	</div>	
	<div class="ipaginframe">
		    <div class="ipagination">                    
		       <%=supplierFormBean.getPagination().getPaginationText()%>
		    </div>  
	</div>
	<div id="footer" class="font01">
         <%@include file="/portal/page/footer.jsp"%>
	</div>
	<form action="<%=request.getContextPath() %>/portal/page/supplier/indexListMakeMore.do?status=<%=status==null?"":status %>" name="SupplierFormBean" id="SupplierFormBean" method="post">				
       </form>
	</body>
</html>
