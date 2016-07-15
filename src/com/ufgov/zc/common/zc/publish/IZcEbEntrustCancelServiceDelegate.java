package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;

public interface IZcEbEntrustCancelServiceDelegate extends Publishable {

  public List selectZcEbEntrustCancel(ElementConditionDto elementConditionDto, RequestMeta requestMeta);
  public ZcEbEntrustCancel selectByKey(String code, RequestMeta requestMeta);

  ZcEbEntrustCancel saveZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  void deleteZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);

  void deleteZcEbEntrustCancelListFN(List list, RequestMeta requestMeta);

  public ZcEbEntrustCancel newCommitFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  public ZcEbEntrustCancel untreadFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);

  public ZcEbEntrustCancel unAuditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);

  public ZcEbEntrustCancel auditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception ;

  public ZcEbEntrustCancel callbackFN(ZcEbEntrustCancel bean, RequestMeta requestMeta);
}
