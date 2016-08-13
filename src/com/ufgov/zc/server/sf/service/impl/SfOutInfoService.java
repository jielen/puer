package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper;
import com.ufgov.zc.server.sf.dao.SfOutInfoMapper;
import com.ufgov.zc.server.sf.dao.SfOutInfoValidateDetailMapper;
import com.ufgov.zc.server.sf.service.ISfOutInfoService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfOutInfoService implements ISfOutInfoService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfOutInfoMapper outInfoMapper;
  private SfOutInfoDetailMapper outInfoDetailMapper;
  private SfOutInfoValidateDetailMapper outInfoValidateDetailMapper;
  private SfMaterialsMapper materialsMapper;
  
 


public SfMaterialsMapper getMaterialsMapper() {
	return materialsMapper;
}


public void setMaterialsMapper(SfMaterialsMapper materialsMapper) {
	this.materialsMapper = materialsMapper;
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


  public SfOutInfoMapper getOutInfoMapper() {
    return outInfoMapper;
  }


  public void setOutInfoMapper(SfOutInfoMapper outInfoMapper) {
    this.outInfoMapper = outInfoMapper;
  }


  public SfOutInfoDetailMapper getOutInfoDetailMapper() {
    return outInfoDetailMapper;
  }


  public void setOutInfoDetailMapper(SfOutInfoDetailMapper outInfoDetailMapper) {
    this.outInfoDetailMapper = outInfoDetailMapper;
  }


  public SfOutInfoValidateDetailMapper getOutInfoValidateDetailMapper() {
    return outInfoValidateDetailMapper;
  }


  public void setOutInfoValidateDetailMapper(SfOutInfoValidateDetailMapper outInfoValidateDetailMapper) {
    this.outInfoValidateDetailMapper = outInfoValidateDetailMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfOutInfo selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfOutInfo rtn=outInfoMapper.selectByPrimaryKey(id);
    if(rtn==null)return null;
    /*ElementConditionDto dto=new ElementConditionDto();
    dto.setSfId(id);
    rtn.setDetailLst(outInfoDetailMapper.selectByOutInfoId(dto));
    rtn.setValidateDetailLst(outInfoValidateDetailMapper.selectByPrimaryKey(id));*/

    List materialLst = materialsMapper.selectByAppentMaterialsId(id);
    rtn.setDetailLst(materialLst == null ? new ArrayList() : materialLst);
    
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfOutInfo saveFN(SfOutInfo inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getOutInfoId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfOutInfo.SEQ_SF_OUT_INFO_ID));
      inData.setOutInfoId(id);  

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
        asWfDraft.setWfDraftName(inData.getName());
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

  private void update(SfOutInfo inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoMapper.updateByPrimaryKey(inData);
    if(inData.getDetailLst()!=null && inData.getDetailLst().size()>0){
    	SfMaterials mm=(SfMaterials) inData.getDetailLst().get(0);
        materialsMapper.deleteByAppentMaterialsId(mm.getAppendMaterialId());
        for(int i=0;i<inData.getDetailLst().size();i++){
          SfMaterials m=(SfMaterials) inData.getDetailLst().get(i);
          m.setAppendMaterialId(inData.getOutInfoId());
          m.setEntrustId(inData.getEntrustId()); 
          if (m.getMaterialId() == null) {
            BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
            m.setMaterialId(id);
          }
          materialsMapper.insert(m);
        }
      }
   /* outInfoDetailMapper.deleteByOutInfoId(inData.getOutInfoId());
    if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfOutInfoDetail m=(SfOutInfoDetail) inData.getDetailLst().get(i);
        m.setOutInfoId(inData.getOutInfoId());
        if(m.getOutInfoDetailId()==null){
          BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfOutInfoDetail.SEQ_SF_OUT_INFO_DETAIL_ID));
          m.setOutInfoDetailId(id);
        }
        outInfoDetailMapper.insert(m);
      }
    }
    
    outInfoValidateDetailMapper.deleteByPrimaryKey(inData.getOutInfoId());
    if(inData.getValidateDetailLst()!=null){
      for(int i=0;i<inData.getValidateDetailLst().size();i++){
        SfOutInfoValidateDetail m=(SfOutInfoValidateDetail) inData.getValidateDetailLst().get(i);
        m.setOutInfoId(inData.getOutInfoId());
        outInfoValidateDetailMapper.insert(m);
      }
    }*/
    
  }

  private void insert(SfOutInfo inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoMapper.insert(inData);
    if(inData.getDetailLst()!=null){
        for(int i=0;i<inData.getDetailLst().size();i++){
          SfMaterials m=(SfMaterials) inData.getDetailLst().get(i);
          m.setAppendMaterialId(inData.getOutInfoId());
          m.setEntrustId(inData.getEntrustId()); 
          if (m.getMaterialId() == null) {
            BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
            m.setMaterialId(id);
          }
          materialsMapper.insert(m);
        }
      }
    /* if(inData.getDetailLst()!=null){
      for(int i=0;i<inData.getDetailLst().size();i++){
        SfOutInfoDetail m=(SfOutInfoDetail) inData.getDetailLst().get(i);
        m.setOutInfoId(inData.getOutInfoId());
        if(m.getOutInfoDetailId()==null){
          BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfOutInfoDetail.SEQ_SF_OUT_INFO_DETAIL_ID));
          m.setOutInfoDetailId(id);
        }
        outInfoDetailMapper.insert(m);
      }
    }
    if(inData.getValidateDetailLst()!=null){
      for(int i=0;i<inData.getValidateDetailLst().size();i++){
        SfOutInfoValidateDetail m=(SfOutInfoValidateDetail) inData.getValidateDetailLst().get(i);
        m.setOutInfoId(inData.getOutInfoId());
        outInfoValidateDetailMapper.insert(m);
      }
    }*/
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoMapper.deleteByPrimaryKey(id);
    outInfoDetailMapper.deleteByOutInfoId(id);
    outInfoValidateDetailMapper.deleteByPrimaryKey(id);
  }

  
  public SfOutInfo unAuditFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfOutInfo untreadFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfOutInfo auditFN(SfOutInfo qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getOutInfoId(),requestMeta);
  }

  
  public SfOutInfo newCommitFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getOutInfoId(),requestMeta);
  }

  
  public SfOutInfo callbackFN(SfOutInfo qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }

}
