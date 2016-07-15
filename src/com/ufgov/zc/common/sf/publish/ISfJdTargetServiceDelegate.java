package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdTargetServiceDelegate extends Publishable {

  List getJdTargetLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdTarget selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfJdTarget saveFN(SfJdTarget inData, RequestMeta requestMeta);

  boolean isUsing(BigDecimal id, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

}
