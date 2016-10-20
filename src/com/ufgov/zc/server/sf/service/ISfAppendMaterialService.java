package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfAppendMaterialService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfAppendMaterial selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfAppendMaterial saveFN(SfAppendMaterial inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfAppendMaterial unAuditFN(SfAppendMaterial qx, RequestMeta requestMeta);

  SfAppendMaterial untreadFN(SfAppendMaterial qx, RequestMeta requestMeta);

  SfAppendMaterial auditFN(SfAppendMaterial qx, RequestMeta requestMeta) throws Exception;

  SfAppendMaterial newCommitFN(SfAppendMaterial qx, RequestMeta requestMeta);

  SfAppendMaterial callbackFN(SfAppendMaterial qx, RequestMeta requestMeta);

}
