package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfMaterialsTransfer;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfMaterialsTransferMapperImp extends SqlMapClientDaoSupport implements SfMaterialsTransferMapper {

  public int deleteByPrimaryKey(BigDecimal transferId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.deleteByPrimaryKey", transferId);
  }

  public int insert(SfMaterialsTransfer record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfMaterialsTransfer record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public SfMaterialsTransfer selectByPrimaryKey(BigDecimal transferId) {
    // TCJLODO Auto-generated method stub
    // TCJLODO Auto-generated method stub
    return (SfMaterialsTransfer) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.selectByPrimaryKey",
      transferId);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.selectMainDataLst", elementConditionDto);
  }

  public int updateByPrimaryKeySelective(SfMaterialsTransfer record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  public int updateByPrimaryKey(SfMaterialsTransfer record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfMaterialsTransferMapper.updateByPrimaryKey", record);
  }

}
