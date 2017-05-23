package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.kingdrive.workflow.model.TableData;
import com.kingdrive.workflow.model.TaskUser;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

public class SfJdDocAuditWorkFlowBasicLisenter extends TaskAdapter {
  //发送审批信息给待办人 在service层进行了消息发送，这里不再发送，所以将方法名加了个1
  public void afterExecution1(WorkflowContext context) throws WorkflowException {
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
    String jgName=getJdjgName(context);
    if(jgName!=null){
      sb.append("【").append(jgName).append("】:");
    } 
    TableData bill=context.getEntityData();
    sb.append("您有鉴定文书审批单需要审批,委托编号:").append(bill.getFieldValue("ENTRUST_CODE"));
    if(bill.getFieldValue("REPORT_CODE")!=null){
      sb.append(",发文编号:").append(bill.getFieldValue("REPORT_CODE"));
    }
    sb.append(",请登录鉴定管理系统进行审批");
    return sb.toString();
  }

  private String getJdjgName(WorkflowContext context) {
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfJdDocAudit doc = (SfJdDocAudit) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue())); 
    if(doc==null)return null;
    SfEntrust entrust=(SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByPrimaryKey",doc.getEntrustId());
    if(entrust==null)return null;
    ElementConditionDto dto=new ElementConditionDto();
    dto.setCoCode(entrust.getCoCode());
    List l=zcEbBaseServiceDao.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto);
    if(l!=null && l.size()>0){
      SfJdjg jgJdjg=(SfJdjg) l.get(0);
      return jgJdjg.getName();
    }
    return null;
  }

}
