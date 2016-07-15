package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.publish.ISfEvaluationServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfEvaluationService;

public class SfEvaluationServiceDelegate implements ISfEvaluationServiceDelegate {

  private ISfEvaluationService evaluationService;
  
  public List getEvaluationLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.getEvaluationLst(elementConditionDto, requestMeta);
  }

  
  public SfEvaluation selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfEvaluation saveFN(SfEvaluation inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    evaluationService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfEvaluation unAuditFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.unAuditFN(qx, requestMeta);
  }

  
  public SfEvaluation untreadFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.untreadFN(qx, requestMeta);
  }

  
  public SfEvaluation auditFN(SfEvaluation qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return evaluationService.auditFN(qx, requestMeta);
  }

  
  public SfEvaluation newCommitFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.newCommitFN(qx, requestMeta);
  }

  
  public SfEvaluation callbackFN(SfEvaluation qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return evaluationService.callbackFN(qx, requestMeta);
  }


  public ISfEvaluationService getEvaluationService() {
    return evaluationService;
  }


  public void setEvaluationService(ISfEvaluationService evaluationService) {
    this.evaluationService = evaluationService;
  }

}
