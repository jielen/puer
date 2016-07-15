package com.ufgov.zc.common.console.model;

public class Component {
	private String compoCode = "";

	private String compoName = "";

	private String menuCode = "";

	public String getCompoCode() {
		return compoCode;
	}

	public void setCompoCode(String compoCode) {
		if (compoCode != null && compoCode.trim().length() >= 1) {
			this.compoCode = compoCode;
		}
	}

	public String getCompoName() {
		return compoName;
	}

	public void setCompoName(String compoName) {
		if (compoName != null && compoName.trim().length() >= 1) {
			this.compoName = compoName;
		}
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		if (menuCode != null && menuCode.trim().length() >= 1) {
			this.menuCode = menuCode;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((compoCode == null) ? 0 : compoCode.hashCode());
		result = prime * result
				+ ((compoName == null) ? 0 : compoName.hashCode());
		result = prime * result
				+ ((menuCode == null) ? 0 : menuCode.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Component other = (Component) obj;
		if (compoCode == null) {
			if (other.compoCode != null)
				return false;
		} else if (!compoCode.equals(other.compoCode))
			return false;
		if (compoName == null) {
			if (other.compoName != null)
				return false;
		} else if (!compoName.equals(other.compoName))
			return false;
		if (menuCode == null) {
			if (other.menuCode != null)
				return false;
		} else if (!menuCode.equals(other.menuCode))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer object = new StringBuffer();
		object.append(compoName);
		return object.toString();
	}
}
