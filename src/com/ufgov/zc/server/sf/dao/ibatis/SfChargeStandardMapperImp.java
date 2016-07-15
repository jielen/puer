package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfChargeStandardMapper;

public class SfChargeStandardMapperImp extends SqlMapClientDaoSupport implements SfChargeStandardMapper {

  
  public int deleteByPrimaryKey(BigDecimal chargeStandardId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfChargeStandardMapper.deleteByPrimaryKey", chargeStandardId);
  }

  
  public int insert(SfChargeStandard record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfChargeStandardMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfChargeStandard record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfChargeStandard selectByPrimaryKey(BigDecimal chargeStandardId) {
    // TCJLODO Auto-generated method stub
    return (SfChargeStandard) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfChargeStandardMapper.selectByPrimaryKey", chargeStandardId);
  }

  
  public int updateByPrimaryKeySelective(SfChargeStandard record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfChargeStandard record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfChargeStandardMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto dto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfChargeStandardMapper.selectMainDataLst",dto);
  }
}
