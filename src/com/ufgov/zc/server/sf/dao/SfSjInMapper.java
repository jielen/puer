package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfSjInMapper {
    int deleteByPrimaryKey(BigDecimal inId);

    int insert(SfSjIn record);

    int insertSelective(SfSjIn record);

    SfSjIn selectByPrimaryKey(BigDecimal inId);

    int updateByPrimaryKeySelective(SfSjIn record);

    int updateByPrimaryKey(SfSjIn record);
    List getMainLst(ElementConditionDto elementConditionDto);
}