package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcSupQuaType;
import com.ufgov.zc.common.zc.publish.IZcQuaTypeServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcQuaTypeService;

public class ZcQuaTypeServiceDelegate implements IZcQuaTypeServiceDelegate {

  private IZcQuaTypeService zcQuaTypeService;

  public IZcQuaTypeService getZcQuaTypeService() {
    return zcQuaTypeService;
  }

  public void setZcQuaTypeService(IZcQuaTypeService zcQuaTypeService) {
    this.zcQuaTypeService = zcQuaTypeService;
  }

  public List getZcSupQuaTypeList(ElementConditionDto dto, RequestMeta meta) {
    return zcQuaTypeService.getZcSupQuaTypeList(dto, meta);
  }

  public void deleteZcSupQuaTypeByTypeCode(String typeCode, RequestMeta meta) {
    zcQuaTypeService.deleteZcSupQuaTypeByTypeCode(typeCode, meta);
  }

  public void deleteZcSupQuaTypeListFN(List ZcSupQuaTypeList, RequestMeta meta) {
    zcQuaTypeService.deleteZcSupQuaTypeListFN(ZcSupQuaTypeList, meta);
  }

  public ZcSupQuaType updateZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta) {
    return zcQuaTypeService.updateZcSupQuaTypeFN(zcSupQuaType, meta);
  }

  public ZcSupQuaType selectByPrimaryKey(String typeCode, RequestMeta requestMeta) {
    return zcQuaTypeService.selectByPrimaryKey(typeCode, requestMeta);
  }

  public ZcSupQuaType insertZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcQuaTypeService.insertZcSupQuaTypeFN(zcSupQuaType, meta);

  }

}
