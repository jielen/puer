package com.ufgov.zc.common.console.model;

public class Function {
	private String funcCode = "";

	private String funcName = "";

	private String compoCode = "";

	private boolean isWrLog = false;

	private boolean isUsedForEver = false;

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		if (funcCode != null && funcCode.trim().length() >= 1) {
			this.funcCode = funcCode;
		}
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		if (funcName != null && funcName.trim().length() >= 1) {
			this.funcName = funcName;
		}
	}

	public String getCompoCode() {
		return compoCode;
	}

	public void setCompoCode(String compoCode) {
		if (compoCode != null && compoCode.trim().length() >= 1) {
			this.compoCode = compoCode;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((funcCode == null) ? 0 : funcCode.hashCode());
		result = prime * result
				+ ((funcName == null) ? 0 : funcName.hashCode());
		result = prime * result
				+ ((compoCode == null) ? 0 : compoCode.hashCode());
		return result;
	}

	public String toString() {
		StringBuffer object = new StringBuffer();
		object.append(funcName);
		return object.toString();
	}

	public boolean isWrLog() {
		return isWrLog;
	}

	public void setWrLog(boolean isWrLog) {
		this.isWrLog = isWrLog;
	}

	public boolean isUsedForEver() {
		return isUsedForEver;
	}

	public void setUsedForEver(boolean isUsedForEver) {
		this.isUsedForEver = isUsedForEver;
	}
}
