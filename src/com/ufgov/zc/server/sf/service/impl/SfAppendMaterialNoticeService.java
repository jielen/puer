package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfAppendMaterialNoticeMapper;
import com.ufgov.zc.server.sf.service.ISfAppendMaterialNoticeService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfAppendMaterialNoticeService implements ISfAppendMaterialNoticeService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfAppendMaterialNoticeMapper appendMaterialNoticeMapper;
  
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


  public SfAppendMaterialNoticeMapper getAppendMaterialNoticeMapper() {
    return appendMaterialNoticeMapper;
  }


  public void setAppendMaterialNoticeMapper(SfAppendMaterialNoticeMapper appendMaterialNoticeMapper) {
    this.appendMaterialNoticeMapper = appendMaterialNoticeMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfAppendMaterialNotice selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeMapper.selectByPrimaryKey(id);
  }

  
  public SfAppendMaterialNotice saveFN(SfAppendMaterialNotice inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getAppendMaterialNotceId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfAppendMaterialNotice.SEQ_APPEND_MATERIAL_NOTICE_ID));
      inData.setAppendMaterialNotceId(id);  

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
  
  private void update(SfAppendMaterialNotice inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialNoticeMapper.updateByPrimaryKey(inData);
  }

  private void insert(SfAppendMaterialNotice inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialNoticeMapper.insert(inData);
  }
  

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialNoticeMapper.deleteByPrimaryKey(id);
  }

  
  public SfAppendMaterialNotice unAuditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAppendMaterialNotice untreadFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAppendMaterialNotice auditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getAppendMaterialNotceId(),requestMeta);
  }

  
  public SfAppendMaterialNotice newCommitFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getAppendMaterialNotceId(),requestMeta);
  }

  
  public SfAppendMaterialNotice callbackFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }
}
