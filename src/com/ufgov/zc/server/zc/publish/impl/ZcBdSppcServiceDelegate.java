package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBdSppc;
import com.ufgov.zc.common.zc.publish.IZcBdSppcServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcBdSppcService;

public class ZcBdSppcServiceDelegate implements IZcBdSppcServiceDelegate {

  private IZcBdSppcService zcBdSppcService;

  public IZcBdSppcService getZcBdSppcService() {
    return zcBdSppcService;
  }

  public void setZcBdSppcService(IZcBdSppcService zcBdSppcService) {
    this.zcBdSppcService = zcBdSppcService;
  }

  public List getZcBdSppcList(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    return this.zcBdSppcService.getZcBdSppcList(dto, meta);
  }

  public ZcBdSppc selectByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    return this.zcBdSppcService.selectByPrimaryKey(zcPrimKey, requestMeta);
  }

  public void deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta) throws SQLException {
    this.zcBdSppcService.deleteByPrimaryKey(zcPrimKey, requestMeta);
  }

  public ZcBdSppc updateZcBdSppcByPrimaryKey(ZcBdSppc zcBdSppc, RequestMeta meta) throws SQLException {
    return this.zcBdSppcService.updateZcBdSppcByPrimaryKey(zcBdSppc, meta);
  }

}
