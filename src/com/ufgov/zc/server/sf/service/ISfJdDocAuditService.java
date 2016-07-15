package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdDocAuditService {


  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdDocAudit selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfJdDocAudit saveFN(SfJdDocAudit inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfJdDocAudit unAuditFN(SfJdDocAudit qx, RequestMeta requestMeta);

  SfJdDocAudit untreadFN(SfJdDocAudit qx, RequestMeta requestMeta);

  SfJdDocAudit auditFN(SfJdDocAudit qx, RequestMeta requestMeta) throws Exception;

  SfJdDocAudit newCommitFN(SfJdDocAudit qx, RequestMeta requestMeta);

  SfJdDocAudit callbackFN(SfJdDocAudit qx, RequestMeta requestMeta);

}
