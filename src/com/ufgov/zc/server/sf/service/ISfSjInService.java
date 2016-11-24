package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfSjInService {

  List getMainLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfSjIn selectByPrimaryKey(BigDecimal InId, RequestMeta requestMeta);

  SfSjIn saveFN(SfSjIn inData, RequestMeta requestMeta) throws SfBusinessException;

  void deleteByPrimaryKeyFN(BigDecimal InId, RequestMeta requestMeta) throws SfBusinessException;

}
