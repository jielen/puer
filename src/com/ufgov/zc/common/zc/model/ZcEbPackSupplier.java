package com.ufgov.zc.common.zc.model;

public class ZcEbPackSupplier extends ZcBaseBill{
	
	private static final long serialVersionUID = -752491417334843679L;

	private String packCode;
	
	private String projCode;
	
	private String providerCode;

	private String tempId;
	
	private ZcEbSupplier zcEbSupplier = new ZcEbSupplier();

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public ZcEbSupplier getZcEbSupplier() {
		return zcEbSupplier;
	}

	public void setZcEbSupplier(ZcEbSupplier zcEbSupplier) {
		this.zcEbSupplier = zcEbSupplier;
	}

}
