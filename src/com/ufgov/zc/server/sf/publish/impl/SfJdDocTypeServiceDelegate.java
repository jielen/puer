package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdDocType;
import com.ufgov.zc.common.sf.publish.ISfJdDocTypeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdDocTypeService;

public class SfJdDocTypeServiceDelegate implements ISfJdDocTypeServiceDelegate {

  private ISfJdDocTypeService jdDocTypeService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocTypeService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public SfJdDocType selectByPrimaryKey(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocTypeService.selectByPrimaryKey(id, requestMeta);
  }

  
  public SfJdDocType saveFN(SfJdDocType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocTypeService.saveFN(inData, requestMeta);
  }

  
  public void deleteByPrimaryKeyFN(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocTypeService.deleteByPrimaryKeyFN(id, requestMeta);
  }


  public ISfJdDocTypeService getJdDocTypeService() {
    return jdDocTypeService;
  }


  public void setJdDocTypeService(ISfJdDocTypeService jdDocTypeService) {
    this.jdDocTypeService = jdDocTypeService;
  }

}
