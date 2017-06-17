package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.publish.ISfDocSendServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfDocSendService;

public class SfDocSendServiceDelegate implements ISfDocSendServiceDelegate {

  private ISfDocSendService sfDocSendService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfDocSendService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfDocSend selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    return sfDocSendService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfDocSend saveFN(SfDocSend inData, RequestMeta requestMeta) {
     
    return sfDocSendService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfDocSendService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfDocSend unAuditFN(SfDocSend qx, RequestMeta requestMeta) {
    return sfDocSendService.unAuditFN(qx, requestMeta);
  }

  
  public SfDocSend untreadFN(SfDocSend qx, RequestMeta requestMeta) {
    return sfDocSendService.untreadFN(qx, requestMeta);
  }

  
  public SfDocSend auditFN(SfDocSend qx, RequestMeta requestMeta) throws Exception {
    return sfDocSendService.auditFN(qx, requestMeta);
  }

  
  public SfDocSend newCommitFN(SfDocSend qx, RequestMeta requestMeta) {
    return sfDocSendService.newCommitFN(qx, requestMeta);
  }

  
  public SfDocSend callbackFN(SfDocSend qx, RequestMeta requestMeta) {
    return sfDocSendService.callbackFN(qx, requestMeta);
  }


  public ISfDocSendService getSfDocSendService() {
    return sfDocSendService;
  }


  public void setSfDocSendService(ISfDocSendService sfDocSendService) {
    this.sfDocSendService = sfDocSendService;
  }

}
