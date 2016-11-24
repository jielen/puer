package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfJdDocAuditService;
import com.ufgov.zc.server.sf.service.ISfJdReportService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfJdDocAuditService implements ISfJdDocAuditService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfJdDocAuditMapper jdDocAuditMapper;
  private SfJdDocAuditDetailMapper jdDocAuditDetailMapper;
  private ISfEntrustService sfEntrustService;
  private IZcEbBaseService zcEbBaseService;
  private ISfJdReportService sfJdReportService; 
  private SfMaterialsTransferDetailMapper materialsTransferDetailMapper;
  
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfJdDocAudit selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfJdDocAudit rtn=jdDocAuditMapper.selectByPrimaryKey(id);
    if(rtn==null)return null;
    SfEntrust e=sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta);
    if(e!=null){
      rtn.setEntrust(e);
    } 
    SfJdReport report=(SfJdReport)zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfJdReportMapper.selectByEntrustId", rtn.getEntrustId());
//    SfJdReport report=sfJdReportService.selectByPrimaryKey(id, requestMeta)
    rtn.setReport(report==null?new SfJdReport():report);
    rtn.setDetailLst(jdDocAuditDetailMapper.selectByPrimaryKey(id));
    List mlst=materialsTransferDetailMapper.selectByPrimaryKey(id);
    rtn.setMaterialLst(mlst==null?new ArrayList():mlst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfJdDocAudit saveFN(SfJdDocAudit inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getJdDocAuditId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfJdDocAudit.SEQ_SF_JD_DOC_AUDIT_ID));
      inData.setJdDocAuditId(id);  

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();
      
      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }      
      insert(inData,requestMeta);
      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getName());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }

  private void update(SfJdDocAudit inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocAuditMapper.updateByPrimaryKey(inData);
    
    jdDocAuditDetailMapper.deleteByPrimaryKey(inData.getJdDocAuditId());
    if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfJdDocAuditDetail m=(SfJdDocAuditDetail) inData.getDetailLst().get(i);
        m.setJdDocAuditId(inData.getJdDocAuditId());
        jdDocAuditDetailMapper.insert(m);
      }
    }
    materialsTransferDetailMapper.deleteByPrimaryKey(inData.getJdDocAuditId());
    if(inData.getMaterialLst()!=null){
      for(int i=0;i<inData.getMaterialLst().size();i++){
        SfMaterialsTransferDetail md=(SfMaterialsTransferDetail) inData.getMaterialLst().get(i);
        md.setTransferId(inData.getJdDocAuditId());
        materialsTransferDetailMapper.insert(md);
      }
    }
  }

  private void insert(SfJdDocAudit inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocAuditMapper.insert(inData);
    if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfJdDocAuditDetail m=(SfJdDocAuditDetail) inData.getDetailLst().get(i);
        m.setJdDocAuditId(inData.getJdDocAuditId());
        jdDocAuditDetailMapper.insert(m);
      }
    }
    if(inData.getMaterialLst()!=null){
      for(int i=0;i<inData.getMaterialLst().size();i++){
        SfMaterialsTransferDetail md=(SfMaterialsTransferDetail) inData.getMaterialLst().get(i);
        md.setTransferId(inData.getJdDocAuditId());
        materialsTransferDetailMapper.insert(md);
      }
    }
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocAuditMapper.deleteByPrimaryKey(id);
    jdDocAuditDetailMapper.deleteByPrimaryKey(id);
  }

  
  public SfJdDocAudit unAuditFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfJdDocAudit untreadFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    sendMsgUntread(qx,requestMeta);
    return qx;
  }

  private void sendMsgUntread(SfJdDocAudit qx, RequestMeta requestMeta) {
	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_JD_DOC_AUDIT");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectUntreadUser", dto);
	  if(userLst!=null ){
		  String mobile="";
		  String msg=qx.getEntrust().getCode()+"鉴定文书审批单被退回了,请登录鉴定管理系统进行查看处理。";
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getJdjgUser(user, qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());
			  } 
		  }
	  }	  
}


  private void sendMsgAudit(SfJdDocAudit qx, RequestMeta requestMeta) {

	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_JD_DOC_AUDIT");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectToDoUser", dto);
	   
	  if(userLst!=null ){
		  String mobile="";
		  String msg=qx.getEntrust().getCode()+"鉴定文书审批单等待您审批,案事件:"+qx.getName()+",请登录鉴定管理系统进行审批。";
		  
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getJdjgUser(user,qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());
			  } 
		  }
	  }
}

  
  public SfJdDocAudit auditFN(SfJdDocAudit qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    sendMsgAudit(qx,requestMeta);
    return selectByPrimaryKey(qx.getJdDocAuditId(),requestMeta);
  }

  
  public SfJdDocAudit newCommitFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    String comment=qx.getComment();
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.newCommit(comment, qx, requestMeta);
    sendMsgAudit(qx,requestMeta);
    return selectByPrimaryKey(qx.getJdDocAuditId(),requestMeta);
  }

  public SfJdDocAudit callbackFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }
  
  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }


  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }


  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }


  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }


  public SfJdDocAuditMapper getJdDocAuditMapper() {
    return jdDocAuditMapper;
  }


  public void setJdDocAuditMapper(SfJdDocAuditMapper jdDocAuditMapper) {
    this.jdDocAuditMapper = jdDocAuditMapper;
  }


  public SfJdDocAuditDetailMapper getJdDocAuditDetailMapper() {
    return jdDocAuditDetailMapper;
  }


  public void setJdDocAuditDetailMapper(SfJdDocAuditDetailMapper jdDocAuditDetailMapper) {
    this.jdDocAuditDetailMapper = jdDocAuditDetailMapper;
  }


  public ISfEntrustService getSfEntrustService() {
    return sfEntrustService;
  }


  public void setSfEntrustService(ISfEntrustService sfEntrustService) {
    this.sfEntrustService = sfEntrustService;
  }


public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}


public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}


public ISfJdReportService getSfJdReportService() {
	return sfJdReportService;
}


public void setSfJdReportService(ISfJdReportService sfJdReportService) {
	this.sfJdReportService = sfJdReportService;
}


public SfMaterialsTransferDetailMapper getMaterialsTransferDetailMapper() {
  return materialsTransferDetailMapper;
}


public void setMaterialsTransferDetailMapper(SfMaterialsTransferDetailMapper materialsTransferDetailMapper) {
  this.materialsTransferDetailMapper = materialsTransferDetailMapper;
}

 

}
