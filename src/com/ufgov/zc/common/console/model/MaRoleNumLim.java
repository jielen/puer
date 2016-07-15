package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class MaRoleNumLim implements Serializable {

	private static final long serialVersionUID = -1068848636317189668L;

	private String roleId;
	private String funcId;
	private String compoId;
	private String ctrlField;
	private String code;

	public String getCompoId() {
		return compoId;
	}

	public void setCompoId(String compoId) {
		this.compoId = compoId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCtrlField() {
		return ctrlField;
	}

	public void setCtrlField(String ctrlField) {
		this.ctrlField = ctrlField;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
