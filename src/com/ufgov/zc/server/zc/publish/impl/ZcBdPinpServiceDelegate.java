package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBBrand;
import com.ufgov.zc.common.zc.publish.IZcBdPinpServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcBdPinpService;

public class ZcBdPinpServiceDelegate implements IZcBdPinpServiceDelegate {

  private IZcBdPinpService zcBdPinpService;

  public IZcBdPinpService getZcBdPinpService() {
    return zcBdPinpService;
  }

  public void setZcBdPinpService(IZcBdPinpService zcBdPinpService) {
    this.zcBdPinpService = zcBdPinpService;
  }

  public List getPinpList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    return this.zcBdPinpService.getPinpList(dto, meta);
  }

  public ZcBBrand selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    return this.zcBdPinpService.selectByPrimaryKey(zcPrimKey, requestMeta);
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    this.zcBdPinpService.deleteByPrimaryKey(zcPrimKey, requestMeta);
  }

  public ZcBBrand updateByPrimaryKey(ZcBBrand zcBBrand, RequestMeta meta) throws SQLException {
    return this.zcBdPinpService.updateByPrimaryKey(zcBBrand, meta);
  }

}
