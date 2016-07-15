package com.ufgov.zc.common.zc.model;

import java.util.Date;

/**

 * @ClassName: ZcEbEntrustBullin

 * @Description: 已上网的采购任务（招标公告）

 * @date: Sep 17, 2012 10:59:47 AM

 * @version: V1.0

 * @since: 1.0

 * @author: yuzz

 * @modify:

 */
public class ZcEbEntrustBullin extends ZcEbEntrustBull{
	
	private String projCode;

	private String projName;

	private Date shellStartTime;

	private Date openBidTime;

	private String openBidAddress;
	
	private String fileId;

	private String fileName;
	
	private String shellStartTimeStr;

	private String openBidTimeStr;
	

	public String getShellStartTimeStr() {
		return shellStartTimeStr;
	}

	public void setShellStartTimeStr(String shellStartTimeStr) {
		this.shellStartTimeStr = shellStartTimeStr;
	}

	public String getOpenBidTimeStr() {
		return openBidTimeStr;
	}

	public void setOpenBidTimeStr(String openBidTimeStr) {
		this.openBidTimeStr = openBidTimeStr;
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

	public Date getShellStartTime() {
		return shellStartTime;
	}

	public void setShellStartTime(Date shellStartTime) {
		this.shellStartTime = shellStartTime;
	}

	public Date getOpenBidTime() {
		return openBidTime;
	}

	public void setOpenBidTime(Date openBidTime) {
		this.openBidTime = openBidTime;
	}

	public String getOpenBidAddress() {
		return openBidAddress;
	}

	public void setOpenBidAddress(String openBidAddress) {
		this.openBidAddress = openBidAddress;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
