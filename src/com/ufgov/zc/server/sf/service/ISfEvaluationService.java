package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfEvaluationService {

  List getEvaluationLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfEvaluation selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfEvaluation saveFN(SfEvaluation inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfEvaluation unAuditFN(SfEvaluation qx, RequestMeta requestMeta);

  SfEvaluation untreadFN(SfEvaluation qx, RequestMeta requestMeta);

  SfEvaluation auditFN(SfEvaluation qx, RequestMeta requestMeta) throws Exception;

  SfEvaluation newCommitFN(SfEvaluation qx, RequestMeta requestMeta);

  SfEvaluation callbackFN(SfEvaluation qx, RequestMeta requestMeta);

}
