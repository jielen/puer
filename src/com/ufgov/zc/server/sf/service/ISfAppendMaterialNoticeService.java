package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;
import com.ufgov.zc.common.sf.model.SfAppendMaterialNotice;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfAppendMaterialNoticeService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfAppendMaterialNotice selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfAppendMaterialNotice saveFN(SfAppendMaterialNotice inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfAppendMaterialNotice unAuditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta);

  SfAppendMaterialNotice untreadFN(SfAppendMaterialNotice qx, RequestMeta requestMeta);

  SfAppendMaterialNotice auditFN(SfAppendMaterialNotice qx, RequestMeta requestMeta) throws Exception;

  SfAppendMaterialNotice newCommitFN(SfAppendMaterialNotice qx, RequestMeta requestMeta);

  SfAppendMaterialNotice callbackFN(SfAppendMaterialNotice qx, RequestMeta requestMeta);



}
