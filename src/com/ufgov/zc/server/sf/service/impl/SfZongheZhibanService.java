package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper;
import com.ufgov.zc.server.sf.service.ISfZongheZhibanService;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfZongheZhibanService implements ISfZongheZhibanService {

  private SfZongheZhibanMapper sfZongheZhibanMapper;
 
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfZongheZhibanMapper.getMainDataLst(elementConditionDto);
  }

 
  public SfZongheZhiban selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    return sfZongheZhibanMapper.selectByPrimaryKey(id);
  }

 
  public SfZongheZhiban getCurrent(ElementConditionDto dto, RequestMeta requestMeta) {
    return sfZongheZhibanMapper.getCurrentZhiban(dto);
  }

 
  public SfZongheZhiban saveFN(SfZongheZhiban inData, RequestMeta requestMeta) {
    boolean isNew=false;
    if(inData.getZhibanId()==null){
      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfZongheZhiban.SEQ_SF_ZONGHE_ZHIBAN_ID));
      inData.setZhibanId(id);
      inData.setStartTime(new Date());
      isNew=true;
    }
    ElementConditionDto dto=new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    SfZongheZhiban cur=getCurrent(dto, requestMeta);
    if(cur!=null && isNew){
      cur.setEndTime(new Date());
      sfZongheZhibanMapper.updateByPrimaryKey(cur);
    }
    if(isNew){
      sfZongheZhibanMapper.insert(inData);
    }else{
      sfZongheZhibanMapper.updateByPrimaryKey(inData);
    }
    return selectByPrimaryKey(inData.getZhibanId(), requestMeta);
  }

 
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfZongheZhibanMapper.deleteByPrimaryKey(id);
  }


  public SfZongheZhibanMapper getSfZongheZhibanMapper() {
    return sfZongheZhibanMapper;
  }


  public void setSfZongheZhibanMapper(SfZongheZhibanMapper sfZongheZhibanMapper) {
    this.sfZongheZhibanMapper = sfZongheZhibanMapper;
  }

}
