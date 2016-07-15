package com.ufgov.zc.server.zc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.zc.model.AnimationMeta;
import com.ufgov.zc.common.zc.model.RealMessage;
import com.ufgov.zc.server.zc.dao.IAnimationmetaDao;

public class AnimationMetaDao extends SqlMapClientDaoSupport implements IAnimationmetaDao {
  private final String namespace = "ZC_ANIMATION_META.";

  
  public List select(AnimationMeta meta) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "select", meta);
  }

  
  public void delete(AnimationMeta meta) {
    this.getSqlMapClientTemplate().delete(namespace + "delete", meta);
  }

  
  public void insert(AnimationMeta meta) {
    this.getSqlMapClientTemplate().insert(namespace + "insert", meta);
  }

  
  public void updateByKey(AnimationMeta meta) {
    this.getSqlMapClientTemplate().insert(namespace + "updateByKey", meta);
  }

  
  public List getCurrentDayBidding(Map param) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "getCurrentDayBidding", new HashMap());
  }

  
  public List getCurrentWeekBidding(Map param) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "getCurrentWeekBidding", new HashMap());
  }

  
  public List getLastWeekBidding(Map param) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "getLastWeekBidding", new HashMap());
  }

  
  public List getPurchaseInfo(Map param) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "getPurchaseInfo", new HashMap());
  }

  
  public List selectRealMessage(RealMessage mess) {
    return this.getSqlMapClientTemplate().queryForList(namespace + "selectRealMessage", mess);
  }

  
  public void insertRealMessage(RealMessage mess) {
    this.getSqlMapClientTemplate().insert(namespace + "insertRealMessage", mess);
  }

  
  public void deleteRealMessage(String ids) {
    this.getSqlMapClientTemplate().insert(namespace + "deleteRealMessage", ids);
  }

}
