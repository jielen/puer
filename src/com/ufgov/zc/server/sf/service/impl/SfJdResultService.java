package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFile;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfJdResultMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfJdResultService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfJdResultService implements ISfJdResultService {

  private IWorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  private SfJdResultMapper jdResultMapper;

  private ISfEntrustService sfEntrustService;
  
  private IZcEbBaseService zcEbBaseService;

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

  public SfJdResultMapper getJdResultMapper() {
    return jdResultMapper;
  }

  public void setJdResultMapper(SfJdResultMapper jdResultMapper) {
    this.jdResultMapper = jdResultMapper;
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultMapper.getMainDataLst(elementConditionDto);
  }

  public SfJdResult selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfJdResult rtn = jdResultMapper.selectByPrimaryKey(id);
    SfEntrust entrust = sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta);
    rtn.setEntrust(entrust == null ? new SfEntrust() : entrust);
    List resultFileLst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdResultFileMapper.selectByResultId", id);
    rtn.setJdRecordFileLst(resultFileLst==null?new ArrayList():resultFileLst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public SfJdResult saveFN(SfJdResult inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getJdResultId() == null) {

      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfJdResult.SEQ_SF_JD_RESULT_ID));
      inData.setJdResultId(id);

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();

      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }
      jdResultMapper.insert(inData); 
      
      
      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getName());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
    } else {
      jdResultMapper.updateByPrimaryKey(inData);
    }
    _saveSubLst(inData,requestMeta);
    return inData;
  }

  private void _saveSubLst(SfJdResult inData, RequestMeta requestMeta) {
	  
	  zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfJdResultFileMapper.deleteByResultId", inData.getJdResultId());
	  if(inData.getJdRecordFileLst()==null||inData.getJdRecordFileLst().size()==0)return;
	  for(int i=0;i<inData.getJdRecordFileLst().size();i++){
		  SfJdResultFile rf=(SfJdResultFile) inData.getJdRecordFileLst().get(i);
		  rf.setJdResultId(inData.getJdResultId());
		  BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfJdResultFile.SEQ_SF_JD_RESULT_FILE_ID));
		  rf.setSfJdResultFileId(id);
	  }
	  zcEbBaseService.insertObjectList("com.ufgov.zc.server.sf.dao.SfJdResultFileMapper.insert", inData.getJdRecordFileLst()); 
}

public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdResultMapper.deleteByPrimaryKey(id);

    zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfJdResultFileMapper.deleteByResultId", id);
  }

  public SfJdResult unAuditFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfJdResult untreadFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfJdResult auditFN(SfJdResult qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx = saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getJdResultId(), requestMeta);
  }

  public SfJdResult newCommitFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getJdResultId(), requestMeta);
  }

  public SfJdResult callbackFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public ISfEntrustService getSfEntrustService() {
    return sfEntrustService;
  }

  public void setSfEntrustService(ISfEntrustService sfEntrustService) {
    this.sfEntrustService = sfEntrustService;
  }

public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}

public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}

}
