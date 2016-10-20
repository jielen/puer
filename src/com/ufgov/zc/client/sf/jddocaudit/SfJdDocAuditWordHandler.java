package com.ufgov.zc.client.sf.jddocaudit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdDocAuditWordHandler extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_jd_doc_audit_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfJdDocAudit jdDocAudit = (SfJdDocAudit) sourceMap.get("jdDocAudit");
    SfEntrust entrust = jdDocAudit.getEntrust();
    SfJdReport report=jdDocAudit.getReport();

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }

//    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    SfJdjg jg=getJdjg(jdDocAudit.getCoCode());
    dataMap.put("jgmc", StringUtil.freeMarkFillWordChar(jg.getName()));
//    dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-494-14"));

//    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
//    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));

    /*String jgxkz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);
    dataMap.put("jgxkz", StringUtil.freeMarkFillWordChar(jgxkz));

    String jgdz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));

    String jgyb = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));*/

    dataMap.put("wtbh", StringUtil.freeMarkFillWordChar(entrust.getCode()));
    
    dataMap.put("acceptCode", StringUtil.freeMarkFillWordChar(entrust.getAcceptCode()));

    dataMap.put("wsbh", StringUtil.freeMarkFillWordChar(report.getReportCode()));

//    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));

    dataMap.put("name", StringUtil.freeMarkFillWordChar(entrust.getName()));
    //  dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    //  dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    //  dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

   /* dataMap.put("jddx", StringUtil.freeMarkFillWordChar(entrust.getJdTargetName()));
    dataMap.put("jdfzr", StringUtil.freeMarkFillWordChar(entrust.getJdFzrName()));
    dataMap.put("zppsr", StringUtil.freeMarkFillWordChar(jdDocAudit.getPhotographer()));
    dataMap.put("fwsl", StringUtil.freeMarkFillWordChar(jdDocAudit.getDocQuatity()==null?"":SfUtil.convertNumToStr(jdDocAudit.getDocQuatity())));
    dataMap.put("spsj",
      StringUtil.freeMarkFillWordChar(jdDocAudit.getInputDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(jdDocAudit.getInputDate())));
*/
    dataMap.put("fj", getFj(jdDocAudit));
    if (SfJdReport.RESULT_TYPE_YJS.equals(jdDocAudit.getReportType())) {
      dataMap.put("jdyjs", StringUtil.freeMarkFillWordChar("鉴定意见书")); 
    } else {
        dataMap.put("jdyjs", StringUtil.freeMarkFillWordChar("鉴定报告")); 
    }
//    dataMap.put("remark", StringUtil.freeMarkFillWordChar(jdDocAudit.getRemark()));

    return dataMap;
  }

  private List getFj(SfJdDocAudit jdDocAudit) {
    // TCJLODO Auto-generated method stub
    List rtn = new ArrayList();
    if (jdDocAudit == null || jdDocAudit.getDetailLst() == null)
      return rtn;
    for (int i = 0; i < jdDocAudit.getDetailLst().size(); i++) {
      SfJdDocAuditDetail d = (SfJdDocAuditDetail) jdDocAudit.getDetailLst().get(i);
      Fjrow row = new Fjrow();
      row.setFjbh(Integer.parseInt(d.getDocType().getDocTypeCode()));
      row.setFj(StringUtil.freeMarkFillWordChar(d.getDocType().getDocTypeName()));
      row.setQuantity(d.getQuantity()==null?"":d.getQuantity().intValue()+"");
      row.setFjg(StringUtil.FU_HAO_GOU);
      rtn.add(row);
    }
    return rtn;
  }

  public class Fjrow {
    private int fjbh;

    private String fj;

    private String fjg;
    
    private String quantity;

    public int getFjbh() {
      return fjbh;
    }

    public void setFjbh(int fjbh) {
      this.fjbh = fjbh;
    }

    public String getFj() {
      return fj;
    }

    public void setFj(String fj) {
      this.fj = fj;
    }

    public String getFjg() {
      return fjg;
    }

    public void setFjg(String fjg) {
      this.fjg = fjg;
    }

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
  }
  SfJdjg getJdjg(String coCode){

		IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
		ElementConditionDto dto=new ElementConditionDto();
		RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
		dto.setCoCode(coCode);
		SfJdjg jdjg=(SfJdjg) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto, meta);
		if(jdjg==null){
			jdjg=new SfJdjg();
		}
		return jdjg;
  }
}
