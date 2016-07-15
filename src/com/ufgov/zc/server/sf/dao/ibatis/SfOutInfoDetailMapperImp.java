package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper;

public class SfOutInfoDetailMapperImp extends SqlMapClientDaoSupport implements SfOutInfoDetailMapper {

  
  public int deleteByPrimaryKey(BigDecimal outInfoDetailId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.deleteByPrimaryKey", outInfoDetailId);
    
  }

  
  public int insert(SfOutInfoDetail record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.insert", record);
    return 1;
  }

  
  public SfOutInfoDetail selectByPrimaryKey(BigDecimal outInfoDetailId) {
    // TCJLODO Auto-generated method stub
    return (SfOutInfoDetail) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.selectByPrimaryKey", outInfoDetailId);
  }

  
  public int updateByPrimaryKey(SfOutInfoDetail record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.updateByPrimaryKey", record);
  }

  
  public List selectByOutInfoId(ElementConditionDto dto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.selectByOutInfoId", dto);
  }

  
  public int deleteByOutInfoId(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoDetailMapper.deleteByOutInfoId", outInfoId);
  }

}
