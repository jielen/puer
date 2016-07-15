package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.publish.ISfChargeStandardServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfChargeStandardService;

public class SfChargeStandardServiceDelegate implements ISfChargeStandardServiceDelegate {

  private ISfChargeStandardService chargeStandardService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeStandardService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfChargeStandard selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeStandardService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfChargeStandard saveFN(SfChargeStandard inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeStandardService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeStandardService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfChargeStandardService getChargeStandardService() {
    return chargeStandardService;
  }


  public void setChargeStandardService(ISfChargeStandardService chargeStandardService) {
    this.chargeStandardService = chargeStandardService;
  }

}
