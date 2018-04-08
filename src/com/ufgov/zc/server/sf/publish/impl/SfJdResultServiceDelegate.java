package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdResultFileStore;
import com.ufgov.zc.common.sf.publish.ISfJdResultServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdResultService;

public class SfJdResultServiceDelegate implements ISfJdResultServiceDelegate {

  private ISfJdResultService jdResultService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfJdResult selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfJdResult saveFN(SfJdResult inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdResultService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfJdResult unAuditFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.unAuditFN(qx, requestMeta);
  }

  
  public SfJdResult untreadFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.untreadFN(qx, requestMeta);
  }

  
  public SfJdResult auditFN(SfJdResult qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return jdResultService.auditFN(qx, requestMeta);
  }

  
  public SfJdResult newCommitFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.newCommitFN(qx, requestMeta);
  }

  
  public SfJdResult callbackFN(SfJdResult qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdResultService.callbackFN(qx, requestMeta);
  }


  public ISfJdResultService getJdResultService() {
    return jdResultService;
  }


  public void setJdResultService(ISfJdResultService jdResultService) {
    this.jdResultService = jdResultService;
  }


 
public SfJdResultFileStore saveFileStoreFN(SfJdResultFileStore inData,
		RequestMeta requestMeta) {
	return jdResultService.saveFileStoreFN(inData, requestMeta);
}

}
