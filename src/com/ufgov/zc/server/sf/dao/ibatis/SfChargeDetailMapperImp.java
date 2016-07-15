package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.server.sf.dao.SfChargeDetailMapper;

public class SfChargeDetailMapperImp extends SqlMapClientDaoSupport implements SfChargeDetailMapper {

  public int deleteByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfChargeDetailMapper.deleteByPrimaryKey", entrustId);

  }

  public int insert(SfChargeDetail record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfChargeDetailMapper.insert", record);
    return 1;
  }

  public List selectByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfChargeDetailMapper.selectByPrimaryKey", entrustId);

  }

}
