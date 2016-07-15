package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbSupQualification;
import com.ufgov.zc.common.zc.publish.IZcEbSupQualificationServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcEbSupQualificationService;

public class ZcEbSupQualificationServiceDelegate implements IZcEbSupQualificationServiceDelegate {
  private IZcEbSupQualificationService zcEbSupQualificationService;

  public IZcEbSupQualificationService getZcEbSupQualificationService() {
    return zcEbSupQualificationService;
  }

  public void setZcEbSupQualificationService(IZcEbSupQualificationService zcEbSupQualificationService) {
    this.zcEbSupQualificationService = zcEbSupQualificationService;
  }

  
  public List selectSupQualifications(ElementConditionDto dto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbSupQualificationService.selectSupQualifications(dto);
  }

  
  public ZcEbSupQualification selectById(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbSupQualificationService.selectById(id);
  }

  
  public ZcEbSupQualification save(ZcEbSupQualification bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcEbSupQualificationService.save(bean, requestMeta);
  }

  
  public void enableById(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcEbSupQualificationService.enableById(id);
  }

  
  public void freezeById(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcEbSupQualificationService.freezeById(id);
  }

  
  public void deleteById(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcEbSupQualificationService.deleteById(id);
  }

}
