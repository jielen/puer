package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

public class SfJdDocAuditWorkFlowLisenter extends TaskAdapter {

  public void afterExecution(WorkflowContext context) throws WorkflowException {
    super.beforeExecution(context);
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfJdDocAudit evalution = (SfJdDocAudit) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));

    SfEntrust entrust = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByPrimaryKey",evalution.getEntrust().getEntrustId()); 
    
    entrust.setStatus(SfEntrust.STATUS_COMPLETE); 
    List l=new ArrayList();
    l.add(entrust);
    zcEbBaseServiceDao.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateByPrimaryKey", l);
    
    //发送短信给委托方
    ZcSUtil su=new ZcSUtil();
    StringBuffer sb=new StringBuffer();
    sb.append("");
    boolean f=false;
    if(entrust.getSjr()!=null){
      sb.append(entrust.getSjr());
      f=true;
    }
    if(entrust.getSjr2()!=null){
      if(f){
        sb.append("、");
      }
      sb.append(entrust.getSjr2());
    }
    sb.append("先生您好，您送交的司法鉴定(委托编号:").append(entrust.getCode()).append(",").append(entrust.getName())
    .append(")已经完成鉴定，请携带《鉴定事项确认书》,在工作日时间到普洱市公安局司法鉴定做取鉴定意见书和相关材检材检样，谢谢。");
    Date d=new Date();
    if(entrust.getSjrTel()!=null && su.isMobile(entrust.getSjrTel())){
      su.sendToBox(""+evalution.getJdDocAuditId().intValue(), "", sb.toString(), entrust.getSjrTel(), d, d);
    }
    if(entrust.getSjr2Tel()!=null && su.isMobile(entrust.getSjr2Tel())){
      su.sendToBox(""+evalution.getJdDocAuditId().intValue(), "", sb.toString(), entrust.getSjr2Tel(), d, d);
    }
    
  }
}
