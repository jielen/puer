/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.DataExchangeRedo;
import com.ufgov.zc.common.zc.model.ZcEbProj;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IDataExchangeDao;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;

/**
 * 立项分包审批监听类，采购中心审批后，送到内网采购单位审批
 * @author Administrator
 *
 */
public class ZcEbProjectWorkFlowLisenter extends TaskAdapter {


  public void afterExecution(WorkflowContext context) throws WorkflowException {
  //招标文件制作完成后，导入到内网给采购单位审核，需要同时导入工作流信息
    Long processId=context.getInstanceId();
    
    IZcEbBaseServiceDao zcEbBaseServiceDao=(IZcEbBaseServiceDao)SpringContext.getBean("zcEbBaseServiceDao");
    
    ElementConditionDto dto =new ElementConditionDto();
    
    List idsLst=new ArrayList();
    
    idsLst.add(""+processId.longValue());
    
    dto.setPmAdjustCodeList(idsLst);
         
    ZcEbProj proj=(ZcEbProj)zcEbBaseServiceDao.queryObject("ZcEbProj.getZcEbProjByProcessinstid", dto);

    if (proj != null) {

      DataExchangeRedo redo = new DataExchangeRedo();

      redo.setDataTypeID("ZC_EB_PROJECT");

      redo.setDataTypeName("招标文件");

      dto = new ElementConditionDto();

     
      redo.setRecordID(proj.getProjCode());

      redo.setRecordName(proj.getProjName());

      redo.setMasterTableName("ZC_EB_PROJ");

      redo.setIsExported("0");

      redo.setGenerateDate(new Date());

      IDataExchangeDao dataExchangeDao=(IDataExchangeDao)SpringContext.getBean("dataExchangeDao");

      dataExchangeDao.deleteByRecordIDAndDataTypeID(redo);

      dataExchangeDao.saveRedo(redo);

    }

  }
}
