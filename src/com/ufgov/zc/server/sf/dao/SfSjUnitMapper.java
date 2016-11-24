package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfSjUnit;
import java.math.BigDecimal;

public interface SfSjUnitMapper {
    int deleteByPrimaryKey(BigDecimal unitId);

    int insert(SfSjUnit record);

    int insertSelective(SfSjUnit record);

    SfSjUnit selectByPrimaryKey(BigDecimal unitId);

    int updateByPrimaryKeySelective(SfSjUnit record);

    int updateByPrimaryKey(SfSjUnit record);
}