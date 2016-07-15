/**
 * 
 */
package com.ufgov.zc.server.system.workflow;

import java.util.Date;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.exception.WorkflowException;
import com.kingdrive.workflow.listener.TaskAdapter;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.DataExchangeRedo;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.dao.IDataExchangeDao;
import com.ufgov.zc.server.zc.dao.IZcEbBaseServiceDao;
import com.ufgov.zc.server.zc.dao.ibatis.DataExchangeDao;
import com.ufgov.zc.server.zc.service.impl.ZcEbBaseService;

/**
 * 采购计划审批监听类
 * @author Administrator
 *
 */
public class ZcPproMakeXmWorkFlowLisenter extends TaskAdapter {


  public void afterExecution(WorkflowContext context) throws WorkflowException {
    // TCJLODO Auto-generated method stub
    //丹徒使用，采购计划在内网审批完成后，需要将计划导出到外网，采购中心在外网执行任务
    Long processId=context.getInstanceId();
    
    IZcEbBaseServiceDao zcEbBaseServiceDao=(IZcEbBaseServiceDao)SpringContext.getBean("zcEbBaseServiceDao");
    
    ZcPProMake make=(ZcPProMake)zcEbBaseServiceDao.queryObject("ZC_P_PRO_MAKE.getZcPProMakeByProcessId", ""+processId.longValue());

    if (make != null) {

      DataExchangeRedo redo = new DataExchangeRedo();

      redo.setDataTypeID("ZC_P_PRO_MAKE");

      // redo.setDataTypeName("电子竞价公告发布");

      redo.setDataTypeName("采购计划");

      ElementConditionDto dto = new ElementConditionDto();

     
      redo.setRecordID(make.getZcMakeCode());

      redo.setRecordName(make.getZcMakeName());

      redo.setMasterTableName("ZC_P_PRO_MAKE");

      redo.setIsExported("0");

      redo.setGenerateDate(new Date());

      IDataExchangeDao dataExchangeDao=(IDataExchangeDao)SpringContext.getBean("dataExchangeDao");

      dataExchangeDao.deleteByRecordIDAndDataTypeID(redo);

      dataExchangeDao.saveRedo(redo);

    }

  }

}
