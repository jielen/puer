package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfSjOutService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfSjOut selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfSjOut saveFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) throws SfBusinessException;

  SfSjOut unAuditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;

  SfSjOut untreadFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;

  SfSjOut auditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;

  SfSjOut newCommitFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;

  SfSjOut callbackFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException;
}
