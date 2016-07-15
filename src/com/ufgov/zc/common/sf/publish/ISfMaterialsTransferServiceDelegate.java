package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfMaterialsTransferServiceDelegate {

  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfMaterialsTransfer selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfMaterialsTransfer saveFN(SfMaterialsTransfer inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfMaterialsTransfer unAuditFN(SfMaterialsTransfer qx, RequestMeta requestMeta);

  SfMaterialsTransfer untreadFN(SfMaterialsTransfer qx, RequestMeta requestMeta);

  SfMaterialsTransfer auditFN(SfMaterialsTransfer qx, RequestMeta requestMeta) throws Exception;

  SfMaterialsTransfer newCommitFN(SfMaterialsTransfer qx, RequestMeta requestMeta);

  SfMaterialsTransfer callbackFN(SfMaterialsTransfer qx, RequestMeta requestMeta);

}
