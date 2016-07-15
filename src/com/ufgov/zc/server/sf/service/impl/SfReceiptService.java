package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfReceiptMapper;
import com.ufgov.zc.server.sf.service.ISfReceiptService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfReceiptService implements ISfReceiptService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfReceiptMapper receiptMapper;
  
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


  public SfReceiptMapper getReceiptMapper() {
    return receiptMapper;
  }


  public void setReceiptMapper(SfReceiptMapper receiptMapper) {
    this.receiptMapper = receiptMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfReceipt selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptMapper.selectByPrimaryKey(id);
  }

  
  public SfReceipt saveFN(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    // TCJLODO Auto-generated method stub
    if (inData.getReceiptId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfReceipt.SEQ_SF_RECEIPT_ID));
      inData.setReceiptId(id);  

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
  
  private void update(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.updateByPrimaryKey(inData);
  }

  private void insert(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.insert(inData);
  }
  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.deleteByPrimaryKey(id);
  }

  
  public SfReceipt unAuditFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfReceipt untreadFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfReceipt auditFN(SfReceipt qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getReceiptId(),requestMeta);
  }

  
  public SfReceipt newCommitFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getReceiptId(),requestMeta);
  }

  
  public SfReceipt callbackFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
