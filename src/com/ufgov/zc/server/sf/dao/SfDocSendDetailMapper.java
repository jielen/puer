package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import java.math.BigDecimal;
import java.util.List;

public interface SfDocSendDetailMapper {
    int deleteByPrimaryKey(BigDecimal sendId);

    int insert(SfDocSendDetail record);

    int insertSelective(SfDocSendDetail record);

    List selectByPrimaryKey(BigDecimal sendId);

    int updateByPrimaryKeySelective(SfDocSendDetail record);

    int updateByPrimaryKey(SfDocSendDetail record);
}