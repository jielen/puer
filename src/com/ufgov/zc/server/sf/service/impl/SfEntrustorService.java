package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfEntrustorUser;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.zc.model.SysEmp;
import com.ufgov.zc.common.zc.model.ZcEbSupplier;
import com.ufgov.zc.server.sf.dao.SfEntrustorMapper;
import com.ufgov.zc.server.sf.service.ISfEntrustorService;
import com.ufgov.zc.server.system.service.IUserService;
import com.ufgov.zc.server.system.util.AsOptionUtil;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;
import com.ufgov.zc.server.zc.util.GeneralFunc;

public class SfEntrustorService implements ISfEntrustorService {

  private SfEntrustorMapper entrustorMapper;

  private IUserService userService;
  
  private IZcEbBaseService zcEbBaseService;
  
  public SfEntrustorMapper getEntrustorMapper() {
    return entrustorMapper;
  }


  public void setEntrustorMapper(SfEntrustorMapper entrustorMapper) {
    this.entrustorMapper = entrustorMapper;
  }


  public List getEntrustorLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.getEntrustorLst(elementConditionDto);
  }

  
  public SfEntrustor selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    SfEntrustor rtn= entrustorMapper.selectByPrimaryKey(id);
    if(rtn.getUser()!=null && rtn.getUser().getPassword()!=null){
    	rtn.getUser().setPassword(GeneralFunc.recodePwd(rtn.getUser().getPassword()));
    	rtn.getUser().setPasswordConfrim(rtn.getUser().getPassword());
    }
    if(rtn.getParentId()!=null){
      SfEntrustor p=entrustorMapper.selectByPrimaryKey(rtn.getParentId());
      if(p!=null){
        rtn.setParentName(p.getName());
      }
    }
    rtn.digest();
    return rtn;
  }

  
  public SfEntrustor saveFN(SfEntrustor inData, RequestMeta requestMeta) throws SfBusinessException {
    // TCJLODO Auto-generated method stub
    if(isSameName(inData, requestMeta)){
      throw new SfBusinessException(inData.getName()+"已经存在了.");
    }
    if(isSameShortName(inData, requestMeta)){
      throw new SfBusinessException("简称："+inData.getShortName()+"已经存在了，请重新输入一个简称.");
    }
    if (inData.getEntrustorId()==null) { 
      ZcSUtil su=new ZcSUtil();
       BigDecimal id=new BigDecimal(su.getNextVal(SfEntrustor.SEQ_SF_ENTRUSTOR_ID));
       inData.setEntrustorId(id);
       
       insert(inData,requestMeta);
    }else{
      update(inData,requestMeta);
    }
    
    return inData;
  } 
  
  private boolean isSameShortName(SfEntrustor bill,RequestMeta meta){
    if(bill.getShortName()==null || bill.getShortName().trim().length()==0){
      return false;
    }
    ElementConditionDto dto=new ElementConditionDto();
    dto.setDattr3(bill.getShortName());
    List lst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getEntrustorLst", dto);
    if(lst==null)return false;
    for(int i=0;i<lst.size();i++){
      SfEntrustor t=(SfEntrustor) lst.get(i);
      if(t.getEntrustorId().equals(bill.getEntrustorId())){
        continue;
      }
      if(t.getShortName().equals(bill.getShortName())){
        return true;
      }
    }
    return false;
  }
  
  private boolean isSameName(SfEntrustor bill,RequestMeta meta){
    ElementConditionDto dto=new ElementConditionDto();
    dto.setDattr2(bill.getName());
    List lst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getEntrustorLst", dto);
    if(lst==null)return false;
    for(int i=0;i<lst.size();i++){
      SfEntrustor t=(SfEntrustor) lst.get(i);
      if(t.getEntrustorId().equals(bill.getEntrustorId())){
        continue;
      }
      if(t.getName().equals(bill.getName())){
        return true;
      }
    }
    return false;
  }


void addUser(SfEntrustor entrustor) {

	    GregorianCalendar g = new GregorianCalendar();
	    int nd = g.get(Calendar.YEAR);

	    String groupId = AsOptionUtil.getInstance().getOptionVal(SfEntrustor.OPT_SF_WTF_GROUP_ID);
	    String orgId = AsOptionUtil.getInstance().getOptionVal(SfEntrustor.OPT_SF_WTF_ORG_ID);
	    String coCode = AsOptionUtil.getInstance().getOptionVal(SfEntrustor.OPT_SF_WTF_CO_CODE);
	    String posiCode = AsOptionUtil.getInstance().getOptionVal(SfEntrustor.OPT_SF_WTF_POSI_ID);

	    User user = new User();
	    user.setUserId(entrustor.getUser().getUserId().trim()); 
	    user.setUserName(entrustor.getName());
	    user.setPassword(GeneralFunc.encodePwd(entrustor.getUser().getPassword()));

	    userService.addUser(user, coCode, orgId, posiCode, groupId, "" + nd, SfElementConstants.VAL_Y.equals(entrustor.getIsLogin()));
	  }

  
  private void update(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.updateByPrimaryKey(inData); 
    SfEntrustorUser wtfUser=(SfEntrustorUser) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getWtfUser", inData.getEntrustorId());
    if(wtfUser!=null){
    	if(inData.getUser()==null || inData.getUser().getUserId()==null ||inData.getUser().getUserId().trim().length()==0){
    		zcEbBaseService.deleteFN("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.deleteWtfUser", inData.getEntrustorId());
    		userService.updateAsEmpLogin(wtfUser.getUserId(), false);
    	}else if(!wtfUser.getUserId().equals(inData.getUser().getUserId())){
    		userService.updateAsEmpLogin(wtfUser.getUserId(), false);
    		zcEbBaseService.deleteFN("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.deleteWtfUser", inData.getEntrustorId());
    		 
	    	    SfEntrustorUser wtfUser2=new SfEntrustorUser();
	    	    wtfUser2.setUserId(inData.getUser().getUserId().trim());
	    	    wtfUser2.setEntrustorId(inData.getEntrustorId());
	    	    zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.insertWtfUser", wtfUser2);
	    		addUser(inData);
    		 
    	}else if(wtfUser.getUserId().equals(inData.getUser().getUserId())){
    		//更新用户的密码信息
    		List userLst=new ArrayList(); 
    		User user=new User();
    		user.setUserId(inData.getUser().getUserId());
    		user.setPassword(GeneralFunc.encodePwd(inData.getUser().getPassword())); 
    		userLst.add(user);
    		zcEbBaseService.updateObjectList("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.updateUserPasswd", userLst); 
    		userService.updateAsEmpLogin(inData.getUser().getUserId(), SfElementConstants.VAL_Y.equals(inData.getIsLogin()));
    	}
    }else{
      if(inData.getUser()!=null && inData.getUser().getUserId()!=null){
  	    SfEntrustorUser wtfUser2=new SfEntrustorUser();
  	    wtfUser2.setUserId(inData.getUser().getUserId().trim());
  	    wtfUser2.setEntrustorId(inData.getEntrustorId());
  	    zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.insertWtfUser", wtfUser2);
  	    addUser(inData);  
      }
    } 
  }


  private void insert(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.insert(inData); 
    if(inData.getUser()==null ||inData.getUser().getUserId()==null){
    	return;
    }
    SfEntrustorUser wtfUser=new SfEntrustorUser();
    wtfUser.setUserId(inData.getUser().getUserId().trim());
    wtfUser.setEntrustorId(inData.getEntrustorId());
    zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.insertWtfUser", wtfUser);
    addUser(inData);
  }


  public IZcEbBaseService getZcEbBaseService() {
	return zcEbBaseService;
}


public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
	this.zcEbBaseService = zcEbBaseService;
}


public boolean isUsing(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.isUsing(id) ;
  }

  
  public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.deleteByPrimaryKey(id);
  }


   
  public SfEntrustor selectByName(String name, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return entrustorMapper.selectByName(name);
  }


public IUserService getUserService() {
	return userService;
}


public void setUserService(IUserService userService) {
	this.userService = userService;
}




}
