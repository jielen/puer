<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%
  String path = request.getContextPath();
  String homePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
		<head>
	  <title>供应商注册信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link href="../css/Style.css" type="text/css" rel="stylesheet">
		
		<script language=javascript src="../js/validation-framework.js"></script>
		
		<script>
		   ValidationFramework.init("../validation/regSupplier_validation-config.xml");	
	  </script>
	  
	  <script language=javascript src="../js/commscript.js"></script>

		<script for=window event=onload language="JScript">
		  document.all.cfmpwd.value = "";
		  document.all.pwd.value = "";
		  isDisplaySNW(document.all.gnw.value);
		</script>


		<script>
	      function isDisplaySNW(v){
	          if(v == '1'){
	              document.all.snw.disabled = false;
	          }else{
	              document.all.snw.disabled = true;
	              document.all.snw.value = "";
	          }
	      }
	  </script>
	</head>

	<body>

		<html:form action="/basedata/regSupplier.do" styleId="form0" method="post">
			<table align=center class="table2" cellSpacing=1 cellPadding=3 width="95%" >
			  <tbody>
			  <tr vAlign=center>
			    <td class="td5" width="34%"><IMG height=30 src="../images/icon01.gif" width=33 align=absMiddle>&nbsp;&nbsp;注册供应商信息</TD>
			  </tr>
			  </tbody>
			</table>
			<br>
		   
		  <table align=center class="table3" cellSpacing=1 cellPadding=0 width="95%">
				<tbody>
				<tr height=20>	
					<td class="td4" align="right" width="10%">登录ID&nbsp;</td>
					<td class="td5" align="left" colspan="2">&nbsp;&nbsp;&nbsp;<html:text property="supplier.loginId" size="20" styleClass="con1" />&nbsp;&nbsp;</td>
					<td class="td4" align="right" width="10%">显示名称&nbsp;</td>
					<td class="td5" align="left" colspan="2">&nbsp;&nbsp;&nbsp;<html:text property="supplier.displayName" size="20" styleClass="con1" />&nbsp;&nbsp;</td>
				</tr>
				<tr height=20>	
					<td class="td4" align="right" width="10%">登录密码&nbsp;</td>
					<td class="td5" align="left" colspan="2">&nbsp;&nbsp;&nbsp;<html:password property="supplier.pwd" size="20" styleClass="con1" styleId="pwd"/>&nbsp;&nbsp;</td>
					<td class="td4" align="right" width="10%">确认密码&nbsp;</td>
					<td class="td5" align="left" colspan="2">&nbsp;&nbsp;&nbsp;<html:password property="supplier.cfmpwd" size="20" styleClass="con1" styleId="cfmpwd"/>&nbsp;&nbsp;</td>
				</tr>
        </tbody>
      </table>
      
      <br>
			<table class=table2 cellSpacing=1 cellPadding=0 width="100%" align=center>
 				<tr height=20>										
					<td class="td5" align="center">
			    	<input type="button" class="but1" value="注册" style="cursor: hand" onclick="goActionWithTargetDoValidation(0,'./regSupplier.do','');">&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="reset" class="but1" value="取消" style="cursor: hand" onclick="goActionWithTarget(0,'<%=homePath %>','');">&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>																											
				</tr>
			</table>   	
			
			<table class=table2 cellSpacing=1 cellPadding=0 width="100%" align=center>
 				<tr height=20>										
					<td class="td5" align="center">
					  <html:messages id="msg" header="messages.header" footer="messages.footer" message="false">
						  <font color="red"><bean:write name="msg" /></font>
						</html:messages>
			    </td>																											
				</tr>
			</table>
		</html:form>
	</body>
</html>
