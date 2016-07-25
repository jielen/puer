package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.kingdrive.workflow.exception.WorkflowException;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.publish.ISfJdReportServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdReportService;

public class SfJdReportServiceDelegate implements ISfJdReportServiceDelegate {

	private ISfJdReportService sfJdReportService;
	
	public List getMainDataLst(ElementConditionDto elementConditionDto,		RequestMeta requestMeta) {
		return sfJdReportService.getMainDataLst(elementConditionDto, requestMeta);
	}

	
	public SfJdReport selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
		return sfJdReportService.selectByPrimaryKey(id, requestMeta);
	}

	
	public SfJdReport saveFN(SfJdReport inData, RequestMeta requestMeta) {
		return sfJdReportService.saveFN(inData, requestMeta);
	}

	
	public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
		sfJdReportService.deleteByPrimaryKeyFN(id, requestMeta);
	}

	
	public int updateByPrimaryKeyFN(SfJdReport inData, RequestMeta requestMeta) {
		
		return sfJdReportService.updateByPrimaryKeyFN(inData, requestMeta);
	}

	
	public SfJdReport unAuditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
		return sfJdReportService.unAuditFN(record, requestMeta);
	}

	
	public SfJdReport untreadFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
		return sfJdReportService.untreadFN(record, requestMeta);
	}

	
	public SfJdReport auditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
		
		return sfJdReportService.auditFN(record, requestMeta);
	}

	
	public SfJdReport newCommitFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
		return sfJdReportService.newCommitFN(record, requestMeta);
	}

	
	public SfJdReport callbackFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
		return sfJdReportService.callbackFN(record, requestMeta);
	}


	public ISfJdReportService getSfJdReportService() {
		return sfJdReportService;
	}


	public void setSfJdReportService(ISfJdReportService sfJdReportService) {
		this.sfJdReportService = sfJdReportService;
	}

}
