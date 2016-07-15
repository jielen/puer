/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.constants.SfElementConstants;

/**
 * @author Administrator
 *
 */
public class SfEntrustWordPrintHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_entrust_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));
    dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号000"));
    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));

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
    dataMap.put("sjrzj", StringUtil.freeMarkFillWordChar(entrust.getSjrZjType()));
    dataMap.put("sjrzjhm", StringUtil.freeMarkFillWordChar(entrust.getSjrZjCode()));
    dataMap.put("sjrtel", StringUtil.freeMarkFillWordChar(entrust.getSjrTel()));
    dataMap.put("sjraddress", StringUtil.freeMarkFillWordChar(entrust.getSjrAddress()));
    dataMap.put("wtmc", StringUtil.freeMarkFillWordChar(entrust.getName()));
    dataMap.put("jdzy",
      StringUtil.freeMarkFillWordChar(entrust.getMajor() == null ? null : AsValDataCache.getName(SfMajor.SF_VS_MAJOR, entrust.getMajorCode())));
    dataMap.put("slr", StringUtil.freeMarkFillWordChar(entrust.getAcceptorName()));
    dataMap.put("brief", StringUtil.freeMarkFillWordChar(entrust.getBrief()));
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < entrust.getMaterials().size(); i++) {
      SfMaterials m = (SfMaterials) entrust.getMaterials().get(i);
      sb.append(StringUtil.freeMarkFillWordChar(m.getName())).append(" ");
      if (m.getQuantity() != null) {
        sb.append(StringUtil.freeMarkFillWordChar(SfUtil.getDecimalStr(m.getQuantity()))).append("份");
      }
      if (m.getQuantity2() != null) {
        sb.append(StringUtil.freeMarkFillWordChar(SfUtil.getDecimalStr(m.getQuantity2()))).append("张");
      }
      sb.append(" ").append(StringUtil.freeMarkFillWordChar(m.getDescription())).append("\n");
    }
    dataMap.put("jdcl", StringUtil.freeMarkFillWordChar(sb.toString()));
    dataMap.put("yjdqk", StringUtil.freeMarkFillWordChar(entrust.getJdHistory()));
    dataMap.put("jdyq", StringUtil.freeMarkFillWordChar(entrust.getJdRequire()));
    dataMap.put("remark", StringUtil.freeMarkFillWordChar(entrust.getRemark()));

    //    dataMap.put("inputdate", df.format(receipt.getInputDate()));

    return dataMap;
  }

}
