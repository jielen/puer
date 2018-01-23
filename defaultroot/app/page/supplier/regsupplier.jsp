<%@ page contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
	<head>
		<title>供应商注册信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link href="./../../css/supplier_style.css" rel="stylesheet"
			type="text/css" />
		<script language="javascript" src="./../../js/DateTimeCalendar.js"></script>
		<script>
			var maxRowAllow = 11;
			var flag;   
			//链接跳转
			function toURL(action){
				if(action=='save'){//保存
					if(
						
							checkContent('name',50,true)
							&&checkContent('userId',15,true)
							&&(checkContent('regCapital',10,true)&&checkNum('regCapital',true))
							&&notnull('establishDate',true)
							&&isPostalCode('zipCode',true)
							&&checkContent('address',150,true)
							&&checkContent('licenseId',25,true)
							&&notnull('licenseTimeStart',true)
							&&(notnull('licenseTimeEnd',true)&&checkEndDate('licenseTimeStart','licenseTimeEnd',true))
							&&checkContent('stateTaxRegId',25,true)
							&&notnull('stateTaxRegDate',true)
							
							&&checkContent('localTaxRegId',25,true)
							&&notnull('localTaxRegDate',true)
							
							&&(checkUrl('url',true)&&checkTeXtLen('url',50,true))
							
							&&(checkContent('email',50,true)&&checkemail('email',true))
							
							&&checkContent('bankName',50,true)
							&&checkContent('accCode',25,true)
							&&checkContent('linkMan',50,true)
							&&(notnull('linkManPhone',true)&&checkPhone('linkManPhone',true))
							&&isMobil('linkManMobile',true)
							&&checkPhone('fax',true)
							
							&&checkContent('mainBusinesses',400,true)
							
							&&checkContent('description',800,true)
							&&checkContent('legalPerson',10,true)
							&&checkTeXtLen('legalPersonAddr',100,true)
							&&(notnull('legalPersonTel',true)&&checkPhone('legalPersonTel',true))
							&&isMobil('legalPersonMobile',true)
							&&checksfzhm('sfzhm',true)
							&&checkQualify()
					    
					){
							if(confirm('你确认保存操作吗?')){
     							document.forms[0].submit();
						    }else
							    return;
							
					}
					
				}
			}
			
			function checkQualify(){
			   try{
			   	var licenseNameS = document.getElementsByName("licenseName");
				   var licenseNOS = document.getElementsByName("licenseNO");
				   var licenseIssuingAuthorityS = document.getElementsByName("licenseIssuingAuthority");
				   var remarkS = document.getElementsByName("remark");
				   for(var i=0; i<licenseNameS.length; i++){
				       if(
				         checkTeXtLen(licenseNameS[i].id,150,true)
						&& checkTeXtLen(licenseNOS[i].id,100,true)
						&& checkTeXtLen(licenseIssuingAuthorityS[i].id,100,true)
						&& checkTeXtLen(remarkS[i].id,1000,true)
						){
						   
						}else{
							return false;
						}
				   
				   }
			   }catch(e){
			    
			   }
			     return true;
			}
			
			
			function checkContent(eid,len,isSel){
				var srcObj = document.getElementById(eid);
				var meg = "\n“"+srcObj.title+"”不能为空，且长度不能超过"+len+"个字符" ;
				
				if( checkIsNull(srcObj.value) || srcObj.value.length > len){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			}
			
			function checkNum(eid,isSel){
				var srcObj = document.getElementById(eid);
				var meg = "\n“"+srcObj.title+"”必须为数字" ;
				
				if( !checkIsNull(srcObj.value) && isNaN(srcObj.value)){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			}
			
			function checkTeXtLen(eid,len,isSel){
				var srcObj = document.getElementById(eid);
				var strtitle = srcObj.title;
				var meg = "";
				if(meg == ""){
				   meg = "长度不能超过"+len+"个字符" ;
				}else{
					meg = "\n“"+srcObj.title+"”长度不能超过"+len+"个字符" ;
				}
				if(srcObj.value.length > len){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			
			}
			
			function checkEndDate(strStart,strEnd,isSel){
				var startObj = document.getElementById(strStart);
				var endObj = document.getElementById(strEnd);
				var meg = "\n“"+endObj.title+"”不能早于“"+startObj.title+"”" ;
				
				if(!compareDate(startObj.value,endObj.value)){
					//alert(msg);
					document.getElementById(strEnd+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						endObj.focus();
					}
					return false;
				}else{
					document.getElementById(strEnd+"_1").innerHTML = "";
				}
				return true;
			}
			
			function checkUrl(eid,isSel){
				var srcObj = document.getElementById(eid);
				var meg = "\n“"+srcObj.title+"”格式不正确！" ;
				
				if( !checkIsNull(srcObj.value) && !check_NetAddress(srcObj.value)){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			
			}
			
			function checkIsNull(val){
				var re = new RegExp('^\S+$');
				if(val == '' || re.test(val)){
					return true;
				}else{
					return false;
				}
			}
			
			function isMobil(uid,isSel)     
			{     
				var srcObj = document.getElementById(uid);
				var meg = "\n“"+srcObj.title+"”格式不正确！" ;
			    var patrn = /^[0-9]{11}$/;   
			    if(!checkIsNull(srcObj.value)&&!patrn.test(srcObj.value)) {   
			        document.getElementById(uid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}  
			    }else{
					document.getElementById(uid+"_1").innerHTML = "";
				}   
			    return true;   
			}   
			
			function checkPhone(uid,isSel){
				var srcObj = document.getElementById(uid);
				var meg = "\n“"+srcObj.title+"”格式不正确！" ;
			   var reg=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/ ; 
			   var falg=true;
			   if(!checkIsNull(srcObj.value)){
			       if(srcObj.value.length<7 || srcObj.value.length>18){ 
			        falg = false; 
			       } 
			       else{ 
			         falg = reg.test(srcObj.value); 
			       } 
			       
			       
				}
				if(!falg){
			       	document.getElementById(uid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
			    }else{
					document.getElementById(uid+"_1").innerHTML = "";
				}
				return falg;
			}
			
			function check_NetAddress(s){
			 	var urlreg=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&=]*)?/;
				//urlreg=/(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i;
			    if (!urlreg.test(s)){       
			         return false;
			     }else{
			     	return true ;	
			     }
		     }
			
			function isPostalCode(eid,isSel)
            {
             var strObj = document.getElementById(eid);
             var s = strObj.value
             var flag = true;
             var pattern =/^[0-9]{6}$/;
                 if(s!="")
                 {
                     if(!pattern.exec(s))
                     {
	                     document.getElementById(eid+"_1").innerHTML = "<font color='red'>请输入正确的邮政编码</font>";
	                     flag = false;
	                      if(isSel){
	                      	strObj.focus();
	                      }
                     }
                 }
                 if(flag){
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return flag;
            }
			function compareDate(strStrat,strEnd) {
		
				var startDate = new Date(strStrat.replace("-",",")).getTime() ;
				var endDate = new Date(strEnd.replace("-",",")).getTime() ;
		
				if( startDate > endDate ) 
				{  
				   return false;
				}
				return true;
			} 
			
			
			function checkMaxlen(eid,len){
				var srcObj = document.getElementById(eid);
				var meg = "\n“"+srcObj.title+"”长度不能超过"+len+"个字符" ;
				
				if( srcObj.length > len){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+msg+"</font>";
					srcObj.focus();
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			
			}
			function msg(){
			 msg='\n-------------------------------------\n\n'+
				   '             信息提交成功             \n\n'+
				   '-------------------------------------\n'+
				   '   请您耐心等候！如果您的信息通过审核，\n'+
				   '系统将向您的手机和邮箱发送正式的帐号！'
			 alert(msg);
			}
			//保存附件
			function openWindow(typeCode,name){
			  var obj=name+"AttId";
			  var qualityId = document.getElementById(obj).value;
			  var model = "resizable=yes,scrollbars=yes,height=380,width=755,"
			  model += "status=no,toolbar=no,menubar=no,location=no,top=200,left=200";
			  var url="/sup/SupQualityCtrl-initSupQualifyform.pfv?supId="+supId+"&qualityId="+qualityId+"&typeCode="+typeCode;
			  window.open(url,"", model);
			}
			//验证TEXTEARE输入框数字
			function checklen(_this,limit,obj){
				var num_obj=document.getElementById(obj);
				var len = _this.value.length;
				if (len > limit){
					_this.value = _this.value.substring(0,limit);
					num_obj.innerHTML = 0;
					var message = "不能超过"+limit+"个字符";
					alert(message);
					return false;
				}else{
					num_obj.innerHTML = limit - len;
					document.getElementById(_this.id+"_1").innerHTML ="";
					return true;
				}
		   }
		   function openOrClose(eleID){
				var thiz = document.getElementById(eleID);
				if(thiz.style.display=='none'){
					thiz.style.display="block";
				}else{
					thiz.style.display="none";
				}
		   }
		   function addRow(tabID){//添加表格的一行
			  var idTB = document.getElementById(tabID);
			  if(idTB.rows.length>=maxRowAllow){
				alert("当前最多只能添加"+(maxRowAllow-1)+"行资质数据...");
				return;
			  }
			  var oTR=idTB.insertRow(idTB.rows.length);
			  makeCols(oTR,oTR.rowIndex);
			  idLast.innerText=idTB.rows.length-1;
			  if(idTB.rows.length>=1)
				idFirst.innerText='1';
			  return true;
			}
			function makeCols(oTR,rowNum){
				var i = 0;
				var oTD=oTR.insertCell(i++);
				oTD.innerHTML="<a>"+(rowNum)+"</a>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="<input type='text' name='licenseName' id='licenseName"+(i+2)+"'/><a id='licenseName"+(i+2)+"_1'></a>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="<input type='text' name='licenseNO' id='licenseNO"+(i+2)+"'/><a id='licenseNO"+(i+2)+"_1'></a>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="<input type='text' name='licenseIssuingAuthority' id='licenseIssuingAuthority"+(i+2)+"'/><a id='licenseIssuingAuthority"+(i+2)+"_1'></a>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="起:<input type='text' style='width:32%' name='effectStartTime' onClick=\"calendar();\"/>&nbsp;&nbsp;&nbsp;"
							 +"止:<input type='text' style='width:32%' name='effectEndTime' onClick=\"calendar();\"/>";
				oTD=oTR.insertCell(i);
				oTD.innerHTML="<textarea style='width:100%' name='remark' id='remark"+(i+2)+"'></textarea><a id='remark"+(i+2)+"_1'></a>";
			}
			function delRow(tabID,delLineID){//删除表格的一行
				var info = "0(无行可删)";
				var idTB = document.getElementById(tabID);
				var idIndex = document.getElementById(delLineID);
				var sIndex=idIndex.value;
				if(sIndex==''){
					sIndex=idTB.rows.length-1;
				}else{
					sIndex=parseInt(sIndex);
				}
				//不可以删除表头
				if(sIndex <= 0 || sIndex > idTB.rows.length){
					idLast.innerText=info;
					return;
				}
				idTB.deleteRow(sIndex);
				if(idTB.rows.length<=1){
					idLast.innerText=info;
				}else{
					idLast.innerText=idTB.rows.length-1;
				}
				if(idTB.rows.length==1){
					idFirst.innerText="0";
				}
			}
			function changeBgcolor(tabID,lineNum){
				
			}

			//判空
			function notnull(eid,isSel){
				var srcObj = document.getElementById(eid);
				var meg = "\n“"+srcObj.title+"”不能为空" ;
				if(checkIsNull(srcObj.value) ){
					//alert(msg);
					document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+meg+"</font>";
					if(isSel){
						srcObj.focus();
					}
					return false;
				}else{
					document.getElementById(eid+"_1").innerHTML = "";
				}
				return true;
			}

			
			//判断身份证号码
			function checksfzhm(eid,isSel){
			   var cardType = document.getElementById("cardType").value;
			   var sfzhm = document.getElementById(eid);
			   var num = sfzhm.value;
		       num = num.toUpperCase();  

		       if(cardType=="01"){
		    	   //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
			         if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))   
			         { 
			              //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
			              document.getElementById(sfzhm.id+"_1").innerHTML = "<font color='red'>输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。</font>";
			            if(isSel){
							sfzhm.focus();
						}
			             return false; 
					  }else{
					       document.getElementById(sfzhm.id+"_1").innerHTML ="";
					       return true;
					  }
			   }else{
                     if(num==""||num==null){
			              //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
			              document.getElementById(sfzhm.id+"_1").innerHTML = "<font color='red'>证件号不能为空</font>";
			            if(isSel){
							sfzhm.focus();
						}
			             return false; 
                     }else{
					       document.getElementById(sfzhm.id+"_1").innerHTML ="";
					       return true;
					  }
			   }
		      			
			}

			//验证密码
			
			function isWhiteWpace (s){
				var whitespace = " \t\n\r";
				var i;
				for (i = 0; i < s.length; i++){   
				var c = s.charAt(i);
				if (whitespace.indexOf(c) >= 0) {
				return true;
				}
				}
				return false;
			}

			function checkPassword(pwd){
				var password = pwd.value;
				if( password.length<6 || password.length>16 ) {
				//alert("\请输入正确的密码,用户名长度为6-16位！")
				document.getElementById(pwd.id+"_1").innerHTML = "<font color='red'>\请输入正确的密码,密码长度为6-16位！</font>";
				
				return false;
				}
				else if (isWhiteWpace(password)){
				//alert("\请输入正确的密码,用户名中不能包含空格！")
				document.getElementById(pwd.id+"_1").innerHTML = "<font color='red'>\请输入正确的密码,密码不能包含空格！</font>";
				
				return false;
               }else{
            	   document.getElementById(pwd.id+"_1").innerHTML ="";
            	   return true;
                   }
			}

			function checkPassword2(pwd2){
				var password2 = pwd2.value;
				if( password2.length<6 || password2.length>16 ) {
				//alert("\请输入正确的密码,用户名长度为6-16位！")
				document.getElementById(pwd2.id+"_1").innerHTML = "<font color='red'>\请输入正确的密码,用户名长度为6-16位！</font>";
				return false;
				}
				else if (isWhiteWpace(password2)){
				//alert("\请输入正确的密码,用户名中不能包含空格！")
				document.getElementById(pwd2.id+"_1").innerHTML = "<font color='red'>\请输入正确的密码,用户名中不能包含空格！</font>";
				return false;
				}
				else if (password2 != document.getElementById("password").value){
					//alert("\ 两次密码不一致！")
					document.getElementById(pwd2.id+"_1").innerHTML = "<font color='red'>\ 两次密码不一致！</font>";
					return false;

					}else{
						document.getElementById(pwd2.id+"_1").innerHTML ="";
						return true;
						}
			}
			

			//验证邮箱
			function checkemail(obj,isSel){
	           var srcObj = document.getElementById(obj);
	            //对电子邮件的验证
				
	            var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	
	            if(!myreg.test(srcObj.value))
	
	            {
	                 //alert('提示\n\n请输入有效的E_mail！');
	                 document.getElementById(obj+"_1").innerHTML = "<font color='red'>请输入有效的E_mail！</font>";
	               	 if(isSel){
	               	  	srcObj.focus();
	               	  }
	                return false;
                }else{
					document.getElementById(obj+"_1").innerHTML = "";
				}
                return true;

        }
		
		function sltDome(obj){
		   if(obj.value=="99"){
			   if(obj.checked){
			   	document.getElementById("typeDome").disabled="";
			   }else{
			   	document.getElementById("typeDome").disabled="true"
			   }
		   }
		
		}	   
			   
	   
		</script>
	    <style type="text/css">
<!--
.STYLE1 {
	color: #366aa5;
	font-size: 36px;
}
-->
        </style>
</head>
	<body>
	     <table width="100%">
		 <tr><td align="right" height="50">
		
		<a href="help.jsp"  target="_blank" style="color:#4f6b72"><U>帮助文档</U></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 </td>
		 <tr><td align="center" >
		
		 <span class="STYLE1">供应商注册</span>		
		 </td>
		 </tr>
	       <tr><td align="center">
		<html:form action="/portal/page/supplier/saveRegSupplier.do"
			method="post" onsubmit="return true">
			<div class="group-frame" style="width: 80%; ">
				<html:messages id="error" message="true">
					<font color="red" size="4" id="errorInfo"><bean:write name="error" /></font>
				</html:messages>
				<div class="group-down" onClick="openOrClose('part2')">
					基本情况
				</div>
				<div id="part2" style="display: block">
					<table width="100%" border="1" id="gpSupplierBO">
						<tr>
							<td class="itda" width="15%" rowspan="1">
								企业名称							</td>
							<td class="itdb" colspan="1">
								<html:text property="supplier.name" title="企业名称"
									style="width:56%;" styleId="name"
									onblur="checkContent('name',50,false)" />
								<font color="red" size="4">*</font>
								<a id="name_1"></a>
							</td>
							<td class="itda" width="15%" rowspan="1">
								组织机构代码							</td>
							<td class="itdb" width="300" colspan="1">
								<html:text property="user.userId" title="组织机构代码"
									style="width:56%;" onblur="checkContent('userId',15,false)"
									styleId="userId" />
								<font color="red" size="4">*</font>
								<a id="userId_1"></a>
							</td>


						</tr>
						<%--  
						<tr >
							<td class="itda">登录密码</td>
							<td class="itdb" colspan="1">
							    <html:password property="user.password" title="登录密码" style="width:36%;" onblur="checkPassword(password)" styleId="password"/>
								<font color="red" size="4">*</font>
								<a id="password_1"></a>
							</td>
							<td class="itda" width="16%">确认密码</td>
							<td class="itdb" width="30%">
								<input id="confirm_txt" type="password" title="确认密码" style="width:36%;" onblur="checkPassword2(confirm_txt)"/>
								<font color="red" size="4">*</font>
								<a id="confirm_txt_1"></a>
							</td>
						</tr>
						 					
						
						
							<td class="itda" width="300">供应商名称</td>
							<td class="itdb" width="30%">
								<html:text property="user.userName" title="供应商名称" style="width:36%;" onblur="notnull(gysmc,title)" styleId="gysmc"/>
								<font color="red" size="4">*</font>
								<a id="gysmc_1"></a>
							</td>
						--%>
						<tr>
							<td width="15%" class="itda">
								注册资本							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.regCapital" title="注册资本"
									style="width:56%;"
									onblur="checkContent('regCapital',10,false)?checkNum('regCapital',false):false"
									styleId="regCapital" />
								万
								<font color="red" size="4">*</font>
								<a id="regCapital_1"></a>
						  </td>
							<td width="15%" class="itda">
								是否中小企业							</td>
							<td class="itdb" colspan="1">
								<html:select property="supplier.isZxqy" title="是否中小企业"
									style="width:20%;">
									<option></option>
									<html:optionsCollection property="scaleList" value="id"
										label="label" />
								</html:select>
							</td>

						</tr>
						<tr>
							<td class="itda" width="15%">
								成立时间							</td>
							<td class="itdb" width="35%">
								<input type="text" name="establishDate" title="成立时间"
									 style="width:56%;" readonly="true"
									onclick="calendar();" onBlur="notnull('establishDate',false)" />
								<font color="red" size="4">*</font>
								<a id="establishDate_1"></a>
						  </td>
							<td class="itda" width="15%">
								邮编							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.zipCode" title="邮编"
									style="width:56%;" onblur="isPostalCode('zipCode',false)"
									styleId="zipCode" />
								<a id="zipCode_1"></a>
						  </td>
						</tr>

						<tr>
							<td width="15%" class="itda">
								营业地址							</td>
							<td class="itdb" colspan="3">
								<html:text property="supplier.address" title="营业地址"
									style="width:56%;" onblur="checkContent('address',150,false)"
									styleId="address" />
								<font color="red" size="4">*</font>
								<a id="address_1"></a>
							</td>

						</tr>
						<tr>
							<td width="15%" class="itda">
								营业执照编号							</td>
							<td class="itdb" colspan="1">
								<html:text property="supplier.licenseId" title="营业执照编号"
									style="width:56%;" onblur="checkContent('licenseId',25,false)"
									styleId="licenseId" />
								<font color="red" size="4">*</font>
								<a id="licenseId_1"></a>
							</td>
							<td class="itda" width="15%">
								营业执照有效期							</td>
							<td width="35%" class="itdb">
								<input type="text" name="licenseTimeStart"
									title="营业执照有效期起始时间" readonly="true" style="width:56%;"
									onclick="calendar();"
									onblur="notnull('licenseTimeStart',false)"
									 />
								至
								<input type="text" name="licenseTimeEnd"
									title="营业执照有效期结束时间" readonly="true" style="width:56%;"
									onclick="calendar();"
									onblur="notnull('licenseTimeEnd',false)?checkEndDate('licenseTimeStart','licenseTimeEnd',false):false "
									 />
								<font color="red" size="4">*</font>
								<a id="licenseTimeStart_1"></a>
								<a id="licenseTimeEnd_1"></a>
						  </td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								国税登记号							</td>
							<td class="itdb" colspan="1">
								<html:text property="supplier.stateTaxRegId" title="国税登记号"
									style="width:56%;"
									onblur="checkContent('stateTaxRegId',25,false)"
									styleId="stateTaxRegId" />
								<font color="red" size="4">*</font>
								<a id="stateTaxRegId_1"></a>
							</td>
							<td class="itda" width="15%">
								国税登记时间							</td>
							<td width="35%" class="itdb">
								<input type="text" name="stateTaxRegDate" title="国税登记时间"
									readonly="true" style="width:56%;" onClick="calendar();"
									onblur="notnull('stateTaxRegDate',false)"
									 />
								<font color="red" size="4">*</font>
								<a id="stateTaxRegDate_1"></a>
						  </td>
						</tr>

						<tr>
							<td width="15%" class="itda">
								地税登记号							</td>
							<td class="itdb" colspan="1">
								<html:text property="supplier.localTaxRegId" title="地税登记号"
									style="width:56%;"
									onblur="checkContent('localTaxRegId',25,false)"
									styleId="localTaxRegId" />
								<font color="red" size="4">*</font>
								<a id="localTaxRegId_1"></a>
							</td>
							<td class="itda" width="15%">
								地税登记时间							</td>
							<td width="35%" class="itdb">
								<input type="text" name="localTaxRegDate" title="地税登记时间"
									readonly="true" style="width:56%;" onClick="calendar();"
									onblur="notnull('localTaxRegDate',false)"
									 />
								<font color="red" size="4">*</font>
								<a id="localTaxRegDate_1"></a>
						  </td>
						</tr>

						<tr>
							<td width="15%" class="itda">
								供应商类别							</td>
						    <td  class="itdb" >
								<logic:iterate  id="opt" name="SupplierFormBean" property="zstList" type="com.ufgov.zc.server.zc.web.OptionItem">
								
								<html:multibox  property="supplier.zcSupplierType">
								<bean:write name="opt"  property="id"/>
								</html:multibox>
								 <bean:write name="opt"  property="label"/>
								</logic:iterate>
    						</td>
							<td width="15%" class="itda">
								供应商性质							</td>
							<td class="itdb" colspan="2">
								<html:select property="supplier.zcSupplierKind" title="供应商性质"
									style="width:30%;">
									<option></option>
									<html:optionsCollection property="zskList" value="id"
										label="label" />
								</html:select>

							</td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								经营类别							</td>
							<td  class="itdb" colspan="3">
								<logic:iterate  id="opt" name="SupplierFormBean" property="bsList" type="com.ufgov.zc.server.zc.web.OptionItem">
								
								<html:multibox  property="bsTypes" onclick="sltDome(this)" >
								<bean:write name="opt"  property="id"/>
								</html:multibox>
								 <bean:write name="opt"  property="label"/>
								</logic:iterate>
								<input width="20" name="typeDome" disabled="disabled">
						  </td>
							
						</tr>

						<tr>
							<td width="15%" class="itda">
								网址							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.url" title="网址" style="width:56%;"
									onblur="checkUrl('url',false)?checkTeXtLen('url',50,false):false"
									styleId="url" />
								<a id="url_1"></a>
						  </td>
							<td width="15%" class="itda">
								电子邮箱							</td>
							<td class="itdb" colspan="2">
								<html:text property="supplier.email" title="电子邮箱"
									style="width:56%;"
									onblur="checkContent('email',50,false)?checkemail('email',false):false"
									styleId="email" />
								<font color="red" size="4">*</font>
								<a id="email_1"></a>
							</td>
						</tr>

						<tr>
							<td width="15%" class="itda">
								开户银行							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.bankName" title="开户银行"
									style="width:56%;" onblur="checkContent('bankName',50,false)"
									styleId="bankName" />
								<font color="red" size="4">*</font>
								<a id="bankName_1"></a>
						  </td>
							<td width="15%" class="itda">
								银行帐号							</td>
							<td class="itdb" colspan="1">
								<html:text property="supplier.accCode" title="银行帐号"
									style="width:56%;" onblur="checkContent('accCode',25)"
									styleId="accCode" />
								<font color="red" size="4">*</font>
								<a id="accCode_1"></a>
							</td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								联系人姓名							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.linkMan" title="联系人姓名"
									style="width:56%;" onblur="checkContent('linkMan',50)"
									styleId="linkMan" />
								<font color="red" size="4">*</font>
								<a id="linkMan_1"></a>
						  </td>
							<td width="15%" class="itda">
								联系人电话							</td>
							<td class="itdb" colspan="2">
								<html:text property="supplier.linkManPhone" title="联系人电话"
									style="width:56%;"
									onblur="notnull('linkManPhone',false)?checkPhone('linkManPhone',false):false"
									styleId="linkManPhone" />
								<font color="red" size="4">*</font>
								<a id="linkManPhone_1"></a>
							</td>
						</tr>
						<tr>
							<td class="itda" width="15%">
								联系人手机号							</td>
							<td class="itdb" colspan="1" width="40%">
								<html:text property="supplier.linkManMobile" title="联系人手机号"
									style="width:56%;" onblur="isMobil('linkManMobile',false)"
									styleId="linkManMobile" />
								<a id="linkManMobile_1"></a>
							</td>
							<td width="15%" class="itda">
								传真							</td>
							<td class="itdb" colspan="2">
								<html:text property="supplier.fax" title="传真" style="width:56%;"
									onblur="checkPhone('fax',false)" styleId="fax" />
								<a id="fax_1"></a>
							</td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								主营范围							</td>
							<td class="itdb" colspan="4">
								<html:textarea property="supplier.mainBusinesses" title="主营范围"
									cols="60" rows="3" style="width:95%"
									onkeypress="checklen(this,400,'mainWorkRange_str_len')"
									onkeyup="checklen(this,400,'mainWorkRange_str_len')"
									onblur="checkContent('mainBusinesses',400,false)"
									styleId="mainBusinesses" />
								<font color="red" size="4">*</font>

								<div class="inpt" style="width: 30%">
									还可以输入
									<font color="red"><span id="mainWorkRange_str_len">400</span>
									</font>字/400
								</div>
								<a id="mainBusinesses_1"></a>
							</td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								公司介绍							</td>
							<td class="itdb" colspan="4">
								<html:textarea property="supplier.description" title="公司整体概括介绍"
									cols="60" rows="3" style="width:95%"
									onkeyup="checklen(this,800,'str_len')"
									onkeypress="checklen(this,800,'str_len')"
									onblur="checkContent('description',800,false)"
									styleId="description" />
								<font color="red" size="4">*</font>

								<div class="inpt" style="width: 30%">
									还可以输入
									<font color="red"><span id="str_len">800</span>
									</font>字/800
								</div>
								<a id="description_1"></a>
							</td>
						</tr>
				  </table>
				</div>
			</div>
			<br>
			<div class="group-frame" style="width: 80%; ">
				<div class="group-down" onClick="openOrClose('part3')">
					法人信息
				</div> 
				<div id="part3" style="display: block; ">
					<table width="100%" border="0" id="repBO">
						<tr>
							<td class="itda" width="15%">
								法人代表人							</td>
							<td class="itdb" width="35%">
								<html:text property="supplier.legalPerson" title="法人代表人"
									style="width:56%;"
									onblur="checkContent('legalPerson',10,false)"
									styleId="legalPerson" />
								<font color="red" size="4">*</font>
								<a id="legalPerson_1"></a>
						  </td>
							<td class="itda" width="15%">
								联系地址							</td>
							<td class="itdb" width="35%">
								<html:text property="supplier.legalPersonAddr" title="联系地址"
									style="width:56%;"
									onblur="checkTeXtLen('legalPersonAddr',100,false)"
									styleId="legalPersonAddr" />
								<a id="legalPersonAddr_1"></a>
						  </td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								联系电话							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.legalPersonTel" title="联系电话"
									style="width:56%;"
									onblur="notnull('legalPersonTel',false)?checkPhone('legalPersonTel',false):false"
									styleId="legalPersonTel" />
								<font color="red" size="4">*</font>
								<a id="legalPersonTel_1"></a>
						  </td>
							<td width="15%" class="itda">
								手机号码							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.legalPersonMobile" title="手机号码"
									style="width:56%;" onblur="isMobil('legalPersonMobile',false)"
									styleId="legalPersonMobile" />
								<a id="legalPersonMobile_1"></a>
						  </td>
						</tr>
						<tr>
							<td width="15%" class="itda">
								传真							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.legalPersonFax" title="传真"
									style="width:56%;" onblur="checkPhone('legalPersonFax',false)"
									styleId="legalPersonFax" />
								<a id="legalPersonFax_1"></a>
						  </td>
							<td width="15%" class="itda">
								证件号码	<html:select property="supplier.legalCardType" title="证件类型"
									style="width:60%;" styleId="cardType" onchange="checksfzhm('sfzhm',false)">
									<html:optionsCollection property="cardNoList" value="id"
										label="label"/>
								</html:select>							
							</td>
							<td width="35%" class="itdb">
								<html:text property="supplier.legalPersonIDCard" title="证件号码"
									style="width:56%;" onblur="checksfzhm('sfzhm',false)"
									styleId="sfzhm" />
								<font id="idCard_star" color="red" size="4">*</font>
								<a id="sfzhm_1"></a>
						  </td>
						</tr>
				  </table>
				</div> 
			</div>
			<br>
			<div class="group-frame" style="width: 80%; ">
				<div class="group-down" onClick="openOrClose('part4')">
					相关资质(资格)证书
				</div>
				<div id="part4" style="display: block" >
					<table width="100%" border="1" id="qualifiesTab"
						style="font-size: 14px">
						<tr bgcolor="green"  align="center">
							<td width="6%" height="30">
								序号							</td>
							<td width="15%">
								资质名称
							</td>
							<td width="15%">
								资质编号
							</td>
							<td width="14%">
								发证机关
							</td>
							<td width="20%">
								资质有效期
							</td>
							<td width="40%">
								备注
							</td>
						</tr>
						<logic:iterate id="a" name="SupplierFormBean" property="list"
							type="com.ufgov.zc.common.zc.model.ZcEbSupplierQualify">
							<tr>
								<td>
									<bean:write name="a" property="seqNum" />
									
								</td>
								<td>
									<html:text name="a" property="licenseName" styleId="licenseName1"/>
									<a id="licenseName1_1"></a>
								</td>
								<td>
									<html:text name="a" property="licenseNO" styleId="licenseNO1"/>
									<a id="licenseNO1_1"></a>
								</td>
								<td>
									<html:text name="a" property="licenseIssuingAuthority" styleId="licenseIssuingAuthority1"/>
									<a id="licenseIssuingAuthority1_1"></a>
								</td>
								<td>
									起:
									<html:text style="width:32%" name="a"
										property="effectStartTime" onclick="calendar();" />
									&nbsp; 止:
									<html:text style="width:32%" name="a" property="effectEndTime"
										onclick="calendar();" />
								</td>  
								<td>
									<html:textarea style="width:100%" name="a" property="remark" styleId="remark1" />
									<a id="remark1_1"></a>
								</td>
							</tr>
						</logic:iterate>
					</table>
					<table width="100%" cellpadding="1" cellspacing="1">
						<tr>
							<td colspan="5" style="width: 70%" align="right">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="pbutton" value="添加一行"
									style="width: 10%;" name="savejz"
									onclick="addRow('qualifiesTab');" />
							</td>
							<td align="left">
								&nbsp;&nbsp;
								<input type="button" class="pbutton" value="删  除"
									style="width: 20%;" name="savejz"
									onclick="delRow('qualifiesTab','deleteTarger');" />
								<font size="2">第</font>
								<input id="deleteTarger" type="text" style="width: 10%;"
									onkeypress="changeBgcolor('qualifiesTab',this.value);" />
								<font size="2">行(<a id="idFirst">1</a>~<a id="idLast">1</a>)</font>
							</td>
						</tr>
					</table>
		  </div>
			</div>
			<table id="operatorTab" width="100%" cellpadding="1" cellspacing="2"
				align="center">
				<tr>
					<td align="center" colspan="4">
						<br>
						<input type="button" class="pbutton" value="保  存"
							style="width: 10%; height: 22px;" name="savejz"
							onclick="toURL('save');" />
					</td>
				</tr>
			</table>
		</html:form>
    </table></tr></td>
	</body> 
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function openNotice(){
	
	//window.open ('supplierNotice.html','通知','height=500,width=800,scrollbars=yes, resizable=yes') 
	
}
	//通知连接
	//  try{
	//	   if(""==errorInfo.innerText){
	//	   		openNotice();
	//	   }
	 //  }catch(e){
	//   		openNotice();
	//   }

//-->
</SCRIPT>