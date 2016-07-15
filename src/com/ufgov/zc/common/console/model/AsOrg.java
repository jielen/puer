package com.ufgov.zc.common.console.model;

import java.io.Serializable;

import com.ufgov.zc.common.commonbiz.model.BaseElement;

public class AsOrg extends BaseElement implements Serializable {

	private static final long serialVersionUID = 5156950107442824147L;

	// ��֯�������
	public final static String COMPANY = "company";
	public final static String ORG = "org";
	public final static String POSI = "position";
	public final static String EMP = "emp";
	public final static String UNRECOGNIZED = "unrecognized"; // �޷�ʶ��

	private String coCode;
	private String orgCode;
	private String posiCode;
	private String empCode;
	private String userId;
	private String normImg;

	public String getCoCode() {
		return coCode;
	}

	public void setCoCode(String coCode) {
		this.coCode = coCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNd() {
		return nd;
	}

	public void setNd(int nd) {
		this.nd = nd;
	}

	public String getNormImg() {
		return normImg;
	}

	public void setNormImg(String normImg) {
		this.normImg = normImg;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getPosiCode() {
		return posiCode;
	}

	public void setPosiCode(String posiCode) {
		this.posiCode = posiCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ��ȡ����֯�������
	 * 
	 * @return
	 */
	public String getType() {
		if (coCode != null && orgCode == null)
			return COMPANY;
		else if (coCode != null && orgCode != null && posiCode == null)
			return ORG;
		else if (coCode != null && orgCode != null && posiCode != null
				&& empCode == null)
			return POSI;
		else if (coCode != null && orgCode != null && posiCode != null
				&& empCode != null)
			return EMP;
		else
			return UNRECOGNIZED;
	}

	/**
	 * ������������ʾ
	 */
	public String toString() {
		return this.name;
	}

}
