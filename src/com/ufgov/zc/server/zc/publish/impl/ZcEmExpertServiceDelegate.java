package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.EmExpert;
import com.ufgov.zc.common.zc.publish.IZcEmExpertServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEmExpertService;

public class ZcEmExpertServiceDelegate implements IZcEmExpertServiceDelegate {

  private IZcEmExpertService zcEmExpertService;
  
  

  public IZcEmExpertService getZcEmExpertService() {
    return zcEmExpertService;
  }

  public void setZcEmExpertService(IZcEmExpertService zcEmExpertService) {
    this.zcEmExpertService = zcEmExpertService;
  }

  public List getEmExpertList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    return this.zcEmExpertService.getEmExpertList(dto, meta) ;
  }

  public EmExpert selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta)  throws SQLException {
    return this.zcEmExpertService.selectByPrimaryKey(zcPrimKey, requestMeta) ;
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta)  throws SQLException {
    this.zcEmExpertService.deleteByPrimaryKey(zcPrimKey, requestMeta) ;
  }

  public EmExpert updateByPrimaryKey(EmExpert zcBMerchandise, RequestMeta meta)  throws SQLException{
    return this.zcEmExpertService.updateByPrimaryKey(zcBMerchandise, meta) ;
  }

}
