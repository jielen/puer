package com.ufgov.zc.server.sf.service;

import java.util.List;

import com.ufgov.zc.common.sf.model.ZcMobileMsg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto; 

public interface IZcMobileMsgService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  ZcMobileMsg updateFN(ZcMobileMsg qx, RequestMeta requestMeta) throws Exception;

  void deleteFN(ZcMobileMsg qx, RequestMeta requestMeta);

  ZcMobileMsg selectByPrimaryKey(String qxCode, RequestMeta requestMeta);

  ZcMobileMsg callbackFN(ZcMobileMsg qx, RequestMeta requestMeta);

}
