package com.ufgov.zc.common.sf.publish;

import java.util.List;

import com.ufgov.zc.common.sf.model.ZcMobileMsg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto; 

public interface IZcMobileMsgServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  ZcMobileMsg updateFN(ZcMobileMsg qx, RequestMeta requestMeta) throws Exception;

  void deleteFN(ZcMobileMsg qx, RequestMeta requestMeta);

  ZcMobileMsg selectByPrimaryKey(String qxCode, RequestMeta requestMeta);

  ZcMobileMsg callbackFN(ZcMobileMsg qx, RequestMeta requestMeta);

}
