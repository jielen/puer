package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfAppendMaterialMapperImp  extends SqlMapClientDaoSupport implements SfAppendMaterialMapper {

  public int deleteByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.deleteByPrimaryKey", outInfoId);
  }

  public int insert(SfAppendMaterial record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.insert", record);
    return 1;
  } 

  public SfAppendMaterial selectByPrimaryKey(BigDecimal outInfoId) {
    // TCJLODO Auto-generated method stub
    return (SfAppendMaterial) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.selectByPrimaryKey", outInfoId);
  }
 

  public List getMainDataLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.selectMainDataLst", elementConditionDto);
  }

  public int updateByPrimaryKey(SfAppendMaterial record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.updateByPrimaryKey", record);
  }

}