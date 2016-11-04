package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfZongheZhibanMapperImp  extends SqlMapClientDaoSupport implements SfZongheZhibanMapper {

  public int deleteByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.deleteByPrimaryKey", id);
  }

  public int insert(SfZongheZhiban record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.insert", record);
    return 1;
  }

  public SfZongheZhiban selectByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return (SfZongheZhiban) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.selectByPrimaryKey", id);
  }

  public int updateByPrimaryKey(SfZongheZhiban record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.selectMainDataLst", elementConditionDto);
  }

  
  public SfZongheZhiban getCurrentZhiban(ElementConditionDto dto) {
    return (SfZongheZhiban) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.selectCurrentZhiban", dto);
  }

}
