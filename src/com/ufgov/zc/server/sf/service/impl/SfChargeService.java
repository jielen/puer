package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfChargeDetailMapper;
import com.ufgov.zc.server.sf.dao.SfChargeMapper;
import com.ufgov.zc.server.sf.service.ISfChargeService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfChargeService implements ISfChargeService {

  private IWorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  private SfChargeMapper chargeMapper;

  private SfChargeDetailMapper chargeDetailMapper;

  public SfChargeDetailMapper getChargeDetailMapper() {
    return chargeDetailMapper;
  }

  public void setChargeDetailMapper(SfChargeDetailMapper chargeDetailMapper) {
    this.chargeDetailMapper = chargeDetailMapper;
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

  public SfChargeMapper getChargeMapper() {
    return chargeMapper;
  }

  public void setChargeMapper(SfChargeMapper chargeMapper) {
    this.chargeMapper = chargeMapper;
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeMapper.getMainDataLst(elementConditionDto);
  }

  public SfCharge selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfCharge rtn = chargeMapper.selectByPrimaryKey(id);

    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public SfCharge saveFN(SfCharge inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getChargeId() == null) {

      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfCharge.SEQ_SF_CHARGE_ID));
      inData.setChargeId(id);

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();

      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }
      insert(inData, requestMeta);
      /* if (isDraft) {
         AsWfDraft asWfDraft = new AsWfDraft();
         asWfDraft.setCompoId(compoId);
         asWfDraft.setWfDraftName(inData.getName());
         asWfDraft.setUserId(userId);
         asWfDraft.setMasterTabId(compoId);
         asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
         workflowDao.insertAsWfdraft(asWfDraft);
       }*/
    } else {
      update(inData, requestMeta);
    }
    return inData;
  }

  private void update(SfCharge inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeMapper.updateByPrimaryKey(inData);
    /*chargeDetailMapper.deleteByPrimaryKey(inData.getChargeId());

    if (inData.getChargeDetaillst() != null) {
      for (int i = 0; i < inData.getChargeDetaillst().size(); i++) {
        SfChargeDetail m = (SfChargeDetail) inData.getChargeDetaillst().get(i);
        //        m.setChargeId(inData.getChargeId());
        chargeDetailMapper.insert(m);
      }
    }*/
  }

  private void insert(SfCharge inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeMapper.insert(inData);
    /*if (inData.getChargeDetaillst() != null) {
      for (int i = 0; i < inData.getChargeDetaillst().size(); i++) {
        SfChargeDetail m = (SfChargeDetail) inData.getChargeDetaillst().get(i);
        //        m.setChargeId(inData.getChargeId());
        chargeDetailMapper.insert(m);
      }
    }*/
  }

  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeMapper.deleteByPrimaryKey(id);
    //    chargeDetailMapper.deleteByPrimaryKey(id);
  }

  public SfCharge unAuditFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfCharge untreadFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfCharge auditFN(SfCharge qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx = saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getChargeId(), requestMeta);
  }

  public SfCharge newCommitFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getChargeId(), requestMeta);
  }

  public SfCharge callbackFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
