<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
  String path = request.getContextPath();
  String homePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <link rel="StyleSheet" href="./../../css/dtree.css" type="text/css" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<script type="text/javascript">
	var rowNum = 0;
	</script>
  </head>

	<body leftmargin="0" topmargin="0">

	
		<table width="180">
			<tr>
				<td height="300" valign="top" nowrap="nowrap">
					<script type="text/javascript" src="./../../js/dtree.js"></script>
					<script type='text/javascript'>
					rowNum = <%=(String)request.getAttribute("rowNum")%>
			tree = new dTree('tree');
			tree.add(0,-1,"所有专家类型");
			tree.config.folderLinks=false;
			tree.config.useCookies=false;
			<logic:iterate id="tree" name="expertForm" property="treeList" type="com.ufgov.zc.common.zc.model.EmExpertType" scope="request" indexId="i">
				tree.add("<bean:write name="tree" property="emTypeCode"/>","<bean:write name="tree" property="emParentTypeCode"/>","<bean:write name="tree" property="emTypeName"/>");
			</logic:iterate>
			      document.write(tree);
			      
</script>
				</td>
			</tr>
		</table>
	</body>
</html>
