package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEntrustManageMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfEntrustManageMapperImp extends SqlMapClientDaoSupport implements SfEntrustManageMapper {

  public int deleteByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfEntrustManageMapper.deleteByPrimaryKey", id);
  }

  public int insert(SfEntrustManage record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfEntrustManageMapper.insert", record);
    return 1;
  }

  public SfEntrustManage selectByPrimaryKey(BigDecimal id) {
    // TCJLODO Auto-generated method stub
    return (SfEntrustManage) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEntrustManageMapper.selectByPrimaryKey", id);
  }

  public int updateByPrimaryKey(SfEntrustManage record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEntrustManageMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfEntrustManageMapper.selectMainDataLst", elementConditionDto);
  }

}
