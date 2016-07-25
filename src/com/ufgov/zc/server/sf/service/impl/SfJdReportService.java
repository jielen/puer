package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.kingdrive.workflow.exception.WorkflowException;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdReportMapper;
import com.ufgov.zc.server.sf.service.ISfJdReportService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdReportService implements ISfJdReportService {

	private SfJdReportMapper sfJdReportMapper;
	  private IWorkflowDao workflowDao;
	  private WFEngineAdapter wfEngineAdapter;
	
	public List getMainDataLst(ElementConditionDto elementConditionDto,			RequestMeta requestMeta) {
		return sfJdReportMapper.getMainDataLst(elementConditionDto);
	}

	
	public SfJdReport selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
		return sfJdReportMapper.selectByPrimaryKey(id);
	}

	
	public SfJdReport saveFN(SfJdReport record, RequestMeta requestMeta) {
		if(record.getJdReporId()==null){
		      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfJdReport.SEQ_SF_JD_REPORT_ID));
		      record.setJdReporId(id);
		      sfJdReportMapper.insert(record);
		}else{
			sfJdReportMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	
	public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
		sfJdReportMapper.deleteByPrimaryKey(id);
	}

	
	public int updateByPrimaryKeyFN(SfJdReport record, RequestMeta requestMeta) {
		sfJdReportMapper.updateByPrimaryKey(record);
		return 1;
	}

	
	public SfJdReport unAuditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {

	    wfEngineAdapter.rework(record.getComment(), record, requestMeta);
	    return record;
	}


	  public SfJdReport untreadFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub
	    wfEngineAdapter.untread(record.getComment(), record, requestMeta);
	    return record;
	  }

	  public SfJdReport auditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub 
	    record = saveFN(record, requestMeta); 
	    wfEngineAdapter.commit(record.getComment(), record, requestMeta);
	    return selectByPrimaryKey(record.getJdReporId(), requestMeta);
	  }

	  public SfJdReport newCommitFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub
 
	    record = saveFN(record, requestMeta); 
	    wfEngineAdapter.newCommit(record.getComment(), record, requestMeta);
	    return selectByPrimaryKey(record.getJdReporId(), requestMeta);
	  }

	  public SfJdReport callbackFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub
	    wfEngineAdapter.callback(record.getComment(), record, requestMeta);
	    return record;
	  }


	public SfJdReportMapper getSfJdReportMapper() {
		return sfJdReportMapper;
	}


	public void setSfJdReportMapper(SfJdReportMapper sfJdReportMapper) {
		this.sfJdReportMapper = sfJdReportMapper;
	}


	public IWorkflowDao getWorkflowDao() {
		return workflowDao;
	}


	public void setWorkflowDao(IWorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}


	public WFEngineAdapter getWfEngineAdapter() {
		return wfEngineAdapter;
	}


	public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
		this.wfEngineAdapter = wfEngineAdapter;
	}

}
