package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBill;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.publish.IZcHtPrePayBillServiceDelegate;
import com.ufgov.zc.server.zc.service.IZcHtPrePayBillService;

public class ZcHtPrePayBillServiceDelegate implements IZcHtPrePayBillServiceDelegate {

  private IZcHtPrePayBillService zcHtPrePayBillService;

  /**
   * @return the zcHtPrePayBillService
   */
  public IZcHtPrePayBillService getZcHtPrePayBillService() {
    return zcHtPrePayBillService;
  }

  /**
   * @param zcHtPrePayBillService the zcHtPrePayBillService to set
   */
  public void setZcHtPrePayBillService(IZcHtPrePayBillService zcHtPrePayBillService) {
    this.zcHtPrePayBillService = zcHtPrePayBillService;
  }

  public List getZcHtPrePayBillList(ElementConditionDto dto, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcHtPrePayBillService.getZcHtPrePayBillList(dto, meta);
  }

  public void deleteZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta) {
    zcHtPrePayBillService.deleteZcHtPrePayBillFN(zcHtPrePayBill, meta);
  }

  public ZcHtPrePayBill updateZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    return zcHtPrePayBillService.updateZcHtPrePayBillFN(zcHtPrePayBill, meta);
  }

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcHtPrePayBillService.selectByPrimaryKey(BillCode, requestMeta);
  }

  public void deleteZcHtPrePayBillListFN(List zcHtPrePayBillList, RequestMeta meta) {

    for (int i = 0; i < zcHtPrePayBillList.size(); i++) {
      ZcHtPrePayBill zcHtPrePayBill = (ZcHtPrePayBill) zcHtPrePayBillList.get(i);
      zcHtPrePayBillService.deleteZcHtPrePayBillFN(zcHtPrePayBill, meta);
    }

  }


public List getZcHtPrePayBillListByHtCode(ZcHtPrePayBill bean, RequestMeta meta) {
	// TCJLODO Auto-generated method stub
	return zcHtPrePayBillService.getZcHtPrePayBillListByHtCode(bean, meta);
}

}
