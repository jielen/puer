<%@page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.ZcEbYanShouBillPrintForm"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html:html lang="true">
<head>
<title>验收单</title>
<meta http-equiv="content-type" content="index/html; charset=gbk" />
<link href="../../css/stylesheet.css" type="text/css" rel="Stylesheet" />
<style type="text/css">
table {border:1px; }
td{border:"1px solid #000000"}
</style>
</head>
<body>
<P class=MsoNormal align=left style='text-align: left;'><u>政府采购项目</u>
</P>
<table cellpadding=0 cellspacing=0>
 <caption style='text-align: center >
 <b style='mso-bidi-font-weight: normal'>
 <span style='font-size: 22.0pt; font-family: 宋体; mso-ascii-font-family: Calibri; mso-ascii-theme-font: minor-latin; mso-fareast-font-family: 宋体; mso-fareast-theme-font: minor-fareast; mso-hansi-font-family: Calibri; mso-hansi-theme-font: minor-latin'>
				验    收    单<br><span style='font-size: 14.0pt; font-family: 宋体'>(货物类、服务类50万元以上、工程类100万元以上）</span></br></span></b></caption>
				
 <tr>
 <td colspan=8  s style='text-align: right;border:0px;' >金额单位：元
</td>
 </tr>
  <tr height=38 style='height:28.5pt'>
  <td height=38 width=140  colspan=1 style='height:28.5pt;border-bottom:0px;'>项目名称</td>
  <td height=38 width=250  colspan=4 style='height:28.5pt;border-bottom:0px;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.projName" />&nbsp</td>
  <td height=38 width=100  colspan=1 style='height:28.5pt;border-bottom:0px;border-left:0px;'>验收日期</td>
  <td height=38 width=200  colspan=2 style='height:28.5pt;border-bottom:0px;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.billYanShouDate" format="yyyy年MM月dd日"/>&nbsp</td>
 </tr>
 <tr>
  <td height=38  colspan=1 style='height:28.5pt;border-bottom:0px'>采购单位</td>
  <td height=38  colspan=4 style='height:28.5pt;border-bottom:0px;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcCoName" />&nbsp</td>
  <td height=38   colspan=1 style='height:28.5pt;border-bottom:0px;border-left:0px;'>联系人</td>
  <td height=38   colspan=2 style='height:28.5pt;border-bottom:0px;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcMakeLinkMan"/>&nbsp</td>
 </tr>
  <tr>
  <td height=38   colspan=1 style='height:28.5pt;border-bottom:0px'>供应商</td>
  <td height=38   colspan=4 style='height:28.5pt;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcSuName" />&nbsp</td>
  <td height=38   colspan=1 style='height:28.5pt;border-left:0px;'>负责人</td>
  <td height=38  colspan=2 style='height:28.5pt;border-left:0px;'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcSuLinkMan"/>&nbsp</td>
 </tr>
 <tr>
  <td height=90  colspan=1 style='height:100pt;'>供应商自检结论</td>
  <td height=90  colspan=4 style='height:100pt;border-right:0px;border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcSuOpinion"/></td>
   <td height=90  colspan=3 style='height:100pt;border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.providerFillDate" format="yyyy年MM月dd日"/>&nbsp</td>
 </tr>
  <tr>
  <td height=90   colspan=1 style='height:100pt;border-top:0px'>采购人验收意见</td>
  <td height=90   colspan=4 style='height:100pt;border-right:0px;border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcCoOpinion" /></td>
   <td height=38   colspan=3 style='border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.caigoRenFillDate" format="yyyy年MM月dd日"/>&nbsp</td>
 </tr>
  <tr>
  <td height=90  colspan=1 style='height:100pt;border-top:0px'>验收小组成员意见</td>
  <td height=90  colspan=4 style='height:100pt;border-right:0px;border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcYanShouTeamOpinion" /></td>
   <td height=38   colspan=3 valign=bottom style='border-left:0px;border-top:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.yanShouTeamFillDate" format="yyyy年MM月dd日"/>&nbsp</td>
 </tr>
  <tr>
  <td height=90   colspan=1 style='height:100pt;border-top:0px;border-bottom:0px'>组织验收单位意见</td>
 <td height=90  colspan=4   style='height:100pt;border:0px'>
 <bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zcOrganizerOpinion"/></td>
   <td height=38   colspan=3  valign="bottom"  style='height:100pt;border-left:0px;border-top:0px;border-bottom:0px'><bean:write name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.zuzhiDanweiFillDate" format="yyyy年MM月dd日"/>
   &nbsp</td>
 </tr>
<tr>
  <td height=38   rowspan=2 colspan=1 >产品名称</td>
  <td rowspan=2 colspan=1 style='border-left:0px;' ><center>规格型号及配置</center></td>
  <td rowspan=2 colspan=1 style='border-left:0px;'><center>合同金额</center></td>
  <td rowspan=2 colspan=1 style='border-left:0px;'><center>合同数量</center></td>
  <td rowspan=2 colspan=1 style='border-left:0px;'><center>实收数量</center></td>
  <td rowspan=1 colspan=3 style='border-left:0px;'><center>与合同偏差</center></td>
</tr>
  <tr>
	<td style='border-left:0px;border-top:0px' ><center >数量</center></td>
	<td style='border-left:0px;border-top:0px' colspan=2 ><center>金额</center></td>
  </tr>
  <logic:iterate id="billItem" name="zcEbYanShouBillPrintForm" property="zbEbYanshouBill.itemList" type="com.ufgov.zc.common.zc.model.ZcEbYanShouBillItem" scope="request" indexId="indexid">   	
			<tr>
			    <td>
			    	<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p>
							<bean:write name="billItem" property="productName" /></o:p>
						</span>
					</p>
			    </td>
				<td width=132 valign=top>
					<p class=MsoNormal align=right style='text-align: right'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="productParam" /></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=113 valign=top>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="htAmount" format="#.00"/></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=76 valign=top>
					<p class=MsoNormal align=right style='text-align: right'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="htNum"/></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=88 valign=top>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="shiShouNum" /></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=88 valign=top>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="pianChaNum" /></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=88 valign=top>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="pianChaAmount" /></o:p>&nbsp
						</span>
					</p>
				</td>
				<td width=88 valign=top>
					<p class=MsoNormal align=center style='text-align: center'>
						<span lang=EN-US
							style='mso-bidi-font-size: 10.5pt; font-family: 仿宋_GB2312'><o:p><bean:write name="billItem" property="pianChaAmount" /></o:p>&nbsp
						</span>
					</p>
				</td>
			</tr>
</logic:iterate>
 <tr>
 <td colspan=8  style='text-align: left;border:0px;' >1、本单一式五份、采购人三份（财政审核结算二份）、供应商一份、采购中心一份。
</td>
 </tr>
  <tr>
 <td colspan=8  style='text-align: left;border:0px;'>2、本验收单以采购人、供应商验收为主，根据验收情况如实填写、签字、盖章确认。
</td>
 </tr>
  <tr>
 <td colspan=8  style='text-align: left;border:0px;'>3、采购中心作为组织方督促协调。
</td>
 </tr>
  <tr>
 <td colspan=8  style='text-align: left;border:0px;'  >4、本表不够可复制。
</b>
</td>
 </tr>
</table>
</body>
</html:html>