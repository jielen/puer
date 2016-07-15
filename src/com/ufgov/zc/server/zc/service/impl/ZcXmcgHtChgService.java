package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdrive.workflow.context.WorkflowContext;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.exception.BusinessException;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBillItemChg;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.common.zc.model.ZcTBchtItemChg;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.model.ZcXmcgHtBi;
import com.ufgov.zc.common.zc.model.ZcXmcgHtBiChg;
import com.ufgov.zc.common.zc.model.ZcXmcgHtChg;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.service.IZcXmcgHtChgService;

public class ZcXmcgHtChgService implements IZcXmcgHtChgService {

  private IBaseDao baseDao;

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List getZcXmcgHtChg(ElementConditionDto dto, RequestMeta meta) throws Exception {
    // TCJLODO Auto-generated method stub
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    return baseDao.query("ZC_XMCG_HT_CHG.selectHtChgListByExample", dto);
  }

  public ZcXmcgHtChg updateZcXmcgHtChgFN(ZcXmcgHtChg zcXmcgHtChg, boolean flag, String serverAdd, RequestMeta requestMeta) throws Exception {

    if ("".equals(ZcSUtil.safeString(zcXmcgHtChg.getZcHtChgId()))) {

      String userId = requestMeta.getSvUserID();

      String compoId = requestMeta.getCompoId();

      String code = NumUtil.getInstance().getNo(compoId, "ZC_HT_CHG_ID", zcXmcgHtChg);

      Long draftid = workflowDao.createDraftId();

      zcXmcgHtChg.setZcHtChgId(code);

      zcXmcgHtChg.setZcInputCode(userId);

      zcXmcgHtChg.setZcInputDate(requestMeta.getSysDate());

      zcXmcgHtChg.setZcHtType("1");// 项目采购

      zcXmcgHtChg.setProcessInstId(draftid);

      baseDao.insert("ZC_XMCG_HT_CHG.insertHtChg", zcXmcgHtChg);

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(code);

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

      List biList = zcXmcgHtChg.getBiList();

      for (int i = 0; i < biList.size(); i++) {

        ZcXmcgHtBiChg bi = (ZcXmcgHtBiChg) biList.get(i);

        bi.setZcHtChgId(code);

        bi.setZcMakeCode(zcXmcgHtChg.getZcMakeCode());

        baseDao.insert("ZC_XMCG_HT_CHG.insertbiChg", bi);

      }

      List itemList = zcXmcgHtChg.getItemList();

      for (int i = 0; i < itemList.size(); i++) {

        ZcTBchtItemChg item = (ZcTBchtItemChg) itemList.get(i);

        item.setZcHtChgId(code);

        item.setZcCtgryId(new BigDecimal(i));

        baseDao.insert("ZC_XMCG_HT_CHG.insertItemChg", item);

      }

      List payList = zcXmcgHtChg.getPayBiList();

      for (int i = 0; i < payList.size(); i++) {
        ZcHtPrePayBillItemChg bi = (ZcHtPrePayBillItemChg) payList.get(i);
        bi.setZcHtChgId(code);
        bi.setBillCode(zcXmcgHtChg.getZcHtCode());
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");

        bi.setPayDate(s.parse(bi.getPayYear() + bi.getPayMonth() + "01"));
        baseDao.insert("ZC_XMCG_HT_CHG.insertBillChg", bi);
      }
      Map map = new HashMap();
      map.put("zcHtChgId", zcXmcgHtChg.getZcHtChgId());
      map.put("zcHtCode", zcXmcgHtChg.getZcHtCode());
      baseDao.insert("ZC_XMCG_HT_CHG.insertHtHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertbiHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertItemHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertBillHistory", map);

    } else {

      String zcHtChgId = zcXmcgHtChg.getZcHtChgId();

      ZcXmcgHtChg originalBean = this.selectByPrimaryKey(zcHtChgId, requestMeta);
      if (originalBean == null) {
        throw new BusinessException("该合同变更已经被删除！");
      }

      //			ZcSUtil.checkDigest(zcXmcgHtChg, originalBean, "zcHtChgId");// 一致性校验

      baseDao.update("ZC_XMCG_HT_CHG.updateHtChgByPrimaryKey", zcXmcgHtChg);

      baseDao.delete("ZC_XMCG_HT_CHG.deletebiChgByChgId", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deleteItemChgByPrimaryKey", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deleteBillChgByChgId", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deleteHtHistoryByPrimaryKey", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deletebiHistoryByChgId", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deleteItemHistoryByPrimaryKey", zcHtChgId);
      baseDao.delete("ZC_XMCG_HT_CHG.deleteBillHistoryByChgId", zcHtChgId);

      List biList = zcXmcgHtChg.getBiList();

      for (int i = 0; i < biList.size(); i++) {

        ZcXmcgHtBiChg bi = (ZcXmcgHtBiChg) biList.get(i);

        bi.setZcHtChgId(zcHtChgId);

        bi.setZcMakeCode(zcXmcgHtChg.getZcMakeCode());

        baseDao.insert("ZC_XMCG_HT_CHG.insertbiChg", bi);

      }

      List itemList = zcXmcgHtChg.getItemList();

      for (int i = 0; i < itemList.size(); i++) {

        ZcTBchtItemChg item = (ZcTBchtItemChg) itemList.get(i);

        item.setZcHtChgId(zcHtChgId);

        item.setZcCtgryId(new BigDecimal(i));

        baseDao.insert("ZC_XMCG_HT_CHG.insertItemChg", item);

      }

      List payList = zcXmcgHtChg.getPayBiList();

      for (int i = 0; i < payList.size(); i++) {
        ZcHtPrePayBillItemChg bi = (ZcHtPrePayBillItemChg) payList.get(i);
        bi.setZcHtChgId(zcHtChgId);
        bi.setBillCode(zcXmcgHtChg.getZcHtCode());
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");

        bi.setPayDate(s.parse(bi.getPayYear() + bi.getPayMonth() + "01"));
        baseDao.insert("ZC_XMCG_HT_CHG.insertBillChg", bi);
      }
      Map map = new HashMap();
      map.put("zcHtChgId", zcXmcgHtChg.getZcHtChgId());
      map.put("zcHtCode", zcXmcgHtChg.getZcHtCode());
      baseDao.insert("ZC_XMCG_HT_CHG.insertHtHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertbiHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertItemHistory", map);
      baseDao.insert("ZC_XMCG_HT_CHG.insertBillHistory", map);
    }
    return zcXmcgHtChg;
  }

  public ZcXmcgHtChg selectByPrimaryKey(String zcHtChgId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    ZcXmcgHtChg chg = (ZcXmcgHtChg) baseDao.read("ZC_XMCG_HT_CHG.selectHtChgByPrimaryKey", zcHtChgId);
    chg.setBiList(baseDao.query("ZC_XMCG_HT_CHG.selectBiChgByChgId", zcHtChgId));

    ElementConditionDto dto = new ElementConditionDto();
    for (int i = 0; i < chg.getBiList().size(); i++) {
      ZcXmcgHtBiChg bi = (ZcXmcgHtBiChg) chg.getBiList().get(i);
      dto.setZcText1(bi.getZcProBiSeq());

      List proMitemBiList = baseDao.query("ZC_P_PRO_MITEM_BI.getMitemBiWithHtBi", dto);

      if (proMitemBiList.size() != 0) {

        ZcPProMitemBi zcPProMitemBi = (ZcPProMitemBi) proMitemBiList.get(0);

        zcPProMitemBi.setZcBiUsedSum(zcPProMitemBi.getZcBiJhuaSum());

        bi.setZcPProMitemBi(zcPProMitemBi);

      }
    }
    chg.setItemList(baseDao.query("ZC_XMCG_HT_CHG.selectItemChgByExample", zcHtChgId));
    chg.setPayBiList(baseDao.query("ZC_XMCG_HT_CHG.selectBillChgByChgId", zcHtChgId));
    //		ZcXmcgHt ht = (ZcXmcgHt) baseDao.read(
    //				"ZC_XMCG_HT_CHG.selectHtHistoryByPrimaryKey", zcHtChgId);
    //		ht.setBiList(baseDao.query("ZC_XMCG_HT_CHG.selectBiHistoryByChgId",
    //				zcHtChgId));
    //
    //		ht.setItemList(baseDao.query(
    //				"ZC_XMCG_HT_CHG.selectItemHistoryByExample", zcHtChgId));
    //		ht.setPayBiList(baseDao.query(
    //				"ZC_XMCG_HT_CHG.selectBillHistoryByChgId", zcHtChgId));
    chg.setZcXmht(selectZcXmcgHtByPrimaryKey(zcHtChgId, requestMeta));

    chg.setDbDigest(null);

    chg.setDbDigest(chg.digest());// 统一入口
    return chg;
  }

  public void deleteByPrimaryKeyFN(String zcHtChgId, boolean flag, String serverAdd, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    baseDao.delete("ZC_XMCG_HT_CHG.deleteHtChgByPrimaryKey", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deletebiChgByChgId", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteItemChgByPrimaryKey", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteBillChgByChgId", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteHtHistoryByPrimaryKey", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deletebiHistoryByChgId", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteItemHistoryByPrimaryKey", zcHtChgId);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteBillHistoryByChgId", zcHtChgId);

  }

  public ZcXmcgHt selectZcXmcgHtByPrimaryKey(String zcHtChgId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    ZcXmcgHt ht = (ZcXmcgHt) baseDao.read("ZC_XMCG_HT_CHG.selectHtHistoryByPrimaryKey", zcHtChgId);
    ht.setBiList(baseDao.query("ZC_XMCG_HT_CHG.selectBiHistoryByChgId", zcHtChgId));

    ElementConditionDto dto = new ElementConditionDto();
    for (int i = 0; i < ht.getBiList().size(); i++) {
      ZcXmcgHtBi bi = (ZcXmcgHtBi) ht.getBiList().get(i);
      dto.setZcText1(bi.getZcProBiSeq());

      List proMitemBiList = baseDao.query("ZC_P_PRO_MITEM_BI.getMitemBiWithHtBi", dto);

      if (proMitemBiList.size() != 0) {

        ZcPProMitemBi zcPProMitemBi = (ZcPProMitemBi) proMitemBiList.get(0);

        zcPProMitemBi.setZcBiUsedSum(zcPProMitemBi.getZcBiJhuaSum());

        bi.setZcPProMitemBi(zcPProMitemBi);

      }
    }
    ht.setItemList(baseDao.query("ZC_XMCG_HT_CHG.selectItemHistoryByExample", zcHtChgId));
    ht.setPayBiList(baseDao.query("ZC_XMCG_HT_CHG.selectBillHistoryByChgId", zcHtChgId));
    return ht;
  }

  public ZcXmcgHtChg newCommitFN(ZcXmcgHtChg ht, boolean flag, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub

    ht.setZcMakeCode(ht.getZcPProMake().getZcMakeCode());

    wfEngineAdapter.newCommit(ht.getComment(), ht, requestMeta);

    return selectByPrimaryKey(ht.getZcHtChgId(), requestMeta);
  }

  public ZcXmcgHtChg callbackFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    WorkflowContext workflowContext = wfEngineAdapter.genCommonWFC(ht.getComment(), ht, requestMeta);
    wfEngineAdapter.callback(workflowContext);
    return ht;
  }

  public ZcXmcgHtChg untreadFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(ht.getComment(), ht, requestMeta);
    return ht;
  }

  public ZcXmcgHtChg unAuditFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {

    wfEngineAdapter.rework(ht.getComment(), ht, requestMeta);

    return ht;
  }

  public ZcXmcgHtChg auditFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    if ("3".equals(ht.getZcHtStatus()) || "exec".equals(ht.getZcHtStatus())) {
      resetHt(ht.getZcHtCode(), ht.getZcHtChgId());
    }
    wfEngineAdapter.commit(ht.getComment(), ht, requestMeta);

    return ht;
  }

  private void resetHt(String htCode, String chgId) {
    baseDao.delete("ZC_XMCG_HT_CHG.deleteHtByPrimaryKey", htCode);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteBiByChgId", htCode);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteItemByPrimaryKey", htCode);
    baseDao.delete("ZC_XMCG_HT_CHG.deleteBillByChgId", htCode);
    baseDao.insert("ZC_XMCG_HT_CHG.insertHt", chgId);
    baseDao.insert("ZC_XMCG_HT_CHG.insertBi", chgId);
    baseDao.insert("ZC_XMCG_HT_CHG.insertItem", chgId);
    baseDao.insert("ZC_XMCG_HT_CHG.insertBill", chgId);
  }

}
