/**
 * 
 */
package com.ufgov.zc.server.sf.service;

import java.util.List;

import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

/**
 * @author Administrator
 *
 */
public interface IZcFaCardService {

  List getCardLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  ZcFaCard unAuditFN(ZcFaCard card, RequestMeta requestMeta);

  ZcFaCard untreadFN(ZcFaCard card, RequestMeta requestMeta);

  ZcFaCard auditFN(ZcFaCard card, RequestMeta requestMeta);

  ZcFaCard updateFN(ZcFaCard card, RequestMeta requestMeta);

  void commitListFN(List cardList, RequestMeta requestMeta);

  void deleteListFN(List cardList, RequestMeta requestMeta);

  ZcFaCard selectByPrimaryKey(String cardId, RequestMeta requestMeta);

  ZcFaCard newCommitFN(ZcFaCard card, RequestMeta requestMeta);

  ZcFaCard callbackFN(ZcFaCard card, RequestMeta requestMeta);

  void deleteByCardIdFN(String cardId, RequestMeta requestMeta);

}
