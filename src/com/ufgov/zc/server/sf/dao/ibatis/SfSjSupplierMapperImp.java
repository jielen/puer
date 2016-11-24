package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfSjSupplierMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

public class SfSjSupplierMapperImp extends SqlMapClientDaoSupport implements SfSjSupplierMapper {

  public int deleteByPrimaryKey(BigDecimal supplierId) {
    return this.getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.deleteByPrimaryKey", supplierId);

  }

  public int insert(SfSjSupplier record) {
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.insert", record);
    return 1;
  }

  public int insertSelective(SfSjSupplier record) {
    return 0;
  }

  public SfSjSupplier selectByPrimaryKey(BigDecimal supplierId) {
    return (SfSjSupplier) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectByPrimaryKey", supplierId);
  }

  public int updateByPrimaryKeySelective(SfSjSupplier record) {
    return 0;
  }

  public int updateByPrimaryKey(SfSjSupplier record) {

    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.updateByPrimaryKey", record);
  }

  public List getMainLst(ElementConditionDto elementConditionDto) {
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectMainDataLst", elementConditionDto);

    return list;
  }

}
