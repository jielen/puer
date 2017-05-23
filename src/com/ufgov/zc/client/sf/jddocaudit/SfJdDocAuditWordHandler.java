package com.ufgov.zc.client.sf.jddocaudit;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.ufgov.zc.common.sf.model.WfActionHistory;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.util.EmpMeta;
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
    
    dataMap.put("slbh", StringUtil.freeMarkFillWordChar(entrust.getAcceptCode()));
    
    dataMap.put("acceptCode", StringUtil.freeMarkFillWordChar(entrust.getAcceptCode()));

    dataMap.put("wsbh", StringUtil.freeMarkFillWordChar(report.getReportCode()));

    dataMap.put("wtdw", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));

    dataMap.put("name", StringUtil.freeMarkFillWordChar(entrust.getName()));
 //获取鉴定科室，原来从asOrg中获取，现在看来，不同专业获取对应科室比较合适
    dataMap.put("ywks", getYwks(entrust));
    
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
    
    setAuditInfo(dataMap,jdDocAudit);

    return dataMap;
  }

  private Object getYwks(SfEntrust entrust) {
    if(entrust.getMajor()!=null && entrust.getMajor().getMajorCode()!=null){
      if(entrust.getMajor().getMajorCode().startsWith("001")){
        return "法医室";
      }else if(entrust.getMajor().getMajorCode().startsWith("002")){
        return "痕迹室";
      }else if(entrust.getMajor().getMajorCode().startsWith("003")){
        return "理化室";
      }else if(entrust.getMajor().getMajorCode().startsWith("004")){
        return "文件鉴定室";
      }else if(entrust.getMajor().getMajorCode().startsWith("005")){
        return "声像室";
      }else if(entrust.getMajor().getMajorCode().startsWith("006")){
        return "电子证据室";
      }else{
        return "";
      }  
    }
    return "";
  }

  /**
   * 获取审批意见
   * @param dataMap
   * @param jdDocAudit
   */
  private void setAuditInfo(Map<String, Object> dataMap, SfJdDocAudit jdDocAudit) {

    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
    List wfHistoryLst= zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectWfHistoryByInstanceId",new BigDecimal(jdDocAudit.getProcessInstId()), WorkEnv.getInstance().getRequestMeta());
    if(wfHistoryLst==null)wfHistoryLst=new ArrayList();
    _setJdfzrAudit(dataMap,wfHistoryLst);
    _setSqqzrAudit(dataMap,wfHistoryLst);
    _setLeaderAudit(dataMap,wfHistoryLst);
  }

  /**
   * 设定领导审核意见
   * @param dataMap
   * @param wfHistoryLst
   */
  private void _setLeaderAudit(Map<String, Object> dataMap, List wfHistoryLst) {
    //31218 是授权签字人审批节点ID
    WfActionHistory hist=getNodeAuditMsg(wfHistoryLst,new BigDecimal(31218));
    if(hist==null){
      dataMap.put("ldYj", "");
      dataMap.put("ld", "");
      dataMap.put("ldDate", "年        月         日");
    }else{
      dataMap.put("ldYj", StringUtil.freeMarkFillWordChar(hist.getDescription()==null?"同意":hist.getDescription()));
      dataMap.put("ld", StringUtil.freeMarkFillWordChar(hist.getOwner()==null?"":EmpMeta.getEmpName(hist.getOwner())));
      dataMap.put("ldDate", getExcuteTime(hist.getExecuteTime()));
    }    
  }

  /**
   * 设定授权签字人审核意见
   * @param dataMap
   * @param wfHistoryLst
   */
  private void _setSqqzrAudit(Map<String, Object> dataMap, List wfHistoryLst) {
    //31215 是授权签字人审批节点ID
    WfActionHistory hist=getNodeAuditMsg(wfHistoryLst,new BigDecimal(31215));
    if(hist==null){
      dataMap.put("sqqzrYj", "");
      dataMap.put("sqqzr", "");
      dataMap.put("sqqzrDate", "年        月         日");
    }else{
      dataMap.put("sqqzrYj", StringUtil.freeMarkFillWordChar(hist.getDescription()==null?"同意":hist.getDescription()));
      dataMap.put("sqqzr", StringUtil.freeMarkFillWordChar(hist.getOwner()==null?"":EmpMeta.getEmpName(hist.getOwner())));
      dataMap.put("sqqzrDate", getExcuteTime(hist.getExecuteTime()));
    }
  }

  /**
   * 设定鉴定负责人审核意见
   * @param dataMap
   * @param wfHistoryLst
   */
  private void _setJdfzrAudit(Map<String, Object> dataMap, List wfHistoryLst) {
    Long nodeId=31125098L;//开始节点的id
    //如果是鉴定协助人发起的，则取鉴定负责人的节点 31357
    BigDecimal jdfzrNodeId=new BigDecimal(31357);
    for(int i=0;i<wfHistoryLst.size();i++){
      WfActionHistory h=(WfActionHistory) wfHistoryLst.get(i);
      if(h.getNodeId().compareTo(jdfzrNodeId)==0){
        nodeId=31357L;
        break;
      }
    }
    WfActionHistory hist=getNodeAuditMsg(wfHistoryLst,new BigDecimal(nodeId));
    if(hist==null){
      dataMap.put("jdfzrYj", "");
      dataMap.put("jdfzr", "");
      dataMap.put("jdfzrDate", "年        月         日");
    }else{
      dataMap.put("jdfzrYj", StringUtil.freeMarkFillWordChar(hist.getDescription()==null?"同意":hist.getDescription()));
      dataMap.put("jdfzr", StringUtil.freeMarkFillWordChar(hist.getOwner()==null?"":EmpMeta.getEmpName(hist.getOwner())));
      dataMap.put("jdfzrDate", getExcuteTime(hist.getExecuteTime()));
    }
  }

  private String getExcuteTime(String executeTime) {
    if(executeTime==null)return "";
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    SimpleDateFormat viewFormat = new SimpleDateFormat("yyyy年MM月dd日");
    String str="";
    try {
      str=viewFormat.format(format.parse(executeTime));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
//      e.printStackTrace();
    }
    return str;
  }

  private WfActionHistory getNodeAuditMsg(List wfHistoryLst, BigDecimal nodeId) {
    WfActionHistory rtn=null;
    for(int i=0;i<wfHistoryLst.size();i++){
      WfActionHistory h=(WfActionHistory) wfHistoryLst.get(i);
      if(h.getNodeId().compareTo(nodeId)==0){
        if(rtn==null){
          rtn=h;
        }else{//获取较大的那个
          if(rtn.getActionHistoryId().compareTo(h.getActionHistoryId())==-1){
            rtn=h;
          }
        }
      }
    }
    return rtn;
  }

  private List getFj(SfJdDocAudit jdDocAudit) {
    // TCJLODO Auto-generated method stub
    List rtn = new ArrayList();
    if (jdDocAudit == null || jdDocAudit.getDetailLst() == null)
      return rtn;
    for (int i = 0; i < jdDocAudit.getDetailLst().size(); i++) {
      SfJdDocAuditDetail d = (SfJdDocAuditDetail) jdDocAudit.getDetailLst().get(i);
      Fjrow row = new Fjrow();
      row.setFjbh(i+1);
      row.setFj(StringUtil.freeMarkFillWordChar(d.getDocName()));
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
