package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;
import com.ufgov.zc.server.sf.dao.SfOutInfoValidateDetailMapper;

public class SfOutInfoValidateDetailMapperImp extends SqlMapClientDaoSupport implements SfOutInfoValidateDetailMapper {

  
  public int deleteByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoValidateDetailMapper.deleteByPrimaryKey", outInfoId);
  }

  
  public int insert(SfOutInfoValidateDetail record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfOutInfoValidateDetailMapper.insert", record);
    return 1;
  }

  
  public List selectByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfOutInfoValidateDetailMapper.selectByPrimaryKey", outInfoId);
    
  }

}
