package com.ufgov.zc.server.sf.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kingdrive.workflow.context.WorkflowContext;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfEntrustorWtcode;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.server.sf.dao.SfChargeDetailMapper;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.sf.dao.SfXysxMapper;
import com.ufgov.zc.server.sf.dao.SfXysxTypeMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfEntrustorService;
import com.ufgov.zc.server.sf.service.ISfJdTargetService;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.service.IUserService;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfEntrustService implements ISfEntrustService {

  private SfEntrustMapper entrustMapper;

  private SfMaterialsMapper materialsMapper;

  private IWorkflowDao workflowDao;

  private WFEngineAdapter wfEngineAdapter;

  private SfXysxTypeMapper xysxTypeMapper;

  private SfXysxMapper xysxMapper;

  private SfChargeDetailMapper chargeDetailMapper;
  
  private IZcEbBaseService zcEbBaseService;
  
  private IUserService userService;

  private ISfEntrustorService entrustorService;
  
  private ISfJdTargetService jdTargetService;  

  public SfXysxTypeMapper getXysxTypeMapper() {
    return xysxTypeMapper;
  }

  public void setXysxTypeMapper(SfXysxTypeMapper xysxTypeMapper) {
    this.xysxTypeMapper = xysxTypeMapper;
  }

  public SfXysxMapper getXysxMapper() {
    return xysxMapper;
  }

  public void setXysxMapper(SfXysxMapper xysxMapper) {
    this.xysxMapper = xysxMapper;
  }

  public List getEntrustLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustMapper.getEntrustLst(elementConditionDto);
  }

  public SfEntrust selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfEntrust rtn = entrustMapper.selectByPrimaryKey(id);
    if (rtn.getWtIdParent() != null) {
      SfEntrust old = entrustMapper.selectByPrimaryKey(rtn.getWtIdParent());
      if (old != null) {
        rtn.setOldEntrust(old);
        rtn.setWtCodeParent(old.getCode());
      }
    }
    SfEntrustor wtf=entrustorService.selectByPrimaryKey(rtn.getEntrustorId(), requestMeta);
    rtn.setEntrustor(wtf==null?new SfEntrustor():wtf);
    List materialLst = materialsMapper.selectByEntrustId(id);
    rtn.setMaterials(materialLst == null ? new ArrayList() : materialLst);
    List chargeLst = chargeDetailMapper.selectByPrimaryKey(id);
    rtn.setJdChargeDetaillst(chargeLst == null ? new ArrayList() : chargeLst);
    //设定一个唯一主键,用于界面的table展现
    for (int i = 0; i < rtn.getJdChargeDetaillst().size(); i++) {
      SfChargeDetail charge = (SfChargeDetail) rtn.getJdChargeDetaillst().get(i);
      charge.setTempId("" + System.currentTimeMillis());
    }
    List xysxLst = xysxMapper.selectByPrimaryKey(id);
    xysxLst = xysxLst == null ? new ArrayList() : xysxLst;
    rtn.setXysxLst(xysxLst);
    rtn.setDbDigest(rtn.digest());
    return rtn;
  }

  public ISfEntrustorService getEntrustorService() {
	return entrustorService;
}

public void setEntrustorService(ISfEntrustorService entrustorService) {
	this.entrustorService = entrustorService;
}

public SfEntrust saveFN(SfEntrust inData, RequestMeta requestMeta) {

    //    System.out.println("service saveFN 1++++++++++++++++++++++++++=" + inData.getAcceptDate());

    // TCJLODO Auto-generated method stub
    if ("".equals(ZcSUtil.safeString(inData.getCode())) || inData.getCode().equals("自动编号")) {

//      String code = NumUtil.getInstance().getNo("SF_ENTRUST", "CODE", inData);//这个用作受理编号了
      
      inData.setCode(getEntrustWtCode(inData,requestMeta));
      BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfEntrust.SEQ_SF_ENTRUST_ID));
      inData.setEntrustId(id);

      boolean isDraft = false;
      String userId = requestMeta.getSvUserID();
      String compoId = requestMeta.getCompoId();

      //      System.out.println("service saveFN 2++++++++++++++++++++++++++=" + inData.getAcceptDate());

      if (inData.getProcessInstId() == null || inData.getProcessInstId().longValue() == -1) {
        Long draftid = workflowDao.createDraftId();
        inData.setProcessInstId(draftid);
        isDraft = true;
      }
      insert(inData, requestMeta);

      //      System.out.println("service saveFN 3++++++++++++++++++++++++++=" + inData.getAcceptDate());

      if (isDraft) {
        AsWfDraft asWfDraft = new AsWfDraft();
        asWfDraft.setCompoId(compoId);
        asWfDraft.setWfDraftName(inData.getCode());
        asWfDraft.setUserId(userId);
        asWfDraft.setMasterTabId(compoId);
        asWfDraft.setWfDraftId(BigDecimal.valueOf(inData.getProcessInstId().longValue()));
        workflowDao.insertAsWfdraft(asWfDraft);
      }
    } else {
      update(inData, requestMeta);
    }
    saveJdTarget(inData,requestMeta);
    return inData;
  }

  private String getEntrustWtCode(SfEntrust inData, RequestMeta requestMeta) {
    ElementConditionDto dto=new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setSfId(inData.getEntrustorId());
    SfEntrustorWtcode wtcode=(SfEntrustorWtcode) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustorWtcodeMapper.getCurNo", dto);
    if(wtcode==null){
      wtcode=new SfEntrustorWtcode();
      wtcode.setWtcodeId(new BigDecimal(ZcSUtil.getNextVal(SfEntrustorWtcode.SEQ_SF_ENTRUSTOR_WTCODE_ID)));
      wtcode.setNd(new Integer(requestMeta.getSvNd()));
      wtcode.setNumNo("1");
      wtcode.setEntrustorId(inData.getEntrustorId());
      wtcode.setJdCompany(inData.getCode());
      zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustorWtcodeMapper.insert", wtcode);
    }else{
      int i=Integer.parseInt(wtcode.getNumNo());
      i+=1;
      wtcode.setNumNo(""+i);
      List l=new ArrayList();
      l.add(wtcode);
      zcEbBaseService.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustorWtcodeMapper.updateByPrimaryKey", l);
    }
    return wtcode.getWeiTuoCode(inData.getEntrustor().getName());
}

  public void update(SfEntrust inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    //    System.out.println("update ++++++++++++++++++++++++++=" + inData.getAcceptDate());

    entrustMapper.updateByPrimaryKey(inData);
    materialsMapper.deleteByEntrustId(inData.getEntrustId());
    chargeDetailMapper.deleteByPrimaryKey(inData.getEntrustId());
    xysxMapper.deleteByPrimaryKey(inData.getEntrustId());

    if (inData.getMaterials() != null) {
      for (int i = 0; i < inData.getMaterials().size(); i++) {
        SfMaterials m = (SfMaterials) inData.getMaterials().get(i);
        setMaterialsId(m);
        m.setEntrustId(inData.getEntrustId());
        materialsMapper.insert(m);
      }
    }
    if (inData.getJdChargeDetaillst() != null) {
      for (int i = 0; i < inData.getJdChargeDetaillst().size(); i++) {
        SfChargeDetail d = (SfChargeDetail) inData.getJdChargeDetaillst().get(i);
        d.setEntrustId(inData.getEntrustId());
        chargeDetailMapper.insert(d);
      }
    }
    if (inData.getXysxLst() != null) {
      //      System.out.println("========================================");
      for (int i = 0; i < inData.getXysxLst().size(); i++) {
        SfXysx d = (SfXysx) inData.getXysxLst().get(i);
        d.setEntrustId(inData.getEntrustId());
        //        System.out.println(d == null ? null : d.getXysxTypeId());
        xysxMapper.insert(d);
      }
    }
  }

  private void setMaterialsId(SfMaterials m) {
    // TCJLODO Auto-generated method stub
    if (m.getMaterialId() != null)
      return;
    BigDecimal id = new BigDecimal(ZcSUtil.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
    m.setMaterialId(id);
  }

  private void insert(SfEntrust inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustMapper.insert(inData);
    if (inData.getMaterials() != null) {
      for (int i = 0; i < inData.getMaterials().size(); i++) {
        SfMaterials m = (SfMaterials) inData.getMaterials().get(i);
        setMaterialsId(m);
        m.setEntrustId(inData.getEntrustId());
        materialsMapper.insert(m);
      }
    }
    if (inData.getJdChargeDetaillst() != null) {
      for (int i = 0; i < inData.getJdChargeDetaillst().size(); i++) {
        SfChargeDetail d = (SfChargeDetail) inData.getJdChargeDetaillst().get(i);
        d.setEntrustId(inData.getEntrustId());
        chargeDetailMapper.insert(d);
      }
    }
    if (inData.getXysxLst() != null) {
      for (int i = 0; i < inData.getXysxLst().size(); i++) {
        SfXysx d = (SfXysx) inData.getXysxLst().get(i);
        d.setEntrustId(inData.getEntrustId());
        xysxMapper.insert(d);
      }
    }
  }

  private void saveJdTarget(SfEntrust inData, RequestMeta requestMeta) {
    if(inData.getJdTarget()!=null){
      inData.getJdTarget().setEntrustId(inData.getEntrustId());
      jdTargetService.saveFN(inData.getJdTarget(), requestMeta);
    }
  }

  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustMapper.deleteByPrimaryKey(id);
    materialsMapper.deleteByEntrustId(id);
  }

  public SfEntrustMapper getEntrustMapper() {
    return entrustMapper;
  }

  public void setEntrustMapper(SfEntrustMapper entrustMapper) {
    this.entrustMapper = entrustMapper;
  }

  public SfMaterialsMapper getMaterialsMapper() {
    return materialsMapper;
  }

  public void setMaterialsMapper(SfMaterialsMapper materialsMapper) {
    this.materialsMapper = materialsMapper;
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

  public SfEntrust unAuditFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.rework(qx.getComment(), qx, requestMeta);
    return qx;
  }

  public SfEntrust untreadFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
    sendMsgUntread(qx,requestMeta);
    sendEntrustStatsToWtf(qx,requestMeta,ISfEntrustService.WF_OPERATION_UNTREAD);
    return qx;
  }

  private void sendEntrustStatsToWtf(SfEntrust qx, RequestMeta requestMeta, String wfOperation) {
    if(ISfEntrustService.WF_OPERATION_UNTREAD.equals(wfOperation)){
      StringBuffer msg=new StringBuffer();
      msg.append("您提交的").append(qx.getCode()).append("鉴定委托被鉴定中心退回，").append(qx.getComment()).append(",请登录司法鉴定管理系统进行查看和处理。");
      ZcSUtil su=new ZcSUtil();
      if(qx.getSjrTel()!=null && su.isMobile(qx.getSjrTel().trim())){
        su.sendToBox(""+qx.getEntrustId().intValue(), "", msg.toString(), qx.getSjrTel().trim(), requestMeta.getSysDate(), requestMeta.getSysDate());
      }
      if(qx.getSjr2Tel()!=null && su.isMobile(qx.getSjr2Tel().trim())){
        su.sendToBox(""+qx.getEntrustId().intValue(), "", msg.toString(), qx.getSjr2Tel().trim(), requestMeta.getSysDate(), requestMeta.getSysDate());
      }
      return;
    }
    
  }

  private void sendMsgUntread(SfEntrust qx, RequestMeta requestMeta) {
	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_ENTRUST");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectUntreadUser", dto);
    userLst=userLst==null?new ArrayList():userLst;
    List userLst2=zcEbBaseService.queryDataForList("ZcEbUtil.selectEntrustKeshiShouliUntreadUser", dto);
    if(userLst2!=null){
      userLst.addAll(userLst2);
    }
	  if(userLst!=null ){
		  String mobile="";
		  String msg=qx.getCode()+"鉴定委托被退回了,请登录鉴定管理系统进行查看处理。";
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getJdjgUser(user, qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, requestMeta.getSysDate(), requestMeta.getSysDate());
			  } 
		  }
	  }	  
}
  private void sendMsgAudit(SfEntrust qx, RequestMeta requestMeta,String oldStatus) {

    ZcSUtil su=new ZcSUtil();
    //给委托方发短信
    SfJdjg jg=su.getJdjgInfo(qx.getCoCode(),requestMeta);
    String jgName="鉴定中心";
    if(jg!=null){
      jgName=jg.getName();
    }
    StringBuffer sb=new StringBuffer();
    //1、委托初审通过，等待接收检材，
    if(qx.getStatus().equals(SfEntrust.STATUS_JIE_SHOU_JIANCAI) && (SfEntrust.STATUS_HE_ZHUN.equals(oldStatus) || SfEntrust.STATUS_QUE_REN.equals(oldStatus))){
      
      sb.append(su.getSjrInfo(qx,requestMeta));
      sb.append("已经通过委托确认，请登录司法鉴定管理系统,打开委托界面，打印委托书，携带相关检材样本，到").append(jgName).append("提交检材物品，并签订和领取司法鉴定委托确认书。");
     }
    //1、鉴定被受理，
    if(qx.getStatus().equals(SfEntrust.STATUS_DOING) && (SfEntrust.STATUS_KE_SHI_SHOULI.equals(oldStatus) || SfEntrust.STATUS_DRAFT.equals(oldStatus))){ 
      sb.append(su.getSjrInfo(qx,requestMeta));
      sb.append("已经被受理，").append(jgName).append("将在约定时间内完成鉴定工作，请保持通讯设备畅通，及时关注鉴定进展情况，谢谢。");
     }
    su.sendMsgToSjr(qx,sb.toString());

    //给待办人发短信
    
    
    
	  ElementConditionDto dto=new ElementConditionDto();
	  dto.setDattr1("SF_ENTRUST");
	  dto.setDattr2(""+qx.getProcessInstId());
	  List userLst=zcEbBaseService.queryDataForList("ZcEbUtil.selectToDoUser", dto);
	  
	  userLst=userLst==null?new ArrayList():userLst;
	  List userLst2=zcEbBaseService.queryDataForList("ZcEbUtil.selectEntrustKeshiShouliToDoUser", dto);
	  if(userLst2!=null){
	    userLst.addAll(userLst2);
	  }
	  
	    
		  String mobile="";
		  String msg=qx.getCode()+"鉴定委托等待您审批,案事件:"+qx.getName()+",请登录鉴定管理系统进行审批。";
		  
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getJdjgUser(user,qx.getProcessInstId(),requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, requestMeta.getSysDate(), requestMeta.getSysDate());
			  } 
		  }
} 
 

public SfEntrust auditFN(SfEntrust qx, RequestMeta requestMeta) throws Exception {
    // TCJLODO Auto-generated method stub
    //    System.out.println("auditFN 1++++++++++++++++++++++++++=" + qx.getAcceptDate());

  SfEntrust oldQx=(SfEntrust) BeanUtils.cloneBean(qx);
  
    qx = saveFN(qx, requestMeta);
    //    System.out.println("auditFN 2++++++++++++++++++++++++++=" + qx.getAcceptDate());
    
    //由于科室受理的待办是通用用户KESHI_SHOULI，所以要将excutor换位KESHI_SHOULI，否则在
    //taskItem = getRuntimeService().getCurrentTaskByNodeUser(instanceId, currentNodeId, user);，会得不到报错
    ElementConditionDto dto=new ElementConditionDto();
    dto.setExecutor(requestMeta.getSvUserID());
    dto.setProcessInstId(qx.getProcessInstId());
     Long instanceId=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isKeshiShouliToDo", dto);
     Long instanceId2=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isKeshiShouliUntreat", dto); 
     Long instanceId3=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isZongHeShouliToDo", dto);
     Long instanceId4=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isZongHeShouliUntreat", dto); 
    if(instanceId!=null || instanceId2!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.KESHI_SHOULI); 
      wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
    }else if(instanceId3!=null || instanceId4!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.ZONGHE_SHOULI); 
      wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
    }else{    
      wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    }
     
     SfEntrust rtn=selectByPrimaryKey(qx.getEntrustId(), requestMeta);
     
     sendMsgAudit(qx,requestMeta,oldQx.getStatus());
     return rtn;
  }


public SfEntrust newCommitFN(SfEntrust bill, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    //    System.out.println("newCommitFN 1++++++++++++++++++++++++++=" + qx.getAcceptDate());
  
  //鉴定机构自己哎中心现场录入时,送审时,自动生成受理编号,用于打印鉴定确认书
  boolean isJdjgInput=false;//是否鉴定机构录入
  if(requestMeta.containRole(ZcSettingConstants.R_SF_JDJG)||requestMeta.containRole(ZcSettingConstants.R_SF_JD_PERSON) ){
    if(bill.getAcceptCode()==null && !"N".equalsIgnoreCase(bill.getIsAccept())){
      bill.setAcceptCode(zcEbBaseService.getAutoNumNo(bill, "SF_ENTRUST", "CODE"));
    }
    isJdjgInput=true;
  }
  String oldStatus=bill.getStatus();
  try {
    SfEntrust oldBill=(SfEntrust) BeanUtils.cloneBean(bill);
    oldStatus=oldBill.getStatus();
  } catch (IllegalAccessException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (InstantiationException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (InvocationTargetException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (NoSuchMethodException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  
    bill = saveFN(bill, requestMeta); 
    
   WorkflowContext context= wfEngineAdapter.newCommit(bill.getComment(), bill, requestMeta);
    bill= selectByPrimaryKey(bill.getEntrustId(), requestMeta);
    
    StringBuffer msg=new StringBuffer();
    if(!isJdjgInput){//发送消息给下一岗的执行者，有待审批的单据
      msg.append(bill.getEntrustor().getName()).append("提交了新的鉴定委托，编号为：").append(bill.getCode());
      msg.append(",鉴定类别为").append(bill.getMajor().getMajorName()).append(", 请及时登录鉴定管理系统进行确认，方便委托方及时打印委托书，提交检材.");
    }else{
      msg.append(bill.getEntrustor().getName()).append("提交了检材和鉴定委托书，委托书编号为：").append(bill.getCode());
      msg.append(",鉴定类别为").append(bill.getMajor().getMajorName()).append(",已通过综合室的确认，请登录鉴定管理系统进行受理，并鉴定委托确认书，安排鉴定工作.");
    }
    
    
    return bill;
  }

  public SfEntrust callbackFN(SfEntrust qx, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    wfEngineAdapter.callback(qx.getComment(), qx, requestMeta);
    
    return qx;
  }

  public SfChargeDetailMapper getChargeDetailMapper() {
    return chargeDetailMapper;
  }

  public void setChargeDetailMapper(SfChargeDetailMapper chargeDetailMapper) {
    this.chargeDetailMapper = chargeDetailMapper;
  }

 /**
  * 受理,同时进行审核操作
 * @throws Exception 
  */
public SfEntrust acceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
//	inData.setIsAccept("Y");
//	inData.setAcceptor(requestMeta.getSvUserID());
//	inData.setAcceptDate(requestMeta.getSysDate());
  return auditFN(inData,requestMeta);
	
}

 /**
  * 不受理，提交审批
 * @throws Exception 
  */
public SfEntrust unAcceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	inData.setIsAccept("N");
	inData.setAcceptDate(requestMeta.getSysDate());
	inData.setAcceptor(requestMeta.getSvUserID());
	auditFN(inData,requestMeta);
	
	return auditFN(inData,requestMeta);
}

public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}

public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}

public IUserService getUserService() {
	return userService;
}

public void setUserService(IUserService userService) {
	this.userService = userService;
}

public ISfJdTargetService getJdTargetService() {
  return jdTargetService;
}

public void setJdTargetService(ISfJdTargetService jdTargetService) {
  this.jdTargetService = jdTargetService;
}
}
