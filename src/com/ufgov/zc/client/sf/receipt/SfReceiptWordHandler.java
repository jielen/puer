package com.ufgov.zc.client.sf.receipt;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfReceiptWordHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_receipt_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfReceipt receipt = (SfReceipt) sourceMap.get("receipt");

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));

    /*String jgxkz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);    
    dataMap.put("jgxkz", StringUtil.freeMarkFillWordChar(jgxkz));
    
    String jgdz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);    
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));
    
    String jgyb=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);    
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));*/

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));

//    dataMap.put("jgdh", "12345678");
    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));

    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    //  dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    //  dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    //  dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    dataMap.put("wtsj", df.format(entrust.getWtDate()));
    dataMap.put("sjr", StringUtil.freeMarkFillWordChar(entrust.getSjr()));
    dataMap.put("wtmc", StringUtil.freeMarkFillWordChar(entrust.getName()));
    dataMap.put("slr", StringUtil.freeMarkFillWordChar(entrust.getAcceptorName()));
    dataMap.put("inputdate", df.format(receipt.getInputDate()));

    return dataMap;
  }

}
