package com.ufgov.zc.common.console.model;

import java.io.Serializable;

import com.ufgov.zc.common.system.constants.NumLimConstants;

public class AsRoleNumLim implements Serializable {

	private static final long serialVersionUID = -5491317493813602017L;

	private String roleId;

	private String funcId;

	private String compoId;

	private String ctrlField;

	private String granRange;

	private String revoRange;

	private String isGran;

	private String isRelation;

	private String grantedUserID;

	private String grantUserCoCode;

	private String grantUserOrgCode;

	private String grantUserPosiCode;

	private String grantUserPosiId;

	private String grantUserId;

	public String getCompoId() {
		return compoId;
	}

	public void setCompoId(String compoId) {
		this.compoId = compoId;
	}

	public String getCtrlField() {
		return ctrlField;
	}

	public void setCtrlField(String ctrlField) {
		this.ctrlField = ctrlField;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getGranRange() {
		return granRange;
	}

	public void setGranRange(String granRange) {
		this.granRange = granRange;
	}

	public String getIsGran() {
		return isGran;
	}

	public void setIsGran(String isGran) {
		this.isGran = isGran;
	}

	public String getIsRelation() {
		return isRelation;
	}

	public void setIsRelation(String isRelation) {
		this.isRelation = isRelation;
	}

	public String getRevoRange() {
		return revoRange;
	}

	public void setRevoRange(String revoRange) {
		this.revoRange = revoRange;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getGrantedUserID() {
		return grantedUserID;
	}

	public void setGrantedUserID(String grantedUserID) {
		this.grantedUserID = grantedUserID;
	}

	public String getGrantUserCoCode() {
		return grantUserCoCode;
	}

	public void setGrantUserCoCode(String grantUserCoCode) {
		this.grantUserCoCode = grantUserCoCode;
	}

	public String getGrantUserOrgCode() {
		return grantUserOrgCode;
	}

	public void setGrantUserOrgCode(String grantUserOrgCode) {
		this.grantUserOrgCode = grantUserOrgCode;
	}

	public String getGrantUserPosiCode() {
		return grantUserPosiCode;
	}

	public void setGrantUserPosiCode(String grantUserPosiCode) {
		this.grantUserPosiCode = grantUserPosiCode;
	}

	public String getGrantUserPosiId() {
		return grantUserPosiId;
	}

	public void setGrantUserPosiId(String grantUserPosiId) {
		this.grantUserPosiId = grantUserPosiId;
	}

	public String getGrantUserId() {
		return grantUserId;
	}

	public void setGrantUserId(String grantUserId) {
		this.grantUserId = grantUserId;
	}

	public String toSql() {
		if (ctrlField.equals(NumLimConstants.SQL_CONDITION))
			return granRange;
		else if (isGran.equals(NumLimConstants.GRAN))
			if (granRange.trim().toLowerCase().startsWith("like")
					|| granRange.trim().toLowerCase().startsWith("=")) {
				return ctrlField + " " + granRange;
			} else {
				return ctrlField + " in (" + granRange + ")";
			}
		else if (isGran.equals(NumLimConstants.REVO))
			return ctrlField + " not in (" + revoRange + ")";
		else
			return null;
	}

}
