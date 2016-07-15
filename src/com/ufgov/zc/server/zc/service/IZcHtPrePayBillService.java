package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBill;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;

public interface IZcHtPrePayBillService {
  public List getZcHtPrePayBillList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta);

  public ZcHtPrePayBill updateZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta);

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode, RequestMeta requestMeta);
  public List getZcHtPrePayBillListByHtCode(ZcHtPrePayBill bean, RequestMeta meta);

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode);
}
