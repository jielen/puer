package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfJdReport extends ZcBaseBill {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3224074338937464893L;
	
	public static final String SEQ_SF_JD_REPORT_ID="SEQ_SF_JD_REPORT_ID"; 
	/**
	   * 页签
	   */
	  public static final String TAB_ID = "SfJdReport_Tab";

	  /**
	   * 搜索条件
	   */
	  public static final String SEARCH_ID = "SfJdReport_search";


	  public static final String SF_VS_REPORT_STATUS = "SF_VS_REPORT_STATUS";
	  


	  public static final String OPT_SF_REPORT_FACE_FILE_ID = "OPT_SF_REPORT_FACE_FILE_ID";


	
	public static final String COL_CO_CODE="SF_JD_REPORT_CO_CODE"; // 单位代码
	public static final String COL_ENTRUST_ID="SF_JD_REPORT_ENTRUST_ID"; // 委托ID
	public static final String COL_ENTRUST_CODE="SF_JD_REPORT_ENTRUST_CODE"; // 委托编号
	public static final String COL_INPUTOR="SF_JD_REPORT_INPUTOR"; // 录入人
	public static final String COL_INPUT_DATE="SF_JD_REPORT_INPUT_DATE"; // 录入时间
	public static final String COL_JD_REPORT_FILE_ID="SF_JD_REPORT_JD_REPORT_FILE_ID"; // 鉴定报告文件ID
	public static final String COL_JD_REPOR_ID="SF_JD_REPORT_JD_REPOR_ID"; // 鉴定报告ID
	public static final String COL_JD_RESULT_ID="SF_JD_REPORT_JD_RESULT_ID"; // 鉴定结果ID
	public static final String COL_NAME="SF_JD_REPORT_NAME"; // 名称
	public static final String COL_ND="SF_JD_REPORT_ND"; // 年度
	public static final String COL_PROCESS_INST_ID="SF_JD_REPORT_PROCESS_INST_ID"; // 工作流实例编号
	public static final String COL_PUBLISH_TIME="SF_JD_REPORT_PUBLISH_TIME"; // 报告发布时间
	public static final String COL_REMARK="SF_JD_REPORT_REMARK"; // 备注
	public static final String COL_REPORT_CODE="SF_JD_REPORT_REPORT_CODE"; // 报告编号
	public static final String COL_STATUS="SF_JD_REPORT_STATUS"; // 状态
	public static final String COL_REPORT_TYPE="SF_JD_REPORT_REPORT_TYPE"; // 类别


	
	 /**
	   * 鉴定意见书/鉴定报告类别
	   */
	  public static final String SF_VS_JD_RESULT_TYPE = "SF_VS_JD_RESULT_TYPE";

	  /**
	   * 检验报告
	   */
	  public static final String RESULT_TYPE_JYBG = "JYBG";

	  /**
	   * 检验意见书
	   */
	  public static final String RESULT_TYPE_YJS = "YJS";


	  private SfEntrust entrust = new SfEntrust();
	  
	  private SfJdResult result=new SfJdResult();
	  
	  private SfJdRecordFileModel model=new SfJdRecordFileModel();

	    private String reportType;
	    private String name;
	    private String entrustCode;
	  
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.JD_REPOR_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private BigDecimal jdReporId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.JD_RESULT_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private BigDecimal jdResultId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.ENTRUST_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private BigDecimal entrustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.JD_REPORT_FILE_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private String jdReportFileId;
 
 
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.INPUTOR
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private String inputor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.INPUT_DATE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private Date inputDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.STATUS
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.REMARK
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.PUBLISH_TIME
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private Date publishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SF_JD_REPORT.REPORT_CODE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    private String reportCode;

    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.JD_REPOR_ID
     *
     * @return the value of SF_JD_REPORT.JD_REPOR_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public BigDecimal getJdReporId() {
        return jdReporId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.JD_REPOR_ID
     *
     * @param jdReporId the value for SF_JD_REPORT.JD_REPOR_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setJdReporId(BigDecimal jdReporId) {
        this.jdReporId = jdReporId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.JD_RESULT_ID
     *
     * @return the value of SF_JD_REPORT.JD_RESULT_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public BigDecimal getJdResultId() {
        return jdResultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.JD_RESULT_ID
     *
     * @param jdResultId the value for SF_JD_REPORT.JD_RESULT_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setJdResultId(BigDecimal jdResultId) {
        this.jdResultId = jdResultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.ENTRUST_ID
     *
     * @return the value of SF_JD_REPORT.ENTRUST_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public BigDecimal getEntrustId() {
        return entrust.getEntrustId();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.ENTRUST_ID
     *
     * @param entrustId the value for SF_JD_REPORT.ENTRUST_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setEntrustId(BigDecimal entrustId) {
        entrust.setEntrustId(entrustId);
        
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.JD_REPORT_FILE_ID
     *
     * @return the value of SF_JD_REPORT.JD_REPORT_FILE_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public String getJdReportFileId() {
        return jdReportFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.JD_REPORT_FILE_ID
     *
     * @param jdReportFileId the value for SF_JD_REPORT.JD_REPORT_FILE_ID
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setJdReportFileId(String jdReportFileId) {
        this.jdReportFileId = jdReportFileId;
    }

     

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.INPUTOR
     *
     * @return the value of SF_JD_REPORT.INPUTOR
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public String getInputor() {
        return inputor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.INPUTOR
     *
     * @param inputor the value for SF_JD_REPORT.INPUTOR
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setInputor(String inputor) {
        this.inputor = inputor;
        if(inputor==null){
        	setInputorName("");
        }else{
        	setInputorName(EmpMeta.getEmpName(inputor));
        }
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.INPUT_DATE
     *
     * @return the value of SF_JD_REPORT.INPUT_DATE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public Date getInputDate() {
        return inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.INPUT_DATE
     *
     * @param inputDate the value for SF_JD_REPORT.INPUT_DATE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.STATUS
     *
     * @return the value of SF_JD_REPORT.STATUS
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.STATUS
     *
     * @param status the value for SF_JD_REPORT.STATUS
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.REMARK
     *
     * @return the value of SF_JD_REPORT.REMARK
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.REMARK
     *
     * @param remark the value for SF_JD_REPORT.REMARK
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.PUBLISH_TIME
     *
     * @return the value of SF_JD_REPORT.PUBLISH_TIME
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.PUBLISH_TIME
     *
     * @param publishTime the value for SF_JD_REPORT.PUBLISH_TIME
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SF_JD_REPORT.REPORT_CODE
     *
     * @return the value of SF_JD_REPORT.REPORT_CODE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SF_JD_REPORT.REPORT_CODE
     *
     * @param reportCode the value for SF_JD_REPORT.REPORT_CODE
     *
     * @mbggenerated Sun Jul 17 16:30:58 CST 2016
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

	public SfEntrust getEntrust() {
		return entrust;
	}

	public void setEntrust(SfEntrust entrust) {
		this.entrust = entrust==null?new SfEntrust():entrust;
		setEntrustCode(entrust==null?null:entrust.getCode());
		setEntrustId(entrust==null?null:entrust.getEntrustId());
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public SfJdResult getResult() {
		return result;
	}

	public void setResult(SfJdResult result) {
		this.result = result==null?new SfJdResult():result;
		setJdResultId(this.result.getJdResultId());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntrustCode() {
		return entrust.getCode();
	}

	public void setEntrustCode(String entrustCode) {
		entrust.setCode(entrustCode);
	}

	public SfJdRecordFileModel getModel() {
		return model;
	}

	public void setModel(SfJdRecordFileModel model) {
		this.model = model==null?new SfJdRecordFileModel():model;
	}

   
}