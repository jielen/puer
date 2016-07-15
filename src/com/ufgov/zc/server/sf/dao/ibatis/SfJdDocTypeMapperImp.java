package com.ufgov.zc.server.sf.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdDocType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper;

public class SfJdDocTypeMapperImp extends SqlMapClientDaoSupport implements SfJdDocTypeMapper {

  
  public int deleteByPrimaryKey(String docTypeCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper.deleteByPrimaryKey", docTypeCode);
  }

  
  public int insert(SfJdDocType record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper.insert", record);
    return 1;
  }

  
  public SfJdDocType selectByPrimaryKey(String docTypeCode) {
    // TCJLODO Auto-generated method stub
    return (SfJdDocType) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper.selectByPrimaryKey", docTypeCode);
  }

  
  public int updateByPrimaryKey(SfJdDocType record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto dto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdDocTypeMapper.selectMainDataLst",dto);
  }

}
