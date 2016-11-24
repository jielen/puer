package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfChargeStandardMapper;
import com.ufgov.zc.server.sf.service.ISfChargeStandardService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfChargeStandardService implements ISfChargeStandardService {

  private SfChargeStandardMapper chargeStandardMapper;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeStandardMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfChargeStandard selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return chargeStandardMapper.selectByPrimaryKey(id);
  }

  
  public SfChargeStandard saveFN(SfChargeStandard inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    if ("".equals(ZcSUtil.safeString(inData.getCode())) || inData.getCode().equals("自动编号")) {

      String code = NumUtil.getInstance().getNo("SF_CHARGE_STANDARD", "CODE", inData);
      inData.setCode(code);
      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfChargeStandard.SEQ_SF_CHARGE_STANDARD_ID));
      inData.setChargeStandardId(id);    
      insert(inData,requestMeta);
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }


  private void update(SfChargeStandard inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeStandardMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfChargeStandard inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeStandardMapper.insert(inData);
  }
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    chargeStandardMapper.deleteByPrimaryKey(id);
  }


  public SfChargeStandardMapper getChargeStandardMapper() {
    return chargeStandardMapper;
  }


  public void setChargeStandardMapper(SfChargeStandardMapper chargeStandardMapper) {
    this.chargeStandardMapper = chargeStandardMapper;
  }

}
