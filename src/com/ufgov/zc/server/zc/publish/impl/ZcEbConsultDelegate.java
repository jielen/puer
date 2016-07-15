package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbConsult;
import com.ufgov.zc.common.zc.publish.IZcEbConsultDelegate;
import com.ufgov.zc.server.zc.service.IZcEbConsultService;

public class ZcEbConsultDelegate implements IZcEbConsultDelegate {

  private IZcEbConsultService zcEbConsultService;

  public IZcEbConsultService getZcEbConsultService() {
    return zcEbConsultService;
  }

  public void setZcEbConsultService(IZcEbConsultService zcEbConsultService) {
    this.zcEbConsultService = zcEbConsultService;
  }

  public List findConsultList(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.findConsultList(dto, meta);
  }

  public ZcEbConsult findConsultById(String id, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.findConsultById(id, meta);
  }

  public void deleteConsultById(String id, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    zcEbConsultService.deleteConsultById(id, meta);
  }

  public void deleteConsultByIds(List ids, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    zcEbConsultService.deleteConsultByIds(ids, meta);
  }

  public ZcEbConsult updateConsult(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.updateConsult(bean, meta);
  }

  public ZcEbConsult newCommitFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.newCommitFN(bean, meta);
  }

  public ZcEbConsult auditFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.auditFN(bean, meta);
  }

  public ZcEbConsult untreadFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.untreadFN(bean, meta);
  }

  public ZcEbConsult callbackFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.callbackFN(bean, meta);
  }

  public ZcEbConsult unAuditFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbConsultService.unAuditFN(bean, meta);
  }

}
