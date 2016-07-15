/**
 * 
 */
package com.ufgov.zc.server.zc.workflow;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;

/**
 * 供应商合同送审监听类
 * @author Administrator
 *
 */
public class ZcEbHtSupplierSendHandler extends TaskAdapter {
  
  public void afterExecution(WorkflowContext context)
    throws WorkflowException {
//    context
    System.out.println("you send a ht "+context.getInstanceId());
}
}
