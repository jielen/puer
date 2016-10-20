package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfEntrustorWtcode;
import java.math.BigDecimal;

public interface SfEntrustorWtcodeMapper {
    int deleteByPrimaryKey(BigDecimal wtcodeId);

    int insert(SfEntrustorWtcode record);

    int insertSelective(SfEntrustorWtcode record);

    SfEntrustorWtcode selectByPrimaryKey(BigDecimal wtcodeId);

    int updateByPrimaryKeySelective(SfEntrustorWtcode record);

    int updateByPrimaryKey(SfEntrustorWtcode record);
}