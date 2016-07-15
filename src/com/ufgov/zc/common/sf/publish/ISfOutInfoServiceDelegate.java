package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfOutInfoServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfOutInfo selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfOutInfo saveFN(SfOutInfo inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfOutInfo unAuditFN(SfOutInfo qx, RequestMeta requestMeta);

  SfOutInfo untreadFN(SfOutInfo qx, RequestMeta requestMeta);

  SfOutInfo auditFN(SfOutInfo qx, RequestMeta requestMeta) throws Exception;

  SfOutInfo newCommitFN(SfOutInfo qx, RequestMeta requestMeta);

  SfOutInfo callbackFN(SfOutInfo qx, RequestMeta requestMeta);

}
