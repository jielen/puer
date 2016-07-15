package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.sf.publish.ISfReceiptServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfReceiptService;

public class SfReceiptServiceDelegate implements ISfReceiptServiceDelegate {

  private ISfReceiptService receiptService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfReceipt selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfReceipt saveFN(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptService.deleteByPrimaryKeyFN(id, requestMeta);
  }

  
  public SfReceipt unAuditFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.unAuditFN(qx, requestMeta);
  }

  
  public SfReceipt untreadFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.untreadFN(qx, requestMeta);
  }

  
  public SfReceipt auditFN(SfReceipt qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    return receiptService.auditFN(qx, requestMeta);
  }

  
  public SfReceipt newCommitFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.newCommitFN(qx, requestMeta);
  }

  
  public SfReceipt callbackFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptService.callbackFN(qx, requestMeta);
  }


  public ISfReceiptService getReceiptService() {
    return receiptService;
  }


  public void setReceiptService(ISfReceiptService receiptService) {
    this.receiptService = receiptService;
  }

}
