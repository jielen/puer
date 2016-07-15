package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEntrustorMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfEntrustorMapperImp extends SqlMapClientDaoSupport implements SfEntrustorMapper {

  
  public int deleteByPrimaryKey(BigDecimal entrustorId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.deleteByPrimaryKey", entrustorId);
  }

  
  public int insert(SfEntrustor record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.insert",record);
    return 1;
  }

  
  public int insertSelective(SfEntrustor record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfEntrustor selectByPrimaryKey(BigDecimal entrustorId) {
    // TCJLODO Auto-generated method stub
    return (SfEntrustor) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.selectByPrimaryKey", entrustorId);
  }

  
  public int updateByPrimaryKeySelective(SfEntrustor record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.updateByPrimaryKeySelective", record);
    return 1;
  }

  
  public int updateByPrimaryKey(SfEntrustor record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.updateByPrimaryKey", record);
    return 1;
  }

  
  public List getEntrustorLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getEntrustorLst", elementConditionDto);

    return list;
  }


  
  public boolean isUsing(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    Integer sum=(Integer) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.isUsing", id);
    if(sum!=null && sum.intValue()>0){
      return true;
    }
    return false;
  }
   
  public SfEntrustor selectByName(String name) {
    // TCJLODO Auto-generated method stub
    return (SfEntrustor) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.selectByName", name);
  }

}
