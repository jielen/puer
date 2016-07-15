package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQb;

public interface IZcQbServiceDelegate extends Publishable {

  List getQbLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta);

  void cancelFn(ZcQb qb, RequestMeta requestMeta);

  ZcQb unAuditFN(ZcQb qb, RequestMeta requestMeta);

  ZcQb untreadFN(ZcQb qb, RequestMeta requestMeta);

  ZcQb auditFN(ZcQb qb, RequestMeta requestMeta) throws Exception;

  ZcQb updateFN(ZcQb qb, RequestMeta requestMeta) throws Exception;

  void commitFN(List beanList, RequestMeta requestMeta);

  void deleteListFN(List beanList, RequestMeta requestMeta);

  void deleteFN(ZcQb qb, RequestMeta requestMeta);

  ZcQb selectByPrimaryKey(String qbCode, RequestMeta requestMeta);

  ZcQb callbackFN(ZcQb qb, RequestMeta requestMeta);

  void deleteByPrimaryKeyFN(String qbCode, RequestMeta requestMeta);

  ZcQb newCommitFN(ZcQb qb, RequestMeta requestMeta);

  ZcQb sendPayFN(ZcQb qb, RequestMeta requestMeta) throws Exception;


}
