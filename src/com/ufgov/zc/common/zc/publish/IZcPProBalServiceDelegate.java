/**   
* @(#) project: zcxa
* @(#) file: IZcPProBalServiceDelegate.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.common.zc.publish;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBal;

/**
* @ClassName: IZcPProBalServiceDelegate
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午06:05:53
* @version: V1.0 
* @since: 1.0
* @author: fanpl
* @modify: 
*/
public interface IZcPProBalServiceDelegate extends Publishable {
  public List getZcPProBalList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcPProBalFN(ZcPProBal zcPProBal, RequestMeta meta);

  public ZcPProBal updateZcPProBalFN(ZcPProBal zcPProBal, RequestMeta meta);

  public ZcPProBal selectByPrimaryKey(String zcBalId, String isFrozen, RequestMeta requestMeta);

  public ZcPProBal newCommitFN(ZcPProBal currentObject, RequestMeta requestMeta) throws Exception;

  public ZcPProBal callbackFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal untreadFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal unAuditFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

  public ZcPProBal auditFN(ZcPProBal zcPProBal, String isFrozen, RequestMeta requestMeta);

  public BigDecimal getSumZcBalSum(Map map, RequestMeta requestMeta);

  public BigDecimal getSumZcBalBiSum(Map map, RequestMeta requestMeta);

  //送国库支付
  public boolean sendPayFN(ZcPProBal zcPProBal, String serverAdd, String isFrozen, RequestMeta requestMeta);

  //修改支付单
  public boolean editPayFN(ZcPProBal zcPProBal, String serverAdd, RequestMeta requestMeta);

  public ZcPProBal untreadToFirstFN(ZcPProBal zcPProBal, RequestMeta requestMeta);

}
