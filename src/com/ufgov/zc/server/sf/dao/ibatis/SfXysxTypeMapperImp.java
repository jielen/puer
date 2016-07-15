package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfXysxType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfXysxTypeMapper;

public class SfXysxTypeMapperImp extends SqlMapClientDaoSupport implements SfXysxTypeMapper {

  public int deleteByPrimaryKey(BigDecimal xysxTypeId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfXysxTypeMapper.deleteByPrimaryKey", xysxTypeId);
  }

  public int insert(SfXysxType record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfXysxTypeMapper.insert", record);
    return 1;
  }

  public SfXysxType selectByPrimaryKey(BigDecimal xysxTypeId) {
    // TCJLODO Auto-generated method stub
    return (SfXysxType) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfXysxTypeMapper.selectByPrimaryKey", xysxTypeId);
  }

  public int updateByPrimaryKey(SfXysxType record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfXysxTypeMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfXysxTypeMapper.selectMainDataLst", elementConditionDto);
  }

}
