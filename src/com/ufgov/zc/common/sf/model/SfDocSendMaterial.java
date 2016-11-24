package com.ufgov.zc.common.sf.model;

import java.math.BigDecimal;

import com.ufgov.zc.common.zc.model.ZcBaseBill;

public class SfDocSendMaterial extends ZcBaseBill{

  /**
   * 
   */
  private static final long serialVersionUID = -6268201134397351960L;
  
  private SfMaterialsTransferDetail materialTransfer=new SfMaterialsTransferDetail();
	private BigDecimal sendId;
	private BigDecimal materialId;
	private String processing;
	private String remark;
	private BigDecimal transferId;

	public BigDecimal getSendId() {
		return sendId;
	}

	public void setSendId(BigDecimal sendId) {
		this.sendId = sendId;
	}

	public BigDecimal getMaterialId() {
		return getMaterialTransfer().getMaterialId();
	}

	public void setMaterialId(BigDecimal materialId) {
		this.materialId = materialId;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getTransferId() {
		return getMaterialTransfer().getTransferId();
	}

	public void setTransferId(BigDecimal transferId) {
		this.transferId = transferId;
	}

  public SfMaterialsTransferDetail getMaterialTransfer() {
    return materialTransfer;
  }

  public void setMaterialTransfer(SfMaterialsTransferDetail materialTransfer) {
    this.materialTransfer = materialTransfer;
  }

}
