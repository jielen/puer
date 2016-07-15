package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.server.sf.dao.SfXysxMapper;

public class SfXysxMapperImp extends SqlMapClientDaoSupport implements SfXysxMapper {

  public int deleteByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfXysxMapper.deleteByPrimaryKey", entrustId);
  }

  public int insert(SfXysx record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfXysxMapper.insert", record);
    return 1;
  }

  public List selectByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfXysxMapper.selectByPrimaryKey", entrustId);
  }

}
