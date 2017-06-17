/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.math.BigDecimal;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

/**
 * 综合组在受理检材后,送审时,生成受理编号,用于打印鉴定确认书,让客户拿着鉴定确认书到鉴定人那里签字
 * @author Administrator
 */
public class SfEntrustAcceptCodeWorkFlowLisenter extends SfEntrustBasicWorkFlowLisenter {

  public void afterExecution(WorkflowContext context) throws WorkflowException {
    super.afterExecution(context);
    Long processId = context.getInstanceId();
    IZcEbBaseServiceDao zcEbBaseServiceDao = (IZcEbBaseServiceDao) SpringContext.getBean("zcEbBaseServiceDao");
    SfEntrust bill = (SfEntrust) zcEbBaseServiceDao.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByProcessinstid", new BigDecimal(processId.longValue()));
    if (bill.getAcceptCode() == null || bill.getAcceptCode().trim().length()==0){     
  //    IZcEbBaseService baseService = (IZcEbBaseService) SpringContext.getBean("zcEbBaseService");
  //    String acceptCode = baseService.getAutoNumNo(bill, "SF_ENTRUST", "CODE");
      ZcSUtil su=new ZcSUtil();
      bill.setAcceptCode(su.getSfEntrustAcceptCode(bill, context));  
      SfEntrustMapper sfEntrustMapper = (SfEntrustMapper) SpringContext.getBean("entrustMapper");
      sfEntrustMapper.updateByPrimaryKey(bill);
    }
    setMaterialsSlCode(bill);
  }

  private void setMaterialsSlCode(SfEntrust bill) {
    if(bill.getAcceptCode()==null)return;
    
    SfMaterialsMapper materialsMapper = (SfMaterialsMapper) SpringContext.getBean("materialsMapper");
    List lst=materialsMapper.selectByEntrustId(bill.getEntrustId());
    if(lst==null || lst.size()==0)return;
    
    materialsMapper.deleteByEntrustId(bill.getEntrustId());
    
    ZcSUtil zs=new ZcSUtil();
    for(int i=0;i<lst.size();i++){
      SfMaterials m=(SfMaterials) lst.get(i);
      if(m.getSlCode()!=null && m.getSlCode().trim().length()>0)continue;
      String s=zs.genJcSlCode(bill.getAcceptCode(), bill.getNd().intValue());      
      int k=i+1;
      m.setSlCode(s+"-"+zs.setZero(""+k, 2));
      materialsMapper.insert(m);
    }
    
//    St
  } 
}
