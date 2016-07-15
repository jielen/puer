package com.ufgov.zc.server.zc.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcQb;
import com.ufgov.zc.common.zc.model.ZcQbBi;
import com.ufgov.zc.common.zc.model.ZcQbItem;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcQbDao;

public class ZcQbDao extends SqlMapClientDaoSupport  implements IZcQbDao {
  public ZcQbDao() {

    super();

  }

 
  public List getQbLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub

    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("ZC_QB.getQbLst", elementConditionDto);

    return list;
  }


 
  public ZcQb selectByPrimaryKey(String qbCode) {
    // TCJLODO Auto-generated method stub
    return  (ZcQb) getSqlMapClientTemplate().queryForObject("ZC_QB.selectByPrimaryKey", qbCode);
  }


 
  public List getQbBiLst(String qbCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("ZC_QB.getQbBiLstByQbcode", qbCode);
  }


 
  public List getItemLst(String qbCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("ZC_QB.getQbItemLstByQbcode", qbCode);
  }


  public List selectBiByQbCode(String qbCode) {
    // TCJLODO Auto-generated method stub
    return getQbBiLst(qbCode);
  }


  
  public void insert(ZcQb qb) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("ZC_QB.insert", qb);
  }


  public String getMaxVouId(String vouId) {
    // TCJLODO Auto-generated method stub
    return (String) getSqlMapClientTemplate().queryForObject("ZC_QB.getMaxVouId", vouId);
  }
  
  public void insertBiLst(final List biList) {
    // TCJLODO Auto-generated method stub
    if (biList == null || biList.size()==0)

      return;


    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

        executor.startBatch();

        for (int i = 0; i < biList.size(); i++) {

          ZcQbBi p = (ZcQbBi)biList.get(i);

          executor.insert("ZC_QB.insertQbBi", p);

        }

        executor.executeBatch();

        return null;

      }

    });

  }


  
  public void insertItemLst(final List itemList) {
    // TCJLODO Auto-generated method stub
    if (itemList == null || itemList.size()==0)
      return;

    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
        executor.startBatch();
        for (int i = 0; i < itemList.size(); i++) {
          ZcQbItem p = (ZcQbItem)itemList.get(i);
          executor.insert("ZC_QB.insertQbItem", p);
        }
        executor.executeBatch();
        return null;
      }

    });   
  }


  
  public void update(ZcQb qb) {
    // TCJLODO Auto-generated method stub
    delete(qb);
    insert(qb);
  }


  
  public void deleteBiByQbCode(String qbCode) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QB.deleteBiByQbCode", qbCode);
  }


  
  public void deleteItemByQbCode(String qbCode) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QB.deleteItemByQbCode", qbCode);    
  }


  
  public void delete(ZcQb qb) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QB.delete", qb.getQbCode());        
  }

}
