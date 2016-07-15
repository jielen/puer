package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.sf.publish.ISfOutInfoServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfOutInfoService;

public class SfOutInfoServiceDelegate implements ISfOutInfoServiceDelegate {

  private ISfOutInfoService outInfoService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfOutInfo selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfOutInfo saveFN(SfOutInfo inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public SfOutInfo unAuditFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.unAuditFN(qx, requestMeta);
  }

  
  public SfOutInfo untreadFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.untreadFN(qx, requestMeta);
  }

  
  public SfOutInfo auditFN(SfOutInfo qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return outInfoService.auditFN(qx, requestMeta);
  }

  
  public SfOutInfo newCommitFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.newCommitFN(qx, requestMeta);
  }

  
  public SfOutInfo callbackFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoService.callbackFN(qx, requestMeta);
  }


  public ISfOutInfoService getOutInfoService() {
    return outInfoService;
  }


  public void setOutInfoService(ISfOutInfoService outInfoService) {
    this.outInfoService = outInfoService;
  }

}
