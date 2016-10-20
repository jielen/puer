package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfCertificate extends ZcBaseBill {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5176990002126689411L;
	public static final String COL_CER_ID="SF_CERTIFICATE_CER_ID"; // 证书ID
	public static final String COL_IS_NOTICE_EXPIRE="SF_CERTIFICATE_IS_NOTICE_EXPIRE"; // 是否提醒到期
	public static final String COL_NAME="SF_CERTIFICATE_NAME"; // 名称
	public static final String COL_REMARK="SF_CERTIFICATE_REMARK"; // 备注
	public static final String COL_ZF_AGENCY="SF_CERTIFICATE_ZF_AGENCY"; // 发证机关
	public static final String COL_ZF_CODE="SF_CERTIFICATE_ZF_CODE"; // 证书编号
	public static final String COL_ZF_DESC="SF_CERTIFICATE_ZF_DESC"; // 证书说明
	public static final String COL_ZF_FILE="SF_CERTIFICATE_ZF_FILE"; // 证书文件
	public static final String COL_ZF_FILE_BLOBID="SF_CERTIFICATE_ZF_FILE_BLOBID"; // 证书文件ID
	public static final String COL_ZF_NOTICE_DAYS="SF_CERTIFICATE_ZF_NOTICE_DAYS"; // 提前提醒时间
	public static final String COL_ZF_OWNER_ID="SF_CERTIFICATE_ZF_OWNER_ID"; // 证书拥有者ID
	public static final String COL_ZF_OWNER_TYPE="SF_CERTIFICATE_ZF_OWNER_TYPE"; // 拥有者类型
	public static final String COL_ZF_END_DATE="SF_CERTIFICATE_ZF_END_DATE"; // 有效期至
	public static final String COL_ZF_BEGIN_DATE="SF_CERTIFICATE_ZF_BEGIN_DATE"; // 发证时间
	
	public static final String SEQ_SF_CERTIFICATE="SEQ_SF_CERTIFICATE";
	

	public static final String VS_SF_CERTIFICATE_TYPE="VS_SF_CERTIFICATE_TYPE";

	public static final String VS_SF_CERTIFICATE_TYPE_zizhi="1";
	public static final String VS_SF_CERTIFICATE_TYPE_xueli="3";
	public static final String VS_SF_CERTIFICATE_TYPE_huojiang="2";
	public static final String VS_SF_CERTIFICATE_TYPE_qita="4";

	
    private BigDecimal cerId;

    private String name;

    private String zfAgency;

    private String zfCode;

    private String zfDesc;

    private String remark;

    private String zfFileBlobid;

    private String zfFile;

    private String isNoticeExpire;

    private BigDecimal zfNoticeDays;

    private String zfOwnerType;

    private BigDecimal zfOwnerId;
    
    private Date zfBeginDate;
    private Date zfEndDate;
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZfAgency() {
        return zfAgency;
    }

    public void setZfAgency(String zfAgency) {
        this.zfAgency = zfAgency;
    }

    public String getZfCode() {
        return zfCode;
    }

    public void setZfCode(String zfCode) {
        this.zfCode = zfCode;
    }

    public String getZfDesc() {
        return zfDesc;
    }

    public void setZfDesc(String zfDesc) {
        this.zfDesc = zfDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZfFileBlobid() {
        return zfFileBlobid;
    }

    public void setZfFileBlobid(String zfFileBlobid) {
        this.zfFileBlobid = zfFileBlobid;
    }

    public String getZfFile() {
        return zfFile;
    }

    public void setZfFile(String zfFile) {
        this.zfFile = zfFile;
    }

    public String getIsNoticeExpire() {
        return isNoticeExpire;
    }

    public void setIsNoticeExpire(String isNoticeExpire) {
        this.isNoticeExpire = isNoticeExpire;
    }

    public BigDecimal getZfNoticeDays() {
        return zfNoticeDays;
    }

    public void setZfNoticeDays(BigDecimal zfNoticeDays) {
        this.zfNoticeDays = zfNoticeDays;
    }

    public String getZfOwnerType() {
        return zfOwnerType;
    }

    public void setZfOwnerType(String zfOwnerType) {
        this.zfOwnerType = zfOwnerType;
    }

    public BigDecimal getZfOwnerId() {
        return zfOwnerId;
    }

    public void setZfOwnerId(BigDecimal zfOwnerId) {
        this.zfOwnerId = zfOwnerId;
    }

	public BigDecimal getCerId() {
		return cerId;
	}

	public void setCerId(BigDecimal cerId) {
		this.cerId = cerId;
	}
 
	public Date getZfBeginDate() {
		return zfBeginDate;
	}

	public void setZfBeginDate(Date zfBeginDate) {
		this.zfBeginDate = zfBeginDate;
	}

	public Date getZfEndDate() {
		return zfEndDate;
	}

	public void setZfEndDate(Date zfEndDate) {
		this.zfEndDate = zfEndDate;
	}
}