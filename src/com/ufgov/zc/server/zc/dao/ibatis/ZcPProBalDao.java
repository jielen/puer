/**   
* @(#) project: zcxa
* @(#) file: ZcPProBalDao.java
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

import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.server.zc.dao.IZcPProBalDao;

/**
* @ClassName: ZcPProBalDao
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: 2010-8-2 下午06:30:59
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
public class ZcPProBalDao extends SqlMapClientDaoSupport implements IZcPProBalDao {

  public List getZcPProBalList(ElementConditionDto dto) {
    return this.getSqlMapClientTemplate().queryForList("ZC_P_PRO_BAL.getZcPProBalList", dto);
  }

  public void deleteZcPProBal(String zcBalId) {
    this.getSqlMapClientTemplate().delete("ZC_P_PRO_BAL.deleteZcPProBalById", zcBalId);
  }

  public ZcPProBal saveZcPProBal(ZcPProBal zcPProBal) {
    this.getSqlMapClientTemplate().insert("ZC_P_PRO_BAL.insertZcPProBal", zcPProBal);
    return zcPProBal;
  }

  public ZcPProBal updateZcPProBal(ZcPProBal zcPProBal) {
    this.getSqlMapClientTemplate().update("ZC_P_PRO_BAL.updateZcPProBal", zcPProBal);
    return zcPProBal;

  }

  public ZcPProBal selectByPrimaryKey(String zcHtId) {
    return (ZcPProBal) this.getSqlMapClientTemplate().queryForObject("ZC_P_PRO_BAL.getZcPProBalByPrimaryKey", zcHtId);

  }

  public BigDecimal getSumZcBalSum(Map map) {
    BigDecimal bigDecimal = new BigDecimal("0.0");
    bigDecimal = (BigDecimal) this.getSqlMapClientTemplate().queryForObject("ZC_P_PRO_BAL.getSumZcBalSum", map);
    return bigDecimal;
  }

}
