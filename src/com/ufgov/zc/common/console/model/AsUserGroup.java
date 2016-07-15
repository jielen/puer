package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class AsUserGroup implements Serializable {
	/**
   * 
   */
	private static final long serialVersionUID = -9121550675920533265L;

	private String groupId;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}