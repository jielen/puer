package com.ufgov.zc.server.zc.dao;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.model.AnimationMeta;
import com.ufgov.zc.common.zc.model.RealMessage;

public interface IAnimationmetaDao {
  public List select(AnimationMeta meta);

  public void delete(AnimationMeta meta);

  public void insert(AnimationMeta meta);

  public void updateByKey(AnimationMeta meta);

  public List getCurrentDayBidding(Map param);

  public List getCurrentWeekBidding(Map param);

  public List getLastWeekBidding(Map param);

  public List getPurchaseInfo(Map param);
  
  public List selectRealMessage(RealMessage mess);
  
  public void insertRealMessage(RealMessage mess);
  
  public void deleteRealMessage(String ids);

}
