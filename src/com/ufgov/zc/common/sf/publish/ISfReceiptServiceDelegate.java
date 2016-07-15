package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfReceiptServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfReceipt selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfReceipt saveFN(SfReceipt inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfReceipt unAuditFN(SfReceipt qx, RequestMeta requestMeta);

  SfReceipt untreadFN(SfReceipt qx, RequestMeta requestMeta);

  SfReceipt auditFN(SfReceipt qx, RequestMeta requestMeta) throws Exception;

  SfReceipt newCommitFN(SfReceipt qx, RequestMeta requestMeta);

  SfReceipt callbackFN(SfReceipt qx, RequestMeta requestMeta);

}
