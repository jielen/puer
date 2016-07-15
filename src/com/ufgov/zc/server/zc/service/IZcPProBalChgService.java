package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBalChg;

public interface IZcPProBalChgService {

  public List getZcPProBalChgList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception;

  public ZcPProBalChg updateZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception;

  public ZcPProBalChg selectByPrimaryKey(String balChgId, RequestMeta requestMeta);

}
