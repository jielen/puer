package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfReceiptMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfReceiptMapperImp extends SqlMapClientDaoSupport implements SfReceiptMapper {

  public int deleteByPrimaryKey(BigDecimal receiptId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfReceiptMapper.deleteByPrimaryKey", receiptId);
  }

  public int insert(SfReceipt record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfReceiptMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfReceipt record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfReceipt selectByPrimaryKey(BigDecimal receiptId) {
    // TCJLODO Auto-generated method stub
    return (SfReceipt) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfReceiptMapper.selectByPrimaryKey", receiptId);
  }

  public int updateByPrimaryKeySelective(SfReceipt record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfReceipt record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfReceiptMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfReceiptMapper.selectMainDataLst", elementConditionDto);
  }

}
