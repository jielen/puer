package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.sf.publish.ISfSjInServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfSjInService;

public class SfSjInServiceDelegate implements ISfSjInServiceDelegate {

  private ISfSjInService sfSjInService;
  
  public List getMainLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfSjInService.getMainLst(elementConditionDto, requestMeta);
  }

  
  public SfSjIn selectByPrimaryKey(BigDecimal InId, RequestMeta requestMeta) {
    return sfSjInService.selectByPrimaryKey(InId, requestMeta);
  }

  
  public SfSjIn saveFN(SfSjIn inData, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjInService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal InId, RequestMeta requestMeta) throws SfBusinessException {
    sfSjInService.deleteByPrimaryKeyFN(InId, requestMeta);
  }


  public ISfSjInService getSfSjInService() {
    return sfSjInService;
  }


  public void setSfSjInService(ISfSjInService sfSjInService) {
    this.sfSjInService = sfSjInService;
  }

}
