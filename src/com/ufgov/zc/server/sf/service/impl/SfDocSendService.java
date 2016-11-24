package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.system.RequestMeta;
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
      insert(inData,requestMeta); 
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
    return null;
  }

  
  public SfDocSend untreadFN(SfDocSend qx, RequestMeta requestMeta) {
    return null;
  }

  
  public SfDocSend auditFN(SfDocSend qx, RequestMeta requestMeta) throws Exception {
    return null;
  }

  
  public SfDocSend newCommitFN(SfDocSend qx, RequestMeta requestMeta) {
    return null;
  }

  
  public SfDocSend callbackFN(SfDocSend qx, RequestMeta requestMeta) {
    return null;
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
