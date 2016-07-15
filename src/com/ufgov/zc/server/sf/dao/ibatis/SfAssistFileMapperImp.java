package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfAssistFile;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfAssistFileMapper;

public class SfAssistFileMapperImp extends SqlMapClientDaoSupport implements SfAssistFileMapper {

  
  public int deleteByPrimaryKey(BigDecimal assistFileId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.deleteByPrimaryKey", assistFileId);
  }

  
  public int insert(SfAssistFile record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfAssistFile record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfAssistFile selectByPrimaryKey(BigDecimal assistFileId) {
    // TCJLODO Auto-generated method stub
    return (SfAssistFile) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.selectByPrimaryKey", assistFileId);
  }

  
  public int updateByPrimaryKeySelective(SfAssistFile record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfAssistFile record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.updateByPrimaryKey", record);
  }

  
  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfAssistFileMapper.selectMainDataLst",elementConditionDto);
  }

}
