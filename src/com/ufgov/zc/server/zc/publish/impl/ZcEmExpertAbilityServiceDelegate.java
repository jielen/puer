package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.EmExpertAbility;
import com.ufgov.zc.common.zc.publish.IZcEmExpertAbilityServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEmExpertAbilityService;

public class ZcEmExpertAbilityServiceDelegate implements IZcEmExpertAbilityServiceDelegate {

  private IZcEmExpertAbilityService zcEmExpertAbilityService;
  


  public IZcEmExpertAbilityService getZcEmExpertAbilityService() {
    return zcEmExpertAbilityService;
  }

  public void setZcEmExpertAbilityService(IZcEmExpertAbilityService zcEmExpertAbilityService) {
    this.zcEmExpertAbilityService = zcEmExpertAbilityService;
  }

  public EmExpertAbility selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta)  throws SQLException {
    return this.zcEmExpertAbilityService.selectByPrimaryKey(zcPrimKey, requestMeta) ;
  }

  public EmExpertAbility updateByPrimaryKey(EmExpertAbility mainBean, RequestMeta meta)  throws SQLException{
    return this.zcEmExpertAbilityService.updateByPrimaryKey(mainBean, meta) ;
  }

}
