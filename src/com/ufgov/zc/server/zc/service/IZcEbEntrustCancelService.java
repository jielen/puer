package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;

public interface IZcEbEntrustCancelService {

  public List selectZcEbEntrustCancel(ElementConditionDto elementConditionDto, RequestMeta requestMeta);
  public ZcEbEntrustCancel selectByKey(String code);

  ZcEbEntrustCancel saveZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  void deleteZcEbEntrustCancelFN(ZcEbEntrustCancel bean);

  void deleteZcEbEntrustCancelListFN(List list);

  public ZcEbEntrustCancel newCommitFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  public ZcEbEntrustCancel untreadFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);

  public ZcEbEntrustCancel unAuditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);

  public ZcEbEntrustCancel auditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  public ZcEbEntrustCancel callbackFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);
}
