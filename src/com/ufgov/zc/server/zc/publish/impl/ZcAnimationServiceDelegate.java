package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.AnimationMeta;
import com.ufgov.zc.common.zc.model.RealMessage;
import com.ufgov.zc.common.zc.publish.IZcAnimationServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcAnimationService;

public class ZcAnimationServiceDelegate implements IZcAnimationServiceDelegate {
  private IZcAnimationService animationSerivce;

  public IZcAnimationService getAnimationSerivce() {
    return animationSerivce;
  }

  public void setAnimationSerivce(IZcAnimationService animationSerivce) {
    this.animationSerivce = animationSerivce;
  }

  
  public List select(AnimationMeta meta, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.select(meta);
  }

  
  public void delete(AnimationMeta meta, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    animationSerivce.delete(meta);
  }

  
  public void insert(AnimationMeta meta, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    animationSerivce.insert(meta);
  }

  
  public void updateByKey(AnimationMeta meta, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    animationSerivce.updateByKey(meta);
  }

  
  public List getCurrentDayBidding(Map param, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.getCurrentDayBidding(param);
  }

  
  public List getCurrentWeekBidding(Map param, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.getCurrentWeekBidding(param);
  }

  
  public List getLastWeekBidding(Map param, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.getLastWeekBidding(param);
  }

  
  public List getPurchaseInfo(Map param, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.getPurchaseInfo(param);
  }

  
  public List selectRealMessage(RealMessage mess, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    return animationSerivce.selectRealMessage(mess);
  }

  
  public void insertRealMessage(RealMessage mess, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    animationSerivce.insertRealMessage(mess);
  }

  
  public void deleteRealMessage(String ids, RequestMeta rm) {
    // TCJLODO Auto-generated method stub
    animationSerivce.deleteRealMessage(ids);
  }

}
