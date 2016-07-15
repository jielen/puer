package com.ufgov.zc.common.zc.model;

import java.util.List;

public class ZcEbSupQualification extends TreeNodeValueObject {

	private String qualifId;

	private String qualifCode;

	private String qualifName;

	private String qualifType;

	private String quallifTypeName;

	private String status;

	private List levs;

	public List getLevs() {
		return levs;
	}

	public void setLevs(List levs) {
		this.levs = levs;
	}

	public String getQuallifTypeName() {
		return quallifTypeName;
	}

	public void setQuallifTypeName(String quallifTypeName) {
		this.quallifTypeName = quallifTypeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQualifId() {
		return qualifId;
	}

	public void setQualifId(String qualifId) {
		this.qualifId = qualifId;
	}

	public String getQualifCode() {
		return qualifCode;
	}

	public void setQualifCode(String qualifCode) {
		this.qualifCode = qualifCode;
	}

	public String getQualifName() {
		return qualifName;
	}

	public void setQualifName(String qualifName) {
		this.qualifName = qualifName;
	}

	public String getQualifType() {
		return qualifType;
	}

	public void setQualifType(String qualifType) {
		this.qualifType = qualifType;
	}

}
