package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.server.sf.dao.SfJdPersonMajorMapper;

public class SfJdPersonMajorMapperImp extends SqlMapClientDaoSupport implements SfJdPersonMajorMapper {

  
  public int deleteByPrimaryKey(BigDecimal jdPersonId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdPersonMajorMapper.deleteByPrimaryKey", jdPersonId);
  }

  
  public int insert(SfJdPersonMajor record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdPersonMajorMapper.insert", record);
    return 1;
  }

  
  public List selectByPrimaryKey(BigDecimal jdPersonId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdPersonMajorMapper.selectByPrimaryKey", jdPersonId);
  }

  

}
