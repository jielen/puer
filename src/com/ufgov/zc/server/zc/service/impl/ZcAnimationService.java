package com.ufgov.zc.server.zc.service.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.model.AnimationMeta;
import com.ufgov.zc.common.zc.model.RealMessage;
import com.ufgov.zc.server.zc.dao.IAnimationmetaDao;
import com.ufgov.zc.server.zc.service.IZcAnimationService;

public class ZcAnimationService implements IZcAnimationService {
  private IAnimationmetaDao animationDao;

  public IAnimationmetaDao getAnimationDao() {
    return animationDao;
  }

  public void setAnimationDao(IAnimationmetaDao animationDao) {
    this.animationDao = animationDao;
  }

  
  public List select(AnimationMeta meta) {
    return animationDao.select(meta);
  }

  
  public void delete(AnimationMeta meta) {
    animationDao.delete(meta);
  }

  
  public void insert(AnimationMeta meta) {
    animationDao.insert(meta);
  }

  
  public void updateByKey(AnimationMeta meta) {
    animationDao.updateByKey(meta);
  }

  
  public List getCurrentDayBidding(Map param) {
    // TCJLODO Auto-generated method stub
    return animationDao.getCurrentDayBidding(param);
  }

  
  public List getCurrentWeekBidding(Map param) {
    // TCJLODO Auto-generated method stub
    return animationDao.getCurrentWeekBidding(param);
  }

  
  public List getLastWeekBidding(Map param) {
    // TCJLODO Auto-generated method stub
    return animationDao.getLastWeekBidding(param);
  }

  
  public List getPurchaseInfo(Map param) {
    // TCJLODO Auto-generated method stub
    return animationDao.getPurchaseInfo(param);
  }

  
  public List selectRealMessage(RealMessage mess) {
    // TCJLODO Auto-generated method stub
    return animationDao.selectRealMessage(mess);
  }

  
  public void insertRealMessage(RealMessage mess) {
    // TCJLODO Auto-generated method stub
    animationDao.insertRealMessage(mess);
  }

  
  public void deleteRealMessage(String ids) {
    // TCJLODO Auto-generated method stub
    animationDao.deleteRealMessage(ids);
  }

}
