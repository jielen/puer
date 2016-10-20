/**
 * 
 */
package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.model.ZcFaCardDoc;
import com.ufgov.zc.common.sf.model.ZcFaCardSub;
import com.ufgov.zc.common.sf.model.ZcFaUsing;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.IZcFaCardDao;
import com.ufgov.zc.server.sf.dao.IZcFaCardDocDao;
import com.ufgov.zc.server.sf.dao.IZcFaCardSubDao;
import com.ufgov.zc.server.sf.dao.IZcFaUsingDao;
import com.ufgov.zc.server.sf.service.IZcFaCardService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;

/**
 * @author Administrator
 *
 */
public class ZcFaCardService implements IZcFaCardService {
  
  private WFEngineAdapter wfEngineAdapter;
  
  private IBaseDao baseDao;

  private IWorkflowDao workflowDao;
  
  private IZcFaCardDao cardDao;
  
  private IZcFaCardSubDao cardSubDao;
  
  private IZcFaCardDocDao cardDocDao;
  
  private IZcFaUsingDao cardUsingDao;
  
  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#getCardLst(com.ufgov.zc.common.system.dto.ElementConditionDto, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public IZcFaCardDao getCardDao() {
    return cardDao;
  }

  public void setCardDao(IZcFaCardDao cardDao) {
    this.cardDao = cardDao;
  }

  public IZcFaCardSubDao getCardSubDao() {
    return cardSubDao;
  }

  public void setCardSubDao(IZcFaCardSubDao cardSubDao) {
    this.cardSubDao = cardSubDao;
  }

  public IZcFaCardDocDao getCardDocDao() {
    return cardDocDao;
  }

  public void setCardDocDao(IZcFaCardDocDao cardDocDao) {
    this.cardDocDao = cardDocDao;
  }

  public IZcFaUsingDao getCardUsingDao() {
    return cardUsingDao;
  }

  public void setCardUsingDao(IZcFaUsingDao cardUsingDao) {
    this.cardUsingDao = cardUsingDao;
  }

  public List getCardLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardDao.getCardLst(elementConditionDto);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#unAuditFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard unAuditFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#untreadFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard untreadFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(card.getComment(), card, requestMeta);

    return card;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#auditFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard auditFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    card=updateFN(card, requestMeta);
    wfEngineAdapter.commit(card.getComment(), card, requestMeta);
    card=selectByPrimaryKey(card.getCardId(), requestMeta);
    return card;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#updateFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard updateFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    String code = "";
    List docList = card.getZcFaCardDocList();
    List subList = card.getZcFaCardSubList();
    List usingList=card.getZcFaCardUsingList();
    String userId = requestMeta.getSvUserID();
    String compoId = requestMeta.getCompoId();
    boolean isDraft = false;

    if(card.getUseOrgCode()!=null){
      card.getZcFaCardUsingList().clear();
      ZcFaUsing using=new ZcFaUsing();
      using.setOrgCode(card.getUseOrgCode());
      using.setUseRat(new BigDecimal(1));
      using.setSerial("1");
      using.setUseEmpName(card.getUseEmpName());
      using.setUseEmpCode(card.getUseEmpCode());
      using.setNd(card.getNd());
      using.setCoCode(card.getCoCode());
      using.setCardId(card.getCardId());
      card.getZcFaCardUsingList().add(using);
      usingList=card.getZcFaCardUsingList();
    }
    
    if (card.getProcessInstId() == null || card.getProcessInstId().longValue() == -1) {
      Long draftid = workflowDao.createDraftId();
      card.setProcessInstId(draftid);
      isDraft = true;
    }

    if ("".equals(ZcSUtil.safeString(card.getCardId())) || card.getCardId().equals("自动编号")) {

      code = ZcSUtil.getSequenceNextVal("ZcEbUtil.getZcFaCardNextSeqVal");
      String coCode=requestMeta.getSvCoCode();
      ZcSUtil zu=new ZcSUtil();
      String id1=zu.createFormatString('0', code, 8, false);
      String id2=zu.createFormatString('0', code, 7, false);
      //这里在单位码和编号间加上8，是为了区分从资产系统中增加的卡片和从采购系统中增加的卡片，防止两边的卡片编号重复
      card.setCardId(coCode+"8"+id1);
      card.setFaCode(coCode+"_8"+id2);
      
      for (int i = 0; i < docList.size(); i++) {
        ZcFaCardDoc doc = (ZcFaCardDoc) docList.get(i);
       doc.setCardId(card.getCardId());
      }
      for (int i = 0; i < subList.size(); i++) {
        ZcFaCardSub item = (ZcFaCardSub) subList.get(i);
        item.setCardSubCode(card.getCardId()+"_"+(i+1));
        item.setCardId(card.getCardId());
      }
      for (int i = 0; i < usingList.size(); i++) {
        ZcFaUsing item = (ZcFaUsing) usingList.get(i);
        item.setCardId(card.getCardId());
      }

      cardDao.insert(card);
      cardDocDao.insertList(docList);
      cardSubDao.insertList(subList);
      cardUsingDao.insertList(usingList);    
      //更新卡片与采购合同的关联表
      HashMap ds=new HashMap();
      ds.put("cardId", card.getCardId());
//      ds.put("htCode", card.getCaigouHt().getZcHtCode());
//      baseDao.insert("ZcFaCard.insertCardHt", ds);

    } else {      
      for (int i = 0; i < docList.size(); i++) {
        ZcFaCardDoc doc = (ZcFaCardDoc) docList.get(i);
       doc.setCardId(card.getCardId());
      }
      for (int i = 0; i < subList.size(); i++) {
        ZcFaCardSub item = (ZcFaCardSub) subList.get(i);
        item.setCardId(card.getCardId());
      }
      for (int i = 0; i < usingList.size(); i++) {
        ZcFaUsing item = (ZcFaUsing) usingList.get(i);
        item.setCardId(card.getCardId());
      }
      cardDao.update(card);
      
      cardDocDao.deleteByCardId(card.getCardId());
      cardSubDao.deleteByCardId(card.getCardId());
      cardUsingDao.deleteByCardId(card.getCardId());
      
      cardDocDao.insertList(docList);
      cardSubDao.insertList(subList);
      cardUsingDao.insertList(subList);


    }

    if (isDraft) {

      AsWfDraft asWfDraft = new AsWfDraft();
      asWfDraft.setCompoId(compoId);
      asWfDraft.setWfDraftName(card.getCardId());
      asWfDraft.setUserId(userId);
      asWfDraft.setMasterTabId(compoId);
      asWfDraft.setWfDraftId(BigDecimal.valueOf(card.getProcessInstId().longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

    }
    return card;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#commitListFN(java.util.List, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public void commitListFN(List cardList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.service.IZcFaCardService#deleteListFN(java.util.List, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public void deleteListFN(List cardList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    for(int i=0;i<cardList.size();i++){
      ZcFaCard card=(ZcFaCard) cardList.get(i);
      deleteByCardIdFN(card.getCardId(), requestMeta);
    }
  }

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
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

  
  public ZcFaCard selectByPrimaryKey(String cardId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    ZcFaCard rtn=cardDao.selectByCardId(cardId);
    rtn.setZcFaCardDocList(baseDao.query("ZcFaCardDoc.selectByCardId", cardId));
    rtn.setZcFaCardSubList(baseDao.query("ZcFaCardSub.selectByCardId", cardId));
    rtn.setZcFaCardUsingList(baseDao.query("ZcFaUsing.selectByCardId", cardId));
    //获取相关合同信息    
    return rtn;
  }

   
  public ZcFaCard newCommitFN(ZcFaCard card, RequestMeta requestMeta) {
    wfEngineAdapter.newCommit(card.getComment(), card, requestMeta);
    return selectByPrimaryKey(card.getCardId(),requestMeta);
  }

   
  public ZcFaCard callbackFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(card.getComment(), card, requestMeta);
    return card;
  }

   
  public void deleteByCardIdFN(String cardId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    cardDao.deleteByCardIdFN(cardId);
    cardSubDao.deleteByCardId(cardId);
    cardDocDao.deleteByCardId(cardId);
    cardUsingDao.deleteByCardId(cardId);
    baseDao.delete("ZcFaCard.deleteCardHt", cardId);
  }

}
