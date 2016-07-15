package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfEntrustorService {

  List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfEntrustor selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfEntrustor saveFN(SfEntrustor inData, RequestMeta requestMeta);

  boolean isUsing(BigDecimal id, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfEntrustor selectByName(String name, RequestMeta requestMeta);

}
