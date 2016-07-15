package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper;
import com.ufgov.zc.server.sf.service.ISfMaterialsTransferService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfMaterialsTransferService implements ISfMaterialsTransferService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfMaterialsTransferMapper materialsTransferMapper;
  private SfMaterialsTransferDetailMapper materialsTransferDetailMapper;
  
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


  public SfMaterialsTransferMapper getMaterialsTransferMapper() {
    return materialsTransferMapper;
  }


  public void setMaterialsTransferMapper(SfMaterialsTransferMapper materialsTransferMapper) {
    this.materialsTransferMapper = materialsTransferMapper;
  }


  public SfMaterialsTransferDetailMapper getMaterialsTransferDetailMapper() {
    return materialsTransferDetailMapper;
  }


  public void setMaterialsTransferDetailMapper(SfMaterialsTransferDetailMapper materialsTransferDetailMapper) {
    this.materialsTransferDetailMapper = materialsTransferDetailMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return materialsTransferMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfMaterialsTransfer selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfMaterialsTransfer rtn=materialsTransferMapper.selectByPrimaryKey(id);
    List detailLst=materialsTransferDetailMapper.selectByPrimaryKey(id);
    rtn.setDetailLst(detailLst==null?new ArrayList():detailLst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfMaterialsTransfer saveFN(SfMaterialsTransfer inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getTransferId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfMaterialsTransfer.SEQ_SF_MATERIALS_TRANSFER_ID));
      inData.setTransferId(id);  

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

  private void update(SfMaterialsTransfer inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    materialsTransferMapper.updateByPrimaryKey(inData);
    
    materialsTransferDetailMapper.deleteByPrimaryKey(inData.getTransferId());
    if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfMaterialsTransferDetail m=(SfMaterialsTransferDetail) inData.getDetailLst().get(i);
        m.setTransferId(inData.getTransferId());
        materialsTransferDetailMapper.insert(m);
      }
    }    
  }

  private void insert(SfMaterialsTransfer inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    materialsTransferMapper.insert(inData);
    if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfMaterialsTransferDetail m=(SfMaterialsTransferDetail) inData.getDetailLst().get(i);
        m.setTransferId(inData.getTransferId());
        materialsTransferDetailMapper.insert(m);
      }
    }
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    materialsTransferMapper.deleteByPrimaryKey(id);
    materialsTransferDetailMapper.deleteByPrimaryKey(id);
  }

  
  public SfMaterialsTransfer unAuditFN(SfMaterialsTransfer qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfMaterialsTransfer untreadFN(SfMaterialsTransfer qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfMaterialsTransfer auditFN(SfMaterialsTransfer qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx.setJieShouDate(requestMeta.getTransDate());
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getTransferId(),requestMeta);
  }

  
  public SfMaterialsTransfer newCommitFN(SfMaterialsTransfer qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    qx.setYiJiaoDate(requestMeta.getTransDate());
    qx.setYiJiaoRen(requestMeta.getSvUserID());
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getTransferId(),requestMeta);
  }

  
  public SfMaterialsTransfer callbackFN(SfMaterialsTransfer qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
