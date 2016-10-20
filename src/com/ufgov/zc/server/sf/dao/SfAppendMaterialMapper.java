package com.ufgov.zc.server.sf.dao;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

import java.math.BigDecimal;
import java.util.List;

public interface SfAppendMaterialMapper {
  
    int deleteByPrimaryKey(BigDecimal appendMaterialId);

    int insert(SfAppendMaterial record); 

    SfAppendMaterial selectByPrimaryKey(BigDecimal appendMaterialId);

    int updateByPrimaryKey(SfAppendMaterial record);
    
    List getMainDataLst(ElementConditionDto elementConditionDto);
}