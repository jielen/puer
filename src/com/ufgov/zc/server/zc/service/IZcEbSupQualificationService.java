package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbSupQualification;

public interface IZcEbSupQualificationService {

  public List selectSupQualifications(ElementConditionDto dto);

  public ZcEbSupQualification selectById(String id);

  public ZcEbSupQualification save(ZcEbSupQualification bean, RequestMeta requestMeta);

  public void enableById(String id);

  public void freezeById(String id);

  public void deleteById(String id);
}
