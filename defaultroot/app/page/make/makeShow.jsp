<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.ufgov.zc.common.zc.model.ZcPProMakeShow"%>
<%@page import="java.util.List;"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
	<head>
		<title>电子竞价</title>
		<link  href="../../css/reset.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/foot.css" type="text/css" rel="stylesheet" />
		<link  href="../../css/style.css" type="text/css" rel="stylesheet" />
		
		<style>
			body {font-size:12px;}
			td {font-size:12px;}
            footer{text-align: center;}
        </style>
        <script src="../../js/jquery-1.4.4.min.js"></script>
       
        <script>
        	$(function(){
        		$("#makeListTable tr:even").addClass("green");
        		
				$("#makeListTable tr").each(function(){
					$(this).hover(function() { 
					   $(this).addClass("black"); 
					}, function() { 
					   $(this).removeClass("black"); 
					}); 
				});

        		
        	})
        	
        	
   function confirmBaoJia(){
       if (confirm("对于本次竞价，您已确保有现货，并且不能恶意报价，否则承担相关一切法律责任，同意请继续。")){
          window.location.href="../../../../portal/acceptXieYi.jsp";
        }else{
		  window.close();
		}	 
	 }

        </script>
        
	</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center">
<!-- LOGO -->
<%@include file="/portal/page/logo.jsp"%> 
</div>
<% ZcPProMakeShow zcPProMakeShow =( ZcPProMakeShow )request.getAttribute("zcPProMakeShow"); %>
<div id="show">
	<p>公告发布日期：<bean:write name="zcPProMakeShow" property="zcAttr5"/></p>
	<p>项目名称：<bean:write name="zcPProMakeShow" property="zcMakeName"/></p>
	<p>西安市市级单位政府采购中心现就"<bean:write name="zcPProMakeShow" property="zcMakeName"/>"邀请合格的协议供应商进行电子竞价。</p>
	<p>一、竞价标的物</p>
	<table id="show-table">
									<col width="25">
									<col width="25">
									<col width="25">
									<col width="25">
									<tbody>
									<tr>
										<th>品牌</th>
										<th>商品名称</th>
										<th>基本规格参数</th>
										<th>数量</th>
									</tr>
									<% List makeList = (List)request.getAttribute("zcPProMakeList");
										for(int i=0;i < makeList.size();i++){
										ZcPProMakeShow item = (ZcPProMakeShow)makeList.get(i);
									%>
									<tr valign="middle">
										
										<td align="center">
											<%=item.getZcBraName() %>
										</td>
										
										<td align="center">
											<%=item.getZcMakeName() %>
										</td>
										<td style="padding:0 6px 0 6px;">
											<%=item.getZcBaseGgyq() %>
										</td>
										<td align="center">
											<%=item.getZcCaigNum() %>
										</td>
									</tr>
									<% }%>
						            </tbody>
	</table>
	 
<p>二、竞价时间</p>
<p>　1.竞价开始时间：<bean:write name="zcPProMakeShow" property="zcAttr5"/></p>
<p>　2.竞价结束时间：<bean:write name="zcPProMakeShow" property="zcEndDate"/></p>
<p>三、报名条件与报名要求</p>
<p>　（一）报名条件<p>
<p>　　1.供应商须为本次竞价标的产品的陕西省西安市政府采购协议供货商；</p>
<p>　　2.供应商须为陕西省西安市政府采购网的注册供应商。注册办法请登录该网站查询，该网站客服电话：66535273、66535275，<p style="text-indent:3em">客服邮箱：<a href="mailto://cfcpn@gpcsoft.com">cfcpn@gpcsoft.com</a></p></p>
<p>　（二）报名要求</p>
<p>　　1.采购方仅接受供应商在陕西省西安市政府采购网进行的报名，全部报名信息均应在通过该网站填写。</p>
<p>　　2.供应商报名时须提交以下资质材料：</p>
<p>　（1）营业执照副本</p>
<p>　（2）作为本次竞价标的产品的中央国家机关政府采购协议供货商的证明材料</p>
<p>　　3.上述证明材料均应为原版文件的扫描件（电子版）。</p>
<p>四、竞价规则</p>
<p>　1.通过注册的供应商为合格供应商；</p>
<p>　2.合格供应商须在竞价时间内参加网上竞价；</p>
<p>　3.竞价时供应商须按系统提示填写产品、价格和服务信息；</p>
<p>　4.在竞价时间内，供应商可以多次报价，最终报价以所报的最低价为准；</p>
<p>　5.在竞价时间内，提供最低报价的供应商为成交供应商；</p>
<p>　6.在竞价时间内，竞价供应商均未提供有效报价的，则竞价失败。</p>
<p style="text-align:center"><button onclick="confirmBaoJia();">我要竞价</button></p>


</div>	
		<div id="footer" class="font01">
          <%@include file="/portal/page/footer.jsp"%>
		</div>
	</body>
</html>
