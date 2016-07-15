/**   
* @(#) project: zcxa
* @(#) file: IZcPProBalDao.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBal;

/**
* @ClassName: IZcPProBalDao
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午06:30:07
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public interface IZcPProBalDao {
  public List getZcPProBalList(ElementConditionDto dto);
  public ZcPProBal saveZcPProBal(ZcPProBal zcPProBal);
  public void deleteZcPProBal(String zcBalId);
  public ZcPProBal updateZcPProBal(ZcPProBal zcPProBal);
  public ZcPProBal selectByPrimaryKey(String zcHtId);
  public BigDecimal getSumZcBalSum(Map map);

}
