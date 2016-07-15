package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEvaluationMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfEvaluationMapperImp extends SqlMapClientDaoSupport implements SfEvaluationMapper {

  public int deleteByPrimaryKey(BigDecimal evaluationId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.deleteByPrimaryKey", evaluationId);
  }

  public int insert(SfEvaluation record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfEvaluation record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfEvaluation selectByPrimaryKey(BigDecimal evaluationId) {
    // TCJLODO Auto-generated method stub
    return (SfEvaluation) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.selectByPrimaryKey", evaluationId);
  }

  public int updateByPrimaryKeySelective(SfEvaluation record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfEvaluation record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.updateByPrimaryKey", record);
  }

  public List getEvaluationLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.selectEvaluationLst", elementConditionDto);
  }

}
