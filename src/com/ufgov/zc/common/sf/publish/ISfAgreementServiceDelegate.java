package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfAgreementServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfAgreement selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfAgreement saveFN(SfAgreement inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfAgreement unAuditFN(SfAgreement qx, RequestMeta requestMeta);

  SfAgreement untreadFN(SfAgreement qx, RequestMeta requestMeta);

  SfAgreement auditFN(SfAgreement qx, RequestMeta requestMeta) throws Exception;

  SfAgreement newCommitFN(SfAgreement qx, RequestMeta requestMeta);

  SfAgreement callbackFN(SfAgreement qx, RequestMeta requestMeta);

}
