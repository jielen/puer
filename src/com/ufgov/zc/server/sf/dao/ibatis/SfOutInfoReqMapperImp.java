package com.ufgov.zc.server.sf.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper;

public class SfOutInfoReqMapperImp extends SqlMapClientDaoSupport implements SfOutInfoReqMapper {


  public int deleteByPrimaryKey(String code) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper.deleteByPrimaryKey", code);
  }

  
  public int insert(SfOutInfoReq record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfOutInfoReq record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public SfOutInfoReq selectByPrimaryKey(String code) {
    // TCJLODO Auto-generated method stub
    return (SfOutInfoReq) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper.selectByPrimaryKey", code);
  }

  
  public int updateByPrimaryKeySelective(SfOutInfoReq record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByPrimaryKey(SfOutInfoReq record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfOutInfoReqMapper.selectMainDataLst",elementConditionDto);
  }

}
