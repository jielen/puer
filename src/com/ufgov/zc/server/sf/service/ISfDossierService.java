package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfDossierService {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfDossier selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfDossier saveFN(SfDossier inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfDossier unAuditFN(SfDossier qx, RequestMeta requestMeta);

  SfDossier untreadFN(SfDossier qx, RequestMeta requestMeta);

  SfDossier auditFN(SfDossier qx, RequestMeta requestMeta) throws Exception;

  SfDossier newCommitFN(SfDossier qx, RequestMeta requestMeta);

  SfDossier callbackFN(SfDossier qx, RequestMeta requestMeta);


}
