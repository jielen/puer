package com.ufgov.zc.server.zc.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;
import com.ufgov.zc.common.zc.model.ZcQx;
import com.ufgov.zc.common.zc.model.ZcQxBi;
import com.ufgov.zc.common.zc.model.ZcQxItem;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcQxDao;

public class ZcQxDao extends SqlMapClientDaoSupport  implements IZcQxDao {
  public ZcQxDao() {

    super();

  }

 
  public List getQxLst(ElementConditionDto elementConditionDto) {
    // TCJLODO Auto-generated method stub

    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));

    List list = getSqlMapClientTemplate().queryForList("ZC_QX.getQxLst", elementConditionDto);

    return list;
  }


 
  public ZcQx selectByPrimaryKey(String qxCode) {
    // TCJLODO Auto-generated method stub
    return  (ZcQx) getSqlMapClientTemplate().queryForObject("ZC_QX.selectByPrimaryKey", qxCode);
  }


 
  public List getQxBiLst(String qxCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("ZC_QX.getQxBiLstByQxcode", qxCode);
  }


 
  public List getItemLst(String qxCode) {
    // TCJLODO Auto-generated method stub
    return getSqlMapClientTemplate().queryForList("ZC_QX.getQxItemLstByQxcode", qxCode);
  }


  public List selectBiByQxCode(String qxCode) {
    // TCJLODO Auto-generated method stub
    return getQxBiLst(qxCode);
  }


  
  public void insert(ZcQx qx) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().insert("ZC_QX.insert", qx);
  }


  public String getMaxVouId(String vouId) {
    // TCJLODO Auto-generated method stub
    return (String) getSqlMapClientTemplate().queryForObject("ZC_QX.getMaxVouId", vouId);
  }
  
  public void insertBiLst(final List biList) {
    // TCJLODO Auto-generated method stub
    if (biList == null || biList.size()==0)

      return;


    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {

        executor.startBatch();

        for (int i = 0; i < biList.size(); i++) {

          ZcQxBi p = (ZcQxBi)biList.get(i);

          executor.insert("ZC_QX.insertQxBi", p);

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
          ZcQxItem p = (ZcQxItem)itemList.get(i);
          executor.insert("ZC_QX.insertQxItem", p);
        }
        executor.executeBatch();
        return null;
      }

    });   
  }


  
  public void update(ZcQx qx) {
    // TCJLODO Auto-generated method stub
    delete(qx);
    insert(qx);
  }


  
  public void deleteBiByQxCode(String qxCode) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QX.deleteBiByQxCode", qxCode);
  }


  
  public void deleteItemByQxCode(String qxCode) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QX.deleteItemByQxCode", qxCode);    
  }


  
  public void delete(ZcQx qx) {
    // TCJLODO Auto-generated method stub
    getSqlMapClientTemplate().delete("ZC_QX.delete", qx.getQxCode());        
  }

}
