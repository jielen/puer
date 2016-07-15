package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class AsRole implements Serializable {

	private static final long serialVersionUID = -8414012323850666269L;

	private String roleId;

	private String roleName;

	private String roleDesc;

	private String coCode;

	public String getCoCode() {
		return coCode;
	}

	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String toString() {
		return "[" + roleId + "]" + roleName;
	}

}
