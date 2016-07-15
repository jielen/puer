package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdPersonServiceDelegate {


  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdPerson selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfJdPerson saveFN(SfJdPerson inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

}
