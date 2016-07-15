package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfChargeMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfChargeMapperImp extends SqlMapClientDaoSupport implements SfChargeMapper {

  public int deleteByPrimaryKey(BigDecimal chargeId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfChargeMapper.deleteByPrimaryKey", chargeId);
  }

  public int insert(SfCharge record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfChargeMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfCharge record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfCharge selectByPrimaryKey(BigDecimal chargeId) {
    // TCJLODO Auto-generated method stub
    return (SfCharge) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfChargeMapper.selectByPrimaryKey", chargeId);
  }

  public int updateByPrimaryKeySelective(SfCharge record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfCharge record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfChargeMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfChargeMapper.selectMainDataLst", elementConditionDto);
  }

}
