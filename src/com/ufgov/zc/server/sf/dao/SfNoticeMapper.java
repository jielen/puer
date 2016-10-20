package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfNotice;
import java.math.BigDecimal;

public interface SfNoticeMapper {
    int deleteByPrimaryKey(BigDecimal noticeId);

    int insert(SfNotice record);

    int insertSelective(SfNotice record);

    SfNotice selectByPrimaryKey(BigDecimal noticeId);

    int updateByPrimaryKeySelective(SfNotice record);

    int updateByPrimaryKey(SfNotice record);
}