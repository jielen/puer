package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfSjOutMapper;
import com.ufgov.zc.server.sf.service.ISfSjInService;
import com.ufgov.zc.server.sf.service.ISfSjOutService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfSjOutService implements ISfSjOutService {
  
  private SfSjOutMapper sfSjOutMapper;
  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private IZcEbBaseService zcEbBaseService;
  private ISfSjInService sfSjInService;

  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfSjOutMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfSjOut selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    SfSjOut rtn= sfSjOutMapper.selectByPrimaryKey(id);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfSjOut saveFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    if(chaochu(bill,requestMeta)){
      throw new SfBusinessException("可用数量不足，请重新输入使用数量");
    }
    if (bill.getOutId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfSjOut.SEQ_SF_SF_SJ_OUT_ID));
      bill.setOutId(id);  

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();
      
      if (bill.getProcessInstId() == null || bill.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        bill.setProcessInstId(draftid);
        isDraft = true;
      }      
      insert(bill,requestMeta);
      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(bill.getSjin().getSj().getName());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(bill.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
   }else{
     update(bill,requestMeta);
   }
    bill.setDbDigest(bill.digest());
   return bill;
  }

  /**
   * 当前单据的使用数量是否已经超出可用数量 
   * @param bill
   * @param requestMeta
   * @return
   */
  private boolean chaochu(SfSjOut bill, RequestMeta requestMeta) {
    ElementConditionDto dto=new ElementConditionDto();
    dto.setSfId(bill.getSjin().getInId());
    BigDecimal usedAmount=new BigDecimal(0),canUseAmount=new BigDecimal(0);
    HashMap usedMp=(HashMap) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfSjOutMapper.getOutedSj", dto);
    if(usedMp!=null && usedMp.size()>0){
      BigDecimal amount=(BigDecimal) usedMp.get("AMOUNT");
      if(amount!=null){
        usedAmount=amount;
      }
    }
    SfSjIn sjIn=sfSjInService.selectByPrimaryKey(bill.getSjin().getInId(), requestMeta);
    canUseAmount=sjIn.getAmount();
      if(bill.getOutId()==null){
        canUseAmount=canUseAmount.subtract(usedAmount);
        if(bill.getAmount().compareTo(canUseAmount)==1){
          return true;
        }else{
          return false;
        }
      }else{
        usedAmount=usedAmount.subtract(bill.getAmount());
        canUseAmount=canUseAmount.subtract(usedAmount); 
        if(bill.getAmount().compareTo(canUseAmount)==1){
          return true;
        }else{
          return false;
        }
      } 
  }


  public ISfSjInService getSfSjInService() {
    return sfSjInService;
  }


  public void setSfSjInService(ISfSjInService sfSjInService) {
    this.sfSjInService = sfSjInService;
  }


  private void update(SfSjOut bill, RequestMeta requestMeta) {
    sfSjOutMapper.updateByPrimaryKey(bill);
  }

  private void insert(SfSjOut bill, RequestMeta requestMeta) {
    sfSjOutMapper.insert(bill);
  }


  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) throws SfBusinessException {
    sfSjOutMapper.deleteByPrimaryKey(id);
  }

  
  public SfSjOut unAuditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    try {
      wfEngineAdapter.rework(bill.getComment(), bill, requestMeta);
    } catch (Exception e) {
      throw new SfBusinessException(e.getMessage(),e);
    }
    return selectByPrimaryKey(bill.getOutId(), requestMeta);
  }

  
  public SfSjOut untreadFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    try {
      wfEngineAdapter.untread(bill.getComment(), bill, requestMeta);
    } catch (Exception e) {
      throw new SfBusinessException(e.getMessage(),e);
    }
    return selectByPrimaryKey(bill.getOutId(), requestMeta);
  }

  
  public SfSjOut auditFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    try {
      bill=saveFN(bill, requestMeta);
      wfEngineAdapter.commit(bill.getComment(), bill, requestMeta);
    } catch (Exception e) {
      throw new SfBusinessException(e.getMessage(),e);
    }
    return selectByPrimaryKey(bill.getOutId(), requestMeta);
  }

  
  public SfSjOut newCommitFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    try {

      String comment=bill.getComment();
      bill=saveFN(bill, requestMeta);
      wfEngineAdapter.newCommit(comment, bill, requestMeta); 
    } catch (Exception e) {
      throw new SfBusinessException(e.getMessage(),e);
    }

    return selectByPrimaryKey(bill.getOutId(),requestMeta);
  }

  
  public SfSjOut callbackFN(SfSjOut bill, RequestMeta requestMeta) throws SfBusinessException {
    try {
      wfEngineAdapter.callback(bill.getComment(), bill, requestMeta);
    } catch (Exception e) {
      throw new SfBusinessException(e.getMessage(),e);
    }
    return selectByPrimaryKey(bill.getOutId(), requestMeta);
  }


  public SfSjOutMapper getSfSjOutMapper() {
    return sfSjOutMapper;
  }


  public void setSfSjOutMapper(SfSjOutMapper sfSjOutMapper) {
    this.sfSjOutMapper = sfSjOutMapper;
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


  public IZcEbBaseService getZcEbBaseService() {
    return zcEbBaseService;
  }


  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
    this.zcEbBaseService = zcEbBaseService;
  }
}
