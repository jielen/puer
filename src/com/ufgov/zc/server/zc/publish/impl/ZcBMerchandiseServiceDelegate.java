package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBMerchandise;
import com.ufgov.zc.common.zc.publish.IZcBMerchandiseServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcBMerchandiseService;

public class ZcBMerchandiseServiceDelegate implements IZcBMerchandiseServiceDelegate {

  private IZcBMerchandiseService zcBMerchandiseService;

  public IZcBMerchandiseService getZcBMerchandiseService() {
    return zcBMerchandiseService;
  }

  public void setZcBMerchandiseService(IZcBMerchandiseService zcBMerchandiseService) {
    this.zcBMerchandiseService = zcBMerchandiseService;
  }

  public List getMerchandiseList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    return this.zcBMerchandiseService.getMerchandiseList(dto, meta);
  }

  public ZcBMerchandise selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    return this.zcBMerchandiseService.selectByPrimaryKey(zcPrimKey, requestMeta);
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    this.zcBMerchandiseService.deleteByPrimaryKey(zcPrimKey, requestMeta);
  }

  public ZcBMerchandise updateByPrimaryKey(ZcBMerchandise zcBMerchandise, RequestMeta meta) throws SQLException {
    return this.zcBMerchandiseService.updateByPrimaryKey(zcBMerchandise, meta);
  }

  
  public void deleteMerchandise(ZcBMerchandise merchandise, RequestMeta meta) throws Exception {
    // TCJLODO Auto-generated method stub
    zcBMerchandiseService.deleteMerchandise(merchandise, meta);
  }

}
