package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfZongheZhibanServiceDelegate { 
  
  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfZongheZhiban selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);
  
  SfZongheZhiban getCurrent(ElementConditionDto dto, RequestMeta requestMeta);

  SfZongheZhiban saveFN(SfZongheZhiban inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

}
