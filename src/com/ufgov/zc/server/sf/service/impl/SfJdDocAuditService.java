package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfJdDocAuditService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdDocAuditService implements ISfJdDocAuditService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfJdDocAuditMapper jdDocAuditMapper;
  private SfJdDocAuditDetailMapper jdDocAuditDetailMapper;
  private ISfEntrustService sfEntrustService;
  
  
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
    rtn.setDetailLst(jdDocAuditDetailMapper.selectByPrimaryKey(id));
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfJdDocAudit saveFN(SfJdDocAudit inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getJdDocAuditId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfJdDocAudit.SEQ_SF_JD_DOC_AUDIT_ID));
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
    return qx;
  }

  
  public SfJdDocAudit auditFN(SfJdDocAudit qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getJdDocAuditId(),requestMeta);
  }

  
  public SfJdDocAudit newCommitFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
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

}
