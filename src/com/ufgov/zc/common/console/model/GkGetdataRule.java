package com.ufgov.zc.common.console.model;

import java.io.Serializable;

public class GkGetdataRule implements Serializable {
	private String ruleId;

	private String ruleName;

	private String ruleResc;

	private String ruleSqlOracle;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleResc() {
		return ruleResc;
	}

	public void setRuleResc(String ruleResc) {
		this.ruleResc = ruleResc;
	}

	public String getRuleSqlOracle() {
		return ruleSqlOracle;
	}

	public void setRuleSqlOracle(String ruleSqlOracle) {
		this.ruleSqlOracle = ruleSqlOracle;
	}
}
