package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper;

public class SfJdDocAuditDetailMapperImp extends SqlMapClientDaoSupport implements SfJdDocAuditDetailMapper {

  
  public int deleteByPrimaryKey(BigDecimal jdDocAuditId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper.deleteByPrimaryKey", jdDocAuditId);
  }

  
  public int insert(SfJdDocAuditDetail record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfJdDocAuditDetail record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public List selectByPrimaryKey(BigDecimal jdDocAuditId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdDocAuditDetailMapper.selectByPrimaryKey", jdDocAuditId);
  }

  
}
