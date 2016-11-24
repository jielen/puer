package com.ufgov.zc.server.sf.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfMajorMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfMajorMapperImp extends SqlMapClientDaoSupport implements SfMajorMapper {
  
  public SfMajorMapperImp() {
    super();
  }

  
  public int deleteByPrimaryKey(String majorCode) {
    // TCJLODO Auto-generated method stub
    return this.getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfMajorMapper.deleteByPrimaryKey", majorCode);
    
  }

  
  public int insert(SfMajor record) {
    // TCJLODO Auto-generated method stub
     getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfMajorMapper.insert",record);
     return 1;
  }

  
  public int insertSelective(SfMajor record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfMajor selectByPrimaryKey(String majorCode) {
    // TCJLODO Auto-generated method stub
    return (SfMajor) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfMajorMapper.selectByPrimaryKey", majorCode);
  }

  
  public int updateByPrimaryKeySelective(SfMajor record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfMajor record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfMajorMapper.updateByPrimaryKey", record);
  }


   
  public List getMajorLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub

    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfMajorMapper.getMajorLst", elementConditionDto);

    return list;
  }


   
  public boolean isUsing(String majorCode) {
    // TCJLODO Auto-generated method stub
    Integer sum=(Integer) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfMajorMapper.isUsing", majorCode);
    if(sum!=null && sum.intValue()>0){
      return true;
    }
    return false;
  }

}
