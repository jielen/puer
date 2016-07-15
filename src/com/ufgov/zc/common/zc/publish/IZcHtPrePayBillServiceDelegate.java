package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcHtPrePayBill;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;

public interface IZcHtPrePayBillServiceDelegate extends Publishable {
  public List getZcHtPrePayBillList(ElementConditionDto dto, RequestMeta meta);

  public void deleteZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta);

  public void deleteZcHtPrePayBillListFN(List zcHtPrePayBillList, RequestMeta meta);

  public ZcHtPrePayBill updateZcHtPrePayBillFN(ZcHtPrePayBill zcHtPrePayBill, RequestMeta meta);

  public ZcHtPrePayBill selectByPrimaryKey(String BillCode, RequestMeta requestMeta);
  public List getZcHtPrePayBillListByHtCode(ZcHtPrePayBill bean, RequestMeta meta);
}
