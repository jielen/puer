package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfSjIn extends ZcBaseBill{
  
    /**
   * 
   */
  private static final long serialVersionUID = 4712197638781101506L;
  
  public static final String COL_AMOUNT="SF_SJ_IN_AMOUNT"; // 数量
  public static final String COL_BUY_CODE="SF_SJ_IN_BUY_CODE"; // 采购单号
  public static final String COL_CO_CODE="SF_SJ_IN_CO_CODE"; // 鉴定机构
  public static final String COL_EXPIRY_DATE="SF_SJ_IN_EXPIRY_DATE"; // 失效日期
  public static final String COL_INPUTOR="SF_SJ_IN_INPUTOR"; // 入库人
  public static final String COL_INVOICE_CODE="SF_SJ_IN_INVOICE_CODE"; // 发票号
  public static final String COL_IN_BILL_CODE="SF_SJ_IN_IN_BILL_CODE"; // 入库单号
  public static final String COL_IN_DATE="SF_SJ_IN_IN_DATE"; // 入库时间
  public static final String COL_IN_ID="SF_SJ_IN_IN_ID"; // 入库单ID
  public static final String COL_ND="SF_SJ_IN_ND"; // 年度
  public static final String COL_PRICE="SF_SJ_IN_PRICE"; // 单价
  public static final String COL_SHIJI_PIHAO="SF_SJ_IN_SHIJI_PIHAO"; // 试剂批号
  public static final String COL_SJ_ID="SF_SJ_IN_SJ_ID"; // 试剂ID
  public static final String COL_STORE_CODE="SF_SJ_IN_STORE_CODE"; // 存储位置ID
  public static final String COL_SUPPLIER_ID="SF_SJ_IN_SUPPLIER_ID"; // 经销商ID
  public static final String COL_TOTAL_NUM="SF_SJ_IN_TOTAL_NUM"; // 总价
  public static final String COL_UNIT_CODE="SF_SJ_IN_UNIT_CODE"; // 单位
  public static final String COL_REMARK="SF_SJ_IN_REMARK"; // 备注
  
  public static final String SEQ_SF_SF_SJ_IN_ID="SEQ_SF_SF_SJ_IN_ID";

  public static final String TAB_ID="SfSjIn_Tab";
  /**
   * 司法鉴定委托搜索条件
   */
  public static final String SEARCH_ID = "SfSjIn_search";
  
  private SfSj sj=new SfSj();
  
  private SfSjSupplier supplier=new SfSjSupplier();

  private String remark;
  
    private BigDecimal inId;

    private BigDecimal sjId;

    private String shijiPihao;

    private BigDecimal amount;

    private BigDecimal price;

    private Date expiryDate;

    private String storeCode;

    private String inBillCode;

    private Date inDate;

    private String inputor; 

    private String invoiceCode;

    private String buyCode; 

    private BigDecimal supplierId;

    private String unitCode;

    private BigDecimal totalNum;

    public BigDecimal getInId() {
        return inId;
    }

    public void setInId(BigDecimal inId) {
        this.inId = inId;
    }

    public BigDecimal getSjId() {
        return getSj().getSjId();
    }

    public void setSjId(BigDecimal sjId) {
        this.sjId = sjId;
    }

    public String getShijiPihao() {
        return shijiPihao;
    }

    public void setShijiPihao(String shijiPihao) {
        this.shijiPihao = shijiPihao;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getInBillCode() {
        return inBillCode;
    }

    public void setInBillCode(String inBillCode) {
        this.inBillCode = inBillCode;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInputor() {
        return inputor;
    }

    public void setInputor(String inputor) {
        this.inputor = inputor;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getBuyCode() {
        return buyCode;
    }

    public void setBuyCode(String buyCode) {
        this.buyCode = buyCode;
    }
 

    public BigDecimal getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(BigDecimal supplierId) {
        this.supplierId = supplierId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public SfSj getSj() {
      return sj;
    }

    public void setSj(SfSj sj) {
      this.sj = sj;
    }

    public SfSjSupplier getSupplier() {
      return supplier;
    }

    public void setSupplier(SfSjSupplier supplier) {
      this.supplier = supplier;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }
    public String getInputorName() {
      if(getInputor()!=null){
        return EmpMeta.getEmpName(getInputor());
      }
      return inputorName;

    }
}