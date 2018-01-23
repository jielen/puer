<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SupplierFormBean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" type="text/css" href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %>/portal/skin/css/style.css" />
<%String spDetailsUrl = request.getContextPath()+"/portal/page/supplier/supplierDetails.do";%>
<script type="text/javascript">
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=i==cursel?"TabBlueClicked2":"TabBlueClicked";
		con.style.display=i==cursel?"block":"none";
	}
}
function showDetails(id){
	  window.open("<%=spDetailsUrl%>?zcSuCode="+id+"");
}
</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="5" colspan="2"></td>
      </tr>
      <tr>
        <td width="30%" valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="titleBLue">
                <tr>
                  <td><img src="../../img/blueTitleR.jpg" width="7" height="27" alt=""></td>
                  <td width="100%" background="../../img/blueTitleC.jpg">注册供应商</td>
                  <td><img src="../../img/blueTitleL.gif" width="6" height="27" alt=""></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableBlue">
                  <tr>
                    <td width="43%" height="2" valign="top"></td>
                    <td width="57%" height="150" rowspan="2" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="font01">
                      <tr>
                        <td width="24%">&nbsp;</td>
                        <td width="76%"> 注册供应商：<span class="STYLE1"><bean:write name="SupplierFormBean" property="countTotalNum" /></span>家</td>
                      </tr>
                      <tr>
                        <td>其中：</td>
                        <td>已审批：<span class="STYLE1"><bean:write name="SupplierFormBean" property="countAuditNum" /></span>家</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td> 未审批：<span class="STYLE1"><bean:write name="SupplierFormBean" property="countNotAuditNum" /></span>家</td>
                      </tr>
					  <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><a class="font01" href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do" target="_blank">我要查询</a>　    <a class="font01" href="<%=request.getContextPath() %>/portal/page/supplier/regSupplier.do" target="_blank">我要申请</a></td>
                      </tr>
                    </table></td>
                      <tr>
                        <td height="75" valign="top"><img src="../../img/pic01.jpg" width="113" height="130" alt=""></td>
                      </tr>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="100%" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="../../img/blueTitleR.jpg" width="7" height="27" alt=""></td>
                  <td width="100%" background="../../img/blueTitleC.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    	<td width="85" class="TabBlue">注册供应商</td>
                      		<td id="51"  class="TabBlueClicked2"  style="cursor:hand;"><span onclick="setTab('5',1,2);">已审核</span></td>
                      		<td id="52"  class="TabBlueClicked"  style="cursor:hand;"><span onclick="setTab('5',2,2);">未审核</span></td>
                      	<td >&nbsp;</td>
                      	<td width="82">
                      		<div align="right"   class="font_blue">
                      			<a href="<%=request.getContextPath() %>/portal/page/supplier/supplierList.do" target="_blank" >更多＋ </a>
                      		</div>
                      	</td>
                    </tr>
                    <tr>
                      <td height="3" colspan="7" background="../../img/titleBgLine.gif"></td>
                    </tr>
                  </table></td>
                  <td><img src="../../img/blueTitleL.gif" width="6" height="27" alt=""></td>
                </tr>
            </table></td>
          </tr>
          <tr>
          <td valign="top">
              <table id="con_5_1" width="100%" border="0" cellpadding="0" cellspacing="0" class="tableBlue" style="display:">
                <tr>
                  <td height="150" valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="font01">
					   <logic:iterate id="supplier" name="SupplierFormBean" property="auditedList" type="com.ufgov.zc.common.zc.model.ZcEbSupplier" scope="request">   
					      <tr>
	                      	<td width="16%" height="22">
	                      	    <a href="javascript:void(0)" class="font01" onclick="showDetails('<bean:write name="supplier" property="code" />');">·<bean:write name="supplier" property="name" /></a>
	                      	</td>
	                      </tr>
                       </logic:iterate>
                  </table></td>
                </tr>
              </table>
              <table id="con_5_2" width="100%" border="0" cellpadding="0" cellspacing="0" class="tableBlue" style="display:none">
                <tr>
                  <td height="150" valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="font01">
					   <logic:iterate id="supplier" name="SupplierFormBean" property="notAuditedList" type="com.ufgov.zc.common.zc.model.ZcEbSupplier" scope="request">   
					      <tr>
	                      	<td width="16%" height="22">
	                      	   <a href="javascript:void(0)" class="font01" target="_blank" onclick="showDetails('<bean:write name="supplier" property="code" />');">·<bean:write name="supplier" property="name" /></a>
	                      	</td>
	                      </tr>
                       </logic:iterate>
                  </table></td>
                </tr>
              </table>
            </td>
          </tr>
        </table></td>
      </tr>