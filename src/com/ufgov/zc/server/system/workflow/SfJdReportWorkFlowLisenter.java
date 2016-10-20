package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

public class SfJdReportWorkFlowLisenter extends TaskAdapter {

	  public void afterExecution(WorkflowContext context)  throws WorkflowException
	  {
		    Long processId = context.getInstanceId();
		    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
		    SfEntrust evalution = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByProcessinstid",
		      new BigDecimal(processId.longValue()));
		    if(SfElementConstants.VAL_Y.equals(evalution.getIsAccept()) && evalution.getJdFzr() == null){
		    	throw new WorkflowException("请指定鉴定负责人");
		    } 
	  }
}
