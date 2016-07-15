package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.constants.ZcPProBalChgConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.model.ZcPProBalChg;
import com.ufgov.zc.common.zc.model.ZcPProBalChgBi;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.common.zc.model.ZcPProMitemBiExample;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.model.ZcXmcgHtBi;
import com.ufgov.zc.server.budget.util.BudgetUtil;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcPProBalChgService;
import com.ufgov.zc.server.zc.service.IZcPProMakeService;

public class ZcPProBalChgService implements IZcPProBalChgService {
  private BaseDao baseDao;

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

  private IZcPProMakeService zcPProMakeService;

  /**
   * @return the zcPProMakeService
   */
  public IZcPProMakeService getZcPProMakeService() {
    return zcPProMakeService;
  }

  /**
   * @param zcPProMakeService the zcPProMakeService to set
   */
  public void setZcPProMakeService(IZcPProMakeService zcPProMakeService) {
    this.zcPProMakeService = zcPProMakeService;
  }

  public List getZcPProBalChgList(ElementConditionDto dto, RequestMeta meta) {
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    return baseDao.query("ZC_P_PRO_BAL_CHG.selectZcPProBalChgList", dto);
  }

  public void deleteZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception {
    Map map = new BudgetUtil().getBalChgDelBudget(baseDao, flag, zcPProBalChg.getZcMakeCode(), zcPProBalChg.getBalChgId());
    baseDao.delete("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_deleteByBalChgId", zcPProBalChg.getBalChgId());
    baseDao.delete("ZC_P_PRO_BAL_CHG.abatorgenerated_deleteByPrimaryKey", zcPProBalChg);
    new BudgetUtil().callService(map, meta.getSvNd());

  }

  public ZcPProBalChg updateZcPProBalChgFN(ZcPProBalChg zcPProBalChg, String serverAdd, boolean flag, RequestMeta meta) throws Exception {

    //更新采购计划、原采购计划中的资金构成到历史记录表中、保存采购合同中的资金构成情况
    String userId = meta.getSvUserID();

    String compoId = meta.getCompoId();

    boolean isDraft = false;

    if (zcPProBalChg.getProcessInstId() == null || zcPProBalChg.getProcessInstId().longValue() == -1) {
      Long draftid = workflowDao.createDraftId();
      zcPProBalChg.setProcessInstId(draftid);
      isDraft = true;

    }

    // 生成接口数据
    Map map = null;

    if ("".equals(ZcSUtil.safeString(zcPProBalChg.getBalChgId())) || zcPProBalChg.getBalChgId().equals("自动编号")) {
      String code = NumUtil.getInstance().getNo(compoId, "BAL_CHG_ID", zcPProBalChg);
      zcPProBalChg.setBalChgId(code);

      map = new BudgetUtil().getBalChgSaveBudget(baseDao, flag, zcPProBalChg.getZcMakeCode(), code, zcPProBalChg.getZcPProChgBiList());

      baseDao.insert("ZC_P_PRO_BAL_CHG.abatorgenerated_insert", zcPProBalChg);
      List biList = zcPProBalChg.getZcPProChgBiList();
      for (int i = 0; i < biList.size(); i++) {
        ZcPProBalChgBi chg = (ZcPProBalChgBi) biList.get(i);
        chg.setBalChgId(zcPProBalChg.getBalChgId());
        chg.setZcMakeCode(zcPProBalChg.getZcMakeCode());
        baseDao.insert("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_insert", chg);
      }

    } else {

      String chgId = zcPProBalChg.getBalChgId();

      map = new BudgetUtil().getBalChgSaveBudget(baseDao, flag, zcPProBalChg.getZcMakeCode(), chgId, zcPProBalChg.getZcPProChgBiList());

      baseDao.update("ZC_P_PRO_BAL_CHG.abatorgenerated_updateByPrimaryKeySelective", zcPProBalChg);

      baseDao.delete("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_deleteByBalChgId", zcPProBalChg.getBalChgId());
      List biList = zcPProBalChg.getZcPProChgBiList();

      for (int i = 0; i < biList.size(); i++) {
        ZcPProBalChgBi chg = (ZcPProBalChgBi) biList.get(i);
        chg.setBalChgId(zcPProBalChg.getBalChgId());
        chg.setZcMakeCode(zcPProBalChg.getZcMakeCode());
        baseDao.insert("ZC_P_PRO_BAL_CHG_BI.abatorgenerated_insert", chg);
      }
    }

    if (ZcPProBalChgConstants.ZC_P_PRO_BAL_STATUS_UPDATE_HT.equals(zcPProBalChg.getStatus())) {
      //校验合同的分配情况。合同的资金构成的总金额是否等于合同金额
      try {
        updateZcPProMitmBI(zcPProBalChg);
        saveZcHtChgBi(zcPProBalChg);
        savaHistoryMakeBI(zcPProBalChg);
        updateZcXmchHtBi(zcPProBalChg);
        //        return selectByPrimaryKey(zcPProBalChg.getBalChgId(), meta);
      } catch (Exception e) {
        // TCJLODO Auto-generated catch block
        e.printStackTrace();
        throw e;
      }

    }

    if (isDraft) {

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(zcPProBalChg.getBalChgId());

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(zcPProBalChg.getProcessInstId().longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

    }

    new BudgetUtil().callService(map, meta.getSvNd());

    return selectByPrimaryKey(zcPProBalChg.getBalChgId(), meta);

  }

  public ZcPProBalChg selectByPrimaryKey(String balChgId, RequestMeta requestMeta) {
    return (ZcPProBalChg) baseDao.read("ZC_P_PRO_BAL_CHG.abatorgenerated_selectByPrimaryKey", balChgId);

  }

  /**
   * @return the baseDao
   */
  public BaseDao getBaseDao() {
    return baseDao;
  }

  /**
   * @param baseDao the baseDao to set
   */
  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  /**
   * @return the wfEngineAdapter
   */
  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  /**
   * @param wfEngineAdapter the wfEngineAdapter to set
   */
  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  /**
   * @return the workflowDao
   */
  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  /**
   * @param workflowDao the workflowDao to set
   */
  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  /**
   * update采购计划中的资金构成情况
   */

  public void updateZcPProMitmBI(ZcPProBalChg zcPProBalChg) throws Exception {

    List biList = zcPProBalChg.getZcPProChgBiList();

    ZcPProMitemBiExample example = new ZcPProMitemBiExample();
    example.createCriteria().andZcMakeCodeEqualTo(zcPProBalChg.getZcMakeCode());
    this.baseDao.delete("ZC_P_PRO_MITEM_BI.ibatorgenerated_deleteByExample", example);

    for (int i = 0; i < biList.size(); i++) {

      ZcPProBalChgBi chg = (ZcPProBalChgBi) biList.get(i);
      ZcPProMitemBi bi = new ZcPProMitemBi();
      BeanUtil.commonFieldsCopy(chg, bi);
      bi.setZcMakeCode(zcPProBalChg.getZcMakeCode());

      baseDao.insert("ZC_P_PRO_MITEM_BI.ibatorgenerated_insert", bi);

    }

  }

  /**
   * 保存原采购计划中的资金构成到历史记录表中
   * 
   */
  private void savaHistoryMakeBI(ZcPProBalChg zcPProBalChg) {
    if (zcPProBalChg.getOldBiList() != null && zcPProBalChg.getOldBiList().size() > 0) {
      for (int i = 0; i < zcPProBalChg.getOldBiList().size(); i++) {
        Map map = new HashMap();
        map.put("bi", zcPProBalChg.getOldBiList().get(i));
        map.put("balChgId", zcPProBalChg.getBalChgId());
        map.put("zcMakeCode", zcPProBalChg.getZcMakeCode());
        baseDao.delete("ZC_P_PRO_MITEM_BI_HISTORY.deleteZcPProMitemBiHisByMakeCode", map);
        baseDao.insert("ZC_P_PRO_MITEM_BI_HISTORY.insertIntoZcPProMitemBiHis", map);
      }
    }
  }

  /**
   * 保存调节的合同资金构成
   * 
   */
  private void saveZcHtChgBi(ZcPProBalChg zcPProBalChg) {
    List xmcgHtList = zcPProBalChg.getXmcgHtList();
    if (xmcgHtList != null && xmcgHtList.size() > 0) {
      //先删，后插入
      baseDao.delete("ZC_BAL_CHG_HT_BI.deleteByChgCode", zcPProBalChg.getBalChgId());
      for (int i = 0; i < xmcgHtList.size(); i++) {
        ZcXmcgHt zcXmcgHt = (ZcXmcgHt) xmcgHtList.get(i);
        List biList = zcXmcgHt.getBiList();
        for (int j = 0; j < biList.size(); j++) {
          ZcXmcgHtBi bi = (ZcXmcgHtBi) biList.get(j);
          if (bi.getZcBiBcsySum() == null || bi.getZcBiBcsySum().longValue() == 0L) {
            continue;
          }
          Map map = new HashMap();
          map.put("bi", bi);
          map.put("balChgId", zcPProBalChg.getBalChgId());
          baseDao.insert("ZC_BAL_CHG_HT_BI.ibatorgenerated_insert", map);
        }
      }
    }

  }

  /**
   * 调节采购合同中的资金构成情况
   */
  private void updateZcXmchHtBi(ZcPProBalChg zcPProBalChg) {
    List xmcgHtList = zcPProBalChg.getXmcgHtList();
    if (xmcgHtList != null && xmcgHtList.size() > 0) {
      for (int i = 0; i < xmcgHtList.size(); i++) {
        ZcXmcgHt zcXmcgHt = (ZcXmcgHt) xmcgHtList.get(i);
        //先删，后插入
        baseDao.delete("ZC_XMCG_HT_BI.deleteByHtCode", zcXmcgHt.getZcHtCode());
        List biList = zcXmcgHt.getBiList();
        for (int j = 0; j < biList.size(); j++) {
          ZcXmcgHtBi bi = (ZcXmcgHtBi) biList.get(j);
          if (bi.getZcBiBcsySum() == null || bi.getZcBiBcsySum().longValue() == 0L) {
            continue;
          }
          baseDao.insert("ZC_XMCG_HT_BI.ibatorgenerated_insert", bi);
        }
      }
    }

  }
}
