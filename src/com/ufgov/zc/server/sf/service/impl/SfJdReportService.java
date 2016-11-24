package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.kingdrive.workflow.exception.WorkflowException;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfJdReportMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfJdReportService;
import com.ufgov.zc.server.sf.service.ISfJdResultService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfJdReportService implements ISfJdReportService {

	private SfJdReportMapper sfJdReportMapper;
	  private IWorkflowDao workflowDao;
	  private WFEngineAdapter wfEngineAdapter;

	  private ISfEntrustService sfEntrustService;
	  
	  private IZcEbBaseService zcEbBaseService;


	private ISfJdResultService jdResultService;
	
	public List getMainDataLst(ElementConditionDto elementConditionDto,			RequestMeta requestMeta) {
		return sfJdReportMapper.getMainDataLst(elementConditionDto);
	}

	
	public SfJdReport selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
		SfJdReport rtn= sfJdReportMapper.selectByPrimaryKey(id);
		rtn.setEntrust(sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta));
		if(rtn.getJdResultId()!=null){
		  rtn.setResult(jdResultService.selectByPrimaryKey(rtn.getJdResultId(), requestMeta));
		}
		rtn.digest();
		return rtn;
	}

	
	public SfJdReport saveFN(SfJdReport record, RequestMeta requestMeta) {
		if(record.getJdReporId()==null){
	    ZcSUtil su=new ZcSUtil();
		      BigDecimal id = new BigDecimal(su.getNextVal(SfJdReport.SEQ_SF_JD_REPORT_ID));
		      record.setJdReporId(id);
		      boolean isDraft = false;
		      String userId = requestMeta.getSvUserID();
		      String compoId = requestMeta.getCompoId();

		      if (record.getProcessInstId() == null || record.getProcessInstId().longValue() == -1) {
		        Long draftid = workflowDao.createDraftId();
		        record.setProcessInstId(draftid);
		        isDraft = true;
		      }

		      sfJdReportMapper.insert(record);
		      
		      if (isDraft) {
		        AsWfDraft asWfDraft = new AsWfDraft();
		        asWfDraft.setCompoId(compoId);
		        asWfDraft.setWfDraftName(record.getName());
		        asWfDraft.setUserId(userId);
		        asWfDraft.setMasterTabId(compoId);
		        asWfDraft.setWfDraftId(BigDecimal.valueOf(record.getProcessInstId().longValue()));
		        workflowDao.insertAsWfdraft(asWfDraft);
		      }
		      
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
	    sendMsgUntread(record,requestMeta);
	    return record;
	  }

	  private void sendMsgUntread(SfJdReport qx, RequestMeta requestMeta) {
		  ElementConditionDto dto=new ElementConditionDto();
		  dto.setDattr1("SF_JD_REPORT");
		  dto.setDattr2(""+qx.getProcessInstId());
		  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectUntreadUser", dto);
		  if(userLst!=null ){
			  String mobile="";
			  String msg=qx.getEntrust().getCode()+"鉴定报告被退回了,请登录鉴定管理系统进行查看处理。";
			  ZcSUtil su=new ZcSUtil();
			  for(int i=0;i<userLst.size();i++){
				  HashMap row=(HashMap) userLst.get(i);
				  String user=(String) row.get("EXECUTOR");
				  HashMap mobiles=su.getJdjgUser(user, qx.getProcessInstId(), requestMeta);
				  Iterator keys=mobiles.keySet().iterator();
				  while(keys.hasNext()){
					  String key=keys.next().toString(); 
					  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());
				  } 
			  }
		  }	  
	}
	  
	  public IZcEbBaseService getZcEbBaseService() {
		return zcEbBaseService;
	}


	public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
		this.zcEbBaseService = zcEbBaseService;
	}


	public SfJdReport auditFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub 
	    record = saveFN(record, requestMeta); 
	    wfEngineAdapter.commit(record.getComment(), record, requestMeta);
	    sendMsgAudit(record,requestMeta);
	    return selectByPrimaryKey(record.getJdReporId(), requestMeta);
	  }

	  private void sendMsgAudit(SfJdReport qx, RequestMeta requestMeta) {

		  ElementConditionDto dto=new ElementConditionDto();
		  dto.setDattr1("SF_JD_REPORT");
		  dto.setDattr2(""+qx.getProcessInstId());
		  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectToDoUser", dto);
		   
		  if(userLst!=null ){
			  String mobile="";
			  String msg=qx.getEntrust().getCode()+"鉴定报告等待您审批,案事件:"+qx.getName()+",请登录鉴定管理系统进行审批。";
			  
			  ZcSUtil su=new ZcSUtil();
			  for(int i=0;i<userLst.size();i++){
				  HashMap row=(HashMap) userLst.get(i);
				  String user=(String) row.get("EXECUTOR");
				  HashMap mobiles=su.getJdjgUser(user, qx.getProcessInstId(), requestMeta);
				  Iterator keys=mobiles.keySet().iterator();
				  while(keys.hasNext()){
					  String key=keys.next().toString(); 
					  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());
				  } 
			  }
		  }
	}
	  public SfJdReport newCommitFN(SfJdReport record, RequestMeta requestMeta) throws WorkflowException {
	    // TCJLODO Auto-generated method stub
 
	    record = saveFN(record, requestMeta); 
	    wfEngineAdapter.newCommit(record.getComment(), record, requestMeta);
	    sendMsgAudit(record,requestMeta);
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


	  public ISfEntrustService getSfEntrustService() {
		return sfEntrustService;
	}


	public void setSfEntrustService(ISfEntrustService sfEntrustService) {
		this.sfEntrustService = sfEntrustService;
	}


	public ISfJdResultService getJdResultService() {
		return jdResultService;
	}


	public void setJdResultService(ISfJdResultService jdResultService) {
		this.jdResultService = jdResultService;
	}

}
