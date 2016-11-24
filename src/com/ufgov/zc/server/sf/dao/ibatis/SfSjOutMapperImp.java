package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfSjOutMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfSjOutMapperImp extends SqlMapClientDaoSupport implements SfSjOutMapper {

  public int deleteByPrimaryKey(BigDecimal jdDocAuditId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfSjOutMapper.deleteByPrimaryKey", jdDocAuditId);

  }

  public int insert(SfSjOut record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfSjOutMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfSjOut record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfSjOut selectByPrimaryKey(BigDecimal jdDocAuditId) {
    // TCJLODO Auto-generated method stub
    return (SfSjOut) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfSjOutMapper.selectByPrimaryKey", jdDocAuditId);
  }

  public int updateByPrimaryKeySelective(SfSjOut record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfSjOut record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfSjOutMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfSjOutMapper.selectMainDataLst", elementConditionDto);
  }

}
