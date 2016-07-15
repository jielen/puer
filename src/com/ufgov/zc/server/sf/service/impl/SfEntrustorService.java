package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEntrustorMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustorService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfEntrustorService implements ISfEntrustorService {

  private SfEntrustorMapper entrustorMapper;
  
  public SfEntrustorMapper getEntrustorMapper() {
    return entrustorMapper;
  }


  public void setEntrustorMapper(SfEntrustorMapper entrustorMapper) {
    this.entrustorMapper = entrustorMapper;
  }


  public List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.getEntrustorLst(elementConditionDto);
  }

  
  public SfEntrustor selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.selectByPrimaryKey(id);
  }

  
  public SfEntrustor saveFN(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getEntrustorId()==null) {

       String code = NumUtil.getInstance().getNo("SF_ENTRUSTOR", "CODE", inData);
       inData.setCode(code);
       BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfEntrustor.SEQ_SF_ENTRUSTOR_ID));
       inData.setEntrustorId(id);
       
       insert(inData,requestMeta);
    }else{
      update(inData,requestMeta);
    }
    return inData;
  }

  
  private void update(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.updateByPrimaryKey(inData);
  }


  private void insert(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.insert(inData);
  }


  public boolean isUsing(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.isUsing(id) ;
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.deleteByPrimaryKey(id);
  }


   
  public SfEntrustor selectByName(String name, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.selectByName(name);
  }




}
