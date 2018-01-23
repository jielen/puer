<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%
  String path = request.getContextPath();
  String homePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<title>专家注册信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<link href="./../../css/supplier_style.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="./../../js/DateTimeCalendar.js"></script>
   <script>
   var rowNum=1;
   var maxRowAllow = 11;
   var flag;
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
		alert("当前最多只能添加"+(maxRowAllow-1)+"行品目明细...");
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
				oTD.innerHTML="<a onclick='openwindow("+rowNum+")'>"+(rowNum)+"</a>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="<input type='text' name='licenseNo' onclick='openwindow("+rowNum+")' id='expertId'/>";
				oTD=oTR.insertCell(i++);
				oTD.innerHTML="<input type='text' name='licenseName' readonly='true' onclick='openwindow("+rowNum+")'/>";
				oTD=oTR.insertCell(i++);
				
				
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
	
	var newWindow;
	//打开新窗口		
	function openwindow(rowNum){
	
		newWindow=window.open ('regexpertQuery.do?rowNum='+rowNum,'newwindow', 'height=400, width=600, top=0,left=0 ,scrollbars=yes, resizable=yes')
		newWindow.rowNum=rowNum;
	// window.open ('newWindow.jsp', 'newwindow', 'height=600, width=800, top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no') 
	}	
	
	 function subExpert(){
	 if(!checkContent("fullname","\n“姓名”不能为空")
							||!checkContent("zjhm","\n“证件号码”不能为空")||!checkContent("unitname","\n“所在单位”不能为空")
							||!checkContent("getJobDate","\n“获取职称时间”不能为空")||!checkContent("zsmc","\n“执业资格证书名称”不能为空")
							||!checkContent("mobile","\n“手机号码”不能为空")||!checkContent("phone","\n“单位电话”不能为空")
							||!checkContent("expertId","\n“专家类型”不能为空,请您添加!")){
	 }else{
	 	confirm('你确认保存操作吗?');
		document.forms[0].submit(); 
	 }
	 }
	 
	 function checkContent(eid,msg){
		var srcObj = document.getElementById(eid);
		var re = new RegExp('^\S+$');
		//alert(srcObj);
		if(srcObj==undefined || srcObj.value==undefined){
		    alert("请添加评标品目明细!");
		    return;
		}else if(srcObj.value!='' && !re.test(srcObj.value)){
		}else{
			//alert(msg);
			document.getElementById(eid+"_1").innerHTML = "<font color='red'>"+msg+"</font>";
			srcObj.focus();
			return false;
		}
		return true;
	  }
	 
	 //判空
	function notnull(str,strname){
		if(str.value.replace(/\s/g,"") == ""){
			  //alert(strname+"不能为空");
			  document.getElementById(str.id+"_1").innerHTML = "<font color='red'>"+strname+"不能为空!</font>";
			  return false;
			}else{
				document.getElementById(str.id+"_1").innerHTML = "";
				return true;
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
			

		
   </script>
  </head>
  
  <body>
  <table align="center" style="width:90%"> <tr><td width="100%" align="center">
  <table  width="100%" align="center">
    <tr><td width="100%" align="center">
      <html:form action="/portal/page/expert/regexpertSave.do" method="post" >
    <div class="group-frame">
    <html:messages id="error" message="true">
				   <font color="red" size="4"><bean:write name="error"/></font>
			</html:messages>
		<div class="group-down" onClick="openOrClose('part2')">专家信息</div>
			<div id="part2" style="display: block">
			   <table width="100%" border="0">
			    <tr>
			    <td width="12%" class="itda" >登录名称</td>
					<td width="20%" class="itdb">
						<html:text property="user.userId" title="登录名称"  onblur="notnull(uname,title)" styleId="uname"/>
						<font color="red" size="4">*</font>
						<a id="uname_1"></a>
					</td>
			      <td width="12%" class="itda">姓名</td>
			      <td width="25%" class="itdb">
			      <html:text property="expertpo.em_expert_name" title="姓名" styleId="fullname" onblur="notnull(fullname,title)"></html:text>
			      <font color="red" size="4">*</font>
			      <a id="fullname_1"></a>
			      </td>
			    	<td width="10%" class="itda">性别</td>
			      <td width="25%" class="itdb">
			      <html:select property="expertpo.em_expert_sex" styleId="sex">
			      <html:optionsCollection property="sexList" value="id" label="label" />
			      </html:select>
			      <font color="red" size="4">*</font>
			      </td>
			    </tr>
			      <tr>
			      <td width="12%" class="itda">登录密码</td>
					<td width="20%" class="itdb" >
					    <html:password property="user.password" title="登录密码"  onblur="checkPassword(password)" styleId="password"/>
						<font color="red" size="4">*</font>
						<a id="password_1"></a>
					</td>
					<td width="12%" class="itda">确认密码</td>
					<td width="25%" class="itdb" >
						<input id="confirm_txt" type="password" title="确认密码"  onblur="checkPassword2(confirm_txt)"/>
						<font color="red" size="4">*</font>
						<a id="confirm_txt_1"></a>
					</td>
			        <td width="10%" class="itda">出生年月</td>
			      <td width="25%" class="itdb">
			       <input id="birthDate" title="出生时间" name="birthDate" onClick="calendar();"/>
			      </td>
			    </tr>
			       <tr>
			        <td width="12%" class="itda">证件类型</td>
			      <td width="20%" class="itdb">
			      <html:select property="expertpo.em_id_type">
			      <html:optionsCollection property="peopleList" value="id" label="label" />
			      <font color="red" size="4">*</font>
			      </html:select>
			      </td>
			      <td width="12%" class="itda">证件号码</td>
			      <td width="25%" class="itdb">
			       <html:text property="expertpo.em_id_no" styleId="zjhm" title="证件号码" onblur="notnull(zjhm,title)"/>
			      <font color="red" size="4">*</font>
			      <a id="zjhm_1"></a>
			      </td>
			      <td width="10%" class="itda">最高学历</td>
			      <td width="25%" class="itdb">
			      <html:text property="expertpo.em_diploma"></html:text>
			      </td>
			    </tr>
			   
			     <tr>
			       <td width="12%" class="itda">毕业院校</td>
			      <td width="20%" class="itdb">
			      <html:text property="expertpo.em_university"></html:text>
			      </td>
			       <td width="12%" class="itda">毕业时间</td>
			      <td width="25%" class="itdb">
			      <input id="graduateDate" title="毕业时间" name="graduateDate" onClick="calendar();"/>
			      </td>
			      <td width="10%" class="itda">参加工作时间</td>
			      <td width="25%" class="itdb">
			     <input id="workDate" title="参加工作时间" name="workDate" onClick="calendar();"/>
			      </td>
			    </tr>
			    <!-- 
			    <tr>
			    <td class="itda">
			         省
			    </td>
			    <td class="itdb">
			    <html:text property="expertpo.em_province"></html:text>
			    </td>
			     <td class="itda">
			           市（州）
			    </td>
			    <td class="itdb">
			    <html:text property="expertpo.em_city"></html:text>
			    </td>
			     <td class="itda">
			           县（区）
			    </td>
			    <td class="itdb">
			     <html:text property="expertpo.em_county"></html:text>
			    </td>
			    </tr>
			      -->
			  <tr>
			     <td width="12%" class="itda">开始专业时间</td>
			      <td width="20%" class="itdb">
			       <input id="beginDate" title="开始专业时间" name="beginDate" onClick="calendar();"/>
			      </td>
			      <td width="12%" class="itda">电子邮箱</td>
			      <td width="25%" class="itdb">
			      <html:text property="expertpo.em_email"></html:text>
			      </td>
			    <td width="10%" class="itda">
			        单位类型			    </td>
			    <td width="25%" class="itdb">
			    <html:select property="expertpo.em_unit_type">
			      <html:optionsCollection property="comList" value="id" label="label" />
			      </html:select>
			    </td>
			    </tr>
			    <tr>
			       <td width="12%" class="itda">
			           所在单位			    </td>
			    <td width="20%" class="itdb">
			    <html:text property="expertpo.em_unit_name" styleId="unitname" title="所在单位" onblur="notnull(unitname,title)"></html:text>
			    <font color="red" size="4">*</font>
			    <a id="unitname_1"></a>
			    </td>
			     <td width="12%" class="itda">
			         职位			    </td>
			    <td width="25%" class="itdb">
			     <html:select property="expertpo.em_principalship">
			      <html:optionsCollection property="positionList" value="id" label="label" />
			      </html:select>
			    </td>
			    <td width="10%" class="itda">
			        职称			    </td>
			    <td width="25%" class="itdb">
			    <html:select property="expertpo.em_expert_protitle">
			      <html:optionsCollection property="callList" value="id" label="label" />
			      </html:select>
			   <font color="red" size="4">*</font>
			    </td>
			    </tr>
			    <tr>
			     <td width="12%" class="itda">
			           获取职称时间			    </td>
			    <td width="20%" class="itdb">
			    <input id="getJobDate" title="获取职称时间" name="getJobDate" onClick="calendar();" onBlur="notnull(getJobDate,title)"/>
			    <font color="red" size="4">*</font>
			    <a id="getJobDate_1"></a>
			    </td>
			     <td width="12%" class="itda">
			         执业资格证书名称			    </td>
			    <td width="25%" class="itdb">
			     <html:text property="expertpo.em_zhiy_zgmc" styleId="zsmc" title="执业资格证书名称" onblur="notnull(zsmc,title)"></html:text>
			     <font color="red" size="4">*</font>
			     <a id="zsmc_1"></a>
			    </td>
			    <td width="10%" class="itda">
			       家庭地址			    </td>
			    <td width="25%" class="itdb">
			    <html:text property="expertpo.em_home_addr"></html:text>
			    </td>
			    </tr>
			       <tr>
			          <td width="12%" class="itda">
			           家庭电话			    </td>
			    <td width="20%" class="itdb">
			    <html:text property="expertpo.em_home_phone"></html:text>
			    </td>
			     <td width="12%" class="itda">
			         手机号码			    </td>
			    <td width="25%" class="itdb">
			     <html:text property="expertpo.em_mobile" styleId="mobile" title="手机号码" onblur="notnull(mobile,title)"></html:text>
			    <font color="red" size="4">*</font>
			    <a id="mobile_1"></a>
			    </td>
			    <td width="10%" class="itda">
			        单位电话			    </td>
			    <td width="25%" class="itdb">
			    <html:text property="expertpo.em_unit_phone" styleId="phone" title="单位电话" onblur="notnull(phone,title)"></html:text>
			    <font color="red" size="4">*</font>
			    <a id="phone_1"></a>
			    </td>
			
			    </tr>
			     <tr>
			       <td width="12%" class="itda">
			           单位地址			    </td>
			    <td width="20%" class="itdb">
			    <html:text property="expertpo.em_unit_addr"></html:text>
			    </td>
			     <td width="12%" class="itda">
			       单位邮编			    </td>
			    <td width="25%" class="itdb">
			     <html:text property="expertpo.em_unit_postcode"></html:text>
			    </td>
			    <td width="10%" class="itda">
			       状态			    </td>
			    <td width="25%">
			     <html:select property="expertpo.em_exp_status" disabled="true">
			      <html:optionsCollection property="stateList" value="id" label="label" />
			      </html:select>
			    </td>
			    </tr>
			    <tr>
			    <td width="12%" class="itda" >
			        主要工作经历			    </td>
			    <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_gongz_jingl" cols="60" rows="3"></html:textarea>
			    </td>
			     <td width="25%" class="itda" >
			     主要研究成果及业绩			    </td>
			     <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_chengguo" cols="60" rows="3"></html:textarea>
			    </td>
			    </tr>
			    <tr>
			    <td width="12%" class="itda" >
			        参加何种协会及担任何职务			    </td>
			    <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_xiehui" cols="60" rows="3"></html:textarea>
			    </td>
			     <td width="25%" class="itda" >
			        担任何种技术指导及顾问			    </td>
			     <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_guwen" cols="60" rows="3"></html:textarea>
			    </td>
			    </tr>
			     <tr>
			    <td width="12%" class="itda" >
			        评标实践经验			    </td>
			    <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_pingb_jingy" cols="60" rows="3"></html:textarea>
			    </td>
			     <td width="25%" class="itda" >
			         备注			    </td>
			     <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_beizhu" cols="60" rows="3"></html:textarea>
			    </td>
			    </tr>
			     <!-- 
			    <tr >
			   
			    <td class="itda">
			         填报人
			    </td>
			    <td class="itdb" >nd
			    <html:text property="expertpo.em_input_code"></html:text>
			    </td>
			     <td class="itda">
			           填报时间
			    </td>
			    <td class="itdb">
			    <input id="inputDate" title="填报时间"  onclick="calendar();"/>
			    </td>
			     <td class="itda">
			        填报单位  
			    </td>
			    <td class="itdb">
			     <html:text property="expertpo.em_co_code"></html:text>
			    </td>
			    </tr>
			     -->
			     <tr>
			    <td width="12%" class="itda" >
			        挂起/恢复原因（历史）			    </td>
			    <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_remark_content" cols="60" rows="3" disabled="true"></html:textarea>
			    </td>
			     <td width="25%" class="itda" >
			        挂起/恢复原因（本次）			    </td>
			     <td class="itdb" colspan="2">
			     <html:textarea property="expertpo.em_remark_content_new" cols="60" rows="3" disabled="true"></html:textarea>
			    </td>
			    </tr>
			    <tr>
			    <td width="12%" class="itda">评价</td>
			    <td class="itdb" colspan="5">
			    <html:textarea property="expertpo.em_expert_remark" cols="60" rows="3" disabled="true"></html:textarea>
			    </td>     
			    </tr>
			   </table>
			   
			</div>
	</div>
	<div class="group-frame">
				<div class="group-down" onClick="openOrClose('part3')">评标品目明细<a id="expertId_1"></a></div>
			    <div id="part3" style="display: block">
			    <table width="100%"><tr><td>
				<table  border="0" id="qualifiesTab" align="left" style="font-size:14px">
				<tr>
				 <td>序号</td>
				 <td>专家类型代码</td>
				 <td>专家类型名称</td>
				</tr>
				<logic:iterate id="a" name="expertForm" property="list" type="com.ufgov.zc.server.zc.web.form.ExpertActionForm">
				<tr onClick="openwindow(0)">
				<td >
				<bean:write name="a" property="seqNum"/>
				</td>
				<td>
				<html:text property="em_type_code" name="a"></html:text>
				</td>
				<td>
				<html:text property="em_type_name" name="a" readonly="true"></html:text>
				</td>
				</tr>
				</logic:iterate>
				</table>
				</td></tr>
				<tr><td>
				
				<table  cellpadding="1" cellspacing="1" width="100%">
						<tr>
							<td >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="pbutton" style="width:60;" value="添加一行" name="savejz" onClick="addRow('qualifiesTab');"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="pbutton" value="删  除" style="width:60;" name="savejz" onClick="delRow('qualifiesTab','deleteTarger');"/>
								<font size="2">第</font>
								<input style="width:60;" id="deleteTarger" type="text"  onKeyPress="changeBgcolor('qualifiesTab',this.value);"/><font size="2">行(<a id="idFirst">1</a>~<a id="idLast">1</a>)</font>
							</td>
						</tr>
				  </table>
				  </td></tr></table>
				</div>
	</div>
	 <table id="operatorTab"  cellpadding="1" cellspacing="2" align="center">
				<tr>
					<td align="center" colspan="4">
						<br>
						<input type="button" class="pbutton" value="保  存" style="width:100%;height:22px;" onClick="subExpert()"/>
					</td>
				</tr>	
		  </table>
	
   </html:form>
   </td></tr></table>

  </td></tr></table>
  </body>
</html>
