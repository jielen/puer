package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdPersonMajorMapper;
import com.ufgov.zc.server.sf.dao.SfJdPersonMapper;
import com.ufgov.zc.server.sf.service.ISfJdPersonService;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdPersonService implements ISfJdPersonService {

  private SfJdPersonMapper jdPersonMapper;
  
  private SfJdPersonMajorMapper jdPersonMajorMapper;
  
  public SfJdPersonMapper getJdPersonMapper() {
    return jdPersonMapper;
  }


  public void setJdPersonMapper(SfJdPersonMapper jdPersonMapper) {
    this.jdPersonMapper = jdPersonMapper;
  }


  public SfJdPersonMajorMapper getJdPersonMajorMapper() {
    return jdPersonMajorMapper;
  }


  public void setJdPersonMajorMapper(SfJdPersonMajorMapper jdPersonMajorMapper) {
    this.jdPersonMajorMapper = jdPersonMajorMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return jdPersonMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfJdPerson selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfJdPerson rtn=jdPersonMapper.selectByPrimaryKey(id);
    rtn.setMajorLst(jdPersonMajorMapper.selectByPrimaryKey(id));
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfJdPerson saveFN(SfJdPerson inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getJdPersonId()==null ) {

      BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfJdPerson.SEQ_SF_JD_PERSON_ID));
      inData.setJdPersonId(id);  
  
      insert(inData,requestMeta);
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }

  private void update(SfJdPerson inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdPersonMapper.updateByPrimaryKey(inData);
    
    jdPersonMajorMapper.deleteByPrimaryKey(inData.getJdPersonId());
    if(inData.getMajorLst()!=null){
      for(int i=0;i<inData.getMajorLst().size();i++){
        SfJdPersonMajor m=(SfJdPersonMajor) inData.getMajorLst().get(i);
        m.setJdPersonId(inData.getJdPersonId());
        jdPersonMajorMapper.insert(m);
      }
    }
  }

  private void insert(SfJdPerson inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdPersonMapper.insert(inData);
    if(inData.getMajorLst()!=null){
      for(int i=0;i<inData.getMajorLst().size();i++){
        SfJdPersonMajor m=(SfJdPersonMajor) inData.getMajorLst().get(i);
        m.setJdPersonId(inData.getJdPersonId());
        jdPersonMajorMapper.insert(m);
      }
    }
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    jdPersonMapper.deleteByPrimaryKey(id);
    jdPersonMajorMapper.deleteByPrimaryKey(id);
  }

}
