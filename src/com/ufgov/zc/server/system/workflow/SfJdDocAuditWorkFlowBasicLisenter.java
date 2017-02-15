package com.ufgov.zc.server.system.workflow;

import java.util.ArrayList;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.kingdrive.workflow.model.TableData;
import com.kingdrive.workflow.model.TaskUser;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfJdDocAuditWorkFlowBasicLisenter extends TaskAdapter {
  //发送审批信息给待办人
  public void afterExecution(WorkflowContext context) throws WorkflowException {
    if(context.getNextExecutor()!=null){
      List users=new ArrayList();
      for(int i=0;i<context.getNextExecutor().size();i++){
        TaskUser tu=(TaskUser)context.getNextExecutor().get(i);
        users.add(tu.getUserId());
      }
      if(users.size()==0){
        users.add("kongyonghu");//这里加上这个空用户，是为了防止这个列表为空时，后台会忽略这个条件，将全部用户搜出来
      }
      ZcSUtil su=new ZcSUtil();
      su.sendMsgToUser(users, getMsg(context));
    }
  }

  private String getMsg(WorkflowContext context) {
    StringBuffer sb=new StringBuffer();
    TableData bill=context.getEntityData();
    sb.append("您有鉴定文书审批单需要审批,委托编号:").append(bill.getFieldValue("ENTRUST_CODE"));
    if(bill.getFieldValue("REPORT_CODE")!=null){
      sb.append(",发文编号:").append(bill.getFieldValue("REPORT_CODE"));
    }
    sb.append(",请登录鉴定管理系统进行审批");
    return sb.toString();
  }

}
