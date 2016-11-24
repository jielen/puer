package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfSjInMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfSjInMapperImp extends SqlMapClientDaoSupport implements SfSjInMapper {

  
  public int deleteByPrimaryKey(BigDecimal inId) {
    return this.getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfSjInMapper.deleteByPrimaryKey", inId);
  }

  
  public int insert(SfSjIn record) {
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfSjInMapper.insert",record);
    return 1;
  }

  
  public int insertSelective(SfSjIn record) {
    return 0;
  }

  
  public SfSjIn selectByPrimaryKey(BigDecimal inId) {
    return (SfSjIn) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfSjInMapper.selectByPrimaryKey", inId);
  }

  
  public int updateByPrimaryKeySelective(SfSjIn record) {
    return 0;
  }

  
  public int updateByPrimaryKey(SfSjIn record) {
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfSjInMapper.updateByPrimaryKey", record);
  }

  public List getMainLst(ElementConditionDto elementConditionDto){
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfSjInMapper.getMainLst", elementConditionDto);
    return list;
  }
}
