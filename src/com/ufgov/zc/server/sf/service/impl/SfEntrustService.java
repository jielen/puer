package com.ufgov.zc.server.sf.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kingdrive.workflow.context.WorkflowContext;
import com.kingdrive.workflow.model.TaskUser;
import com.kingdrive.workflow.model.runtime.ActionHistoryModel;
import com.kingdrive.workflow.model.runtime.CurrentTaskModel;
import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfEntrustorWtcode;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.sf.model.ZcMobileMsg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.server.sf.dao.SfChargeDetailMapper;
import com.ufgov.zc.server.sf.dao.SfDocSendMapper;
import com.ufgov.zc.server.sf.dao.SfEntrustMapper;
import com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper;
import com.ufgov.zc.server.sf.dao.SfMaterialsMapper;
import com.ufgov.zc.server.sf.dao.SfXysxMapper;
import com.ufgov.zc.server.sf.dao.SfXysxTypeMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustService;
import com.ufgov.zc.server.sf.service.ISfEntrustorService;
import com.ufgov.zc.server.sf.service.ISfJdDocAuditService;
import com.ufgov.zc.server.sf.service.ISfJdTargetService;
import com.ufgov.zc.server.sf.service.IZcMobileMsgService;
import com.ufgov.zc.server.system.SpringContext;
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
  
  private IZcMobileMsgService zcMobileMsgService; 
  
  private SfJdDocAuditMapper jdDocAuditMapper;
  
  private SfDocSendMapper sfDocSendMapper;

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
    
    List curLst=entrustMapper.getEntrustLst(elementConditionDto);
    if(curLst==null){
      curLst=new ArrayList();       
    }
    
    //在待处理页签底下，
    if(elementConditionDto.getStatus()!=null && elementConditionDto.getStatus().equals("todo")){  
       //囊括如果存在当前登录人的鉴定文书审批待办单信息，则将其对于的委托数据合并到当前页签下，并将状态改为鉴定文书审批    
       List entrustWithJddocAuditing=getEntrustsWithJdDocAuditing(elementConditionDto,requestMeta);       
       if(entrustWithJddocAuditing!=null){
         curLst.addAll(0,entrustWithJddocAuditing);        
       }
       
       //如果有文书已经审批了，还没有打印已经给综合受理，则将其对于的委托数据合并到当前页签下，并将状态改为文书打印    
       List printDocLst=getEntrustsHavingJdDocAudited(elementConditionDto,requestMeta); 
       if(printDocLst!=null){
         curLst.addAll(0,printDocLst);        
       }      
       //如果文书已经由经手人发起提交了，如果当前登录人是综合受理人，则显示在这个页签，将状态改为移交鉴定文书    
       //先不加这些给值班人，他们移交时，手工从鉴定中选择对应的委托
     /*  List jiaojieDocLst=getEntrustsReportDoc(elementConditionDto,requestMeta); 
       if(jiaojieDocLst!=null){
         curLst.addAll(jiaojieDocLst);        
       }  */    
    }
    return curLst;
  }

  private List getEntrustsReportDoc(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {

    ElementConditionDto dto=new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    List zblst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.selectCurrentZhiban", dto);
    if(zblst==null || zblst.size()==0)return null;
    boolean zhiban=false;
    for(int i=0;i<zblst.size();i++){
      SfZongheZhiban zb=(SfZongheZhiban) zblst.get(i);
      if(zb.getUserId().equals(requestMeta.getSvUserID())){
        zhiban=true;
        break;
      }
    }
    if(zhiban){
      dto=new ElementConditionDto();
      dto.setStatus("todo");
      dto.setNd(requestMeta.getSvNd());
      dto.setExecutor(requestMeta.getSvUserID());
      dto.setCoCode(requestMeta.getSvCoCode());
      List lst=sfDocSendMapper.getMainDataLst(elementConditionDto);
      List entustIdLst=new ArrayList();
      if(lst!=null && lst.size()>0){
        for(int i=0;i<lst.size();i++){
          SfDocSend doc=(SfDocSend) lst.get(i);
          entustIdLst.add(doc.getEntrust().getEntrustId());
        }        
      }else{
        return null;
      }
      

      dto=(ElementConditionDto) ObjectUtil.deepCopy(elementConditionDto);
      dto.setStatus("getEntrustsReportDoc");//getEntrustsReportDoc
      dto.setPmAdjustCodeList(entustIdLst);
      List entrustList=entrustMapper.getEntrustLst(dto);
      //将entrust的status转化为docAudit 鉴定文书审批
      if(entrustList!=null){
        for(int i=0;i<entrustList.size();i++){
          SfEntrust entrust=(SfEntrust) entrustList.get(i);
          entrust.setStatus(SfEntrust.STATUS_DOC_JIAOJIE);
        }
        return entrustList;
      }
    }
    return null;
  }

  /**
   * 获取当前登录人制作的，已经审批结束的文书审批单，但还没有打印整理，进入文书移交的，便于提醒起草人及时打印，签字、移交给综合受理人
   * @param elementConditionDto
   * @param requestMeta
   * @return
   */
  private List getEntrustsHavingJdDocAudited(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    ElementConditionDto dto=(ElementConditionDto) ObjectUtil.deepCopy(elementConditionDto);
    dto.setStatus(SfEntrust.STATUS_REPORT_PRINTING);
    List lst=entrustMapper.getEntrustLst(dto);
    if(lst!=null ){
      for(int i=0;i<lst.size();i++){
        SfEntrust e=(SfEntrust) lst.get(i);
        e.setStatus(SfEntrust.STATUS_REPORT_PRINTING);
      }
    }
    return lst;
  }
  
  /**
   * 获取当前登录人的鉴定文书审批单信息，并转化为对于的entrust
   * 
   * @param elementConditionDto
   * @param requestMeta
   * @return
   */
  private List getEntrustsWithJdDocAuditing(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    ElementConditionDto dto=(ElementConditionDto) ObjectUtil.deepCopy(elementConditionDto);
    dto.setCompoId("SF_JD_DOC_AUDIT");
    dto.setWfcompoId(dto.getCompoId());
    dto.setStatus("todo");
    List docLst=jdDocAuditMapper.getMainDataLst(dto);
    dto.setStatus("untread");
    List docLst2=jdDocAuditMapper.getMainDataLst(dto);
    List entustIdLst=new ArrayList();
    if(docLst!=null){
      for(int i=0;i<docLst.size();i++){
        SfJdDocAudit doc=(SfJdDocAudit) docLst.get(i);
        entustIdLst.add(doc.getEntrust().getEntrustId());
      }
    }
    if(docLst2!=null){
      for(int i=0;i<docLst2.size();i++){
        SfJdDocAudit doc=(SfJdDocAudit) docLst2.get(i);
        entustIdLst.add(doc.getEntrust().getEntrustId());
      }
    }
    if(!entustIdLst.isEmpty()){
      dto=(ElementConditionDto) ObjectUtil.deepCopy(elementConditionDto);
      dto.setStatus("getEntustWithJdDocAudting");
      dto.setPmAdjustCodeList(entustIdLst);
      List entrustList=entrustMapper.getEntrustLst(dto);
      //将entrust的status转化为docAudit 鉴定文书审批
      if(entrustList!=null){
        for(int i=0;i<entrustList.size();i++){
          SfEntrust entrust=(SfEntrust) entrustList.get(i);
          entrust.setStatus(SfEntrust.STATUS_DOC_AUDITING);
        }
      }
      return entrustList;
    }
    return null;
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
      ZcSUtil su=new ZcSUtil();
      BigDecimal id = new BigDecimal(su.getNextVal(SfEntrust.SEQ_SF_ENTRUST_ID));
      inData.setEntrustId(id);
      inData.setBarCode("WT"+id.toString());

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
      ZcSUtil su=new ZcSUtil();
      wtcode.setWtcodeId(new BigDecimal(su.getNextVal(SfEntrustorWtcode.SEQ_SF_ENTRUSTOR_WTCODE_ID)));
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
    if(inData.getEntrustor().getShortName()!=null && inData.getEntrustor().getShortName().trim().length()>0){
      return wtcode.getWeiTuoCode(inData.getEntrustor().getShortName().trim());
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
    ZcSUtil su=new ZcSUtil();
    BigDecimal id = new BigDecimal(su.getNextVal(SfMaterials.SEQ_SF_MATERIALS_ID));
    m.setMaterialId(id);
    m.setBarCode("JC"+id.toString());
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
    if(inData.getJdTarget()!=null && inData.getJdTarget().isNotEmpty()){      
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

  public SfEntrust untreadFN(SfEntrust qx, RequestMeta requestMeta) throws SfBusinessException {
    // TCJLODO Auto-generated method stub

    ElementConditionDto dto=new ElementConditionDto();
    dto.setExecutor(requestMeta.getSvUserID());
    dto.setProcessInstId(qx.getProcessInstId());
     Long instanceId=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isKeshiShouliToDo", dto);
     Long instanceId2=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isKeshiShouliUntreat", dto); 
     Long instanceId3=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isZongHeShouliToDo", dto);
     Long instanceId4=(Long) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.isZongHeShouliUntreat", dto);

     WorkflowContext context=null;
     try{
      if(instanceId!=null || instanceId2!=null){
        RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
        newMeta.setSvUserID(SfElementConstants.KESHI_SHOULI); 
        context=wfEngineAdapter.untread(qx.getComment(), qx, newMeta);
      }else if(instanceId3!=null || instanceId4!=null){
        RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
        newMeta.setSvUserID(SfElementConstants.ZONGHE_SHOULI); 
        context=wfEngineAdapter.untread(qx.getComment(), qx, newMeta);
      }else{    
        context=wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
      }
     }catch (Exception e) {
      // TODO: handle exception
       throw new SfBusinessException("退回异常.", e);
    }
     
    
//    wfEngineAdapter.untread(qx.getComment(), qx, requestMeta);
     ZcSUtil su=new ZcSUtil();
     SfJdjg jg=su.getJdjgInfo(qx.getCoCode());
     String jgName="鉴定中心",jgName2="";
     if(jg!=null){
       jgName=jg.getName();
     }
     jgName2="【"+jgName+"】:";
     
    if(context!=null && context.getNextNode()!=null && context.getNextNode().getNodeId().longValue()==31039013L){//31039013L是委托书流程的第一个节点 
      _sendUntreadMsgToSjr(qx,requestMeta,ISfEntrustService.WF_OPERATION_UNTREAD,jgName,jgName2);      
    }
//    _sendUntreadMsgToPreUser(qx,requestMeta,context,jgName,jgName2);
    return qx;
  }

  private void _sendUntreadMsgToSjr(SfEntrust qx, RequestMeta requestMeta, String wfOperation, String jgName, String jgName2) {
    
    StringBuffer msg=new StringBuffer();
    if(ISfEntrustService.WF_OPERATION_UNTREAD.equals(wfOperation)){
      msg.append(jgName2).append("您提交的").append(qx.getCode()).append(",").append(qx.getComment()).append(" 被").append(jgName).append("退回").append(",请登录司法鉴定管理系统进行查看和处理。"); 
    }
    ZcSUtil su=new ZcSUtil();
    su.sendMsgToSjr(qx, msg.toString());
    
  }

  private void _sendUntreadMsgToPreUser(SfEntrust bill, RequestMeta requestMeta,WorkflowContext context, String jgName, String jgName2) {
/*   
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
		  String msg=qx.getCode()+"被退回了,请登录鉴定管理系统进行查看处理。";
		  ZcSUtil su=new ZcSUtil();
		  for(int i=0;i<userLst.size();i++){
			  HashMap row=(HashMap) userLst.get(i);
			  String user=(String) row.get("EXECUTOR");
			  HashMap mobiles=su.getJdjgUser(user, qx.getProcessInstId(), requestMeta);
			  Iterator keys=mobiles.keySet().iterator();
			  while(keys.hasNext()){
				  String key=keys.next().toString(); 
				  su.sendToBox(""+qx.getEntrustId().intValue(), "", msg, key, ZcSUtil.getSysDate(), ZcSUtil.getSysDate());
			  } 
		  }
	  }
*/
    if(context.getNextExecutor()==null || context.getNextExecutor().size()==0)return;
    StringBuffer msg=new StringBuffer();
    msg.append(jgName2);
    msg.append(bill.getCode()).append("被退回了,");
    if(context.getComment()!=null && context.getComment().trim().length()>0){
      msg.append(context.getComment()).append(",");
    }
    msg.append("请登录鉴定管理系统进行查看处理。");
    List users=new ArrayList();
    ZcSUtil su=new ZcSUtil();
    for(int i=0;i<context.getNextExecutor().size();i++){
      TaskUser tu=(TaskUser) context.getNextExecutor().get(i);
      if(tu.getUserId().equals(SfElementConstants.KESHI_SHOULI)){//科室受理
        users.addAll(su.getKeshiShouLiUser(bill,requestMeta));
      }else if(tu.getUserId().equals(SfElementConstants.ZONGHE_SHOULI)){//综合值班受理
        List zongheZhibanLst=su.getZonheShouLiUser(bill.getNd().intValue(),bill.getCoCode());
        if(zongheZhibanLst==null || zongheZhibanLst.size()==0){
          //目前没有人值班，需要将消息预存，等有人值班时，再进行发送
//          _saveToTemp(msg,bill,SfElementConstants.ZONGHE_SHOULI,requestMeta);//先不这么处理
        }else{
          users.addAll(zongheZhibanLst);
        }        
      }else{
        users.add(tu.getUserId());
      }
    }
    
    //获取用户名称和和电话号码
    ElementConditionDto dto=new ElementConditionDto();
    dto.getPmAdjustCodeList().clear();
    if(users.size()==0){
      users.add("kongyonghu");//这里加上这个空用户，是为了防止这个列表为空时，后台会忽略这个条件，将全部用户搜出来
    }
    dto.getPmAdjustCodeList().addAll(users);
    dto.setDattr1("havePhone");
    List userLst=zcEbBaseService.queryDataForList("AsEmp.getAsEmpLst", dto);
     
    if(userLst==null || userLst.size()==0)return;
    su.sendMsgToAsEmp(userLst,msg.toString());
} 

  private void sendMsgAudit(SfEntrust bill, RequestMeta requestMeta,String oldStatus, List nextUsers) {

    ZcSUtil su=new ZcSUtil();
    SfJdjg jg=su.getJdjgInfo(bill.getCoCode());
    String jgName="鉴定中心",jgName2="";
    if(jg!=null){
      jgName=jg.getName();
    }
    jgName2="【"+jgName+"】:";
    //给送检人发送信息
    _sendAuditMsgToSjr(bill, requestMeta, oldStatus,jgName,jgName2);

    //给待办人发短信 
//    _sendAuditMsgToNextUser(bill, requestMeta, nextUsers,jgName,jgName2);
	    
}

  private void _sendAuditMsgToNextUser(SfEntrust bill, RequestMeta requestMeta, List nextUsers,String jgName,String jgName2) {
    if(nextUsers==null || nextUsers.size()==0)return;
    StringBuffer sb=new StringBuffer();
    sb.append(jgName2).append(bill.getCode()).append("等待您审批,案事件:").append(bill.getName()==null?"":bill.getName()).append(",请登录鉴定管理系统进行审批。");
    String msg=sb.toString();
    List users=new ArrayList();
    ZcSUtil su=new ZcSUtil();
    for(int i=0;i<nextUsers.size();i++){
      TaskUser tu=(TaskUser) nextUsers.get(i);
      if(tu.getUserId().equals(SfElementConstants.KESHI_SHOULI)){//科室受理
        users.addAll(su.getKeshiShouLiUser(bill,requestMeta));
      }else if(tu.getUserId().equals(SfElementConstants.ZONGHE_SHOULI)){//综合值班受理
        List zongheZhibanLst=su.getZonheShouLiUser(bill,requestMeta);
        if(zongheZhibanLst==null || zongheZhibanLst.size()==0){
          //目前没有人值班，需要将消息预存，等有人值班时，再进行发送
//          _saveToTemp(msg,bill,SfElementConstants.ZONGHE_SHOULI,requestMeta);//先不这么处理
        }else{
          users.addAll(zongheZhibanLst);
        }        
      }else{ 
        users.add(tu.getUserId());
      }
    }
    
    //获取用户名称和和电话号码
    ElementConditionDto dto=new ElementConditionDto();
    dto.getPmAdjustCodeList().clear();
    if(users.size()==0){
      users.add("kongyonghu");//这里加上这个空用户，是为了防止这个列表为空时，后台会忽略这个条件，将全部用户搜出来
    }
    dto.getPmAdjustCodeList().addAll(users);
    dto.setDattr1("havePhone");
    List userLst=zcEbBaseService.queryDataForList("AsEmp.getAsEmpLst", dto);
     
    if(userLst==null || userLst.size()==0)return;
    su.sendMsgToAsEmp(userLst,msg);
  }

  private void _saveToTemp(String msg, SfEntrust bill, String userId, RequestMeta requestMeta) {
    ZcMobileMsg zm=new ZcMobileMsg();
    zm.setCoCode(requestMeta.getSvCoCode());
    zm.setNd(new Integer(requestMeta.getSvNd()));
    zm.setIsSended(ZcMobileMsg.ZC_VS_IS_SENDED_NO);
    zm.setInputDate(ZcSUtil.getSysDate());
    zm.setInputor(requestMeta.getSvUserID());
    zm.setBusinessType("SF_ENTRUST");
    zm.setProjCode(bill.getCode());
    zm.setContent(msg);
    try {
      zcMobileMsgService.updateFN(zm, requestMeta);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void _sendAuditMsgToSjr(SfEntrust bill, RequestMeta requestMeta, String oldStatus,String jgName,String jgName2) {
    ZcSUtil su=new ZcSUtil();
    //给委托方发短信
    StringBuffer sb=new StringBuffer();
    //1、委托初审通过，等待接收检材，
    if(bill.getStatus().equals(SfEntrust.STATUS_JIE_SHOU_JIANCAI) && (SfEntrust.STATUS_HE_ZHUN.equals(oldStatus) || SfEntrust.STATUS_QUE_REN.equals(oldStatus))){   
      sb.append(jgName2);   
      sb.append(su.getSjrInfo(bill,requestMeta));
      sb.append("已经通过委托确认，请登录司法鉴定管理系统,打开委托界面，打印委托书，携带相关检材样本，到").append(jgName).append("提交检材物品。");
     }else if(bill.getStatus().equals(SfEntrust.STATUS_DOING)){//开始鉴定了，发送消息给送检人
       sb.append(jgName2);
       sb.append(su.getSjrInfo(bill,requestMeta));
       int days=7;
       if(bill.getExpectedTime()!=null){
         days=bill.getExpectedTime().intValue();
       }
       sb.append("已经被").append(jgName).append("受理，预计用时").append(days).append("天完成鉴定工作，请保持通讯设备畅通，及时关注鉴定进展情况，谢谢。");  
     }else if(bill.getStatus().equals(SfEntrust.STATUS_TH_WTF) 
       && (
         SfEntrust.STATUS_HE_ZHUN.equals(oldStatus) || 
         SfEntrust.STATUS_QUE_REN.equals(oldStatus) || 
         SfEntrust.STATUS_JIE_SHOU_JIANCAI.equals(oldStatus) || 
         SfEntrust.STATUS_KE_SHI_SHOULI.equals(oldStatus)
         )
        ){
       sb.append(jgName2);
       sb.append(su.getSjrInfo(bill,requestMeta));
       sb.append("被鉴定中心退回，").append(bill.getComment()).append(",请登录司法鉴定管理系统查看退回情况，如有疑问请联系鉴定中心，谢谢关注。");
     }else if(bill.getIsAccept()!=null && bill.getIsAccept().equals("N")){
       sb.append(jgName2);
       sb.append(su.getSjrInfo(bill,requestMeta));
       sb.append("没有被鉴定中心受理，具体情况请联系鉴定中心，谢谢关注。");
     }
    if(sb.length()>0){
      su.sendMsgToSjr(bill,sb.toString()); 
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
     WorkflowContext context=null;
    if(instanceId!=null || instanceId2!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.KESHI_SHOULI); 
      context=wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
      _saveSfWfHistory(context.getHistoryModel(),requestMeta);
    }else if(instanceId3!=null || instanceId4!=null){
      RequestMeta newMeta=(RequestMeta) BeanUtils.cloneBean(requestMeta);
      newMeta.setSvUserID(SfElementConstants.ZONGHE_SHOULI); 
      context=wfEngineAdapter.commit(qx.getComment(), qx, newMeta);
      _saveSfWfHistory(context.getHistoryModel(),requestMeta);
    }else{    
      context=wfEngineAdapter.commit(qx.getComment(), qx, requestMeta);
    }
     
     SfEntrust rtn=selectByPrimaryKey(qx.getEntrustId(), requestMeta);
     
     sendMsgAudit(rtn,requestMeta,oldQx.getStatus(),context.getNextExecutor());
     return rtn;
  }

/**
 * 以综合受理、科室受理角色操作时，记录对应的实际操作人
 * @param currentTaskModel
 * @param requestMeta
 */
private void _saveSfWfHistory(ActionHistoryModel history, RequestMeta requestMeta) {
  if(history==null)return;
  history.setExecutor(requestMeta.getSvUserID()); 
  history.setOwner(requestMeta.getSvUserID());
  zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.insert_sf_ActionHistory", history);
}

public SfEntrust newCommitFN(SfEntrust bill, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub

    //    System.out.println("newCommitFN 1++++++++++++++++++++++++++=" + qx.getAcceptDate());
  
  //鉴定机构自己哎中心现场录入时,送审时,自动生成受理编号,用于打印鉴定确认书
  boolean isJdjgInput=false;//是否鉴定机构录入
  if(requestMeta.containRole(ZcSettingConstants.R_SF_JDJG)||requestMeta.containRole(ZcSettingConstants.R_SF_JD_PERSON) ){
    if(bill.getAcceptCode()==null && !"N".equalsIgnoreCase(bill.getIsAccept())){
      String compoId="000";
      if(bill.getCoCode()==null || bill.getCoCode().equals("000")){
        compoId="SF_ENTRUST";
      }else{
        compoId="SF_ENTRUST"+bill.getCoCode();
      } 
      bill.setAcceptCode(zcEbBaseService.getAutoNumNo(bill, compoId, "CODE"));
      setMaterialsSlCode(bill);
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
    
   /* StringBuffer msg=new StringBuffer();
    if(!isJdjgInput){//发送消息给下一岗的执行者，有待审批的单据
      msg.append(bill.getEntrustor().getName()).append("提交了新的鉴定委托，编号为：").append(bill.getCode());
      msg.append(",鉴定类别为").append(bill.getMajor().getMajorName()).append(", 请及时登录鉴定管理系统进行确认，方便委托方及时打印委托书，提交检材.");
      
    }else{
      msg.append(bill.getEntrustor().getName()).append("提交了检材和鉴定委托书，委托书编号为：").append(bill.getCode());
      msg.append(",鉴定类别为").append(bill.getMajor().getMajorName()).append(",已通过综合室的确认，请登录鉴定管理系统进行受理，并鉴定委托确认书，安排鉴定工作.");
    }*/
    
    sendMsgAudit(bill, requestMeta, oldStatus,context.getNextExecutor());    
    
    return bill;
  }

/**
 * 设置检材的受理编号
 * 四位年号－受理顺序号－细分检材顺序号
 * @param bill
 */
  private void setMaterialsSlCode(SfEntrust bill) {

    if(bill.getAcceptCode()==null)return;
    
//    SfMaterialsMapper materialsMapper = (SfMaterialsMapper) SpringContext.getBean("materialsMapper");
    List lst=bill.getMaterials();
    if(lst==null || lst.size()==0)return;
    
//    materialsMapper.deleteByEntrustId(bill.getEntrustId());
    
    ZcSUtil zs=new ZcSUtil();
    for(int i=0;i<lst.size();i++){
      SfMaterials m=(SfMaterials) lst.get(i);
      if(m.getSlCode()!=null && m.getSlCode().trim().length()>0)continue;
      String s=zs.genJcSlCode(bill.getAcceptCode(), bill.getNd().intValue(),m.getMaterialType());      
      int k=i+1;
      m.setSlCode(s+"-"+zs.setZero(""+k, 2));
//      materialsMapper.insert(m);
    }
    
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
//	inData.setAcceptDate(ZcSUtil.getSysDate());
  return auditFN(inData,requestMeta);
	
}

 /**
  * 不受理，提交审批
 * @throws Exception 
  */
public SfEntrust unAcceptFN(SfEntrust inData, RequestMeta requestMeta) throws Exception {
	inData.setIsAccept("N");
	inData.setAcceptDate(ZcSUtil.getSysDate());
	inData.setAcceptor(requestMeta.getSvUserID()); 
	
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

public IZcMobileMsgService getZcMobileMsgService() {
  return zcMobileMsgService;
}

public void setZcMobileMsgService(IZcMobileMsgService zcMobileMsgService) {
  this.zcMobileMsgService = zcMobileMsgService;
}
  

public SfJdDocAuditMapper getJdDocAuditMapper() {
  return jdDocAuditMapper;
}

public void setJdDocAuditMapper(SfJdDocAuditMapper jdDocAuditMapper) {
  this.jdDocAuditMapper = jdDocAuditMapper;
}

/**
 * 检材条码和委托条码都进行检索
 */
public SfEntrust selectByBarCode(String barCode, RequestMeta requestMeta) {
  //委托条码
  SfEntrust entrust=(SfEntrust) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByBarCode", barCode);
  if(entrust!=null){
//    return selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
    return entrust;
  }
  //检材条码检索
  entrust=(SfEntrust) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectByMaterialBarCode", barCode);
  if(entrust!=null){
//    return selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
    return entrust;
  }
  return null;
}

public SfDocSendMapper getSfDocSendMapper() {
  return sfDocSendMapper;
}

public void setSfDocSendMapper(SfDocSendMapper sfDocSendMapper) {
  this.sfDocSendMapper = sfDocSendMapper;
}
}
