package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.ZcEbConstants;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancel;
import com.ufgov.zc.common.zc.model.ZcEbEntrustCancelDetail;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.dao.ibatis.BaseDao;
import com.ufgov.zc.server.zc.service.IZcEbEntrustCancelService;
import com.ufgov.zc.server.zc.util.ZcProxyUtil;

public class ZcEbEntrustCancelService implements IZcEbEntrustCancelService {

  private BaseDao baseDao;

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

  public BaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(BaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List selectZcEbEntrustCancel(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    return baseDao.query("ZcEbEntrustCancel.getZcEbEntrustCancel", elementConditionDto);
  }

  public ZcEbEntrustCancel selectByKey(String code) {
    // TCJLODO Auto-generated method stub
    List list = (List) baseDao.query("ZcEbEntrustCancel.getZcEbEntrustCancelByKey", code);
    if (list == null || list.size() == 0) {
      return new ZcEbEntrustCancel();
    }
    ZcEbEntrustCancel bean = (ZcEbEntrustCancel) list.get(0);
    bean.setPackList(baseDao.query("ZcEbEntrustCancel.selectDetailByKey", code));
    return bean;
  }

  public void deleteZcEbEntrustCancelFN(ZcEbEntrustCancel bean) {
    // TCJLODO Auto-generated method stub
    baseDao.delete("ZcEbEntrustCancel.deleteByKey", bean.getEntrustCancelId());
    baseDao.delete("ZcEbEntrustCancel.deleteDetailByKey", bean.getEntrustCancelId());

  }

  public void deleteZcEbEntrustCancelListFN(List list) {
    // TCJLODO Auto-generated method stub
    for (int i = 0; i < list.size(); i++) {
      ZcEbEntrustCancel bean = (ZcEbEntrustCancel) list.get(i);
      baseDao.delete("ZcEbEntrustCancel.deleteByKey", bean.getEntrustCancelId());
      baseDao.delete("ZcEbEntrustCancel.deleteDetailByKey", bean.getEntrustCancelId());
    }
  }

  public ZcEbEntrustCancel newCommitFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(bean.getComment(), bean, requestMeta);
    return selectByKey(bean.getEntrustCancelId());
  }

  public ZcEbEntrustCancel untreadFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(bean.getComment(), bean, requestMeta);
    return bean;
  }

  public ZcEbEntrustCancel unAuditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.unAudit(bean.getComment(), bean, requestMeta);
    return bean;
  }

  public ZcEbEntrustCancel auditFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    String roleCode = (String) baseDao.read("AsRole.getRoleCodeById", bean.getRoleId());
    boolean isEnd = ("3".equals(bean.getStatus()) && "CG_CGZX_FZR".equals(roleCode)) || ("5".equals(bean.getStatus()) && "CG_CGCZ".equals(roleCode));
    try {

      if (isEnd) {
        cancel(bean, true);
      }
      wfEngineAdapter.commit(bean.getComment(), bean, requestMeta);
    } catch (Exception e) {
      if (isEnd) {
        cancel(bean, false);
      }
      throw e;
    }
    return bean;
  }

  public ZcEbEntrustCancel callbackFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(bean.getComment(), bean, requestMeta);
    return bean;
  }

  public ZcEbEntrustCancel saveZcEbEntrustCancelFN(ZcEbEntrustCancel bean, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub

    if (bean.getEntrustCancelId() == null || "".equals(bean.getEntrustCancelId())) {

      String userId = bean.getZcInputCode();

      String compoId = requestMeta.getCompoId();

      String code = NumUtil.getInstance().getNo(compoId, "ENTRUST_CANCEL_ID", bean);

      Long draftid = workflowDao.createDraftId();

      bean.setEntrustCancelId(code);
      bean.setProcessInstId(draftid);

      bean.setZcInputCode(userId);
      bean.setZcInputDate(requestMeta.getSysDate());

      baseDao.insert("ZcEbEntrustCancel.insertZcEbEntrustCancel", bean);

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(code);

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

      if (bean.getPackList() != null) {
        for (int i = 0; i < bean.getPackList().size(); i++) {
          ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean.getPackList().get(i);
          cd.setEntrustCancelId(bean.getEntrustCancelId());
          baseDao.insert("ZcEbEntrustCancel.insertZcEbEntrustCancelDetail", bean.getPackList().get(i));
        }
      }
    } else {
      baseDao.update("ZcEbEntrustCancel.updateZcEbEntrustCancel", bean);
      baseDao.delete("ZcEbEntrustCancel.deleteDetailByKey", bean.getEntrustCancelId());

      if (bean.getPackList() != null) {
        for (int i = 0; i < bean.getPackList().size(); i++) {
          ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean.getPackList().get(i);
          cd.setEntrustCancelId(bean.getEntrustCancelId());
          baseDao.insert("ZcEbEntrustCancel.insertZcEbEntrustCancelDetail", bean.getPackList().get(i));
        }
      }

    }

    return bean;
  }

  private void cancel(ZcEbEntrustCancel bean, boolean isCancel) throws Exception {
    if (bean.getPackList() != null) {
      StringBuffer proj = new StringBuffer("' '");
      for (int i = 0; i < bean.getPackList().size(); i++) {
        ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean.getPackList().get(i);
        proj.append(",'").append(cd.getPackCode()).append("'");
      }
      List list = baseDao.query("ZcEbEntrustCancel.getZcEbBulletinBidForCancel", proj.toString());
      if (isCancel && list != null && list.size() > 0) {
        StringBuffer err = new StringBuffer("以下项目已成交，不能取消\n");

        for (int i = 0; i < list.size(); i++) {
          err.append("分包编号：").append(list.get(i)).append("\n");
        }
        throw new Exception(err.toString());
      }

      Map para = new HashMap();
      para.put("cancelId", bean.getEntrustCancelId());

      for (int i = 0; i < bean.getPackList().size(); i++) {
        ZcEbEntrustCancelDetail cd = (ZcEbEntrustCancelDetail) bean.getPackList().get(i);
        List projCode = baseDao.query("ZcEbEntrustCancel.getProjCodeByPackCode", cd.getPackCode());
        if (projCode == null || projCode.size() == 0) {
          cd.setIsAll(null);
        }

        Map isAlls = new HashMap();
        for (int j = 0; j < projCode.size(); j++) {
          para.put("projCode", projCode.get(j));
          isAlls.put(projCode.get(j), (Integer) baseDao.read("ZcEbEntrustCancel.selectProjIsAllCancel", para));
          // 只取得最后一个项目
          break;
        }
        cd.setIsAll(isAlls);
      }

    }

    List cName = new ArrayList();
    cName.add("zcPProMakeCancelProxy");
    cName.add("zcEbEntrustCancelProxy");
    cName.add("zcEbAuditSheetCancelProxy");
    cName.add("zcEbRequirementCancelProxy");
    cName.add("zcEbBullitenCancelProxy");
    cName.add("zcEbProjCancelProxy");
    cName.add("zcEbSignupCancelProxy");
    cName.add("zcEbEvalCancelProxy");

    String mName = "entrustCancel";
    if (!isCancel) {
      mName = "entrustRecovery";
    }

    Map map = new HashMap();
    map.put(ZcEbConstants.BEAN, bean);
    map.put(ZcEbConstants.DAO, baseDao);
    ZcProxyUtil.proxy(cName, mName, map);
  }

}
