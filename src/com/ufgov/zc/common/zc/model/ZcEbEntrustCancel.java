package com.ufgov.zc.common.zc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZcEbEntrustCancel extends ZcBaseBill implements Serializable {

	/**
   * 
   */
	private static final long serialVersionUID = -3125029505990586610L;

	private String entrustCancelId;

	private String sn;

	private String zcMakeCode;

	private String zcMakeName;

	private String zcMakeLinkman;

	private String zcMakeTel;

	private String status;

	private String zcInputCode;

	private Date zcInputDate;

	private BigDecimal zcMoneyBiSum;

	// 采购单位申报的总计划金额。
	private BigDecimal zcMakeSum;

	private String zcPifuCgfs;

	private String zcDiyuDaima;

	private Date zcWeitoDate;

	private String projDocMan;

	private String projBidMan;

	private String projCode;

	private String projName;

	private List packList = new ArrayList();

	private String remark;

	private String snCode;// 中心任务单编号

	private int packNum;

	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getPackNum() {
		return packNum;
	}

	public void setPackNum(int packNum) {
		this.packNum = packNum;
	}

	public String getEntrustCancelId() {
		return entrustCancelId;
	}

	public void setEntrustCancelId(String entrustCancelId) {
		this.entrustCancelId = entrustCancelId;
		this.setTitleField(entrustCancelId);
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZcInputCode() {
		return zcInputCode;
	}

	public void setZcInputCode(String zcInputCode) {
		this.zcInputCode = zcInputCode;
	}

	public Date getZcInputDate() {
		return zcInputDate;
	}

	public void setZcInputDate(Date zcInputDate) {
		this.zcInputDate = zcInputDate;
	}

	public BigDecimal getZcMoneyBiSum() {
		return zcMoneyBiSum;
	}

	public void setZcMoneyBiSum(BigDecimal zcMoneyBiSum) {
		this.zcMoneyBiSum = zcMoneyBiSum;
	}

	public BigDecimal getZcMakeSum() {
		return zcMakeSum;
	}

	public void setZcMakeSum(BigDecimal zcMakeSum) {
		this.zcMakeSum = zcMakeSum;
	}

	public String getZcPifuCgfs() {
		return zcPifuCgfs;
	}

	public void setZcPifuCgfs(String zcPifuCgfs) {
		this.zcPifuCgfs = zcPifuCgfs;
	}

	public String getZcDiyuDaima() {
		return zcDiyuDaima;
	}

	public void setZcDiyuDaima(String zcDiyuDaima) {
		this.zcDiyuDaima = zcDiyuDaima;
	}

	public Date getZcWeitoDate() {
		return zcWeitoDate;
	}

	public void setZcWeitoDate(Date zcWeitoDate) {
		this.zcWeitoDate = zcWeitoDate;
	}

	public String getProjDocMan() {
		return projDocMan;
	}

	public void setProjDocMan(String projDocMan) {
		this.projDocMan = projDocMan;
	}

	public String getProjBidMan() {
		return projBidMan;
	}

	public void setProjBidMan(String projBidMan) {
		this.projBidMan = projBidMan;
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

	public List getPackList() {
		return packList;
	}

	public void setPackList(List packList) {
		this.packList = packList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

}
