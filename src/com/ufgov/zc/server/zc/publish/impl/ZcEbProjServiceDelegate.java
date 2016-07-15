package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.zc.model.ZcEbPack;
import com.ufgov.zc.common.zc.model.ZcEbProj;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.common.zc.publish.IZcEbProjServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEbProjService;

public class ZcEbProjServiceDelegate implements IZcEbProjServiceDelegate {

  private IZcEbProjService zcEbProjService;

  public List getZcEbProj(ElementConditionDto dto, RequestMeta meta) {

    return zcEbProjService.getZcEbProj(dto, meta);

  }

  public IZcEbProjService getZcEbProjService() {

    return zcEbProjService;

  }

  public void setZcEbProjService(IZcEbProjService zcEbProjService) {

    this.zcEbProjService = zcEbProjService;

  }

  public void delete(String projCode, RequestMeta requestMeta) {

    this.zcEbProjService.delete(projCode);

  }

  public List getZcEbPackListByProjCode(String projCode, RequestMeta requestMeta) {

    return this.zcEbProjService.getZcEbPackListByProjCode(projCode);

  }

  public ZcEbProj save(ZcEbProj zcEbProj, RequestMeta requestMeta) {

    return this.zcEbProjService.save(zcEbProj, requestMeta);

  }

  public ZcEbProj getZcEbProjByProjCode(String projCode, RequestMeta requestMeta) {

    return this.zcEbProjService.getZcEbProjByProjCode(projCode);

  }

  public ZcEbProj newCommitFN(ZcEbProj proj, RequestMeta requestMeta) {

    return this.zcEbProjService.newCommitFN(proj, requestMeta);

  }

  public ZcEbProj auditFN(ZcEbProj proj, RequestMeta requestMeta) {

    return this.zcEbProjService.auditFN(proj, requestMeta);

  }

  public ZcEbProj unAuditFN(ZcEbProj proj, RequestMeta requestMeta) throws Exception {

    return this.zcEbProjService.unAuditFN(proj, requestMeta);

  }

  public ZcEbProj untreadFN(ZcEbProj proj, RequestMeta requestMeta) {

    return this.zcEbProjService.untreadFN(proj, requestMeta);

  }

  public ZcEbProj callbackFN(ZcEbProj proj, RequestMeta requestMeta) {

    return this.zcEbProjService.callbackFN(proj, requestMeta);

  }

  public void updateZcEbPack(ZcEbPack pack, RequestMeta meta) {

    this.zcEbProjService.updateZcEbPack(pack);

  }

  public List getBidConditions(String projCode, RequestMeta requestMeta) {

    return this.zcEbProjService.getBidConditions(projCode);

  }

  public List getProjXunJia(ElementConditionDto dto, RequestMeta requestMeta) {

    return this.zcEbProjService.getProjXunJia(dto);

  }

  public List auditFN(List beanList, RequestMeta requestMeta) {

    return this.zcEbProjService.auditFN(beanList, requestMeta);

  }

  public List save(List beanList, RequestMeta requestMeta) {

    return this.zcEbProjService.save(beanList);

  }

  public AsFile getXunJiaWordContent(ZcEbProj zcEbProj, RequestMeta requestMeta, String fileID, boolean isWaitFrelease) {

    // TCJLODO Auto-generated method stub

    return zcEbProjService.getXunJiaWordContent(zcEbProj, fileID, isWaitFrelease);

  }

  public List getZcEbProjWithPackesDetails(ElementConditionDto dto, RequestMeta requestMeta) {
    return zcEbProjService.getZcEbProjWithPacks(dto, requestMeta);

  }

  public String getZcEbXiebanPerson(String projCode, RequestMeta requestMeta) {

    return zcEbProjService.getZcEbXiebanPerson(projCode);

  }

  public List getFormulaPackListByProjCode(String projCode, RequestMeta requestMeta) {
    return zcEbProjService.getFormulaPackListByProjCode(projCode);

  }

  public List getZcEbSignupProj(ElementConditionDto dto, RequestMeta meta) {
    return zcEbProjService.getZcEbSignupProj(dto);
  }

  public List getZcEbProjPackVO(ElementConditionDto dto, RequestMeta meta) {
    return zcEbProjService.getZcEbProjPackVO(dto, meta);

  }

  
  public boolean checkStatus(ZcEbPack pack, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbProjService.checkStatus(pack);
  }

  
  public List queryExportsDatas(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbProjService.queryExportsDatas(dto, meta);
  }

   
  public String importTransDatasFN(ZcEbProj proj, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbProjService.importTransDatasFN(proj, meta);
  }

   
  public List getExportsZbFile(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcEbProjService.getExportsZbFile(dto, meta);
  }
}
