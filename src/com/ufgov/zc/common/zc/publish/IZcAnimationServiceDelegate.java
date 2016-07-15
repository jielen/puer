package com.ufgov.zc.common.zc.publish;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.AnimationMeta;
import com.ufgov.zc.common.zc.model.RealMessage;

public interface IZcAnimationServiceDelegate extends Publishable {
  public List select(AnimationMeta meta, RequestMeta rm);

  public void delete(AnimationMeta meta, RequestMeta rm);

  public void insert(AnimationMeta meta, RequestMeta rm);

  public void updateByKey(AnimationMeta meta, RequestMeta rm);

  public List getCurrentDayBidding(Map param, RequestMeta rm);
  
  public List getCurrentWeekBidding(Map param, RequestMeta rm);
  
  public List getLastWeekBidding(Map param, RequestMeta rm);
  
  public List getPurchaseInfo(Map param, RequestMeta rm);
  
  public List selectRealMessage(RealMessage mess, RequestMeta rm);
  
  public void insertRealMessage(RealMessage mess, RequestMeta rm);
  
  public void deleteRealMessage(String ids, RequestMeta rm);

}
