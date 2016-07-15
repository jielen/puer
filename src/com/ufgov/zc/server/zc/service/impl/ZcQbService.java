package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.budget.model.VwBudgetGp;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.exception.ZcPayInterfaceException;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.common.zc.model.ZcQb;
import com.ufgov.zc.common.zc.model.ZcQbBi;
import com.ufgov.zc.common.zc.model.ZcQbItem;
import com.ufgov.zc.server.budget.util.BudgetUtil;
import com.ufgov.zc.server.payInterface.util.PayForZcUtil;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IZcQbDao;
import com.ufgov.zc.server.zc.service.IZcQbService;

public class ZcQbService implements IZcQbService {

  private IBaseDao baseDao;

  private IWorkflowDao workflowDao;

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  private IZcQbDao qbDao;
  
  private WFEngineAdapter wfEngineAdapter;

  public List getQbLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    List list = qbDao.getQbLst(elementConditionDto);

    ZcSUtil.setBillDBDigest(list);

    return list;
  }

  public void cancelFn(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQb unAuditFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  public ZcQb untreadFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qb.getComment(), qb, requestMeta);

    return qb;
  }

  public ZcQb auditFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qb=updateFN(qb, requestMeta);
    wfEngineAdapter.commit(qb.getComment(), qb, requestMeta);

    return qb;
  }

  public ZcQb updateFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub

    System.out.println("111="+qb.getCoCode()+qb.getCoName());
    String code = "";

    String temp_code = "";

    List biList = qb.getBiList();

    String userId = requestMeta.getSvUserID();

    String compoId = requestMeta.getCompoId();

    boolean isDraft = false;

    if (qb.getProcessInstId() == null || qb.getProcessInstId().longValue() == -1) {

      Long draftid = workflowDao.createDraftId();

      qb.setProcessInstId(draftid);

      isDraft = true;

    }

    Map map = null;

    if ("".equals(ZcSUtil.safeString(qb.getQbCode())) || qb.getQbCode().equals("自动编号")) {

     
      // 当新建项目的时候，项目编码不进行正式编码，首先创建一个临时编码，等提交送审之后，进行正式编码

      temp_code = NumUtil.getInstance().getNo("ZC_EB_QX", "QX_CODE", qb);

      code = temp_code;

      qb.setQbCode(code);

      // add by wangkewei end

      

      map = new BudgetUtil().getSaveBudgetByZcQb(qbDao, baseDao, ZcSUtil.isUseBi(), qb, biList);
      
      BigDecimal biSum=new BigDecimal(0);

      for (int i = 0; i < biList.size(); i++) {

        ZcQbBi bi = (ZcQbBi) biList.get(i);

        bi.setQbCode(code);
        if(bi.getZcBiNo()!=null && bi.getZcBiNo().trim().length()>0){
          biSum=biSum.add(bi.getZcBiJhuaSum());
        }
      }
      qb.setBiSum(biSum);
      
      for (int i = 0; i < qb.getItemList().size(); i++) {

        ZcQbItem item = (ZcQbItem) qb.getItemList().get(i);

        item.setQbCode(code);
      }

//      System.out.println("222="+qb.getCoCode()+qb.getCoName());
      qbDao.insert(qb);
//      System.out.println("333="+qb.getCoCode()+qb.getCoName());
      qbDao.insertBiLst(biList);
      qbDao.insertItemLst(qb.getItemList());

//      System.out.println("444="+qb.getCoCode()+qb.getCoName());
    } else {

      code = qb.getQbCode();

      // ZcPProMake originalBean = this.selectByPrimaryKey(code);
      //

      // ZcSUtil.checkDigest(qb, originalBean,
      // "zcMakeCode");//一致性校验
      
      BigDecimal biSum=new BigDecimal(0);

      for (int i = 0; i < biList.size(); i++) {

        ZcQbBi bi = (ZcQbBi) biList.get(i);

        bi.setQbCode(code);
        if(bi.getZcBiNo()!=null && bi.getZcBiNo().trim().length()>0){
          biSum=biSum.add(bi.getZcBiJhuaSum());
        }
      }
//      System.out.println("555="+qb.getCoCode()+qb.getCoName());
      qb.setBiSum(biSum);
      qbDao.update(qb);

//      System.out.println("666="+qb.getCoCode()+qb.getCoName());
      map = new BudgetUtil().getSaveBudgetByZcQb(qbDao, baseDao, ZcSUtil.isUseBi(), qb, biList);

      qbDao.deleteBiByQbCode(qb.getQbCode());

      qbDao.deleteItemByQbCode(qb.getQbCode());

      for (int i = 0; i < biList.size(); i++) {

        ZcQbBi mbi = (ZcQbBi) biList.get(i);

        mbi.setQbCode(code);
      }
      qbDao.insertBiLst(biList);
      
      
      for (int i = 0; i < qb.getItemList().size(); i++) {

        ZcQbItem item = (ZcQbItem)  qb.getItemList().get(i);

        item.setQbCode(code);
      }
      qbDao.insertItemLst(qb.getItemList());
      

    }

    if (isDraft) {

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(qb.getQbCode());

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(qb.getProcessInstId().longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

    }

    new BudgetUtil().callService(map,requestMeta.getSvNd());

    System.out.println("777="+qb.getCoCode()+qb.getCoName());
    return qb;
  }

  public void commitFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public void deleteListFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public void deleteFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQb selectByPrimaryKey(String qbCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    ZcQb qb = this.qbDao.selectByPrimaryKey(qbCode);
    qb.setBiList(getBiLst(qbCode));
    qb.setItemList(getItemLst(qbCode));
    
    qb.setDbDigest(null);
    qb.setDbDigest(qb.digest());// 统一入口

    return qb;
  }

  private List getItemLst(String qbCode) {
    // TCJLODO Auto-generated method stub
    return this.qbDao.getItemLst(qbCode);
  }

  private List getBiLst(String qbCode) {
    // TCJLODO Auto-generated method stub
    List biLst = this.qbDao.getQbBiLst(qbCode);
    if(!isUseBudget()){
      return biLst;
    }
    String ids = "";
    for (int i = 0; i < biLst.size(); i++) {
      ZcQbBi bi = (ZcQbBi) biLst.get(i);
      if (bi.getZcBiNo() != null && bi.getZcBiNo().trim().length() > 0) {
        if (ids.length() == 0) {
          ids = "'" + bi.getZcBiNo() + "'";
        } else {
          ids = ids + ",'" + bi.getZcBiNo() + "'";
        }
      }
    }
    if(ids.trim().length()>0){
      List yuanBiLst=this.baseDao.query("VwBudgetGp.getVwBudgetGpByBiNoLst", ids);
      if(yuanBiLst==null){
        throw new RuntimeException("获取以下指标信息报错:"+ids);
      }
      for(int i=0;i<biLst.size();i++){
        ZcQbBi bi = (ZcQbBi) biLst.get(i);
        for(int j=0;j<yuanBiLst.size();j++){
          VwBudgetGp gp=(VwBudgetGp)yuanBiLst.get(j);
          if(bi.getZcBiNo()!=null && bi.getZcBiNo().equals(""+gp.getSumId())){
            //将指标的现有可用金额+当前计划占用的指标金额，作为当前指标的可用金额，这样保险单保存后，再修改，被本保险单占用的指标应该属于可用指标额度范围内，
            bi.setZcBiDoSum(gp.getCanuseMoney().add(bi.getZcBiJhuaSum()));
          }
        }
        
      }
    }
    return biLst;
  }

  private boolean isUseBudget() {
    
   return false;
  }

  public ZcQb callbackFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qb.getComment(), qb, requestMeta);

    return qb;
  }

  public void deleteByPrimaryKeyFN(String qbCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQb newCommitFN(ZcQb qb, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    wfEngineAdapter.newCommit(qb.getComment(), qb, requestMeta);

    return selectByPrimaryKey(qb.getQbCode(),requestMeta);
  }

  public IBaseDao getBaseDao() {
    return baseDao;
  }

  public void setBaseDao(IBaseDao baseDao) {
    this.baseDao = baseDao;
  }

  public IWorkflowDao getWorkflowDao() {
    return workflowDao;
  }

  public void setWorkflowDao(IWorkflowDao workflowDao) {
    this.workflowDao = workflowDao;
  }

  public IZcQbDao getQbDao() {
    return qbDao;
  }

  public void setQbDao(IZcQbDao qbDao) {
    this.qbDao = qbDao;
  }

  
  public ZcQb sendPayFN(ZcQb qb, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qb=updateFN(qb, requestMeta);
    
    Map map = null;

    BudgetUtil bu=new BudgetUtil();
    //指标接口和支付接口分开，这样指标接口出错时则不掉用取消结冻指标操作
    try {
      /*
       * 释放指标金额，本次支付金额10w，则应该修改采购计划金额=原采购计划金额-10w
       */
      map = bu.getQbShiFangBudget(baseDao, true, qb);
      bu.callService(map,requestMeta.getSvNd());
      new PayForZcUtil().PayByQb(qb, requestMeta);
    } catch (ZcPayInterfaceException e) {    
      try {
        bu.cancelCallService(map,requestMeta.getSvNd());
      } catch (Exception e1) {
        String errorInfo="";
        errorInfo="汽车保险费用单【" + qb.getQbCode() + "】生成支付出错时，取消结冻的指标出现异常\n";
        List biLst = qb.getBiList();
        for (int i = 0; i < biLst.size(); i++) {
          ZcQbBi bi=(ZcQbBi)biLst.get(i);
          errorInfo+="指标编号：" + bi.getZcBiNo() + " 金额："
            + (bi.getZcBiJhuaSum() == null ? "" :bi.getZcBiJhuaSum().toString())+"\n";
        }
        
        System.err.println("异常信息如下："+errorInfo);
        e1.printStackTrace();
        throw new RuntimeException(e1.getMessage()+errorInfo);
      }
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
    qb=auditFN(qb, requestMeta);
    //支付指标
    return qb;
  }
}

