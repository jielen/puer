package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcYearPlan;

public interface IZcYearEndServiceDelegate extends Publishable {

  public List getZcPProMake(ElementConditionDto dto, RequestMeta meta) throws Exception;

  public void updateProEnd(String zcMakeCode, boolean flag, String serverAdd, RequestMeta requestMeta) throws Exception;

  public void updateYearEnd(String zcMakeCode, RequestMeta requestMeta) throws Exception;

  /**
   * 获取锁定后的项目
   * @param dto 查询条件
   * @param meta 
   * @return 项目集合
   * @throws Exception
   */
  public List getZcCarraryMake(ElementConditionDto dto, RequestMeta meta) throws Exception;

  /**
   * 设置项目状态
   * @param dto 查询条件
   * @param meta 
   * @throws Exception
   */
  public void updateMakeYepFlagByFlag(ElementConditionDto dto,String serverAdd, RequestMeta meta) throws Exception;

  /**
   * 结转立项功能
   * @param dto 条件
   * @param meta
   * @throws Exception
   */
  public void carraryNewMake(ElementConditionDto dto,String serverAdd, RequestMeta meta) throws Exception;

  /**
   * 手动结转立项
   * @param yearPlan
   * @param serverAdd
   * @param meta
   * @throws Exception
   */
  public void carraryNewMakeByManual(ZcYearPlan yearPlan,String serverAdd, RequestMeta meta) throws Exception;
}
