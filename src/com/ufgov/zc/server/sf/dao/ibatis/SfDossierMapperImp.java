package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfDossierMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfDossierMapperImp extends SqlMapClientDaoSupport implements SfDossierMapper {

  public int deleteByPrimaryKey(BigDecimal dossierId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfDossierMapper.deleteByPrimaryKey", dossierId);
  }

  public int insert(SfDossier record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfDossierMapper.insert", record);
    return 1;
  }

  public SfDossier selectByPrimaryKey(BigDecimal dossierId) {
    // TCJLODO Auto-generated method stub
    return (SfDossier) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfDossierMapper.selectByPrimaryKey", dossierId);
  }

  public int updateByPrimaryKey(SfDossier record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfDossierMapper.updateByPrimaryKey", record);
  }

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfDossierMapper.selectMainDataLst", elementConditionDto);
  }

}
