package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfAppendMaterial extends ZcBaseBill{
    /**
   * 
   */
  private static final long serialVersionUID = -7546688197708024721L;
  
  public static final String SEQ_SF_APPEND_MATERIAL_ID = "SEQ_SF_APPEND_MATERIAL_ID";

  public static final String TAB_ID = "SfAppendMaterial_Tab";

  /**
   * 搜索条件
   */
  public static final String SEARCH_ID = "SfAppendMaterial_search";

  public static final String SF_VS_APPEND_MATERIAL_STATUS = "SF_VS_APPEND_MATERIAL_STATUS";
  
  public static final String COL_ACCEPTOR="SF_APPEND_MATERIAL_ACCEPTOR"; // 受理人
  public static final String COL_ACCEPT_DATE="SF_APPEND_MATERIAL_ACCEPT_DATE"; // 受理时间
  public static final String COL_APPEND_MATERIAL_ID="SF_APPEND_MATERIAL_APPEND_MATERIAL_ID"; // 追加检材ID
  public static final String COL_CO_CODE="SF_APPEND_MATERIAL_CO_CODE"; // 鉴定机构
  public static final String COL_ENTRUST_CODE="SF_APPEND_MATERIAL_ENTRUST_CODE"; // 委托编号
  public static final String COL_ENTRUST_ID="SF_APPEND_MATERIAL_ENTRUST_ID"; // 委托ID
  public static final String COL_INPUTOR="SF_APPEND_MATERIAL_INPUTOR"; // 录入人
  public static final String COL_INPUT_DATE="SF_APPEND_MATERIAL_INPUT_DATE"; // 录入时间
  public static final String COL_NAME="SF_APPEND_MATERIAL_NAME"; // 名称
  public static final String COL_ND="SF_APPEND_MATERIAL_ND"; // 年度
  public static final String COL_PROCESS_INST_ID="SF_APPEND_MATERIAL_PROCESS_INST_ID"; // 工作流实例号
  public static final String COL_REMARK="SF_APPEND_MATERIAL_REMARK"; // 补充说明
  public static final String COL_SJR="SF_APPEND_MATERIAL_SJR"; // 送检人
  public static final String COL_SJR_TEL="SF_APPEND_MATERIAL_SJR_TEL"; // 送检人电话
  public static final String COL_SJR_ZJ_CODE="SF_APPEND_MATERIAL_SJR_ZJ_CODE"; // 送检人证件号码
  public static final String COL_SJR_ZJ_TYPE="SF_APPEND_MATERIAL_SJR_ZJ_TYPE"; // 送检人证件类别
  public static final String COL_STATUS="SF_APPEND_MATERIAL_STATUS"; // 状态
  public static final String COL_VALIDATOR="SF_APPEND_MATERIAL_VALIDATOR"; // 验证人
  public static final String COL_VALIDAT_DATE="SF_APPEND_MATERIAL_VALIDAT_DATE"; // 验证时间
  public static final String COL_VALIDAT_IS_PASS="SF_APPEND_MATERIAL_VALIDAT_IS_PASS"; // 是否受理
  public static final String COL_VALIDAT_OPINION="SF_APPEND_MATERIAL_VALIDAT_OPINION"; // 验证意见

//检材明细类型是SfMaterials
    private List detailLst=new ArrayList();
    
    private BigDecimal appendMaterialId;
    
    private String appendMaterialIdStr;

    private String entrustCode;

    private String sjrTel;

    private String name;

    private String inputor;

    private Date inputDate;

    private String acceptor;

    private Date acceptDate;

    private String status;  

    private String validator;

    private Date validatDate;

    private String validatOpinion;

    private String validatIsPass;

    private String remark; 

    private BigDecimal entrustId;

    private String sjr;

    private String sjrZjType;

    private String sjrZjCode;

    public BigDecimal getAppendMaterialId() {
        return appendMaterialId;
    }

    public void setAppendMaterialId(BigDecimal appendMaterialId) {
        this.appendMaterialId = appendMaterialId;
    }

    public String getEntrustCode() {
        return entrustCode;
    }

    public void setEntrustCode(String entrustCode) {
        this.entrustCode = entrustCode;
    }

    public String getSjrTel() {
        return sjrTel;
    }

    public void setSjrTel(String sjrTel) {
        this.sjrTel = sjrTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInputor() {
        return inputor;
    }

    public void setInputor(String inputor) {
        this.inputor = inputor;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public Date getValidatDate() {
        return validatDate;
    }

    public void setValidatDate(Date validatDate) {
        this.validatDate = validatDate;
    }

    public String getValidatOpinion() {
        return validatOpinion;
    }

    public void setValidatOpinion(String validatOpinion) {
        this.validatOpinion = validatOpinion;
    }

    public String getValidatIsPass() {
        return validatIsPass;
    }

    public void setValidatIsPass(String validatIsPass) {
        this.validatIsPass = validatIsPass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
 

    public BigDecimal getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(BigDecimal entrustId) {
        this.entrustId = entrustId;
    }

    public String getSjr() {
        return sjr;
    }

    public void setSjr(String sjr) {
        this.sjr = sjr;
    }

    public String getSjrZjType() {
        return sjrZjType;
    }

    public void setSjrZjType(String sjrZjType) {
        this.sjrZjType = sjrZjType;
    }

    public String getSjrZjCode() {
        return sjrZjCode;
    }

    public void setSjrZjCode(String sjrZjCode) {
        this.sjrZjCode = sjrZjCode;
    }

    public List getDetailLst() {
      return detailLst;
    }

    public void setDetailLst(List detailLst) {
      this.detailLst = detailLst;
    }
    public String getAcceptorName() {
      return EmpMeta.getEmpName(acceptor);
    }

    public void setAcceptorName(String acceptorName) {

    }
    public String getInputorName() {
      return EmpMeta.getEmpName(inputor);
    }

    public void setInputorName(String inputorName) {

    }

    public String getAppendMaterialIdStr() {
      if(getAppendMaterialId()!=null){
        String str=""+getAppendMaterialId().intValue();
        StringBuffer sb=new StringBuffer();
        if(str.length()<8){
          for(int i=0;i<8-str.length();i++){
            sb.append("0");
          }
          sb.append(str);
          return sb.toString();
        }
        return str;
      }
      return appendMaterialIdStr;
    }

    public void setAppendMaterialIdStr(String appendMaterialIdStr) {
      this.appendMaterialIdStr = appendMaterialIdStr;
    }
}