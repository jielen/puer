package com.ufgov.zc.common.sf.publish;

import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdDocCodeServiceDelegate {


	  List getMainDataLst(ElementConditionDto dto, RequestMeta requestMeta);

	  SfJdDocCode selectByPrimaryKey(String pinJieCode, RequestMeta requestMeta);

	  SfJdDocCode saveFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException;
	  
	  void deleteByPrimaryKeyFN(String pinJieCode, RequestMeta requestMeta);
	  
	  int updateByPrimaryKeyFN(SfJdDocCode inData,RequestMeta requestMeta) throws SfBusinessException;

}
