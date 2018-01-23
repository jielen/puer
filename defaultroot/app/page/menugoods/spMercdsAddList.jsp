<%@page contentType="text/html; charset=GBK" %>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link href="../../css/merchandise.css" type="text/css" rel="stylesheet">
</head>
<body>
 
  
<html:form action="/portal/page/menugoods/spMercdAddSave.do"  method="post" >
	<table class="show-table">
					<col width="170">
					<col width="200">
					<col width="125">
                    <col width="240">
					<tbody>
						<tr>
							<th colspan="4">商品录入-商品信息</th>
						</tr>
						<tr>
							<td >zcMerCode</td>
							<td><html:text property="zcBMerchandise.zcMerCode"  /></td>
							<td >zcMerName</td>
							<td><html:text property="zcBMerchandise.zcMerName" /></td>
								
							
						</tr>
						<tr>
							<td >zcMerCollocate</td>
							<td><html:text property="zcBMerchandise.zcMerCollocate"  /></td>
							<td >zcMerSpec</td>
							<td><html:text property="zcBMerchandise.zcMerSpec"  /></td>
						</tr>
						<tr>
							<td >zcCatalogueCode</td>
							<td colspan="3"><html:text property="zcBMerchandise.zcCatalogueCode"  /></td>
						</tr>
						<tr>
							<td >zcBraCode</td>
							<td ><html:text property="zcBMerchandise.zcBraCode"  /></td>
							<td >zcBraName</td>
							<td ><html:text property="zcBMerchandise.zcBraName"  /></td>
						</tr>
                        <tr>
							<td >zcMerMPrice</td>
							<td ><html:text property="zcBMerchandise.zcMerMPrice" /></td>
							<td >zcMerTax</td>
							<td ><html:text property="zcBMerchandise.zcMerTax" /></td>
						</tr>
						<tr>
							
							<td >ZC_MER_PIC_BLOBID</td>
							<td ><html:text property="zcBMerchandise.zcMerPicBlobid" /></td>
							<td >ZC_REMARK</td>
							<td ><html:text property="zcBMerchandise.zcRemark"  /></td>
							
						</tr>
						<tr>
							<td >ZC_MER_IS_LSHB</td>
							<td colspan="3"><html:text property="zcBMerchandise.zcMerIsLshb"  /></td>
						</tr>
	</tbody>
				</table>
				
				<div style="width:100%;height:20px;"></div>
				<table class="show-table">
					<col width="170">
					<col width="200">
					<col width="125">
                    <col width="240">
					<tbody>
						<tr>
							<th colspan="4">商品录入-价格信息</th>
						</tr>
						<tr>
							<td >ZC_MER_CODE(同一个商品，实际不用再次录入)</td>
							<td><html:text property="zcBMerPrice.ZC_MER_CODE"  /></td>
							
							<td >ZC_BRA_CODE</td>
							<td><html:text property="zcBMerPrice.ZC_BRA_CODE"  /></td>
								
							
						</tr>
						<tr>
							<td >ZC_PROJ_ID</td>
							<td><html:text property="zcBMerPrice.ZC_PROJ_ID"  /></td>
							<td >ZC_SU_CODE（当前供应商CODE）</td>
							<td><html:text property="zcBMerPrice.ZC_SU_CODE"  /></td>
						</tr>
						<tr>
							<td>ZC_MER_CG_PRICE(价格)</td>
							<td colspan="3"><html:text property="zcBMerPrice.ZC_MER_CG_PRICE" /></td>
						</tr>
	</tbody>
				</table>
				
				<div style="width:100%;height:20px;"></div>
				<table class="show-table">
					<col width="170">
					<col width="200">
					<col width="125">
                    <col width="240">
					<tbody>
						<tr>
							<th colspan="4">商品录入-discount</th>
						</tr>
						<tr>
							<td >ZC_MER_CODE(同一个商品，实际不用再次录入)</td>
							<td><html:text property="zcBMerDiscount.ZC_MER_CODE"  /></td>
							
							<td >ZC_SU_CODE（当前供应商CODE））</td>
							<td><html:text property="zcBMerDiscount.ZC_SU_CODE" /></td>
								
							
						</tr>
						<tr>
							<td >ZC_TREATY_LOWER_LIMIT</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_LOWER_LIMIT"  /></td>
							<td >ZC_TREATY_UPPER_LIMIT</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_UPPER_LIMIT"  /></td>
						</tr>
						
						<tr>
							<td >ZC_TREATY_DISCOUNT_RATE</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_DISCOUNT_RATE"  /></td>
							<td >ZC_TREATY_PRICE</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_PRICE"  /></td>
						</tr>
						<tr>
						<td >ZC_TREATY_MEMO</td>
							<td><html:text property="zcBMerDiscount.ZC_TREATY_MEMO"  /></td>
							<td colspan="2"><input type="submit" value="commit"/></td>
						</tr>
	</tbody>
				</table>
</html:form>
</body>
</html>
