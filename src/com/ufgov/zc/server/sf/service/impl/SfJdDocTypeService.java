package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfJdDocType;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper;
import com.ufgov.zc.server.sf.service.ISfJdDocTypeService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdDocTypeService implements ISfJdDocTypeService {

  private SfJdDocTypeMapper jdDocTypeMapper;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocTypeMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfJdDocType selectByPrimaryKey(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdDocTypeMapper.selectByPrimaryKey(id);
  }

  
  public SfJdDocType saveFN(SfJdDocType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    if ("".equals(ZcSUtil.safeString(inData.getDocTypeCode())) || inData.getDocTypeCode().equals("自动编号")) {

      String code = NumUtil.getInstance().getNo("SF_JD_DOC_TYPE", "DOC_TYPE_CODE", inData);
      inData.setDocTypeCode(code); 
      insert(inData,requestMeta);
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }


  private void update(SfJdDocType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocTypeMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfJdDocType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocTypeMapper.insert(inData);
  }

  
  public void deleteByPrimaryKeyFN(String id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdDocTypeMapper.deleteByPrimaryKey(id);
  }


  public SfJdDocTypeMapper getJdDocTypeMapper() {
    return jdDocTypeMapper;
  }


  public void setJdDocTypeMapper(SfJdDocTypeMapper jdDocTypeMapper) {
    this.jdDocTypeMapper = jdDocTypeMapper;
  }

}
