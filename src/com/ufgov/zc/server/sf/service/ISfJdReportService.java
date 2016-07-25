package com.ufgov.zc.server.sf.service;

import java.math.BigDecimal;
import java.util.List;

import com.kingdrive.workflow.exception.WorkflowException;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

public interface ISfJdReportService {


	  List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

	  SfJdReport selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta);

	  SfJdReport saveFN(SfJdReport record, RequestMeta requestMeta);

	  void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta);

	  int updateByPrimaryKeyFN(SfJdReport inData,RequestMeta requestMeta);

	  SfJdReport unAuditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException;

	  SfJdReport untreadFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException;

	  SfJdReport auditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException;

	  SfJdReport newCommitFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException;

	  SfJdReport callbackFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException;
}
