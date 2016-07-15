package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.sf.publish.ISfOutInfoTypeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfOutInfoTypeService;

public class SfOutInfoTypeServiceDelegate implements ISfOutInfoTypeServiceDelegate {

  private ISfOutInfoTypeService outInfoTypeService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoTypeService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfOutInfoType selectByPrimaryKey(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoTypeService.selectByPrimaryKey(code, requestMeta);
  }

  
  public SfOutInfoType saveFN(SfOutInfoType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoTypeService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoTypeService.deleteByPrimaryKeyFN(code, requestMeta);
  }


  public ISfOutInfoTypeService getOutInfoTypeService() {
    return outInfoTypeService;
  }


  public void setOutInfoTypeService(ISfOutInfoTypeService outInfoTypeService) {
    this.outInfoTypeService = outInfoTypeService;
  }

}
