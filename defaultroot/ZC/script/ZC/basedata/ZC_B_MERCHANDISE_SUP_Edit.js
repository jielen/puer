var svUserID = null;
var svUserName = null;
var svCoCode = null;
var svNd     = null;
var voToolbar = null;
var voFree = null;
var voGridItem   = null;
var zcMerPrice =null;//市场价格ZC_MER_M_PRICE

function setPageInit() {
    var voRM = PageX.getRowManager();
    voRM.setValidChanged(false);
    svUserID = DataTools.getSV("svUserID");
   // svUserName = DataTools.getSV("svUserName");
   // svCoCode = DataTools.getSV("svCoCode");
  //  svNd         = DataTools.getSV("svNd");
    voToolbar = PageX.getCtrlObj("toolbar");
    voFree = PageX.getFree("ZC_V_B_MERCHANDISE_SUPPLIER");

    voGridItem = PageX.getCtrlObj("ZC_B_MER_DISCOUNT_WEB_Grid");
	voGridItem.addListener(new Listener(voGridItem.OnAfterUpdate,eventAnswer_voGridItem_OnAfterUpdate,this));
	voGridItem.addListener(new Listener(voGridItem.OnAfterInsertRow,eventAnser_voGridItem_OnAfterInsertRow,this));

   // var voMerPic = ZC_MER_PIC.oOwner;
   // var vsMerpicBlobid = voFree.getValue("ZC_MER_PIC_BLOBID");
   // if(vsMerpicBlobid != null && "" != vsMerpicBlobid){
  //      voMerPic.setFileId(vsMerpicBlobid);
   // }
    //var voSuNo = voGridItem.getEditBox("ZC_SU_CODE");
    //voSuNo.addListener(new Listener(voSuNo.OnBeforeSelect, eventAnswer_SuCode_OnBeforeSelect, this));
    //voSuNo.addListener(new Listener(voSuNo.OnAfterSelect, eventAnswer_SuCode_OnAfterSelect, this));
    
   // var voProjCode = PageX.getCtrlObj("ZC_PROJ_ID");
    //voProjCode.addListener(new Listener(voProjCode.OnBeforeSelect,eventAnswer_voProjCode_OnBeforeSelect, this));
    
   // var voBraCode = PageX.getCtrlObj("ZC_BRA_CODE");
    //voBraCode.addListener(new Listener(voBraCode.OnBeforeSelect,eventAnswer_voBraCode_OnBeforeSelect, this));
    
   // var voCataCode = PageX.getCtrlObj("ZC_CATALOGUE_CODE");
   // voCataCode.addListener(new Listener(voCataCode.OnBeforeSelect, eventAnswer_CataCode_OnBeforeSelect, this));   
	
	//var voZC_TREATY_PRICE = PageX.getCtrlObj("ZC_TREATY_PRICE");
	//voZC_TREATY_PRICE.addListener(new Listener(voZC_TREATY_PRICE.NumericBox_eventAnswer_OnChange, eventAnswer_voZC_TREATY_PRICE,this));
    
    //添加市场单价监听，如果市场单价变化 
    
    voToolbar.addListener(new Listener(voToolbar.OnCallClick, eventAnswer_Toolbar_OnCallClick, this));
    if (PageX.isNew()) {
       // voFree.setValue("ZC_DIYU_DAIMA",optVsDiyu);
        //voFree.setValue("ZC_YEAR",svNd);
		
        set_new_page_default_value();
    } else {
        voFree.setReadOnly(true);                // 设置Free对象为只读属性; 
        voGridItem.setReadOnly(true);           // 设置voGridItem对象为只读属性
        //voToolbar.setCallDisabled("frpedit", true); // 设置frpedit按钮为不可用状态   
        var ZC_MER_STATUS = PageX.getCtrlObj("ZC_MER_STATUS");

	  	if(ZC_MER_STATUS.getValue() == "0"){ //编辑状态
			voToolbar.setCallVisible("frpedit",true);
			voToolbar.setCallDisabled("frpsave", true);
            //voToolbar.setCallVisible("fdelete",true);  	  
	  	}else if(ZC_MER_STATUS.getValue() == "2"){ //生效
	  		voToolbar.setCallVisible("frpedit",true);
			voToolbar.setCallDisabled("frpsave", true);
            //voToolbar.setCallVisible("fdelete",false);
	  	}else if(ZC_MER_STATUS.getValue() == "3"){ //停用
	  		voToolbar.setCallVisible("frpedit",false);
            //voToolbar.setCallVisible("fdelete",false);
	  	}else if(ZC_MER_STATUS.getValue() == "4"){ //作废
	  		voToolbar.setCallVisible("frpedit",false);
	  	}
    }
	//var rowCount = voGridItem.getRowCount();
	//var zcMerPrice = PageX.getCtrlObj("ZC_MER_M_PRICE").getValue();
	//for(var iRowIndex=0; iRowIndex < rowCount; iRowIndex++ ){
	//	var zcDiscountRate = voGridItem.getValueByRowField(iRowIndex,"ZC_TREATY_DISCOUNT_RATE");
	//	var price=zcMerPrice*(100-zcDiscountRate)/100;
	//	voGridItem.setValueByRowField(iRowIndex,"ZC_TREATY_PRICE",price);
	//}
	voRM.setValidChanged(true);
}
//计算采购价格 this, iRowIndex, sFieldName, vsValue, vsOldValue
function eventAnswer_voGridItem_OnAfterUpdate(){

	zcMerPrice = PageX.getCtrlObj("ZC_MER_M_PRICE").getValue();
	

	//oSender，iRowIndex, sFieldName，sValue，sOldValue；

	var zcDiscountRate = voGridItem.getValue("ZC_TREATY_DISCOUNT_RATE");
	var price=zcMerPrice*(100-zcDiscountRate)/100;
	voGridItem.setValue("ZC_TREATY_PRICE",price);
	//alert(sOldValue);alert(sValue);
	
	
}
 function eventAnser_voGridItem_OnAfterInsertRow(){
	voGridItem.setValue("ZC_SU_CODE",DataTools.getSV("svUserID"));
	voGridItem.setValue("ZC_TREATY_UPPER_LIMIT",0);
	voGridItem.setValue("ZC_TREATY_LOWER_LIMIT",0);
	voGridItem.setValue("ZC_TREATY_DISCOUNT_RATE",0);
 }
function eventAnswer_voZC_TREATY_PRICE(){
	
}

function set_new_page_default_value() {
	voFree.setReadOnly(false);              // 设置Free对象为可读写;	
	voGridItem.setReadOnly(false);
	voToolbar.setCallDisabled("frpedit", true);// 设置frpedit按钮为不可用状态
	//voFree.setValue("GBSTAT_CODE","0");

	voToolbar.setCallDisabled("frpedit", false);
	voToolbar.setCallDisabled("fdelete", true);

//fill_in_Input_Info(voFree,svUserID,svUserName);
	//voFree.setFieldReadOnly("AUT_RESN",true);
}


function is_used() {
	var sPROV_CODE = voFree.getValue("PROV_CODE");
	if (sPROV_CODE == "") {
	   alert("供应商代码不能为空.");
		return true;
	}
	var names = new Array("PROV_CODE");
	var values = new Array(sPROV_CODE);
	var result = qryData("GB_GET_PROV_CODE", names, values);
	var xmldom = new ActiveXObject("Microsoft.XMLDOM");
	xmldom.loadXML(result);
	return xmldom.documentElement.firstChild.firstChild.getAttribute("value") != "0";
}

function eventAnswer_SuCode_OnBeforeSelect(oSender,sValue,oEvent){
	 var sSQL=" ZC_B_SUPPLIER.ZC_SU_STATUS = '2'";
	 oSender.sBeforeCond = sSQL; 
}

function eventAnswer_voProjCode_OnBeforeSelect(oSender,oEvent){
	var sSQL = " ZC_XYGH_ZBJG.ZC_PROJ_STATUS = '02'";
	oSender.sBeforeCond = sSQL; 
}

function eventAnswer_voBraCode_OnBeforeSelect(oSender,oEvent){
	var vlProjCode = voFree.getValue("ZC_PROJ_ID");
	if(vlProjCode==null||vlProjCode==""){
		alert("请先选择[货物批次代码]!");
		oSender.abortEvent(true);
		return;
	}else{
		//var sSQL = " ZC_ZB_PINP.ZC_PROJ_ID = '"+vlProjCode+"' and ZC_ZB_PINP.ZC_ZBPP_STATUS = '02'";
		var sSQL = " ZC_ZB_PINP.ZC_ZBPP_STATUS = '02'";
		oSender.sBeforeCond = sSQL;
	}
}

function eventAnswer_CataCode_OnBeforeSelect(oSender, oEvent) {
    var sSQL = " ZC_B_CATALOGUE.ZC_YEAR ='" + svNd + "'";
    oSender.sBeforeCond = sSQL;
}

//供应商不能重复
function eventAnswer_SuCode_OnAfterSelect(oSender,oEvent){
    var vsItemIndex = oSender.getOuterObjCurRowX();
    var vsSuNo = voGridItem.getValueByRowField(vsItemIndex,"ZC_SU_CODE");
    for(var j=0,suCount=voGridItem.getRowCount();j<suCount;j++){
        if(j != vsItemIndex){
            var vsTempSuNo = voGridItem.getValueByRowField(j,"ZC_SU_CODE");
            if(vsTempSuNo == vsSuNo){
                alert("[供应商价格] 中 [供应商编号] 不能重复！");
                clearGridItemRow(vsItemIndex);
                return;
            }
        }
    }
    var voSelectResult = oSender.getSelectResult();
    var vasFieldNames = voSelectResult[0];
    var vasFieldValues = voSelectResult[1][0];
    var vsDiyuCode = vasFieldValues[getFieldIndex(vasFieldNames, "ZC_DIYU_DAIMA")];
    voGridItem.setValueByRowField(vsItemIndex, "ZC_DIYU_DAIMA",vsDiyuCode);    
}


function clearGridItemRow(rowIndex){
    for(var i=0,count=voGridItem.getColCount();i<count;i++){
        var voCol = voGridItem.getCol(i);
        if("NumericBox" == voCol.editboxtype){
            voGridItem.setValueByRC(rowIndex,i,0.00);
        }else{
            voGridItem.setValueByRC(rowIndex,i,"");
        }
    }
}


// 菜单"增加"功能
function fadd() {
    PageX.newBill();
    set_new_page_default_value();
}


//删除品牌，需要去校验 ZC_P_REQUEST_ITEM（采购申请子表） 两个表是否有引用此条数据:
function before_fdelete(){
    var xmldom = new ActiveXObject("Microsoft.XMLDOM");
    var names = new Array();
    var values = new Array();
    names[0] = "GETDATA";
    values[0] = " select count(*) from ZC_P_REQUEST_ITEM where ZC_MER_CODE ='" + voFree.getValue("ZC_MER_CODE") + "' and ZC_PROJ_ID ='" + voFree.getValue("ZC_PROJ_ID") + "'";
    var xmlstr = qryData("ZC_GETDATA", names, values);
    xmldom.loadXML(xmlstr);
	if(xmldom.documentElement.firstChild.firstChild.getAttribute("value") != "0"){
		alert("此商品信息已经在协议采购中使用，不能删除！");
		return false;
	}
	return true;
}

//----------------------------------------------------------------------
// 菜单"删除"功能
function fdelete() {
    if(before_fdelete()==true){
        var vvRet = PageX.deleteBill();
        if (vvRet) {
            openerListReload();
            PageX.newBill();
            set_new_page_default_value();
    	    alert("删除成功");
        } else {
    	    alert("删除失败,失败的原因事: \n" + vvRet);
        }
    }
}


function checkEmpty(){
	var voMap= new Map();
	voMap.put("ZC_B_MERCHANDISE","协议供货商品");
	voMap.put("ZC_MER_CODE","商品编号");
	voMap.put("ZC_MER_NAME","商品名称");
	voMap.put("ZC_DIYU_DAIMA","地域代码");
	voMap.put("ZC_MER_SPEC","型号规格");
	voMap.put("ZC_CATALOGUE_CODE","品目代码");
	voMap.put("ZC_BRA_CODE","品牌代码");
	voMap.put("ZC_B_MER_PRICE","商品供应商和价格");
	voMap.put("ZC_MER_M_PRICE","市场单价");
	voMap.put("ZC_PROJ_ID","货物批次代码");
	voMap.put("ZC_MER_IS_ATTACH","是否配件");
	voMap.put("ZC_IS_JNJS","是否节能节水产品");
	voMap.put("ZC_IS_SHARED","是否外网共享商品");
	voMap.put("ZC_MER_IS_ZZCX","是否自主创新产品");
	voMap.put("ZC_MER_IS_LSHB","是否绿色环保产品");
	var va2xResult= PageX.checkEmptyValue(voMap);
	if ( !va2xResult[0]){
		alert(va2xResult[1]);
		return false;
	}
	return true;
}


function beforSave() {
    if (!checkEmpty()) {
              return false;
    } else {
    	for(var i=0;i<voGridItem.getRowCount();i++){
    		var quota_one = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_XIANE1"));
    		var rate_one = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_RATE1"));
    		var quota_two = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_XIANE2"));
    		var rate_two = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_RATE2"));
    		var quota_three = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_XIANE3"));
    		var rate_three = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_RATE3"));
    		var quota_four = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_XIANE4"));
    		var rate_four = PF.parseFloat(voGridItem.getValueByRowField(i,"ZC_DIS_RATE4"));
    		if(quota_one<PF.parseFloat("0.00")){
    			alert("折扣限额不能小于0！");
    			return false;
    		}
    		if(quota_two!=0&&quota_one>quota_two){
    			alert("折扣限额要递增输入！");
    			return false;
    		}
    		if(quota_three!=PF.parseFloat("0.00") && quota_two>quota_three){
    			alert("折扣限额要递增输入！");
    			return false;
    		}
    		if(quota_four!=PF.parseFloat("0.00")&&quota_three>quota_four){
    			alert("折扣限额要递增输入！");
    			return false;
    		}
    		if(rate_two!=PF.parseFloat("0.00")&&rate_one<rate_two){
    			alert("折扣率要递减输入！");
    			return false;
    		}
    		if(rate_three!=PF.parseFloat("0.00")&&rate_two<rate_three){
    			alert("折扣率要递减输入！");
    			return false;
    		}
    		if(rate_four!=PF.parseFloat("0.00")&&rate_three<rate_four){
    			alert("折扣率要递减输入！");
    			return false;
    		}
    	}
        return true;
    }
}


function checkNum(){
            if(PF.parseFloat(voGrid.getValue("ZC_DIS_MAX"))<=PF.parseFloat(voGrid.getValue("ZC_DIS_MIN"))){
  			     alert( "价格下限不能大于等于价格上限! ") ;
  			     return false;
      		}if(PF.parseFloat(voGrid.getValue("ZC_DIS_MAX"))<PF.parseFloat(voFree.getValue("ZC_MER_M_PRICE"))){
      		     alert( "价格上限不能小于市场单价! ") ;
  			     return false;
      		}else return true;
}


// 新增按钮点击后的工具栏状态
function after_fadd_new(voToolbar) {
    voToolbar.setCallDisabled("frpedit",false); 
	voToolbar.setCallDisabled("fdelete",false);
	voToolbar.setCallDisabled("frpedit",true);
	voToolbar.setCallDisabled("fstop",false);
	voToolbar.setCallDisabled("fstart",false);
	voToolbar.setCallVisible("fstop",false);
	voToolbar.setCallVisible("fstart",true);  	
	voToolbar.setCallVisible("fcanceled",false);
    voGridItem.setReadOnly(true);
}


// 菜单"保存"功能
function after_frpedit() {
	// 如果数据没有发生变化
    if (!PageX.isChanged()) {
        voFree.setReadOnly(true); // 设置主表为只读属性			  			   		   
        //voGrid.setReadOnly(true);
        after_fadd_new(voToolbar);
		alert("没有发生更改,不用保存!");
    } else { // 如果数据发生变化
        var vvRet = PageX.saveBill();
        if (vvRet) {
            voFree.setReadOnly(true); // 设置主表为只读属性			  			   		   
            //voGrid.setReadOnly(true);
            after_fadd_new(voToolbar);
      		openerListReload();            
		    alert("保存成功!");
        } else {
            alert(vvRet);
        }
    }
}


/*
function faudit_y(){    	
	var sCompo="GB_MODL_AGR_AUT";
	var names = ["BILL_CODE", "AUDIT_EMP_CODE", "AUDIT_EMP_NAME","CO_CODE"];
	var values = [voFree.getValue("GB_LIST_CODE"),svUserID,svUserName,svCoCode];

	var result = Info.requestData("faudit_y",sCompo,names,values,null); 
	if(result == null){
	   alert("审批通过失败!");
	   return;
	}

    if (result.getAttribute("success") == "false"){
		alert("审批通过失败,失败的原因是:n" + result.firstChild.text);
		return;
    }
	else{
	   fill_in_Audit_Info(voFree,svUserID,svUserName);
	   voFree.setValue("GBSTAT_CODE","2"); 				 
	   PageX.getRowManager().clearAll();
	   alert("审批通过成功!");			 					
	} 	
}
*/


//----------------------------------------------------------------------
// 菜单"审批不通过"功能
/*
function faudit_n(){
   
   var sCompo="GB_MODL_AGR_AUT";
   var names = ["BILL_CODE", "AUDIT_EMP_CODE", "AUDIT_EMP_NAME","CO_CODE"];
   var values = [voFree.getValue("GB_LIST_CODE"),svUserID,svUserName,svCoCode];

   var result = Info.requestData("faudit_n",sCompo,names,values,null); 
   if(result == null){
	   alert("审批不通过失败!");
	   return;
   }

   if (result.getAttribute("success") == "false"){
		alert("审批不通过失败,失败的原因是:n" + result.firstChild.text);
		return;
   }
   else{
	   fill_in_Audit_Info(voFree,svUserID,svUserName);
	   voFree.setValue("GBSTAT_CODE","4"); 
				 
	   PageX.getRowManager().clearAll();
	   alert("审批不通过成功!");			 					
	} 	  
}
*/


function fcanceledF()
{
 
   if (vvRet) {
		   openerListReload();
	       voToolbar.setCallVisible("frpedit",false);
           //voToolbar.setCallVisible("fdelete",false);
	     //  voToolbar.setCallVisible("fstop",false);
           //voToolbar.setCallVisible("fstart",false);
           voToolbar.setCallVisible("fcanceled",false);
	}
	else{
	   alert("作废失败 ,失败的原因是: \n" + vvRet);
	}

}

//停用
function fstopF(){
   if(!confirm("是否要停用商品?")) return ;
   var ZC_MER_STATUS = PageX.getCtrlObj("ZC_MER_STATUS");
   ZC_MER_STATUS.setValue("3");
   //var voRM = PageX.getRowManager();
   //voRM.setDoBusinessOnSave("com.ufgov.zc.event.BaMerEvent", "stop,"+voFree.getValue("ZC_PROJ_ID"));
   var vvRet= PageX.saveBill();	   
   if (vvRet) {
		   openerListReload();
	       alert("停用成功!");
	       voToolbar.setCallVisible("frpedit",false);
           voToolbar.setCallVisible("frpedit",false);
           //voToolbar.setCallVisible("fdelete",false);
	       voToolbar.setCallVisible("fstop",false);
           voToolbar.setCallVisible("fstart",true);
           voToolbar.setCallVisible("fcanceled",true);
	}
	else{
	   alert("停用失败 ,失败的原因是: \n" + vvRet);
	}
}

//生效
function fstartF(){
	if(!confirm("是否要生效商品?")) return ;
  	var projStatus = voFree.getValue("ZC_PROJ_STATUS");
  	var brandStatus = voFree.getValue("ZC_ZBPP_STATUS");
  	if(projStatus!=02&&brandStatus!=02){
  		alert("请先生效该商品的批次和品牌！");
  		return;
  	}
	var ZC_MER_STATUS = PageX.getCtrlObj("ZC_MER_STATUS");
	ZC_MER_STATUS.setValue("2");
	//var voRM = PageX.getRowManager();
	//voRM.setDoBusinessOnSave("com.ufgov.zc.event.BaMerEvent", "start,"+voFree.getValue("ZC_PROJ_ID"));
    var vvRet= PageX.saveBill();	   
    if (vvRet) {
		   openerListReload();
	       alert("生效成功!");
	       //voToolbar.setCallVisible("frpedit",false);
           //voToolbar.setCallVisible("frpedit",false);
           //voToolbar.setCallVisible("fdelete",false);
	       voToolbar.setCallVisible("fstop",true);
           voToolbar.setCallVisible("fstart",false);	
           voToolbar.setCallVisible("fcanceled",false);
	}
	else{
	   alert("生效失败 ,失败的原因是: \n" + vvRet);
	}
}

function eventAnswer_Toolbar_OnCallClick(oSender, oCall, oEvent) {
    //var sGBSTAT_CODE = voFree.getValue("GBSTAT_CODE");    
	
    switch (oCall.id) {
      case "fadd":
        fadd();
        break;
    //-----------------------修改-----------------------------
      case "frpedit":
      //  voToolbar.setCallDisabled("fprn_tpl_set", true);
      //  voToolbar.setCallDisabled("fprint", true);
       // voToolbar.setCallDisabled("fdelete", true);
       // voToolbar.setCallDisabled("frpedit", false);
        voToolbar.setCallDisabled("frpedit", true);
		voToolbar.setCallDisabled("frpsave",false);
       // voFree.setReadOnly(false);
        //voGrid.setReadOnly(false);
        voFree.setFieldReadOnly("ZC_MER_CODE", true);
		voFree.setFieldReadOnly("ZC_IS_SHARED",true);
		voFree.setFieldReadOnly("ZC_MER_IS_ZZCX",true);
		voFree.setFieldReadOnly("ZC_MER_IS_LSHB",true);
		voFree.setFieldReadOnly("ZC_IS_JNJS",true);
		voFree.setFieldReadOnly("ZC_MER_PIC",true);
        voGridItem.setReadOnly(false);
        break;
    //-----------------------删除-----------------------------
      case "fdelete":
        //if (sGBSTAT_CODE != "0") {
	    //    alert("只能删除编辑状态的表单");
        //    break;
        //}

	    //var result = is_used();
	    //if(result){
	    //   alert("已使用的供应商不能删除.");
	    //break;
	    //}
	  
	    if (!confirm("确定删除？")) break;
        fdelete();
        break;
    //-----------------------保存-----------------------------
      case "frpedit":
        //if (sGBSTAT_CODE != "0") {
		//    alert("只能保存编辑状态的表单");
        //    break;
        //}
        if (!beforSave()) {
            break;
        }
        after_frpedit();
        break;
         //-----------------------作废-----------------------------
	    case "fcanceled":
	    	fcanceledF();
	    	break;
	
		//-----------------------生效-----------------------------
	    case "fstart":
	    	fstartF();
	    	break;
		//-----------------------停用-----------------------------
	    case "fstop":
	    	fstopF();
	    	break;    	    	
	    
    //-----------------------审核-----------------------------
        /*case "faudit_y":
	   if (sGBSTAT_CODE != "0"){
		   alert("只能审批编辑状态的单据!");
           break;
	   }
	   if (!confirm("确定审批通过？")) break;	   	   
	   faudit_y();
	 
       break;
	//-----------------------销审-----------------------------
	case "faudit_n":
        if (sGBSTAT_CODE != "0"){
		   alert("只能审批编辑状态的单据!");
           break;
		}		      		
		if (!confirm("确定审批不通过？")) break;        		
        
		faudit_n();
 
        break;
   */     
   //-----------------------启用-----------------------------
   //   case "fstart":
   //     if (sGBSTAT_CODE != "1") {
   //         alert("只能启用审批通过的单据!");
   //         break;
   //     }		      		
   //	  if (!confirm("确定启用吗？")) break;
   //     fstart();
   //     break;
	//-----------------------操作记录-----------------------------
    //  case "fop_log":
    //    var billType = "26";
    //    var listNo = voFree.getValue("GB_LIST_CODE");
    //    var sCon = "GB_BILL_NO='" + listNo + "' and GB_BILL_TYPE='" + billType + "'";
    //    setShowOpLog(sCon);
    //    break;	
	 case "frpsave":
    	//-----------------------保存-----------------------------
		after_fsave();
      break;	
    
    //-----------------------退出-----------------------------
      case "fexit":
        window.close();
        break;
    //-----------------------打印-----------------------------
      case "fprint":
	   PrintX.fprint();
	   break;
	//-----------------------打印设置-----------------------------
	   case "fprn_tpl_set":
	   PrintX.fdynamicPrintSet();
	   break;
    //-----------------------缺省-----------------------------
      default:
     
    }
    
}

//求交集
function checkIntersection(){
	var rowCount = voGridItem.getRowCount();
	if(rowCount > 1){
			for(var iRowIndex=0; iRowIndex < rowCount-1; iRowIndex++ ){
				var lowerLimit = voGridItem.getValueByRowField(iRowIndex,"ZC_TREATY_LOWER_LIMIT");
				var upperLimit = voGridItem.getValueByRowField(iRowIndex,"ZC_TREATY_UPPER_LIMIT");
				for(var rowCur=iRowIndex+1; rowCur < rowCount; rowCur++ ){
				var left = voGridItem.getValueByRowField(rowCur,"ZC_TREATY_LOWER_LIMIT");//低节点
				var right = voGridItem.getValueByRowField(rowCur,"ZC_TREATY_UPPER_LIMIT");//高节点
				if( (left < lowerLimit && right < lowerLimit) || ( left > upperLimit)){
					continue;
				}else {
					return false;
				}
			}
		}
		return true;
	}else{
	return true;
	}
}
function checkOrder(){
	var rowCount = voGridItem.getRowCount();
	for(var iRowIndex=0; iRowIndex < rowCount; iRowIndex++ ){
		var lowerLimit = voGridItem.getValueByRowField(iRowIndex,"ZC_TREATY_LOWER_LIMIT");
		var upperLimit = voGridItem.getValueByRowField(iRowIndex,"ZC_TREATY_UPPER_LIMIT");
		if(lowerLimit >= upperLimit){
			return false;
		}else{
			return true;
		}
	}
}
//上限要大于下限
function after_fsave(){
		if(!checkOrder()){
			alert("协议数量下限不能大于上限");
			return false;
		}
		if(!checkIntersection()){
			alert("协议数量的上下限范围不能重复");
			return false;
		}
		var resultInfo = PageX.saveBill();
		if (resultInfo)
		{
			alert("保存成功");
			voFree.setReadOnly(true); // 设置主表为只读属性		
			voToolbar.setCallDisabled("frpsave", true);
			voToolbar.setCallDisabled("frpedit",false);
			openerListReload();					
		}
		else
		{
			  alert("保存失败原因为: \n" + resultInfo);
		}   
	
}
window.onbeforeunload = function closeEditPage(){
    if(PageX.isChanged()) event.returnValue="当前页面上的数据已经修改,但是没有保存!";
}

