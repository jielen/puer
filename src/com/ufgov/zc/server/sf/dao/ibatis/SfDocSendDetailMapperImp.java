package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper;

public class SfDocSendDetailMapperImp extends SqlMapClientDaoSupport implements SfDocSendDetailMapper {

  
  public int deleteByPrimaryKey(BigDecimal sendId) {
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper.deleteByPrimaryKey", sendId);
  }

  
  public int insert(SfDocSendDetail record) {
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper.insert", record);
    return 1;
  }

  
  public int insertSelective(SfDocSendDetail record) {
    return 0;
  }

  
  public List selectByPrimaryKey(BigDecimal sendId) {
    return   getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper.selectByPrimaryKey", sendId);
    
  }

  
  public int updateByPrimaryKeySelective(SfDocSendDetail record) {
    return 0;
  }

  
  public int updateByPrimaryKey(SfDocSendDetail record) {
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfDocSendDetailMapper.updateByPrimaryKey", record);
  }

}
