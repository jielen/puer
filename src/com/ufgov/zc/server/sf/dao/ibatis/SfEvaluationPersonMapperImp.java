package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.server.sf.dao.SfEvaluationPersonMapper;

public class SfEvaluationPersonMapperImp extends SqlMapClientDaoSupport implements SfEvaluationPersonMapper {

  
  public int deleteByPrimaryKey(BigDecimal evaluationId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfEvaluationPersonMapper.deleteByPrimaryKey", evaluationId);
  }

  
  public int insert(SfEvaluationPerson record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfEvaluationPersonMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfEvaluationPerson record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public List selectByPrimaryKey(BigDecimal evaluationId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfEvaluationPersonMapper.selectByPrimaryKey", evaluationId);
  }

  

}
