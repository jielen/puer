package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcEbConsult;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.service.IZcEbConsultService;

public class ZcEbConsultService implements IZcEbConsultService {

  private IBaseDao baseDao;

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List findConsultList(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(meta.getCompoId(), "fwatch"));
    return baseDao.query("ZcEbConsult.findList", dto);
  }

  public ZcEbConsult findConsultById(String id, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return (ZcEbConsult) baseDao.read("ZcEbConsult.findConsultById", id);
  }

  public void deleteConsultById(String id, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    baseDao.delete("ZcEbConsult.deleteById", id);
  }

  public void deleteConsultByIds(List ids, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    for (int i = 0; i < ids.size(); i++) {
      deleteConsultById(ids.get(i).toString(), meta);
    }
  }

  public ZcEbConsult updateConsult(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    if (bean.getId() == null || bean.getId().length() == 0) {
      Long draftid = workflowDao.createDraftId();

      AsWfDraft asWfDraft = new AsWfDraft();
      asWfDraft.setCompoId(meta.getCompoId());
      asWfDraft.setWfDraftName(bean.getTitleField());
      asWfDraft.setUserId(meta.getSvUserID());
      asWfDraft.setMasterTabId(meta.getCompoId());
      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);
      bean.setProcessInstId(draftid);
      String code = NumUtil.getInstance().getNo(meta.getCompoId(), "ID", bean);

      bean.setId(code);

      baseDao.insert("ZcEbConsult.insertConsult", bean);
    } else {
      if (bean.getReplyDate() == null && bean.getReply() != null && bean.getReply().length() > 0) {
        bean.setReplyDate(new Date());
      }
      baseDao.update("ZcEbConsult.updateConsult", bean);
    }
    return findConsultById(bean.getId(), meta);
  }

  public ZcEbConsult newCommitFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(bean.getComment(), bean, meta);
    return bean;
  }

  public ZcEbConsult auditFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.commit(bean.getComment(), bean, meta);
    bean = findConsultById(bean.getId(), meta);
    if ("exec".equals(bean.getStatus())) {
      baseDao.insert("ZcEbConsult.insertWcmsAsk", bean);
      baseDao.insert("ZcEbConsult.insertWcmsAns", bean);
    }
    return bean;
  }

  public ZcEbConsult untreadFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(bean.getComment(), bean, meta);
    return bean;
  }

  public ZcEbConsult callbackFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(bean.getComment(), bean, meta);
    return bean;
  }

  public ZcEbConsult unAuditFN(ZcEbConsult bean, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.unAudit(bean.getComment(), bean, meta);
    return bean;
  }

}
