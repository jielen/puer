package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdResultServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfJdResult selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfJdResult saveFN(SfJdResult inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfJdResult unAuditFN(SfJdResult qx, RequestMeta requestMeta);

  SfJdResult untreadFN(SfJdResult qx, RequestMeta requestMeta);

  SfJdResult auditFN(SfJdResult qx, RequestMeta requestMeta) throws Exception;

  SfJdResult newCommitFN(SfJdResult qx, RequestMeta requestMeta);

  SfJdResult callbackFN(SfJdResult qx, RequestMeta requestMeta);

}
