package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.publish.ISfEntrustorServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfEntrustorService;

public class SfEntrustorServiceDelegate implements ISfEntrustorServiceDelegate {

  private ISfEntrustorService entrustorService;
  
  public List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorService.getEntrustorLst(elementConditionDto, requestMeta);
  }

  
  public SfEntrustor selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfEntrustor saveFN(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorService.saveFN(inData, requestMeta);
  }

  
  public boolean isUsing(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorService.isUsing(id, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfEntrustorService getEntrustorService() {
    return entrustorService;
  }


  public void setEntrustorService(ISfEntrustorService entrustorService) {
    this.entrustorService = entrustorService;
  }


   
  public SfEntrustor selectByName(String name, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorService.selectByName(name, requestMeta);
  }


}
