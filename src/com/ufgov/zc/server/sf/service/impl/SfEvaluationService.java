package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfEvaluationMapper;
import com.ufgov.zc.server.sf.dao.SfEvaluationPersonMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfEvaluationService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfEvaluationService implements ISfEvaluationService {

  private SfEvaluationMapper evaluationMapper;

  private SfEvaluationPersonMapper evaluationPersonMapper;

  private IWorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  private ISfEntrustService sfEntrustService;

  public SfEvaluationMapper getEvaluationMapper() {
    return evaluationMapper;
  }

  public void setEvaluationMapper(SfEvaluationMapper evaluationMapper) {
    this.evaluationMapper = evaluationMapper;
  }

  public SfEvaluationPersonMapper getEvaluationPersonMapper() {
    return evaluationPersonMapper;
  }

  public void setEvaluationPersonMapper(SfEvaluationPersonMapper evaluationPersonMapper) {
    this.evaluationPersonMapper = evaluationPersonMapper;
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

  public List getEvaluationLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationMapper.getEvaluationLst(elementConditionDto);
  }

  public SfEvaluation selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfEvaluation rtn = evaluationMapper.selectByPrimaryKey(id);
    rtn.setEvaluationPersons(evaluationPersonMapper.selectByPrimaryKey(id));
    rtn.setEntrust(sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta));
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public SfEvaluation saveFN(SfEvaluation inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    // TCJLODO Auto-generated method stub
    if (inData.getEvaluationId() == null) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id = new BigDecimal(su.getNextVal(SfEvaluation.SEQ_SF_EVALUATION_ID));
      inData.setEvaluationId(id);

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();

      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }
      insert(inData, requestMeta);

      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getName());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
    } else {
      update(inData, requestMeta);
    }
    sfEntrustService.saveFN(inData.getEntrust(), requestMeta);
    return inData;
  }

  private void update(SfEvaluation inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    evaluationMapper.updateByPrimaryKey(inData);
    evaluationPersonMapper.deleteByPrimaryKey(inData.getEvaluationId());

    if (inData.getEvaluationPersons() != null) {
      for (int i = 0; i < inData.getEvaluationPersons().size(); i++) {
        SfEvaluationPerson m = (SfEvaluationPerson) inData.getEvaluationPersons().get(i);
        m.setEvaluationId(inData.getEvaluationId());
        evaluationPersonMapper.insert(m);
      }
    }
  }

  private void insert(SfEvaluation inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    evaluationMapper.insert(inData);
    if (inData.getEvaluationPersons() != null) {
      for (int i = 0; i < inData.getEvaluationPersons().size(); i++) {
        SfEvaluationPerson m = (SfEvaluationPerson) inData.getEvaluationPersons().get(i);
        m.setEvaluationId(inData.getEvaluationId());
        evaluationPersonMapper.insert(m);
      }
    }
  }

  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    evaluationMapper.deleteByPrimaryKey(id);
    evaluationPersonMapper.deleteByPrimaryKey(id);
  }

  public SfEvaluation unAuditFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfEvaluation untreadFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfEvaluation auditFN(SfEvaluation qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx = saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getEvaluationId(), requestMeta);
  }

  public SfEvaluation newCommitFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getEvaluationId(), requestMeta);
  }

  public SfEvaluation callbackFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public ISfEntrustService getSfEntrustService() {
    return sfEntrustService;
  }

  public void setSfEntrustService(ISfEntrustService sfEntrustService) {
    this.sfEntrustService = sfEntrustService;
  }

}
