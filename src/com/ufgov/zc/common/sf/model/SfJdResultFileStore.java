package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfJdResultFileStore extends ZcBaseBill {
    /**
	 * 
	 */
	private static final long serialVersionUID = -323000975635104387L;
	
	public static final String COL_JD_RESULT_ID="SF_JD_RESULT_FILE_STORE_JD_RESULT_ID"; // 鉴定结果ID
	public static final String COL_PATH="SF_JD_RESULT_FILE_STORE_PATH"; // 存储目录
	public static final String COL_SF_JD_RESULT_FILE_STORE_ID="SF_JD_RESULT_FILE_STORE_SF_JD_RESULT_FILE_STORE_ID"; // 鉴定过程文件存储ID
	public static final String COL_STORE_TYPE="SF_JD_RESULT_FILE_STORE_STORE_TYPE"; // 存储类型
	
	public static final String SEQ_SF_JD_RESULT_FILE_STORE_ID = "SEQ_SF_JD_RESULT_FILE_STORE_ID";

	private BigDecimal sfJdResultFileStoreId;

    private BigDecimal jdResultId;
    
    private BigDecimal entrustId;

    private String storeType;

    private String path;

    public BigDecimal getSfJdResultFileStoreId() {
        return sfJdResultFileStoreId;
    }

    public void setSfJdResultFileStoreId(BigDecimal sfJdResultFileStoreId) {
        this.sfJdResultFileStoreId = sfJdResultFileStoreId;
    }

    public BigDecimal getJdResultId() {
        return jdResultId;
    }

    public void setJdResultId(BigDecimal jdResultId) {
        this.jdResultId = jdResultId;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	public BigDecimal getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(BigDecimal entrustId) {
		this.entrustId = entrustId;
	}
}