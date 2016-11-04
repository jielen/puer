package com.ufgov.zc.common.sf.publish;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfEntrustManageServiceDelegate {


  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  SfEntrustManage selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

  SfEntrustManage saveFN(SfEntrustManage inData, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

  SfEntrustManage unAuditFN(SfEntrustManage qx, RequestMeta requestMeta);

  SfEntrustManage untreadFN(SfEntrustManage qx, RequestMeta requestMeta);

  SfEntrustManage auditFN(SfEntrustManage qx, RequestMeta requestMeta) throws Exception;

  SfEntrustManage newCommitFN(SfEntrustManage qx, RequestMeta requestMeta);

  SfEntrustManage callbackFN(SfEntrustManage qx, RequestMeta requestMeta);

}
