package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfDocSendMapper {
    int deleteByPrimaryKey(BigDecimal sendId);

    int insert(SfDocSend record);

    int insertSelective(SfDocSend record);

    SfDocSend selectByPrimaryKey(BigDecimal sendId);

    int updateByPrimaryKeySelective(SfDocSend record);

    int updateByPrimaryKey(SfDocSend record);
    
    List getMainDataLst(ElementConditionDto elementConditionDto);
}