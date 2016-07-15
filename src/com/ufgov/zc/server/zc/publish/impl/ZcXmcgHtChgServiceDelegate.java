package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.model.ZcXmcgHtChg;
import com.ufgov.zc.common.zc.publish.IZcXmcgHtChgServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcXmcgHtChgService;

public class ZcXmcgHtChgServiceDelegate implements IZcXmcgHtChgServiceDelegate {

	private IZcXmcgHtChgService zcXmcgHtChgService;


	public IZcXmcgHtChgService getZcXmcgHtChgService() {
		return zcXmcgHtChgService;
	}


	public void setZcXmcgHtChgService(IZcXmcgHtChgService zcXmcgHtChgService) {
		this.zcXmcgHtChgService = zcXmcgHtChgService;
	}


	public List getZcXmcgHtChg(ElementConditionDto dto, RequestMeta meta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.getZcXmcgHtChg(dto, meta);
	}


	public ZcXmcgHtChg updateZcXmcgHtChgFN(ZcXmcgHtChg zcXmcgHtChg,
			boolean flag, String serverAdd, RequestMeta requestMeta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.updateZcXmcgHtChgFN(zcXmcgHtChg, flag, serverAdd, requestMeta);
	}


	public ZcXmcgHtChg selectByPrimaryKey(String zcHtChgId,
			RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.selectByPrimaryKey(zcHtChgId, requestMeta);
	}


	public void deleteByPrimaryKeyFN(String zcHtChgId, boolean flag,
			String serverAdd, RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		zcXmcgHtChgService.deleteByPrimaryKeyFN(zcHtChgId, flag, serverAdd, requestMeta);
	}


	public ZcXmcgHt selectZcXmcgHtByPrimaryKey(String zcHtCode,
			RequestMeta requestMeta) {
		return zcXmcgHtChgService.selectZcXmcgHtByPrimaryKey(zcHtCode, requestMeta);
	}



	public ZcXmcgHtChg newCommitFN(ZcXmcgHtChg ht, boolean flag,
			RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.newCommitFN(ht, flag, requestMeta);
	}



	public ZcXmcgHtChg callbackFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.callbackFN(ht, requestMeta);
	}



	public ZcXmcgHtChg untreadFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.untreadFN(ht, requestMeta);
	}



	public ZcXmcgHtChg unAuditFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.unAuditFN(ht, requestMeta);
	}



	public ZcXmcgHtChg auditFN(ZcXmcgHtChg ht, RequestMeta requestMeta) {
		// TCJLODO Auto-generated method stub
		return zcXmcgHtChgService.auditFN(ht, requestMeta);
	}

}
