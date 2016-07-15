package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBill;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBillItem;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcHtPrePayBillService;

public class ZcHtPrePayBillService implements IZcHtPrePayBillService {
  private BaseDao baseDao;

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

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

  public List getZcHtPrePayBillList(ElementConditionDto dto, RequestMeta meta) {
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    return baseDao.query("ZC_HT_PRE_PAY_BILL.getHtPrePayBillList", dto);
  }

  public void deleteZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta) {
    if (zcHtPrePayBill.getZcImpFileBlobid() != null && !"".equals(zcHtPrePayBill.getZcImpFileBlobid())) {
      baseDao.delete("AsFile.deleteAsFileById", zcHtPrePayBill.getZcImpFileBlobid());
    }
    baseDao.delete("ZC_HT_PRE_PAY_BILL.abatorgenerated_deleteByPrimaryKey", zcHtPrePayBill.getBillCode());
    baseDao.delete("ZC_HT_PRE_PAY_BILL.deleteItemDetailByBillCode", zcHtPrePayBill.getBillCode());

  }

  public ZcHtPrePayBill updateZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta) {
    if ("".equals(ZcSUtil.safeString(zcHtPrePayBill.getBillCode())) || "自动编号".equals(ZcSUtil.safeString(zcHtPrePayBill.getBillCode()))) {
      String userId = meta.getSvUserID();
      String compoId = meta.getCompoId();
      String code = NumUtil.getInstance().getNo(compoId, "BILL_CODE", zcHtPrePayBill);
      Long draftid = workflowDao.createDraftId();
      zcHtPrePayBill.setBillCode(code);
      zcHtPrePayBill.setProcessInstId(draftid);
      AsWfDraft asWfDraft = new AsWfDraft();
      asWfDraft.setCompoId(compoId);
      asWfDraft.setWfDraftName(code);
      asWfDraft.setUserId(userId);
      asWfDraft.setMasterTabId(compoId);
      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));
      workflowDao.insertAsWfdraft(asWfDraft);
      baseDao.insert("ZC_HT_PRE_PAY_BILL.abatorgenerated_insert", zcHtPrePayBill);
      List biList = zcHtPrePayBill.getBillList();

      ZcHtPrePayBillItem bi = (ZcHtPrePayBillItem) biList.get(biList.size() - 1);
      bi.setPayQrCode(code);
      baseDao.insert("ZC_HT_PRE_PAY_BILL.abatorgenerated_insertItemDetail", bi);
    } else {
      String billCode = zcHtPrePayBill.getBillCode();
      baseDao.update("ZC_HT_PRE_PAY_BILL.abatorgenerated_updateByPrimaryKey", zcHtPrePayBill);
      baseDao.delete("ZC_HT_PRE_PAY_BILL.deleteItemDetailByBillCode", zcHtPrePayBill.getBillCode());
      List biList = zcHtPrePayBill.getBillList();

      ZcHtPrePayBillItem bi = (ZcHtPrePayBillItem) biList.get(biList.size() - 1);
      bi.setPayQrCode(billCode);
      baseDao.insert("ZC_HT_PRE_PAY_BILL.abatorgenerated_insertItemDetail", bi);
    }
    return selectByPrimaryKey(zcHtPrePayBill.getBillCode(), meta);
  }

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode, RequestMeta requestMeta) {
    ZcHtPrePayBill bill = (ZcHtPrePayBill) baseDao.read("ZC_HT_PRE_PAY_BILL.abatorgenerated_selectByPrimaryKey", BillCode);
    if (bill == null) {
      return null;
    }
    if (bill.getZcHtCode() != null) {
      //	    List billList = baseDao.query("ZC_HT_PRE_PAY_BILL.selectPayBillItemByBillCode",BillCode);
      List billList = getZcHtPrePayBillListByHtCode(bill, requestMeta);
      bill.setBillList(billList);
    } else {
      bill.setBillList(new ArrayList());
    }
    return bill;
  }

  public List getZcHtPrePayBillListByHtCode(ZcHtPrePayBill bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    List list = baseDao.query("ZC_HT_PRE_PAY_BILL.selectAddQrByBillCode", bean.getZcHtCode());
    List l = new ArrayList();
    if (list != null && list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        ZcHtPrePayBillItem it = (ZcHtPrePayBillItem) list.get(i);
        l.add(it);
        if (it.getPayQrCode() == null || "".equals(it.getPayQrCode()) || (bean.getBillCode() != null && bean.getBillCode().equals(it.getPayQrCode()))) {
          //	      it.setStatus("1");
          return l;
        }
      }
    }
    return list;
  }

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode) {
    ZcHtPrePayBill bill = (ZcHtPrePayBill) baseDao.read("ZC_HT_PRE_PAY_BILL.abatorgenerated_selectByPrimaryKey", BillCode);
    if (bill == null) {
      return null;
    }
    if (bill.getZcHtCode() != null) {
      List billList = baseDao.query("ZC_HT_PRE_PAY_BILL.selectPayBillItemByBillCode", BillCode);
      bill.setBillList(billList);
    } else {
      bill.setBillList(new ArrayList());
    }
    String coName = (String) baseDao.read("ZC_HT_PRE_PAY_BILL.selectCoNameByCode", bill.getCoCode());
    bill.setCoName(coName == null ? "" : coName);
    ZcPProMake zcPProMake = new ZcPProMake();
    zcPProMake.setZcMakeCode(bill.getZcMakeCode());
    zcPProMake = (ZcPProMake) baseDao.read("ZC_P_PRO_MAKE.ibatorgenerated_selectByPrimaryKey", zcPProMake);
    ZcXmcgHt zcXmcgHt = new ZcXmcgHt();
    zcXmcgHt.setZcHtCode(bill.getZcHtCode());
    zcXmcgHt = (ZcXmcgHt) baseDao.read("ZC_XMCG_HT.ibatorgenerated_selectByPrimaryKey", zcXmcgHt);
    if (zcPProMake != null) {
      bill.setZcPProMake(zcPProMake);
    } else {
      bill.setZcPProMake(new ZcPProMake());
    }
    if (zcXmcgHt != null) {
      bill.setZcXmcgHt(zcXmcgHt);
    } else {
      bill.setZcXmcgHt(new ZcXmcgHt());
    }

    return bill;
  }
}
