package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfChargeServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfCharge selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfCharge saveFN(SfCharge inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfCharge unAuditFN(SfCharge qx, RequestMeta requestMeta);

  SfCharge untreadFN(SfCharge qx, RequestMeta requestMeta);

  SfCharge auditFN(SfCharge qx, RequestMeta requestMeta) throws Exception;

  SfCharge newCommitFN(SfCharge qx, RequestMeta requestMeta);

  SfCharge callbackFN(SfCharge qx, RequestMeta requestMeta);


}
