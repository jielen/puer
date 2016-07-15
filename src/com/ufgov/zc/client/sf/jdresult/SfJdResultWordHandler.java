package com.ufgov.zc.client.sf.jdresult;

import java.util.HashMap;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;

public class SfJdResultWordHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_jd_result_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfJdResult jdresult = (SfJdResult) sourceMap.get("jdresult");
    SfEntrust entrust = jdresult.getEntrust();

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));
    dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-481-2-14"));
    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));

    /*String jgxkz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);    
    dataMap.put("jgxkz", StringUtil.freeMarkFillWordChar(jgxkz));
    
    String jgdz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);    
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));
    
    String jgyb=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);    
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));*/

    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));

    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    //  dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    //  dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    //  dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

    dataMap.put("sjr", StringUtil.freeMarkFillWordChar(entrust.getSjr()));
    dataMap.put("wtsj", StringUtil.freeMarkFillWordChar(entrust.getWtDate() == null ? null : DateUtil.dateToDdString(entrust.getWtDate())));
    dataMap.put("slr", StringUtil.freeMarkFillWordChar(entrust.getAcceptorName()));
    dataMap.put("jddx", StringUtil.freeMarkFillWordChar(jdresult.getJdTargetName()));
    dataMap.put("jdff", StringUtil.freeMarkFillWordChar(jdresult.getJdMethod()));
    dataMap.put("jdsj",
      StringUtil.freeMarkFillWordChar(jdresult.getJdDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(jdresult.getJdDate())));
    dataMap.put("jdfzr", StringUtil.freeMarkFillWordChar(entrust.getJdFzrName()));
    dataMap.put("jdfhr", StringUtil.freeMarkFillWordChar(entrust.getJdFhrName()));
    dataMap.put("brief", StringUtil.freeMarkFillWordChar(jdresult.getBrief()));
    dataMap.put("jdresult", StringUtil.freeMarkFillWordChar(jdresult.getJdResult()));
    dataMap.put("jdprocess", StringUtil.freeMarkFillWordChar(jdresult.getJdProcess()));
    dataMap.put("jdopinion", StringUtil.freeMarkFillWordChar(jdresult.getJdOpinion()));

    return dataMap;
  }

}