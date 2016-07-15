package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbProjSupport;
import com.ufgov.zc.common.zc.publish.IZcEbEntrustBulletinServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEbEntrustBulletinService;

public class ZcEbEntrustBulletinServiceDelegate implements IZcEbEntrustBulletinServiceDelegate {

  private IZcEbEntrustBulletinService bullService;

  public List getZcEbEntrustBull(ElementConditionDto dto, RequestMeta meta) {
    return bullService.getZcEbEntrustBull(dto, meta);
  }

  public List getZcEbEntrustBullin(ElementConditionDto dto, RequestMeta meta) {
    return bullService.getZcEbEntrustBullin(dto, meta);
  }

  public List getZcEbEntrustReport(ElementConditionDto dto, RequestMeta meta) {
    return bullService.getZcEbEntrustReport(dto, meta);
  }

  public List getZcEbProjectSupport(ElementConditionDto dto, RequestMeta meta) {
    return bullService.getZcEbProjectSupport(dto, meta);
  }

  public List getZcEbPackHistory(ElementConditionDto dto, RequestMeta meta) {
    return bullService.getZcEbPackHistory(dto, meta);
  }

  public IZcEbEntrustBulletinService getBullService() {
    return bullService;
  }

  public void setBullService(IZcEbEntrustBulletinService bullService) {
    this.bullService = bullService;
  }

  public void newCommitFN(ZcEbProjSupport proj, RequestMeta meta) {
    this.bullService.newCommitFN(proj, meta);
  }

  public void auditFN(ZcEbProjSupport proj, RequestMeta meta) {
    this.bullService.auditFN(proj, meta);
  }

  public void untreadFN(ZcEbProjSupport proj, RequestMeta meta) {
    this.bullService.untreadFN(proj, meta);
  }

  public void callbackFN(ZcEbProjSupport proj, RequestMeta meta) {
    this.bullService.callbackFN(proj, meta);
  }

  public void unauditFN(ZcEbProjSupport proj, RequestMeta meta) {
    this.bullService.unauditFN(proj, meta);
  }

  public void updateSupplierIsSite(List zcEbProjSupportList, RequestMeta meta) {
    this.bullService.updateSupplierIsSite(zcEbProjSupportList);

  }

}
