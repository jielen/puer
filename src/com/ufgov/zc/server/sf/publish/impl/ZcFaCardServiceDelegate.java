/**
 * 
 */
package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.publish.IZcFaCardServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.IZcFaCardService;

/**
 * @author Administrator
 *
 */
public class ZcFaCardServiceDelegate implements IZcFaCardServiceDelegate {
  private IZcFaCardService cardService;

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#getCardLst(com.ufgov.zc.common.system.dto.ElementConditionDto, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public List getCardLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.getCardLst(elementConditionDto, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#unAuditFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard unAuditFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.unAuditFN(card, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#untreadFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard untreadFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.untreadFN(card, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#auditFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard auditFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.auditFN(card, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#updateFN(com.ufgov.zc.common.zc.model.ZcFaCard, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public ZcFaCard updateFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.updateFN(card, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#commitListFN(java.util.List, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public void commitListFN(List cardList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    cardService.commitListFN(cardList, requestMeta);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.common.zc.publish.IZcFaCardServiceDelegate#deleteListFN(java.util.List, com.ufgov.zc.common.system.RequestMeta)
   */
  
  public void deleteListFN(List cardList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    cardService.deleteListFN(cardList, requestMeta);
  }

  public IZcFaCardService getCardService() {
    return cardService;
  }

  public void setCardService(IZcFaCardService cardService) {
    this.cardService = cardService;
  }

   
  public ZcFaCard selectByPrimaryKey(String cardId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.selectByPrimaryKey(cardId, requestMeta);
  }

   
  public ZcFaCard newCommitFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.newCommitFN(card, requestMeta);
  }

   
  public ZcFaCard callbackFN(ZcFaCard card, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return cardService.callbackFN(card, requestMeta);
  }

   
  public void deleteByCardIdFN(String cardId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    cardService.deleteByCardIdFN(cardId, requestMeta);
  }

}
