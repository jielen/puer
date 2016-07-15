package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPProBalChg;

public interface IZcPProBalChgServiceDelegate extends Publishable {

  public List getZcPProBalChgList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception;

  public ZcPProBalChg updateZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception;

  public ZcPProBalChg selectByPrimaryKey(String balChgId, RequestMeta requestMeta);

}
