package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfAgreementMapper;

public class SfAgreementMapperImp extends SqlMapClientDaoSupport implements SfAgreementMapper {

  
  public int deleteByPrimaryKey(BigDecimal agreementId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfAgreementMapper.deleteByPrimaryKey", agreementId);
  }

  
  public int insert(SfAgreement record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfAgreementMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfAgreement record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfAgreement selectByPrimaryKey(BigDecimal agreementId) {
    // TCJLODO Auto-generated method stub
    return (SfAgreement) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfAgreementMapper.selectByPrimaryKey", agreementId);
  }

  
  public int updateByPrimaryKeySelective(SfAgreement record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfAgreement record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfAgreementMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfAgreementMapper.selectMainDataLst",elementConditionDto);
  }

}
