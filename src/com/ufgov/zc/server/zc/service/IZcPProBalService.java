/**   
* @(#) project: zcxa
* @(#) file: IZcPProBalService.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBal;

/**
* @ClassName: IZcPProBalService
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午06:27:36
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public interface IZcPProBalService {
  public List getZcPProBalList(ElementConditionDto dto, RequestMeta requestMeta);

  void deleteByPrimaryKey(String zcBalId);

  public ZcPProBal updateZcPProBal(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal selectByPrimaryKey(String zcBalId, String isFrozen);

  ZcPProBal callbackFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  ZcPProBal newCommitFN(ZcPProBal currentObject, RequestMeta requestMeta) throws Exception;

  public ZcPProBal untreadFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal unAuditFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal auditFN(ZcPProBal zcPProBal, String isFrozen, RequestMeta requestMeta);

  BigDecimal getSumZcBalSum(Map map);

  BigDecimal getSumZcBalBiSum(Map map);

  public boolean sendPay(ZcPProBal zcPProBal, String serverAdd, String isFrozen, RequestMeta requestMeta);

  public boolean editPay(ZcPProBal zcPProBal, String serverAdd, RequestMeta requestMeta);

  public void updateAfterPaySuccess(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public void updateAfterPay(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal untreadToFirstFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

}
