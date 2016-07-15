package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcPPro;
import com.ufgov.zc.common.zc.publish.IZcPProDelegate;
import com.ufgov.zc.server.zc.service.IZcPProService;

public class ZcPProDelegate implements IZcPProDelegate {

	private IZcPProService zcPProService;

	public IZcPProService getZcPProService() {
		return zcPProService;
	}

	public void setZcPProService(IZcPProService zcPProService) {
		this.zcPProService = zcPProService;
	}

	public void deleteZcPPro(String id, RequestMeta requestMeta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		zcPProService.deleteZcPPro(id);
	}

	public void updateZcPPro(ZcPPro zcPPro, RequestMeta requestMeta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		zcPProService.updateZcPPro(zcPPro);
	}

	public void saveZcPPro(ZcPPro zcPPro, RequestMeta requestMeta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		zcPProService.saveZcPPro(zcPPro);
	}

	public List getZcPPro(ElementConditionDto elementConditionDto,
			RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		return zcPProService.getZcPPro(elementConditionDto);
	}


	public ZcPPro getZcPProById(String id, RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		return zcPProService.getZcPProById(id);
	}

}
