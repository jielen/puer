package com.ufgov.zc.server.sf.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdPersonMapper;
import com.ufgov.zc.server.sf.dao.SfMajorMapper;
import com.ufgov.zc.server.sf.service.ISfMajorService;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfMajorService implements ISfMajorService {

  private SfMajorMapper majorMapper;
  
  private SfJdPersonMapper jdPersonMapper;
  
  private IZcEbBaseService zcEbBaseService;
  
   
  public SfJdPersonMapper getJdPersonMapper() {
    return jdPersonMapper;
  }

  public void setJdPersonMapper(SfJdPersonMapper jdPersonMapper) {
    this.jdPersonMapper = jdPersonMapper;
  }

  public List getMajorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorMapper.getMajorLst(elementConditionDto);
  }

  public SfMajorMapper getMajorMapper() {
    return majorMapper;
  }

  public void setMajorMapper(SfMajorMapper majorMapper) {
    this.majorMapper = majorMapper;
  }

  
  public SfMajor selectByPrimaryKey(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
     SfMajor rtn=majorMapper.selectByPrimaryKey(majorCode);
     if(rtn==null)return null;
     ElementConditionDto dto=new ElementConditionDto();
     dto.setDattr1(majorCode);
     rtn.setJdPersonLst(jdPersonMapper.getMainDataLst(dto));
     if(rtn.getParent()==null){
       rtn.setParent(new SfMajor());
     }
     return rtn;
  }

  
  public SfMajor saveFN(SfMajor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if(inData.getMajorCode()==null){
      String code ="";
      ElementConditionDto dto=new ElementConditionDto();
      if(inData.getParent()==null || inData.getParent().getMajorCode()==null){
        dto.setDattr1("parentLst");
      }else{
        dto.setDattr1("detailtLst");
        dto.setDattr2(inData.getParent().getMajorCode());
      }
      List l=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfMajorMapper.getMaxCode", dto);
      if(l==null || l.size()==0){
        code = NumUtil.getInstance().getNo("SF_MAJOR", "MAJOR_CODE", inData);
      }else{
        HashMap row=(HashMap) l.get(0);
        code="00"+row.get("MAXCODE");
      }
      inData.setMajorCode(code);
      majorMapper.insert(inData);
    }else{
     majorMapper.deleteByPrimaryKey(inData.getMajorCode());
     majorMapper.insert(inData);
    }
     return inData;
  }

  
  public void deleteByPrimaryKeyFN(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    majorMapper.deleteByPrimaryKey(majorCode);
  }

   
  public boolean isUsing(String majorCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return majorMapper.isUsing(majorCode);
     
  }

  public IZcEbBaseService getZcEbBaseService() {
    return zcEbBaseService;
  }

  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
    this.zcEbBaseService = zcEbBaseService;
  }


}
