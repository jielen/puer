<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.AgencyFormBean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.ufgov.zc.server.zc.dao.pagination.*"%>
<html>
	<head>
		<title>西安市政府采购</title>
		<link  href="../../css/foot.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/style.css" type="text/css" rel="stylesheet" />
		<style>
			body {font-size:12px;}
			td {font-size:12px;}
            footer{text-align: center;}
        </style>
	</head>
<%
  AgencyFormBean agencyFormBean = (AgencyFormBean)request.getAttribute("agencyFormBean");
  int countNum = ((AgencyFormBean)request.getAttribute("agencyFormBean")).getCountNum().intValue();
  String status=agencyFormBean.getAgency().getZcStatCode();
  
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
														政府采购代理机构
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
															href="<%=request.getContextPath() %>/portal/page/agency/agencyList.do"
															style="color: #000">全部</a>
													</td>
												</tr>
												<tr>	
													<td>
														<a
															href="<%=request.getContextPath() %>/portal/page/agency/agencyList.do?status=0"
															style="color: #000">未审核</a>
													</td>
												</tr>
												<tr>
													<td>
														<a
															href="<%=request.getContextPath() %>/portal/page/agency/agencyList.do?status=1"
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
								政府采购代理机构&nbsp;&nbsp;
							<%if("0".equals(status)) {%>
								>><a href="<%=request.getContextPath() %>/portal/page/agency/agencyList.do?status=<%=status %>"
									class="font01">未审核</a>
							<%}else if("1".equals(status)){ %>
								>><a href="<%=request.getContextPath() %>/portal/page/agency/agencyList.do?status=<%=status %>"
									class="font01">已审核</a>
							<%} %>	
							</td>
						</tr>
						<tr height="404">
							<td bgcolor="#FFFFFF" valign="top">
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0" class="font01">
									<logic:iterate id="agency" name="agencyFormBean" property="agencyList" type="com.ufgov.zc.common.zc.model.ZcBAgency" scope="request">   
									<tr>
										<td width="81%" height="22">
											·[<bean:write name="agency" property="zcDiyuName" />]    
											<a href="<%=request.getContextPath() %>/portal/page/agency/agencyDetails.do?zcAgeyCode=<bean:write name="agency" property="zcAgeyCode" />"
												target="_blank" title="<bean:write name="agency" property="zcAgeyName" />"
												class="font01"> <bean:write name="agency" property="zcAgeyName" /> </a>
										</td>
										<td width="18%" align="right" style="padding-right: 10px">
											<bean:write name="agency" property="zcInputDate" format="yyyy-MM-dd"/>
										</td>
									</tr>
						            </logic:iterate>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center" bgcolor="#FFFFFF">
                               <div class="pagination" align="right">
                                   <%=agencyFormBean.getPagination().getPaginationText()%>
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
		<form action="<%=request.getContextPath() %>/portal/page/agency/agencyList.do?status=<%=status==null?"":status %>" name="agencyFormBean" id="agencyFormBean" method="post">				
       </form>
	</body>
</html>
