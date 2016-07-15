package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfOutInfoMapperImp extends SqlMapClientDaoSupport implements SfOutInfoMapper {

  public int deleteByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoMapper.deleteByPrimaryKey", outInfoId);
  }

  public int insert(SfOutInfo record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfOutInfoMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfOutInfo record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfOutInfo selectByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return (SfOutInfo) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfOutInfoMapper.selectByPrimaryKey", outInfoId);
  }

  public int updateByPrimaryKeySelective(SfOutInfo record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfOutInfoMapper.selectMainDataLst", elementConditionDto);
  }

  public int updateByPrimaryKey(SfOutInfo record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfOutInfoMapper.updateByPrimaryKey", record);
  }

}
