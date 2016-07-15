package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.budget.model.VwBudgetGp;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.exception.ZcPayInterfaceException;
import com.ufgov.zc.common.zc.model.ZcPProBal;
import com.ufgov.zc.common.zc.model.ZcPProBalBi;
import com.ufgov.zc.common.zc.model.ZcQx;
import com.ufgov.zc.common.zc.model.ZcQxBi;
import com.ufgov.zc.common.zc.model.ZcQxItem;
import com.ufgov.zc.server.budget.util.BudgetUtil;
import com.ufgov.zc.server.payInterface.util.PayForZcUtil;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IZcQxDao;
import com.ufgov.zc.server.zc.service.IZcQxService;

public class ZcQxService implements IZcQxService {

  private IBaseDao baseDao;

  private IWorkflowDao workflowDao;

  public WFEngineAdapter getWfEngineAdapter() {
    return wfEngineAdapter;
  }

  public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
    this.wfEngineAdapter = wfEngineAdapter;
  }

  private IZcQxDao qxDao;
  
  private WFEngineAdapter wfEngineAdapter;

  public List getQxLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    List list = qxDao.getQxLst(elementConditionDto);

    ZcSUtil.setBillDBDigest(list);

    return list;
  }

  public void cancelFn(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQx unAuditFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return null;
  }

  public ZcQx untreadFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);

    return qx;
  }

  public ZcQx auditFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=updateFN(qx, requestMeta);
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);

    return qx;
  }

  public ZcQx updateFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub

//    System.out.println("1111="+qx.getCoCode()+qx.getCoName());
    String code = "";

    String temp_code = "";

    List biList = qx.getBiList();

    String userId = requestMeta.getSvUserID();

    String compoId = requestMeta.getCompoId();

    boolean isDraft = false;

    if (qx.getProcessInstId() == null || qx.getProcessInstId().longValue() == -1) {

      Long draftid = workflowDao.createDraftId();

      qx.setProcessInstId(draftid);

      isDraft = true;

    }

    Map map = null;

    if ("".equals(ZcSUtil.safeString(qx.getQxCode())) || qx.getQxCode().equals("自动编号")) {

     
      // 当新建项目的时候，项目编码不进行正式编码，首先创建一个临时编码，等提交送审之后，进行正式编码

      temp_code = NumUtil.getInstance().getNo("ZC_EB_QX", "QX_CODE", qx);

      code = temp_code;

      qx.setQxCode(code);

      // add by wangkewei end

      

      map = new BudgetUtil().getSaveBudgetByZcQx(qxDao, baseDao, ZcSUtil.isUseBi(), qx, biList);
      
      BigDecimal biSum=new BigDecimal(0);

      for (int i = 0; i < biList.size(); i++) {

        ZcQxBi bi = (ZcQxBi) biList.get(i);

        bi.setQxCode(code);
        if(bi.getZcBiNo()!=null && bi.getZcBiNo().trim().length()>0){
          biSum=biSum.add(bi.getZcBiJhuaSum());
        }
      }
      qx.setBiSum(biSum);
      
      for (int i = 0; i < qx.getItemList().size(); i++) {

        ZcQxItem item = (ZcQxItem) qx.getItemList().get(i);

        item.setQxCode(code);
      }

//      System.out.println("222="+qx.getCoCode()+qx.getCoName());
      qxDao.insert(qx);
//      System.out.println("333="+qx.getCoCode()+qx.getCoName());
      qxDao.insertBiLst(biList);
//      System.out.println("444="+qx.getCoCode()+qx.getCoName());
      qxDao.insertItemLst(qx.getItemList());
      System.out.println("555="+qx.getCoCode()+qx.getCoName());

    } else {
      BigDecimal biSum=new BigDecimal(0);

      for (int i = 0; i < biList.size(); i++) {

        ZcQxBi bi = (ZcQxBi) biList.get(i);

        bi.setQxCode(code);
        if(bi.getZcBiNo()!=null && bi.getZcBiNo().trim().length()>0){
          biSum=biSum.add(bi.getZcBiJhuaSum());
        }
      }
      qx.setBiSum(biSum);
      
      code = qx.getQxCode();

      // ZcPProMake originalBean = this.selectByPrimaryKey(code);
      //

      // ZcSUtil.checkDigest(qx, originalBean,
      // "zcMakeCode");//一致性校验

//      System.out.println("666="+qx.getCoCode()+qx.getCoName());
      qxDao.update(qx);

//      System.out.println("777="+qx.getCoCode()+qx.getCoName());
      map = new BudgetUtil().getSaveBudgetByZcQx(qxDao, baseDao, ZcSUtil.isUseBi(), qx, biList);

      qxDao.deleteBiByQxCode(qx.getQxCode());

      qxDao.deleteItemByQxCode(qx.getQxCode());

      for (int i = 0; i < biList.size(); i++) {

        ZcQxBi mbi = (ZcQxBi) biList.get(i);

        mbi.setQxCode(code);
      }
      qxDao.insertBiLst(biList);

//      System.out.println("888="+qx.getCoCode()+qx.getCoName());
      
      for (int i = 0; i < qx.getItemList().size(); i++) {

        ZcQxItem item = (ZcQxItem)  qx.getItemList().get(i);

        item.setQxCode(code);
      }
      qxDao.insertItemLst(qx.getItemList());

      System.out.println("999"+qx.getCoCode()+qx.getCoName());

    }

    if (isDraft) {

      AsWfDraft asWfDraft = new AsWfDraft();

      asWfDraft.setCompoId(compoId);

      asWfDraft.setWfDraftName(qx.getQxCode());

      asWfDraft.setUserId(userId);

      asWfDraft.setMasterTabId(compoId);

      asWfDraft.setWfDraftId(BigDecimal.valueOf(qx.getProcessInstId().longValue()));

      workflowDao.insertAsWfdraft(asWfDraft);

    }

    new BudgetUtil().callService(map,requestMeta.getSvNd());

    System.out.println("aaa="+qx.getCoCode()+qx.getCoName());
    return qx;
  }

  public void commitFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public void deleteListFN(List beanList, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public void deleteFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQx selectByPrimaryKey(String qxCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    ZcQx qx = this.qxDao.selectByPrimaryKey(qxCode);
    qx.setBiList(getBiLst(qxCode));
    qx.setItemList(getItemLst(qxCode));
    
    qx.setDbDigest(null);
    qx.setDbDigest(qx.digest());// 统一入口

    return qx;
  }

  private List getItemLst(String qxCode) {
    // TCJLODO Auto-generated method stub
    return this.qxDao.getItemLst(qxCode);
  }

  private List getBiLst(String qxCode) {
    // TCJLODO Auto-generated method stub
    List biLst = this.qxDao.getQxBiLst(qxCode);
    if(!isUseBudget()){
      return biLst;
    }
    String ids = "";
    for (int i = 0; i < biLst.size(); i++) {
      ZcQxBi bi = (ZcQxBi) biLst.get(i);
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
        ZcQxBi bi = (ZcQxBi) biLst.get(i);
        for(int j=0;j<yuanBiLst.size();j++){
          VwBudgetGp gp=(VwBudgetGp)yuanBiLst.get(j);
          if(bi.getZcBiNo()!=null && bi.getZcBiNo().equals(""+gp.getSumId())){
            //将指标的现有可用金额+当前计划占用的指标金额，作为当前指标的可用金额，这样维修单保存后，再修改，被本维修单占用的指标应该属于可用指标额度范围内，
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

  public ZcQx callbackFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);

    return qx;
  }

  public void deleteByPrimaryKeyFN(String qxCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

  }

  public ZcQx newCommitFN(ZcQx qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);

    return selectByPrimaryKey(qx.getQxCode(),requestMeta);
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

  public IZcQxDao getQxDao() {
    return qxDao;
  }

  public void setQxDao(IZcQxDao qxDao) {
    this.qxDao = qxDao;
  }

  
  public ZcQx sendPayFN(ZcQx qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=updateFN(qx, requestMeta);
    BudgetUtil bu=new BudgetUtil();
    Map map = null;

    //指标接口和支付接口分开，这样指标接口出错时则不掉用取消结冻指标操作
    try {
      /*
       * 释放指标金额，本次支付金额10w，则应该修改采购计划金额=原采购计划金额-10w
       */
      map = bu.getQxShifangBudget(baseDao, true, qx);
      bu.callService(map,requestMeta.getSvNd());
      new PayForZcUtil().PayByQx(qx, requestMeta);
    } catch (ZcPayInterfaceException e) {    
      try {
        bu.cancelCallService(map,requestMeta.getSvNd());
      } catch (Exception e1) {
        String errorInfo="";
        errorInfo="汽车维修保养费用单【" + qx.getQxCode() + "】生成支付出错时，取消结冻的指标出现异常\n";
        List biLst = qx.getBiList();
        for (int i = 0; i < biLst.size(); i++) {
          ZcQxBi bi=(ZcQxBi)biLst.get(i);
          errorInfo+="指标编号：" + bi.getZcBiNo() + " 金额："
            + (bi.getZcBiJhuaSum() == null ? "" : bi.getZcBiJhuaSum().toString())+"\n";
        }
        
        System.err.println("异常信息如下："+errorInfo);
        e1.printStackTrace();
        throw new RuntimeException(e1.getMessage()+errorInfo);
      }
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
    qx=auditFN(qx, requestMeta);
    //支付指标
    return qx;
  }
}
