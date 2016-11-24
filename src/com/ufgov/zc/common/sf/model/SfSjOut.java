package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjOut extends ZcBaseBill{

  /**
   * 
   */
  private static final long serialVersionUID = 1083456307444885833L;

  public static final String SEQ_SF_SF_SJ_OUT_ID="SEQ_SF_SF_SJ_OUT_ID";

  public static final String TAB_ID="SfSjOut_Tab";
  /**
   * 司法鉴定委托搜索条件
   */
  public static final String SEARCH_ID = "SfSjOut_search";
  
  public static final String COL_AMOUNT="SF_SJ_OUT_AMOUNT"; // 数量
  public static final String COL_CO_CODE="SF_SJ_OUT_CO_CODE"; // 鉴定机构
  public static final String COL_INPUTOR="SF_SJ_OUT_INPUTOR"; // 录入人
  public static final String COL_INPUT_DATE="SF_SJ_OUT_INPUT_DATE"; // 录入时间
  public static final String COL_IN_ID="SF_SJ_OUT_IN_ID"; // 入库单ID
  public static final String COL_LOSS_REASON="SF_SJ_OUT_LOSS_REASON"; // 损失原因
  public static final String COL_LOSS_TIME="SF_SJ_OUT_LOSS_TIME"; // 损耗时间
  public static final String COL_ND="SF_SJ_OUT_ND"; // 年度
  public static final String COL_OUT_BILL_CODE="SF_SJ_OUT_OUT_BILL_CODE"; // 出库单号
  public static final String COL_OUT_DATE="SF_SJ_OUT_OUT_DATE"; // 出库时间
  public static final String COL_OUT_ID="SF_SJ_OUT_OUT_ID"; // 出库ID
  public static final String COL_OUT_TYPE="SF_SJ_OUT_OUT_TYPE"; // 类型
  public static final String COL_PROCESS_INST_ID="SF_SJ_OUT_PROCESS_INST_ID"; // 工作流实例号
  public static final String COL_PROPOSER="SF_SJ_OUT_PROPOSER"; // 领用人
  public static final String COL_PROPOSER_DEPT="SF_SJ_OUT_PROPOSER_DEPT"; // 领用部门
  public static final String COL_REMARK="SF_SJ_OUT_REMARK"; // 备注
  public static final String COL_STATUS="SF_SJ_OUT_STATUS"; // 状态
  
  /**
   * 试剂出库类型
   */
  public static final String SF_VS_SJ_OUT_TYPE="SF_VS_SJ_OUT_TYPE";
  /**
   * 消耗
   */
  public static final String SF_VS_SJ_OUT_TYPE_USED="used";
  /**
   * 损耗
   */
  public static final String SF_VS_SJ_OUT_TYPE_LOSSED="lossed";

  
  private SfSjIn sjin=new SfSjIn();
  
    private BigDecimal outId;

    private String outBillCode;
 
    private String proposer;
    private String proposerName;

    private String proposerDept;
 
    private String inputor;

    private Date inputDate;

    private Date outDate;

    private String remark;

    private String status;

    private BigDecimal amount;

    private BigDecimal inId;

    private String outType;

    private String lossReason;

    private Date lossTime;

    public BigDecimal getOutId() {
        return outId;
    }

    public void setOutId(BigDecimal outId) {
        this.outId = outId;
    }

    public String getOutBillCode() {
        return outBillCode;
    }

    public void setOutBillCode(String outBillCode) {
        this.outBillCode = outBillCode;
    }
 
    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getProposerDept() {
        return proposerDept;
    }

    public void setProposerDept(String proposerDept) {
        this.proposerDept = proposerDept;
    }
 

    public String getInputor() {
        return inputor;
    }
    public String getInputorName() {
      if(getInputor()!=null){
        return EmpMeta.getEmpName(getInputor());
      }
      return inputorName;

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

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInId() {
        return inId;
    }

    public void setInId(BigDecimal inId) {
        this.inId = inId;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getLossReason() {
        return lossReason;
    }

    public void setLossReason(String lossReason) {
        this.lossReason = lossReason;
    }

    public Date getLossTime() {
        return lossTime;
    }

    public void setLossTime(Date lossTime) {
        this.lossTime = lossTime;
    }

    public SfSjIn getSjin() {
      return sjin;
    }

    public void setSjin(SfSjIn sjin) {
      this.sjin = sjin;
    }

    public String getProposerName() {
      if(getProposer()!=null){
        return EmpMeta.getEmpName(getProposer());
      }
      return proposerName;
    }

    public void setProposerName(String proposerName) {
      this.proposerName = proposerName;
    }
}