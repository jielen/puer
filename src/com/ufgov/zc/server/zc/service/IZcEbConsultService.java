package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbConsult;

public interface IZcEbConsultService {

  public List findConsultList(ElementConditionDto dto, RequestMeta meta);

  public ZcEbConsult findConsultById(String id, RequestMeta meta);

  public void deleteConsultById(String id, RequestMeta meta);

  public void deleteConsultByIds(List ids, RequestMeta meta);

  public ZcEbConsult updateConsult(ZcEbConsult bean, RequestMeta meta);

  public ZcEbConsult newCommitFN(ZcEbConsult bean, RequestMeta meta);

  public ZcEbConsult auditFN(ZcEbConsult bean, RequestMeta meta);

  public ZcEbConsult untreadFN(ZcEbConsult bean, RequestMeta meta);

  public ZcEbConsult callbackFN(ZcEbConsult bean, RequestMeta meta);

  public ZcEbConsult unAuditFN(ZcEbConsult bean, RequestMeta meta);
}
