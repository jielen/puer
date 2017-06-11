package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfPrintClient;
import java.math.BigDecimal;

public interface SfPrintClientMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SfPrintClient record);

    int insertSelective(SfPrintClient record);

    SfPrintClient selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SfPrintClient record);

    int updateByPrimaryKey(SfPrintClient record);
}