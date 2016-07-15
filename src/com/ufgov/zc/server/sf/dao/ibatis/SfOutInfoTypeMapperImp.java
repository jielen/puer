package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper;

public class SfOutInfoTypeMapperImp extends SqlMapClientDaoSupport implements SfOutInfoTypeMapper {


  public int deleteByPrimaryKey(String outInfoTypeCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper.deleteByPrimaryKey", outInfoTypeCode);
  }

  
  public int insert(SfOutInfoType record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfOutInfoType record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfOutInfoType selectByPrimaryKey(String outInfoTypeCode) {
    // TCJLODO Auto-generated method stub
    return (SfOutInfoType) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper.selectByPrimaryKey", outInfoTypeCode);
  }

  
  public int updateByPrimaryKeySelective(SfOutInfoType record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfOutInfoType record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfOutInfoTypeMapper.selectMainDataLst",elementConditionDto);
  }

}
