package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfDocSendServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfDocSend selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfDocSend saveFN(SfDocSend inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfDocSend unAuditFN(SfDocSend qx, RequestMeta requestMeta);

  SfDocSend untreadFN(SfDocSend qx, RequestMeta requestMeta);

  SfDocSend auditFN(SfDocSend qx, RequestMeta requestMeta) throws Exception;

  SfDocSend newCommitFN(SfDocSend qx, RequestMeta requestMeta);

  SfDocSend callbackFN(SfDocSend qx, RequestMeta requestMeta);
}
