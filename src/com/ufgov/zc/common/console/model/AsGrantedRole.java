package com.ufgov.zc.common.console.model;

import java.io.Serializable;
import java.util.Date;

public class AsGrantedRole implements Serializable {
	/**
   * 
   */
	private static final long serialVersionUID = 8805539981160021803L;

	private String id;

	private String grantUserId;

	private String grantedUserId;

	private Date beginDate;

	private Date endDate;

	private String grantUserCoCode;

	private String grantUserOrgCode;

	private String grantPosiCode;

	private String grantPosiId;

	private String roleId;

	private String roleName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrantUserId() {
		return grantUserId;
	}

	public void setGrantUserId(String grantUserId) {
		this.grantUserId = grantUserId;
	}

	public String getGrantedUserId() {
		return grantedUserId;
	}

	public void setGrantedUserId(String grantedUserId) {
		this.grantedUserId = grantedUserId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getGrantPosiId() {
		return grantPosiId;
	}

	public void setGrantPosiId(String grantPosiId) {
		this.grantPosiId = grantPosiId;
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

	public String getGrantPosiCode() {
		return grantPosiCode;
	}

	public void setGrantPosiCode(String grantPosiCode) {
		this.grantPosiCode = grantPosiCode;
	}

	public AsGrantedRole() {
		super();
	}

	public boolean compareAsRole(AsRole role) {
		return this.getRoleId().equals(role.getRoleId());
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			if (!(obj instanceof AsGrantedRole)) {
				return false;
			} else {
				AsGrantedRole role = (AsGrantedRole) obj;
				return this.getRoleId().equals(role.getRoleId());
			}
		}
	}
}
