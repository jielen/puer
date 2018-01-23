<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SupplierFormBean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.ufgov.zc.server.zc.dao.pagination.*"%>
<html>
	<head>
		<title>注册供应商</title>
		<link  href="../../css/foot.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/style.css" type="text/css" rel="stylesheet" />
		<style>
			body {font-size:12px;}
			td {font-size:12px;}
            footer{text-align: center;}
        </style>
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
		<table width="1000" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td width="227" valign="top" bgcolor="#D8E1FA">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="titleBLue">
									<tr>
										<td>
											<img src="../../img/blueTitleR.jpg"
												width="7" height="27" alt="">
										</td>
										<td width="100%"
											background="../../img/blueTitleC.jpg">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td class="titleBLue">
														注册供应商
														<div class="font_blue"></div>
													</td>
												</tr>
											</table>
										</td>
										<td>
											<img src="../../img/blueTitleL.gif"
												width="6" height="27" alt="">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="tableBlue2">
									<tr>
										<td colspan="2" valign="top">
											<table width="96%" border="0" align="center" cellpadding="0"
												cellspacing="0" class="font01">
												<tr>
													<td>
														<a
															href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do"
															style="color: #000">全部</a>
													</td>
												</tr>
												<tr>	
													<td>
														<a
															href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do?status=0"
															style="color: #000">未审核</a>
													</td>
												</tr>
												<tr>
													<td>
														<a
															href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do?status=2"
															style="color: #000">已审核</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td width="773" valign="top">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="1" class="tableBlue2">
						<tr>
							<td height="28"
								class="font01" bgcolor="#EAEAEA">
								当前位置：
								<a href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/page/index/index.html"
									class="font01">首页</a>&nbsp;&nbsp;>>
								注册供应商&nbsp;&nbsp;
							<%if("0".equals(status)) {%>
								>><a href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do?status=<%=status %>"
									class="font01">未审核</a>
							<%}else if("2".equals(status)){ %>
								>><a href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do?status=<%=status %>"
									class="font01">已审核</a>
							<%} %>	
							</td>
						</tr>
						<tr height="404">
							<td bgcolor="#FFFFFF" valign="top">
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="font01">
									<logic:iterate id="supplier" name="SupplierFormBean" property="suList" type="com.ufgov.zc.common.zc.model.ZcEbSupplier" scope="request">   
									<tr>
										<td width="81%" height="22">
											·
											<a href="<%=request.getContextPath() %>/portal/page/supplier/supplierDetails.do?zcSuCode=<bean:write name="supplier" property="code" />"
												target="_blank" title="<bean:write name="supplier" property="name" />"
												class="font01"> <bean:write name="supplier" property="name" /> </a>
										</td>
										<td width="18%" align="right" style="padding-right: 10px">
											<bean:write name="supplier" property="zcCreateDate" format="yyyy-MM-dd"/>
										</td>
									</tr>
						            </logic:iterate>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center" bgcolor="#FFFFFF">
                               <div class="pagination" align="right">
                                   <%=supplierFormBean.getPagination().getPaginationText()%>
                               </div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div id="footer" class="font01">
          <%@include file="/portal/page/footer.jsp"%>
		</div>
		<form action="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do?status=<%=status==null?"":status %>" name="SupplierFormBean" id="SupplierFormBean" method="post">				
       </form>
	</body>
</html>
