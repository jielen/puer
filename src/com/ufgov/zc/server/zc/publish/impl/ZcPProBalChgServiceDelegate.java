package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBalChg;
import com.ufgov.zc.common.zc.publish.IZcPProBalChgServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcPProBalChgService;

public class ZcPProBalChgServiceDelegate implements IZcPProBalChgServiceDelegate {

  private IZcPProBalChgService zcPProBalChgService;

  /**
   * @return the zcPProBalChgService
   */
  public IZcPProBalChgService getZcPProBalChgService() {
    return zcPProBalChgService;
  }

  /**
   * @param zcPProBalChgService the zcPProBalChgService to set
   */
  public void setZcPProBalChgService(IZcPProBalChgService zcPProBalChgService) {
    this.zcPProBalChgService = zcPProBalChgService;
  }

  public List getZcPProBalChgList(ElementConditionDto dto, RequestMeta meta) {
    return zcPProBalChgService.getZcPProBalChgList(dto, meta);
  }

  public void deleteZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception {
    zcPProBalChgService.deleteZcPProBalChgFN(zcPProBalChg, serverAdd, flag, meta);
  }

  public ZcPProBalChg updateZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception {
    return zcPProBalChgService.updateZcPProBalChgFN(zcPProBalChg, serverAdd, flag, meta);
  }

  public ZcPProBalChg selectByPrimaryKey(String balChgId, RequestMeta requestMeta) {
    return zcPProBalChgService.selectByPrimaryKey(balChgId, requestMeta);
  }

}
