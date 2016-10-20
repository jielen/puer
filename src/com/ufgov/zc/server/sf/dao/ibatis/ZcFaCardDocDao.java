package com.ufgov.zc.server.sf.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.sf.model.ZcFaCardDoc;
import com.ufgov.zc.common.sf.model.ZcFaCardDocExample;
import com.ufgov.zc.server.sf.dao.IZcFaCardDocDao;

public class ZcFaCardDocDao extends SqlMapClientDaoSupport implements IZcFaCardDocDao {

  
  public int countByExample(ZcFaCardDocExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int deleteByExample(ZcFaCardDocExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int insert(ZcFaCardDoc record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int insertSelective(ZcFaCardDoc record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public List selectByExample(ZcFaCardDocExample example) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  
  public int updateByExampleSelective(ZcFaCardDoc record, ZcFaCardDocExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  
  public int updateByExample(ZcFaCardDoc record, ZcFaCardDocExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }


   
  public void insertList(final List docList) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
        executor.startBatch();
        for (int i = 0; i < docList.size(); i++) {
          ZcFaCardDoc pack = (ZcFaCardDoc) docList.get(i);
          executor.insert("ZcFaCardDoc.insert", pack);
        }
        executor.executeBatch();
        return null;
      }
    });
  }


   
  public void deleteByCardId(String cardId) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().delete("ZcFaCardDoc.deleteByCardId", cardId);
  }

}
