package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdPersonMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfJdPersonMapperImp extends SqlMapClientDaoSupport implements SfJdPersonMapper {

  
  public int deleteByPrimaryKey(BigDecimal jdPersonId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.deleteByPrimaryKey", jdPersonId);
  }


  
  public int insert(SfJdPerson record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfJdPerson record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfJdPerson selectByPrimaryKey(BigDecimal jdPersonId) {
    // TCJLODO Auto-generated method stub
    return (SfJdPerson) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.selectByPrimaryKey", jdPersonId);
  }

  
  public int updateByPrimaryKeySelective(SfJdPerson record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfJdPerson record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.updateByPrimaryKey", record);
  }

  
  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub 
	  elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.selectMainDataLst",elementConditionDto);
  }

}
