package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.ZcMobileMsg;
import com.ufgov.zc.common.sf.publish.IZcMobileMsgServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto; 
import com.ufgov.zc.server.sf.service.IZcMobileMsgService;

public class ZcMobileMsgServiceDelegate implements IZcMobileMsgServiceDelegate {
  private IZcMobileMsgService zcMobileMsgService;

  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return zcMobileMsgService.getMainDataLst(elementConditionDto, requestMeta);
  }

  public ZcMobileMsg updateFN(ZcMobileMsg qx, RequestMeta requestMeta) throws Exception {
    return zcMobileMsgService.updateFN(qx, requestMeta);
  }

  public void deleteFN(ZcMobileMsg qx, RequestMeta requestMeta) {
    zcMobileMsgService.deleteFN(qx, requestMeta);
  }

  public ZcMobileMsg selectByPrimaryKey(String qxCode, RequestMeta requestMeta) {
    return zcMobileMsgService.selectByPrimaryKey(qxCode, requestMeta);
  }

  public ZcMobileMsg callbackFN(ZcMobileMsg qx, RequestMeta requestMeta) {
    return null;
  }

  public IZcMobileMsgService getZcMobileMsgService() {
    return zcMobileMsgService;
  }

  public void setZcMobileMsgService(IZcMobileMsgService zcMobileMsgService) {
    this.zcMobileMsgService = zcMobileMsgService;
  }

}
