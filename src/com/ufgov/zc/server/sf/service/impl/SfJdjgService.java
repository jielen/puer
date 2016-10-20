package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.apache.bcel.generic.ARRAYLENGTH;

import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdjgService;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SfJdjgService implements ISfJdjgService {


	  private IZcEbBaseService zcEbBaseService;
	  
	public List getMainDataLst(ElementConditionDto elementConditionDto,
			RequestMeta requestMeta) {
	 return	zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", elementConditionDto);
		 
	}

	
	public SfJdjg selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
		
		SfJdjg rtn= (SfJdjg) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectByPrimaryKey", id);
		
		List certLst=zcEbBaseService.queryDataForList("com.ufgov.zc.server.sf.dao.SfCertificateMapper.selectByOwner", id);
		rtn.setCertificatLst(certLst==null?new ArrayList():certLst);
		rtn.digest();
		return rtn;
	}

	
	public SfJdjg saveFN(SfJdjg inData, RequestMeta requestMeta) {
		if(inData.getJgId()==null){
			inData.setJgId(new BigDecimal(ZcSUtil.getNextVal(SfJdjg.SEQ_SF_JDJG_ID)));
			insert(inData,requestMeta);
		}else{
			update(inData,requestMeta);
		}
		return inData;
	}

	
	private void update(SfJdjg inData, RequestMeta requestMeta) {
		List l=new ArrayList();
		l.add(inData);
		zcEbBaseService.updateObjectList("com.ufgov.zc.server.sf.dao.SfJdjgMapper.updateByPrimaryKey", l);
		zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfCertificateMapper.deleteByOwner", inData.getJgId());
	    if(inData.getCertificatLst()!=null){
	    	for(int i=0;i<inData.getCertificatLst().size();i++){
	    		SfCertificate cer=(SfCertificate) inData.getCertificatLst().get(i);
	    		cer.setZfOwnerId(inData.getJgId());
	    		if(cer.getCerId()==null){
	    			cer.setCerId(new BigDecimal(ZcSUtil.getNextVal(SfCertificate.SEQ_SF_CERTIFICATE)));
	    		}
	    		zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfCertificateMapper.insert", cer);
	    	}
	    }
	}


	private void insert(SfJdjg inData, RequestMeta requestMeta) {
		zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.insert", inData);		
	    if(inData.getCertificatLst()!=null){
	    	for(int i=0;i<inData.getCertificatLst().size();i++){
	    		SfCertificate cer=(SfCertificate) inData.getCertificatLst().get(i);
	    		cer.setZfOwnerId(inData.getJgId());
	    		if(cer.getCerId()==null){
	    			cer.setCerId(new BigDecimal(ZcSUtil.getNextVal(SfCertificate.SEQ_SF_CERTIFICATE)));
	    		}
	    		zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfCertificateMapper.insert", cer);
	    	}
	    }
	}


	public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
		zcEbBaseService.insertObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.deleteByPrimaryKey", id);		
		zcEbBaseService.delete("com.ufgov.zc.server.sf.dao.SfCertificateMapper.deleteByOwner", id); 
	}


	public IZcEbBaseService getZcEbBaseService() {
		return zcEbBaseService;
	}


	public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
		this.zcEbBaseService = zcEbBaseService;
	}

}
