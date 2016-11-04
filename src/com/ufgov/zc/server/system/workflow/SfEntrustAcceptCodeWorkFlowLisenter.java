/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

/**
 * 综合组在受理检材后,送审时,生成受理编号,用于打印鉴定确认书,让客户拿着鉴定确认书到鉴定人那里签字
 * @author Administrator
 */
public class SfEntrustAcceptCodeWorkFlowLisenter extends SfEntrustBasicWorkFlowLisenter {

  public void afterExecution(WorkflowContext context) throws WorkflowException {
    super.afterExecution(context);
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEntrust bill = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByProcessinstid", new BigDecimal(processId.longValue()));
    if (bill.getAcceptCode() != null && bill.getAcceptCode().trim().length()>0) return;

    IZcEbBaseService baseService = (IZcEbBaseService) SpringContext.getBean("zcEbBaseService");
    String acceptCode = baseService.getAutoNumNo(bill, "SF_ENTRUST", "CODE");
    bill.setAcceptCode(acceptCode);

    SfEntrustMapper sfEntrustMapper = (SfEntrustMapper) SpringContext.getBean("entrustMapper");
    sfEntrustMapper.updateByPrimaryKey(bill);
  }
}
