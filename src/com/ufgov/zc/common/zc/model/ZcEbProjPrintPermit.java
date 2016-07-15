package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

public class ZcEbProjPrintPermit  extends ZcBaseBill implements Serializable {
	
	private static final long serialVersionUID = 559880161103601396L;

	private String projCode;
	
	private String status;

	private Date openBidTime;

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOpenBidTime() {
		return openBidTime;
	}

	public void setOpenBidTime(Date openBidTime) {
		this.openBidTime = openBidTime;
	}
	
}
