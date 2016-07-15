package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class GkCompoNewToOld implements Serializable {
	private String newCompoId;

	private String newCompoName;

	private String oldCompoId;

	private String oldCompoName;

	private String toType;

	public String getNewCompoId() {
		return newCompoId;
	}

	public void setNewCompoId(String newCompoId) {
		this.newCompoId = newCompoId;
	}

	public String getNewCompoName() {
		return newCompoName;
	}

	public void setNewCompoName(String newCompoName) {
		this.newCompoName = newCompoName;
	}

	public String getOldCompoId() {
		return oldCompoId;
	}

	public void setOldCompoId(String oldCompoId) {
		this.oldCompoId = oldCompoId;
	}

	public String getOldCompoName() {
		return oldCompoName;
	}

	public void setOldCompoName(String oldCompoName) {
		this.oldCompoName = oldCompoName;
	}

	public String getToType() {
		return toType;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}
}
