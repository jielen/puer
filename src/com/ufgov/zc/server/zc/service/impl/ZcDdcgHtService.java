package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.ZcDdcgHt;
import com.ufgov.zc.common.zc.model.ZcDdcghtItem;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.service.IZcDdcgHtService;

public class ZcDdcgHtService implements IZcDdcgHtService {

  private IBaseDao baseDao;

  private IWorkflowDao workflowDao;

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

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public List getZcDdcgHt(ElementConditionDto dto, RequestMeta meta) throws SQLException {

    dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(dto.getWfcompoId(), NumLimConstants.FWATCH));
    return baseDao.query("ZC_DDCG_HT._selectZcDdcgHtList", dto);
  }

  public ZcDdcgHt selectByPrimaryKey(String zcHtCode, RequestMeta requestMeta) {

    return (ZcDdcgHt) baseDao.read("ZC_DDCG_HT._selectByPrimaryKey", zcHtCode);
  }

  public void deleteByPrimaryKeyFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception {
    baseDao.delete("ZC_DDCG_HT._deleteByZcHtCode", zcDdcgHt.getZcHtCode());
  }

  public ZcDdcgHt updateZcDdcgHtFN(ZcDdcgHt zcDdcgHt, RequestMeta requestMeta) throws Exception {

    if ("".equals(ZcSUtil.safeString(zcDdcgHt.getZcHtCode()))) {

      String userId = requestMeta.getSvUserID();

      String compoId = requestMeta.getCompoId();

      String code = NumUtil.getInstance().getNo(compoId, "ZC_HT_CODE", zcDdcgHt);

      Long draftid = workflowDao.createDraftId();

      zcDdcgHt.setZcHtCode(code);

      zcDdcgHt.setProcessInstId(draftid);

      baseDao.update("ZC_DDCG_HT._insert", zcDdcgHt);

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(code);

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

      List itemList = zcDdcgHt.getItemList();

      for (int i = 0; i < itemList.size(); i++) {

        ZcDdcghtItem item = (ZcDdcghtItem) itemList.get(i);
        item.setZcHtCode(code);

      }

    } else {
      String code = zcDdcgHt.getZcHtCode();
      //删掉As_file表里面老的合同文本
      ZcDdcgHt ht = (ZcDdcgHt) baseDao.read("ZC_DDCG_HT._selectByPrimaryKey", code);
      baseDao.delete("AsFile.deleteAsFileById", ht.getZcConTextBlobid());
      baseDao.update("ZC_DDCG_HT._updateByPrimaryKey", zcDdcgHt);
    }

    return zcDdcgHt;
  }

}
