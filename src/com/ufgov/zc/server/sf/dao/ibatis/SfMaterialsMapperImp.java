/**
 * 
 */
package com.ufgov.zc.server.sf.dao.ibatis;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;

/**
 * @author Administrator
 *
 */
public class SfMaterialsMapperImp extends SqlMapClientDaoSupport implements SfMaterialsMapper {

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#deleteByPrimaryKey(java.math.BigDecimal)
   */
  
  public int deleteByPrimaryKey(BigDecimal materialId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.deleteByPrimaryKey", materialId);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#insert(com.ufgov.zc.common.sf.model.SfMaterials)
   */
  
  public int insert(SfMaterials record) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.insert", record);
    return 1;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#insertSelective(com.ufgov.zc.common.sf.model.SfMaterials)
   */
  
  public int insertSelective(SfMaterials record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#selectByPrimaryKey(java.math.BigDecimal)
   */
  
  public SfMaterials selectByPrimaryKey(BigDecimal materialId) {
    // TCJLODO Auto-generated method stub
    return (SfMaterials) getSqlMapClientTemplate().queryForObject("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.selectByPrimaryKey", materialId);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#updateByPrimaryKeySelective(com.ufgov.zc.common.sf.model.SfMaterials)
   */
  
  public int updateByPrimaryKeySelective(SfMaterials record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.sf.dao.SfMaterialsMapper#updateByPrimaryKey(com.ufgov.zc.common.sf.model.SfMaterials)
   */
  
  public int updateByPrimaryKey(SfMaterials record) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().update("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.updateByPrimaryKey", record);    
  }

   
  public int deleteByEntrustId(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().delete("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.deleteByEntrustId", entrustId);
  }

  
  public List selectByEntrustId(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("com.ufgov.zc.server.sf.dao.SfMaterialsMapper.selectByEntrustId", entrustId);
  }

}
