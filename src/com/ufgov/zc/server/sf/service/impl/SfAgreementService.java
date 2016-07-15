package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfAgreementMapper;
import com.ufgov.zc.server.sf.service.ISfAgreementService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfAgreementService implements ISfAgreementService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfAgreementMapper agreementMapper;
  
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


  public SfAgreementMapper getAgreementMapper() {
    return agreementMapper;
  }


  public void setAgreementMapper(SfAgreementMapper agreementMapper) {
    this.agreementMapper = agreementMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfAgreement selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementMapper.selectByPrimaryKey(id);
  }

  
  public SfAgreement saveFN(SfAgreement inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getAgreementId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfAgreement.SEQ_SF_AGREEMENT_ID));
      inData.setAgreementId(id);  

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
  
  private void update(SfAgreement inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    agreementMapper.updateByPrimaryKey(inData);
  }

  private void insert(SfAgreement inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    agreementMapper.insert(inData);
  }
  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    agreementMapper.deleteByPrimaryKey(id);
  }

  
  public SfAgreement unAuditFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAgreement untreadFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAgreement auditFN(SfAgreement qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getAgreementId(),requestMeta);
  }

  
  public SfAgreement newCommitFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getAgreementId(),requestMeta);
  }

  
  public SfAgreement callbackFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
