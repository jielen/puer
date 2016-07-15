package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdTargetService {

  List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdTarget selectByPrimaryKey(BigDecimal entrustorId, RequestMeta requestMeta);

  SfJdTarget saveFN(SfJdTarget inData, RequestMeta requestMeta);

  boolean isUsing(BigDecimal entrustorId, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal entrustorId, RequestMeta requestMeta);

}
