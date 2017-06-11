package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfEntrustService;

public class SfEntrustServiceDelegate implements ISfEntrustServiceDelegate {

  private ISfEntrustService sfEntrustService;

  public List getEntrustLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.getEntrustLst(elementConditionDto, requestMeta);
  }

  public SfEntrust selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.selectByPrimaryKey(id, requestMeta);
  }

  public SfEntrust saveFN(SfEntrust inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    //    System.out.println("delegate saveFN 1++++++++++++++++++++++++++=" + inData.getAcceptDate());
    return sfEntrustService.saveFN(inData, requestMeta);
  }

  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    sfEntrustService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  public SfEntrust unAuditFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.unAuditFN(qx, requestMeta);
  }

  public SfEntrust untreadFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.untreadFN(qx, requestMeta);
  }

  public SfEntrust auditFN(SfEntrust qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.auditFN(qx, requestMeta);
  }

  public SfEntrust newCommitFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.newCommitFN(qx, requestMeta);
  }

  public SfEntrust callbackFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return sfEntrustService.callbackFN(qx, requestMeta);
  }

  public ISfEntrustService getSfEntrustService() {
    return sfEntrustService;
  }

  public void setSfEntrustService(ISfEntrustService sfEntrustService) {
    this.sfEntrustService = sfEntrustService;
  }

 
public SfEntrust acceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	return this.sfEntrustService.acceptFN(inData, requestMeta);
}

 
public SfEntrust unAcceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	return this.sfEntrustService.unAcceptFN(inData, requestMeta);
}
 
public SfEntrust selectByBarCode(String barCode, RequestMeta requestMeta) {
  return sfEntrustService.selectByBarCode(barCode, requestMeta);
}

}
