package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfEntrustorUser extends ZcBaseBill{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2073764912914404099L;

	private String userId;
	
	private BigDecimal entrustorId;
	
	public BigDecimal getEntrustorId() {
		return entrustorId;
	}
	public void setEntrustorId(BigDecimal entrustorId) {
		this.entrustorId = entrustorId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	} 
}
