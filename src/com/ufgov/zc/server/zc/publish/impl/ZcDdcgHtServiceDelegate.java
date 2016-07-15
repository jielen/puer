package com.ufgov.zc.server.zc.publish.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcDdcgHt;
import com.ufgov.zc.common.zc.publish.IZcDdcgHtServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcDdcgHtService;

public class ZcDdcgHtServiceDelegate implements IZcDdcgHtServiceDelegate {

  private IZcDdcgHtService zcDdcgHtService;

  public IZcDdcgHtService getZcDdcgHtService() {
    return zcDdcgHtService;
  }

  public void setZcDdcgHtService(IZcDdcgHtService zcDdcgHtService) {
    this.zcDdcgHtService = zcDdcgHtService;
  }

  public List getZcDdcgHt(ElementConditionDto dto, RequestMeta meta) throws SQLException {
    return zcDdcgHtService.getZcDdcgHt(dto, meta);
  }

  public ZcDdcgHt selectByPrimaryKey(String zcHtCode, RequestMeta requestMeta) {
    return zcDdcgHtService.selectByPrimaryKey(zcHtCode, requestMeta);
  }

  public void deleteByPrimaryKeyFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception {
    zcDdcgHtService.deleteByPrimaryKeyFN(zcDdcgHt, requestMeta);
  }

  public ZcDdcgHt updateZcDdcgHtFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception {
    return zcDdcgHtService.updateZcDdcgHtFN(zcDdcgHt, requestMeta);
  }

}
