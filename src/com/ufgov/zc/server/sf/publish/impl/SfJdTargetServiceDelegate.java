package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.publish.ISfJdTargetServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdTargetService;

public class SfJdTargetServiceDelegate implements ISfJdTargetServiceDelegate {
  
  private ISfJdTargetService jdTargetService;
  
  public List getJdTargetLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetService.getEntrustorLst(elementConditionDto, requestMeta);
  }

  
  public SfJdTarget selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfJdTarget saveFN(SfJdTarget inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetService.saveFN(inData, requestMeta);
  }

  
  public boolean isUsing(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetService.isUsing(id, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdTargetService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfJdTargetService getJdTargetService() {
    return jdTargetService;
  }


  public void setJdTargetService(ISfJdTargetService jdTargetService) {
    this.jdTargetService = jdTargetService;
  }

}
