package com.ufgov.zc.common.budget.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VwBudgetGp implements Serializable{

	private static final long serialVersionUID = -5525105841571180308L;

	private int sumId;
	
	/*
	 * 项目结转中，挂接指标时，使用sumId时，出现无法把Integer 类型加入到
	 * zcbiNo 属性中，暂时使用zcBiNo属性。
	 */
	private String zcBiNo; 
	//指标总预算
  private BigDecimal budgetMoney;
  //指标可用金额
	private BigDecimal canuseMoney;
//预算单位
	private String enCode;
	private String enName;
//资金性质
	private String mkCode;
	private String mkName;
//功能分类
	private String bsCode;
	private String bsName;
//项目类别
	private String biCode;
	private String biName;
//付款方式
	private String pkCode;
	private String pkName;
	//核算类型
	private String boCode;
	private String boName;
	 // 业务处室
	private String mbCode;
	private String mbName;
//指标流向
	private String btCode;
	private String btName;
	//指标类型
	private String bpCode;
	private String bpName;
//指标来源
	private String blCode;
	private String blName;
//预算项目
	private String bisCode;
	private String bisName;
	//指标摘要
	private String smCode;
	private String smName;
	//政府采购标识
	private String gbCode;
	private String gbName;
	//工资标识
	private String saltagCode;
	private String saltagName;
//文号
	private String fileName;
	private String fileCode;
	//
	private String budgetVouCode;
	private String budgetVouName;
	//
	private String gpplanCode;
	private String gpplanName;
	//收支管理
	private String inpmCode;
	private String inpmName;
//年度
	private int setYear;
	//区划
	private String rgCode;
	
	//经济分类
	private String bsiId;
	private String bsiCode;
	private String bsiName;


	private String enId;
	private String mkId;
	private String bsId;
	private String biId;
	private String pkId;
	private String boId;
	private String mbId;
	private String btId;
	private String bpId;
	private String blId;
	private String bisId;
	private String smId;
	private String gbId;
	private String saltagId;
	private String fileId;
	private String budgetVouId;
	private String gpplanId;
	private String inpmId;




	public String getEnId() {
		return enId;
	}
	public void setEnId(String enId) {
		this.enId = enId;
	}
	public String getMkId() {
		return mkId;
	}
	public void setMkId(String mkId) {
		this.mkId = mkId;
	}
	public String getBsId() {
		return bsId;
	}
	public void setBsId(String bsId) {
		this.bsId = bsId;
	}
	public String getBiId() {
		return biId;
	}
	public void setBiId(String biId) {
		this.biId = biId;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public String getBoId() {
		return boId;
	}
	public void setBoId(String boId) {
		this.boId = boId;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getBtId() {
		return btId;
	}
	public void setBtId(String btId) {
		this.btId = btId;
	}
	public String getBpId() {
		return bpId;
	}
	public void setBpId(String bpId) {
		this.bpId = bpId;
	}
	public String getBlId() {
		return blId;
	}
	public void setBlId(String blId) {
		this.blId = blId;
	}
	public String getBisId() {
		return bisId;
	}
	public void setBisId(String bisId) {
		this.bisId = bisId;
	}
	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
	public String getGbId() {
		return gbId;
	}
	public void setGbId(String gbId) {
		this.gbId = gbId;
	}
	public String getSaltagId() {
		return saltagId;
	}
	public void setSaltagId(String saltagId) {
		this.saltagId = saltagId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getBudgetVouId() {
		return budgetVouId;
	}
	public void setBudgetVouId(String budgetVouId) {
		this.budgetVouId = budgetVouId;
	}
	public String getGpplanId() {
		return gpplanId;
	}
	public void setGpplanId(String gpplanId) {
		this.gpplanId = gpplanId;
	}
	public String getInpmId() {
		return inpmId;
	}
	public void setInpmId(String inpmId) {
		this.inpmId = inpmId;
	}
	public int getSumId() {
		return sumId;
	}
	public void setSumId(int sumId) {
		this.sumId = sumId;
	}
	public BigDecimal getBudgetMoney() {
		return budgetMoney;
	}
	public void setBudgetMoney(BigDecimal budgetMoney) {
		this.budgetMoney = budgetMoney;
	}
	public BigDecimal getCanuseMoney() {
		return canuseMoney;
	}
	public void setCanuseMoney(BigDecimal canuseMoney) {
		this.canuseMoney = canuseMoney;
	}
	public String getEnCode() {
		return enCode;
	}
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getMkCode() {
		return mkCode;
	}
	public void setMkCode(String mkCode) {
		this.mkCode = mkCode;
	}
	public String getMkName() {
		return mkName;
	}
	public void setMkName(String mkName) {
		this.mkName = mkName;
	}
	public String getBsCode() {
		return bsCode;
	}
	public void setBsCode(String bsCode) {
		this.bsCode = bsCode;
	}
	public String getBsName() {
		return bsName;
	}
	public void setBsName(String bsName) {
		this.bsName = bsName;
	}
	public String getBiCode() {
		return biCode;
	}
	public void setBiCode(String biCode) {
		this.biCode = biCode;
	}
	public String getBiName() {
		return biName;
	}
	public void setBiName(String biName) {
		this.biName = biName;
	}
	public String getPkCode() {
		return pkCode;
	}
	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public String getBoCode() {
		return boCode;
	}
	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}
	public String getBoName() {
		return boName;
	}
	public void setBoName(String boName) {
		this.boName = boName;
	}
	public String getMbCode() {
		return mbCode;
	}
	public void setMbCode(String mbCode) {
		this.mbCode = mbCode;
	}
	public String getMbName() {
		return mbName;
	}
	public void setMbName(String mbName) {
		this.mbName = mbName;
	}
	public String getBtCode() {
		return btCode;
	}
	public void setBtCode(String btCode) {
		this.btCode = btCode;
	}
	public String getBtName() {
		return btName;
	}
	public void setBtName(String btName) {
		this.btName = btName;
	}
	public String getBpCode() {
		return bpCode;
	}
	public void setBpCode(String bpCode) {
		this.bpCode = bpCode;
	}
	public String getBpName() {
		return bpName;
	}
	public void setBpName(String bpName) {
		this.bpName = bpName;
	}
	public String getBlCode() {
		return blCode;
	}
	public void setBlCode(String blCode) {
		this.blCode = blCode;
	}
	public String getBlName() {
		return blName;
	}
	public void setBlName(String blName) {
		this.blName = blName;
	}
	public String getBisCode() {
		return bisCode;
	}
	public void setBisCode(String bisCode) {
		this.bisCode = bisCode;
	}
	public String getBisName() {
		return bisName;
	}
	public void setBisName(String bisName) {
		this.bisName = bisName;
	}
	public String getSmCode() {
		return smCode;
	}
	public void setSmCode(String smCode) {
		this.smCode = smCode;
	}
	public String getSmName() {
		return smName;
	}
	public void setSmName(String smName) {
		this.smName = smName;
	}
	public String getGbCode() {
		return gbCode;
	}
	public void setGbCode(String gbCode) {
		this.gbCode = gbCode;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getSaltagCode() {
		return saltagCode;
	}
	public void setSaltagCode(String saltagCode) {
		this.saltagCode = saltagCode;
	}
	public String getSaltagName() {
		return saltagName;
	}
	public void setSaltagName(String saltagName) {
		this.saltagName = saltagName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getBudgetVouCode() {
		return budgetVouCode;
	}
	public void setBudgetVouCode(String budgetVouCode) {
		this.budgetVouCode = budgetVouCode;
	}
	public String getBudgetVouName() {
		return budgetVouName;
	}
	public void setBudgetVouName(String budgetVouName) {
		this.budgetVouName = budgetVouName;
	}
	public String getGpplanCode() {
		return gpplanCode;
	}
	public void setGpplanCode(String gpplanCode) {
		this.gpplanCode = gpplanCode;
	}
	public String getGpplanName() {
		return gpplanName;
	}
	public void setGpplanName(String gpplanName) {
		this.gpplanName = gpplanName;
	}
	public String getInpmCode() {
		return inpmCode;
	}
	public void setInpmCode(String inpmCode) {
		this.inpmCode = inpmCode;
	}
	public String getInpmName() {
		return inpmName;
	}
	public void setInpmName(String inpmName) {
		this.inpmName = inpmName;
	}
	public int getSetYear() {
		return setYear;
	}
	public void setSetYear(int setYear) {
		this.setYear = setYear;
	}
	public String getRgCode() {
		return rgCode;
	}
	public void setRgCode(String rgCode) {
		this.rgCode = rgCode;
	}
	public String getZcBiNo() {
    return sumId + "";
  }

  public void setZcBiNo(String zcBiNo) {
    this.zcBiNo = zcBiNo;
  }
  public String getBsiId() {
    return bsiId;
  }
  public void setBsiId(String bsiId) {
    this.bsiId = bsiId;
  }
  public String getBsiCode() {
    return bsiCode;
  }
  public void setBsiCode(String bsiCode) {
    this.bsiCode = bsiCode;
  }
  public String getBsiName() {
    return bsiName;
  }
  public void setBsiName(String bsiName) {
    this.bsiName = bsiName;
  }
}
