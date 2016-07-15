package com.ufgov.zc.server.zc.proxy.impl;

import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrust;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcWfCurrentTaskCancel;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.proxy.IZcEbEntrustCancel;
import com.ufgov.zc.server.zc.util.ZcEntrustCancelUtil;

public class ZcEbEntrustCancelProxyImpl implements IZcEbEntrustCancel {

  
  public Object entrustCancel(Object o) {
    Map map = (Map) o;
    ZcEbEntrustCancel bean = (ZcEbEntrustCancel) (map.get(ZcEbConstants.BEAN));
    BaseDao baseDao = (BaseDao) (map.get(ZcEbConstants.DAO));

    //已经分包且只取消一部分，不处理任务审批
    if (bean.getPackList() != null && bean.getPackNum() > 0 && bean.getPackNum() != bean.getPackList().size()) {
      return null;
    }

    // 设置业务表相关数据
    ZcWfCurrentTaskCancel task = new ZcWfCurrentTaskCancel();
    task.setEntrustCancelId(bean.getEntrustCancelId());
    task.setTblName(ZcEbConstants.ZC_EB_ENTRUST);
    task.setStatusCol(ZcEbConstants.STATUS);
    task.setKeyColName(ZcEbConstants.SN);

    // 取得业务表格数据
    List list = baseDao.query("ZcEbEntrust.getZcEbEntrustBySn", bean.getSn());
    if (list == null || list.size() == 0) {
      return null;
    }

    for (int i = 0; i < list.size(); i++) {
      ZcEbEntrust entrust = (ZcEbEntrust) list.get(i);
      task.setStatus(entrust.getStatus());
      task.setInstanceId(entrust.getSn());

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
    task.setTblName(ZcEbConstants.ZC_EB_ENTRUST);
    task.setKeyColName(ZcEbConstants.SN);

    // 取得备份
    List list = baseDao.query("ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);
    if (list == null || list.size() == 0) {

    }else{
      task = (ZcWfCurrentTaskCancel) list.get(0);

      ZcEntrustCancelUtil.recovery(baseDao, task);
    }

    task.setTblName("wf_instance");
    list = baseDao.query("ZcWfCurrentTaskCancel.getZcWfCurrentTaskCancelResult", task);

    if (list == null || list.size() == 0) {
      return null;
    }
    for (int i = 0; i < list.size(); i++){
      task = (ZcWfCurrentTaskCancel) list.get(i);

      ZcEntrustCancelUtil.recovery(baseDao, task);
    }

    return null;
  }

}
