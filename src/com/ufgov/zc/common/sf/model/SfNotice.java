package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfNotice  extends ZcBaseBill{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4255007044228817766L;

	  public static final String SEQ_SF_NOTICE_ID = "SEQ_SF_NOTICE_ID";
		/**
		 * 页签
		 */
		public static final String TAB_ID = "SfNotice_Tab";
		
	public static final String VS_SF_NOTICE_STATUS="VS_SF_NOTICE_STATUS";
	public static final String VS_SF_NOTICE_STATUS_enable="enable";
	public static final String VS_SF_NOTICE_STATUS_disable="disable";

	public static final String VS_SF_NOTICE_RATE_TYPE="VS_SF_NOTICE_RATE_TYPE";
	public static final String VS_SF_NOTICE_RATE_TYPE_day="day";
	public static final String VS_SF_NOTICE_RATE_TYPE_hour="hour"; 
	public static final String VS_SF_NOTICE_RATE_TYPE_month="month"; 
	
	public static final String COL_BEFORE_TIMES1="SF_NOTICE_BEFORE_TIMES1"; // 预警提前数1
	public static final String COL_BEFORE_TIMES2="SF_NOTICE_BEFORE_TIMES2"; // 预警提前数2
	public static final String COL_BUSINESS_HANDLER="SF_NOTICE_BUSINESS_HANDLER"; // 业务执行类
	public static final String COL_NAME="SF_NOTICE_NAME"; // 名称
	public static final String COL_NOTICE_ID="SF_NOTICE_NOTICE_ID"; // 提醒ID
	public static final String COL_RATE1="SF_NOTICE_RATE1"; // 提醒频率1
	public static final String COL_RATE1_TYPE="SF_NOTICE_RATE1_TYPE"; // 提醒频率单位1，如月/天/小时
	public static final String COL_RATE2="SF_NOTICE_RATE2"; // 提醒频率2，如月/天/小时
	public static final String COL_RATE2_TYPE="SF_NOTICE_RATE2_TYPE"; // 提醒频率单位2，如月/天/小时
	public static final String COL_REMARK="SF_NOTICE_REMARK"; // 备注
	public static final String COL_STATUS="SF_NOTICE_STATUS"; // 状态：启用/停用
	public static final String COL_TIME_UNIT1="SF_NOTICE_TIME_UNIT1"; // 时间单位1，如月/天/小时
	public static final String COL_TIME_UNIT2="SF_NOTICE_TIME_UNIT2"; // 时间单位2，如月/天/小时

	

	private BigDecimal noticeId;

    private String name;

    private String businessHandler;

    private String remark;

    private String status;

    private BigDecimal beforeTimes1;

    private String timeUnit1;

    private BigDecimal rate1;

    private String rate1Type;

    private BigDecimal beforeTimes2;

    private String timeUnit2;

    private BigDecimal rate2;

    private String rate2Type;

    public BigDecimal getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(BigDecimal noticeId) {
        this.noticeId = noticeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessHandler() {
        return businessHandler;
    }

    public void setBusinessHandler(String businessHandler) {
        this.businessHandler = businessHandler;
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

    public BigDecimal getBeforeTimes1() {
        return beforeTimes1;
    }

    public void setBeforeTimes1(BigDecimal beforeTimes1) {
        this.beforeTimes1 = beforeTimes1;
    }

    public String getTimeUnit1() {
        return timeUnit1;
    }

    public void setTimeUnit1(String timeUnit1) {
        this.timeUnit1 = timeUnit1;
    }

    public BigDecimal getRate1() {
        return rate1;
    }

    public void setRate1(BigDecimal rate1) {
        this.rate1 = rate1;
    }

    public String getRate1Type() {
        return rate1Type;
    }

    public void setRate1Type(String rate1Type) {
        this.rate1Type = rate1Type;
    }

    public BigDecimal getBeforeTimes2() {
        return beforeTimes2;
    }

    public void setBeforeTimes2(BigDecimal beforeTimes2) {
        this.beforeTimes2 = beforeTimes2;
    }

    public String getTimeUnit2() {
        return timeUnit2;
    }

    public void setTimeUnit2(String timeUnit2) {
        this.timeUnit2 = timeUnit2;
    }

    public BigDecimal getRate2() {
        return rate2;
    }

    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }

    public String getRate2Type() {
        return rate2Type;
    }

    public void setRate2Type(String rate2Type) {
        this.rate2Type = rate2Type;
    }
}