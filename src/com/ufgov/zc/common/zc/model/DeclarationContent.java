package com.ufgov.zc.common.zc.model;

import java.util.Date;

public class DeclarationContent extends Content {
	private String ID;
	private String declarationType;
	private String bidType;
	private String region;
	private String bidWay;
	private String bidMenu;
	private Date openBidDate;
	private String openBidAddress;
	private String projCode;
	private String packCode;
	private String projName;
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	private String orgName;
	private String executorName;
	private String downloadInfo;


	public String getDownloadInfo() {
		return downloadInfo;
	}

	public void setDownloadInfo(String downloadInfo) {
		this.downloadInfo = downloadInfo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDeclarationType() {
		return declarationType;
	}

	public void setDeclarationType(String declarationType) {
		this.declarationType = declarationType;
	}

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBidWay() {
		return bidWay;
	}

	public void setBidWay(String bidWay) {
		this.bidWay = bidWay;
	}

	public String getBidMenu() {
		return bidMenu;
	}

	public void setBidMenu(String bidMenu) {
		this.bidMenu = bidMenu;
	}

	public Date getOpenBidDate() {
		return openBidDate;
	}

	public void setOpenBidDate(Date openBidDate) {
		this.openBidDate = openBidDate;
	}

	public String getOpenBidAddress() {
		return openBidAddress;
	}

	public void setOpenBidAddress(String openBidAddress) {
		this.openBidAddress = openBidAddress;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}


}
