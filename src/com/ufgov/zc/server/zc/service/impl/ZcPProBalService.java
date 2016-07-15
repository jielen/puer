/**   
 * @(#) project: zcxa
 * @(#) file: ZcPProBalService.java
 * 
 * Copyright 2010 UFGOV, Inc. All rights reserved.
 * UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdrive.workflow.context.WorkflowContext;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.common.zc.model.ZcPProBalBi;
import com.ufgov.zc.common.zc.model.ZcPProReturnBi;
import com.ufgov.zc.server.budget.util.BudgetUtil;
import com.ufgov.zc.server.payInterface.util.PayForZcUtil;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IZcPProBalBiDao;
import com.ufgov.zc.server.zc.dao.IZcPProBalDao;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;
import com.ufgov.zc.server.zc.service.IZcPProBalService;

/**
 * @ClassName: ZcPProBalService
 * @Description: TCJLODO(这里用一句话描述这个类的作用)
 * @date: 2010-8-2 下午06:29:01
 * @version: V1.0
 * @since: 1.0
 * @author: Administrator
 * @modify:
 */
public class ZcPProBalService implements IZcPProBalService {

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

  private IZcPProBalDao zcPProBalDao;

  private IZcPProBalBiDao zcPProBalBiDao;

  private IBaseDao baseDao;

  private IZcEbBaseService zcEbBaseServiceF3;

  /**
   * @return the baseDao
   */
  public IBaseDao getBaseDao() {
    return baseDao;
  }

  /**
   * @param baseDao
   *            the baseDao to set
   */
  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public IZcEbBaseService getZcEbBaseServiceF3() {
    return zcEbBaseServiceF3;
  }

  public void setZcEbBaseServiceF3(IZcEbBaseService zcEbBaseServiceF3) {
    this.zcEbBaseServiceF3 = zcEbBaseServiceF3;
  }

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  public IZcPProBalBiDao getZcPProBalBiDao() {
    return zcPProBalBiDao;
  }

  public void setZcPProBalBiDao(IZcPProBalBiDao zcPProBalBiDao) {
    this.zcPProBalBiDao = zcPProBalBiDao;
  }

  public IZcPProBalDao getZcPProBalDao() {
    return zcPProBalDao;
  }

  public void setZcPProBalDao(IZcPProBalDao zcPProBalDao) {
    this.zcPProBalDao = zcPProBalDao;
  }

  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public List getZcPProBalList(ElementConditionDto dto, RequestMeta requestMeta) {
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    List list = zcPProBalDao.getZcPProBalList(dto);
    ZcSUtil.setBillDBDigest(list);
    return list;

  }

  public ZcPProBal updateZcPProBal(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    if ("".equals(ZcSUtil.safeString(zcPProBal.getZcBalId())) || "自动编号".equals(ZcSUtil.safeString(zcPProBal.getZcBalId()))) {
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();
      String code = NumUtil.getInstance().getNo(compoId, "ZC_BAL_ID", zcPProBal);
      Long draftid = workflowDao.createDraftId();
      zcPProBal.setZcBalId(code);
      zcPProBal.setProcessInstId(draftid);
      zcPProBalDao.saveZcPProBal(zcPProBal);
      AsWfDraft asWfDraft = new AsWfDraft();
      asWfDraft.setCompoId(compoId);
      asWfDraft.setWfDraftName(code);
      asWfDraft.setUserId(userId);
      asWfDraft.setMasterTabId(compoId);
      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));
      workflowDao.insertAsWfdraft(asWfDraft);
      List biList = zcPProBal.getBiList();
      for (int i = 0; i < biList.size(); i++) {
        ZcPProBalBi bi = (ZcPProBalBi) biList.get(i);
        bi.setZcBalId(code);
        bi.setZcCode(zcPProBal.getZcMakeCode());
        // 插入
        zcPProBalBiDao.insertZcPProBalBi(bi);
      }
      List returnBiList = zcPProBal.getReturnBiList();
      if (returnBiList != null && returnBiList.size() > 0) {
        for (int i = 0; i < returnBiList.size(); i++) {
          ZcPProReturnBi returnBi = (ZcPProReturnBi) returnBiList.get(i);
          returnBi.setZcBalId(code);
          // 插入
          baseDao.insert("ZC_P_PRO_RETURN_BI.abatorgenerated_insert", returnBi);
        }
      }
    } else {
      String id = zcPProBal.getZcBalId();
      ZcPProBal originalBean = this.selectByPrimaryKey(id, "N");
      ZcSUtil.checkDigest(zcPProBal, originalBean, "zcBalId");// 一致性校验
      // 修改
      zcPProBalDao.updateZcPProBal(zcPProBal);
      zcPProBalBiDao.deleteZcPProBalBiByBalId(id);
      List biList = zcPProBal.getBiList();
      for (int i = 0; i < biList.size(); i++) {
        ZcPProBalBi bi = (ZcPProBalBi) biList.get(i);
        bi.setZcBalId(id);
        bi.setZcCode(zcPProBal.getZcMakeCode());
        zcPProBalBiDao.insertZcPProBalBi(bi);
      }
      List returnBiList = zcPProBal.getReturnBiList();
      baseDao.delete("ZC_P_PRO_RETURN_BI.abatorgenerated_deleteByZcBalId", id);
      if (returnBiList != null && returnBiList.size() > 0) {
        for (int i = 0; i < returnBiList.size(); i++) {
          ZcPProReturnBi returnBi = (ZcPProReturnBi) returnBiList.get(i);
          returnBi.setZcBalId(id);
          // 插入
          baseDao.insert("ZC_P_PRO_RETURN_BI.abatorgenerated_insert", returnBi);
        }
      }
    }
    return zcPProBal;
  }

  public void deleteByPrimaryKey(String zcBalId) {
    zcPProBalDao.deleteZcPProBal(zcBalId);
    zcPProBalBiDao.deleteZcPProBalBiByBalId(zcBalId);
  }

  public void updateAfterPaySuccess(ZcPProBal zcPProBal, RequestMeta requestMeta) {

    zcPProBal.setZcBalStatus("sendGk");
    baseDao.update("ZC_P_PRO_BAL.updateZcPProBalStatus", zcPProBal);
  }

  public ZcPProBal selectByPrimaryKey(String zcBalId, String isFrozen) {
    ZcPProBal zcPProBal = (ZcPProBal) zcPProBalDao.selectByPrimaryKey(zcBalId);
    List balBiList = zcPProBalBiDao.getZcPProBalBiList(zcBalId);
    List returnBiList = baseDao.query("ZC_P_PRO_RETURN_BI.selectZcPProReturnBiList", zcBalId);

    Map map = new HashMap();
    map.put("zcHtCode", zcPProBal.getZcHtCode());
    for (int i = 0; i < balBiList.size(); i++) {
      ZcPProBalBi bi = (ZcPProBalBi) balBiList.get(i);
      map.put("zcHtBiNo", bi.getZcHtBiNo());
      bi.setZcBiYjjsSum(this.getSumZcBalBiSum(map));
    }

    zcPProBal.setBiList(balBiList);
    zcPProBal.setReturnBiList(returnBiList);
    zcPProBal.setDbDigest(null);
    zcPProBal.setDbDigest(zcPProBal.digest());

    return zcPProBal;
  }

  public ZcPProBal callbackFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    WorkflowContext workflowContext = wfEngineAdapter.genCommonWFC(zcPProBal.getComment(), zcPProBal, requestMeta);
    wfEngineAdapter.callback(workflowContext);

    return zcPProBal;
  }

  public ZcPProBal newCommitFN(ZcPProBal currentObject, RequestMeta requestMeta) throws Exception {
    wfEngineAdapter.newCommit(currentObject.getComment(), currentObject, requestMeta);
    return selectByPrimaryKey(currentObject.getZcBalId(), "N");
  }

  public BigDecimal getSumZcBalSum(Map map) {
    return zcPProBalDao.getSumZcBalSum(map);
  }

  public BigDecimal getSumZcBalBiSum(Map map) {
    return zcPProBalBiDao.getSumZcBalBiSum(map);
  }

  public ZcPProBal auditFN(ZcPProBal zcPProBal, String isFrozen, RequestMeta requestMeta) {
    zcPProBal= updateZcPProBal(zcPProBal,requestMeta);
    wfEngineAdapter.commit(zcPProBal.getComment(), zcPProBal, requestMeta);
    if ("Y".equals(isFrozen)) {
      zcPProBal.setBiList(zcPProBalBiDao.getZcPProBalBiList(zcPProBal.getZcBalId()));
      zcEbBaseServiceF3.insertF3Pay(zcPProBal);
    }
    return zcPProBal;
  }

  public ZcPProBal unAuditFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    wfEngineAdapter.rework(zcPProBal.getComment(), zcPProBal, requestMeta);
    return zcPProBal;
  }

  public ZcPProBal untreadFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    wfEngineAdapter.untread(zcPProBal.getComment(), zcPProBal, requestMeta);
    return zcPProBal;
  }

  public boolean sendPay(ZcPProBal zcPProBal, String serverAdd, String isFrozen, RequestMeta requestMeta) {
    Map shifangBudgetMap = null;

    // 支付调用报错后需要再次冻结的指标集合
    // List<Map<String, Object>> updOlds = new ArrayList<Map<String,
    // Object>>();
    List updOlds = new ArrayList();
    BudgetUtil bu=new BudgetUtil();
    // 指标接口和支付接口分开，这样指标接口出错时则不掉用取消结冻指标操作
    try {
      boolean isr = false;
/*      if ("Y".equals(zcPProBal.getIsLastPay())) {
        if (zcPProBal.getReturnBiList() != null && zcPProBal.getReturnBiList().size() > 0) {
          isr = true;
          baseDao.update("ZC_YEAR_END.updateMakeYepFlagOverLastPay", "'" + zcPProBal.getZcMakeCode() + "'");
        } else {
          baseDao.update("ZC_YEAR_END.updateMakeYepFlagLastPay", "'" + zcPProBal.getZcMakeCode() + "'");
        }
      }*/

      zcPProBal= updateZcPProBal(zcPProBal,requestMeta);
      wfEngineAdapter.commit(zcPProBal.getComment(), zcPProBal, requestMeta);

      /*
       * 释放指标金额，本次支付金额10w，则应该修改采购计划金额=原采购计划金额-10w
       */
      shifangBudgetMap = bu.getBalShifangBudget(baseDao, true, zcPProBal.getZcMakeCode(), zcPProBal.getZcBalId(), isr);
      bu.callShifangService(shifangBudgetMap, requestMeta.getSvNd());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
    List sucess = new ArrayList();
    try {
      // 生成支付
      new PayForZcUtil().PayByBal(zcPProBal, sucess, requestMeta);

    } catch (Exception e) {
      try {
        bu.cancelCallService(shifangBudgetMap, requestMeta.getSvNd());
      } catch (Exception e1) {
        System.err.println("支付单【" + zcPProBal.getZcBalId() + "】生成支付出错时，取消结冻的指标出现异常");
        List biLst = zcPProBal.getBiList();
        ZcPProBalBi b = null;
        for (int i = 0; i < biLst.size(); i++) {
          b = (ZcPProBalBi) biLst.get(i);
          System.err.println("合同编号：" + b.getZcHtCode() + "指标编号：" + b.getZcBiNo() + "采购计划或补充合同结冻金额："
            + (b.getZcBiBcjsSum() == null ? "" : b.getZcBiBcjsSum().toString()));
        }
        System.err.println("异常信息如下：");
        e1.printStackTrace();
        throw new RuntimeException(e1.getMessage());
      }
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());

    }
    // 支付指标
    return true;
  }

  /**
   * 
   * 1、收款人信息变更导致退票： 2、支付单金额变更：同“收款人信息变更”类似处理，但是不能超过指标金额；并由支付系统自动补“增/减”计划。
   */
  public boolean editPay(ZcPProBal zcPProBal, String serverAdd, RequestMeta requestMeta) {
    try {
      new PayForZcUtil().updatePayBillByBal(zcPProBal, serverAdd, requestMeta);
    } catch (Exception e1) {
      e1.printStackTrace();
      return false;
    }
    return true;

  }

  public ZcPProBal untreadToFirstFN(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untreadToFirst(zcPProBal.getComment(), zcPProBal, requestMeta);
    return selectByPrimaryKey(zcPProBal.getZcBalId(), "N");
  }

  public void updateAfterPay(ZcPProBal zcPProBal, RequestMeta requestMeta) {
    for (int i = 0; i < zcPProBal.getBiList().size(); i++) {
      ZcPProBalBi bi = (ZcPProBalBi) zcPProBal.getBiList().get(i);
      if (bi.getZcAmBillNo() != null && !"".equals(bi.getZcAmBillNo())) {
        baseDao.update("ZC_P_PRO_BAL_BI.updateZcPProBiZcAmBillNo", bi);
      }
    }

    for (int i = 0; i < zcPProBal.getReturnBiList().size(); i++) {
      ZcPProReturnBi bi = (ZcPProReturnBi) zcPProBal.getReturnBiList().get(i);
      if (bi.getZcAmBillNo() != null && !"".equals(bi.getZcAmBillNo())) {
        baseDao.update("ZC_P_PRO_RETURN_BI.updateZcAmBillNo", bi);
      }
    }
  }
}
