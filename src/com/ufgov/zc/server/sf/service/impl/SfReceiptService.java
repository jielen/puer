package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.sf.model.SmsBoxsending;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfReceiptMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfReceiptService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;
import com.ufgov.zc.server.zc.service.impl.ZcEbBaseService;

public class SfReceiptService implements ISfReceiptService {

  private IWorkflowDao workflowDao;
  private WFEngineAdapter wfEngineAdapter;
  private SfReceiptMapper receiptMapper;
  private ISfEntrustService sfEntrustService;
  
  private IZcEbBaseService zcEbBaseService;
  
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


  public SfReceiptMapper getReceiptMapper() {
    return receiptMapper;
  }


  public void setReceiptMapper(SfReceiptMapper receiptMapper) {
    this.receiptMapper = receiptMapper;
  }


  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return receiptMapper.getMainDataLst(elementConditionDto);
  }

  
  public SfReceipt selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfReceipt rtn= receiptMapper.selectByPrimaryKey(id);
    SfEntrust entrust=sfEntrustService.selectByPrimaryKey(rtn.getEntrustId(), requestMeta);
    rtn.setEntrust(entrust==null?new SfEntrust():entrust);
    rtn.digest();
    return rtn;
  }

  
  public SfReceipt saveFN(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    // TCJLODO Auto-generated method stub
    if (inData.getReceiptId()==null ) {

      ZcSUtil su=new ZcSUtil();
      BigDecimal id=new BigDecimal(su.getNextVal(SfReceipt.SEQ_SF_RECEIPT_ID));
      inData.setReceiptId(id);  

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();
      
      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }      
      insert(inData,requestMeta);
      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getName());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
   }else{
     update(inData,requestMeta);
   }
   return inData;
  }
  
  private void update(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.updateByPrimaryKey(inData);
  }

  private void insert(SfReceipt inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.insert(inData);
  }
  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    receiptMapper.deleteByPrimaryKey(id);
  }

  
  public SfReceipt unAuditFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfReceipt untreadFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    return qx;
  }

  
  public SfReceipt auditFN(SfReceipt qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    qx=saveFN(qx, requestMeta);
    String beforeStatus=qx.getStatus();
    wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    qx= selectByPrimaryKey(qx.getReceiptId(),requestMeta);
    String endStatus=qx.getStatus();

    if(lastAudit(beforeStatus,endStatus)){
  	  sendMsg(qx,requestMeta);
  	  if(qx.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_JU_JUE)){
  		  updateEntrustToUnAccept(qx,requestMeta);
  	  }else if(qx.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_STOP)
  			  ||qx.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_PAUSE)){
  		  updateEntrustStatus(qx,requestMeta);
  	  }
    }
    return qx;
  }

  private void updateEntrustStatus(SfReceipt qx, RequestMeta requestMeta) {
	  qx.getEntrust().setStatus(qx.getReceiptType());
	  sfEntrustService.saveFN(qx.getEntrust(), requestMeta);
	  
}


private void updateEntrustToUnAccept(SfReceipt qx, RequestMeta requestMeta) {
	  if(!"N".equalsIgnoreCase(qx.getEntrust().getIsAccept())){
		  qx.getEntrust().setIsAccept("N");
		  if(qx.getEntrust().getNotAcceptReason()==null){
			  qx.getEntrust().setNotAcceptReason("不受理");
		  }
		  sfEntrustService.saveFN(qx.getEntrust(), requestMeta);
	  }
}


private boolean notAccept(SfReceipt qx) {
	if(qx.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_JU_JUE)){
		return true;
	}
	return false;
}


private boolean lastAudit(String beforeStatus, String endStatus) {
	  if("exec".equalsIgnoreCase(endStatus) && !"exec".equalsIgnoreCase(beforeStatus)){
		  return true;
	  }
	return false;
}
private void sendMsg(SfReceipt bill,RequestMeta requestMeta) {
	 
    
    List userLst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.selectByLoginAccount", bill.getEntrust().getEntrustor().getUser().getUserId());
     
	  if(userLst!=null ){
		  String mobile="";
		  String msg=getNoticeMsg(bill);
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  SfEntrustor row=(SfEntrustor) userLst.get(i);
			  String user=row.getUser().getUserId();
			  HashMap mobiles=su.getJdjgUser(user, bill.getEntrust().getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+bill.getReceiptId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());				   
			  } 
		  }
	  }	  
	  
    
}



private String getNoticeMsg(SfReceipt bill) { 
    StringBuffer sb=new StringBuffer();

	  if(SfReceipt.RECIEPT_TYPE_APPEND.equals(bill.getReceiptType())){
		  sb.append(bill.getEntrust().getName()+"需要补充检材检样,请联系鉴定中心");
	  }else if(SfReceipt.RECIEPT_TYPE_JU_JUE.equals(bill.getReceiptType())){
		  sb.append( bill.getEntrust().getName()+"不予受理,请联系鉴定中心");
	  }else if(SfReceipt.RECIEPT_TYPE_PAUSE.equals(bill.getReceiptType())){
		  sb.append( bill.getEntrust().getName()+"已暂停鉴定,请联系鉴定中心");
	  }else if(SfReceipt.RECIEPT_TYPE_STOP.equals(bill.getReceiptType())){
		  sb.append( bill.getEntrust().getName()+"已终止鉴定,请联系鉴定中心");
	  }
	return sb.toString();
}
  
  public SfReceipt newCommitFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.newCommit(qx.getComment(), qx, requestMeta);
    return selectByPrimaryKey(qx.getReceiptId(),requestMeta);
  }

  
  public SfReceipt callbackFN(SfReceipt qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    return qx;
  }


public ISfEntrustService getSfEntrustService() {
	return sfEntrustService;
}


public void setSfEntrustService(ISfEntrustService sfEntrustService) {
	this.sfEntrustService = sfEntrustService;
}


public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}


public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}

}
