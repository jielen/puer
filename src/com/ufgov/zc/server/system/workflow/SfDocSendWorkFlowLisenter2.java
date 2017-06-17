package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

/**
 * 领取文书时，进行判断，必须填写领取人的相关信息
 * @author Administrator
 *
 */
public class SfDocSendWorkFlowLisenter2 extends TaskAdapter {

  public void beforeExecution(WorkflowContext context)    throws WorkflowException
  {
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfDocSend evalution = (SfDocSend) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));
    StringBuffer sb=new StringBuffer();//|| evalution.getRecievorTel()==null || evalution.getRecievorTel().trim().length()==0
    if(evalution.getRecievor()==null || evalution.getRecievor().trim().length()==0 ){
      sb.append("领取人"); 
    }
    if(evalution.getRecievorTel()==null || evalution.getRecievorTel().trim().length()==0 ){
      if(sb.length()>0){
        sb.append("、");
      }
      sb.append("领取人电话");
      
    }
    if(sb.length()>0){
      String str="请填写"+sb.toString()+"信息";
      throw new WorkflowException(str);
    }
  }
}
