package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZcEbEntrustBull  extends ZcBaseBill implements Serializable {
	
	private static final long serialVersionUID = -9200980881285335108L;

	private String sn;

	private String snCode;

	private Date zcWeitoDate;

	private String zcMakeCode;

	private String zcMakeName;

	private String orgCode;

	private String zcMakeLinkman;
	
	private String zcMakeTel;
	
	private BigDecimal zcMoneyBiSum;
	
	private String zcPifuCgfs;
	
	private String executorName;
	
	private String phone;
	
	private String status;
	
	private String optStatus;
	
	private String superintendent;

	private String superintendentName;
	
	private String attn;
	
	private String attnName;

	private String superintendentOrg;
	
	private String sPhone;//文件经办人联系方式

	private String aPhone;//开标经办人联系方式

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getaPhone() {
		return aPhone;
	}

	public void setaPhone(String aPhone) {
		this.aPhone = aPhone;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public Date getZcWeitoDate() {
		return zcWeitoDate;
	}

	public void setZcWeitoDate(Date zcWeitoDate) {
		this.zcWeitoDate = zcWeitoDate;
	}

	public String getZcMakeCode() {
		return zcMakeCode;
	}

	public void setZcMakeCode(String zcMakeCode) {
		this.zcMakeCode = zcMakeCode;
	}

	public String getZcMakeName() {
		return zcMakeName;
	}

	public void setZcMakeName(String zcMakeName) {
		this.zcMakeName = zcMakeName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getZcMakeLinkman() {
		return zcMakeLinkman;
	}

	public void setZcMakeLinkman(String zcMakeLinkman) {
		this.zcMakeLinkman = zcMakeLinkman;
	}

	public String getZcMakeTel() {
		return zcMakeTel;
	}

	public void setZcMakeTel(String zcMakeTel) {
		this.zcMakeTel = zcMakeTel;
	}

	public BigDecimal getZcMoneyBiSum() {
		return zcMoneyBiSum;
	}

	public void setZcMoneyBiSum(BigDecimal zcMoneyBiSum) {
		this.zcMoneyBiSum = zcMoneyBiSum;
	}

	public String getZcPifuCgfs() {
		return zcPifuCgfs;
	}

	public void setZcPifuCgfs(String zcPifuCgfs) {
		this.zcPifuCgfs = zcPifuCgfs;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOptStatus() {
		return optStatus;
	}

	public void setOptStatus(String optStatus) {
		this.optStatus = optStatus;
	}

	public String getSuperintendent() {
		return superintendent;
	}

	public void setSuperintendent(String superintendent) {
		this.superintendent = superintendent;
	}

	public String getSuperintendentName() {
		return superintendentName;
	}

	public void setSuperintendentName(String superintendentName) {
		this.superintendentName = superintendentName;
	}

	public String getAttn() {
		return attn;
	}

	public void setAttn(String attn) {
		this.attn = attn;
	}

	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}

	public String getSuperintendentOrg() {
		return superintendentOrg;
	}

	public void setSuperintendentOrg(String superintendentOrg) {
		this.superintendentOrg = superintendentOrg;
	}
	
}
