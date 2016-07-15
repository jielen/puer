package com.ufgov.zc.server.sf.service;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfMajorService {

  List getMajorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfMajor selectByPrimaryKey(String majorCode, RequestMeta requestMeta);

  SfMajor saveFN(SfMajor inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String majorCode, RequestMeta requestMeta);

  boolean isUsing(String majorCode, RequestMeta requestMeta);

}
