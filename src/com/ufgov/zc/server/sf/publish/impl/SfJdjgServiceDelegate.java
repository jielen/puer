package com.ufgov.zc.server.sf.publish.impl;

import java.math.BigDecimal;

import java.util.List;

import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.publish.ISfJdjgServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.service.ISfJdjgService;


public class SfJdjgServiceDelegate implements ISfJdjgServiceDelegate
		 {
	private ISfJdjgService sfJdjgService;
	
	public List getMainDataLst(ElementConditionDto elementConditionDto,
			RequestMeta requestMeta) {
		return sfJdjgService.getMainDataLst(elementConditionDto, requestMeta);
	}

	
	public SfJdjg selectByPrimaryKey(BigDecimal id, RequestMeta requestMeta) {
		return sfJdjgService.selectByPrimaryKey(id, requestMeta);
	}

	
	public SfJdjg saveFN(SfJdjg inData, RequestMeta requestMeta) {
		return sfJdjgService.saveFN(inData, requestMeta);
	}

	
	public void deleteByPrimaryKeyFN(BigDecimal id, RequestMeta requestMeta) {
		sfJdjgService.deleteByPrimaryKeyFN(id, requestMeta);
	}


	public ISfJdjgService getSfJdjgService() {
		return sfJdjgService;
	}


	public void setSfJdjgService(ISfJdjgService sfJdjgService) {
		this.sfJdjgService = sfJdjgService;
	}

}
