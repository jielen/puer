package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdTargetMapper;
import com.ufgov.zc.server.sf.service.ISfJdTargetService;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdTargetService implements ISfJdTargetService {

  private SfJdTargetMapper jdTargetMapper;
  
  public List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetMapper.getJdTargetLst(elementConditionDto);
  }
  
  public SfJdTarget selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetMapper.selectByPrimaryKey(id);
  }

  
  public SfJdTarget saveFN(SfJdTarget inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    if (inData.getJdTargetId()==null) {

       BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfJdTarget.SEQ_SF_JD_TARGET_ID));
       inData.setJdTargetId(id);       
       insert(inData,requestMeta);
    }else{
      update(inData,requestMeta);
    }
    return inData;
  }
  
  private void update(SfJdTarget inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdTargetMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfJdTarget inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdTargetMapper.insert(inData);
  }
  
  public boolean isUsing(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdTargetMapper.isUsing(id);
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdTargetMapper.deleteByPrimaryKey(id);
  }


  public SfJdTargetMapper getJdTargetMapper() {
    return jdTargetMapper;
  }


  public void setJdTargetMapper(SfJdTargetMapper jdTargetMapper) {
    this.jdTargetMapper = jdTargetMapper;
  }

}
