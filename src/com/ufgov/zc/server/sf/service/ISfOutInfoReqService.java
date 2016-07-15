package com.ufgov.zc.server.sf.service;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfOutInfoReqService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfOutInfoReq selectByPrimaryKey(String code, RequestMeta requestMeta);

  SfOutInfoReq saveFN(SfOutInfoReq inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta);

}
