package com.ufgov.zc.server.zc.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdrive.workflow.context.WorkflowContext;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.ZcEbBulletinConstants;
import com.ufgov.zc.common.zc.model.Content;
import com.ufgov.zc.common.zc.model.DataExchangeRedo;
import com.ufgov.zc.common.zc.model.DeclarationContent;
import com.ufgov.zc.common.zc.model.MailInfo;
import com.ufgov.zc.common.zc.model.ZcEbApArticle;
import com.ufgov.zc.common.zc.model.ZcEbApArticlePortlet;
import com.ufgov.zc.common.zc.model.ZcEbBulletin;
import com.ufgov.zc.common.zc.model.ZcEbBulletinPack;
import com.ufgov.zc.common.zc.model.ZcEbPack;
import com.ufgov.zc.common.zc.model.ZcEbPackPlan;
import com.ufgov.zc.common.zc.model.ZcEbPlan;
import com.ufgov.zc.common.zc.model.ZcEbProj;
import com.ufgov.zc.common.zc.model.ZcEbProjZbFile;
import com.ufgov.zc.common.zc.model.ZcJingJiaModel;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.AsOptionUtil;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.webservice.zwdt.ZwdtWebServiceUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IDataExchangeDao;
import com.ufgov.zc.server.zc.dao.IZcEbBulletinDao;
import com.ufgov.zc.server.zc.service.IZcEbBulletinService;
import com.ufgov.zc.server.zc.service.IZcEbPlanService;
import com.ufgov.zc.server.zc.service.IZcEbProjService;
import com.ufgov.zc.server.zc.util.MailUtil;

public class ZcEbBulletinService implements IZcEbBulletinService {

  private IZcEbBulletinDao zcEbBulletinDao;

  private WFEngineAdapter wfEngineAdapter;

  private IWorkflowDao workflowDao;

  private static SimpleDateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  private static SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");

  private IBaseDao baseDao;

  private IDataExchangeDao dataExchangeDao;

  private IZcEbProjService zcEbProjService;

  private IZcEbPlanService zcEbPlanService;

  private boolean isDataChange = false;

  public IZcEbBulletinDao getZcEbBulletinDao() {
    return zcEbBulletinDao;
  }

  public void setZcEbBulletinDao(IZcEbBulletinDao zcEbBulletinDao) {
    this.zcEbBulletinDao = zcEbBulletinDao;
  }

  public IDataExchangeDao getDataExchangeDao() {
    return dataExchangeDao;
  }

  public void setDataExchangeDao(IDataExchangeDao dataExchangeDao) {
    this.dataExchangeDao = dataExchangeDao;
  }

  public List getZcEbBulletinBid(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinBid(dto);
  }

  public List getZcEbBulletinBidForExp(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinBidForExp(dto);
  }

  public List getZcEbBulletinBidDir(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinBidDir(dto);
  }

  public List getZcEbBulletinChg(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinChg(dto);
  }

  public List getZcEbBulletinChgDir(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinChgDir(dto);
  }

  public List getZcEbBulletinSpd(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinSpd(dto);
  }

  public List getZcEbBulletinSpdDir(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinSpdDir(dto);
  }

  public List getZcEbBulletinWid(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinWid(dto);
  }

  public List getZcEbBulletinWidDir(ElementConditionDto dto) {
    return zcEbBulletinDao.getZcEbBulletinWidDir(dto);
  }

  public int audit(ZcEbBulletin zcEbBulletin) {
    int rows = zcEbBulletinDao.updateSelectBulletin(zcEbBulletin);
    return rows;
  }

  public int delete(ZcEbBulletin zcEbBulletin) {
    int rows = zcEbBulletinDao.delete(zcEbBulletin);
    return rows;
  }

  public void newCommit(ZcEbBulletin zcEbBulletin, RequestMeta requestMeta) throws Exception {
    checkCanSend(zcEbBulletin);
    wfEngineAdapter.newCommit(zcEbBulletin.getComment(), zcEbBulletin, requestMeta);
  }

  public void commit(ZcEbBulletin zcEbBulletin, RequestMeta requestMeta) {
    wfEngineAdapter.commit(zcEbBulletin.getComment(), zcEbBulletin, requestMeta);
  }

  public void unAudit(ZcEbBulletin zcEbBulletin, RequestMeta requestMeta) {
    wfEngineAdapter.unAudit(zcEbBulletin.getComment(), zcEbBulletin, requestMeta);
  }

  public ZcEbBulletin insert(ZcEbBulletin zcEbBulletin, RequestMeta meta) {
    Long processId = createWfDraft(zcEbBulletin.getTitleField(), meta);
    zcEbBulletin.setProcessInstId(processId);
    if (zcEbBulletin.getPackList() != null) {
      for (int i = 0; i < zcEbBulletin.getPackList().size(); i++) {
        ZcEbBulletinPack pack = (ZcEbBulletinPack) zcEbBulletin.getPackList().get(i);
        pack.setBulletinId(zcEbBulletin.getBulletinID());
        baseDao.insert("ZcEbBulletin.insertBulletinPack", pack);
      }
    }
    zcEbBulletinDao.insert(zcEbBulletin);
    return zcEbBulletin ;
  }

  public int update(ZcEbBulletin zcEbBulletin, RequestMeta meta) {
    int rows = zcEbBulletinDao.updateSelectBulletin(zcEbBulletin);
    baseDao.delete("ZcEbBulletin.deleteBulletinPackByPrimaryKey", zcEbBulletin.getBulletinID());
    if (zcEbBulletin.getPackList() != null) {
      for (int i = 0; i < zcEbBulletin.getPackList().size(); i++) {
        ZcEbBulletinPack pack = (ZcEbBulletinPack) zcEbBulletin.getPackList().get(i);
        pack.setBulletinId(zcEbBulletin.getBulletinID());
        baseDao.insert("ZcEbBulletin.insertBulletinPack", pack);
      }
    }
    // if
    // (ZcEbBulletinConstants.TYPE_BULLETIN_JING_JIA_BID.equals(zcEbBulletin.getBulletinType()))
    // {//电子竞价招标公告
    // HashMap para = new HashMap();
    // para.put("projCode", zcEbBulletin.getProjCode());
    // para.put("zcXieYiEndDate",
    // zcEbBulletin.getZcPProMake().getZcXieYiEndDate());
    // this.baseDao.update("ZcEbBulletin.updateZcPProMakeEndDate", para);
    // } else if
    // (ZcEbBulletinConstants.TYPE_BULLETIN_BID.equals(zcEbBulletin.getBulletinType())
    // ||
    // ZcEbBulletinConstants.TYPE_BULLETIN_XUN_JIA_BID.equals(zcEbBulletin.getBulletinType()))
    // {
    // //招标公告或者询价招标公告
    // HashMap para = new HashMap();
    // para.put("projCode", zcEbBulletin.getProjCode());
    // para.put("planBidEndDate",
    // zcEbBulletin.getZcPProMake().getZcXieYiEndDate());
    // this.baseDao.update("ZcEbBulletin.updateZcEbPlanBidEndDate", para);
    // }
    // if
    // (ZcEbBulletinConstants.STATUS_BULLETIN_BID_PUBLISHED.equals(zcEbBulletin.getBulletinStatus())
    // &&
    // (ZcEbBulletinConstants.TYPE_BULLETIN_JING_JIA_BID.equals(zcEbBulletin.getBulletinType())
    // || ZcEbBulletinConstants.TYPE_BULLETIN_JING_JIA_WID
    // .equals(zcEbBulletin.getBulletinType()))) {//发布操作，且是电子竞价的招标或者成交
    // createExchangeDataForJingjia(zcEbBulletin, meta);
    // return rows;
    // }
    // createExchangeData(zcEbBulletin, meta);
    return rows;
  }

  private void createExchangeDataForJingjia(ZcEbBulletin zcEbBulletin, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    String title = zcEbBulletin.getProjCode() + ".ztb";
    String href = AsOptionUtil.getInstance().getOptionVal("OPT_ZC_PORTAL_HREF_PATH") + zcEbBulletin.getFileID() + ZcSettingConstants.HTML_FILE_SUFFIX;
    String pletIDs = AsOptionUtil.getInstance().getOptionVal("OPT_ZC_PLET_BID");
    ZcEbApArticle arc = insertApArticle(title, meta.getSvUserName(), href);
    insertApArticlePortlet(String.valueOf(arc.getArticleID()), pletIDs);

    DataExchangeRedo redo = new DataExchangeRedo();
    if ("frelease".equals(meta.getFuncId())) {
      redo.setRecordName("发布公告");
    } else {
      return;
    }
    redo.setDataTypeID(meta.getCompoId());
    redo.setDataTypeName("公告管理");
    redo.setRecordID(zcEbBulletin.getProjCode() + "@" + arc.getArticleID());
    redo.setMasterTableName("ZC_EB_BULLETIN");
    redo.setIsExported("0");
    redo.setGenerateDate(new Date());
    redo.setOperateType(meta.getFuncId());
    dataExchangeDao.deleteByRecordIDAndDataTypeID(redo);
    dataExchangeDao.saveRedo(redo);
  }

  /**
   * 删除掉同一栏目中相同title的文章
   * 
   * @param title
   * @param pletIDs
   */
  private void deleteHistorySame(String title, String pletID) {
    List artList = getArticleIDWithPletID(pletID);
    artList = getArticleIDWithTitleInIDs(title, artList);
    if (artList.size() > 0) {
      deleteArticleWithIDs(artList);
      deleteArticlePletWithIDs(artList, pletID);
    }
  }

  /**
   * 根据栏目的id获得所有的栏目下的文章id
   * 
   * @param pletIDs
   * @return id1,id2,id3,....
   */
  private List getArticleIDWithPletID(String pletID) {
    List ids = new ArrayList();
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1(pletID);
    ZcEbApArticlePortlet ap = null;
    List list = this.baseDao.query("ZcEbApArticlePortlet.queryArticlesByPortletID", dto);
    for (int i = 0; i < list.size(); i++) {
      ap = (ZcEbApArticlePortlet) list.get(i);
      ids.add(ap.getArticleID());
    }
    return ids;
  }

  private List getArticleIDWithTitleInIDs(String title, List articleIDs) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1(title);
    dto.setPmAdjustCodeList(articleIDs);
    ZcEbApArticle art = null;
    List buff = new ArrayList();
    List list = this.baseDao.query("ZcEbApArticle.getZcEbApArticlesSimple", dto);
    for (int i = 0; i < list.size(); i++) {
      art = (ZcEbApArticle) list.get(i);
      buff.add("" + art.getArticleID());
    }

    return buff;
  }

  /**
   * 根据文章id删除指定的文章
   * 
   * @param ids
   */
  private void deleteArticleWithIDs(List ids) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setPmAdjustCodeList(ids);
    this.baseDao.delete("ZcEbApArticle.deleteArticleByIDs", dto);
  }

  /**
   * 根据栏目id和文章id删除栏目和文章的关联表
   * 
   * @param artIDs
   * @param pletIDs
   */
  private void deleteArticlePletWithIDs(List artIDs, String pletID) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setPmAdjustCodeList(artIDs);
    dto.setDattr2(pletID);
    this.baseDao.delete("ZcEbApArticlePortlet.deleteByArticleAndPortletID", dto);
  }

  public boolean pubBulletin(String title, String creator, String href, String pletIDs, RequestMeta meta, Object o) {
    this.deleteHistorySame(title, pletIDs);
    ZcEbApArticle arc = insertApArticle(title, creator, href);
    insertApArticlePortlet(String.valueOf(arc.getArticleID()), pletIDs);
    importTransData(o, meta);
    return true;
  }

  public ZcEbApArticle insertApArticle(String title, String creator, String href) {
    Date date = new Date();
    String dateTime = dfDateTime.format(date);
    String strDate = dfDate.format(date);
    ZcEbApArticle art = new ZcEbApArticle();
    art.setTitle(title);
    art.setCreator(creator);
    art.setCreateTime(Timestamp.valueOf(dateTime));
    art.setType("01");
    art.setAttachBlobID("0");
    art.setWroteTime(java.sql.Date.valueOf(strDate));
    art.setMendor(creator);
    art.setMendTime(Timestamp.valueOf(dateTime));
    art.setHref(href);
    art.setVisitCapacity(1);
    this.baseDao.insert("ZcEbApArticle.insertApArticle", art);
    return art;
  }

  public ZcEbApArticlePortlet insertApArticlePortlet(String articleID, String pletIDs) {
    Date date = new Date();
    String dateTime = dfDateTime.format(date);
    ZcEbApArticlePortlet plet = new ZcEbApArticlePortlet();
    plet.setArticleID(articleID);
    plet.setPgPletID(pletIDs);
    plet.setPortletType(null);
    plet.setPubTime(Timestamp.valueOf(dateTime));
    this.baseDao.insert("ZcEbApArticlePortlet.insertArticlePortlet", plet);
    return plet;
  }

  public ZcEbProjZbFile getZcEbProjZbFile(String projCode, String type) {
    return zcEbBulletinDao.getZcEbProjZbFile(projCode, type);
  }

  private Long createWfDraft(String title, RequestMeta meta) {
    Long draftid = workflowDao.createDraftId();
    // zcEbBulletin.setProcessInstId(draftid);

    AsWfDraft asWfDraft = new AsWfDraft();
    asWfDraft.setCompoId(meta.getCompoId());
    asWfDraft.setWfDraftName(title);// zcEbBulletin.getTitleField());
    asWfDraft.setUserId(meta.getSvUserID());
    asWfDraft.setMasterTabId(meta.getCompoId());
    asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

    workflowDao.insertAsWfdraft(asWfDraft);
    return draftid;
  }

  public void createExchangeData(ZcEbBulletin tin, RequestMeta meta) {
    if (isDataChange) {
      return;
    }
    DataExchangeRedo redo = new DataExchangeRedo();
    if ("frelease".equals(meta.getFuncId())) {
      redo.setRecordName("发布公告");
    } else {
      return;
    }
    redo.setDataTypeID(meta.getCompoId());
    redo.setDataTypeName("公告管理");
    redo.setRecordID(tin.getBulletinID());
    redo.setMasterTableName("ZC_EB_BULLETIN");
    redo.setIsExported("0");
    redo.setGenerateDate(new Date());
    redo.setOperateType(meta.getFuncId());
    dataExchangeDao.deleteByRecordIDAndDataTypeID(redo);
    dataExchangeDao.saveRedo(redo);
  }

  public ZcEbBulletin readZcEbBulletinById(String tinId) {
    Map map = new HashMap();
    map.put("BULLETIN_ID", tinId);
    return (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
  }

  public List findTransData(ElementConditionDto dto, RequestMeta meta) {
    List list = new ArrayList();
    if (dto.getPmAdjustCodeList() == null) {
      return list;
    }
    for (int i = 0; i < dto.getPmAdjustCodeList().size(); i++) {
      DataExchangeRedo redo = (DataExchangeRedo) dto.getPmAdjustCodeList().get(i);
      String id = redo.getRecordID();
      ZcEbBulletin tin = readZcEbBulletinById(id);
      ZcEbProj p = (ZcEbProj) zcEbProjService.readByProjCode(tin.getProjCode());
      tin.setZcEbProj(p);
      dto.setProjCode(p.getProjCode());
      List temp = zcEbPlanService.getZcEbPlan(dto, null);
      ZcEbPlan zcEbPlan = null;
      if (temp != null && temp.size() > 0) {
        zcEbPlan = (ZcEbPlan) temp.get(0);
      }

      tin.setZcEbPlan(zcEbPlan);
      list.add(tin);
    }
    return list;
  }

  public String importTransData(Object o, RequestMeta meta) {
    this.isDataChange = true;
    ZcEbBulletin tin = (ZcEbBulletin) o;
    zcEbProjService.delete(tin.getProjCode());
    delete(tin);
    zcEbProjService.insertZcEbProjHasProjCode(tin.getZcEbProj());
    zcEbBulletinDao.insert(tin);
    return null;
  }

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

  public IZcEbProjService getZcEbProjService() {
    return zcEbProjService;
  }

  public void setZcEbProjService(IZcEbProjService zcEbProjService) {
    this.zcEbProjService = zcEbProjService;
  }

  public IZcEbPlanService getZcEbPlanService() {
    return zcEbPlanService;
  }

  public void setZcEbPlanService(IZcEbPlanService zcEbPlanService) {
    this.zcEbPlanService = zcEbPlanService;
  }

  public void interruptZcPProMake(ZcEbBulletin make, RequestMeta requestMeta) {
    wfEngineAdapter.interrupt(null, make, requestMeta);
  }

  public ZcEbBulletin CancelMake(ZcEbBulletin currentObject, RequestMeta requestMeta) {
    interruptZcPProMake(currentObject, requestMeta);
    this.update(currentObject, requestMeta);
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", currentObject.getBulletinID());
    ZcEbBulletin bu = (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
    return bu;
  }

  public ZcEbBulletin auditFN(ZcEbBulletin bulletin, RequestMeta requestMeta) throws Exception {
    checkCanSend(bulletin);
    zcEbBulletinDao.updateSelectBulletin(bulletin);

    wfEngineAdapter.commit(bulletin.getComment(), bulletin, requestMeta);

    return getZcEbBulletinById(bulletin.getBulletinID());
  }

  private void checkCanSend(ZcEbBulletin bulletin) throws Exception {
//    if (!"B".equals(bulletin.getBulletinType())) {
//      return;
//    }
    Integer temp = (Integer) baseDao.read("ZcEbBulletin.checkCanSend", bulletin.getProjCode());
    int temp2 = temp == null ? 0 : temp.intValue();
    int count = temp2;
    if (count > 0) {
      throw new Exception("招标文件未终审，不能进行该操作");
    }
  }

  private ZcEbBulletin getZcEbBulletinById(String id) {
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", id);
    ZcEbBulletin bu = (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
    return bu;
  }

  private String getCgcfzr(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    ZcPProMake make = getZcPProMakeByBulletin(bulletin);
    if (make != null) {
      String val = AsOptionUtil.getInstance().getOptionVal("OPT_HT_AUDIT_TO_CGC_ROLE_STRING");
      HashMap para = new HashMap();
      para.put("CO_CODE", make.getCoCode());
      para.put("ND", String.valueOf(requestMeta.getSvNd()));
      para.put("csbh", make.getOrgCode());
      para.put("ysdwfz", val);
      String cgcfzr = (String) this.baseDao.read("ZC_XMCG_HT.getCaiGouChuFuZeRenNameForXmcg", para);// 采购处
      // 负责人user_id
      return cgcfzr;
    }
    return null;
  }

  public ZcPProMake getZcPProMakeByBulletin(ZcEbBulletin bulletin) {
    List list = null;
    if ("JB".equals(bulletin.getBulletinType()) || "JW".equals(bulletin.getBulletinType())) {// 如果是竞价的招标
      ZcPProMake make = new ZcPProMake();
      make.setZcMakeCode(bulletin.getProjCode());
      list = this.baseDao.query("ZC_P_PRO_MAKE.ibatorgenerated_selectByPrimaryKey", make);
      if (list != null && list.size() > 0) {
        return (ZcPProMake) list.get(0);
      }
    } else {
      ElementConditionDto con = new ElementConditionDto();
      con.setZcText1(bulletin.getProjCode());
      list = this.baseDao.query("ZcEbProj.getZcEbPackByEntrustCode", con);
      if (list != null && list.size() > 0) {
        ZcEbPack pack = (ZcEbPack) list.get(0);
        ZcPProMake make = new ZcPProMake();
        if (pack.getEntrust() != null) {
          make.setCoCode(pack.getEntrust().getCoCode());
          make.setOrgCode(pack.getEntrust().getOrgCode());
          return make;
        }
      }
    }

    return null;
  }

  public ZcEbBulletin callbackFN(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    wfEngineAdapter.callback(bulletin.getComment(), bulletin, requestMeta);
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", bulletin.getBulletinID());
    ZcEbBulletin bu = (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
    return bu;
  }

  public ZcEbBulletin unAuditFN(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    wfEngineAdapter.rework(bulletin.getComment(), bulletin, requestMeta);
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", bulletin.getBulletinID());
    ZcEbBulletin bu = (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
    return bu;
  }

  public ZcEbBulletin untreadFN(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    wfEngineAdapter.untread(bulletin.getComment(), bulletin, requestMeta);
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", bulletin.getBulletinID());
    ZcEbBulletin bu = (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
    return bu;
  }

  private void commitToZxjb(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    String zxjbr = getZxjbr(bulletin);// 获取采购中心经办人，负责人
    WorkflowContext workflowContext = wfEngineAdapter.genCommonWFC(bulletin.getComment(), bulletin, requestMeta);
    List result = new ArrayList();
    result.add(zxjbr);
    workflowContext.setNextExecutor(result);
    wfEngineAdapter.commit(workflowContext);
  }

  private String getZxjbr(ZcEbBulletin bulletin) {
    // TCJLODO Auto-generated method stub
    // ZcEbProtocol p = (ZcEbProtocol)
    // this.baseDao.read("ZcEbProtocol.getZcEbProtocolByProtCode",
    // protCode);
    String userId = (String) baseDao.read("ZcEbRequirement.getNodeCgzxUserId", bulletin.getProcessInstId());
    return userId;
  }

  public List getZcEbBulletinList(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    return zcEbBulletinDao.getZcEbBulletinList(elementConditionDto);
  }

  public List getZcEbBulletinProjList(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    // elementConditionDto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(
    // elementConditionDto.getWfcompoId(), NumLimConstants.FWATCH));
    return zcEbBulletinDao.getZcEbBulletinProjList(elementConditionDto);
  }

  public ZcEbBulletin sendRecord(ZcEbBulletin bulletin, RequestMeta requestMeta) {
    String cgcfzr = getCgcfzr(bulletin, requestMeta);
    if (cgcfzr == null || "".equals(cgcfzr)) {
      throw new RuntimeException("不能确认此项目，对应采购处负责人！");
    }
    WorkflowContext workflowContext = wfEngineAdapter.genCommonWFC(bulletin.getComment(), bulletin, requestMeta);
    List result = new ArrayList();
    result.add(cgcfzr);
    workflowContext.setNextExecutor(result);
    wfEngineAdapter.commit(workflowContext);
    return getZcEbBulletinById(bulletin.getBulletinID());
  }

  public ZcEbBulletin publishBulletinFN(ZcEbBulletin tin, String dir, Map option, RequestMeta meta) throws Exception {
    // 更新外网网站
//    System.out.println("公告开始更新外网开始");
    addOut(tin,meta);
//    System.out.println("公告更新外网结束");

    // 配置数据
//    final MailInfo mailInfo = new MailInfo();
//    String server = "";
//    String comp = "";
//    String pass = "";
//
//    mailInfo.setMailServerHost(option.get("OPT_ZC_MAIL_FROM_POP").toString());
//    mailInfo.setMailServerPort(option.get("OPT_ZC_MAIL_FROM_PORT").toString());
//    mailInfo.setUserName(option.get("OPT_ZC_MAIL_FROM_USER").toString());
//    mailInfo.setFromAddress(option.get("OPT_ZC_MAIL_FROM_USER").toString());
//    mailInfo.setPassword(option.get("OPT_ZC_MAIL_FROM_PASS").toString());
//    if (tin.getCgtype() != null && ("1".equals(tin.getCgtype()) || "2".equals(tin.getCgtype()))) {
//      mailInfo.setToAddress(option.get("OPT_ZC_MAIL_TO_GKYQ_USER").toString());
//    } else {
//      mailInfo.setToAddress(option.get("OPT_ZC_MAIL_TO_USER").toString());
//    }
//    mailInfo.setSubject(option.get("OPT_ZC_MAIL_TITLE") + tin.getBulname());
//    server = option.get("OPT_ZC_MAIL_INTERFACE_SERVER").toString();
//    comp = option.get("OPT_ZC_MAIL_INTERFACE_COMP").toString();
//    pass = option.get("OPT_ZC_MAIL_INTERFACE_PASS").toString();

    // 更新公告内容
//    System.out.println("公告开始更新外网开始aa");
    baseDao.update("ZcEbBulletin.updateByPrimaryKeySelective", tin);
//    System.out.println("公告开始更新外网开始bb");
    if (ZcEbBulletin.ZHAOBIAO_DYLY.equals(tin.getBulletinType())) {
      // baseDao.update("ZcEbBulletin.updateZcEbPlanBidEndDate", tin);
      ZcEbPackPlan pp = new ZcEbPackPlan();
      pp.setProjCode(tin.getProjCode());
      pp.setBidEndTime(tin.getOpenBidTime());
      pp.setOpenBidTime(tin.getOpenBidTime());
      pp.setSellEndTime(tin.getFailureDate());
//      System.out.println("公告开始更新外网开始cc");
      baseDao.insert("ZcEbBulletin.insertDlyBidInfo", tin.getBulletinID());
//      System.out.println("公告开始更新外网开始dd");
      for (int i = 0; i < tin.getPackList().size(); i++) {
        ZcEbBulletinPack bp = (ZcEbBulletinPack) tin.getPackList().get(i);
        pp.setPackCode(bp.getPackCode());
//        System.out.println("公告开始更新外网开始ee");
        baseDao.delete("ZcEbBulletin.deletePackPlanByPrimaryKey", pp);
//        System.out.println("公告开始更新外网开始ff");
        baseDao.insert("ZcEbBulletin.insertPackPlan", pp);
//        System.out.println("公告开始更新外网开始gg");
      }
    }

    System.out.println("公告开始更新外网开始hh");
    baseDao.update("ZC_WCMS_CONTENT.updateDeclarationContent", tin);
    System.out.println("公告开始更新外网开始kk");
    
    // 调用用政务大厅接口
//    sendBulletinToZWDT(tin, option, server, comp, pass);

    // 送审
    wfEngineAdapter.commit(tin.getComment(), tin, meta);
    
    //延期、变更公告时，给报名供应商发邮件通知
//    String content = tin.getFile().toString();
//    sendMailToSupplier(tin, mailInfo, content);

    // 取最新值
    HashMap map = new HashMap();
    map.put("BULLETIN_ID", tin.getBulletinID());
    return (ZcEbBulletin) baseDao.read("ZcEbBulletin.readBulletinById", map);
  }

  /**
   * 调用用政务大厅接口
   * @param tin
   * @param option
   * @param server
   * @param comp
   * @param pass
   * @throws RuntimeException
   * @throws Exception
   * @throws UnsupportedEncodingException
   * Administrator 
   * Jul 15, 2013
   */
  private void sendBulletinToZWDT(ZcEbBulletin tin, Map option, String server, String comp, String pass) throws RuntimeException, Exception,
    UnsupportedEncodingException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
    String code = option.get("C-" + tin.getBulletinType()).toString();
    if (code == null || "".equals(code)) {
      code = option.get("C-all").toString();
    }

    String time = option.get("T-" + tin.getBulletinType()).toString();
    if (time == null || "".equals(time) || time.split(":").length != 2) {
      time = option.get("T-all").toString();
    }
    Calendar c = Calendar.getInstance();
    try {
      String[] str = time.split(":");
      c.add(Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()));
    } catch (Exception e) {
      throw new RuntimeException("配置政务大厅发布公告有效期出错！");
    }
    String result = ZwdtWebServiceUtil.callZWDTService(server, comp, pass, code, new String(tin.getBulname().getBytes("utf-8"), "utf-8"), tin
      .getFile().toString(), df.format(new Date()), df.format(c.getTime()), "", "", "");
    if (!"0".equals(result)) {

      throw new RuntimeException("调用政务大厅接口出错！");
    }
  }

  /**
   * 延期、变更公告时，给报名供应商发邮件通知
   * @param tin
   * @param mailInfo
   * @param content
   * Administrator 
   * Jul 15, 2013
   */
  private void sendMailToSupplier(ZcEbBulletin tin, final MailInfo mailInfo, String content) {
    // 延期、变更公告时，给报名供应商发邮件通知

    if ("C".equals(tin.getBulletinType()) || "D".equals(tin.getBulletinType())) {
      Map bull = new HashMap();
      if ("C".equals(tin.getBulletinType())) {
        bull.put("projCode", tin.getProjectCode());
        bull.put("packCode", "'" + tin.getProjCode() + "'");
      } else {
        bull.put("projCode", tin.getProjCode());
        StringBuffer sb = new StringBuffer("' '");
        for (int i = 0; i < tin.getPackList().size(); i++) {
          ZcEbBulletinPack pack = (ZcEbBulletinPack) tin.getPackList().get(i);
          sb.append(",'").append(pack.getPackCode()).append("'");
        }
        bull.put("packCode", sb.toString());
      }
      List mail = baseDao.query("ZC_WCMS_CONTENT.getSuMail", bull);
      if (mail != null) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mail.size(); i++) {
          if (mail.get(i) != null && !"".equals(mail.get(i))) {
            sb.append(",").append(mail.get(i));
          }
        }
        mailInfo.setToAddress(mailInfo.getToAddress() + sb.toString());
      }
    }

    mailInfo.setValidate(true);
    mailInfo.setContent(content);

    // 这个类主要来发送邮件
    new Thread() {
      public void run() {
        MailUtil m = new MailUtil();
        m.sendHtmlMail(mailInfo);
      }
    }.start();
  }

  private void addOut(ZcEbBulletin zcEbBulletin, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
//    System.out.println("公告开始更新外网开始11");
    DeclarationContent dc = new DeclarationContent();
    dc.setID(zcEbBulletin.getBulletinID());
    dc.setDeclarationType(getBulType(zcEbBulletin.getBulletinType()));
    if (ZcEbBulletin.BIANGENG.equals(zcEbBulletin.getBulletinType())) {
      dc.setProjCode(zcEbBulletin.getProjectCode());
    } else {
      dc.setProjCode(zcEbBulletin.getProjCode());
    }
//    System.out.println("公告开始更新外网开始22");
    dc.setDownloadInfo(zcEbBulletin.getDownLoad());
    List list = baseDao.query("ZC_WCMS_CONTENT.getBulInfo", dc.getProjCode());
    if (list != null && list.size() > 0) {
      DeclarationContent dcs = (DeclarationContent) list.get(0);
      dc.setBidType(dcs.getBidType());
      dc.setBidWay(dcs.getBidWay());
      dc.setOpenBidAddress(dcs.getOpenBidAddress());
      dc.setOpenBidDate(dcs.getOpenBidDate());
      dc.setProjName(dcs.getProjName());
      dc.setOrgName(dcs.getOrgName());
      dc.setExecutorName(dcs.getExecutorName());
      dc.setRegion("220000");
//      dc.setRegion(EleRegionUsingUtil.getInstance().getTopEleRegionCode());
      zcEbBulletin.setCgtype(dcs.getBidMenu());
      zcEbBulletin.setBulname(dcs.getDeclarationType());
    }
//    System.out.println("公告开始更新外网开始33");
    if (zcEbBulletin.getBulname() == null || "".equals(zcEbBulletin.getBulname())) {
      zcEbBulletin.setBulname(zcEbBulletin.getProjName());
    }

    Content c = new Content();
    c.setTitle(zcEbBulletin.getBulname());
    c.setType("DECLARATION");
    c.setIsImport("Y");
    c.setAuthor(zcEbBulletin.getExecutor());
//    System.out.println("=====公告内容====\n"+zcEbBulletin.getFile());
    c.setContent(zcEbBulletin.getFile().toString());
    c.setCreateDate(zcEbBulletin.getExecuteDate());
    // c.setExpireDate(zcEbBulletin.getFailureDate());
    c.setID(zcEbBulletin.getBulletinID());
    // c.setValidDate(zcEbBulletin.getEffectiveDate());
//    System.out.println("公告开始更新外网开始44");
    baseDao.insert("ZC_WCMS_CONTENT.insertContent", c);
//    System.out.println("公告开始更新外网开始55");
    baseDao.insert("ZC_WCMS_CONTENT.insertDeclarationContent", dc);
//    System.out.println("公告开始更新外网开始66");
  }

  private String getBulType(String str) {
    // ZQYJGG("征求意见公告"),
    if ("O".equals(str)) {
      return "ZQYJGG";
    }
    // GSGG("公示公告"),
    if ("GS".equals(str)) {
      return "GSGG";
    }
    // ZGYSGG("资格预审公告"),
    if ("ZGYS".equals(str)) {
      return "ZGYSGG";
    }
    // ZHAOBGG("招标公告"),
    // CGGG("采购公告"),
    if (ZcEbBulletin.ZHAOBIAO_GKZB.equals(str) 
      ||ZcEbBulletin.ZHAOBIAO_YQZB.equals(str) 
      ||ZcEbBulletin.ZHAOBIAO_JZXTP.equals(str)
      ||ZcEbBulletin.ZHAOBIAO_XJ.equals(str)
      ||ZcEbBulletin.ZHAOBIAO_DYLY.equals(str) 
      ||ZcEbBulletin.ZHAOBIAO_QT.equals(str)) {
      return "ZHAOBGG";
    }
    // XJGG("询价公告"),
   /* if (ZcEbBulletin.ZHAOBIAO_XJ.equals(str) ) {
      return "XJGG";
    }*/
    // BGGG("变更公告"),
    if ("C".equals(str) || "D".equals(str)) {
      return "BGGG";
    }

    // YZBGS("预中标公示"),
    if ("YZB".equals(str)) {
      return "YZBGS";
    }
    // ZHONGBGG("中标公告"),
    // CJGG("成交公告"),
    // JGGG("结果公告");
    if (ZcEbBulletin.ZHONGBIAO_GKZB.equals(str)
      ||ZcEbBulletin.ZHONGBIAO_YQZB.equals(str)
      ||ZcEbBulletin.ZHONGBIAO_JZXTP.equals(str)
      ||ZcEbBulletin.ZHONGBIAO_XJ.equals(str)
      ||ZcEbBulletin.ZHONGBIAO_DYLY.equals(str)
      ||ZcEbBulletin.ZHONGBIAO_QT.equals(str)) {
      return "ZHONGBGG";
    }
    return str;
  }
  public ZcEbBulletin getZcEbBulletinByKey(String key, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    Map map = new HashMap();
    map.put("BULLETIN_ID", key);
    List list = baseDao.query("ZcEbBulletin.readBulletinById", map);
    if (list == null || list.size() == 0) {
      return new ZcEbBulletin();
    }

    ZcEbBulletin tin = (ZcEbBulletin) list.get(0);
//    tin.setRoleCode((String) baseDao.read("AsRole.getRoleCodeById", meta.getSvRoleId()));
    if ("C".equals(tin.getBulletinType())) {
      Map map1 = new HashMap();
      map1.put("projCode", tin.getProjectCode());// 变更公告，projectCode存放的是zc_Eb_proj表中的proj_code的值
      map1.put("packCode", tin.getProjCode());// 变更公告，projectCode存放的是zc_Eb_pack表中的pack_code的值
      ZcEbPack pack = (ZcEbPack) baseDao.read("ZcEbProj.getZcEbPackByPackCode", map1);
      tin.setPackName(pack.getPackName());
      ZcEbPackPlan zcEbPackPlan = (ZcEbPackPlan) baseDao.read("ZcEbPlan.getZcEbPackPlanByMap", map1);
      if (zcEbPackPlan != null) {
        tin.setPackPlan(zcEbPackPlan);
      }
      ZcEbProj zcEbProj = (ZcEbProj) baseDao.read("ZcEbProj.getOriginalZcEbProjByProjCode", tin.getProjectCode());

      Date publishDate = (Date) baseDao.read("ZcEbBulletin.selectGgPubListDate", tin.getProjectCode());

      if (publishDate != null) {

        tin.setBullPublishDate(publishDate);
      }

      tin.setZcEbProj(zcEbProj);
    } else {
      ZcEbPlan zcEbPlan = (ZcEbPlan) baseDao.read("ZcEbPlan.getZcEbPlanByProjCode", tin.getProjCode());
      if (zcEbPlan != null) {
        tin.setZcEbPlan(zcEbPlan);
      }
      if (ZcEbBulletinConstants.TYPE_BULLETIN_DLY.equals(tin.getBulletinType())) {
        tin.setPackList(baseDao.query("ZcEbBulletin.getZcEbBulletinPack", tin));
      }
    }
    return tin;
  }

  public void updateIsExtrac(ZcEbBulletin bulletin) {
    // TCJLODO Auto-generated method stub
    baseDao.update("ZcEbBulletin.updateIsExtrac", bulletin);
  }

  public String getRoleCodeById(String id) {
    // TCJLODO Auto-generated method stub
    return (String) baseDao.read("AsRole.getRoleCodeById", id);
  }

  public ZcJingJiaModel getZcJingJiaModel(String projCode, String fileId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub

    ZcJingJiaModel model = (ZcJingJiaModel) baseDao.read("ZcEbBulletin.getJingjiaModelsByProjCode", projCode);
    if (model == null) {
      model = new ZcJingJiaModel();
    }

    model.setYear((String) baseDao.read("ZcEbBulletin.selectGhDate", projCode));

    AsFile template = (AsFile) baseDao.read("ZC_XMCG_HT.getContractTemplateContent", fileId);
    if (template != null) {
      model.setFile(template);
    } else {
      model.setFile(new AsFile());
    }

    return model;
  }
}
