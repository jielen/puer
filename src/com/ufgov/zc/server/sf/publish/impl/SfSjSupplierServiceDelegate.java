package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.publish.ISfMajorServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfSjSupplierServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfSjSupplierService;

public class SfSjSupplierServiceDelegate implements ISfSjSupplierServiceDelegate {
private ISfSjSupplierService sfSjSupplierService;
   


  public ISfSjSupplierService getSfSjSupplierService() {
    return sfSjSupplierService;
  }


  public void setSfSjSupplierService(ISfSjSupplierService sfSjSupplierService) {
    this.sfSjSupplierService = sfSjSupplierService;
  }


  
  public List getMainLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfSjSupplierService.getMainLst(elementConditionDto, requestMeta);
  }


  
  public SfSjSupplier selectByPrimaryKey(BigDecimal supplierId, RequestMeta requestMeta) {
    return sfSjSupplierService.selectByPrimaryKey(supplierId, requestMeta);
  }


  
  public SfSjSupplier saveFN(SfSjSupplier inData, RequestMeta requestMeta) throws SfBusinessException {
    return sfSjSupplierService.saveFN(inData, requestMeta);
  }


  
  public void deleteByPrimaryKeyFN(BigDecimal supplierId, RequestMeta requestMeta) throws SfBusinessException {
    sfSjSupplierService.deleteByPrimaryKeyFN(supplierId, requestMeta);
  }


  
  public boolean isUsing(BigDecimal supplierId, RequestMeta requestMeta) {
    return sfSjSupplierService.isUsing(supplierId, requestMeta);
  }

}
