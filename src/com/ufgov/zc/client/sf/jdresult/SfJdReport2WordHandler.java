package com.ufgov.zc.client.sf.jdresult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;

/**
 * 检验意见书模板
 * @author Administrator
 *
 */
public class SfJdReport2WordHandler extends WordHandlerAdapter {

  boolean isZhenBen = true;

  public SfJdReport2WordHandler(boolean isZhenben) {
    super();
    this.isZhenBen = isZhenben;
  }

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub

    return "sf_jd_yijianshu_template";
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
    if (isZhenBen) {
      dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-499-1A-14"));
      dataMap.put("zfb", StringUtil.freeMarkFillWordChar("正  本"));
      dataMap.put("fyj", StringUtil.freeMarkFillWordChar("司法鉴定委托书、司法鉴定许可证、司法鉴定人执业证"));
    } else {
      dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-499-1B-14"));
      dataMap.put("zfb", StringUtil.freeMarkFillWordChar("副  本"));
      dataMap.put("fyj", StringUtil.freeMarkFillWordChar("司法鉴定许可证"));
    }
    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));

    String jgxkz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);
    dataMap.put("jgxkz", StringUtil.freeMarkFillWordChar(jgxkz));

    String jgdz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));

    String jgyb = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));

    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));

    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    //  dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    //  dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    //  dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

    dataMap.put("wtmc", StringUtil.freeMarkFillWordChar(entrust.getName()));

    dataMap.put("wtsj", StringUtil.freeMarkFillWordChar(entrust.getWtDate() == null ? null : DateUtil.dateToDdString(entrust.getWtDate())));

    //鉴定材料
    StringBuffer sb = new StringBuffer();
    if (entrust.getMaterials() == null) {
      entrust.setMaterials(new ArrayList());
    }
    for (int i = 0; i < entrust.getMaterials().size(); i++) {
      SfMaterials material = (SfMaterials) entrust.getMaterials().get(i);
      if (i > 0) {
        sb.append(";");
      }
      sb.append(material.getName()).append(" ").append(material.getQuantity()).append(material.getUnit());
    }
    dataMap.put("jdcl", StringUtil.freeMarkFillWordChar(sb.toString()));

    dataMap.put("jdsj",
      StringUtil.freeMarkFillWordChar(jdresult.getJdDate() == null ? "  年    月   日" : DateUtil.dateToDdString(jdresult.getJdDate())));
    dataMap.put("jdsj2",
      StringUtil.freeMarkFillWordChar(jdresult.getJdDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(jdresult.getJdDate())));
    dataMap.put("jddd", StringUtil.freeMarkFillWordChar(jdresult.getJdAddress()));
    dataMap.put("zcry", StringUtil.freeMarkFillWordChar(jdresult.getZcPersons()));
    dataMap.put("jddx", StringUtil.freeMarkFillWordChar(jdresult.getJdTargetName()));

    dataMap.put("brief", StringUtil.freeMarkFillWordChar(jdresult.getBrief()));
    dataMap.put("jdresult", StringUtil.freeMarkFillWordChar(jdresult.getJdResult()));
    dataMap.put("jdprocess", StringUtil.freeMarkFillWordChar(jdresult.getJdProcess()));
    dataMap.put("jdopinion", StringUtil.freeMarkFillWordChar(jdresult.getJdOpinion()));

    dataMap.put("jdfzr", StringUtil.freeMarkFillWordChar(entrust.getJdFzrName()));
    dataMap.put("jdfzrzyzh", StringUtil.freeMarkFillWordChar(getZybh(entrust.getJdFzr())));

    return dataMap;
  }

  private String getZybh(String jdFzr) {
    // TCJLODO Auto-generated method stub
    if (jdFzr == null)
      return null;
    SfJdPerson person = (SfJdPerson) baseService.queryObject("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.selectByAccount", jdFzr, meta);

    if (person == null)
      return null;

    return person.getCertificateNo();
  }

}
