package com.ufgov.zc.common.console.model;

import java.io.Serializable;

import com.ufgov.zc.common.system.constants.NumLimConstants;

public class AsUserNumLim implements Serializable {

	private static final long serialVersionUID = 7605695780862151513L;

	private String userId;
	private String funcId;
	private String compoId;
	private String ctrlField;
	private String granRange;
	private String revoRange;
	private String isGran;
	private String isRelation;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toSql() {
		if (ctrlField.equals(NumLimConstants.SQL_CONDITION))
			return granRange;
		else if (isGran.equals(NumLimConstants.GRAN))
			return ctrlField + " in (" + granRange + ")";
		else if (isGran.equals(NumLimConstants.REVO))
			return ctrlField + " not in (" + revoRange + ")";
		else
			return null;
	}

}
