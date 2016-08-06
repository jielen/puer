package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
    rtn.digest();
    return rtn;
  }

  
  public SfEntrustor saveFN(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if (inData.getEntrustorId()==null) { 
       BigDecimal id=new BigDecimal(ZcSUtil.getNextVal(SfEntrustor.SEQ_SF_ENTRUSTOR_ID));
       inData.setEntrustorId(id);
       
       insert(inData,requestMeta);
    }else{
      update(inData,requestMeta);
    }
    
    return inData;
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
    		zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.deleteWtfUser", inData.getEntrustorId());
    		userService.updateAsEmpLogin(wtfUser.getUserId(), false);
    	}else if(!wtfUser.getUserId().equals(inData.getUser().getUserId())){
    		userService.updateAsEmpLogin(wtfUser.getUserId(), false);
    		zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.deleteWtfUser", inData.getEntrustorId());
    		 
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
	    SfEntrustorUser wtfUser2=new SfEntrustorUser();
	    wtfUser2.setUserId(inData.getUser().getUserId().trim());
	    wtfUser2.setEntrustorId(inData.getEntrustorId());
	    zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfEntrustorMapper.insertWtfUser", wtfUser2);
		addUser(inData);    	
    } 
  }


  private void insert(SfEntrustor inData, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    entrustorMapper.insert(inData); 
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
