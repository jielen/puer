package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.publish.ISfChargeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfChargeService;

public class SfChargeServiceDelegate implements ISfChargeServiceDelegate {

  private ISfChargeService chargeService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfCharge selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfCharge saveFN(SfCharge inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfCharge unAuditFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.unAuditFN(qx, requestMeta);
  }

  
  public SfCharge untreadFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.untreadFN(qx, requestMeta);
  }

  
  public SfCharge auditFN(SfCharge qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return chargeService.auditFN(qx, requestMeta);
  }

  
  public SfCharge newCommitFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.newCommitFN(qx, requestMeta);
  }

  
  public SfCharge callbackFN(SfCharge qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeService.callbackFN(qx, requestMeta);
  }


  public ISfChargeService getChargeService() {
    return chargeService;
  }


  public void setChargeService(ISfChargeService chargeService) {
    this.chargeService = chargeService;
  }

}
