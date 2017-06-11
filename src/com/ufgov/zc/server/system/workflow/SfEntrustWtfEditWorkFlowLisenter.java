/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

/**
 * 委托方修改鉴定中心退回的监听器
 * 主要是修改字段IS_TH_WTF为N或空
 * @author Administrator
 *
 */
public class SfEntrustWtfEditWorkFlowLisenter extends SfEntrustBasicWorkFlowLisenter {
  public void afterExecution(WorkflowContext context) throws WorkflowException
  {
    super.afterExecution(context);
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEntrust entrust = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));
    entrust.setIsThWtf(""); 
    SfEntrustMapper mp=(SfEntrustMapper)SpringContext.getBean("entrustMapper");
    mp.updateByPrimaryKey(entrust);
  }
}
