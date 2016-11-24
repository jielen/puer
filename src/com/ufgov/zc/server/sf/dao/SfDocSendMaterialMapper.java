package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import java.math.BigDecimal;
import java.util.List;

public interface SfDocSendMaterialMapper {
    int deleteByPrimaryKey(BigDecimal sendId);

    int insert(SfDocSendMaterial record);

    int insertSelective(SfDocSendMaterial record);

    List selectByPrimaryKey(BigDecimal sendId);

    int updateByPrimaryKeySelective(SfDocSendMaterial record);

    int updateByPrimaryKey(SfDocSendMaterial record);
}