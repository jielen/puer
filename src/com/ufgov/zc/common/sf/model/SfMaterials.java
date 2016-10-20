package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfMaterials extends ZcBaseBill {
  public static final String SEQ_SF_MATERIALS_ID = "SEQ_SF_MATERIALS_ID";

  public static final String COL_APPEND_MATERIAL_ID="SF_MATERIALS_APPEND_MATERIAL_ID"; // 追加检材ID
  public static final String COL_ATTACH_FILE="SF_MATERIALS_ATTACH_FILE"; // 文件
  public static final String COL_ATTACH_FILE_BLOBID="SF_MATERIALS_ATTACH_FILE_BLOBID"; // 文件ID
  public static final String COL_BAR_CODE="SF_MATERIALS_BAR_CODE"; // 条码
  public static final String COL_BIANHAO="SF_BIANHAO"; // 编号
  public static final String COL_DESCRIPTION="SF_MATERIALS_DESCRIPTION"; // 描述
  public static final String COL_ENTRUST_ID="SF_MATERIALS_ENTRUST_ID"; // 委托ID
  public static final String COL_JIAN_HOU_CHULI_TYPE="SF_MATERIALS_JIAN_HOU_CHULI_TYPE"; // 鉴后处理
  public static final String COL_JIAN_HOU_STORE_TIME="SF_MATERIALS_JIAN_HOU_STORE_TIME"; // 鉴定后存储时间
  public static final String COL_MATERIAL_ID="SF_MATERIALS_MATERIAL_ID"; // 材料ID
  public static final String COL_MATERIAL_TYPE="SF_MATERIAL_TYPE"; // 类别  1：检材  2：样本"
  public static final String COL_NAME="SF_MATERIALS_NAME"; // 名称
  public static final String COL_QUANTITY="SF_MATERIALS_QUANTITY"; // 数量
  public static final String COL_QUANTITY2="SF_MATERIALS_QUANTITY2"; // 数量2
  public static final String COL_QUANTITY3="SF_MATERIALS_QUANTITY3"; // 数量3,用于直接描述，如几只、几个等
  public static final String COL_REMARK="SF_MATERIALS_REMARK"; // 补充说明
  public static final String COL_SAVE_ADDRESS="SF_MATERIALS_SAVE_ADDRESS"; // 存储位置
  public static final String COL_SAVE_CONDITON="SF_MATERIALS_SAVE_CONDITON"; // 存储条件
  public static final String COL_UNIT="SF_MATERIALS_UNIT"; // 单位

  
//  public static final String COL_ATTACH_FILE="SF_MATERIALS_ATTACH_FILE"; // 文件
//  public static final String COL_ATTACH_FILE_BLOBID="SF_MATERIALS_ATTACH_FILE_BLOBID"; // 文件ID
//  public static final String COL_JIAN_HOU_CHULI_TYPE="SF_MATERIALS_JIAN_HOU_CHULI_TYPE"; // 鉴后处理
//  public static final String COL_SAVE_CONDITON="SF_MATERIALS_SAVE_CONDITON"; // 存储条件
//  public static final String COL_REMARK="SF_MATERIALS_REMARK"; // 补充说明
//  public static final String COL_JIAN_HOU_STORE_TIME="SF_MATERIALS_JIAN_HOU_STORE_TIME"; // 鉴定后存储时间
//  public static final String COL_SAVE_ADDRESS="SF_MATERIALS_SAVE_ADDRESS"; // 存储位置
//  public static final String COL_BAR_CODE="SF_MATERIALS_BAR_CODE"; // 条码

  

  /**
   * 类别
   */
  public static final String SF_VS_MATERIAL_TYPE = "SF_VS_MATERIAL_TYPE";
  
/**
 * 检材
 */
  public static final String SF_VS_MATERIAL_TYPE_jiancai = "1";
  /**
   * 样本
   */
  public static final String SF_VS_MATERIAL_TYPE_yangben = "2";
  

  /**
   * 类别
   */
  public static final String SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE = "SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE";
  public static final String SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE_tuihui = "2";
  public static final String SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE_cundang = "3";
  public static final String SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE_xiaohui = "6"; 
  
  /**
   * 检材默认保存天数
   */
  public static final String OPT_SF_MATERIALS_STORE_DAYS="OPT_SF_MATERIALS_STORE_DAYS";
  
 /* private String  attachFile;
  private String attachFileBlobid;
  private String jianHouChuliType;
  private String saveConditon;
  private String remark;
  private BigDecimal jianHouStoreTime;
  private String saveAddress;
  private String barCode;*/
  

  private String attachFile;
  private String attachFileBlobid;
  private String jianHouChuliType;
  private String saveConditon;
  private String remark;
  private BigDecimal jianHouStoreTime;
  private String saveAddress;
  private String barCode;
  

private BigDecimal appendMaterialId;
  

  private String bianhao;
  
  private String materialType;
  /**
  * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.MATERIAL_ID
  * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
  */
  private BigDecimal materialId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.NAME
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  private String name;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.QUANTITY
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  private BigDecimal quantity;

  private BigDecimal quantity2;

  private String quantity3;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.UNIT
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  private String unit;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.DESCRIPTION
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  private String description;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column SF_MATERIALS.ENTRUST_ID
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  private BigDecimal entrustId;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.MATERIAL_ID
   * @return  the value of SF_MATERIALS.MATERIAL_ID
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public BigDecimal getMaterialId() {
    return materialId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.MATERIAL_ID
   * @param materialId  the value for SF_MATERIALS.MATERIAL_ID
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setMaterialId(BigDecimal materialId) {
    this.materialId = materialId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.NAME
   * @return  the value of SF_MATERIALS.NAME
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public String getName() {
    return name;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.NAME
   * @param name  the value for SF_MATERIALS.NAME
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.QUANTITY
   * @return  the value of SF_MATERIALS.QUANTITY
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public BigDecimal getQuantity() {
    return quantity;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.QUANTITY
   * @param quantity  the value for SF_MATERIALS.QUANTITY
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.UNIT
   * @return  the value of SF_MATERIALS.UNIT
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public String getUnit() {
    return unit;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.UNIT
   * @param unit  the value for SF_MATERIALS.UNIT
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.DESCRIPTION
   * @return  the value of SF_MATERIALS.DESCRIPTION
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public String getDescription() {
    return description;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.DESCRIPTION
   * @param description  the value for SF_MATERIALS.DESCRIPTION
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column SF_MATERIALS.ENTRUST_ID
   * @return  the value of SF_MATERIALS.ENTRUST_ID
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public BigDecimal getEntrustId() {
    return entrustId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column SF_MATERIALS.ENTRUST_ID
   * @param entrustId  the value for SF_MATERIALS.ENTRUST_ID
   * @mbggenerated  Sun Jan 11 06:13:48 CST 2015
   */
  public void setEntrustId(BigDecimal entrustId) {
    this.entrustId = entrustId;
  }

  public String getTempId() {
    if (getMaterialId() != null) {
      return getMaterialId().toString();
    } else {
      return super.getTempId();
    }
  }

  public BigDecimal getQuantity2() {
    return quantity2;
  }

  public void setQuantity2(BigDecimal quantity2) {
    this.quantity2 = quantity2;
  }

  public String getMaterialType() {
	return materialType;
}

public void setMaterialType(String materialType) {
	this.materialType = materialType;
}

public String getQuantity3() {
	return quantity3;
}

public void setQuantity3(String quantity3) {
	this.quantity3 = quantity3;
}

public String getBianhao() {
	return bianhao;
}

public void setBianhao(String bianhao) {
	this.bianhao = bianhao;
}

/**
   * 
   */
  private static final long serialVersionUID = 6916108888317822607L;
  
  public String toString(){
	  StringBuffer sb=new StringBuffer();
	  if("1".equals(materialType)){
		  sb.append("检材");
	  }else if("2".equals(materialType)){
		  sb.append("样本");
	  }
	  if(sb.length()>0){
		  sb.append(": ");
	  }
	  if(getBianhao()!=null){
		  sb.append(getBianhao()).append(" ");
	  }
	  if(getName()!=null){
		  sb.append(getName()).append(" ");
	  }
	  if(getQuantity3()!=null){
		  sb.append(getQuantity3()).append("");
	  }
	  if(getUnit()!=null){
		  sb.append(getUnit()).append(" ");
	  }
	  if(getDescription()!=null){
		  sb.append(getDescription()).append(" ");
	  }
	  return sb.toString();
  }

public BigDecimal getAppendMaterialId() {
	return appendMaterialId;
}

public void setAppendMaterialId(BigDecimal appendMaterialId) {
	this.appendMaterialId = appendMaterialId;
}

public String getAttachFile() {
	return attachFile;
}

public void setAttachFile(String attachFile) {
	this.attachFile = attachFile;
}

public String getAttachFileBlobid() {
	return attachFileBlobid;
}

public void setAttachFileBlobid(String attachFileBlobid) {
	this.attachFileBlobid = attachFileBlobid;
}

public String getJianHouChuliType() {
	return jianHouChuliType;
}

public void setJianHouChuliType(String jianHouChuliType) {
	this.jianHouChuliType = jianHouChuliType;
}

public String getSaveConditon() {
	return saveConditon;
}

public void setSaveConditon(String saveConditon) {
	this.saveConditon = saveConditon;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public BigDecimal getJianHouStoreTime() {
	return jianHouStoreTime;
}

public void setJianHouStoreTime(BigDecimal jianHouStoreTime) {
	this.jianHouStoreTime = jianHouStoreTime;
}

public String getSaveAddress() {
	return saveAddress;
}

public void setSaveAddress(String saveAddress) {
	this.saveAddress = saveAddress;
}

public String getBarCode() {
	return barCode;
}

public void setBarCode(String barCode) {
	this.barCode = barCode;
}
 
}