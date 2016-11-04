package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.sf.publish.ISfZongheZhibanServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfZongheZhibanService;


public class SfZongheZhibanServiceDelegate implements ISfZongheZhibanServiceDelegate {

 private ISfZongheZhibanService sfZongheZhibanService;
 
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfZongheZhibanService.getMainDataLst(elementConditionDto, requestMeta);
  }

 
  public SfZongheZhiban selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    return sfZongheZhibanService.selectByPrimaryKey(id, requestMeta);
  }

 
  public SfZongheZhiban getCurrent(ElementConditionDto dto, RequestMeta requestMeta) {
    return sfZongheZhibanService.getCurrent(dto, requestMeta);
  }

 
  public SfZongheZhiban saveFN(SfZongheZhiban inData, RequestMeta requestMeta) {
    return sfZongheZhibanService.saveFN(inData, requestMeta);
  }

 
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfZongheZhibanService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfZongheZhibanService getSfZongheZhibanService() {
    return sfZongheZhibanService;
  }


  public void setSfZongheZhibanService(ISfZongheZhibanService sfZongheZhibanService) {
    this.sfZongheZhibanService = sfZongheZhibanService;
  }

}
