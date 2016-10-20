package com.ufgov.zc.server.sf.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.sf.model.ZcFaUsing;
import com.ufgov.zc.common.sf.model.ZcFaUsingExample;
import com.ufgov.zc.server.sf.dao.IZcFaUsingDao;

public class ZcFaUsingDao extends SqlMapClientDaoSupport implements IZcFaUsingDao {

  
  public int countByExample(ZcFaUsingExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int deleteByExample(ZcFaUsingExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int insert(ZcFaUsing record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int insertSelective(ZcFaUsing record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public List selectByExample(ZcFaUsingExample example) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  
  public int updateByExampleSelective(ZcFaUsing record, ZcFaUsingExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByExample(ZcFaUsing record, ZcFaUsingExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

   
  public void deleteByCardId(String cardId) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().delete("ZcFaUsing.deleteByCardId", cardId);
  }

  
  public void insertList(final List usingList) {
    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
        executor.startBatch();
        for (int i = 0; i < usingList.size(); i++) {
          ZcFaUsing pack = (ZcFaUsing) usingList.get(i);
          executor.insert("ZcFaUsing.insert", pack);
        }
        executor.executeBatch();
        return null;
      }
    });
  }

}
