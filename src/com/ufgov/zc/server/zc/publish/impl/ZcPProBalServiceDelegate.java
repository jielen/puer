/**   
* @(#) project: zcxa
* @(#) file: ZcPProBalServiceDelegate.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.publish.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.common.zc.publish.IZcPProBalServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcPProBalService;

/**
* @ClassName: ZcPProBalServiceDelegate
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午06:26:32
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public class ZcPProBalServiceDelegate implements IZcPProBalServiceDelegate {

  private IZcPProBalService zcPProBalService;

  public IZcPProBalService getZcPProBalService() {
    return zcPProBalService;
  }

  public void setZcPProBalService(IZcPProBalService zcPProBalService) {
    this.zcPProBalService = zcPProBalService;
  }

  public List getZcPProBalList(ElementConditionDto dto, RequestMeta requestMeta) {
    return zcPProBalService.getZcPProBalList(dto, requestMeta);
  }

  public ZcPProBal selectByPrimaryKey(String zcBalId, String isFrozen, RequestMeta requestMeta) {
    return zcPProBalService.selectByPrimaryKey(zcBalId, isFrozen);
  }

  public void deleteZcPProBalFN(ZcPProBal zcPProBal, RequestMeta meta) {
    zcPProBalService.deleteByPrimaryKey(zcPProBal.getZcBalId());
  }

  public ZcPProBal updateZcPProBalFN(ZcPProBal zcPProBal, RequestMeta meta) {
    return zcPProBalService.updateZcPProBal(zcPProBal, meta);
  }

  public ZcPProBal callbackFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    return zcPProBalService.callbackFN(zcPProBal, requestMeta);
  }

  public ZcPProBal newCommitFN(ZcPProBal currentObject, RequestMeta requestMeta) throws Exception {
    return zcPProBalService.newCommitFN(currentObject, requestMeta);
  }

  public BigDecimal getSumZcBalSum(Map map, RequestMeta requestMeta) {
    return zcPProBalService.getSumZcBalSum(map);
  }

  public BigDecimal getSumZcBalBiSum(Map map, RequestMeta requestMeta) {
    return zcPProBalService.getSumZcBalBiSum(map);
  }

  public ZcPProBal auditFN(ZcPProBal zcPProBal, String isFrozen, RequestMeta requestMeta) {
    ZcPProBal zcpro = zcPProBalService.auditFN(zcPProBal, isFrozen, requestMeta);

    return zcpro;

  }

  public ZcPProBal unAuditFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {

    return zcPProBalService.unAuditFN(zcPProBal, requestMeta);
  }

  public ZcPProBal untreadFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    return zcPProBalService.untreadFN(zcPProBal, requestMeta);
  }

  //送国库
  public boolean sendPayFN(ZcPProBal zcPProBal, String serverAdd, String isFrozen, RequestMeta requestMeta) {
    boolean res = false;
    try{
      res = zcPProBalService.sendPay(zcPProBal, serverAdd, isFrozen, requestMeta);
    }catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }finally{
      zcPProBalService.updateAfterPay(zcPProBal, requestMeta);
    }
    return res;
  }

  public boolean editPayFN(ZcPProBal zcPProBal, String serverAdd, RequestMeta requestMeta) {
    return zcPProBalService.editPay(zcPProBal, serverAdd, requestMeta);

  }

  
  public ZcPProBal untreadToFirstFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcPProBalService.untreadToFirstFN(zcPProBal, requestMeta);
  }

}
