package com.ufgov.zc.client.sf.appendmaterial;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.util.EmpMeta;

public class SfAppendMaterialWordPrintHandler  extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_appendMaterial_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");
    SfAppendMaterial material = (SfAppendMaterial) sourceMap.get("appendMaterial");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }


    //鉴定机构信息
    setJdjgInfo(dataMap,entrust);

    //委托编号
    setEntrustCode(dataMap,entrust);       

    //委托方信息
    setEntrustorInfo(dataMap,entrust.getEntrustor());
    
    //受理信息
    setShouLiInfo(dataMap,material);
    
    //送检人信息 
    setSongJianRenInfo(dataMap,material);
    
    //案件信息
    setAnJianInfo(dataMap,entrust); 
    //鉴定材料
    setMaterialsInfo(dataMap,material);

    

    //备注
    setRemarkInfo(dataMap,material);
    return dataMap; 
  }
  protected void setRemarkInfo(Map<String, Object> dataMap, SfAppendMaterial material) {
    dataMap.put("XGSM", StringUtil.freeMarkFillWordChar(material.getRemark()));
}

  protected void setMaterialsInfo(Map<String, Object> dataMap, SfAppendMaterial material) {       
        dataMap.put("JDCL", StringUtil.freeMarkFillWordChar(SfBookmarkUtil.getJdclString(material.getDetailLst())));
  }
  protected void setAnJianInfo(Map<String, Object> dataMap, SfEntrust entrust) {
        dataMap.put("ASJMC", StringUtil.freeMarkFillWordChar(entrust.getName()));
        dataMap.put("AJBH", StringUtil.freeMarkFillWordChar(entrust.getAnjianCode()==null?"无":entrust.getAnjianCode()));
  }
  protected void setSongJianRenInfo(Map<String, Object> dataMap,      SfAppendMaterial material) {
        dataMap.put("SJR", StringUtil.freeMarkFillWordChar(material.getSjr()));
        dataMap.put("SJRDH", StringUtil.freeMarkFillWordChar(material.getSjrTel()==null?"无":material.getSjrTel()));
        String s=material.getSjrZjType()==null?"":material.getSjrZjType();
        s+=material.getSjrZjCode()==null?"":" "+material.getSjrZjCode();
        dataMap.put("SJRZJ", StringUtil.freeMarkFillWordChar(s)); 
  }

  protected void setShouLiInfo(Map<String, Object> dataMap, SfAppendMaterial material) {
      dataMap.put("SLR", StringUtil.freeMarkFillWordChar(material.getAcceptor()==null?"":EmpMeta.getEmpName(material.getAcceptor())));      
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      dataMap.put("SLRQ", material.getAcceptDate()==null?"":df.format(material.getAcceptDate()));
      dataMap.put("BCJCBH", StringUtil.freeMarkFillWordChar(material.getAppendMaterialIdStr()));
  }
  protected void setEntrustCode(Map<String, Object> dataMap, SfEntrust material) {
      dataMap.put("WTBH", StringUtil.freeMarkFillWordChar(material.getCode()));
}

  protected void setEntrustorInfo(Map<String, Object> dataMap,  SfEntrustor entrustor) {

      dataMap.put("WTF", StringUtil.freeMarkFillWordChar(entrustor.getName()));
      dataMap.put("LXR", StringUtil.freeMarkFillWordChar(entrustor.getLinkMan()));
//      dataMap.put("WTFDZ", StringUtil.freeMarkFillWordChar(entrustor.getAddress()));//放到委托里获取了
      dataMap.put("WTFYB", StringUtil.freeMarkFillWordChar(entrustor.getZip()==null?"无":entrustor.getZip()));
      dataMap.put("LXDH", StringUtil.freeMarkFillWordChar(entrustor.getLinkTel()));
  }

  protected void setJdjgInfo(Map<String, Object> dataMap, SfEntrust material) {
      String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
      dataMap.put("JGMC", StringUtil.freeMarkFillWordChar(jgmc));

      String jgxkz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);
      dataMap.put("XKZH", StringUtil.freeMarkFillWordChar(jgxkz));

      String jgdz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);
      dataMap.put("JGDZ", StringUtil.freeMarkFillWordChar(jgdz));

      String jgyb = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);
      dataMap.put("JGYB", StringUtil.freeMarkFillWordChar(jgyb));

      String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
      dataMap.put("JGDH", StringUtil.freeMarkFillWordChar(jgdh));
      
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