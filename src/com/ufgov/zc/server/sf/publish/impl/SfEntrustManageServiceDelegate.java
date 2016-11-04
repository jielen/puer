package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.sf.publish.ISfEntrustManageServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfEntrustManageService;

public class SfEntrustManageServiceDelegate implements ISfEntrustManageServiceDelegate {
  
  private ISfEntrustManageService sfEntrustManageService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfEntrustManageService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfEntrustManage selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    return sfEntrustManageService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfEntrustManage saveFN(SfEntrustManage inData, RequestMeta requestMeta) {
    return sfEntrustManageService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfEntrustManageService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfEntrustManage unAuditFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return sfEntrustManageService.unAuditFN(qx, requestMeta);
  }

  
  public SfEntrustManage untreadFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return sfEntrustManageService.untreadFN(qx, requestMeta);
  }

  
  public SfEntrustManage auditFN(SfEntrustManage qx, RequestMeta requestMeta) throws Exception {
    return sfEntrustManageService.auditFN(qx, requestMeta);
  }

  
  public SfEntrustManage newCommitFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return sfEntrustManageService.newCommitFN(qx, requestMeta);
  }

  
  public SfEntrustManage callbackFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return sfEntrustManageService.callbackFN(qx, requestMeta);
  }


  public ISfEntrustManageService getSfEntrustManageService() {
    return sfEntrustManageService;
  }


  public void setSfEntrustManageService(ISfEntrustManageService sfEntrustManageService) {
    this.sfEntrustManageService = sfEntrustManageService;
  }

}
