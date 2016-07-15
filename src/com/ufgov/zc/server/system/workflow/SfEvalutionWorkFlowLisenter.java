/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

/**
 * @author Administrator
 *
 */
public class SfEvalutionWorkFlowLisenter extends TaskAdapter {

  public void afterExecution(WorkflowContext context) throws WorkflowException {
    // TCJLODO Auto-generated method stub
    //汽车保险审批过程中，需在内外网间同步合同信息和审批信息
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEvaluation evalution = (SfEvaluation) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEvaluationMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));
    if (evalution != null) {
      ElementConditionDto dto = new ElementConditionDto();
      dto.setStatus("exec");
      dto.setSfId(evalution.getEntrustId());
      SfEntrustMapper entrustDao = (SfEntrustMapper) SpringContext.getBean("entrustMapper");
      entrustDao.updateStatus(dto);
    }
  }
}
