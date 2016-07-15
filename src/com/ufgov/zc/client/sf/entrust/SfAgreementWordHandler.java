package com.ufgov.zc.client.sf.entrust;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.ChangeRMB;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfAgreementWordHandler extends WordHandlerAdapter {

  //  String templateFileId = "sf_agreement_template";

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_agreement_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }
    //页眉、页脚
    /*dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-471-14"));
    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));  */  

    dataMap.put("bh", StringUtil.freeMarkFillWordChar(""));
    dataMap.put("bb", StringUtil.freeMarkFillWordChar(""));
    dataMap.put("xd", StringUtil.freeMarkFillWordChar(""));

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));

    String jgxkz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);
    dataMap.put("xkzh", StringUtil.freeMarkFillWordChar(jgxkz));

    String jgdz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));

    String jgyb = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));

    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));

    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    dataMap.put("wtrq", df.format(entrust.getWtDate()));
    dataMap.put("sjr", StringUtil.freeMarkFillWordChar(entrust.getSjr()));
    dataMap.put("wtsx", StringUtil.freeMarkFillWordChar(entrust.getName()));
    dataMap.put("jdyq", StringUtil.freeMarkFillWordChar(entrust.getJdRequire()));
    String cxjd = "否";
    if (entrust.getIsCxjd() != null && entrust.getIsCxjd().trim().equalsIgnoreCase("y")) {
      cxjd = "是";
    }
    dataMap.put("sfcxjd", cxjd);
    dataMap.put("jazy", StringUtil.freeMarkFillWordChar(entrust.getBrief()));

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
      sb.append(material.getName()).append(" ");
      if (material.getQuantity() != null) {
        sb.append(StringUtil.freeMarkFillWordChar(SfUtil.getDecimalStr(material.getQuantity()))).append("份");
      }
      if (material.getQuantity2() != null) {
        sb.append(StringUtil.freeMarkFillWordChar(SfUtil.getDecimalStr(material.getQuantity2()))).append("张");
      }

      //      sb.append(material.getQuantity()).append(material.getUnit());
    }
    dataMap.put("jdcl", StringUtil.freeMarkFillWordChar(sb.toString()));

    //鉴定费用   
    setCharges(dataMap, entrust.getJdChargeDetaillst());

    //鉴定文件发送方式
    String docFh1 = StringUtil.FU_HAO_KUANG, docFh2 = StringUtil.FU_HAO_KUANG, docFh3 = StringUtil.FU_HAO_KUANG;
    String docDz = "", docFz = "";
    if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_ZIQU.equalsIgnoreCase(entrust.getJdDocSendType())) {
      docFh1 = StringUtil.FU_HAO_GOU;
    } else if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_YOUJI.equalsIgnoreCase(entrust.getJdDocSendType())) {
      docFh2 = StringUtil.FU_HAO_GOU;
      docDz = entrust.getJdDocSendTypeFz();
    } else if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_QITA.equalsIgnoreCase(entrust.getJdDocSendType())) {
      docFh3 = StringUtil.FU_HAO_GOU;
      docFz = entrust.getJdDocSendTypeFz();
    }
    dataMap.put("docfh1", StringUtil.freeMarkFillWordChar(docFh1));
    dataMap.put("docfh2", StringUtil.freeMarkFillWordChar(docFh2));
    dataMap.put("docfh3", StringUtil.freeMarkFillWordChar(docFh3));
    dataMap.put("docdz", StringUtil.freeMarkFillWordChar(docDz));
    dataMap.put("docfz", StringUtil.freeMarkFillWordChar(docFz));

    //协议事项
    setXysx(dataMap, entrust);

    dataMap.put("remark", StringUtil.freeMarkFillWordChar(entrust.getRemark()));
    return dataMap;
  }

  private void setXysx(Map<String, Object> dataMap, SfEntrust entrust) {
    // TCJLODO Auto-generated method stub
    HashMap<BigDecimal, String> xysxMaps = new HashMap<BigDecimal, String>();
    if (entrust.getXysxLst() != null) {
      for (int i = 0; i < entrust.getXysxLst().size(); i++) {
        SfXysx sx = (SfXysx) entrust.getXysxLst().get(i);
        xysxMaps.put(sx.getXysxTypeId(), sx.getInputContent());
      }
    }
    for (int i = 1; i <= 17; i++) {
      String valName = "sx" + i;
      dataMap.put(valName, StringUtil.freeMarkFillWordChar(getXysxVal(i, xysxMaps)));
    }
  }

  private String getXysxVal(int i, HashMap<BigDecimal, String> xysxMaps) {
    // TCJLODO Auto-generated method stub
    String val = xysxMaps.get(new BigDecimal(i));
    if (isCheckBox(i)) {
      if ("Y".equalsIgnoreCase(val)) {
        val = StringUtil.FU_HAO_GOU;
      } else {
        val = StringUtil.FU_HAO_KUANG;
      }
    }
    return val;
  }

  private boolean isCheckBox(int i) {
    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 14) {
      return true;
    }
    return false;
  }

  /**
   * 鉴定费用   
   * @param dataMap
   * @param sourceMap
   */
  private void setCharges(Map<String, Object> dataMap, List<SfChargeDetail> jdChargeDetailLst) {

    //鉴定费用    
    BigDecimal totalCharge = new BigDecimal(0);
    BigDecimal txjeTotal = new BigDecimal(0);

    List sfmxLst = new ArrayList();

    if (jdChargeDetailLst != null || jdChargeDetailLst.size() > 0) {
      for (SfChargeDetail detail : jdChargeDetailLst) {
        totalCharge = totalCharge.add(detail.getTotalPrice());
        SFMX sfmx = new SFMX();
        String mc = detail.getChargeStandardName();
        if (mc == null || mc.trim().length() == 0) {//不是标准收费，是特殊项目
          txjeTotal = txjeTotal.add(detail.getTotalPrice() == null ? new BigDecimal(0) : detail.getTotalPrice());
          continue;
        }
        sfmx.setSfxm(StringUtil.freeMarkFillWordChar(mc));
        if (SfChargeDetail.PRICE_TYPE_BIAOZHUN.equalsIgnoreCase(detail.getPriceType())) {
          sfmx.setBzfh(StringUtil.FU_HAO_GOU);
          sfmx.setXyfh(StringUtil.FU_HAO_KUANG);
        } else {
          sfmx.setBzfh(StringUtil.FU_HAO_KUANG);
          sfmx.setXyfh(StringUtil.FU_HAO_GOU);
        }
        double je = detail.getTotalPrice() == null ? 0.0 : detail.getTotalPrice().doubleValue();
        sfmx.setSfje("" + je);

        sfmxLst.add(sfmx);

      }
    }

    if (sfmxLst.size() == 0) {//为空时，建立空白的数据，以便替换模板上的变量
      SFMX sfmx = new SFMX();
      sfmx.setSfxm("");
      sfmx.setBzfh(StringUtil.FU_HAO_KUANG);
      sfmx.setXyfh(StringUtil.FU_HAO_KUANG);
      sfmx.setSfje("");
      sfmxLst.add(sfmx);
    }

    dataMap.put("jdsf", sfmxLst);
    if (txjeTotal.doubleValue() > 0) {
      dataMap.put("txfh", StringUtil.FU_HAO_GOU);
      dataMap.put("txjdsf", "" + txjeTotal.doubleValue());
    } else {
      dataMap.put("txfh", StringUtil.FU_HAO_KUANG);
      dataMap.put("txjdsf", "");
    }

    dataMap.put("zje", "" + totalCharge.doubleValue());
    dataMap.put("zjedx", "" + ChangeRMB.doChange("" + totalCharge.doubleValue()));
  }

  public class SFMX {
    String sfxm;//收费项目

    String bzfh;//标准符号

    String xyfh;//协议符号

    String sfje;//收费金额

    public String getSfxm() {
      return sfxm;
    }

    public void setSfxm(String sfxm) {
      this.sfxm = sfxm;
    }

    public String getBzfh() {
      return bzfh;
    }

    public void setBzfh(String bzfh) {
      this.bzfh = bzfh;
    }

    public String getXyfh() {
      return xyfh;
    }

    public void setXyfh(String xyfh) {
      this.xyfh = xyfh;
    }

    public String getSfje() {
      return sfje;
    }

    public void setSfje(String sfje) {
      this.sfje = sfje;
    }
  }
}
