package com.ufgov.zc.server.zc.proxy.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcPProMakeCancelProxyImpl implements IZcEbEntrustCancel {

  
  public Object entrustCancel(Object o) {
    Map map = (Map) o;
    ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map.get(ZcEbConstants.BEAN));
    BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));
    //已经分包且只取消一部分，不处理批办单
    if(bean.getPackList() != null && bean.getPackNum() > 0 && bean.getPackNum() != bean.getPackList().size()){
      return null;
    }

    // 设置业务表相关数据
    ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
    task.setEntrustCancelId(bean.getEntrustCancelId());
    task.setTblName(ZcEbConstants.ZC_P_PRO_MAKE);
    task.setKeyColName(ZcEbConstants.ZC_MAKE_CODE);
    task.setStatusCol(ZcEbConstants.ZC_YEP_FLAG);

    // 取得业务表格数据
    ZcPProMake key = new ZcPProMake();
    key.setZcMakeCode(bean.getZcMakeCode());
    List list = baseDao.query("ZC_P_PRO_MAKE.ibatorgenerated_selectByPrimaryKey", key);
    if (list == null || list.size() == 0) {
      return null;
    }

    for(int i = 0; i < list.size(); i++){
      ZcPProMake make = (ZcPProMake) list.get(i);
      task.setStatus(make.getZcMakeStatus());
      task.setInstanceId(bean.getZcMakeCode());

      ZcEntrustCancelUtil.cancel(baseDao, task);
    }
    return null;
  }

  
  public Object entrustRecovery(Object o) {
    Map map = (Map) o;
    ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map.get(ZcEbConstants.BEAN));
    BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));

    // 设置业务表相关数据
    ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
    task.setEntrustCancelId(bean.getEntrustCancelId());
    task.setTblName(ZcEbConstants.ZC_EB_DUTY_AUDIT_SHEET);
    task.setKeyColName(ZcEbConstants.PROCESS_INST_ID);

    // 取得备份
    List list = baseDao.query("ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
    if (list == null || list.size() == 0) {
      return null;
    }
    task = (ZcWfCurrentTaskCancel) list.get(0);

    ZcEntrustCancelUtil.recovery(baseDao, task);
    return null;
  }

}
