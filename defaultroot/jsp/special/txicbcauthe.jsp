<%--
	桐乡项目，工行用来读取UK中用户认证信息的js函数
--%>
<script type="text/javascript">
	//检查是否插卡
	function checkCard() {
		var flag = CtrlSPK.CheckCard();
		return flag;
	}
	
	//获取用户信息:11位客户号+3位代表号
	function readUserInfo(pin) {
		var userInfo = "";
		var userNo = "";
		var delegateNo = "";
		
		var flag = CtrlSPK.ReadData(1, pin, 124, 20);
		if (flag == "0") {
			userNo = CtrlSPK.GetResult();
		} else {
			userNo = "error";
		}
		flag = CtrlSPK.ReadData(1, pin, 144, 5);
		if (flag == "0") {
			delegateNo = CtrlSPK.GetResult();
		} else {
			delegateNo = "error";
		}
		return userNo + delegateNo;
	}
	
	//获取签名信息
	function signData(pin, data) {
		var flag = CtrlSPK.SignData(1, pin, data, data.length);
		if (flag == "0") {
			return CtrlSPK.GetResult();
		} else {
			return "error";
		}
	}
	
	//读取证书信息
	function readCerInfo(pin) {
		var flag = CtrlSPK.ReadData(1, pin, 0, 159);
		if (flag == "0") {
			return CtrlSPK.GetResult();
		} else {
			return "error";
		}
	}
	
	//读取公钥信息
	function readPublicKey() {
		var flag = CtrlSPK.ReadPulicKey(1);
		if (flag == "0") {
			return CtrlSPK.GetResult();
		} else {
			return "error";
		}
	}
	
	//读取发证机构签名信息
	function readKeyInfo(pin) {
		var flag = CtrlSPK.ReadData(1, pin, 287, 128);
		if (flag == "0") {
			return CtrlSPK.GetResult();
		} else {
			return "error";
		}
	}
	
	//修改pin码
	function changePin(oldPin, newPin) {
		var flag = CtrlSPK.ChangePIN(oldPin, newPin);
		if (flag == "0") {
			return "0";
		} else {
			return "error";
		}
	}
</script>
<OBJECT ID="CtrlSPK" CLASSID="CLSID:1FAC4FD1-9B6B-47E8-BA0F-6DEAC603418B" 
	CODEBASE= "/GK/jsp/special/GDSrvZJ.dll#version=1,0,0,2" width="35" height="25"></OBJECT>