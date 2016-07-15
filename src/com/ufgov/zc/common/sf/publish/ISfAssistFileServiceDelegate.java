package com.ufgov.zc.common.sf.publish;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfAssistFileServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  List saveFN(List fileLst, RequestMeta requestMeta);
}
