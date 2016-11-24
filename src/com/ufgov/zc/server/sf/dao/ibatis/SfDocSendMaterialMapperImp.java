package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper;

public class SfDocSendMaterialMapperImp extends SqlMapClientDaoSupport implements SfDocSendMaterialMapper {

  
  public int deleteByPrimaryKey(BigDecimal sendId) {
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper.deleteByPrimaryKey", sendId);
  }

  
  public int insert(SfDocSendMaterial record) {
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfDocSendMaterial record) {
    return 0;
  }

  
  public List selectByPrimaryKey(BigDecimal sendId) {
    return   getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper.selectByPrimaryKey", sendId);
    
  }

  
  public int updateByPrimaryKeySelective(SfDocSendMaterial record) {
    return 0;
  }

  
  public int updateByPrimaryKey(SfDocSendMaterial record) {
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfDocSendMaterialMapper.updateByPrimaryKey", record);
  }

}
