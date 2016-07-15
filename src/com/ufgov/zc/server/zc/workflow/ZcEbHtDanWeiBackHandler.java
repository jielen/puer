/**
 * 
 */
package com.ufgov.zc.server.zc.workflow;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;

/**
 * @author Administrator
 *
 */
public class ZcEbHtDanWeiBackHandler extends TaskAdapter {
 
  public void beforeExecution(WorkflowContext context)
      throws WorkflowException {
    System.out.println("you beforeExecution "+context.getInstanceId());
  }

  public void afterExecution(WorkflowContext context)
      throws WorkflowException {
    System.out.println("you afterExecution "+context.getInstanceId());
  }

  public void beforeUntread(WorkflowContext context) throws WorkflowException {
    System.out.println("you beforeUntread "+context.getInstanceId());
  }

  public void afterUntread(WorkflowContext context) throws WorkflowException {
    System.out.println("you afterUntread "+context.getInstanceId());
  }
  
   public void beforeRework(WorkflowContext meta) throws WorkflowException {
     System.out.println("you beforeRework "+meta.getInstanceId());
   }

   public void afterRework(WorkflowContext meta) throws WorkflowException {
     System.out.println("you afterRework "+meta.getInstanceId());
  } 
}
