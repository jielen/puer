/**

 * IZcEbBaseServiceDelegate.java

 * com.ufgov.gk.common.zc.publish

 * Administrator

 * 2010-4-30

 */

package com.ufgov.zc.common.zc.publish;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

/**

 * @author Administrator

 *

 */

public interface IZcEbBaseServiceDelegate extends Publishable {

  public List getForeignEntitySelectedData(String sqlMapSelectId, ElementConditionDto dto, RequestMeta meta);

  public List queryDataForList(String sqlMapSelectId, Map parameter, RequestMeta meta);

  public String getSequenceNextVal(String sequenceName, RequestMeta meta);  

  public String getNextVal(String sequenceName, RequestMeta meta);

  public List queryDataForList(String sqlMapSelectId, Object param, RequestMeta meta);

  public Object queryObject(String sqlMapSelectId, Object param, RequestMeta meta);

  public void updateObjectListFN(String sqlMapUpdateId, List list, RequestMeta meta);  

  public void insertWithDeleteFN(String delSqlId,Object delValue,String insertSqlId,Object insertValue,RequestMeta meta);
  
  public void insertFN(String insertSqlId,Object insertValue,RequestMeta meta);

  public void insertObjectListFN(String insertSqlId, List list,RequestMeta meta);
  
  public void deleteFN(String sqlMapDeleteId, Object parameter,RequestMeta meta);

  /*
   * 工作流相关
   */
  public ZcBaseBill auditFN(ZcBaseBill bill, RequestMeta meta);

  public ZcBaseBill callbackFN(ZcBaseBill bill, RequestMeta meta);

  public ZcBaseBill newCommitFN(ZcBaseBill bill, RequestMeta meta);

  public ZcBaseBill unAuditFN(ZcBaseBill bill, RequestMeta meta);

  public ZcBaseBill untreadFN(ZcBaseBill bill, RequestMeta meta);

  /*
   * 工作流相关
   */
  public void auditFN(List billList, RequestMeta meta);

  public void callbackFN(List billList, RequestMeta meta);

  public void newCommitFN(List billList, RequestMeta meta);

  public void unAuditFN(List billList, RequestMeta meta);

  public void untreadFN(List billList, RequestMeta meta);
  
  public String getAutoNumNo(ZcBaseBill bill,String compoName,String fieldName);
  Date getSysDate(RequestMeta requestMeta);
}
