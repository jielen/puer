package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper;

public class SfMaterialsTransferDetailMapperImp extends SqlMapClientDaoSupport implements SfMaterialsTransferDetailMapper {

  
  public int deleteByPrimaryKey(BigDecimal transferId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper.deleteByPrimaryKey", transferId);
  }
  
  public int insert(SfMaterialsTransferDetail record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper.insert", record);
    return 1;
  }
  
  public List selectByPrimaryKey(BigDecimal transferId) {
    // TCJLODO Auto-generated method stub
    return  getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfMaterialsTransferDetailMapper.selectByPrimaryKey", transferId);
  }

}
