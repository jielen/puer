package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfEntrustService {

  List getEntrustLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfEntrust selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfEntrust saveFN(SfEntrust inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfEntrust unAuditFN(SfEntrust qx, RequestMeta requestMeta);

  SfEntrust untreadFN(SfEntrust qx, RequestMeta requestMeta);

  SfEntrust auditFN(SfEntrust qx, RequestMeta requestMeta) throws Exception;

  SfEntrust newCommitFN(SfEntrust qx, RequestMeta requestMeta);

  SfEntrust callbackFN(SfEntrust qx, RequestMeta requestMeta);

}
