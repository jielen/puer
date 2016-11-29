package com.ufgov.zc.common.commonbiz.fieldmap;

import java.util.HashMap;
import java.util.Map;

public class SfMaterialsFM {

	  public static Map fieldMap = new HashMap();

	  static {

	    fieldMap.putAll(ZcBaseBillFM.fieldMap);
	    
	    fieldMap.put("APPEND_MATERIAL_ID","appendMaterialId");
	    fieldMap.put("ATTACH_FILE","attachFile");
	    fieldMap.put("ATTACH_FILE_BLOBID","attachFileBlobid");
	    fieldMap.put("BAR_CODE","barCode");
	    fieldMap.put("BIANHAO","bianhao");
	    fieldMap.put("DESCRIPTION","description");
	    fieldMap.put("ENTRUST_ID","entrustId");
	    fieldMap.put("JIAN_HOU_CHULI_TYPE","jianHouChuliType");
	    fieldMap.put("JIAN_HOU_STORE_TIME","jianHouStoreTime");
	    fieldMap.put("MATERIAL_ID","materialId");
	    fieldMap.put("MATERIAL_TYPE","materialType");
	    fieldMap.put("NAME","name");
	    fieldMap.put("QUANTITY","quantity");
	    fieldMap.put("QUANTITY2","quantity2");
	    fieldMap.put("QUANTITY3","quantity3");
	    fieldMap.put("REMARK","remark");
	    fieldMap.put("SAVE_ADDRESS","saveAddress");
	    fieldMap.put("SAVE_CONDITON","saveConditon");
	    fieldMap.put("UNIT","unit");
      fieldMap.put("TQ_INFO","tqInfo");

	  }
}
