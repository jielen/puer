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
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

public class SfJdDocAuditFinishWorkFlowLisenter extends SfJdDocAuditWorkFlowBasicLisenter {

  public void afterExecution(WorkflowContext context) throws WorkflowException {
    super.afterExecution(context);
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfJdDocAudit evalution = (SfJdDocAudit) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));

    SfEntrust entrust = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByPrimaryKey",evalution.getEntrust().getEntrustId()); 
    
    //这个改到鉴定文书已经移交到综合室值班人时再改变这个状态  -- 20170615 cjl
  /* entrust.setStatus(SfEntrust.STATUS_COMPLETE); 
    List l=new ArrayList();
    l.add(entrust);
    zcEbBaseServiceDao.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateByPrimaryKey", l);*/
    
    //发送短信给委托方
//    _sendMsgToWtf(entrust);  这个改到鉴定文书已经移交到综合室值班人时再发送  -- 20170615 cjl
    //发送短信给起草人   
    //不发短信了--20171206 cjl
    //_sendMsgToQcr(evalution,entrust,zcEbBaseServiceDao); 
  }

  /**
   * 发送短信给起草人,准备打印文书和签字了
   * @param evalution
   */
  private void _sendMsgToQcr(SfJdDocAudit evalution,SfEntrust entrust,IZcEbBaseServiceDao zcEbBaseServiceDao) {

    //获取用户名称和和电话号码
    ElementConditionDto dto=new ElementConditionDto();
    dto.getPmAdjustCodeList().clear();
    List users=new ArrayList();
    users.add(evalution.getInputor());
    if(users.size()==0){
      users.add("kongyonghu");//这里加上这个空用户，是为了防止这个列表为空时，后台会忽略这个条件，将全部用户搜出来
    }
    dto.getPmAdjustCodeList().addAll(users);
    dto.setDattr1("havePhone");
    List userLst=zcEbBaseServiceDao.queryDataForList("AsEmp.getAsEmpLst", dto);
     
    if(userLst==null || userLst.size()==0)return;
    
    ZcSUtil su=new ZcSUtil();
    StringBuffer sb=new StringBuffer(); 
    SfJdjg jg=su.getJdjgInfo(entrust.getCoCode());
    String jgName="鉴定中心";
    if(jg!=null){
      jgName=jg.getName();
      sb.append("【").append(jgName).append("】:");
    }
    sb.append("您好,");
    sb.append(entrust.getCode()).append("【").append(entrust.getName()).append("】");
    sb.append(",鉴定文书审批单已经完成审批，请打印鉴定文书，找相关人员签字，准备移交给委托方。");
    su.sendMsgToAsEmp(userLst,sb.toString());
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
