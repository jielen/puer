package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.publish.ISfAppendMaterialServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfAppendMaterialService;

public class SfAppendMaterialServiceDelegate implements ISfAppendMaterialServiceDelegate {

  private ISfAppendMaterialService sfAppendMaterialService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfAppendMaterial selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfAppendMaterial saveFN(SfAppendMaterial inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    sfAppendMaterialService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public SfAppendMaterial unAuditFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.unAuditFN(qx, requestMeta);
  }

  
  public SfAppendMaterial untreadFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.untreadFN(qx, requestMeta);
  }

  
  public SfAppendMaterial auditFN(SfAppendMaterial qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.auditFN(qx, requestMeta);
  }

  
  public SfAppendMaterial newCommitFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.newCommitFN(qx, requestMeta);
  }

  
  public SfAppendMaterial callbackFN(SfAppendMaterial qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfAppendMaterialService.callbackFN(qx, requestMeta);
  }


  public ISfAppendMaterialService getSfAppendMaterialService() {
    return sfAppendMaterialService;
  }


  public void setSfAppendMaterialService(ISfAppendMaterialService sfAppendMaterialService) {
    this.sfAppendMaterialService = sfAppendMaterialService;
  }

}
