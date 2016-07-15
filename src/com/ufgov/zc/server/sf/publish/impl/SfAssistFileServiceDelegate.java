package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.publish.ISfAssistFileServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfAssistFileService;

public class SfAssistFileServiceDelegate implements ISfAssistFileServiceDelegate {

  private ISfAssistFileService assistFileService;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return assistFileService.getMainDataLst(elementConditionDto, requestMeta);
  }

  
  public List saveFN(List fileLst, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return assistFileService.saveFN(fileLst, requestMeta);
  }


  public ISfAssistFileService getAssistFileService() {
    return assistFileService;
  }


  public void setAssistFileService(ISfAssistFileService assistFileService) {
    this.assistFileService = assistFileService;
  }

}
