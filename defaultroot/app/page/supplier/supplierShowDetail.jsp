<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.server.zc.web.form.SupplierFormBean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<HTML>
	<HEAD>
		<title>西安市政府采购</title>
        <meta http-equiv="content-type" content="index/html; charset=gbk">
        <meta content="西安市政府采购,政府采购,采购公告,采购动态,采购知识,采购资讯,代理机构,协议定点采购,政府采购供应商" name="keywords">
        <meta name="description" content="西安市政府采购网" />
		<link  href="../../css/foot.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		    var highlightcolor='#fefbdc';//鼠标经过背景色
		    var clickcolor='#e0efff';//鼠标选中背景色

			function webaddr(webAddr){
				if(webAddr==""){
					alert("该供应商还没有主页！");
				}else{
					window.open(webAddr);
				}
			} 
			 
		</script>
        <script src="../../js/tableHightLight.js" type="text/javascript"></script>	
		<style>
			.report {border-bottom:#834929 1px dashed;height:25px;}
			.report a:link { color: #FF0000; text-decoration: underline} 
	 		.report a:visited { color: #FF0000; text-decoration: underline} 
	 		.report a:hover { color: #FF0000; text-decoration: underline} 
	 		.report a:active { color: #FF0000; text-decoration: underline}
			.bd{width:980px;padding:0px;margin:0px;}
	        .bd h1{float:left;FONT-FAMILY: "宋体";font-size:16px;text-align:center;display:inline;margin:0;padding:0;height:25px;width:100%;}
	        .bd h2{float:left;color:#834929;font-size:12px;display:inline;margin:0px;padding:0px;line-height:25px;
	            text-align:center;background:url(../../img/t1.gif) no-repeat 0% 50%;width:95px;}
	        .bd h3{float:left;color:#834929;font-size:16px;display:inline;margin:0px;padding:0px;line-height:25px;
	            text-align:center;background:url(../../img/t1.gif) no-repeat 0% 50%;width:295px;}
	        .lb {text-align:left;}
	        .lb td{FONT-FAMILY: "宋体";font-size:12px;height:25px;line-height:25px;}
	        .lbc td{FONT-FAMILY: "宋体";font-size:12px;height:25px;padding-left:15px;line-height:25px;}
	        .lb2{background:#fff;}
	        .lb3{width:100px;}
	        .lb4{background:#FBF5F8;}
	        table{ 
	        }
			.ww1{border-bottom:#DBC3C9 1px solid;border-right:#DBC3C9 1px solid;}
			.ww2{border-bottom:#DBC3C9 1px solid;}
			.ww3{border-right:#DBC3C9 1px solid;}  
			.ww4{border-right:#DBC3C9 1px solid;border-left:#DBC3C9 1px solid;border-bottom:#DBC3C9 1px solid;border-top:#DBC3C9 1px solid;}  
			.ww5{border-top:#DBC3C9 1px solid;}
			.ww6{border-right:#DBC3C9 1px solid;border-bottom:#DBC3C9 1px solid;}  
			.ww7{border-right:#DBC3C9 1px solid;border-bottom:#DBC3C9 1px solid;border-left:#DBC3C9 1px solid;}  
			.ww8{border-bottom:#DBC3C9 1px solid;border-right:#DBC3C9 1px solid;border-top:#DBC3C9 1px solid;} 
			.ww9{border-top:#DBC3C9 1px solid;border-right:#DBC3C9 1px solid;border-left:#DBC3C9 1px solid;}
			body {font-size:12px;}
			td {font-size:12px;}
			A:link {
				COLOR: #000; TEXT-DECORATION: none
			}
			A:visited {
				COLOR: #f26522; TEXT-DECORATION: none;font-weight:bold;
			}
			A:hover {
				COLOR: #f26522; TEXT-DECORATION: underline;font-weight:bold;
			}
			A:active {
				COLOR: #f26522; TEXT-DECORATION: underline;font-weight:bold;
			}
			.gyjg8{border-bottom:#e0e0e0 1px dashed}
			.normal_tabpg{
		 		width: 90px;padding-bottom: 2px;*padding-bottom: 0px!important;cursor: pointer;background-image: url( "../../img/gyjg_mx.gif" );
			}
			.select_tabpg{
				width: 90px;padding-bottom: 4px;*padding-bottom: 0px!important;cursor: pointer;background-image: url( "../../img/gyjg_m.gif" );
			}
		 	.gg{line-height: 26px;font-size: 14px;height: 26px;border-bottom:#e0e0e0 1px solid;text-align:left;}
			.gg td{line-height: 26px;font-size: 14px;height: 26px;border-bottom:#e0e0e0 1px solid;}
			.g_h{line-height: 30px;font-size: 14px;height: 30px;border:#EED97C 1px solid;background:#FFFCEB;text-align:left;}
			.g_h1{line-height: 38px;font-size: 14px;height: 38px;background:#F4F8FD;text-align:left;}
		 	.yy td{FONT-SIZE: 14px;}
		 	.footer{text-align: center;}
        </style>
	</HEAD>
<BODY  style="background: #ffffff" >
<logic:iterate id="supplier" name="SupplierFormBean" property="suList" type="com.ufgov.zc.common.zc.model.ZcEbSupplier" scope="request">   
<div align="center">
<!-- LOGO -->
<%@include file="/portal/page/logo.jsp"%> 
</div>
<div align="center">
<div class="bd" style="" align="center"> 
<br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr>
	<td width="278">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td height="212" valign="top">
		<table width="245" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		<td>
		<table width="100%" border="0"
		cellspacing="3" cellpadding="0">
		<tr>
		<td style="BORDER-BOTTOM: #707070 1px solid; BORDER-LEFT: #707070 1px solid; BORDER-TOP: #707070 1px solid; BORDER-RIGHT: #707070 1px solid" 						rowSpan=2 align=right><img
		src="/pub_res/" onerror="this.src='../../img/no_pic.gif'" width="245" height="180" alt="供应商图片">
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
	<td width="400" valign="top">
		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		<td height="30" class="gyjg2"><strong></strong></td>
		</tr>
		<tr>
		<td height="27" class="gyjg8">
			<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
          	<tr>
            <td width="7"><img src="../../img/gyjg_tub.gif" width="8" height="8" hspace="8" /></td>
            <td class="gy_hs" align="left"><font color="#C4722C">供应商名称：</font><bean:write name="supplier" property="name" /></td>
          	</tr>
        	</table>
        </td>
		</tr>
		<tr>
		<td height="27" class="gyjg8">
			<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<td width="7"><img src="../../img/gyjg_tub.gif" width="8" height="8" hspace="8"></td>
			<td class="gy_hs" align="left"><font color="#C4722C">联系人：</font><bean:write name="supplier" property="linkMan" /></td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td height="27" class="gyjg8">
			<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<td width="7"><img src="../../img/gyjg_tub.gif" width="8" height="8" hspace="8"></td>
			<td class="gy_hs" align="left"><font color="#C4722C">联系电话：</font><bean:write name="supplier" property="linkManPhone" /></td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td height="27" class="gyjg8">
			<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<td width="7"><img src="../../img/gyjg_tub.gif" width="8" height="8" hspace="8"></td>
			<td class="gy_hs" align="left"><font color="#C4722C">传真电话：</font><bean:write name="supplier" property="fax" /></td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td height="27" class="gyjg8">
			<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			<td width="7"><img src="../../img/gyjg_tub.gif" width="8" height="8" hspace="8"></td>
			<td class="gy_hs" align="left"><font color="#C4722C">营业地址：</font><bean:write name="supplier" property="address" /></td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td height="27" class="gyjg8">
				<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="7"></td>
					<td><a href="javascript:webaddr('<bean:write name="supplier" property="url" />')">
						<IMG src="../../img/gyjg_buy2.gif"
						width="100" height="25" border="0"></a>
					</td>			
				</tr>
				</table>
			</td>
		</tr>
		<tr>
		<td height="27">
			<table width="90%" border="0" align="left" cellpadding="0" cellspacing="0">
			<tr align="center"></tr>
			</table>
		</td>
		</tr>
		</table>
	</td>
	<td width="298">
		<table style="text-align:left;padding-left:30px;" width="100%" border="0" cellspacing="0" cellpadding="0" background="../../img/gyjg_bj.gif">
		<tr>
			<td height="147" valign="bottom">
				<table width="80%" border="0" align="right"
				cellpadding="0" cellspacing="0" class="yy">
				<tr>
				<td><strong><font color="#C4722C" style="font-weight:bold">供应商诚信指数</font></strong></td>
				</tr>
				<tr>
				<td height="45">
				
					<IMG border=0 src="../../img/gyjg_xxhei.gif">
				
					<IMG border=0 src="../../img/gyjg_xxhei.gif">
				
					<IMG border=0 src="../../img/gyjg_xxhei.gif">
				
					<IMG border=0 src="../../img/gyjg_xxhei.gif">
				
					<IMG border=0 src="../../img/gyjg_xxhei.gif">
				
				</td>
				</tr>
				<tr>
				<td height="45">
				<strong>
					<font color="#C4722C" style="font-weight:bold">供应商诚信分值：</font>
					<font color="#F7941D" style="font-weight:bold">50.0</font>
				</strong>
				</td>
				</tr>
				<tr>
				<td height="20">
				</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<h2>基本信息</h2>
<table class="lb"  width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8" bizobj="com.zrar.zjgp.sup.bo.GpSupplierBO" id="gpSupplierBO" align="center" >
  <tr>
    <td width="18%" rowspan="2" align="center">供应商名称<br/>（全称）</td>
    <td width="32%" height="40" align="left" class="lb2" rowspan="2">&nbsp;<bean:write name="supplier" property="name" /></td>
    <td width="18%" align="center">成立时间</td>
    <td width="32%" align="left" class="lb2">&nbsp;<bean:write name="supplier" property="establishDate" format="yyyy-MM-dd"/></td>
  </tr>
  <tr >
    <td align="center">组织机构代码</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="name" /></td>
  </tr>
  <tr >
    <td height="40" align="center">营业地址</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="address" /></td>
    <td align="center">邮政编码</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="zipCode" /></td>
  </tr>
  <tr >
    <td height="40" align="center">网    址</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="url" /></td>
    <td align="center">电子邮箱</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="email" /></td>
  </tr>
  <tr >
    <td height="40" align="center">联系电话</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="linkManPhone" /></td>
    <td align="center">传真电话</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="fax" /></td>
  </tr>
  <tr >
    <td height="40" align="center">是否协议供应商</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="isXysu" /></td>
    <td align="center">是否中小企业</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="isZxqy" /></td>
  </tr>
  <tr >
    <td height="40" align="center">供应商类别</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="zcSupplierType" /></td>
    <td align="center">供应商性质</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="zcSupplierKind" /></td>
  </tr>
  <tr>
    <td height="40" align="center">开户银行</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="bankName" /></td>
    <td align="center">银行账号</td>
    <td align="left" class="lb2">&nbsp;<bean:write name="supplier" property="accCode" /></td>
  </tr>
  <tr>
    <td colspan="4">
    	<table class="lb" width="100%" border="0" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8">
	      <tr>
	        <td width="8%" class="ww3" align="center" style="layout-flow: vertical-ideographic"><br/>供应商基本情况简介<br/></td>
	        <td width="92%" style="background:#fff;">&nbsp;<bean:write name="supplier" property="description" /></td>
	      </tr>
	    </table>
	</td>
  </tr>
</table>
<h2>企业信息</h2>
<table class="lb" width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8" align="center" >
  	<tr>
	    <td align="center" style="layout-flow: vertical-ideographic">营业执照或事业法人证</td>
	    <td width="92%" height="210">
	    <table class="lb" width="100%" border="0" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8" >
		    <tr>
	        	<td height="100%">
		        	<table width="100%" height="100%"  border="0" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8" align="center">
				          <tr>
				            <td width="14%" align="center" height="40" class="ww1">营业执照编号</td>
				            <td width="35%" align="left" class="ww1" style="background:#fff;">&nbsp;<bean:write name="supplier" property="licenseId" /></td>
				            <td width="14%" align="center" class="ww1">注册资本</td>
				            <td width="36%" align="left" colspan="2" class="ww2" style="background:#fff;">&nbsp;<bean:write name="supplier" property="regCapital" />（万元）</td>
				          </tr>
				          <tr>
				            <td align="center" height="40" class="ww1">有效期开始时间</td>
				            <td align="left"  class="ww1" style="background:#fff;">&nbsp;<bean:write name="supplier" property="licenseTimeStart" format="yyyy-MM-dd"/></td>
				            <td align="center" class="ww1">有效期结束时间</td>
				            <td align="left" colspan="2" class="ww2" style="background:#fff;">&nbsp;<bean:write name="supplier" property="licenseTimeEnd" format="yyyy-MM-dd"/></td>
				          </tr>
				          <tr>
				            <td width="14%" height="80" class="ww1"><p align="center">经营范围<br/>（主营）</p></td>
				            <td width="56%" colspan="2" class="ww1" style="background:#fff;">&nbsp;<bean:write name="supplier" property="mainBusinesses" />（以营业执照为准）</td>
				            <td width="9%"  align="center" class="ww3">对应<br/>政府<br/>采购<br/>目录</td>
				            <td width="20%" style="background:#fff;">
				               <logic:iterate id="zmlist" name="SupplierFormBean" property="zyxmList" type="com.ufgov.zc.common.zc.model.ZcEbZyxm" scope="request">
				                  &nbsp;<bean:write name="zmlist" property="zcCatalogueName" /><br>
				               </logic:iterate>
				                                        （以营业执照为准）
				            </td>
				          </tr>
			        </table>
				</td>
			</tr>
		</table>
    	</td>
	</tr>
	<tr>
    	<td align="center" class="ww6" style="layout-flow: vertical-ideographic">税务登记证</td>
    	<td >
	    	<table class="lb" width="100%" height="80" border="0" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8">
	      		<tr>
		        	<td width="15%" rowspan="2" align="center" class="ww1">登记证号</td>
		        	<td width="38%" height="40" class="ww1" style="background:#fff;">
		        	   &nbsp;<bean:write name="supplier" property="stateTaxRegId" />（国税）
			        </td>
			        <td width="15%" rowspan="2" align="center" class="ww1">登记时间</td>
			        <td colspan="2" class="ww2" style="background:#fff;">&nbsp;<bean:write name="supplier" property="stateTaxRegDate" format="yyyy-MM-dd"/></td>
	        	</tr>
	      		<tr>
			        <td height="40" class="ww1" style="background:#fff;">
			           &nbsp;<bean:write name="supplier" property="localTaxRegId" />（地税）
				    </td>
			        <td colspan="2" class="ww2" style="background:#fff;">&nbsp;<bean:write name="supplier" property="localTaxRegDate" format="yyyy-MM-dd"/></td>
	      		</tr>
	    	</table>
    	</td>
  	</tr>
</table>
<h2>法人信息</h2>
<table class="lb" onMouseover="changeto()" onMouseout="changeback()" width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF" bgcolor="#FBF5F8" bizobj="com.zrar.zjgp.sup.bo.GpSupLegalRepBO" id="repBO">
  <tr> 
      <td width="15%" align="center">法人代表</td>
      <td width="35%" class="lb2">&nbsp;<bean:write name="supplier" property="legalPerson" /></td>
      <td width="15%" align="center">联系地址</td>
      <td width="35%" class="lb2">&nbsp;<bean:write name="supplier" property="legalPersonAddr" /></td>
  </tr>
  <tr> 
      <td  align="center">联系电话</td>
      <td class="lb2">&nbsp;<bean:write name="supplier" property="legalPersonTel" /></td>
      <td align="center">手机号码</td>
      <td class="lb2">&nbsp;<bean:write name="supplier" property="legalPersonMobile" /></td>
  </tr>
  <tr> 
	  <td  align="center">传    真</td>
      <td class="lb2">&nbsp;<bean:write name="supplier" property="legalPersonFax" /></td>
      <td align="center">证件：<bean:write name="supplier" property="legalCardType" /></td>
      <td class="lb2">&nbsp;<bean:write name="supplier" property="legalPersonIDCard" /></td>
  </tr>
</table>
<h2>相关资质</h2>
  <table class="lbc" onMouseover="changeto()" onMouseout="changeback()" width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#DBC3C9" bordercolor="#FFFFFF">
    <tr class="lb4" id="nc"> 
      <td width="20%">资质名称</td>
      <td width="15%">证书编号</td>
      <td width="10%">有效开始日期</td>
      <td width="10%">有效结束日期</td>
      <td width="20%">发证机构名称</td>
      <td width="9%">备注</td>
    </tr>
<logic:iterate id="qualifyList" name="SupplierFormBean" property="qualifyList" type="com.ufgov.zc.common.zc.model.ZcEbSupplierQualify" scope="request">   
    <tr> 
      <td align="left"><bean:write name="qualifyList" property="licenseName" />&nbsp;</td>
      <td><bean:write name="qualifyList" property="licenseNO" />&nbsp;</td>
      <td><bean:write name="qualifyList" property="effectStartTime" format="yyyy-MM-dd"/>&nbsp;</td>
      <td><bean:write name="qualifyList" property="effectEndTime" format="yyyy-MM-dd"/>&nbsp;</td>
      <td><bean:write name="qualifyList" property="licenseIssuingAuthority" /></td>
      <td><bean:write name="qualifyList" property="remark" /> &nbsp;</td>
    </tr>
</logic:iterate>       
 </table>
</div>
<!--页面底部-->
<!-- 页尾 -->
<%@include file="/portal/page/footer.jsp"%>
</div>
</logic:iterate>
<div style="clear:both"></div>
<br>
<iframe id="c_iframe"  src="" style="display:none"></iframe> 
</body>
