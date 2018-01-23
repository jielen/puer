<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>代理机构注册需知</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<script>
	
		//下一步
		function gotonotice(){
			var obj1=document.getElementById("agree");
			var obj2=document.getElementById("noagree");
			if(obj1.checked){
				window.location="regAgency.do";
			}else if(obj2.checked){
			     closeWindow();
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
			
			document.getElementById("div_next").style.display="none";
			window.close();
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
	          <td height="35" align="center"><b>代理机构注册须知</b></td>
	        </tr>
	        <tr> 
	          <td class="top-wz"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
	              <tr> 
	                <td class="top-wz">
	                <h5>注册陕西省政府采购代理机构应具备以下条件： </h5> 
                        1.具有法人资格，且注册资本为人民币50万元以上； <br>
                        2.与行政机关没有隶属关系或者其他利益关系； <br>
                        3.具有健全的组织机构和内部管理制度； <br>
                        4.拥有固定的营业场所和开展政府采购代理业务所需设备、设施等办公条件； <br>
                        5.具有良好的商业信誉以及依法缴纳税收和社会保障资金的良好记录； <br>
                        6.申请政府采购代理机构资格前三年内，在经营活动中没有重大违法记录； <br>
                        7.有参加过规定的政府采购培训，熟悉政府采购法律、法规、规章制度和采购代理业务的法律、经济和技术方面的专业人员，其中：技术方面的专业人员，具有中专以上学历的不得少于职工总数的50％，具有高级职称的不得少于职工总数的10％。 <br>
	                <h5>申请陕西省政府采购代理机构应填报或扫描录入以下资料：</h5> 
                        1.企业法人营业执照和税务登记证副本及其复印件；<br>
                        2.机构章程，内部机构设置和人员配备情况说明，以及符合规定条件的技术方面专业人员的学历、职称证书复印件；<br>
                        3.会计师事务所出具的验资报告或者上年度的财务审计报告；<br>
                        4.拥有固定的营业场所和开展政府采购代理业务所需设备、设施等办公条件的相关证明材料；<br>
                        5.依法缴纳税收和社会保障资金的证明；<br>
                        6.申请政府采购代理机构资格前三年内有无重大违法记录的情况说明；<br>
                        7.法律、行政法规规定的其他材料。<br>
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
	          	<div id="div_next" style="display:none">
	          		<input type="button" value="下一步  >>" class="btn" onClick="gotonotice()"/>
	          	</div>
	          </td>
	        </tr>
	      </table></td>
	  </tr>
	  <tr>
	    <td height="60" valign="top" background="images/jw.gif"> 
		<div id="footer" class="font01">
          <%@include file="/portal/page/footer.jsp"%>
		</div>
	    </td>
	  </tr>
	</table>
</body>


</html>
