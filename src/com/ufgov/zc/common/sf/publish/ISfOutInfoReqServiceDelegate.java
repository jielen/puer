package com.ufgov.zc.common.sf.publish;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfOutInfoReqServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfOutInfoReq selectByPrimaryKey(String code, RequestMeta requestMeta);

  SfOutInfoReq saveFN(SfOutInfoReq inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta);


}
