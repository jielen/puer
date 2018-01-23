var voGrid=null;
var svNd = null;
var voSearch = null;
var svUserID = null;

var className = "com.ufgov.zc.service.dataimp.ImportService";
var fieldSeparator = "☆";
var tableName = "ZC_V_B_MERCHANDISE_SUPPLIER";

function setPageInit() {
   // svNd = DataTools.getSV("svNd");
	svUserID = DataTools.getSV("svUserID");
	//alert(svUserID);
    //var voToolbar = PageX.getCtrlObj("toolbar");
   // voToolbar.addListener(new Listener(voToolbar.OnCallClick, eventAnswer_Toolbar_OnCallClick, this));
    
   // var voCataCode = PageX.getCtrlObj("ZC_CATALOGUE_CODE");
   // voCataCode.addListener(new Listener(voCataCode.OnBeforeSelect, eventAnswer_CataCode_OnBeforeSelect, this));   

    voGrid = PageX.getCtrlObj("ZC_V_B_MERCHANDISE_SUPPLIER_Grid");
    voGrid.addListener(new Listener(voGrid.OnRowDblClick, eventAnswer_VouHeadGrid_OnRowDblClick, this));

   // var voStatus = statusbox.oOwner;
   // voStatus.addListener(new Listener(voStatus.OnChange, eventAnswer_status_OnChange, this));

    voSearch = PageX.getCtrlObj("searchbar");

	//voSearch.setValue(" PR_MA_EMP_NUM.AUDIT_STAT = '2'");
	
	voSearch.setAdvanceBtnVisible("false");
    voSearch.search();
    /**
     var asOptions = new Array();
     asOptions[0] = new Array("", "");
     asOptions[1] = new Array("0","编辑");
     asOptions[2] = new Array("1","已审批");
     asOptions[3] = new Array("2","已启用");
     asOptions[4] = new Array("5","上报待审");
     asOptions[5] = new Array("6","审批不通过");
     voStatus.setOptions(asOptions);
     */
}


//搜索改变事件
function eventAnswer_status_OnChange(oSender, sValue, oEvent) {
    var voSearch = PageX.getCtrlObj("searchbar");
    voSearch.search();
}


//Toolbar点击事件
function eventAnswer_Toolbar_OnCallClick(oSender, oCall, oEvent) {
    switch (oCall.id) {
        case "fadd":
            PageX.openBill(null, screen.availWidth, screen.availHeight, null, null, "scrollbars=yes",null,"ZC_B_MERCHANDISE");            
            break;
        case "zc_fxyproject":
            zc_fxyproject();
            break;
        case "fhelp":
            window.open('./help/ZC/ZC_B_MERCHANDISE.htm', 'helpwindow', 'height=506, width=506, top=0, left=506, toolbar=yes, menubar=no, scrollbars=yes,resizable=yes,location=no, status=no');
            break;
        case "fimport": 
            fimportF(); 
            break;
        case "zc_fimport":
            zc_fimportF();
            break;
        case "fdelete":
            fdelete();
            break;
        default:
    }
}


function fdelete(){
    var aiRow = voGrid.getSelectedRowIndexs();
    if (aiRow.length == 0) {
        alert("请先在列表中勾选需要操作的行！");
        return;
    }
    if (!confirm("确定要删除吗？")){
		return;
    }
    var errorInfo = new Array();
    var xmldom = new ActiveXObject("Microsoft.XMLDOM");
    var names = new Array();
    var values = new Array();
    names[0] = "GETDATA";
    var vaoRow = DataTools.getFieldValues(voGrid.getTableName(), aiRow, new Array("ZC_MER_CODE", "ZC_PROJ_ID", "ZC_MER_NAME"));
    var temp = 0;
    for (var i = 0, j = vaoRow.length; i < j; i++) {
        var vsMerCode = vaoRow[i].get("ZC_MER_CODE");
        var vsProjId = vaoRow[i].get("ZC_PROJ_ID");
        values[0] = " select count(*) from ZC_P_REQUEST_ITEM where ZC_MER_CODE ='" + vsMerCode + "' and ZC_PROJ_ID ='" + vsProjId + "'";
        var xmlstr = qryData("ZC_GETDATA", names, values);
        xmldom.loadXML(xmlstr);
        if(xmldom.documentElement.firstChild.firstChild.getAttribute("value") == 0){
            var vvRet = Info.requestData("executeSQL", "ZC_COMMON", new Array("beanId", "methodName", "sql"), new Array("commonDBTools", "executeSQL", "delete from ZC_B_MERCHANDISE where ZC_MER_CODE ='" + vsMerCode + "' and ZC_PROJ_ID ='" + vsProjId + "';delete from ZC_B_MER_PRICE where ZC_MER_CODE ='" + vsMerCode + "' and ZC_PROJ_ID ='" + vsProjId + "'"));
            if (vvRet == null) {
                errorInfo[temp] = "名称为 [" + vaoRow[i].get("ZC_MER_NAME") + "] 的商品删除失败原因是:作废操作返回值为null,可能出现异常.\n";
                temp++;
                continue;
            }
            var vtSuccess = false;
            var voInfo = vvRet.selectSingleNode("info");
            if (voInfo == null) voInfo = PF.parseXml(vvRet.text);
            if (voInfo == null) {
                errorInfo[temp] = "名称为 [" + vaoRow[i].get("ZC_MER_NAME") + "] 的商品删除失败原因是:返回消息格式异常,不能被正常解析.\n";
                temp++;
                continue;
            } else {
                vtSuccess = PF.parseBool(voInfo.getAttribute("success"));
                if (!vtSuccess) {
                    errorInfo[temp] = "名称为 [" + vaoRow[i].get("ZC_MER_NAME") + "] 的商品删除失败原因是:"+voInfo.selectSingleNode("message").text+"\n";
                    temp++;
                    continue;
                }
            }
        } else {
            errorInfo[temp] = "名称为 [" + vaoRow[i].get("ZC_MER_NAME") + "] 的商品已经在商品信息中使用，不能删除!\n";
            temp++;
            continue;
        }
    }

    if(errorInfo.length > 0){
        var vsErrorInfo = new StringBuffer();
        vsErrorInfo.append("删除成功");
        vsErrorInfo.append(vaoRow.length-errorInfo.length);
        vsErrorInfo.append("条记录，");
        vsErrorInfo.append("删除失败");
        vsErrorInfo.append(errorInfo.length);
        vsErrorInfo.append("条记录，失败原因：\n");
        for(var m = 0, n = errorInfo.length; m < n; m++){
            vsErrorInfo.append(errorInfo[m]);
        }
        alert(vsErrorInfo.toString());
    }else{
        alert("删除成功!");
    }
    if(errorInfo.length != vaoRow.length){
         voSearch.search();
    }
}


function eventAnswer_CataCode_OnBeforeSelect(oSender, oEvent) {
    var sSQL = " ZC_B_CATALOGUE.ZC_YEAR ='" ;//+ svNd + "'";
    oSender.sBeforeCond = sSQL;
}


function fimportF(){
    var vavArg = new Array();
    vavArg[0]= window;
    vavArg[1]= className;
    vavArg[2]= fieldSeparator;

    var paramStr = new StringBuffer();
    paramStr.append("&className="+className);
    //paramStr.append("&fieldSeparator="+fieldSeparator);
    paramStr.append("&tableName="+tableName);
    paramStr.append("&titleLineNum="+2);
    paramStr.append("&key1=ZC_MER_CODE");
    paramStr.append("&key2=ZC_PROJ_ID");

    var vasRet = window.showModalDialog(PageX.sRootPath + "/jsp/ZC/FileBox_SelectFile2.jsp?"+paramStr.toString(), vavArg, "dialogWidth:400px; dialogHeight:130px; center:yes; resizable:no; status: no; scroll:no; help: no");
	if(vasRet == "ok"){
		voSearch.search();
	}else{
	    return false;
	}
}


function zc_fimportF(){
    var vavArg = new Array();
    vavArg[0]= window;
    vavArg[1]= className;
    vavArg[2]= fieldSeparator;

    var paramStr = new StringBuffer();
    paramStr.append("&className="+className);
    //paramStr.append("&fieldSeparator="+fieldSeparator);
    paramStr.append("&tableName=ZC_V_B_MERCHANDISE_SUPPLIER");
    paramStr.append("&titleLintableNameeNum="+2);
    paramStr.append("&key1=ZC_MER_CODE");
    paramStr.append("&key2=ZC_PROJ_ID");
    paramStr.append("&key3=ZC_SU_CODE");

    var vasRet = window.showModalDialog(PageX.sRootPath + "/jsp/ZC/FileBox_SelectFile2.jsp?"+paramStr.toString(), vavArg, "dialogWidth:400px; dialogHeight:130px; center:yes; resizable:no; status: no; scroll:no; help: no");
	if(vasRet == "ok"){
		voSearch.search();
	}else{
	    return false;
	}
}


//Grid双击事件
function eventAnswer_VouHeadGrid_OnRowDblClick(oSender, oRow, oEvent) {
    if (oRow == null) {
        return;
    }
    var viRow = oRow.rowIndex;
    var vsMerCode = voGrid.getValueByRowField(viRow, "ZC_MER_CODE");
    
    var vsMerCondition = "( ZC_V_B_MERCHANDISE_SUPPLIER.ZC_SU_CODE='"+svUserID+"' and ZC_V_B_MERCHANDISE_SUPPLIER.ZC_MER_CODE='"+vsMerCode+"')";
	PageX.openBill(vsMerCondition, screen.availWidth, screen.availHeight, null, null, "scrollbars=yes",null,"ZC_MER_SUP");
}


function eventAnswer_voCatalogueCode_OnAfterSelect(oSender, sValue, oEvent) {
    var vsItemIndex = oSender.getOuterObjCurRowX();
    var vsCatalogueCodeBox = voGridTpinmuVal.getValueByRowField(vsItemIndex, "ZC_CATALOGUE_CODE");
    var vsCatalogueYearBox = voGridTpinmuVal.getValueByRowField(vsItemIndex, "ZC_YEAR");
    if (vsCatalogueCodeBox != "" && vsCatalogueYearBox != "") {
        var names = new Array("GETDATA");     
        var values = new Array(" select distinct ZC_IS_USED from ZC_B_CATALOGUE where ZC_CATALOGUE_CODE = '" + vsCatalogueCodeBox + "' and ZC_YEAR = '" + vsCatalogueYearBox + "'");
	    var xmlstr = qryData("ZC_GETDATA", names, values);
	    var xmldom = new ActiveXObject("Microsoft.XMLDOM");
	    xmldom.loadXML(xmlstr);
	    var isUsed = xmldom.documentElement.firstChild.firstChild.getAttribute("value");
	    if(isUsed=='N'||isUsed==''||isUsed==null){
	    	voGridTpinmuVal.setValueByRowField(vsItemIndex, "ZC_CATALOGUE_CODE",'');
		    voGridTpinmuVal.setValueByRowField(vsItemIndex, "ZC_CATALOGUE_NAME",'');
		    voGridTpinmuVal.setValueByRowField(vsItemIndex, "ZC_YEAR",'');
		    alert("对不起,请您选择详细的采购品目!");
	    }
    }
}


//协议采购立项,根据选定的商品来初始化协议采购的界面
function zc_fxyproject() {
    var mercodes = "";
    var projIDs="";
    var errorinfo = "";
    var vaoRow = DataTools.getFieldValues(voGrid.getTableName(),voGrid.getSelectedRowIndexs(), new Array("ZC_MER_CODE", "ZC_MER_STATUS","ZC_PROJ_ID"));
    if (vaoRow.length == 0) {
        alert("请先选择本次做采购立项处理的商品!");
        return false;
    }
    if (!confirm("确定要将您选中的商品放在一个采购项目中执行吗？"))return;

    for (var j = 0; j < vaoRow.length; j++) {
        var vmoFieldValue = vaoRow[j];
        if (vmoFieldValue.get("ZC_MER_STATUS") != "2") { //todo edit the status of the merstatus
            errorinfo = errorinfo + "所选用的商品必须为[生效]状态！\n";
        } else {
            mercodes = mercodes + vmoFieldValue.get("ZC_MER_CODE") + ",";
            projIDs = projIDs + vmoFieldValue.get("ZC_PROJ_ID") + ",";

        }
    }

    errorinfo = errorinfo.substring(0, errorinfo.length - 1);
    mercodes = mercodes.substring(0, mercodes.length - 1);
    if (errorinfo.length != 0) {
        if (mercodes.length == 0) {
            alert("所选用的商品不是生效状态的商品,不能立项！");
            return false;
        }
        if (!confirm("下列商品\n" + errorinfo + "\n不是可用状态！\n但如下商品\n" + mercodes + "\n的是生效的,是否继续？"))return false;
    }
    var vsURL = "Proxy?function=geteditpage&condition=1%3D0&ZC_REQ_FROM=0&zcMerIds=" + mercodes + "&zcProjIds="+projIDs+"&componame=ZC_P_REQUEST&fieldvalue=ZC_P_REQUEST_E";

    var vsStyle = "height=" + screen.availHeight + ","
            + "width=" + screen.availWidth + ","
            + "top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no";

    window.open(vsURL, '_blank', vsStyle).focus();
}