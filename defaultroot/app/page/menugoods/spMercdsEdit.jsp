<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcBMerchandise"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcBMerPrice"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcBMerDiscount"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link href="../../css/merchandise.css" type="text/css" rel="stylesheet">
<script src="../../js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="../../js/jquery.validate.id.js" type="text/javascript"></script>
<script src="../../js/message_cn.js" type="text/javascript"></script>
<script src="../../js/myValidator.js" type="text/javascript"></script>
<script>
$(function(){
	$(".calcuPrice").click(function(){
		$.calcuPrice();
	});
})
</script>
</head>
<body><br>

<html:form action="/portal/page/menugoods/spMercdEditSave.do" styleClass="merchandiseForm"  method="post" >
	<table class="show-table">
					<tbody>
						<tr>
							<th colspan="4">商品信息</th>
						</tr>
						<tr>
						<% ZcBMerchandise zcBMerchandise = (ZcBMerchandise)request.getAttribute("ZcBMerchandise");%>
						
						<% if (zcBMerchandise.getZcMerCode()!= null){ %>
							<td >zcMerCode</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerCode"/>
							<td><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcMerCode</td>
							<td></td>
						<%} %>
						
						<% if (zcBMerchandise.getZcMerName()!= null){ %>
							<td >zcMerName</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerName"/>
							<td><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcMerName</td>
							<td></td>
						<%} %>
						</tr>
						<tr>
						<% if (zcBMerchandise.getZcMerCollocate()!= null){ %>
							<td >zcMerCollocate</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerCollocate"/>
							<td><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcMerCollocate</td>
							<td></td>
						<%} %>
						<% if (zcBMerchandise.getZcMerSpec()!= null){ %>
							<td >zcMerSpec</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerSpec"/>
							<td><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcMerSpec</td>
							<td></td>
						<%} %>
						</tr>
						<tr>
						<% if (zcBMerchandise.getZcCatalogueCode()!= null){ %>
							<td >zcCatalogueCode</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcCatalogueCode"/>
							<td colspan="3"><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcCatalogueCode</td>
							<td colspan="3"></td>
						<%} %>
						</tr>
						<tr>
						<% if (zcBMerchandise.getZcBraCode()!= null){ %>
							<td >zcBraCode</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcBraCode"/>
							<td ><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcBraCode</td>
							<td ></td>	
						<%} %>
						<% if (zcBMerchandise.getZcBraName()!= null){ %>
							<td >zcBraName</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcBraName"/>
							<td ><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcBraName</td>
							<td ></td>
						<%} %>
						</tr>
                       
						 <tr>
						<% if (zcBMerchandise.getZcMerMPrice()!= null){ %>
							<td >市场价格</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerMPrice"/>
							<td ><input type="text" class="zcMerMPrice" id="zcMerMPrice" value="<%=beanWriteValue.toString() %>"></input></td>
						<% } else{%>
							<td >市场价格</td>
							<td class="zcMerMPrice"><input type="text" class="zcMerMPrice" id="zcMerMPrice" value="0" ></input></td>
						<%} %>
						<% if (zcBMerchandise.getZcMerTax()!= null){ %>
							<td >zcMerTax</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerTax"/>
							<td ><%=beanWriteValue.toString() %></td>
						<% } else{%>
							<td >zcMerTax</td>
							<td ></td>
						<%} %>	
						</tr>
						<%--<tr>
							<% if (zcBMerchandise.getZcMerPicBlobid()!= null){ %>
							<td >ZC_MER_PIC_BLOBID</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerPicBlobid"/>
							<td ><%=beanWriteValue.toString() %></td>
							<% } else{%>
							<td >ZC_MER_PIC_BLOBID</td>
							<td ></td>
							<%} %>
							<% if (zcBMerchandise.getZcRemark()!= null){ %>
							<td >ZC_REMARK</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcRemark"/>
							<td ><%=beanWriteValue.toString() %></td>
							<% } else{%>
							<td >ZC_REMARK</td>
							<td ></td>
							<%} %>
						</tr>
						<tr>
						<% if (zcBMerchandise.getZcMerIsLshb()!= null){ %>
							<td >ZC_MER_IS_LSHB</td><bean:define id="beanWriteValue" name="ZcBMerchandise" property="zcMerIsLshb"/>
							<td colspan="3"><%=beanWriteValue.toString() %></td>
						<% } else{%>	
							<td >ZC_MER_IS_LSHB</td>
							<td colspan="3"></td>
						<%} %>
						</tr>
					--%></tbody>
				</table>
				
				<div style="width:100%;height:20px;"></div>
				<table class="show-table">
					
					<tbody>
						<tr>
							<th colspan="4">价格信息</th>
						</tr>
						<% ZcBMerPrice zcBMerPrice = (ZcBMerPrice)request.getAttribute("ZcBMerPrice");%>
						
						<% if (zcBMerPrice.getZcMerCode()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerPrice" property="ZC_MER_CODE"/>
							<html:hidden property="zcBMerPrice.ZC_MER_CODE"  styleId="ZC_MER_CG_PRICE" value='<%=beanWriteValue.toString() %>' /></td>
						<% } else{%>	
							
							<html:hidden property="zcBMerPrice.ZC_MER_CODE"  value='' />
						<%} %>	
						<% if (zcBMerPrice.getZcBraCode()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerPrice" property="ZC_BRA_CODE"/>
							<html:hidden property="zcBMerPrice.ZC_BRA_CODE"  value='<%=beanWriteValue.toString() %>' />
						<% } else{%>
							
							<html:hidden property="zcBMerPrice.ZC_BRA_CODE"   value='' />	
						<%} %>	
						<tr>
						<% if (zcBMerPrice.getZcProjID()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerPrice" property="ZC_PROJ_ID"/>
							<html:hidden property="zcBMerPrice.ZC_PROJ_ID"  value='<%=beanWriteValue.toString() %>' />
						<% } else{%>
							
							<html:hidden property="zcBMerPrice.ZC_PROJ_ID"  value='' />
						<%} %>	
						<% if (zcBMerPrice.getZcSuCode()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerPrice" property="ZC_SU_CODE"/>
							<html:hidden property="zcBMerPrice.ZC_SU_CODE" readonly="true" value='<%=beanWriteValue.toString() %>' />
						<% } else{%>	
							
							<html:hidden property="zcBMerPrice.ZC_SU_CODE" readonly="true" value='' />
						<%} %>
						
						<tr>
						<input type="hidden" id="ZC_MER_CG_PRICE" name="ZC_MER_CG_PRICE" value='<%=request.getAttribute("ZC_MER_CG_PRICE").toString() %>'/>
						
						<% if (zcBMerPrice.getZcMerCGPrice()!= null){ %>
							<td>协议采购价</td><bean:define id="beanWriteValue" name="ZcBMerPrice" property="ZC_MER_CG_PRICE"/>
							<td colspan="3">
								<html:text property="zcBMerPrice.ZC_MER_CG_PRICE" styleId="zcmerprice" readonly="true" value='<%=beanWriteValue.toString() %>' />
							<a href="javascript:void(0)" class="calcuPrice">重新计算</a>
							</td>
						<%}else{ %>
							<td>协议采购价</td>
							<td colspan="3"><html:text property="zcBMerPrice.ZC_MER_CG_PRICE" readonly="true" styleId="zcmerprice" value='' />
							<a href="javascript:void(0)" class="calcuPrice">重新计算</a>
							</td>
						<%} %>
						</tr>
					
						<tr>
							<th colspan="4">商品录入-discount</th>
						</tr>
						<tr>
						<% ZcBMerDiscount zcBMerDiscount = (ZcBMerDiscount)request.getAttribute("ZcBMerDiscount");%>
						<% if (zcBMerDiscount.getZcMerCode()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_MER_CODE"/>
							<html:hidden property="zcBMerDiscount.ZC_MER_CODE" readonly="true" value='<%=beanWriteValue.toString() %>' />
						<%}else{ %>
							<bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_MER_CODE"/>
							<html:hidden property="zcBMerDiscount.ZC_MER_CODE" readonly="true" value='<%=beanWriteValue.toString() %>' />
						<%} %>
						
						<% if (zcBMerDiscount.getZcSuCode()!= null){ %>
							<bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_SU_CODE"/>
							<html:hidden property="zcBMerDiscount.ZC_SU_CODE" readonly="true" value='<%=beanWriteValue.toString() %>' />
						<%}else{ %>		
							<html:hidden property="zcBMerDiscount.ZC_SU_CODE" readonly="true" value='' />
						<%} %>
							
						</tr>
						<tr>
						<% if (zcBMerDiscount.getZcTreatyLowerLimit()!= null){ %>
							<td >协议数量下限</td><bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_TREATY_LOWER_LIMIT"/>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_LOWER_LIMIT" styleId="lowerlimit" value='<%=beanWriteValue.toString() %>' /></td>
						<%}else{ %>
							<td >协议数量下限</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_LOWER_LIMIT" styleId="lowerlimit" value='0' /></td>
						<%} %>	
						<% if (zcBMerDiscount.getZcTreatyUpperLimit()!= null){ %>
							<td >协议数量上限</td><bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_TREATY_UPPER_LIMIT"/>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_UPPER_LIMIT" styleId="upperlimit" value='<%=beanWriteValue.toString() %>' /></td>
						<%}else{ %>
							<td >协议数量上限</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_UPPER_LIMIT" styleId="upperlimit" value='0' /></td>
						<%} %>
						</tr>
						
						<tr>
						<% if (zcBMerDiscount.getZcTreatyDiscountRate()!= null){ %>
							<td >协议折扣率</td><bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_TREATY_DISCOUNT_RATE"/>
							<td ><html:text property="zcBMerDiscount.ZC_TREATY_DISCOUNT_RATE" styleClass="disRate" styleId="disRate" value='<%=beanWriteValue.toString() %>' /></td>
						<%}else{ %>	
							<td >协议折扣率</td>
							<td ><html:text property="zcBMerDiscount.ZC_TREATY_DISCOUNT_RATE" styleClass="disRate"  styleId="disRate" value="0" /></td>
						<%} %>	
						<% if (zcBMerDiscount.getZcTreatyMemo()!= null){ %>
							<td >备注</td><bean:define id="beanWriteValue" name="ZcBMerDiscount" property="ZC_TREATY_MEMO"/>
							<td>&nbsp;<html:textarea property="zcBMerDiscount.ZC_TREATY_MEMO" value='<%=beanWriteValue.toString() %>' /></td>
						<%}else{ %>	
							<td >备注</td>
							<td><html:textarea property="zcBMerDiscount.ZC_TREATY_MEMO" value='' /><br></td>
						<%} %>	
						</tr>
						<tr>
						
							<td colspan="4" align="center"><input type="submit" value="commit"/></td>
						</tr>
	</tbody>
				</table>
				
				
</html:form>

</body>
</html>
