package com.ufgov.zc.server.sf.service.impl;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper;
import com.ufgov.zc.server.sf.service.ISfOutInfoReqService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfOutInfoReqService implements ISfOutInfoReqService {

  private SfOutInfoReqMapper outInfoReqMapper;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoReqMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfOutInfoReq selectByPrimaryKey(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return outInfoReqMapper.selectByPrimaryKey(code);
  }

  
  public SfOutInfoReq saveFN(SfOutInfoReq inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if ("".equals(ZcSUtil.safeString(inData.getOutInfoReqCode())) || inData.getOutInfoReqCode().equals("自动编号")) {

      String code = NumUtil.getInstance().getNo("SF_OUT_INFO_REQ", "OUT_INFO_REQ_CODE", inData);
      inData.setOutInfoReqCode(code);      
      insert(inData,requestMeta);
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }

  private void update(SfOutInfoReq inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoReqMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfOutInfoReq inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoReqMapper.insert(inData);
  }

  
  public void deleteByPrimaryKeyFN(String code, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    outInfoReqMapper.deleteByPrimaryKey(code);
  }


  public SfOutInfoReqMapper getOutInfoReqMapper() {
    return outInfoReqMapper;
  }


  public void setOutInfoReqMapper(SfOutInfoReqMapper outInfoReqMapper) {
    this.outInfoReqMapper = outInfoReqMapper;
  }

}
