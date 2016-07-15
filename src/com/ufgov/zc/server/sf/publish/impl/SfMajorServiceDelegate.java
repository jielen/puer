package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.publish.ISfMajorServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfMajorService;

public class SfMajorServiceDelegate implements ISfMajorServiceDelegate {

  private ISfMajorService majorService;
   
  public List getMajorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorService.getMajorLst(elementConditionDto, requestMeta);
  }

  public ISfMajorService getMajorService() {
    return majorService;
  }

  public void setMajorService(ISfMajorService majorService) {
    this.majorService = majorService;
  }

  
  public SfMajor selectByPrimaryKey(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorService.selectByPrimaryKey(majorCode, requestMeta);
  }

  
  public SfMajor saveFN(SfMajor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    majorService.deleteByPrimaryKeyFN(majorCode, requestMeta);
  }

   
  public boolean isUsing(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorService.isUsing(majorCode, requestMeta);
  }


}
