package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.sf.publish.ISfOutInfoReqServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfOutInfoReqService;

public class SfOutInfoReqServiceDelegate implements ISfOutInfoReqServiceDelegate {

  private ISfOutInfoReqService outInfoReqService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoReqService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfOutInfoReq selectByPrimaryKey(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoReqService.selectByPrimaryKey(code, requestMeta);
  }

  
  public SfOutInfoReq saveFN(SfOutInfoReq inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoReqService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoReqService.deleteByPrimaryKeyFN(code, requestMeta);
  }

  public ISfOutInfoReqService getOutInfoReqService() {
    return outInfoReqService;
  }


  public void setOutInfoReqService(ISfOutInfoReqService outInfoReqService) {
    this.outInfoReqService = outInfoReqService;
  }

}
