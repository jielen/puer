/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

/**
 * 用于综合岗接收时的处理，1）将委托状态更新为鉴定完成状态；2）给委托方发短信，来领取委托书
 * @author Administrator
 *
 */
public class SfDocSendWorkFlowLisenter extends TaskAdapter {
 
  
  public void afterExecution(WorkflowContext context)  throws WorkflowException
  {
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfDocSend evalution = (SfDocSend) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfDocSendMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));
 
    SfEntrust entrust = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByPrimaryKey",evalution.getEntrust().getEntrustId()); 
    entrust.setStatus(SfEntrust.STATUS_COMPLETE); 
    List l=new ArrayList();
    l.add(entrust);
    zcEbBaseServiceDao.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateByPrimaryKey", l);
    //发送短信给委托方
   _sendMsgToWtf(entrust); 
  }
  /**
   * 发送短信给委托方,提示已经完成鉴定了。 来领取鉴定文书
   * @param entrust
   */
  public void _sendMsgToWtf(SfEntrust entrust) {
    ZcSUtil su=new ZcSUtil();
    StringBuffer sb=new StringBuffer(); 
    SfJdjg jg=su.getJdjgInfo(entrust.getCoCode());
    String jgName="鉴定中心";
    if(jg!=null){
      jgName=jg.getName();
      sb.append("【").append(jgName).append("】:");
    }
    if(entrust.getEntrustor()!=null && entrust.getEntrustor().getName()!=null){
      sb.append(entrust.getEntrustor().getName()).append(",");
    }
    sb.append("您好,");
    sb.append(entrust.getCode()).append("【").append(entrust.getName()).append("】");
    sb.append(",已经完成鉴定，请携带《鉴定事项确认书》,在工作日时间到").append(jgName).append("领取鉴定意见书和相关材检材检样，谢谢。");
    su.sendMsgToSjr(entrust, sb.toString());
  }
}

