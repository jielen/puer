package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.model.runtime.ActionHistoryModel;
import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper;
import com.ufgov.zc.server.sf.dao.SfDocSendMapper;
import com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper;
import com.ufgov.zc.server.sf.service.ISfDocSendService;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfJdDocAuditService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfDocSendService implements ISfDocSendService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter; 
  private SfDocSendMapper sfDocSendMapper;
  private SfDocSendDetailMapper sfDocSendDetailMapper;
  private ISfEntrustService sfEntrustService;
  private IZcEbBaseService zcEbBaseService;
  private ISfJdDocAuditService jdDocAuditService;
  private SfDocSendMaterialMapper sfDocSendMaterialMapper;
  
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


  public SfDocSendMapper getSfDocSendMapper() {
    return sfDocSendMapper;
  }


  public void setSfDocSendMapper(SfDocSendMapper sfDocSendMapper) {
    this.sfDocSendMapper = sfDocSendMapper;
  }


  public SfDocSendDetailMapper getSfDocSendDetailMapper() {
    return sfDocSendDetailMapper;
  }


  public void setSfDocSendDetailMapper(SfDocSendDetailMapper sfDocSendDetailMapper) {
    this.sfDocSendDetailMapper = sfDocSendDetailMapper;
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
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfDocSendMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfDocSend selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    SfDocSend rtn=sfDocSendMapper.selectByPrimaryKey(id);
    List d=sfDocSendDetailMapper.selectByPrimaryKey(id);
    rtn.setDocDetailLst(d==null?new ArrayList():d);
    List m=sfDocSendMaterialMapper.selectByPrimaryKey(id);
    rtn.setMaterialLst(m==null?new ArrayList():m);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfDocSend saveFN(SfDocSend inData, RequestMeta requestMeta) {
    if (inData.getSendId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfDocSend.SEQ_SF_DOC_SEND_ID));
      inData.setSendId(id); 

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId(); 
      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }    
      
      insert(inData,requestMeta); 

      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getEntrustCode());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
      
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }
  private void update(SfDocSend inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    sfDocSendMapper.updateByPrimaryKey(inData);
    
    sfDocSendDetailMapper.deleteByPrimaryKey(inData.getSendId());
    if(inData.getDocDetailLst()!=null){
      for(int i=0;i<inData.getDocDetailLst().size();i++){
        SfDocSendDetail m=(SfDocSendDetail) inData.getDocDetailLst().get(i);
        m.setSendId(inData.getSendId());
        sfDocSendDetailMapper.insert(m);
      }
    } 
    sfDocSendMaterialMapper.deleteByPrimaryKey(inData.getSendId());
    if(inData.getMaterialLst()!=null){
      for(int i=0;i<inData.getMaterialLst().size();i++){
        SfDocSendMaterial m=(SfDocSendMaterial) inData.getMaterialLst().get(i);
        m.setSendId(inData.getSendId());
        sfDocSendMaterialMapper.insert(m);
      }
    } 
  }

  private void insert(SfDocSend inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    sfDocSendMapper.insert(inData);
    if(inData.getDocDetailLst()!=null){
      for(int i=0;i<inData.getDocDetailLst().size();i++){
        SfDocSendDetail m=(SfDocSendDetail) inData.getDocDetailLst().get(i);
        m.setSendId(inData.getSendId());
        sfDocSendDetailMapper.insert(m);
      }
    }  
    if(inData.getMaterialLst()!=null){
      for(int i=0;i<inData.getMaterialLst().size();i++){
        SfDocSendMaterial m=(SfDocSendMaterial) inData.getMaterialLst().get(i);
        m.setSendId(inData.getSendId());
        sfDocSendMaterialMapper.insert(m);
      }
    } 
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfDocSendMapper.deleteByPrimaryKey(id);
    sfDocSendDetailMapper.deleteByPrimaryKey(id);
  }

  
  public SfDocSend unAuditFN(SfDocSend qx, RequestMeta requestMeta) {
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfDocSend untreadFN(SfDocSend qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta); 
    
    ElementConditionDto dto=new ElementConditionDto();
    dto.setExecutor(requestMeta.getSvUserID());
    dto.setProcessInstId(qx.getProcessInstId()); 
    
     Long instanceId3=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.isZongHeShouliToDo", dto);
     Long instanceId4=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.isZongHeShouliUntreat", dto);

     WorkflowContext context=null;
     try{
       if(instanceId3!=null || instanceId4!=null){
        RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
        newMeta.setSvUserID(SfElementConstants.ZONGHE_SHOULI); 
        context=wfEngineAdapter.untread(qx.getComment(), qx, newMeta);
      }else{    
        context=wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
      }
     }catch (Exception e) {
      // TODO: handle exception
       throw new SfBusinessException("退回异常.", e);
    }
    return qx;
  }

  
  public SfDocSend auditFN(SfDocSend qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    SfDocSend oldQx=(SfDocSend) BeanUtils.cloneBean(qx);
    if(qx.getStatus().equals(SfDocSend.SF_DOC_SEND_STATUS_waiting_jieshou)){//当前是接收人在接收文书
      qx.setJieShouDate(ZcSUtil.getSysDate());
      qx.setJieShouRen(requestMeta.getSvUserID());
    }else if(qx.getStatus().equals(SfDocSend.SF_DOC_SEND_STATUS_waiting_send)){//当前是值班人在发放文书
      qx.setSendDate(ZcSUtil.getSysDate());
      qx.setSendor(requestMeta.getSvUserID());
    }
    qx=saveFN(qx, requestMeta);
//    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta); 
    
    //--
    //由于科室受理的待办是通用用户KESHI_SHOULI，所以要将excutor换位KESHI_SHOULI，否则在
    //taskItem = getRuntimeService().getCurrentTaskByNodeUser(instanceId, currentNodeId, user);，会得不到报错
    ElementConditionDto dto=new ElementConditionDto();
    dto.setExecutor(requestMeta.getSvUserID());
    dto.setProcessInstId(qx.getProcessInstId()); 
     Long instanceId3=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.isZongHeShouliToDo", dto);
     Long instanceId4=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.isZongHeShouliUntreat", dto); 
     WorkflowContext context=null;
    if(instanceId3!=null || instanceId4!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.ZONGHE_SHOULI); 
      context=wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
      _saveSfWfHistory(context.getHistoryModel(),requestMeta);
    }else{    
      wfEngineAdapter.commit(qx.getComment(), qx, requestMeta); 
    } 
    
    return selectByPrimaryKey(qx.getSendId(),requestMeta);
  }

/**
 * 以综合受理、科室受理角色操作时，记录对应的实际操作人
 * @param currentTaskModel
 * @param requestMeta
 */
private void _saveSfWfHistory(ActionHistoryModel history, RequestMeta requestMeta) {
  if(history==null)return;
  history.setExecutor(requestMeta.getSvUserID()); 
  history.setOwner(requestMeta.getSvUserID());
  zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.insert_sf_ActionHistory", history);
}
  
  public SfDocSend newCommitFN(SfDocSend qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    String comment=qx.getComment();
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.newCommit(comment, qx, requestMeta); 
    return selectByPrimaryKey(qx.getSendId(),requestMeta);
  }

  
  public SfDocSend callbackFN(SfDocSend qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }


  public ISfJdDocAuditService getJdDocAuditService() {
    return jdDocAuditService;
  }


  public void setJdDocAuditService(ISfJdDocAuditService jdDocAuditService) {
    this.jdDocAuditService = jdDocAuditService;
  }


  public SfDocSendMaterialMapper getSfDocSendMaterialMapper() {
    return sfDocSendMaterialMapper;
  }


  public void setSfDocSendMaterialMapper(SfDocSendMaterialMapper sfDocSendMaterialMapper) {
    this.sfDocSendMaterialMapper = sfDocSendMaterialMapper;
  }

}
