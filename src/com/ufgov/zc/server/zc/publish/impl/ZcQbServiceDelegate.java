package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQb;
import com.ufgov.zc.common.zc.publish.IZcQbServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcQbService;

public class ZcQbServiceDelegate implements IZcQbServiceDelegate {
  private IZcQbService zcQbService;
  
  public List getQbLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.getQbLst(elementConditionDto, requestMeta);
  }

  
  public void cancelFn(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQbService.cancelFn(qb, requestMeta);
  }

  
  public ZcQb unAuditFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.unAuditFN(qb, requestMeta);
  }

  
  public ZcQb untreadFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.untreadFN(qb, requestMeta);
  }

  
  public ZcQb auditFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
   return  zcQbService.auditFN(qb, requestMeta);
  }

  
  public ZcQb updateFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return zcQbService.updateFN(qb, requestMeta);
  }

  
  public void commitFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQbService.commitFN(beanList, requestMeta);
  }

  
  public void deleteListFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQbService.deleteListFN(beanList, requestMeta);
  }

  
  public void deleteFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQbService.deleteFN(qb, requestMeta);
  }

  
  public ZcQb selectByPrimaryKey(String qbCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.selectByPrimaryKey(qbCode, requestMeta);
  }

  
  public ZcQb callbackFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.callbackFN(qb, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String qbCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQbService.deleteByPrimaryKeyFN(qbCode, requestMeta);
  }

  
  public ZcQb newCommitFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQbService.newCommitFN(qb, requestMeta);
  }


  public IZcQbService getZcQbService() {
    return zcQbService;
  }


  public void setZcQbService(IZcQbService zcQbService) {
    this.zcQbService = zcQbService;
  }


  
  public ZcQb sendPayFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return this.zcQbService.sendPayFN(qb,requestMeta);
  }


}
