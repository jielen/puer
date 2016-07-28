package com.ufgov.zc.server.sf.publish.impl;

import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.sf.publish.ISfJdDocCodeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdDocCodeService;

public class SfJdDocCodeServiceDelegate implements ISfJdDocCodeServiceDelegate {

	private ISfJdDocCodeService sfJdDocCodeService;
	
	public List getMainDataLst(ElementConditionDto dto, RequestMeta requestMeta) {
		return sfJdDocCodeService.getMainDataLst(dto, requestMeta);
	}

	
	public SfJdDocCode selectByPrimaryKey(String pinJieCode,			RequestMeta requestMeta) {
		return sfJdDocCodeService.selectByPrimaryKey(pinJieCode, requestMeta);
	}

	
	public SfJdDocCode saveFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		return sfJdDocCodeService.saveFN(inData, requestMeta);
	}

	
	public void deleteByPrimaryKeyFN(String pinJieCode, RequestMeta requestMeta) {
		sfJdDocCodeService.deleteByPrimaryKeyFN(pinJieCode, requestMeta);
	}

	
	public int updateByPrimaryKeyFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		return sfJdDocCodeService.updateByPrimaryKeyFN(inData, requestMeta);
	}


	public ISfJdDocCodeService getSfJdDocCodeService() {
		return sfJdDocCodeService;
	}


	public void setSfJdDocCodeService(ISfJdDocCodeService sfJdDocCodeService) {
		this.sfJdDocCodeService = sfJdDocCodeService;
	}

}
