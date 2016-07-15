package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbSupQualification;

public interface IZcEbSupQualificationServiceDelegate extends Publishable {

  public List selectSupQualifications(ElementConditionDto dto, RequestMeta requestMeta);

  public ZcEbSupQualification selectById(String id, RequestMeta requestMeta);

  public ZcEbSupQualification save(ZcEbSupQualification bean, RequestMeta requestMeta);

  public void enableById(String id, RequestMeta requestMeta);

  public void freezeById(String id, RequestMeta requestMeta);

  public void deleteById(String id, RequestMeta requestMeta);
}
