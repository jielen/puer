package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfSjInMapper;
import com.ufgov.zc.server.sf.service.ISfSjInService;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfSjInService implements ISfSjInService {

  private SfSjInMapper sfSjInMapper;
  
  public List getMainLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfSjInMapper.getMainLst(elementConditionDto);
  }

  
  public SfSjIn selectByPrimaryKey(BigDecimal InId, RequestMeta requestMeta) {
    return sfSjInMapper.selectByPrimaryKey(InId);
  }

  
  public SfSjIn saveFN(SfSjIn inData, RequestMeta requestMeta) throws SfBusinessException {
    if(inData.getInId()==null){
      ZcSUtil su=new ZcSUtil();
      inData.setInId(new BigDecimal(su.getNextVal(SfSjIn.SEQ_SF_SF_SJ_IN_ID)));
      sfSjInMapper.insert(inData); 
    }else{ 
      sfSjInMapper.updateByPrimaryKey(inData);
    }
    return inData;
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal InId, RequestMeta requestMeta) throws SfBusinessException {
    ElementConditionDto dto=new ElementConditionDto();
    dto.setDattr1("using");
    dto.setSfId(InId);
    List lst=sfSjInMapper.getMainLst(dto);
    if(lst!=null && lst.size()>0){
      throw new SfBusinessException("已经被使用，不能删除");
    }
    sfSjInMapper.deleteByPrimaryKey(InId);
  }


  public SfSjInMapper getSfSjInMapper() {
    return sfSjInMapper;
  }


  public void setSfSjInMapper(SfSjInMapper sfSjInMapper) {
    this.sfSjInMapper = sfSjInMapper;
  }

}
