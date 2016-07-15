package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcSupQuaType;

public interface IZcQuaTypeServiceDelegate extends Publishable {

  public List getZcSupQuaTypeList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcSupQuaTypeByTypeCode(String typeCode, RequestMeta meta);

  public void deleteZcSupQuaTypeListFN(List ZcSupQuaTypeList, RequestMeta meta);

  public ZcSupQuaType updateZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta);

  public ZcSupQuaType insertZcSupQuaTypeFN(ZcSupQuaType zcSupQuaType, RequestMeta meta);

  public ZcSupQuaType selectByPrimaryKey(String typeCode, RequestMeta requestMeta);

}
