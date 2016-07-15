package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdTargetMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfJdTargetMapperImp extends SqlMapClientDaoSupport implements SfJdTargetMapper {

  
  
  public int deleteByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.deleteByPrimaryKey", id);
  }

  
  public int insert(SfJdTarget record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.insert",record);
    return 1;
  }

  
  public int insertSelective(SfJdTarget record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfJdTarget selectByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return (SfJdTarget) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.selectByPrimaryKey", id);
  }

  
  public int updateByPrimaryKeySelective(SfJdTarget record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.updateByPrimaryKeySelective", record);
    return 1;
  }

  
  public int updateByPrimaryKey(SfJdTarget record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.updateByPrimaryKey", record);
    return 1;
  }

  
  public List getJdTargetLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.getJdTargetLst", elementConditionDto);

    return list;
  }


  
  public boolean isUsing(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    Integer sum=(Integer) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdTargetMapper.isUsing", id);
    if(sum!=null && sum.intValue()>0){
      return true;
    }
    return false;
  }

}
