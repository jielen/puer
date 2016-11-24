package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfDossierMapper;
import com.ufgov.zc.server.sf.service.ISfDossierService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfDossierService implements ISfDossierService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfDossierMapper dossierMapper;
  
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


  public SfDossierMapper getDossierMapper() {
    return dossierMapper;
  }


  public void setDossierMapper(SfDossierMapper dossierMapper) {
    this.dossierMapper = dossierMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfDossier selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierMapper.selectByPrimaryKey(id);
  }

  
  public SfDossier saveFN(SfDossier inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getDossierId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfDossier.SEQ_SF_DOSSIER_ID));
      inData.setDossierId(id);  

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
  
  private void update(SfDossier inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    dossierMapper.updateByPrimaryKey(inData);
  }

  private void insert(SfDossier inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    dossierMapper.insert(inData);
  }  

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    dossierMapper.deleteByPrimaryKey(id);
  }

  
  public SfDossier unAuditFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfDossier untreadFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfDossier auditFN(SfDossier qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getDossierId(),requestMeta);
  }

  
  public SfDossier newCommitFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getDossierId(),requestMeta);
  }

  
  public SfDossier callbackFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
