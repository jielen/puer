<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<html>
<head>
<LINK href="script/applus.css" rel=stylesheet type=text/css>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script language="JavaScript" >
function login(formName){ 
  var expertName=document.all.expertName.value;
  var expertIDNo=document.all.expertIDNo.value;
  var projCodeSel=document.getElementsByName("projCodeSel")[0];
  var selectedProjCode= projCodeSel.options[projCodeSel.options.selectedIndex].value;
  document.all.projCode.value=selectedProjCode;
  //alert(expertName);
  //alert(expertIDNo);
  //alert(document.all.projCode.value);
  var msg="";
  if(null==expertName || expertName.length == 0){
  	 msg+="请输入专家姓名!\n";
  }
  if(null==expertIDNo || expertIDNo.length == 0){
  	 msg+="请输入证件号码!\n";
  }
  if(msg.length>0){
  	alert(msg);
  	return ;
  }
  /**
  var action = formName.action;
  action+="&expertName="+expertName+"&expertIDNo="+expertIDNo+"&projCode="+selectedProjCode;
  formName.action=action;
  alert(formName.action);
  */
  formName.submit();
}


function moveFocus(){
  if(event.keyCode == 13){
    var expertName = document.getElementById("expertName").value;
    if(expertName == null || expertName.length == 0){
      alert("请输入专家姓名!");
    }else{
      document.getElementById("expertIDNo").focus();
    }
  }
}

function init(){
  document.getElementById("expertName").focus();
  clearAllTxt();
}


function clearAllTxt(){
  document.getElementById("expertName").value="";
  document.getElementById("expertIDNo").value="";
}
</script>

<title>专家信息录入</title>
<style type="text/css">
<!--
.AnyiTitle {
  font-family: "宋体";
  font-size: 24px;
  font-weight: bold;
}
.unChangedFontSize {
  font-family: "宋体";
  font-size: 16px;
}
-->

.sffocus {
	BORDER-RIGHT: #f90 1px solid; BORDER-TOP: #f90 1px solid; BACKGROUND: #fff; BORDER-LEFT: #f90 1px solid; COLOR: blue; BORDER-BOTTOM: #f90 1px solid
}
</style>
</head>

<%!
  public static List getOpenedProjList() throws SQLException{
    String sql="SELECT P.PROJ_CODE,P.PROJ_NAME FROM V_ZC_EB_TO_EVAL_PROJ P ";
    return com.anyi.gp.pub.DBHelper.queryToList(sql,null);
  }
%>
	<body  leftMargin="20" rightMargin="100" topMargin="60" onload="init();">
	<table border=0 cellpadding="0" cellspacing="0" width="600"  align="center" border="1">
			<form id="formName" name="fLogin" method="POST" action="loginExpert.jsp?className=<%=request.getParameter("className")%>&token=<%=request.getParameter("token")%>">
		<tr>
		  <td width="600" height="60" align="center" colspan="3" class="AnyiTitle">专家信息录入</td>
		</tr>  
		<tr>
		  <td width="600" align="center" colspan="3">
		  <table border="0" cellpadding="0" cellspacing="0">
	          <tr>
	             <td width="20%" height="40" align="right"  class="unChangedFontSize" >专家姓名:</td>
	             <td><input id="expertName" type="text" name="expertName" value="" size="20" tabindex="1" onkeyup="moveFocus()"  style="border:1 solid #6398E6;"></td>
	          </tr>
	          <tr>
	             <td width="20%"  height="40" align="right"  class="unChangedFontSize" >证件号码:</td>
	             <td><input id="expertIDNo" type="text" name="expertIDNo" value="" size="20" tabindex="2"    style="border:1 solid #6398E6;"></td>
	          </tr>
	          <tr>
	             <td width="20%"  height="40" align="right" class="unChangedFontSize" >评审项目:</td>
	             <td > <SELECT id="projCodeSel" name="projCodeSel"  style= "width:300px ">
	                 <%
	                   List projList=getOpenedProjList();
	                   for(int i=0;i<projList.size();i++){
	                   	 Object[] cols=(Object[])projList.get(i);
	                 %>
	                   <OPTION VALUE="<%=(String)cols[0]%>" ><%=(String)cols[1]%></OPTION> 
	                <%}%>
	                 </SELECT>
	             <input id="projCode" type="hidden" name="projCode" value="" />
	            </td>
	          </tr>
	      </table>
	     </td>
	   </tr>
	   <tr>
	    <td width="600" align="center" colspan="3">
	       <table  border="0"  cellpadding="0" cellspacing="0">
	          <tr>
	           <td class="unChangedFontSize" width=40></td>
	           <td align="right" height="40" ><img src="/style/img/login/ok.gif" style="cursor:hand" onClick="login(fLogin)" alt="确定"></td>
	           <td class="unChangedFontSize" width=70>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	           <td  height="40"><img src="/style/img/login/cancel.gif" style="cursor:hand" onClick="fLogin.reset();" alt="清空"></td>
	          </tr>
	       </table>
	    </td>
	  </tr>
	  	  </form>
	  </table> 	     

	</body>
</html>
