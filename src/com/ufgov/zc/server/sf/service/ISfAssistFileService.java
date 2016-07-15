package com.ufgov.zc.server.sf.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfAssistFileService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  List saveFN(List fileLst, RequestMeta requestMeta);

}
