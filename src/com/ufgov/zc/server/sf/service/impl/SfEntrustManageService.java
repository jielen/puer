package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfEntrustManageMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustManageService;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfEntrustManageService implements ISfEntrustManageService {
  
  private SfEntrustManageMapper sfEntrustManageMapper;
  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private IZcEbBaseService zcEbBaseService;
  private ISfEntrustService sfEntrustService;

  
  public SfEntrustManageMapper getSfEntrustManageMapper() {
    return sfEntrustManageMapper;
  }


  public void setSfEntrustManageMapper(SfEntrustManageMapper sfEntrustManageMapper) {
    this.sfEntrustManageMapper = sfEntrustManageMapper;
  }


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


  public IZcEbBaseService getZcEbBaseService() {
    return zcEbBaseService;
  }


  public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
    this.zcEbBaseService = zcEbBaseService;
  }


  public ISfEntrustService getSfEntrustService() {
    return sfEntrustService;
  }


  public void setSfEntrustService(ISfEntrustService sfEntrustService) {
    this.sfEntrustService = sfEntrustService;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    return sfEntrustManageMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfEntrustManage selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    
    SfEntrustManage rtn= sfEntrustManageMapper.selectByPrimaryKey(id);
    if(rtn==null)return null;
    SfEntrust e=sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta);
    rtn.setEntrust(e==null?new SfEntrust():e);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  
  public SfEntrustManage saveFN(SfEntrustManage inData, RequestMeta requestMeta) {

    if (inData.getManageId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfEntrustManage.SEQ_SF_ENTRUST_MANAGE_ID));
      inData.setManageId(id); 
      insert(inData,requestMeta); 
   }else{
     update(inData,requestMeta);
   }
    updateEntrust(inData,requestMeta);
    //发送消息通知各方
    
    ZcSUtil su=new ZcSUtil();
    String msg=getMsg(inData,requestMeta,su.getSjrInfo(inData.getEntrust(), requestMeta)," 请及时关注.");
    su.sendMsgToSjr(inData.getEntrust(),msg); 
    msg=getMsg(inData,requestMeta,""," 特此通知.");
//    su.sendMsgToJdjgLeader(inData.getEntrust(),msg);  
   return inData;
  }

  private String getMsg(SfEntrustManage inData, RequestMeta requestMeta,String beginMsg,String endMsg) {
    StringBuffer sb=new StringBuffer(); 
    if(SfEntrustManage.MANAGE_TYPE_DELAY.equals(inData.getManageType())){
      if(inData.getNewEndDate()!=null){
        SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
        sb.append("延期到").append(sm.format(inData.getNewEndDate())).append("完成鉴定.");        
      }else{
        sb.append(endMsg);
      }
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append(" 延期原因：").append(inData.getReason().trim());
      }
      sb.append(" 请及时关注.");
    }else if(SfEntrustManage.MANAGE_TYPE_STOP.equals(inData.getManageType())){
      sb.append("终止鉴定了，");
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append("停止原因：").append(inData.getReason().trim());
      }
      sb.append(endMsg);
    }else if(SfEntrustManage.MANAGE_TYPE_START.equals(inData.getManageType())){
      sb.append("启动鉴定工作了，");
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append("启动原因：").append(inData.getReason().trim());
      }
      sb.append(endMsg);      
    }else if(SfEntrustManage.MANAGE_TYPE_PAUSE.equals(inData.getManageType())){
      sb.append("暂停鉴定了，");
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append("暂停原因：").append(inData.getReason().trim());
      }
      sb.append(endMsg);      
    }else if(SfEntrustManage.MANAGE_TYPE_ZHUANSONG.equals(inData.getManageType())){
      sb.append("转送其他鉴定机构进行鉴定了，");
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append("转送原因：").append(inData.getReason().trim());
      }
      sb.append(endMsg);      
    }else if(SfEntrustManage.MANAGE_TYPE_OTHER.equals(inData.getManageType())){
      
      if(inData.getReason()!=null && inData.getReason().trim().length()>0){
        sb.append(inData.getReason().trim());
      }
      sb.append(endMsg);      
    }
    return sb.toString();
  }


  private void updateEntrust(SfEntrustManage inData, RequestMeta requestMeta) {
    SfEntrust e=sfEntrustService.selectByPrimaryKey(inData.getEntrustId(), requestMeta);
    if(e==null)return;
    if(SfEntrustManage.MANAGE_TYPE_DELAY.equals(inData.getManageType())){
      Calendar a=Calendar.getInstance();
      a.setTime(e.getAcceptDate());
      Calendar b=Calendar.getInstance();
      b.setTime(inData.getNewEndDate());
      int day1=a.get(Calendar.DAY_OF_YEAR);
      int day2=b.get(Calendar.DAY_OF_YEAR);
      e.setExpectedTime(new BigDecimal(day2-day1));
      e.setStatus(SfEntrust.STATUS_DELAY);     
    }else if(SfEntrustManage.MANAGE_TYPE_PAUSE.equals(inData.getManageType())){
      e.setStatus(SfEntrust.STATUS_PAUSE);
    }else if(SfEntrustManage.MANAGE_TYPE_START.equals(inData.getManageType())){
      e.setStatus(SfEntrust.STATUS_DOING);
    }else if(SfEntrustManage.MANAGE_TYPE_STOP.equals(inData.getManageType())){
      e.setStatus(SfEntrust.STATUS_STOP);
    }else if(SfEntrustManage.MANAGE_TYPE_ZHUANSONG.equals(inData.getManageType())){
      e.setStatus(SfEntrust.STATUS_ZHUANSONG);
    }
    e=sfEntrustService.saveFN(e, requestMeta); 
    inData.setEntrust(e);
  }


  private void update(SfEntrustManage inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    sfEntrustManageMapper.updateByPrimaryKey(inData);  
  }
  public void insert(SfEntrustManage inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub 
    if (inData.getManageId()==null ) {
      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfEntrustManage.SEQ_SF_ENTRUST_MANAGE_ID));
      inData.setManageId(id); 
    }
    sfEntrustManageMapper.insert(inData); 
  }
  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    sfEntrustManageMapper.deleteByPrimaryKey(id);
  }

  
  public SfEntrustManage unAuditFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return null;
  }

  
  public SfEntrustManage untreadFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return null;
  }

  
  public SfEntrustManage auditFN(SfEntrustManage qx, RequestMeta requestMeta) throws Exception {
    return null;
  }

  
  public SfEntrustManage newCommitFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return null;
  }

  
  public SfEntrustManage callbackFN(SfEntrustManage qx, RequestMeta requestMeta) {
    return null;
  }

}
