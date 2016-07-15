/**   
* @(#) project: zcxa
* @(#) file: IZcPProBalBiDao.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.model.ZcPProBalBi;

/**
* @ClassName: IZcPProBalBiDao
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-4 上午08:52:25
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public interface IZcPProBalBiDao {
  public List getZcPProBalBiList(String zcBalId);

  public ZcPProBalBi insertZcPProBalBi(ZcPProBalBi zcPProBalbi);

  public void deleteZcPProBalBiByBalId(String balId);

  public BigDecimal getSumZcBalBiSum(Map map);

  public List getBalBudget(String zcBalId);

}
