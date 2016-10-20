/**
 * 
 */
package com.ufgov.zc.server.sf.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ufgov.zc.common.sf.model.ZcFaCardSub;
import com.ufgov.zc.common.sf.model.ZcFaCardSubExample;
import com.ufgov.zc.server.sf.dao.IZcFaCardSubDao;

/**
 * @author Administrator
 *
 */
public class ZcFaCardSubDao extends SqlMapClientDaoSupport implements IZcFaCardSubDao {

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#countByExample(com.ufgov.zc.common.zc.model.ZcFaCardSubExample)
   */
  
  public int countByExample(ZcFaCardSubExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#deleteByExample(com.ufgov.zc.common.zc.model.ZcFaCardSubExample)
   */
  
  public int deleteByExample(ZcFaCardSubExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#insert(com.ufgov.zc.common.zc.model.ZcFaCardSub)
   */
  
  public int insert(ZcFaCardSub record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#insertSelective(com.ufgov.zc.common.zc.model.ZcFaCardSub)
   */
  
  public int insertSelective(ZcFaCardSub record) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#selectByExample(com.ufgov.zc.common.zc.model.ZcFaCardSubExample)
   */
  
  public List selectByExample(ZcFaCardSubExample example) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#updateByExampleSelective(com.ufgov.zc.common.zc.model.ZcFaCardSub, com.ufgov.zc.common.zc.model.ZcFaCardSubExample)
   */
  
  public int updateByExampleSelective(ZcFaCardSub record, ZcFaCardSubExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.server.zc.dao.IZcFaCardSubDao#updateByExample(com.ufgov.zc.common.zc.model.ZcFaCardSub, com.ufgov.zc.common.zc.model.ZcFaCardSubExample)
   */
  
  public int updateByExample(ZcFaCardSub record, ZcFaCardSubExample example) {
    // TCJLODO Auto-generated method stub
    return 0;
  }

   
  public void insertList(final List subList) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
      public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
        executor.startBatch();
        for (int i = 0; i < subList.size(); i++) {
          ZcFaCardSub pack = (ZcFaCardSub) subList.get(i);
          executor.insert("ZcFaCardSub.insert", pack);
        }
        executor.executeBatch();
        return null;
      }
    });
  }

   
  public void deleteByCardId(String cardId) {
    // TCJLODO Auto-generated method stub
    this.getSqlMapClientTemplate().delete("ZcFaCardSub.deleteByCardId", cardId);    
  }

}
