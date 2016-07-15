package com.ufgov.zc.client.sf.outinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;

public class SfOutInfoWordPrintHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_outinfo_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");
    SfOutInfo outinfo = (SfOutInfo) sourceMap.get("outInfo");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jgmc));
    dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-489-14"));
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

    //    dataMap.put("jgdh", "12345678");
    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));
    dataMap.put("wtmc", StringUtil.freeMarkFillWordChar(entrust.getName()));

    /* dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
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
     dataMap.put("jdzy", StringUtil.freeMarkFillWordChar(entrust.getMajor() == null ? null : entrust.getMajor().getMajorName()));
     dataMap.put("slr", StringUtil.freeMarkFillWordChar(entrust.getAcceptorName()));
     dataMap.put("brief", StringUtil.freeMarkFillWordChar(entrust.getBrief()));
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < entrust.getMaterials().size(); i++) {
       SfMaterials m = (SfMaterials) entrust.getMaterials().get(i);
       sb.append(StringUtil.freeMarkFillWordChar(m.getName())).append(" ")
         .append(StringUtil.freeMarkFillWordChar(SfUtil.getDecimalStr(m.getQuantity()))).append(StringUtil.freeMarkFillWordChar(m.getUnit()))
         .append(" ").append(StringUtil.freeMarkFillWordChar(m.getDescription())).append("\n");
     }
     dataMap.put("jdcl", StringUtil.freeMarkFillWordChar(sb.toString()));
     dataMap.put("yjdqk", StringUtil.freeMarkFillWordChar(entrust.getJdHistory()));
     dataMap.put("jdyq", StringUtil.freeMarkFillWordChar(entrust.getJdRequire()));
     dataMap.put("remark", StringUtil.freeMarkFillWordChar(entrust.getRemark()));*/

    //    dataMap.put("inputdate", df.format(receipt.getInputDate()));

    dataMap.put("xxlb", StringUtil.freeMarkFillWordChar(getXxlb(outinfo)));
    dataMap.put("tgf", StringUtil.freeMarkFillWordChar(outinfo.getTgf()));
    dataMap.put("tqfs", StringUtil.freeMarkFillWordChar(getTqfs(outinfo)));
    dataMap.put("xxmx", getXxmx(outinfo));
    dataMap.put("tgr", StringUtil.freeMarkFillWordChar(outinfo.getTgf()));
    dataMap.put("tgsj", StringUtil.freeMarkFillWordChar(DateUtil.dateToChinaString(outinfo.getAcceptDate())));
    dataMap.put("yzjg", getYzjg(outinfo));
    dataMap.put("yzr", StringUtil.freeMarkFillWordChar(outinfo.getValidatorName()));
    dataMap.put("yzsj", StringUtil.freeMarkFillWordChar(DateUtil.dateToChinaString(outinfo.getValidatDate())));

    return dataMap;
  }

  /**
   * 信息明细
   * @param outinfo
   * @return
   */
  private Object getXxmx(SfOutInfo outinfo) {
    // TCJLODO Auto-generated method stub
    List rtn = new ArrayList();
    if (outinfo == null || outinfo.getDetailLst() == null)
      return rtn;

    for (int i = 0; i < outinfo.getDetailLst().size(); i++) {
      SfOutInfoDetail d = (SfOutInfoDetail) outinfo.getDetailLst().get(i);
      Xxmx mx = new Xxmx();
      mx.xxmc = StringUtil.freeMarkFillWordChar(d.getName());
      mx.xcsj = StringUtil.freeMarkFillWordChar(d.getProductTime() == null ? null : DateUtil.dateToDdString(d.getProductTime()));
      String quantity = d.getQuantity() == null ? null : (SfUtil.convertNumToStr(d.getQuantity()) + " " + (d.getUnit() == null ? "" : d.getUnit()));
      mx.sl = StringUtil.freeMarkFillWordChar(quantity);
      mx.xz = StringUtil.freeMarkFillWordChar(d.getDescription());
      mx.remark = StringUtil.freeMarkFillWordChar("");
      rtn.add(mx);
    }
    return rtn;
  }

  /**
   * 验证结果
   * @param outinfo
   * @return
   */
  private List getYzjg(SfOutInfo outinfo) {
    // TCJLODO Auto-generated method stub
    List rtn = new ArrayList();
    if (outinfo == null || outinfo.getValidateDetailLst() == null)
      return rtn;
    for (int i = 0; i < outinfo.getValidateDetailLst().size(); i++) {
      SfOutInfoValidateDetail v = (SfOutInfoValidateDetail) outinfo.getValidateDetailLst().get(i);
      Yzjg jg = new Yzjg();
      jg.yzbh = Integer.parseInt(v.getInfoReq().getOutInfoReqCode());
      jg.yznr = StringUtil.freeMarkFillWordChar(v.getInfoReq().getOutInfoReqName());
      if ("Y".equalsIgnoreCase(v.getValidateResult())) {
        jg.yzjgs = StringUtil.FU_HAO_GOU;
      } else {
        jg.yzjgf = StringUtil.FU_HAO_GOU;
      }
      rtn.add(jg);
    }
    return rtn;
  }

  private String getTqfs(SfOutInfo outinfo) {
    // TCJLODO Auto-generated method stub
    StringBuffer b = new StringBuffer();
    if (outinfo == null || outinfo.getDetailLst() == null)
      return b.toString();
    for (int i = 0; i < outinfo.getDetailLst().size(); i++) {
      SfOutInfoDetail d = (SfOutInfoDetail) outinfo.getDetailLst().get(i);
      if (i > 0) {
        b.append("、");
      }
      b.append(d.getTiQuFangShi());
    }
    return b.toString();
  }

  private String getXxlb(SfOutInfo outinfo) {
    // TCJLODO Auto-generated method stub
    StringBuffer b = new StringBuffer();
    if (outinfo == null || outinfo.getDetailLst() == null)
      return b.toString();
    for (int i = 0; i < outinfo.getDetailLst().size(); i++) {
      SfOutInfoDetail d = (SfOutInfoDetail) outinfo.getDetailLst().get(i);
      if (i > 0) {
        b.append("、");
      }
      b.append(d.getInfoType().getOutInfoTypeName());
    }
    return b.toString();
  }

  public class Yzjg {
    private int yzbh;

    private String yznr;

    private String yzjgs = StringUtil.FU_HAO_KUANG;

    private String yzjgf = StringUtil.FU_HAO_KUANG;

    public String getYzjgs() {
      return yzjgs;
    }

    public void setYzjgs(String yzjgs) {
      this.yzjgs = yzjgs;
    }

    public String getYzjgf() {
      return yzjgf;
    }

    public void setYzjgf(String yzjgf) {
      this.yzjgf = yzjgf;
    }

    public int getYzbh() {
      return yzbh;
    }

    public void setYzbh(int yzbh) {
      this.yzbh = yzbh;
    }

    public String getYznr() {
      return yznr;
    }

    public void setYznr(String yznr) {
      this.yznr = yznr;
    }
  }

  /**
   * 信息明细
   * @author Administrator
   *
   */
  public class Xxmx {
    private String xxmc;

    private String xcsj;

    private String sl;

    private String xz;

    private String remark;

    public String getXxmc() {
      return xxmc;
    }

    public void setXxmc(String xxmc) {
      this.xxmc = xxmc;
    }

    public String getXcsj() {
      return xcsj;
    }

    public void setXcsj(String xcsj) {
      this.xcsj = xcsj;
    }

    public String getSl() {
      return sl;
    }

    public void setSl(String sl) {
      this.sl = sl;
    }

    public String getXz() {
      return xz;
    }

    public void setXz(String xz) {
      this.xz = xz;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

  }
}
