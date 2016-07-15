package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.publish.ISfAgreementServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfAgreementService;

public class SfAgreementServiceDelegate implements ISfAgreementServiceDelegate {

  private ISfAgreementService agreementService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfAgreement selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfAgreement saveFN(SfAgreement inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    agreementService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfAgreement unAuditFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.unAuditFN(qx, requestMeta);
  }

  
  public SfAgreement untreadFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.untreadFN(qx, requestMeta);
  }

  
  public SfAgreement auditFN(SfAgreement qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return agreementService.auditFN(qx, requestMeta);
  }

  
  public SfAgreement newCommitFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.newCommitFN(qx, requestMeta);
  }

  
  public SfAgreement callbackFN(SfAgreement qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return agreementService.callbackFN(qx, requestMeta);
  }


  public ISfAgreementService getAgreementService() {
    return agreementService;
  }


  public void setAgreementService(ISfAgreementService agreementService) {
    this.agreementService = agreementService;
  }

}
