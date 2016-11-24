package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfSjOutMapper {
    int deleteByPrimaryKey(BigDecimal outId);

    int insert(SfSjOut record);

    int insertSelective(SfSjOut record);

    SfSjOut selectByPrimaryKey(BigDecimal outId);

    int updateByPrimaryKeySelective(SfSjOut record);

    int updateByPrimaryKey(SfSjOut record);
    
    List getMainDataLst(ElementConditionDto elementConditionDto);
}