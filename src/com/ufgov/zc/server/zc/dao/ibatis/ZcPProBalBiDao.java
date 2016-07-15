/**   
* @(#) project: zcxa
* @(#) file: ZcPProBalBiDao.java
* 
* Copyright 2010 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.server.zc.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.zc.model.ZcPProBalBi;
import com.ufgov.zc.server.zc.dao.IZcPProBalBiDao;

/**
* @ClassName: ZcPProBalBiDao
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-4 上午08:55:56
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public class ZcPProBalBiDao extends SqlMapClientDaoSupport implements IZcPProBalBiDao {

  public List getZcPProBalBiList(String zcBalId) {
    return this.getSqlMapClientTemplate().queryForList("ZC_P_PRO_BAL_BI.selectZcPProBiList", zcBalId);
  }

  public void deleteZcPProBalBiByBalId(String zcBalId) {
    this.getSqlMapClientTemplate().delete("ZC_P_PRO_BAL_BI.deleteZcPProBiByBiNo", zcBalId);
  }

  public ZcPProBalBi insertZcPProBalBi(ZcPProBalBi zcPProBalBi) {
    this.getSqlMapClientTemplate().insert("ZC_P_PRO_BAL_BI.insertZcPProBi", zcPProBalBi);
    return zcPProBalBi;
  }

  public BigDecimal getSumZcBalBiSum(Map map) {
    BigDecimal bigDecimal = new BigDecimal("0.0");
    bigDecimal = (BigDecimal) this.getSqlMapClientTemplate().queryForObject("ZC_P_PRO_BAL_BI.getSumZcBalBiSum", map);
    return bigDecimal;
  }

public List getBalBudget(String zcBalId) {
    return this.getSqlMapClientTemplate().queryForList("ZC_P_PRO_BAL_BI.getBalBudget", zcBalId);
}
}
