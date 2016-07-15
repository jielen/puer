package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcYearPlan;
import com.ufgov.zc.common.zc.publish.IZcYearEndServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcYearEndService;

public class ZcYearEndServiceDelegate implements IZcYearEndServiceDelegate {

	private IZcYearEndService zcYearEndService;

	public IZcYearEndService getZcYearEndService() {
		return zcYearEndService;
	}

	public void setZcYearEndService(IZcYearEndService zcYearEndService) {
		this.zcYearEndService = zcYearEndService;
	}

	public List getZcPProMake(ElementConditionDto dto, RequestMeta meta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		return zcYearEndService.getZcPProMake(dto, meta);
	}

	public void updateProEnd(String zcMakeCode, boolean flag, String serverAdd,
			RequestMeta requestMeta) throws Exception {
		// TCJLODO Auto-generated method stub
		zcYearEndService.updateProEnd(zcMakeCode, flag, serverAdd, requestMeta);
	}

	public void updateYearEnd(String zcMakeCode, RequestMeta requestMeta)
			throws Exception {
		// TCJLODO Auto-generated method stub
		zcYearEndService.updateYearEnd(zcMakeCode, requestMeta);
	}

	/**
	 * 获取结转锁定的项目
	 */
	public List getZcCarraryMake(ElementConditionDto dto, RequestMeta meta)
			throws Exception {

		return zcYearEndService.getZcCarraryMake(dto, meta);
	}

	/**
	 * 设置项目状态
	 */
	public void updateMakeYepFlagByFlag(ElementConditionDto dto,
			String serverAdd, RequestMeta meta) throws Exception {
		zcYearEndService.updateMakeYepFlagByFlag(dto, serverAdd, meta);
	}

	/**
	 * 结转立项
	 */
	public void carraryNewMake(ElementConditionDto dto, String serverAdd,
			RequestMeta meta) throws Exception {
		// TCJLODO Auto-generated method stub
		zcYearEndService.carraryNewMake(dto, serverAdd, meta);
	}

	public void carraryNewMakeByManual(ZcYearPlan yearPlan, String serverAdd,
			RequestMeta meta) throws Exception {
		zcYearEndService.carraryNewMakeByManual(yearPlan, serverAdd, meta);
	}

}
