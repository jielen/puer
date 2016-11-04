package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfZongheZhibanMapper {
    int deleteByPrimaryKey(BigDecimal zhibanId);

    int insert(SfZongheZhiban record); 

    SfZongheZhiban selectByPrimaryKey(BigDecimal zhibanId); 
    
    SfZongheZhiban getCurrentZhiban(ElementConditionDto dto); 

    int updateByPrimaryKey(SfZongheZhiban record);
    
    List getMainDataLst(ElementConditionDto elementConditionDto);
}