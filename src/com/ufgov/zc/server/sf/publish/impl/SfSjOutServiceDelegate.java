package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.sf.publish.ISfSjOutServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfSjOutService;

public class SfSjOutServiceDelegate implements ISfSjOutServiceDelegate {

  private ISfSjOutService sfSjOutService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfSjOutService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfSjOut selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    return sfSjOutService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfSjOut saveFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.saveFN(bill, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) throws SfBusinessException {
    sfSjOutService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfSjOut unAuditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.unAuditFN(bill, requestMeta);
  }

  
  public SfSjOut untreadFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.untreadFN(bill, requestMeta);
  }

  
  public SfSjOut auditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.auditFN(bill, requestMeta);
  }

  
  public SfSjOut newCommitFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.newCommitFN(bill, requestMeta);
  }

  
  public SfSjOut callbackFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjOutService.callbackFN(bill, requestMeta);
  }


  public ISfSjOutService getSfSjOutService() {
    return sfSjOutService;
  }


  public void setSfSjOutService(ISfSjOutService sfSjOutService) {
    this.sfSjOutService = sfSjOutService;
  }

}
