package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;
import com.ufgov.zc.common.sf.publish.ISfAppendMaterialNoticeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfAppendMaterialNoticeService;

public class SfAppendMaterialNoticeServiceDelegate implements ISfAppendMaterialNoticeServiceDelegate {

  private ISfAppendMaterialNoticeService appendMaterialNoticeService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfAppendMaterialNotice selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfAppendMaterialNotice saveFN(SfAppendMaterialNotice inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialNoticeService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfAppendMaterialNotice unAuditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.unAuditFN(qx, requestMeta);
  }

  
  public SfAppendMaterialNotice untreadFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.untreadFN(qx, requestMeta);
  }

  
  public SfAppendMaterialNotice auditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.auditFN(qx, requestMeta);
  }

  
  public SfAppendMaterialNotice newCommitFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.newCommitFN(qx, requestMeta);
  }

  
  public SfAppendMaterialNotice callbackFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return appendMaterialNoticeService.callbackFN(qx, requestMeta);
  }


  public ISfAppendMaterialNoticeService getAppendMaterialNoticeService() {
    return appendMaterialNoticeService;
  }


  public void setAppendMaterialNoticeService(ISfAppendMaterialNoticeService appendMaterialNoticeService) {
    this.appendMaterialNoticeService = appendMaterialNoticeService;
  }

}
