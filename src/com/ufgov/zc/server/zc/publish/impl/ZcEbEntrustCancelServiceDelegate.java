package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.publish.IZcEbEntrustCancelServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEbEntrustCancelService;

public class ZcEbEntrustCancelServiceDelegate implements IZcEbEntrustCancelServiceDelegate {

  private IZcEbEntrustCancelService zcEbEntrustCancelService;
  
  public IZcEbEntrustCancelService getZcEbEntrustCancelService() {
    return zcEbEntrustCancelService;
  }

  public void setZcEbEntrustCancelService(IZcEbEntrustCancelService zcEbEntrustCancelService) {
    this.zcEbEntrustCancelService = zcEbEntrustCancelService;
  }

  
  public List selectZcEbEntrustCancel(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.selectZcEbEntrustCancel(elementConditionDto, requestMeta);
  }

  
  public ZcEbEntrustCancel selectByKey(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.selectByKey(code);
  }

  
  public void deleteZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcEbEntrustCancelService.deleteZcEbEntrustCancelFN(bean);
  }

  
  public void deleteZcEbEntrustCancelListFN(List list, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcEbEntrustCancelService.deleteZcEbEntrustCancelListFN(list);
  }

  
  public ZcEbEntrustCancel newCommitFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception  {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.newCommitFN(bean, requestMeta);
  }

  
  public ZcEbEntrustCancel untreadFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.untreadFN(bean, requestMeta);
  }

  
  public ZcEbEntrustCancel unAuditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.unAuditFN(bean, requestMeta);
  }

  
  public ZcEbEntrustCancel auditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception  {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.auditFN(bean, requestMeta);
  }

  
  public ZcEbEntrustCancel callbackFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.callbackFN(bean, requestMeta);
  }

  
  public ZcEbEntrustCancel saveZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return zcEbEntrustCancelService.saveZcEbEntrustCancelFN(bean, requestMeta);
  }

}
