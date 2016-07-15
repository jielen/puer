package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbProjSupport;

public interface IZcEbEntrustBulletinService {

  public List getZcEbEntrustBull(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbEntrustBullin(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbEntrustReport(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbProjectSupport(ElementConditionDto dto, RequestMeta meta);

  public List getZcEbPackHistory(ElementConditionDto dto, RequestMeta meta);

  public void newCommitFN(ZcEbProjSupport proj, RequestMeta meta);

  public void auditFN(ZcEbProjSupport proj, RequestMeta meta);

  public void untreadFN(ZcEbProjSupport proj, RequestMeta meta);

  public void callbackFN(ZcEbProjSupport proj, RequestMeta meta);

  public void unauditFN(ZcEbProjSupport proj, RequestMeta meta);

  public void updateSupplierIsSite(List zcEbProjSupportList);
}
