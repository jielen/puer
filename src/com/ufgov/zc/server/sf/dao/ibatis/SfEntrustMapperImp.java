/**
 * 
 */
package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.system.util.NumLimUtil;

/**
 * @author Administrator
 *
 */
public class SfEntrustMapperImp extends SqlMapClientDaoSupport implements SfEntrustMapper {

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#deleteByPrimaryKey(java.math.BigDecimal)
   */

  public int deleteByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfEntrustMapper.deleteByPrimaryKey", entrustId);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#insert(com.ufgov.zc.common.sf.model.SfEntrust)
   */

  public int insert(SfEntrust record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfEntrustMapper.insert", record);
    return 1;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#insertSelective(com.ufgov.zc.common.sf.model.SfEntrust)
   */

  public int insertSelective(SfEntrust record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#selectByPrimaryKey(java.math.BigDecimal)
   */

  public SfEntrust selectByPrimaryKey(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return (SfEntrust) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByPrimaryKey", entrustId);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#updateByPrimaryKeySelective(com.ufgov.zc.common.sf.model.SfEntrust)
   */

  public int updateByPrimaryKeySelective(SfEntrust record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfEntrustMapper#updateByPrimaryKey(com.ufgov.zc.common.sf.model.SfEntrust)
   */

  public int updateByPrimaryKey(SfEntrust record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateByPrimaryKey", record);
  }

  public List getEntrustLst(ElementConditionDto dto) {
    // TCJLODO Auto-generated method stub

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));

    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectEntrustLst", dto);
  }

  public int updateStatus(ElementConditionDto dto) {

    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateStatus", dto);
  }
}
