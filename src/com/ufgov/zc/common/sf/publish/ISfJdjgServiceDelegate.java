package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdjgServiceDelegate {


	  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

	  SfJdjg selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

	  SfJdjg saveFN(SfJdjg inData, RequestMeta requestMeta);

	  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);
}
