package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.kingdrive.workflow.utils.WFConst;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.server.sf.service.ISfEntrustManageService;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

public class SfEntrustBasicWorkFlowLisenter extends TaskAdapter {

  /**
   * 更新管理日志
   */
  public void afterExecution(WorkflowContext context) throws WorkflowException
  {
    ISfEntrustService entrustService=(ISfEntrustService) SpringContext.getBean("sfEntrustService"); 
    ISfEntrustManageService manageService=(ISfEntrustManageService)SpringContext.getBean("sfEntrustManageService");

    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEntrust entrust = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByProcessinstid",
      new BigDecimal(processId.longValue()));
    entrust=entrustService.selectByPrimaryKey(entrust.getEntrustId(), null);

    SfEntrustManage m=new SfEntrustManage();
    m.setEntrust(entrust);
    m.setEntrustCode(entrust.getCode());
    m.setEntrustId(entrust.getEntrustId()); 
    m.setManageTypeTxt(getManageTypeTxt(entrust));
    m.setInputDate(new Date());
    m.setInputor(context.getExecutor());
    String nd=(String) context.getAllVariables().get(WFConst.ND);
    m.setNd((new Integer(nd)));
    m.setCoCode(entrust.getCoCode());
    m.setManageTime(new Date()); 
    manageService.insert(m, null);
  }

  private String getManageTypeTxt(SfEntrust entrust) {
    return ZcSUtil.getAsValName(SfEntrust.SF_VS_ENTRUST_STATUS, entrust.getStatus()); 
  }

  public void afterUntread(WorkflowContext context)   throws WorkflowException
  { 
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEntrust bill=new SfEntrust();
    bill.setStatus(SfEntrust.STATUS_UNTREAT);
    bill.setProcessInstId(context.getInstanceId());
    List l=new ArrayList();
    l.add(bill);
    zcEbBaseServiceDao.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.updateStatusByProcessId", l);
    
  }
}

