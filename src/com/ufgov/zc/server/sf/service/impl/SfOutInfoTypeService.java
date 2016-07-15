package com.ufgov.zc.server.sf.service.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper;
import com.ufgov.zc.server.sf.service.ISfOutInfoTypeService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfOutInfoTypeService implements ISfOutInfoTypeService {

  private SfOutInfoTypeMapper outInfoTypeMapper;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoTypeMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfOutInfoType selectByPrimaryKey(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfOutInfoType rtn=outInfoTypeMapper.selectByPrimaryKey(code);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfOutInfoType saveFN(SfOutInfoType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if ("".equals(ZcSUtil.safeString(inData.getOutInfoTypeCode())) || inData.getOutInfoTypeCode().equals("自动编号")) {

      String code = NumUtil.getInstance().getNo("SF_OUT_INFO_TYPE", "OUT_INFO_TYPE_CODE", inData);
      inData.setOutInfoTypeCode(code);
      
      insert(inData,requestMeta);

   }else{
     update(inData,requestMeta);
   }
   return inData;
  }

  private void update(SfOutInfoType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoTypeMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfOutInfoType inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoTypeMapper.insert(inData);
  }
  
  public void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoTypeMapper.deleteByPrimaryKey(code);
  }


  public SfOutInfoTypeMapper getOutInfoTypeMapper() {
    return outInfoTypeMapper;
  }


  public void setOutInfoTypeMapper(SfOutInfoTypeMapper outInfoTypeMapper) {
    this.outInfoTypeMapper = outInfoTypeMapper;
  }

}
