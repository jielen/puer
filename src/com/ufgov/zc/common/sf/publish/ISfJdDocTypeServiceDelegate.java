package com.ufgov.zc.common.sf.publish;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdDocType;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdDocTypeServiceDelegate {


  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdDocType selectByPrimaryKey(String id, RequestMeta requestMeta);

  SfJdDocType saveFN(SfJdDocType inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String id, RequestMeta requestMeta);
}
