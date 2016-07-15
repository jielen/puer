package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.publish.ISfJdDocAuditServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdDocAuditService;

public class SfJdDocAuditServiceDelegate implements ISfJdDocAuditServiceDelegate {

  private ISfJdDocAuditService jdDocAuditService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfJdDocAudit selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfJdDocAudit saveFN(SfJdDocAudit inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocAuditService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfJdDocAudit unAuditFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.unAuditFN(qx, requestMeta);
  }

  
  public SfJdDocAudit untreadFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.untreadFN(qx, requestMeta);
  }

  
  public SfJdDocAudit auditFN(SfJdDocAudit qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.auditFN(qx, requestMeta);
  }

  
  public SfJdDocAudit newCommitFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.newCommitFN(qx, requestMeta);
  }

  
  public SfJdDocAudit callbackFN(SfJdDocAudit qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocAuditService.callbackFN(qx, requestMeta);
  }


  public ISfJdDocAuditService getJdDocAuditService() {
    return jdDocAuditService;
  }


  public void setJdDocAuditService(ISfJdDocAuditService jdDocAuditService) {
    this.jdDocAuditService = jdDocAuditService;
  }

}
