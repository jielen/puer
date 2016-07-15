package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcEbProjPrintPermit;
import com.ufgov.zc.common.zc.model.ZcEbProjSupport;
import com.ufgov.zc.server.system.dao.ibatis.WorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.dao.IZcEbEntrustBulletinDao;
import com.ufgov.zc.server.zc.service.IZcEbEntrustBulletinService;

public class ZcEbEntrustBulletinService implements IZcEbEntrustBulletinService {

  private IZcEbEntrustBulletinDao bullDao;

  private WorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  public List getZcEbEntrustBull(ElementConditionDto dto, RequestMeta meta) {
    return bullDao.getZcEbEntrustBull(dto, meta);
  }

  public List getZcEbEntrustBullin(ElementConditionDto dto, RequestMeta meta) {
    return bullDao.getZcEbEntrustBullin(dto, meta);
  }

  public List getZcEbEntrustReport(ElementConditionDto dto, RequestMeta meta) {
    return bullDao.getZcEbEntrustReport(dto, meta);
  }

  public List getZcEbProjectSupport(ElementConditionDto dto, RequestMeta meta) {

    return bullDao.getZcEbProjectSupport(dto, meta);
  }

  public IZcEbEntrustBulletinDao getBullDao() {
    return bullDao;
  }

  public void setBullDao(IZcEbEntrustBulletinDao bullDao) {
    this.bullDao = bullDao;
  }

  public WorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(WorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  public void newCommitFN(ZcEbProjSupport proj, RequestMeta meta) {

    ZcEbProjPrintPermit pro = transProjSupportToProjPrintPermit(proj);

    List list = this.bullDao.getZcEbProjPrintPermit(pro);

    if (list != null && list.size() > 0) {
      updateBill(pro, meta);
    } else {
      insertBill(pro, meta);
    }

  }

  private void updateBill(ZcEbProjPrintPermit proj, RequestMeta meta) {
    bullDao.updatePrintPermit(proj);
    wfEngineAdapter.newCommit("", proj, meta);
  }

  private void insertBill(ZcEbProjPrintPermit proj, RequestMeta meta) {

    String userId = meta.getSvUserID();

    String compoId = meta.getCompoId();

    Long draftid = workflowDao.createDraftId();

    proj.setProcessInstId(draftid);

    AsWfDraft asWfDraft = new AsWfDraft();

    asWfDraft.setCompoId(compoId);

    asWfDraft.setWfDraftName(proj.getProjCode());

    asWfDraft.setUserId(userId);

    asWfDraft.setMasterTabId(compoId);

    asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

    workflowDao.insertAsWfdraft(asWfDraft);

    bullDao.insertPrintPermit(proj);

    wfEngineAdapter.newCommit("", proj, meta);
  }

  public void auditFN(ZcEbProjSupport proj, RequestMeta meta) {
    ZcEbProjPrintPermit pro = new ZcEbProjPrintPermit();
    pro.setProjCode(proj.getProjCode());
    pro.setStatus(proj.getStatus());
    pro.setProcessInstId(proj.getProcessInstId());
    //		bullDao.updatePrintPermit(pro);

    wfEngineAdapter.commit(proj.getComment(), pro, meta);

  }

  private ZcEbProjPrintPermit transProjSupportToProjPrintPermit(ZcEbProjSupport proj) {

    ZcEbProjPrintPermit pro = new ZcEbProjPrintPermit();

    pro.setProjCode(proj.getProjCode());

    pro.setStatus(proj.getStatus());

    pro.setProcessInstId(proj.getProcessInstId());

    pro.setComment(proj.getComment());
    
    pro.setOpenBidTime(proj.getOpenBidTime());

    return pro;
  }

  public void untreadFN(ZcEbProjSupport proj, RequestMeta meta) {

    wfEngineAdapter.untread(proj.getComment(), transProjSupportToProjPrintPermit(proj), meta);

  }

  public void callbackFN(ZcEbProjSupport proj, RequestMeta meta) {

    wfEngineAdapter.callback(proj.getComment(), transProjSupportToProjPrintPermit(proj), meta);

  }

  public void unauditFN(ZcEbProjSupport proj, RequestMeta meta) {

    wfEngineAdapter.unAudit(proj.getComment(), transProjSupportToProjPrintPermit(proj), meta);

  }

  public void updateSupplierIsSite(List zcEbProjSupportList) {
    bullDao.updateSupplierIsSite(zcEbProjSupportList);
    
    bullDao.frozenZcBSupplier(zcEbProjSupportList);
  }

  
  public List getZcEbPackHistory(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return bullDao.getZcEbPackHistory(dto, meta);
  }
}
