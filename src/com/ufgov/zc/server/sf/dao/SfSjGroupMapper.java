package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfSjGroup;
import java.math.BigDecimal;

public interface SfSjGroupMapper {
    int deleteByPrimaryKey(BigDecimal groupId);

    int insert(SfSjGroup record);

    int insertSelective(SfSjGroup record);

    SfSjGroup selectByPrimaryKey(BigDecimal groupId);

    int updateByPrimaryKeySelective(SfSjGroup record);

    int updateByPrimaryKey(SfSjGroup record);
}