package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class ZcEbEntrustCancelDetail extends ZcBaseBill implements Serializable {

	/**
   * 
   */
	private static final long serialVersionUID = -254198888147393000L;

	private String entrustCancelId;

	private BigDecimal packSum;

	private String packDesc;

	private String packCode;

	private String packName;

	private String tempId;
	private Map isAll;

	public Map getIsAll() {
		return isAll;
	}

	public void setIsAll(Map isAll) {
		this.isAll = isAll;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getEntrustCancelId() {
		return entrustCancelId;
	}

	public void setEntrustCancelId(String entrustCancelId) {
		this.entrustCancelId = entrustCancelId;
	}

	public BigDecimal getPackSum() {
		return packSum;
	}

	public void setPackSum(BigDecimal packSum) {
		this.packSum = packSum;
	}

	public String getPackDesc() {
		return packDesc;
	}

	public void setPackDesc(String packDesc) {
		this.packDesc = packDesc;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

}
