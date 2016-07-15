/**
 * 
 */
package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQx;
import com.ufgov.zc.common.zc.publish.IZcQxServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcQxService;

/**
 * @author Administrator
 *
 */
public class ZcQxServiceDelegate implements IZcQxServiceDelegate {
  private IZcQxService zcQxService;
  
  public List getQxLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.getQxLst(elementConditionDto, requestMeta);
  }

  
  public void cancelFn(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQxService.cancelFn(qx, requestMeta);
  }

  
  public ZcQx unAuditFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.unAuditFN(qx, requestMeta);
  }

  
  public ZcQx untreadFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.untreadFN(qx, requestMeta);
  }

  
  public ZcQx auditFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
   return  zcQxService.auditFN(qx, requestMeta);
  }

  
  public ZcQx updateFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return zcQxService.updateFN(qx, requestMeta);
  }

  
  public void commitFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQxService.commitFN(beanList, requestMeta);
  }

  
  public void deleteListFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQxService.deleteListFN(beanList, requestMeta);
  }

  
  public void deleteFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQxService.deleteFN(qx, requestMeta);
  }

  
  public ZcQx selectByPrimaryKey(String qxCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.selectByPrimaryKey(qxCode, requestMeta);
  }

  
  public ZcQx callbackFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.callbackFN(qx, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String qxCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcQxService.deleteByPrimaryKeyFN(qxCode, requestMeta);
  }

  
  public ZcQx newCommitFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcQxService.newCommitFN(qx, requestMeta);
  }


  public IZcQxService getZcQxService() {
    return zcQxService;
  }


  public void setZcQxService(IZcQxService zcQxService) {
    this.zcQxService = zcQxService;
  }


  
  public ZcQx sendPayFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return this.zcQxService.sendPayFN(qx,requestMeta);
  }

}
