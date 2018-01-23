<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>供应商注册需知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<script>
	
		//下一步
		function gotonotice(){
			var obj1=document.getElementById("agree");
			if(obj1.checked){
				window.location="regSupplier.do";
			}else{
				alert("您必须选中“已阅读”才能进行下一步操作");
			}
		}
		
		function gotoNext(){
			var obj1=document.getElementById("agree");
			var obj2=document.getElementById("noagree");
			if(obj1.checked){
				if(obj2.checked){
					obj2.checked=false;
				}
				document.getElementById("div_next").style.display="block";
				
			}else{
				document.getElementById("div_next").style.display="none";
			
			}
			
		
		}
		
		function closeWindow(){
			var obj1=document.getElementById("agree");
			var obj2=document.getElementById("noagree");
			if(obj2.checked){
				if(obj1.checked){
					obj1.checked=false;
				}
			}
			
			//document.getElementById("div_next").style.display="none";
		}
		//注册进度查询
		function zhucheSearch(){
			var url = '/sup/SupWebShowCtrl-initRegisterForm.pfv';
			window.open(url ,null,"top=300 left=460 height=200,width=400,status=no,toolbar=no,menubar=no");
		}
	</script>
	<style>
		.top-wz{
			 font-size:12px;color:black;
			 line-height:25px;
		}
		.top-wz1{
			 font-size:12px;color:black;
			 line-height:20px;
		}
		/*页面底部*/
		.footer {
		   width: 781px;
			background:#efefef;
		}
		.footer p {
			text-align: center;
			font-size: 12px;
			margin:0px;
			line-height:25px;
			height:25px;
			padding:0px;
		}
		.btn{
			BORDER-RIGHT: #CCCCCC 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-BOTTOM: #CCCCCC 1px solid;
			PADDING:5px 2px 2px 2px; 
			FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#996600); 
			BORDER-LEFT: #ffffff 1px solid; CURSOR: hand; COLOR: black; 
			font-size:14px;
		}
		.top-wz a,.top-wz a:visited{color:blue;TEXT-DECORATION:underline;}
		.point {font-size:16px;font-weight:800;}
		.point span{font-size:12px;color:red;}
	</style>
	<style>
	.menu li{line-height:25px;
		list-style-type:none;
		float:right;
		color:#3344AE;
		padding-top:60px;
		width:100px;
		font-family:"黑体";
		font-size:18px;  
		text-align:center;
	}
	.menu{float:left;width:88%;padding-left:200px;margin:0px;}
	.menu ul{padding:0px;margin:0px;}
	.menu LI A {
 
		LINE-HEIGHT: 25px; 
		DISPLAY: block; 
		COLOR: #fff; 
		TEXT-DECORATION: none
	}
	.menu A:hover {
		COLOR: #fff;TEXT-DECORATION: none
	}
	.menu  .cur A {
		BACKGROUND: #029BE1; COLOR: #fff;
	}
	.menu  .cur A:hover {
		COLOR: #fff;TEXT-DECORATION: none
	}
	
	A:hover {
		COLOR: #000;TEXT-DECORATION: none
	}
	.menu  .cur{FONT-WEIGHT: bold;line-height:25px;}
	p.p1 a,p.p1 a:hover,p.p1 a:visited{color:white;TEXT-DECORATION:underline}
	</style>
</head>


  
 <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<table width="781" border="0" align="center" cellpadding="0" cellspacing="0">
	
	  <tr> 
	    <td height="422" valign="top" style="background:url(images/zjbj_bg1.gif) #fff repeat-x 50% top"> 
	    	<table width="710" border="2" align="center" cellpadding="0" cellspacing="0" bordercolor="#0098DE">
	        <tr> 
	          <td height="35" align="center" background="portal/img/gys.jpg" >
	          </td>
	        </tr>
	        <tr> 
	          <td class="top-wz"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr> 
	                <td class="top-wz">
	                <h5>注册陕西省政府采购供应商应具备以下条件： </h5> 
	                	1．具有独立承担民事责任的能力； <br>
						2．具有良好的商业信誉和健全的财务会计制度； <br>
						3．具有履行合同所必需的设备和专业技术能力； <br>
						4．有依法缴纳税收和社会保障资金的良好记录； <br>
						5．参加政府采购活动前3年内，在经营活动中没有重大违法记录； <br>
						6．法律、法规规定的其它条件。 <br>
	                <h5>申请陕西省政府采购供应商应填报或扫描录入以下资料：</h5> 
	                	1．企业法人营业执照（或事业等其他法人证书）； <br>
	    				2．税务登记证； <br>
						3．社会保险登记证； <br>
	    				4．组织机构代码证和法定代表人身份证； <br>
	    				5．最近年度的资产负债表、损益表和验资（出资）报告； <br>
	    				6．特许生产、经营或安全卫生许可，特定技术或业务资质，IS0质量和环保管理认证，品牌授权代理等有助于证明其经营和管理能力的证书； <br>
	    				7．审查机构认为需要提交或供应商认为可以提供的其他资料（如公司章程、信用报告等）。 <br>
	                <h5>注册供应商享有以下权利：</h5>
	                	1．使用“陕西省政府采购供应商信息管理系统”（以下简称系统），直接参加网上政府采购活动，并向省政府采购中心和省财政厅提出改进信息管理系统的意见和建议；  <br>
	    				2．查阅并下载采购文件（标书），浏览有关采购公告的历史信息，发布供销信息，订阅与其经营范围和所注册的产品服务相关的采购信息；  <br>
	    				3．直接被系统随机抽取为竞争性谈判、询价采购的受邀供应商；  <br>
	    				4．在本省范围内参加政府采购活动时，免于提供已注册登记资格信息的相关证明文件，并在预中标成交前免于对该部分资格信息的资格性审查；  <br>
	    				5．对采购单位和采购代理机构组织的政府采购活动的服务水平、工作效率和公正性等进行评价； <br>
	    				6．在陕西政府采购网站优先开设网上商铺、优先获取网站广告权，介绍、发布自己的产品服务和优惠促销等信息；  <br>
	    				7．法律、法规规定的其他权利。  <br>
	                <h5>注册供应商承担以下义务：</h5> 
	                	1．自觉遵守政府采购法律法规和其他相关规定，共同维护政府采购市场秩序和公平竞争环境，并接受财政部门的监督检查； <br>
	    				2．依法、诚信地参加政府采购活动，积极参与政府采购市场诚信体系建设； <br>
	    				3．依法履行政府采购合同和政府采购活动中的各项承诺，切实维护国家和社会公共利益以及采购单位的合法权益； <br>
	    				4．对在政府采购过程中获取的国家秘密和商业秘密负有保密责任； <br>
	    				5．全面、真实地登记供应商相关信息；及时调整变更信息，并按规定报送审查机构审查； <br>
	    				6．法律、法规规定的其他义务。 <br>
				    <h5>其他事项：</h5> 
		        		1．经审核通过被注册成为陕西省供应商库的供应商，其注册资格全省有效，参加全省政府采购活动，无需要再注册登记。<br>
						2．下列供应商必须申请注册：<br>
						&nbsp;&nbsp;（1）政府采购中标或成交供应商； <br> 
	   					&nbsp;&nbsp;（2）政府采购协议供货或定点管理的供应商及其供货商； <br> 
	   					&nbsp;&nbsp;（3）参与网上政府采购活动的供应商；<br> 
	                </td>
	              </tr>
	              <tr>
	              <td class="point" align="center" ><span>您必须选中“已阅读”才能进行下一步操作</span><br>
	              <input type="checkbox" id="agree"   onclick="gotoNext();" >已阅读
	              <input type="checkbox" id="noagree" onClick="closeWindow();">未阅读
	              </td>
                </tr>
	            </table></td>
	        </tr>
	      </table>
	      <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr > 
	          <td height="100" align="center" >
	          	<div id="div_next" >
	          		
	          		<input type="button" value="下一步  >>" class="btn" onClick="gotonotice()"/>
	          	</div>
	          </td>
	        </tr>
	      </table></td>
	  </tr>
	  <tr> 
	    <td height="71" valign="top" background="images/dib.gif"> 
	      <div align="center"> </div></td>
	  </tr>
	  <tr>
	    <td height="60" valign="top" background="images/jw.gif"> 
		<div id="footer" class="font01">
          <%@include file="/portal/page/footer.jsp"%>
		</div>
	    </td>
	  </tr>
	</table>
	<map name="Map" id="Map">
		<area shape="rect" coords="270,91,329,108" href="http://www.zjzfcg.gov.cn/new/bszn/index.htm?mu=gyszcsq" title="注册流程"/>
	<map name="Map">
</body>


</html>
