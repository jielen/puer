package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.util.Date;

import com.ufgov.zc.common.commonbiz.model.WfAware;
import com.ufgov.zc.common.system.Digestable;
import com.ufgov.zc.common.system.util.DigestUtil;

public class ZcPPro extends ZcBaseBill{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804831084594680314L;

	private String chrId;

	private String chrName;

	private String enId;

	private String enName;

	private String biId;

	private String biCode;

	public String getBiCode() {
		return biCode;
	}

	public void setBiCode(String biCode) {
		this.biCode = biCode;
	}

	private String biName;

	private int isDeleted;

	private String createUser;

	private Date createDate;

	public String getChrId() {
		return chrId;
	}

	public void setChrId(String chrId) {
		this.chrId = chrId;
	}

	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getEnId() {
		return enId;
	}

	public void setEnId(String enId) {
		this.enId = enId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getBiId() {
		return biId;
	}

	public void setBiId(String biId) {
		this.biId = biId;
	}

	public String getBiName() {
		return biName;
	}

	public void setBiName(String biName) {
		this.biName = biName;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
