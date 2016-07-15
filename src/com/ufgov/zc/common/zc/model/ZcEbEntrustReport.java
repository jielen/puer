package com.ufgov.zc.common.zc.model;

import java.math.BigDecimal;

public class ZcEbEntrustReport extends ZcEbEntrustBull{
	
	private String projCode;

	private String projName;
	
	private String providerCode;
	
	private BigDecimal bidSum;

	private String providerName;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public BigDecimal getBidSum() {
		return bidSum;
	}

	public void setBidSum(BigDecimal bidSum) {
		this.bidSum = bidSum;
	}
	
	

}
