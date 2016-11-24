/**
 * 
 */
package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.sf.service.ISfAppendMaterialService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

/**
 * @author Administrator
 *
 */
public class SfAppendMaterialService implements ISfAppendMaterialService {
  
  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfAppendMaterialMapper appendMaterialMapper;  
  private SfMaterialsMapper materialsMapper;
  private IZcEbBaseService zcEbBaseService;

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.service.ISfAppendMaterialService#getMainDataLst(com.ufgov.zc.common.system.dto.ElementConditionDto, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public IZcEbBaseService getZcEbBaseService() {
    return zcEbBaseService;
  }

  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
    this.zcEbBaseService = zcEbBaseService;
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

  public SfAppendMaterialMapper getAppendMaterialMapper() {
    return appendMaterialMapper;
  }

  public void setAppendMaterialMapper(SfAppendMaterialMapper appendMaterialMapper) {
    this.appendMaterialMapper = appendMaterialMapper;
  }

  public SfMaterialsMapper getMaterialsMapper() {
    return materialsMapper;
  }

  public void setMaterialsMapper(SfMaterialsMapper materialsMapper) {
    this.materialsMapper = materialsMapper;
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return appendMaterialMapper.getMainDataLst(elementConditionDto);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.service.ISfAppendMaterialService#selectByPrimaryKey(java.math.BigDecimal, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public SfAppendMaterial selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    SfAppendMaterial m=appendMaterialMapper.selectByPrimaryKey(id);
    if(m==null)return null;
    List detail=materialsMapper.selectByAppentMaterialsId(id);
    if(detail==null){
      detail=new ArrayList();
    }
    m.setDetailLst(detail);
    m.setDbDigest(m.digest());
    
    return m;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.service.ISfAppendMaterialService#saveFN(com.ufgov.zc.common.sf.model.SfAppendMaterial, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public SfAppendMaterial saveFN(SfAppendMaterial inData, RequestMeta requestMeta) {

    if (inData.getAppendMaterialId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfAppendMaterial.SEQ_SF_APPEND_MATERIAL_ID));
      inData.setAppendMaterialId(id);

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

  private void update(SfAppendMaterial inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialMapper.updateByPrimaryKey(inData);
    if(inData.getDetailLst()!=null && inData.getDetailLst().size()>0){
      SfMaterials mm=(SfMaterials) inData.getDetailLst().get(0);
        materialsMapper.deleteByAppentMaterialsId(mm.getAppendMaterialId());
        ZcSUtil su=new ZcSUtil();
        for(int i=0;i<inData.getDetailLst().size();i++){
          SfMaterials m=(SfMaterials) inData.getDetailLst().get(i);
          m.setAppendMaterialId(inData.getAppendMaterialId());
          m.setEntrustId(inData.getEntrustId()); 
          if (m.getMaterialId() == null) {
            BigDecimal id = new BigDecimal(su.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
            m.setMaterialId(id);
          }
          materialsMapper.insert(m);
        }
      }    
  }
  private void insert(SfAppendMaterial inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    appendMaterialMapper.insert(inData);
    if(inData.getDetailLst()!=null){
      ZcSUtil su=new ZcSUtil();
        for(int i=0;i<inData.getDetailLst().size();i++){
          SfMaterials m=(SfMaterials) inData.getDetailLst().get(i);
          m.setAppendMaterialId(inData.getAppendMaterialId());
          m.setEntrustId(inData.getEntrustId()); 
          if (m.getMaterialId() == null) {
            BigDecimal id = new BigDecimal(su.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
            m.setMaterialId(id);
          }
          materialsMapper.insert(m);
        }
      }
  }
  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.service.ISfAppendMaterialService#deleteByPrimaryKeyFN(java.math.BigDecimal, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    materialsMapper.deleteByAppentMaterialsId(id);
    appendMaterialMapper.deleteByPrimaryKey(id);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.service.ISfAppendMaterialService#unAuditFN(com.ufgov.zc.common.sf.model.SfAppendMaterial, com.ufgov.zc.common.system.RequestMeta)
   */

  public SfAppendMaterial unAuditFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAppendMaterial untreadFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfAppendMaterial auditFN(SfAppendMaterial qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    
    ElementConditionDto dto=new ElementConditionDto();
    dto.setExecutor(requestMeta.getSvUserID());
    dto.setProcessInstId(qx.getProcessInstId());
     Long instanceId=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.isKeshiShouliToDo", dto);
     Long instanceId2=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.isKeshiShouliUntreat", dto); 
    if(instanceId!=null || instanceId2!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.KESHI_SHOULI); 
      wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
    }else{    
      wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    }
      
    return selectByPrimaryKey(qx.getAppendMaterialId(),requestMeta);
  }

  
  public SfAppendMaterial newCommitFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getAppendMaterialId(),requestMeta);
  }

  
  public SfAppendMaterial callbackFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }


}
