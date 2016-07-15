package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class Menu implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = -2266009177018010084L;

	private String menuCode = "";

	private String parentCode = "";

	private String menuName = "";

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		if (menuCode != null && menuCode.trim().length() >= 1) {
			this.menuCode = menuCode;
		}
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		if (parentCode != null && parentCode.trim().length() >= 1) {
			this.parentCode = parentCode.trim();
		}
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		if (menuName != null && menuName.trim().length() >= 1) {
			this.menuName = menuName.trim();
		}
	}

	public String toString() {
		StringBuffer object = new StringBuffer();
		/*
		 * if (menuCode != "") { object.append("["); object.append(menuCode);
		 * object.append("]"); }
		 */object.append(menuName);
		return object.toString();
	}

}
