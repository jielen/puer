/**
 * ZcEbZbDao.java
 * com.ufgov.zc.server.zc.dao
 * Administrator
 * 2010-7-16
 */
package com.ufgov.zc.server.zc.dao.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcebZb;
import com.ufgov.zc.common.zc.model.ZcebZbItem;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcEbZbDao;

/**
 * @author Administrator
 *
 */
public class ZcebZbDao extends SqlMapClientDaoSupport implements IZcEbZbDao {

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcEbZbDao#deleteByPrimaryKey(java.lang.String)
   */
  public void deleteByPrimaryKey(String zbCode) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().delete("ZcebZb.deleteZcebZb", zbCode);
    this.getSqlMapClientTemplate().delete("ZcebZb.deleteZcebZbItem", zbCode);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcEbZbDao#getZcebZb(com.ufgov.zc.common.system.dto.ElementConditionDto)
   */
  public List getZcebZb(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    List list = this.getSqlMapClientTemplate().queryForList("ZcebZb.getZcebZb", dto);
    for (int i = 0; i < list.size(); i++) {
      ZcebZb p = (ZcebZb) list.get(i);
      p.setItems(getZcebZbItem(p.getBidCode()));
    }
    return list;
  }

  public List getZcebZbItem(String zbCode) {
    // TCJLODO Auto-generated method stub
    return this.getSqlMapClientTemplate().queryForList("ZcebZb.getZcebZbItem", zbCode);

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcEbZbDao#insertZcebZb(com.ufgov.zc.common.zc.model.ZcebZb)
   */
  public void insertZcebZb(final ZcebZb zcebZb) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().insert("ZcebZb.insertZcebZb", zcebZb);
    for (int i = 0; i < zcebZb.getItems().size(); i++) {
      ZcebZbItem p = (ZcebZbItem) zcebZb.getItems().get(i);
      this.getSqlMapClientTemplate().insert("ZcebZb.insertZcebZbItem", p);
    }
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcEbZbDao#selectByPrimaryKey(java.lang.String)
   */
  public ZcebZb selectByPrimaryKey(String zbCode) {
    // TCJLODO Auto-generated method stub
    List items = this.getSqlMapClientTemplate().queryForList("ZcebZb.getZcebZbItem", zbCode);
    ZcebZb zcebZb = (ZcebZb) this.getSqlMapClientTemplate().queryForObject("ZcebZb.getZcebZbById", zbCode);
    if (zcebZb != null)
      zcebZb.setItems(items == null ? new ArrayList() : items);
    return zcebZb;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcEbZbDao#updateZcebZb(com.ufgov.zc.common.zc.model.ZcebZb)
   */
  public ZcebZb updateZcebZb(final ZcebZb zcebZb) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().update("ZcebZb.updateZcebZb", zcebZb);
    this.getSqlMapClientTemplate().delete("ZcebZb.deleteZcebZbItem", zcebZb.getBidCode());

    for (int i = 0; i < zcebZb.getItems().size(); i++) {
      ZcebZbItem p = (ZcebZbItem) zcebZb.getItems().get(i);
      this.getSqlMapClientTemplate().insert("ZcebZb.insertZcebZbItem", zcebZb.getItems().get(i));
    }

    return zcebZb;
  }

  public ZcebZb getZcebZbById(String bidCode) {
    // TCJLODO Auto-generated method stub
    return (ZcebZb) this.getSqlMapClientTemplate().queryForObject("ZcebZb.getZcebZbById", bidCode);
  }

  public BigDecimal getZcebZbBidSum(String makeCode) {
    BigDecimal bd = (BigDecimal) this.getSqlMapClientTemplate().queryForObject("ZcebZb.ZcebZbBidSumResult", makeCode);
    return bd;
  }

  public BigDecimal getOtherBidSums1(ZcebZb zb) {
    // TCJLODO Auto-generated method stub
    return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("ZcebZb.getOtherBidSums1", zb);
  }

  public BigDecimal getOtherBidSums2(ZcebZb zb) {
    // TCJLODO Auto-generated method stub
    return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("ZcebZb.getOtherBidSums2", zb);
  }

  public List getZcebZbHavingHt(String bidCode) {
    // TCJLODO Auto-generated method stub
    return this.getSqlMapClientTemplate().queryForList("ZcebZb.getZcebZbHavingHt", bidCode);
  }

}
