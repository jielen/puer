package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.sf.publish.ISfDossierServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfDossierService;

public class SfDossierServiceDelegate implements ISfDossierServiceDelegate {

  private ISfDossierService dossierService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfDossier selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfDossier saveFN(SfDossier inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    dossierService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfDossier unAuditFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.unAuditFN(qx, requestMeta);
  }

  
  public SfDossier untreadFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.untreadFN(qx, requestMeta);
  }

  
  public SfDossier auditFN(SfDossier qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return dossierService.auditFN(qx, requestMeta);
  }

  
  public SfDossier newCommitFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.newCommitFN(qx, requestMeta);
  }

  
  public SfDossier callbackFN(SfDossier qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return dossierService.callbackFN(qx, requestMeta);
  }


  public ISfDossierService getDossierService() {
    return dossierService;
  }


  public void setDossierService(ISfDossierService dossierService) {
    this.dossierService = dossierService;
  }

}
